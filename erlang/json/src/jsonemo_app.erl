-module(jsonemo_app).

-behaviour(application).

%% Application callbacks
-export([start/2, stop/1]).

-export([hello/0]).

%% ===================================================================
%% Application callbacks
%% ===================================================================

start(_StartType, _StartArgs) ->
    jsonemo_sup:start_link().

stop(_State) ->
    ok.

hello() ->
    io:format("Hello Jsonemo! ~n").


-ifdef(TEST).
-include("jsonemo_test.hrl").
-endif.
