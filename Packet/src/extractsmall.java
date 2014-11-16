import java.util.*;
public class extractsmall {
	
	
	public static List<Integer> getpacketnumber(HashMap<String,List<Integer>> hname,String ipadd){
		List<Integer> numlist= hname.get(ipadd);;
		
		for(int i=0;i<numlist.size();i++){
			
			
			printrqstd.printpacket(numlist.get(i));
			
			
			//System.out.println(numlist);	
		}
			
		return numlist;
	}
	
	// return packet num list for any port 
	public static List<Integer> getpacketnumberport(HashMap<Integer,List<Integer>> hname,Integer portnd){
		List<Integer> numlist= hname.get(portnd);;
		System.out.println(numlist);
		return numlist;
	}
	
	/// compare the src ip and the destination ip and get the list
	public static List<Integer> getpacketsrcdesc(String srcip,String descip){
		List<Integer> srclist= Data.srcip_map.get(srcip);
		List<Integer> desclist= Data.destip_map.get(descip);
		List<Integer> retlist = new ArrayList<Integer>();
		int i,j;
		for( i=0;i<srclist.size();i++){
			for(j=0;j<desclist.size();j++){
				
				if(srclist.get(i)==desclist.get(j)){
					
					printrqstd.printpacket(srclist.get(i));
					retlist.add(srclist.get(i));				}
			}
			
		}
				
		return retlist;	
		
		}
	
	// compare two lists and return the common item list;
	public static List<Integer> getcommon(List<Integer> lista,List<Integer> listb){
		
		List<Integer> retlist = new ArrayList<Integer>();
		int i,j;
		for( i=0;i<lista.size();i++){
			for(j=0;j<listb.size();j++){
				
				if(lista.get(i)==listb.get(j)){
					
					printrqstd.printpacket(lista.get(i));
					retlist.add(lista.get(i));				}
			}
			
		}
				
		return retlist;	
		
		}
		

}
