var http = require('http');
var fs = require('fs');

var weibojson = fs.readFileSync('weibo.json', 'utf-8');

http.createServer(function(request, response) {
  response.writeHead(200, {"Content-Type": "text/html"});
  response.write('<head><meta charset="utf-8"/></head>');
  response.write(weibojson);
  response.end();
}).listen(8888);

console.log('Server running at http://127.0.0.1:8888/');
