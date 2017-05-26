package ch.hearc.tchat.server.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Textual service, create threads to communicate in/out with the users.
 * Create MessageSender, a thread that broadcast chat message to everyone.
 * Create one MessageReciever for each users, a thread that listen to its
 * given user. When in/out created to communicate with the user, add socket 
 * to old socket and remove socket from tcpSocket list.
 *
 * @param tcpSocket - A simple socket, used for textual communications.
 */
public class MessageService implements Runnable{
	
	List<Socket> tcpSocket;
	List<Socket> oldTcpSocket = new CopyOnWriteArrayList<Socket>();;
	
	List<String> message = new CopyOnWriteArrayList<String>();
	List<PrintWriter> out = new CopyOnWriteArrayList<PrintWriter>();
	BufferedReader in;
	
	public MessageService(List<Socket> tcpSocket){
		this.tcpSocket = tcpSocket;
	}
	
	@Override
	public void run() {

		new Thread(new MessageSender(out, message)).start();
		while(true){
			
			if(!tcpSocket.isEmpty()){
				
				for(Socket socket: tcpSocket){
					
					try{
						
						out.add(new PrintWriter(socket.getOutputStream()));
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						new Thread(new MessageReciever(in, message)).start();
						oldTcpSocket.add(socket);
						tcpSocket.remove(socket);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			
			//Je vais remplacer ce caca par un thread qui check toutes les 2s si les threads sont en vie.
			//Avec les in et out pour pouvoir tout close() et list.remove(e) proprement.
			//C'est simple (dans ma tête) mais il est bien tard, bonne nuit!
			try {
				for(Socket socket: oldTcpSocket){ 
					if(socket.isClosed()){
						System.out.println("SERVER: Socket closed "+socket.toString());
						oldTcpSocket.remove(socket);
					}
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
}
