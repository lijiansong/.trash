#include<cstdio>
int main()
{
	freopen("in.txt","r",stdin);
	int c,nl;
	nl=0;
	while((c=getchar())!=EOF)
	{
		if(c=='\n') ++nl;
	}
	printf("%d\n",nl);
	return 0;
} 
