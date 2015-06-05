// hello.cc
#include <node.h>

using namespace v8;  // node 0.10

Handle<Value> Method(const Arguments& args) {
  HandleScope scope;
  return scope.Close(String::New("world"));
}

void init(Handle<Object> exports) {
  exports->Set(String::NewSymbol("hello"),
               FunctionTemplate::New(Method)->GetFunction());
}

NODE_MODULE(addon, init); // common with binding.gyp target_name, otherwise symbol addon_module not found
