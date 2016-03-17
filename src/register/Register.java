package register;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import shared.SharedAssets;

public class Register {
	public static void main(String... args) throws UnknownHostException, IOException {
		String ip = null;
		// Detect server
		System.out.println("Initial");
		MulticastSocket socket = new MulticastSocket(SharedAssets.CLIENT_PORT);
		socket.joinGroup(SharedAssets.getgroup());
		socket.send(SharedAssets.getPacket(SharedAssets.SERVER_PORT));
		System.out.println("sent");
		DatagramPacket packet = null;
		socket.receive(packet = SharedAssets.getPacket(SharedAssets.CLIENT_PORT));
		System.out.println("receive");
		ip = packet.getAddress().toString().substring(1);
		System.out.println("ip found was " + ip);
		socket.close();

		// Normal mode

		Socket sendSocket = new Socket(ip, SharedAssets.CON_PORT);
		ObjectOutputStream out = new ObjectOutputStream(sendSocket.getOutputStream());
		try {
			while (!sendSocket.isClosed()) {
				String message = JOptionPane.showInputDialog(null, "This is the client");
				out.writeObject(message);
				if (message == null) {
					out.flush();
					out.close();
					sendSocket.close();
				}
			}
		} catch (IOException e) {
			System.out.println("Good bye");
		}
	}
}
