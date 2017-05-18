package ch.hearc.tchat.server;

import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;

import ch.hearc.tchat.server.message.MessageService;
import ch.hearc.tchat.server.voice.VoiceService;

public class Server {
	
	public static List<Socket> tcpSocket;
	public static List<DatagramSocket> udpSocket;
	
	public static void main(String[] args){
		
		Thread client = new Thread(
				new ClientService(tcpSocket, udpSocket));
		client.start();
		
		Thread messageService = new Thread( 
				new MessageService(tcpSocket) );
		messageService.start();
		
		Thread voiceService = new Thread(
				new VoiceService(udpSocket));
		voiceService.start();
		
		
	}
}
