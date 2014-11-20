
require 'cgi'

cgi = CGI.new("html4")

cookie = cgi.cookies['mycookie']

cgi.out('cookie' => cookie) do
  cgi.head + cgi.body { cookie[0] }
end
