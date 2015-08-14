
// npm install xmlhttprequest (diff  w3c-xmlhttprequest)
var XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest;

var getJSON = function(url) {
  var promise = new Promise(function(resolve, reject) {
    let client = new XMLHttpRequest();
    client.open("GET", url);
    client.onreadystatechange = handler;
    client.responseType = "json";
    client.setRequestHeader("Accept", "application/json");
    client.send();

    function handler() {
      let state = this.readyState;
      let body = this.responseText;
      //console.log("state = " + state + " \nbody: " + body);
      if (state === 4) {
        resolve(body);
      } else if (state === 3) {
        console.log("state = LOADING");        
      } else if (state === 2) {
        console.log("state = HEADERS_RECEIVED");        
      } else if (state === 1) {
        console.log("state = OPENED");
      } else {
        reject("state = UNSENT");
      }
    }

  });
  return promise;
};

//var url = "http://api.weibo.com/2/statuses/public_timeline.json";
var url = "http://127.0.0.1:8888/";
getJSON(url).then(function(json) {
  console.log("Contents: " + json);
}, function(error) {
  console.log("error", JSON.stringify(error));
});

// cd ../../node && node server.js
// iojs [debug] --use_strict promisedemo.js


var promise2 = new Promise(function(resolve, reject) {
  throw new Error('test');
});

promise2.catch(function(error) {
  console.log(error);
});

// all race

var timeoutLog = function(msg) {
  var promise = new Promise(function(resolve, reject) {
    setTimeout(function() {
      console.log(msg);
      resolve(msg);
    }, 2000);
  });
  return promise;
}

var promises = [1, 2, 3, 4, 5, 6].map(function(id) {
  return timeoutLog(id);
});

Promise.all(promises).then(function(posts) {
  console.log(posts);
}).catch(function(reason) {
  console.log(reason);
});

Promise.race(promises).then(function(posts) {
  console.log(posts);
}).catch(function(reason) {
  console.log(reason);
});

// resolve reject

var p = Promise.resolve('Hello');

p.then(function (s) {
  console.log(s);
});

var pr = Promise.reject('reject error');
pr.then(null, function (s) {
  console.log(s);
});
