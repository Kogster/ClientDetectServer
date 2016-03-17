package shared;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SharedAssets {
	public static int SERVER_PORT = 4446; // these are different so that the program
										// can be tested on a machine locally.
	public static int CLIENT_PORT = 4447;
	public static int CON_PORT = 4448;

	public static InetAddress getgroup() {
		try {
			return InetAddress.getByName("224.2.6.0"); //chosen arbitrarily
		} catch (UnknownHostException e) {
			return null;
		}
	}

	public static DatagramPacket getPacket(int port) {
		DatagramPacket packet;
		byte[] buf = new byte[256];
		buf = "ETTDEMO".getBytes();
		packet = new DatagramPacket(buf, buf.length, getgroup(), port);
		return packet;
	}
}
