$LOAD_PATH << '.'

#!/usr/bin/env ruby
require 'protobuf/rpc/client'
require 'rpc.pb'

# build request
request = Rpcser::SearchRequest.new
request.keyword = 'baidu'
#raise StandardError, 'setup a request'

# create blank response
response = Rpcser::SearchResponse.new

# execute rpc
Protobuf::Rpc::Client.new('localhost', 9999).call :search, request, response

# show response
puts response

