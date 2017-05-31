package ch.hearc.tchat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	Socket socket = null;
	PrintWriter out = null;
	String ip = "127.0.0.1";
	int port = 2017;
	Thread reciever = null;

	public Client(){
			
			this.init();
		}
	public Client(String ip, int port){
		
		this.ip = ip;
		this.port = port;
		this.init();
	}
	
	public void close(){
		try {
			
			while(!socket.isClosed()){
				
				socket.close();
			}
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void init(){
		
		System.out.println("Client is starting...");
		while(socket == null){
			
			try {	

				socket = new Socket(ip, port);
			}
			catch (IOException e) {

				System.out.println("Could not reach server, trying again...");
			}
		}
		try {
			
			out = new PrintWriter(socket.getOutputStream());
			reciever = new Thread(new Reciever(socket, this));
			reciever.start();
			System.out.println("CLIENT: Ready.");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}	
	public void send(String message){
		
		out.println(message);
		out.flush(); //Overkill for the win!
		System.out.println("CLIENT: Message sent.");
	}
	public void newMessage(String message){
		
		//Add new message on interface.
		System.out.println("CLIENT: Display message on interface. Msg: "+message);
	}
}
