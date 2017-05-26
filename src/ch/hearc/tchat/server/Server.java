package ch.hearc.tchat.server;

import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ch.hearc.tchat.server.message.MessageService;
import ch.hearc.tchat.server.voice.VoiceService;

/**
 * Create Service threads and give then shared resources, tcpSocket and udpSocket.
 */
public class Server {
	
	public static List<Socket> tcpSocket = new CopyOnWriteArrayList<Socket>();
	public static List<DatagramSocket> udpSocket = new CopyOnWriteArrayList<DatagramSocket>();
	
	public static void main(String[] args){
		
		System.out.println("Server is starting...");
		
		Thread clientService = new Thread(
				new ClientService(tcpSocket, udpSocket));
		clientService.start();
		System.out.println("SERVER: ClientService ready.");
		
		Thread messageService = new Thread( 
				new MessageService(tcpSocket) );
		messageService.start();
		System.out.println("SERVER: MessageService ready.");
		
		Thread voiceService = new Thread(
				new VoiceService(udpSocket));
		voiceService.start();
		System.out.println("SERVER: VoiceService ready.");
		
		
	}
}
