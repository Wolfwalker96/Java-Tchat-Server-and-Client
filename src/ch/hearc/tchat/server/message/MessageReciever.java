package ch.hearc.tchat.server.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * One thread of this class is created for every users. This thread is listening
 * to the user and add any text received to the message list.
 *
 * @param  - An input, represent an user to listen to.
 * @param  - A list in which we add any message we received from the user.
 */
public class MessageReciever implements Runnable{

	BufferedReader in;
	List<String> message;
	String currentMsg;
	int flag;
	boolean running = true;
	CleanerStruct cls;
	
	public MessageReciever(BufferedReader in, List<String> message, CleanerStruct cls){
		
		this.in = in;
		this.message = message;
		this.cls = cls;
	}
	
	@Override
	public void run() {
		
		while(running){
			
			try {
				currentMsg = in.readLine();
				System.out.println("SERVER: Message recieved ");
				//System.out.println(currentMsg);
				if(currentMsg != null && currentMsg.length() >= 2){
					flag = 	Integer.parseInt(currentMsg.substring(0, 2));
				}
				else{
					flag = 0;
				}
				switch (flag){
				
					case 1:
						//Message to send.
						message.add(currentMsg);
						break;
					
					case 2:
						//Ack, reset timer
						cls.ttl = System.currentTimeMillis();
						break;
						
					default:
						//Stop communications, kill thread.
						//Timeout/error
						in.close();
						running = false;
						break;
				}
				Thread.sleep(200);
				currentMsg = null;
			} catch (IOException e) {
				running = false;
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
