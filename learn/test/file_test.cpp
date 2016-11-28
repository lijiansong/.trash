#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
int main(int argc,char *argv[])
{
	if(argc==2)
	{
		string line;
		ifstream infile;
		//training data
		infile.open(argv[1]);
		int count=0,col_count=0;
		if(infile.is_open())
		{
			while(getline(infile,line) && line!="")
			{
				//cout<<line;
				++count;
				char *token = strtok((char *)line.c_str(), ",");
				while(token)
				{
					cout<<*token<<endl;
					++col_count;
					token=strtok(NULL,",");
				}
				//cin.get();
			}
			infile.close();
		}
		else
		{
			cout<<"Error: connot open "<<argv[1]<<endl;
		}
		cout<<count<<" "<<col_count<<endl;
	}
	return 0;
}
