%{
int chars = 0;
int words = 0;
int lines = 0;
%}

%%

[^ \t\n\r\f\v]+ { words++; chars += strlen(yytext); }
\n { chars++; lines++; }
. { chars++; }

%%

int main (int argc, char* argv [])
{
yylex ();
printf ("%4d line,%4d word,%4d char\n", lines, words, chars);
return 0;
}

// flex wc.l generator lex.yy.c
// gcc -o wc lex.yy.c -lfl
// cat wc.lo | ./wc

//[a-zA-Z]+ { words++; chars += strlen(yytext); }
