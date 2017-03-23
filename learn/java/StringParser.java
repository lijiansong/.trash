import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StringParser {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length==3){
			//java Hw1Grp3 R=/hw1/lineitem.tbl groupby:R2 res:count,avg(R3),max(R4)
			String cmdStr="Hw1Grp3 R=/hw1/lineitem.tbl groupby:R2 res:count,avg(R3),max(R4)";
			String fileName=args[0].substring(2);
			int groupByNo=Integer.parseInt(args[1].substring(args[1].indexOf("R")+1));
			
			//res
			String columnFamily=args[2].split(":")[0];
			String []operation=args[2].split(":")[1].split(",");
			Map<Integer,Integer> map =null;//key is count avg or max, value is the columnNo
			for(int i=0;i<operation.length;++i){
				if(operation[i].substring(0, 5).equals("count")){
					//count
					map.put(0, 0);
				}
				else if(operation[i].substring(0, 3).equals("avg")){
					//avg(R3)
					int indexR=operation[i].indexOf("R");
					int indexRight=operation[i].indexOf(")");
					int colNum=Integer.parseInt(operation[i].substring(indexR+1, indexRight));
					map.put(1, colNum);
				}
				else if(operation[i].substring(0, 3).equals("max")){
					//max(R4)
					int indexR=operation[i].indexOf("R");
					int indexRight=operation[i].indexOf(")");
					int colNum=Integer.parseInt(operation[i].substring(indexR+1, indexRight));
					map.put(2, colNum);
				}
			}
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
					opMap.put(0, 0);
				}
				else if(operation[i].substring(0, 3).equals("avg")){
					//avg(R3)
					int _indexR=operation[i].indexOf("R");
					int _indexRight=operation[i].indexOf(")");
					int colNum=Integer.parseInt(operation[i].substring(_indexR+1, _indexRight));
					opMap.put(1, colNum);
				}
				else if(operation[i].substring(0, 3).equals("max")){
					//max(R4)
					int _indexR=operation[i].indexOf("R");
					int _indexRight=operation[i].indexOf(")");
					int colNum=Integer.parseInt(operation[i].substring(_indexR+1, _indexRight));
					opMap.put(2, colNum);
				}//else error
			}
			System.out.println("opMap: ");
			for(Map.Entry<Integer, Integer> entry:opMap.entrySet()){
				System.out.println(entry.getKey()+" "+entry.getValue());
			}
			
			Map<Integer, Integer> newOpMap=new HashMap<Integer,Integer>();
			//java Hw1Grp3 R=/hw1/lineitem.tbl groupby:R2 res:count,avg(R3),max(R4)
			final ArrayList<ArrayList<String> > dataList=new ArrayList<ArrayList<String> >();
			final String path="/home/json-lee/workdir/java/hadoop/neon1-workspace/StringParser/src/test.txt";
			File file=new File(path);
			if(file.isFile()&&file.exists()){
				BufferedReader reader=new BufferedReader(new FileReader(file));
				String line;
				while((line=reader.readLine())!=null){
					ArrayList<String> lineTmp=new ArrayList<String>();
					String []oneLine=line.split("\\|");
					lineTmp.add(oneLine[groupByNumber]);//groupbykey column
					newOpMap.put(0, 0);
					int opCounter=1;
					for(Map.Entry<Integer, Integer> entry:opMap.entrySet()){
						if(entry.getKey()==0)
							continue;
						lineTmp.add(oneLine[entry.getValue()]);
						if(!newOpMap.containsKey(entry.getKey())){
							newOpMap.put(entry.getKey(), opCounter);
							++opCounter;
						}
					}
					dataList.add(lineTmp);
				}
				System.out.println("newOpMap: ");
				for(Map.Entry<Integer, Integer> entry:newOpMap.entrySet()){
					System.out.println(entry.getKey()+" "+entry.getValue());
				}
//				for(ArrayList<String> list : dataList){
//					for(String data:list){
//						System.out.println(data);
//					}
//				}
				Collections.sort(dataList, new Comparator<ArrayList<String>>() {

					@Override
					public int compare(ArrayList<String> o1, ArrayList<String> o2) {
						// TODO Auto-generated method stub
						//return 0;
						return o1.get(0).compareTo(o2.get(0));
					}
				});
				for(ArrayList<String> al:dataList){
					for(String data:al){
						System.out.print(data+" ");
					}
					System.out.println();
				}
				
				//get count
				//String tmp=dataList.get(0).get(groupByNumber);
				String tmp=dataList.get(0).get(0);
				System.out.println(tmp);
				int count=1;
				for(ArrayList<String> tmpList:dataList){
					if(!tmp.equals(tmpList.get(0))){
						++count;
						tmp=tmpList.get(0);
					}
				}
				System.out.println("count: "+count);
				
				// get avg and get max
				for(Map.Entry<Integer, Integer> entry:newOpMap.entrySet()){
					//System.out.println(entry.getKey()+" "+entry.getValue());
					
				}
				//get avg
				for(ArrayList<String> tmpList:dataList){
					
				}
//				double value=3.147;
//				System.out.println((Math.round(value*100 )*0.01d));
//				System.out.println((int)(value*100 +0.5)/100.0);
//				System.out.println(Double.parseDouble(new DecimalFormat("#.##").format(value)));
//				System.out.println(Double.parseDouble(String.format("%.2f", value)));
				
			}
			else{
				System.out.println("File doesn't exist!");
			}
			
		}
		
		
	}

}
