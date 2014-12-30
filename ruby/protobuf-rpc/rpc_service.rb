require 'protobuf/rpc/server'
require 'protobuf/rpc/handler'
require 'rpc.pb'

class Rpcser::SearchHandler < Protobuf::Rpc::Handler
  request Rpcser::SearchRequest
  response Rpcser::SearchResponse

  def self.process_request(request, response)
    if request.keyword == 'baidu'
      response.result = 'www.baidu.com'
    elsif request.keyword == 'github'
      response.result = 'www.github.com'
    else
      response.result = ''
    end
  end
end

class Rpcser::RpcService < Protobuf::Rpc::Server
  def setup_handlers
    @handlers = {
                 :search => Rpcser::SearchHandler,
                }
  end
end

