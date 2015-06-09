%{
#define YYSTYPE double
#include "calc.tab.h"
#ifdef CALC_LEX
YYSTYPE yylval;
#endif
%}

%%

"+"                                { return ADD; }
"-"                                { return ADD; }
"*"                                { return ADD; }
"/"                                { return ADD; }
"("                                { return ADD; }
")"                                { return ADD; }
"|"                                { return ADD; }
\n                                 { return EOL; }
[ t]                               { /* ignore */ }
([0-9]*\.?[0-9]+|[0-9]+\.[0-9]*)  { yylval = atof(yytext); return NUMBER; }

%%

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
