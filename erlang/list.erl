-module(list).

-export([max/1, min/1]).

max([Head | Rest]) ->
    max(Rest, Head).

max([], Res) ->
    Res;
max([Head | Rest], Result_so_far) when Head > Result_so_far ->
    max(Rest, Head);
max([Head | Rest], Result_so_far) ->
    max(Rest, Result_so_far).

min([Head | Rest]) ->
    min(Rest, Head).

min([], Tmp) ->
    Tmp;
min([Head | Rest], Result_so_near) when Head < Result_so_near ->
    min(Rest, Head);
min([Head | Rest], Result_so_near) ->
    min(Rest, Result_so_near).
