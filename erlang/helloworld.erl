
-module(helloworld).
-export([echo/0]).
echo() ->
    io:format("Hello, world!~n").

