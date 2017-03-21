import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			
			final ArrayList<ArrayList<String> > dataList=new ArrayList<ArrayList<String> >();
			final String path="/home/json-lee/workdir/java/hadoop/neon1-workspace/StringParser/src/test.txt";
			File file=new File(path);
			if(file.isFile()&&file.exists()){
				BufferedReader reader=new BufferedReader(new FileReader(file));
				String line;
				while((line=reader.readLine())!=null){
					ArrayList<String> lineTmp=new ArrayList<String>();
					for(String s:line.split("\\|"))
						lineTmp.add(s);
					dataList.add(lineTmp);
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
						return o1.get(groupByNumber).compareTo(o2.get(groupByNumber));
					}
				});
				for(ArrayList<String> al:dataList){
					for(String data:al){
						System.out.print(data+" ");
					}
					System.out.println();
				}
				
			}
			else{
				System.out.println("File doesn't exist!");
			}
			
		}
		
		
	}

}
