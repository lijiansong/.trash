#include <iostream>
#include <fstream>
#include <sstream>
#include <cstring>
#include <vector>
#include <string>
#include <cstdlib>
using namespace std;
int main(int argc,char *argv[])
{
	// if(argc==2)
	// {
	// 	string line;
	// 	ifstream infile;
	// 	//training data
	// 	infile.open(argv[1]);
	// 	int count=0,col_count=0;
	// 	if(infile.is_open())
	// 	{
	// 		while(getline(infile,line) && line!="")
	// 		{
	// 			//cout<<line;
	// 			++count;
	// 			char *token = strtok((char *)line.c_str(), ",");
	// 			while(token)
	// 			{
	// 				cout<<*token<<endl;
	// 				++col_count;
	// 				token=strtok(NULL,",");
	// 			}
	// 			//cin.get();
	// 		}
	// 		infile.close();
	// 	}
	// 	else
	// 	{
	// 		cout<<"Error: connot open "<<argv[1]<<endl;
	// 	}
	// 	cout<<count<<" "<<col_count<<endl;
	// }
	vector<string> v;
	double a=100.10;
	stringstream ss;
	ss<<a;
	string str;
	ss>>str;
	ss.clear();
	v.push_back(str);
	cout<<v[0]<<endl;
	double val=atof(v[0].c_str());
	cout<<val<<endl;
	return 0;
}