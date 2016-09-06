%%
"+" {printf("PLUS\n");}
"-" {printf("MINUS\n");}
"*" {printf("TIMES\n");}
"/" {printf("DIVIDE\n");}
"|" {printf("ABS\n");}
[0-9]+ {printf("NUMBER %s\n",yytext);}
\n {printf("NEW LINE\n");}
[ \t] {}
. {printf("Invalid character %s\n",yytext);}
%%
int main(int argc,char *argv[])
{
	freopen("in.txt","r",stdin);
	freopen("out.txt","w",stdout);
	yylex();
	
	fclose(stdin);
	fclose(stdout);
	return 0;
}
int yywrap(void)
{
return 1;
}
