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
 * given user. 
 * Create tcpCleaner, a thread that close properly sockets and in/out.
 *
 * @param tcpSocket - A simple socket, used for textual communications.
 */
public class MessageService implements Runnable{
	
	List<Socket> tcpSocket;
	List<Socket> oldTcpSocket = new CopyOnWriteArrayList<Socket>();
	
	List<String> message = new CopyOnWriteArrayList<String>();
	List<PrintWriter> out = new CopyOnWriteArrayList<PrintWriter>();
	List<CleanerStruct> cls = new CopyOnWriteArrayList<CleanerStruct>(); //for tcpCleaner
	BufferedReader in;
	
	public MessageService(List<Socket> tcpSocket){
		this.tcpSocket = tcpSocket;
	}
	
	@Override
	public void run() {

		new Thread(new MessageSender(out, message)).start();
		new Thread(new TcpCleaner(cls, oldTcpSocket, out)).start();
		while(true){
			
			if(!tcpSocket.isEmpty()){
				
				for(Socket socket: tcpSocket){
					
					try{
						
						PrintWriter tempOut = new PrintWriter(socket.getOutputStream());
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						CleanerStruct clear = new CleanerStruct();
						
						clear.add(socket, tempOut, in);
						out.add(tempOut);
						new Thread(new MessageReciever(in, message)).start();
						
						cls.add(clear);
						oldTcpSocket.add(socket);
						tcpSocket.remove(socket);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class CleanerStruct
{
	public Socket socket;
	public PrintWriter out;
	public BufferedReader in;
	public void add(Socket socket, PrintWriter out, BufferedReader in){
		
		this.socket=socket;
		this.out=out;
		this.in=in;
	}
}
