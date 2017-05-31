package ch.hearc.tchat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views_controllers/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 640, 440));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
    	
    	//test area
    	Client client = new Client();				//create
       	client.send("01Florian: Hello World!");		//send message
    	try {
			Thread.sleep(3000);						//wait for response
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	client.close();								//close
    }
}
