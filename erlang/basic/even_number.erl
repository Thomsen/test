-module(even_number).

-export([tail_loop/0, lists_func/0, list_comp/0]).

%% tail recursive
tail_loop()->
    tail_loop(get_num(), []).

tail_loop([], List) ->
    List;
tail_loop([F | Other], List) ->
    tail_loop(Other, List ++ (if F rem 2 == 0 -> [F]; true -> [] end)).

%% > [F | Other] = [0, 1, 2, 3].
%% [0, 1, 2, 3]
%% > F.
%% 0
%% > Other.
%% [1, 2, 3,]

%% list module
lists_func() ->
    %% foldl(Fun(Item, []), Acc, List)
    lists:foldl(fun(X, List) ->
                        if X rem 2 == 0 ->
                                List ++ [X];  %% [List|[X]]
                           true ->
                                List
                        end
                end, [], get_num()).

%% list analytical
list_comp() ->
    [X || X <- get_num(), X rem 2 == 0].

%% > [X || X <- [0, 1, 2, 3]].
%% [0,1,2,3]
%% > X.
%% * 1: variable 'X' is unbound

%% [Expr(E) || E <- List]
%% 'lc^0'([E | Tail], Expr) ->
%%     [Expr(E) | 'lc^0'(Tail, Expr)];
%% 'lc^0'([], _Expr) ->
%%     [].


get_num() ->
    lists:seq(0,9).
