#include "json/json.h"
#include <string>
#include <iostream>
using namespace std;
int main()
{
 string test ="{\"id\":1,\"name\":\"json-lee\"}";
 Json::Reader reader;
 Json::Value value;
 if(reader.parse(test,value))
 {
  if(!value["id"].isNull())
  {
   cout<<"id: "<<value["id"].asInt()<<endl;
   cout<<"name: "<<value["name"].asString()<<endl;
  }
 }
 return 0;
}
