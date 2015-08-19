-module(flatlist). % 模块声明，必须出现在其他声明或代码之前

-export([flat_length/1]). % - 属性前缀，声明函数并将函数导出

%% flat_length(List) % '以%开头的为注释'
%% Calculate the length of a list of lists.

flat_length(List) -> % 函数定义，子句头部
    flat_length(List, 0). % 由单个子句组成，子句主体

flat_length([H|T], N) when list(H) -> % 三个子句（局部函数），以';'分隔，'.'结尾。H为列表头部，T为列表尾部。list(H)称作保护式。
    flat_length(H, flat_length(T, N)); % 只有在参数于函数头部的模式相匹配且保护式断言成立时，函数体才会被求值。保护子句
flat_length([H|T], N) ->
    flat_length(T, N+1); % 无保护子句
flat_length([], N) ->
    N. % 无保护子句
