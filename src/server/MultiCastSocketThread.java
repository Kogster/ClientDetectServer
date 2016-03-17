package server;

import java.io.*;
import java.net.*;

import shared.SharedAssets;

public class MultiCastSocketThread implements Runnable {

	protected MulticastSocket socket = null;
	protected BufferedReader in = null;

	public MultiCastSocketThread() throws IOException {
		socket = new MulticastSocket(SharedAssets.SERVER_PORT);
		socket.joinGroup(SharedAssets.getgroup());
	}

	public void run() {
		while (true) {
			try {
				System.out.println("waiting to receive");
				socket.receive(SharedAssets.getPacket(SharedAssets.SERVER_PORT));
				System.out.println("received");
				socket.send(SharedAssets.getPacket(SharedAssets.CLIENT_PORT));
				System.out.println("packet sent");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// socket.leaveGroup(SharedAssets.getgroup());
		// socket.close();
	}
}