package fr.afpa.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Mainview extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("DashbordAdmin.fxml"));
		Scene scene = new Scene(anchorPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BANK CDA");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
