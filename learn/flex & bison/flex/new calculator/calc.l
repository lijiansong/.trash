%{
	/*enum yytokentype{
		NUMBER=258,
		ADD=259,
		SUB=260,
		MUL=261,
		DIV=262,
		ABS=263,
		EOL=264
	};*/
	#include"calc.tab.h"
	int yylval;
%}

%%
"+" {return ADD;}
"-" {return SUB;}
"*" {return MUL;}
"/" {return DIV;}
"|" {return ABS;}
[0-9]+ {yylval=atoi(yytext);return NUMBER;}
\n {return EOL;}
[ \t] {/*ignore the space symbol*/}
. {printf("Invalid character %c\n",*yytext);}
%%
/*int main(int argc,char *argv[])
{
	freopen("in.txt","r",stdin);
	freopen("out.txt","w",stdout);
	int tok;
	while(tok=yylex())
	{
		printf("%d",tok);
		if(tok==NUMBER)
			printf(" =%d\n",yylval);
		else
			printf("\n");
	}
	fclose(stdin);
	fclose(stdout);
	return 0;
}*/
int yywrap(void)
{
	return 1;
}
