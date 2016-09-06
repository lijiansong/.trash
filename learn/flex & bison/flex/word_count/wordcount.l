%{
int chars=0;
int words=0;
int lines=0;
%}
%%
[^ \t\n\r\f\v]+	{words++;chars+=strlen(yytext);}
\n	{chars++;lines++;}
.	{chars++;}
%%
int main(int argc,char *argv[])
{
	freopen("in.txt","r",stdin);
	freopen("out.txt","w",stdout);
	yylex();
	printf("lines: %d\nwords: %d\nchars: %d\n",lines,words,chars);
	fclose(stdin);
	fclose(stdout);
	return 0;
}
int yywrap(void)
{
return 1;
}

