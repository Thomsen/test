-module(tuple_record).

-export([test1/0, test2/0]).

-record(person, {name, age, phone}).

% #person{name="Joe", age="21", phone="999-999"}.

birthday(P) ->
    P#person{age = P#person.age + 1}.

joe() ->
    #person{name="Joe", age=21, phone="999-999"}.

showPerson(#person{name=Name, age=Age, phone=Phone}) ->
    io:format("name: ~p age: ~p phone: ~p~n", [Name, Age, Phone]).

test1() ->
    showPerson(joe()).

test2() ->
    showPerson(birthday(joe())).
