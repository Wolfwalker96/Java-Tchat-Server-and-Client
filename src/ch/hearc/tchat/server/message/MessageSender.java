package ch.hearc.tchat.server.message;

import java.io.PrintWriter;
import java.util.List;

/**
 * Check for new messages. If new messages available, send them to
 * every users ordered by the older to the newest. Sent messages
 * are removed from the message list.
 *
 * @param  - A list of outputs, represent every users to send data to.
 * @param  - A list of every messages to send.
 */
public class MessageSender implements Runnable {

	List<PrintWriter> out;
	List<String> message;
	
	public MessageSender(List<PrintWriter> out, List<String> message) {
		
		this.out = out;
		this.message = message;
	}

	@Override
	public void run() {
		
		while(true){
			
			if(!message.isEmpty()){
				
				System.out.println("SERVER: Broadcasting message to users." + " Nb of users: " + out.size());
				for(String msgToSend: message){
					
					for(PrintWriter output: out){
						
						output.println(msgToSend);
						output.flush();
					}
				}
				message.clear();
			}
			try {
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

}
