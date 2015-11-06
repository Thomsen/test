-include_lib("eunit/include/eunit.hrl").

my_test() ->
    ?assert(1 + 2 =:= 3).

simple_test() ->
    ok = application:start(jsonemo),
    ?assertNot(undefined =:= whereis(jsonemo_sup)).
