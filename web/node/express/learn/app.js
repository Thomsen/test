var express = require('express');

var utility = require('utility');

var app = express();

app.get('/', function(req,res) {
  //res.send('hello express'); // not send to headers if not annotation


  var q = req.query.q;

  if (q) {
    var md5value = utility.md5(q);
    res.send(md5value);
  } else {
    res.send('need q paramater');
  }

});

var superagent = require('superagent');
var cheerio = require('cheerio');

app.get('/cnode', function(req, res) {
  superagent.get('https://cnodejs.org')
    .end(function(err, sres) {
      if (err) {
        return next(err);
      }

      var $ = cheerio.load(sres.text);
      var items = [];
      $('#topic_list .topic_title').each(function(idx, element) {
        var $element = $(element);
        items.push({
          title: $element.attr('title'),
          href: $element.attr('href')
        });
      });

      res.send(items);
    })


});

var url = require('url');
var eventproxy = require('eventproxy');

var cnodeUrl = 'https://cnodejs.org';

var async = require('async');

superagent.get(cnodeUrl)
  .end(function(err, res) {
    if (err) {
      return console.error(err);
    }

    var topicUrls = [];
    var $ = cheerio.load(res.text);

    $('#topic_list .topic_title').each(function (idx, element) {
      var $element = $(element);

      var href = url.resolve(cnodeUrl, $element.attr('href'));
      topicUrls.push(href);
    });

    console.log(topicUrls);

    // eventproxy

    var ep = new eventproxy();

    ep.after('topic_html', topicUrls.length, function(topics) {
      topics = topics.map(function(topicPair) {
        var topicUrl = topicPair[0];
        var topicHtml = topicPair[1];
        var $ = cheerio.load(topicHtml);
        return ({
          title: $('.topic_full_title').text().trim(),
          href: topicUrl,
          comment1: $('.reply_content').eq(0).text().trim()
        });
      });

      console.log('eventproxy final: ');
      console.log(topics);
    });

    topicUrls.forEach(function (topicUrl) {
      superagent.get(topicUrl)
        .end(function(err, res) {
          console.log('fetch ' + topicUrl + ' successful');
          ep.emit('topic_html', [topicUrl, res.text]);
        })
    })

    // async
    var concurrencyCount = 0;

    var fetchUrl = function(url, callback) {
      var delay = parseInt((Math.random() * 1000000) % 2000, 10);
      concurrencyCount ++;
      console.log('current concurrency amount: ', concurrencyCount, ', fetch ', url);
      superagent.get(url)
        .end(function(err, res) {
          console.log('fetch ', url, " end");
          var $ = cheerio.load(res.text);
          var topic = {
            title: $('.topic_full_title').text().trim(),

          }
          callback(null, topic);
        });
    };

    async.mapLimit(topicUrls, 5, function(url, callback) {
      fetchUrl(url, callback);
    }, function(err, result) {
      console.log('async final: ');
      console.log(url, ' html content: ', result);
    })

  });


app.listen(3000, function() {
  console.log('app is listening at port 3000');
});
