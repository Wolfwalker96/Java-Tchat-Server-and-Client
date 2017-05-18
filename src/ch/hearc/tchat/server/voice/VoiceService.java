package ch.hearc.tchat.server.voice;

import java.net.DatagramSocket;
import java.util.List;

public class VoiceService implements Runnable{
	
	List<DatagramSocket> udpSocket;
	
	public VoiceService(List<DatagramSocket> udpSocket){
		this.udpSocket = udpSocket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
