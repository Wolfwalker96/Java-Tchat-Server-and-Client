package ch.hearc.tchat.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Check for new clients trying to connect to the database and allocate
 * them threads for the different services they might use (textual, vocal, document).
 *
 * @param tcpSocket - A simple socket, used for textual communications.
 * @param udpSocket - A datagramSocket, used for voice communications.
 */
public class ClientService implements Runnable{
	
	List<Socket> tcpSocket;
	List<DatagramSocket> udpSocket;
	
	public ClientService(List<Socket> tcpSocket, List<DatagramSocket> udpSocket){
		this.tcpSocket = tcpSocket;
		this.udpSocket = udpSocket;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			try(ServerSocket serverSocket = new ServerSocket(2017)){
	
				//Socket will be closed in message
				Socket socket = serverSocket.accept();
				tcpSocket.add(socket);
				System.out.println("SERVER: New client acquired");
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
}
