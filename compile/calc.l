// definitions
%{
#define YYSTYPE double
#include "calc.tab.h"
#ifdef CALC_LEX
YYSTYPE yylval;
#endif
%}

%%

// rules
"+"                                { return ADD; }
"-"                                { return SUB; }
"*"                                { return MUL; }
"/"                                { return DIV; }
"("                                { return ABS; }
")"                                { return OP; }
"|"                                { return CP; }
\n                                 { return EOL; }
[ t]                               { /* ignore */ }
([0-9]*\.?[0-9]+|[0-9]+\.[0-9]*)  { yylval = atof(yytext); return NUMBER; }

%%

// user code
#ifdef CALC_LEX
int main (int argc, char **argv)
{
  int token;
  while (token == yylex())
  {
    printf("%d", token);
    if (token == NUMBER) {
      printf(" = %f\n", yylval);
    } else {
      printf("\n");
    }
  }
  return 0;
}
#endif
