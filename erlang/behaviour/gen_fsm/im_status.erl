%% online offline qme busy invisible leave dndst
-module(im_status).

-behaviour(gen_fsm).

-export([start_link/0, login/2, logout/1]).

-export([init/1, static/2, moving/2, handle_event/3, handle_sync_event/4, handle_info/3, terminate/3, code_change/4]).

-define(SERVER, ?MODULE).

-spec(start_link(Im::string()) -> {ok, pid()} | ignore | {error, term()}).

start_link(Im) ->
    gen_fsm:start_link({local, ?SERVER}, ?MODULE, [], []).

init([]) ->
    io:format("init status offline", []),
    ok.

static(Event, State) ->
    ok.

moving(Event, State) ->
    ok.

handle_event(_Event, StateName, State) ->
    ok.

handle_sync_event(_Event, _From, StateName, State) ->
    Reply = ok,
    {reply, Reply, StateName, State}.

handle_info(_Info, StateName, State) ->
    ok.

terminate(_Reason, _StateName, _State) ->
    ok.

code_change(_OldVsn, StateName, State, _Extra) ->
    ok.
