import jpcap.JpcapCaptor;

import java.io.*;

import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.*;

public class Test {

	static JpcapCaptor captor;
	static NetworkInterface[] array;
	String str, info;
	int x;
	static int choice;

	public static void main(String[] args) {

		array = JpcapCaptor.getDeviceList();

		System.out.println("Available Interfaces");

		for (int i = 1; i < array.length; i++) {
			System.out.println(i + " > " + array[i].description);
		}
		System.out.println("________________________\n");
		choice = Integer.parseInt(getInput("Choose interfaces(0,1,) :"));

		System.out.println("Listening on Interface -> "
				+ array[choice].description);
		System.out.println("________________________\n");
		try {

			captor = JpcapCaptor.openDevice(array[choice], 65535, false, 20);
			System.out.println("___________captor_____________\n");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		while (true) {
			Packet info = captor.getPacket();
			// captor.setFilter("TCP and UDP",true);
			if (info != null)
				System.out.println(info);

			if(info instanceof TCPPacket){
				TCPPacket tc= (TCPPacket) info;
				System.out.println(tc.src_ip);
			}
			if(info instanceof UDPPacket){
				UDPPacket ud= (UDPPacket) info;
				System.out.println(ud.src_ip);
				
			}
			
			
		}

	}

	private static String getInput(String string) {
		String input = "";
		System.out.print(string);
		BufferedReader bufferedreader = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			input = bufferedreader.readLine();
		} catch (IOException ioexception) {
		}
		return input;
	}

}
