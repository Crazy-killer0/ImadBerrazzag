package fr.afpa.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.service.GestionFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;

public class InfoClientController {
	
	@FXML
	Label numeroClient;
	@FXML
	Label nom;
	@FXML
	Label prenom;
	@FXML
	Label dateDeNaissance;
	@FXML
	ImageView imageQR;
	@FXML
	TableView<Compte> listCompte;
	@FXML
	TableColumn<Compte, String> typeCompte;
	@FXML
	TableColumn<Compte, String> nbCompte;
	@FXML
	TableColumn<Compte, String> solde;
	@FXML
	TableColumn<Compte, String> lastoperation;
	@FXML
	Button impression;
	
	
	
	/**
	 * 
	 * @param client
	 */
	public void initData(Client client) {

		
		numeroClient.setUserData(client);
		numeroClient.setText(client.getIdClient());
		nom.setText(client.getNom());
		prenom.setText(client.getPrenom());
		String dateNaissance = ""+client.getDateNaissance();  
		dateDeNaissance.setText(dateNaissance);
		
		FileInputStream input;
		
		GestionFile gf = new GestionFile();
		gf.genererQRCode(client);
		try {
			input = new FileInputStream("qrcode.jpg");
			Image image = new Image(input);
			imageQR.setImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		typeCompte.setCellValueFactory(new PropertyValueFactory<>("type"));
		nbCompte.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
		solde.setCellValueFactory(new PropertyValueFactory<>("solde"));

		ObservableList<Compte> list = FXCollections.observableArrayList();
		list.addAll(client.getListeCompte());
		listCompte.setItems(list);

	}

}
