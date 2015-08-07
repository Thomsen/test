-module(andprocremote).
-export([start/1, ping/2, pong/0]).

ping(0, Pong_Node) ->
    {pong, Pong_Node} ! finished,
    io:format("ping finished~n", []);
ping(N, Pong_Node) ->
    {pong, Pong_Node} ! {ping, self()},
    receive
        pong ->
            io:format("ping received pong~n", [])
    end,
    ping(N - 1, Pong_Node).

pong() ->
    receive
        finished ->
            io:format("pong finished~n", []);
        {ping, Ping_PID} ->
            io:format("pong received ping~n", []),
            Ping_PID ! pong,
            pong()
    end.

start(Pong_Node) ->
    register(pong, spawn(andproc, pong, [])),
    spawn(andproc, ping, [3, Pong_Node]).
