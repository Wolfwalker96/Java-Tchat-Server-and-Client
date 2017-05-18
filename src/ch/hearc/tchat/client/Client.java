package ch.hearc.tchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args){
		
		Socket socket = null;
		BufferedReader in = null;
		
		try {
			
			//send
			System.out.println("Client is starting...");
			while(socket == null){
				
				try {	

				socket = new Socket("127.0.0.1", 2017);
				}
				catch (IOException e) {
	
					System.out.println("Could not reach server, trying again...");
				}
			}
			
			//wait and get info
			System.out.println("CLIENT - Reading from server...");
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			//print info
			System.out.println("CLIENT - from server: " + in.readLine());
		} catch (IOException e) {

			e.printStackTrace();
		}
		finally{
			
			try {
				
				in.close();
				socket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
