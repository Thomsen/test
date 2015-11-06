-module(server).

-export([start/2, rpc/2, call/2]).

start(Name, Mod) ->
    register(Name, spawn(fun() -> loop(Name, Mod, Mod:init()) end)).

rpc(Name, Mod) ->
    ok.

loop(Name, Mod, State) ->
    receive
        {From, {call, Request}} ->
            {Response, NewState} = Mod:handle_call(Request, State),
            From ! {Name, Response},
            loop(Name, Mod, NewState);
        {From, {cast, Request}}  ->
            NewState = Mod:handle_cast(Request, State),
            loop(Name, Mod, NewState)
    end.

call(Name, Request) ->
    Name ! {self(), {call, Request}}, % process communication
    receive {Name, Response} -> Response
    end.

cast(Name, Request) ->
    Name ! {self(), {call, Request}}.
