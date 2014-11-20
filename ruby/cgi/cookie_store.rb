
require 'cgi'

cgi = CGI.new('name' => 'mycookie',
              'value' => 'user id',
              'expires' => Time.now + 3600)

cgi.out('cookie' => cookie) do
  cgi.head + cgi.body { 'Cookie stored' }
end
