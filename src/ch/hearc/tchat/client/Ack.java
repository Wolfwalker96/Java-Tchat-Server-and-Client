package ch.hearc.tchat.client;

import java.io.PrintWriter;
import java.net.Socket;

public class Ack implements Runnable {

	Socket socket = null;
	PrintWriter out = null;
	
	public Ack(Socket socket, PrintWriter out) {

		this.socket = socket;
		this.out = out;
	}
	@Override
	public void run() {

		while(socket.isClosed()!= true){
			
			try {
				
				Thread.sleep(3000);
				out.println("02");
				out.flush();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}

	}

}
