package ch.hearc.tchat.client.views_controllers;

import ch.hearc.tchat.client.Client;
import ch.hearc.tchat.client.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    @FXML
    Button btnConnect;

    @FXML
    Button btnVoice;

    @FXML
    Button btnFile;

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

    ObservableList<String> messages = FXCollections.observableArrayList();
    Client client;
    boolean isConnected;

    @FXML
    public void initialize(){

        onDisconnect();
        btnFile.setDisable(true);
        btnVoice.setDisable(true);
    }

    @FXML
    public void btnConnectPressed(ActionEvent e){
        // Do Connect.
        try {
            if(isConnected){
                client.close();
                onDisconnect();
            }
            else {
                client = new Client(txfIp.getText(), 2017);
                client.addObserver(this);
                onConnectSuccess();
            }
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    @FXML
    public void btnSendPressed(ActionEvent e){
        // Send Message.
        Message tmp = new Message(txfPseudo.getText(),txaText.getText());
        client.send(tmp.toSend());
        txaText.setText("");
    }

    public void onConnectSuccess(){

        btnSend.setDisable(false);
        txaText.setDisable(false);
        lstMessages.setDisable(false);
        messages.clear();
        lstMessages.setItems(messages);
        lstMessages.refresh();
        txfIp.setDisable(true);
        txfPseudo.setDisable(true);
        btnConnect.setText("Disconnect");
        isConnected=true;
    }

    public void onDisconnect(){

        btnSend.setDisable(true);
        txaText.setDisable(true);
        lstMessages.setDisable(true);
        txfIp.setDisable(false);
        txfPseudo.setDisable(false);
        btnConnect.setText("Connect");
        isConnected=false;
    }

    @Override
    public void update(Observable o, Object arg) {

        messages.add(arg.toString());
        lstMessages.setItems(messages);
    }
}
