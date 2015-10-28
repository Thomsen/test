-module(thread).

-export([startsay/0, say_something/2, start/0, ping/2, pong/0]).

say_something(What, 0) ->
    done;
say_something(What, Times) ->
    io:format("~p~n", [What]),
    say_something(What, Times-1).

startsay() ->
    spawn(thread, say_something, [hello, 3]),
    spawn(thread, say_something, [goodbye, 3]).

ping(0, Pong_PID) ->
    Pong_PID ! finished,
    io:format("ping finish~n", []);
ping(N, Pong_PID) ->
    Pong_PID ! {ping, self()},
    receive
        pong ->
            io:format("Ping received pong~n", [])
    end,

    ping(N-1, Pong_PID).

pong() ->
    receive
        finished ->
            io:format("Pong finished~n", []);
        {ping, Ping_PID} ->
            io:format("Pong received ping~n", []),
            Ping_PID ! pong,
            pong()
    end.

start() ->
    Pong_PID = spawn(thread, pong, []),
    spawn(thread, ping, [3, Pong_PID]).
