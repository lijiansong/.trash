#include<cstdio>
int main()
{
	freopen("in.txt","r",stdin);
	/*long nc;
	nc=0;
	while(getchar()!=EOF) ++nc;
	printf("%ld\n",nc);*/
	double nc;
	for(nc=0;getchar()!=EOF;++nc);
	printf("%.0f\n",nc);
	
	return 0;
} 
