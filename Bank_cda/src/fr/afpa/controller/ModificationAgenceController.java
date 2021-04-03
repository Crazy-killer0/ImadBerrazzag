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

public class ModificationAgenceController {
	
	@FXML
	Button confirmer;
	@FXML 
	Button desactiver;
	@FXML
	Button back;
	@FXML
	TextField libelle;
	@FXML
	TextField numRue;
	@FXML
	TextField nomRue;
	@FXML
	TextField cP;
	@FXML
	TextField ville;
	@FXML
	TextField pays;
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
	 * Methode pour initialiser les données
	 * @param agence
	 */
	public void initData(Agence agence) {
		
		libelle.setUserData(agence);
		libelle.setText(agence.getNom());
		numRue.setText(agence.getAdresse().getNumeroRue());
		nomRue.setText(agence.getAdresse().getNomRue());
		cP.setText(agence.getAdresse().getCodePopstal());
		ville.setText(agence.getAdresse().getVille());
		pays.setText(agence.getAdresse().getPays());
		
		
	}
	
	/**
	 * Methode qui modifie les données de l'agence
	 */
	public void update() {
		
		System.out.println(controleForm());
		if(controleForm()) {
			
			Agence agence = (Agence) libelle.getUserData();
			Adresse adresse = new Adresse(numRue.getText(),nomRue.getText(),cP.getText(),ville.getText(),pays.getText());
			
			agence.setAdresse(adresse);
			agence.setNom(libelle.getText());
			ga.modifierAgence(agence);
			
			System.out.println(agence);
			
			back();
		}
	}
	
	/**
	 * Methode pour retourner a la page dashbordAdmin 
	 */
	public void back() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashbordAdmin.fxml"));
			Scene scene = new Scene(loader.load());
			DashbordAdminController controller = loader.getController();
			controller.initData();
			Stage stage = (Stage) ville.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode pour changer le status de l'agence
	 */
	public void status() {
		
		if(desactiver.getText().equals("Désactiver")) {
			
			Agence agence = (Agence) libelle.getUserData();
			ga.etatAgence(agence, false);
			
			desactiver.setText("Activer    ");
			desactiver.getStyleClass().remove("btnRed");
			desactiver.getStyleClass().add("btnGreen");
			
		}else {
			
			Agence agence = (Agence) libelle.getUserData();
			ga.etatAgence(agence, true);
			desactiver.getStyleClass().remove("btnGreen");
			desactiver.getStyleClass().add("btnRed");
			desactiver.setText("Désactiver");
		
		}
	}
	
	/**
	 * Methode de controle du formulaire 
	 * @return boolean : true le formulaire est bon, false il n'est pas valide
	 */
	public boolean controleForm() {

		boolean valide = true;
		
		boolean libelleEmpty  = ControllerSaisie.textFieldEmpty(libelle, libelleErr, " Le champ ne doit pas être vide");
		boolean nomRueEmpty   = ControllerSaisie.textFieldEmpty(nomRue, nomRueErr, " Le champ ne doit pas être vide");
		boolean numRueEmpty   = ControllerSaisie.textFieldEmpty(numRue, numRueErr, " Le champ ne doit pas être vide");
		boolean villeEmpty    = ControllerSaisie.textFieldEmpty(ville, villeErr, " Le champ ne doit pas être vide");
		boolean paysEmpty     = ControllerSaisie.textFieldEmpty(pays, paysErr, " Le champ ne doit pas être vide");
		boolean cpEmpty       = ControllerSaisie.textFieldEmpty(cP, cPErr, " Le champ ne doit pas être vide");
		
		boolean libelleAlpha  = ControllerSaisie.isAlpha(libelle, libelleErr, " Le champ ne doit pas contenir de chiffre");
		boolean villeAlpha    = ControllerSaisie.isAlpha(ville, villeErr, " Le champ ne doit pas contenir de chiffre");
		boolean paysAlpha     = ControllerSaisie.isAlpha(pays, paysErr, " Le champ ne doit pas contenir de chiffre");
		

		
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
