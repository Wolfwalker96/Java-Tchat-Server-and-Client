package ch.hearc.tchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args){
		
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
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
			System.out.println("CLIENT: Getting input stream");
			
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			System.out.println("CLIENT: Getting output stream");
			out = new PrintWriter(socket.getOutputStream());
			//print info
			System.out.println("CLIENT: Sending message");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
			out.flush();
			out.println("01Florian: Salut la crique! :-)");
			out.flush();

			System.out.println("CLIENT: message sent");
			System.out.println("CLIENT: Recieved from server: " + in.readLine());
			
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
