
-module(helloworld).
-export([echo/0]).
-export([fac/1]).
echo() ->
    io:format("Hello, world!~n").

fac(1) ->
    1;
fac(N) ->
    N*fac(N-1).
