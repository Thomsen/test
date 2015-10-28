-module(demo_sup).

-behaviour(supervisor).

-export([start_link/1, init/1]).

start_link(_) ->
    io:format("demo app start link ~n"),
    supervisor:start_link({local, ?MODULE}, ?MODULE, []).

init([]) ->
    io:format("demo sup init ~n"),
    {ok, {
       { one_for_one, 1, 3600}, []}}.
