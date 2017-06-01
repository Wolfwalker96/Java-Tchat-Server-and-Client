package ch.hearc.tchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver implements Runnable{

	BufferedReader in = null;
	Socket socket = null;
	String msg = null;
	Client client = null;
	public Receiver(Socket socket, Client client) {
		
		this.socket = socket;
		this.client = client;
		try {
			in = new BufferedReader(
					new InputStreamReader(this.socket.getInputStream()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		boolean alive = true;
		while(alive){
			
			try {
				
				Thread.sleep(100);
				while((msg = in.readLine()) == null){

					Thread.sleep(200);
				}
				if(msg.length()>2) client.newMessage(msg.substring(2));
				if(socket.isClosed()){
					alive = false;
					System.out.println("THE END");
				}
			} catch (IOException | InterruptedException e) {
				alive = false;
			}
		}
		try {
			in.close();
			socket.close();
			System.out.println("CLIENT: Connexion closed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
