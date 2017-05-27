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
				
				if(clear.socket.isClosed()){
					
					try {
						
						clear.in.close();
						clear.out.close();
						
						tcpSocket.remove(clear.socket);
						out.remove(clear.out);
						cls.remove(clear);
						
						System.out.println("SERVER: Socket closed "+clear.socket.toString());
					} catch (IOException e) {
						
						//Nothing happens
					}
				}
			}
			try {
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				//Nothing happens.
			}
		}
	}

}
