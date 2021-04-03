package fr.afpa.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		AnchorPane anchorPane;
		try {
			anchorPane = FXMLLoader.load(getClass().getResource("/fr/afpa/view/DashbordAdmin.fxml"));
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("BANK CDA");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}