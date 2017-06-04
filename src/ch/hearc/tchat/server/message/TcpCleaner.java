package ch.hearc.tchat.server.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * A thread specialized in cleaning closed sockets. Remove in/out from lists,
 * remove the socket from the tcpSocket list and close anything that might
 * be still open.
 *
 * @param cls - A structure which holds every information in/out for a given thread.
 * @param tcpSocket - List of Socket from which we will remove disconnected elements.
 * @param out - List of every open outputs from which we will remove disconnected elements.
 */
public class TcpCleaner implements Runnable {

	List<CleanerStruct> cls;
	List<Socket> tcpSocket;
	List<PrintWriter> out;
	
	public TcpCleaner(List<CleanerStruct> cls, List<Socket> tcpSocket, List<PrintWriter> out) {
		
		this.cls=cls;
		this.tcpSocket=tcpSocket;
		this.out=out;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			for(CleanerStruct clear: cls){
				
				if(clear.socket.isClosed() || System.currentTimeMillis()-clear.ttl > 6500){
					
					try {
						while(!clear.socket.isClosed()){
							clear.socket.close();
						}
						clear.in.close();
						clear.out.close();
						
						tcpSocket.remove(clear.socket);
						out.remove(clear.out);
						cls.remove(clear);
						
						System.out.println("SERVER: Socket closed "+clear.socket.toString());
					} catch (IOException e) {
						
						System.out.println("SERVER: in tcpCleaner, already closed!");
					}
				}
			}
			try {
				
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
				System.out.println("SERVER: in tcpCleaner, sleep(200) interrupted.");
			}
		}
	}

}
