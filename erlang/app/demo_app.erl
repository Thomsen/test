-module(demo_app).

-behaviour(application).

-export([start/2, stop/1]).

start(_Type, StartArgs) ->
    io:format("demo app start ~n"),
    case demo_sup:start_link(StartArgs) of
        {ok, Pid} ->
            {ok, Pid};
        Error ->
            Error
    end.

stop(_State) ->
    ok.

%% >c(demo_sup).
%% >c(demo_app).
%% >application:start(demo).
%% >application:loaded_applications().
