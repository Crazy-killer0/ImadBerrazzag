package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Client;
import fr.afpa.service.GestionUtilisateur;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RechercherClientController {
	@FXML
	TextField nom;
	@FXML
	TextField numCompte;
	@FXML
	TextField id;
	@FXML
	Button valider;
	@FXML
	Button annuler;
	@FXML
	Button logout;
	@FXML
	Label nomErr;
	@FXML
	Label numCErr;
	@FXML
	Label idErr;
	GestionUtilisateur gu = new GestionUtilisateur();
	
	public void rechercher() {
		
		if(controlForm()) {
		
		String identifiant = id.getText();
		String nomm = nom.getText();
		String numeroC = numCompte.getText();
		
		if (gu.rechercherClient(nomm, numeroC, identifiant) != null) {
			
			afficherInfo();
			
			
		}
		else {
			System.out.print("Existe pas");
			back();
		}
		
		
		}
		
		
	
	}
	public void back() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DashbordConseiller.fxml"));
			Scene scene = new Scene(loader.load());
			DashbordAdminController controller = loader.getController();
			controller.initData();
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void afficherInfo() {

		try {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InfoDetailsClient.fxml"));
		Scene scene = new Scene(loader.load());
		Stage stage = (Stage) nom.getScene().getWindow();
		stage.setScene(scene);
		stage.show();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	public boolean controlForm() {
boolean valide = true;
		
		boolean nomEmpty  = ControllerSaisie.textFieldEmpty(nom, nomErr, " Le champ ne doit pas être vide");
		boolean numCompteEmpty   = ControllerSaisie.textFieldEmpty(numCompte, numCErr, " Le champ ne doit pas être vide");
		boolean idEmpty   = ControllerSaisie.textFieldEmpty(id, idErr, " Le champ ne doit pas être vide");
		boolean nomAlpha = ControllerSaisie.isAlpha(nom, nomErr, " Le champ ne doit pas contenir de chiffre");
	
		
		

		
		if (nomEmpty || numCompteEmpty || idEmpty) {

			valide = false ;
			
		} else  if( !nomAlpha ){
			
			valide = false;

			
		} else {
			
			valide = true;
		}
		

		return valide;
	}
	
}