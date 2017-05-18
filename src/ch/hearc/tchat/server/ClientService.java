package ch.hearc.tchat.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ClientService implements Runnable{
	
	List<Socket> tcpSocket;
	List<DatagramSocket> udpSocket;
	
	public ClientService(List<Socket> tcpSocket, List<DatagramSocket> udpSocket){
		this.tcpSocket = tcpSocket;
		this.udpSocket = udpSocket;
	}
	
	@Override
	public void run() {
		
		try(ServerSocket serverSocket = new ServerSocket(2017)){

			System.out.println("SERVER - Client Service ready");
			while(true){
				
				//Socket will be closed in message
				Socket socket = serverSocket.accept();
				tcpSocket.add(socket);
			}
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		try(DatagramSocket datagramSocket = new DatagramSocket(2017)){
			
			udpSocket.add(datagramSocket);
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
	}
}
