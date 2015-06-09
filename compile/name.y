%
typedef char* string;
#define YYSTYPE string
%}
%token NAME EQ AGE
%%
file : record file
| record
;
record : NAME EQ AGE {
  printf("%c is %s years old!!!n", $1, $3); }
;
%%
int main()
{
  yyparse();
  return 0;
}
int yyerror(char* msg)
{
  printf("error encountered: %sn", msg);
}
