var http=require('http');
http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    res.end('Hello Node.jsn');
}).listen(8214, '127.0.0.1');
console.log('Server runnint at http://127.0.0.1:8214/');
