%%
"colour"	{printf("color");}
"flavour"	{printf("flavor");}
"favourite"	{printf("favorite");}
.	{printf("%s", yytext);}
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
