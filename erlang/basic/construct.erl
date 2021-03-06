-module(construct).

-export([test_if/2, month_length/2]).

test_if(A, B) ->
    if A==5 ->
            io:format("A=5~n", []),
            a_equals_5;
       B==6 ->
            io:format("B=6~n", []),
            b_equals_6;
       A==2, B==3 ->
            io:format("A==2. B==3~n", []),
            a_equals_2_b_equals_3;
       A==1 ; B==7 ->
            io:format("A==1 : B==7~n", []),
            a_equals_1_or_b_equals_7
    end.

month_length(Year, Month) ->
    Leap = if
               trunc(Year / 400) * 400 == Year ->
                   leap;
               trunc(Year / 100) * 100 == Year ->
                   not_leap;
               trunc(Year / 4) * 4 == Year ->
                   leap;
               true ->
                   not_leap
           end,

    case Month of
        sep ->
            30;
        apr ->
            30;
        jun ->
            30;
        nov ->
            30;
        feb when Leap == leap ->
            29;
        feb ->
            28;
        jan ->
            31;
        mar ->
            31;
        may ->
            31;
        jul ->
            31;
        aug ->
            31;
        oct ->
            31;
        dec ->
            31
    end.
