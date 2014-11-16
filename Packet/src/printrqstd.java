


public class printrqstd {
		
	
	public static void printpacket(Integer count11){
		
		  PacketData printing_packet = Data.alldata_map.get(count11);
		  System.out.println("Packet Nmber :"+count11);
		  System.out.println("Source IP : "+(String) printing_packet.src_ip1+" | port :"+ printing_packet.src_port1);
		  System.out.println("Destination IP : "+(String) printing_packet.dest_ip1+" | port :"+ printing_packet.dest_port1);
		  System.out.println("Packet length : "+ printing_packet.length1+" | protocol :"+ printing_packet.protocol1);
		  System.out.println("--------------------------------------------------------------------------------------------");
		
		
		
	}
}
