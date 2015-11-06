-module(cat_fsm).

-export([start/0, event/2]).

start() ->
    spawn(fun() -> dont_give_crap() end).

event(Pid, Event) ->
    Ref = make_ref(),
    Pid ! {self(), Ref, Event},
    receive
        {Ref, Msg} ->
            {ok, Msg}
    after 5000 ->
            {error, timeout}
    end.

dont_give_crap() ->
    receive
        {Pid, Ref, Msg} ->
            Pid ! {Ref, meh};
        _ ->
            ok
    end,
    io:format("Switching to 'dont_give_crap' state ~n"),
    dont_give_crap().


%% > c(cat_fsm).
%% cat_fsm.erl:20: Warning: variable 'Msg' is unused
%% {ok,cat_fsm}
%% > Cat = cat_fsm:start().
%% <0.43.0>
%% > cat_fsm:event(Cat, pet).
%% Switching to 'dont_give_crap' state
%% {ok,meh}
