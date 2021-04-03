package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Adresse;
import fr.afpa.beans.Agence;
import fr.afpa.service.GestionAgence;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreationAgenceController {

	@FXML
	Button logout;
	@FXML
	Button back;
	@FXML
	Button annuler;
	@FXML
	Button ajouterAgence;
	@FXML
	Button user;
	@FXML
	TextField nomRue;
	@FXML
	TextField numRue;
	@FXML
	TextField cP;
	@FXML
	TextField ville;
	@FXML
	TextField pays;
	@FXML
	TextField libelle;
	@FXML
	Label libelleErr;
	@FXML
	Label numRueErr;
	@FXML
	Label nomRueErr;
	@FXML
	Label cPErr;
	@FXML
	Label villeErr;
	@FXML
	Label paysErr;

	GestionAgence ga = new GestionAgence();
	
	/**
	 * Methode pour deconnecter l'utilisateur
	 */
	public void deconnecter() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EspaceConnexion.fxml"));
			Scene scene = new Scene(loader.load());
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode pour retourner a la page dashbordAdmin 
	 */
	public void back() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DashbordAdmin.fxml"));
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

	/**
	 * Methode pour enregistrer l'agence
	 */
	public void ajouterAgence() {

		if(controleForm()) {
			
			Adresse adresse = new Adresse(numRue.getText(), nomRue.getText(), cP.getText(), ville.getText(),pays.getText());
			
			Agence agence = new Agence(libelle.getText(), adresse);
			
			
			if (ga.creationAgence(agence)) {

				back();

			}
			
			
		}
		

	}


    /**
     * Methode qui verifie le formulaire
     * @return boolean qui precise si le formulaire est bon ou pas
     */
	public boolean controleForm() {

		boolean valide = true;
		
		boolean libelleEmpty  = ControllerSaisie.textFieldEmpty(libelle, libelleErr, " Le champ ne doit pas être vide");
		boolean nomRueEmpty   = ControllerSaisie.textFieldEmpty(nomRue, nomRueErr, " Le champ ne doit pas être vide");
		boolean numRueEmpty   = ControllerSaisie.textFieldEmpty(numRue, numRueErr, " Le champ ne doit pas être vide");
		boolean villeEmpty    = ControllerSaisie.textFieldEmpty(ville, villeErr, " Le champ ne doit pas être vide");
		boolean paysEmpty     = ControllerSaisie.textFieldEmpty(pays, paysErr, " Le champ ne doit pas être vide");
		boolean cpEmpty       = ControllerSaisie.textFieldEmpty(cP, cPErr, " Le champ ne doit pas être vide");
		
		boolean libelleAlpha = ControllerSaisie.isAlpha(libelle, libelleErr, " Le champ ne doit pas contenir de chiffre");
		boolean villeAlpha   = ControllerSaisie.isAlpha(ville, villeErr, " Le champ ne doit pas contenir de chiffre");
		boolean paysAlpha    = ControllerSaisie.isAlpha(pays, paysErr, " Le champ ne doit pas contenir de chiffre");
		

		
		if (libelleEmpty || cpEmpty || nomRueEmpty || numRueEmpty || villeEmpty || paysEmpty) {

			valide = false ;
			
		} else  if( !libelleAlpha || !villeAlpha || !paysAlpha){
			
			valide = false;

			
		} else {
			
			valide = true;
		}
		

		return valide;
	}

}
