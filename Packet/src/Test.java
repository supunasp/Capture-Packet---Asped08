import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.*;
import java.io.*;

import java.util.*;

public class Test {

    static JpcapCaptor captor;
    static NetworkInterface[] array;
    String str, info;
    int x;
    static int choice;
    static int captureTime;
    static List<Integer> srcip_count1 = new ArrayList<> ();    
	
    public static void main(String[] args) {
         
	capturing();
        System.out.println("ok");
        
    }
	
    public static void capturing(){
	
	array = JpcapCaptor.getDeviceList();
        System.out.println("Available Interfaces");
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + " > " + array[i].description);
	}
		
	System.out.println("________________________\n");
	
        choice = Integer.parseInt(getInput("Choose interfaces(0,1,) :"));
       
	captureTime = Integer.parseInt(getInput("Enter the duration of capturing/(ms) :"));
		
	System.out.println("Listening on Interface -> "
				+ array[choice].description);
	System.out.println("\n");
		
        
        try {
            captor = JpcapCaptor.openDevice(array[choice], 65535, false,captureTime);
            System.out.println("___________ capturing _____________\n");
            captor.setFilter("tcp",true);
        } 
        catch (IOException ioe) {
            
            ioe.printStackTrace();
	}
        
        
	int count = 1;
        while (count<20) { //only capturing 20 packets
            
            Packet info = captor.getPacket();
            if (info != null){
            
            
                //Selecting protocol and adding data to PacketData class    
                PacketData selected_info = Store.converting(info);
            
                String newsrc = selected_info.src_ip1;
                String newdest = selected_info.dest_ip1;
                int newsrcport = selected_info.src_port1;
                int newdestport = selected_info.dest_port1;
                int newlength = selected_info.length1;
                String newprotocol = selected_info.protocol1;
            
                Data data = new Data();
            
                Data.alldata_map.put(count,selected_info);
            
                System.out.println(Data.alldata_map.size());
            
                Store.addsrcip(selected_info,data,newsrc,count,Data.srcip_map);
                Store.adddestip(selected_info,data,newdest,count,Data.destip_map);
                Store.addsrcport(selected_info,data,newsrcport,count,Data.srcport_map);
                
                Store.adddestport(selected_info,data,newdestport,count,Data.destport_map);
                Store.addlength(selected_info,data,newlength,count,Data.length_map);
                Store.addprotocol(selected_info,data,newprotocol,count,Data.protocol_map);
            
                count++;
            
            }
        }
        
        
        //exmple outputs
        System.out.println("Sourceip Hashmap");
        System.out.println(Data.srcip_map.keySet());
        System.out.println(Data.srcip_map.values());
        //System.out.println(Data.srcip_map.get("192.168.43.1"));
        //System.out.println(Data.srcip_map.get("192.168.43.28"));
        
        System.out.println("Destinationip Hashmap");
        System.out.println(Data.destip_map.keySet());
        System.out.println(Data.destip_map.values());
        //System.out.println(Data.destip_map.get("192.168.43.1"));
        //System.out.println(Data.destip_map.get("192.168.43.28"));
        
        System.out.println("Sourceport Hashmap");
        System.out.println(Data.srcport_map.keySet());
        System.out.println(Data.srcport_map.values());
        
        System.out.println("Destport Hashmap");
        System.out.println(Data.destport_map.keySet());
        System.out.println(Data.destport_map.values());
        
        System.out.println("length Hashmap");
        System.out.println(Data.length_map.keySet());
        System.out.println(Data.length_map.values());
        
        System.out.println("protocol Hashmap");
        System.out.println(Data.protocol_map.keySet());
        extractsmall.getpacketnumber(Data.srcip_map, "192.168.1.100");
	
    }
    

    private static String getInput(String string) {
	
        String input = "";
	System.out.print(string);
	BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
	
        try {
            input = bufferedreader.readLine();
        } 
        catch (IOException ioexception) {}
	
        return input;
    }

}