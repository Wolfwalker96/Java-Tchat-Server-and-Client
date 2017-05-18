package ch.hearc.tchat.server.message;

import java.net.Socket;
import java.util.List;

public class MessageService implements Runnable{
	
	List<Socket> tcpSocket;
	
	public MessageService(List<Socket> tcpSocket){
		this.tcpSocket = tcpSocket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
