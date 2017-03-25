#include <iostream>
#include <cstring>
#include <algorithm>
#include <cstdio>
#include <vector>
using namespace std;
int main(int argc, char const *argv[])
{
	freopen("in.txt","r",stdin);
	vector<vector<int> > data(20,vector<int>(3,0));
	for(int i=0;i<20;++i)
	{
		for(int j=0;j<3;++j)
		{
			cin >> data[i][j];
		}
	}
	int head=data[0][0];
	int count=0;
	vector<double> result(3,0.0);
	int tmp_counter=0;
	for(int i=0;i<20;++i)
	{
		vector<int> tmp(data[i].begin(),data[i].end());
		if(head!=tmp[0])
		{
			cout<<"count: "<<count<<endl<<"avg: "<<result[1]/count<<endl<<"max:"<<result[2]<<endl;
			count=0;
			head=tmp[0];
			fill(result.begin(),result.end(),0.0);
		}
		++count;
		result[1]+=tmp[1];
		if(result[2]-tmp[2]<1e-6){
			result[2]=tmp[2];
		}
	}
	cout<<"count: "<<count<<endl<<"avg: "<<result[1]/count<<endl<<"max:"<<result[2]<<endl;
	count=0;
	//head=tmp[0];
	fill(result.begin(),result.end(),0.0);
	return 0;
}