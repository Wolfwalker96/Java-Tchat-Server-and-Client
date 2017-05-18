package ch.hearc.tchat.client.views_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

public class Controller {

    @FXML
    Button btnConnect;

    @FXML
    Button btnSend;

    @FXML
    TextArea txaText;

    @FXML
    TextField txfIp;

    @FXML
    TextField txfPseudo;

    @FXML
    ListView<String> lstMessages;

    @FXML
    public void initialize(){
        btnSend.setDisable(true);
        txaText.setDisable(true);
    }

    @FXML
    public void btnConnectPressed(ActionEvent e){
        // Do Connect.
        onConnectSuccess();

        // Lire les messages

    }

    @FXML
    public void btnSendPressed(ActionEvent e){
        // Send Message
    }

    public void onConnectSuccess(){
        btnSend.setDisable(false);
        txaText.setDisable(false);
    }

    public void onNewMessage(){

    }

}
