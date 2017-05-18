package ch.hearc.tchat.client;

import java.io.Serializable;

/**
 * Created by paul.jeanbour on 18.05.2017.
 */
public class Message implements Serializable {
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
