-module(gen_behav).

-export([start/1, stop/0]).

%% -export([behaviour_info/1]).
%% behaviour_info(callbacks) ->
%%     [{init, 1}, {handle, 2}];
%% behaviour_info(_Other) ->
%%     undefined.

-type opts() :: [{atom(), any()}].

-callback init(binary()) ->
    any().
-callback handle(binary(), opts()) ->
    any().

start(Mod) ->
    State = Mod:init(0),
    {ok, State2} = Mod:handle(add, State),
    io:format("State : ~p ~n", [State2]).

stop() ->
    stop.
