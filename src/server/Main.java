package server;

import java.io.IOException;

import shared.SharedAssets;

public class Main {
	public static void main(String... args){
		try {
			new Thread(new ServerThread(SharedAssets.CON_PORT)).start();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("ServerThread failed to start");
			System.exit(1);
		}

		try {
			new Thread(new MultiCastSocketThread()).start();
		} catch (IOException e) {
			System.err.println("MulticastServer failed to start");
			System.exit(1);
		}
		System.out.println("server started");
	}
}
