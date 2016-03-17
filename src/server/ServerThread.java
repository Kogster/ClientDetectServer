package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
	private ServerSocket sock;
	
	
	public ServerThread(int port) throws IOException, ClassNotFoundException {
		this.sock = new ServerSocket(port);			
	}

	@Override
	public void run() {
		while (true) {
			
			Socket connection = null;
			try {
				System.out.println("Accepting");
				connection = sock.accept();
			} catch (IOException e1) {
				System.err.println("server failed to accept connection");
			}

			new Thread(new Runnable() {
				Socket connection;

				@Override
				public void run() {
					ObjectInputStream read = null;
					try {
						read = new ObjectInputStream(connection.getInputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}

					while (!connection.isClosed()) {
						String message = null;
						try {
							message = (String) read.readObject();
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
						if (message == null) {
							try {
								read.close();
								connection.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							System.out.println(message);
						}
					}
					System.out.println("client dissapered");
				}

				Runnable addSock(Socket sock) {
					this.connection = sock;
					return this;
				}
			}.addSock(connection)).start();
		}
		
	}
}
