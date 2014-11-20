
require 'cgi'

cgi = CGI.new

puts cgi.header
puts "<html><body>hello cgi</body></html>"
