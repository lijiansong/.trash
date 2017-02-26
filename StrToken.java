package cn.ac.ict.jsonlee;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
/*
 * 词频统计demo
 * */

public class StrToken {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] text=new String[]{"today is good",
				"today weather is good",
				"good weather is good",
				"today has good weather",
				"the weather is good"};
		Hashtable<String, Integer> ht=new Hashtable<>();
		for(int i=0;i<text.length;++i){
			StringTokenizer st=new StringTokenizer(text[i]);
			while(st.hasMoreTokens()){
				String word=st.nextToken();
				if (!ht.containsKey(word)) {
					ht.put(word, new Integer(1));
				}
				else{
					int wc=ht.get(word).intValue()+1;
					ht.put(word, wc);
				}
			}//while
		}//for
		for(Iterator<String> itr=ht.keySet().iterator();itr.hasNext();){
			String word=(String)itr.next();
			System.out.println(word+": "+ht.get(word));
		}
	}
}
