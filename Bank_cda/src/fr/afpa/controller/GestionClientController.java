package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionClientController {
	
	
	@FXML
	Button creeCompte;
	@FXML
	Button consulterCompte;
	@FXML 
	Button consulterClient;
	@FXML
	Button logout;
	
	Client client;
	
	public void initData(Client client) {
		
	this.client = client;
	System.out.println(this.client);
	}
	
	public void consulterCompte() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashbordClient.fxml"));
			Scene scene = new Scene(loader.load());
			DashbordClientController controller  = loader.getController();
			controller.initData(client);
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void consulterClient() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/InfoCLient.fxml"));
			Scene scene = new Scene(loader.load());
			InfoClientController controller  = loader.getController();
			controller.initData(client);
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
