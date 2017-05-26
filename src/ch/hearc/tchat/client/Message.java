package ch.hearc.tchat.client;

import java.io.Serializable;

/**
 * Client side layout for a message. To display raw strings into nice chat log.
 * 
 * @param pseudo - Pseudo of the use who sent this message.
 * @param content - Content of the message.
 */
public class Message implements Serializable {
    
	/**
	 * V1, no change log available...
	 */
	private static final long serialVersionUID = 1L;
	
	private String pseudo;
    private String content;

    public Message(String pseudo, String content){
        this.pseudo=pseudo;
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public String toString() {
        return getPseudo()+" : "+getContent();
    }
}
