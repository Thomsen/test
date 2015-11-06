-module(use_behav).

-behaviour(gen_benav).

-export([init/1, handle/2]).

init(State) ->
    io:format("init ~p ~n", [State]),
    State.

handle(Request, State) ->
    io:format("handle request: ~p state: ~p~n", [Request, State]),
    State2 = State + 1,
    {ok, State2}.

%% erlc gen_behav.erl
%% erlc use_behav.erl
%% erl
%% > gen_behav:start(use_behav).

%% > gen_behav:module_info().
%% > gen_behav:behaviour_info(callbacks).
