import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class StringParse {
	private static class Value{
		int count;
		double []result=new double[3];
	}
	public static void main(String[] args) throws IOException {
		if(args.length==3){
		}
		else
		{
			String str="count";
			System.out.println(str.substring(0, 5).equals("count"));
			str="avg(R3)";
			int indexR=str.indexOf("R");
			int indexRight=str.indexOf(")");
			//System.out.println(indexR);
			String subStr=str.substring(indexR+1,indexRight);
			//System.out.println(subStr);
			int columnNum=Integer.parseInt(subStr);
			System.out.println(columnNum);
			
			String testStr="groupby:R2";
			String groupByNumberStr=testStr.substring(testStr.indexOf("R")+1);
			int groupByNumber=Integer.parseInt(groupByNumberStr);
			System.out.println(groupByNumber);
			testStr="res:count,avg(R3),max(R4)";
			System.out.println(testStr.split(":")[0]);
			
			Map<Integer,Integer> opMap =new HashMap<Integer, Integer>();
			String []operation=testStr.split(":")[1].split(",");
			for(int i=0;i<operation.length;++i){
				if(operation[i].substring(0, 5).equals("count")){
					//count
					opMap.put(1, 0);
				}
				else if(operation[i].substring(0, 3).equals("avg")){
					//avg(R3)
					int _indexR=operation[i].indexOf("R");
					int _indexRight=operation[i].indexOf(")");
					int colNum=Integer.parseInt(operation[i].substring(_indexR+1, _indexRight));
					opMap.put(2, colNum);
				}
				else if(operation[i].substring(0, 3).equals("max")){
					//max(R4)
					int _indexR=operation[i].indexOf("R");
					int _indexRight=operation[i].indexOf(")");
					int colNum=Integer.parseInt(operation[i].substring(_indexR+1, _indexRight));
					opMap.put(3, colNum);
				}//else error
			}
			System.out.println("opMap: ");
			for(Map.Entry<Integer, Integer> entry:opMap.entrySet()){
				System.out.println(entry.getKey()+" "+entry.getValue());
			}
			
			Map<Integer, Integer> newOpMap=new HashMap<Integer,Integer>();
			Hashtable<String, Value> hashtable=new Hashtable<String,Value>();
			//java Hw1Grp3 R=/hw1/lineitem.tbl groupby:R2 res:count,avg(R3),max(R4)
			final ArrayList<ArrayList<String> > dataList=new ArrayList<ArrayList<String> >();
			final String path="/home/json-lee/workdir/java/hadoop/neon1-workspace/StringParser/src/test.txt";
			File file=new File(path);
			if(file.isFile()&&file.exists()){
				BufferedReader reader=new BufferedReader(new FileReader(file));
				String line;
				while((line=reader.readLine())!=null){
//					ArrayList<String> lineTmp=new ArrayList<String>();
//					String []oneLine=line.split("\\|");
//					lineTmp.add(oneLine[groupByNumber]);//groupbykey column
//					//newOpMap.put(0, 0);
//					int opCounter=1;
//					for(Map.Entry<Integer, Integer> entry:opMap.entrySet()){
//						if(entry.getKey()==1){
//							newOpMap.put(1, 0);
//							continue;
//						}
//						lineTmp.add(oneLine[entry.getValue()]);
//						if(!newOpMap.containsKey(entry.getKey())){
//							newOpMap.put(entry.getKey(), opCounter);
//							++opCounter;
//						}
//					}
//					dataList.add(lineTmp);
					String []oneLine=line.split("\\|");
					Value val=new Value();
					val.count=0;
					Arrays.fill(val.result, 0);
					//val.result[2]=Double.MIN_VALUE;
					if(hashtable.containsKey(oneLine[groupByNumber])){
						val.count=hashtable.get(oneLine[groupByNumber]).count+1;
						for(Map.Entry<Integer, Integer> entry:opMap.entrySet()){
							switch (entry.getKey()) {
//							case 1://count
//								val.result[0]
//								break;
							case 2://avg
								val.result[1]=hashtable.get(oneLine[groupByNumber]).result[1]+Double.valueOf(oneLine[entry.getValue()]);
								break;
							case 3://max
								val.result[2]=Math.max(hashtable.get(oneLine[groupByNumber]).result[2], Double.valueOf(oneLine[entry.getValue()]));
								break;
							default:
								break;
							}
						}
					}else{//not contains
						val.count=1;
						for(Map.Entry<Integer, Integer> entry:opMap.entrySet()){
							switch (entry.getKey()) {
							case 2://avg
								val.result[1]=Double.valueOf(oneLine[entry.getValue()]);
								break;
							case 3://max
								val.result[2]=Double.valueOf(oneLine[entry.getValue()]);
								break;
							default:
								break;
							}
						}
					}
					hashtable.put(oneLine[groupByNumber], val);
				}
				reader.close();
				
				//show result
				for(Entry<String, Value> entry:hashtable.entrySet()){
					for(Map.Entry<Integer, Integer> mapEntry:opMap.entrySet()){
						switch (mapEntry.getKey()) {
						case 1:
							System.out.println("count: "+entry.getValue().count);
							break;
						case 2:
							System.out.println("avg(R"+mapEntry.getValue()+"): "+new DecimalFormat("##0.00").format(entry.getValue().result[1]/entry.getValue().count));
							break;
						case 3:
							System.out.println("max(R"+mapEntry.getValue()+"): "+entry.getValue().result[2]);
							break;
						default:
							break;
						}
					}
				}
				
//				System.out.println("newOpMap: ");
//				for(Map.Entry<Integer, Integer> entry:newOpMap.entrySet()){
//					System.out.println(entry.getKey()+" "+entry.getValue());
//				}
				//sort
//				Collections.sort(dataList, new Comparator<ArrayList<String>>() {
//
//					@Override
//					public int compare(ArrayList<String> o1, ArrayList<String> o2) {
//						// TODO Auto-generated method stub
//						//return 0;
//						return o1.get(0).compareTo(o2.get(0));
//					}
//				});
				
//				System.out.println("after sorting: ");
//				for(ArrayList<String> al:dataList){
//					for(String data:al){
//						System.out.print(data+" ");
//					}
//					System.out.println();
//				}
				
				//String tmp=dataList.get(0).get(groupByNumber);
//				String tmp=dataList.get(0).get(0);
//				//System.out.println(tmp);
//				int count=0;
//				//System.out.println(newOpMap.size());
//				//double []result=new double[newOpMap.size()];
//				double []result=new double[3];
//				Arrays.fill(result, 0);
//				result[2]=Double.MIN_VALUE;
//				
//				for(ArrayList<String> tmpList:dataList){
//					if(!tmp.equals(tmpList.get(0))){//different
//						//display the result
//						for(Map.Entry<Integer, Integer> entry:newOpMap.entrySet()){
//							switch (entry.getKey()) {
//							case 1:
//								System.out.println("count: "+count);
//								break;
//							case 2:
//								System.out.println("avg(R"+opMap.get(entry.getKey())+"): "+new DecimalFormat("##0.00").format(result[entry.getKey()]/count));
//								break;
//							case 3:
//								System.out.println("max(R"+opMap.get(entry.getKey())+ "): "+result[2]);
//								break;
//							default:
//								break;
//							}
//						}
//						count=0;
//						tmp=tmpList.get(0);
//						Arrays.fill(result, 0);
//						result[2]=Double.MIN_VALUE;
//					}
//					
//					++count;
//					//scan newOpMap
//					for(Map.Entry<Integer, Integer> entry:newOpMap.entrySet()){
//						switch (entry.getKey()) {
//
//						case 2:
//							result[1]+=Double.valueOf(tmpList.get(entry.getValue()));
//							break;
//						case 3:
//							if(result[2] - Double.valueOf(tmpList.get(entry.getValue())) < 1e-6)
//								result[2]=Double.valueOf(tmpList.get(entry.getValue()));
//							break;
//						default:
//							break;
//						}
//					}
//					
//				}//end of for Map iterator
//				for(Map.Entry<Integer, Integer> entry:newOpMap.entrySet()){
//					switch (entry.getKey()) {
//					case 1:
//						System.out.println("count: "+count);
//						break;
//					case 2:
//						System.out.println("avg: "+new DecimalFormat("##0.00").format(result[1]/count));
//						break;
//					case 3:
//						System.out.println("max: "+result[2]);
//						break;
//					default:
//						break;
//					}
//				}
//				count=0;
//				Arrays.fill(result, 0);
//				result[2]=Double.MIN_VALUE;
			}
			else{
				System.out.println("File doesn't exist!");
			}	
		}
	}

}
