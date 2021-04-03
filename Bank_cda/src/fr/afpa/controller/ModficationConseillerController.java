package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Adresse;
import fr.afpa.beans.Agence;
import fr.afpa.beans.Conseiller;
import fr.afpa.service.GestionAgence;
import fr.afpa.service.GestionUtilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModficationConseillerController {

	
	Conseiller conseiller;
	@FXML
	Label nomErr;
	@FXML
	Label prenomErr;
	@FXML
	Label emailErr;
	@FXML
	Label codeAgenceErr;
	@FXML
	Label motDePasseErr;
	@FXML
	TextField nom;
	@FXML
	TextField prenom;
	@FXML
	TextField email;
	@FXML
	TextField codeAgence;
	@FXML
	TextField password;
	@FXML
	Button desactiver;

	GestionUtilisateur gu = new GestionUtilisateur();
	GestionAgence ga = new GestionAgence();
	
	public void initData(Conseiller conseiller) {

		this.conseiller = conseiller;

		nom.setText(conseiller.getNom());
		prenom.setText(conseiller.getPrenom());
		email.setText(conseiller.getEmail());
		password.setText(conseiller.getMotDePasse());
		codeAgence.setText(conseiller.getAgence().getCodeAgence());

	}
	
	public void update() {
		
	
		if(controlForm()) {
			
			
			conseiller.setEmail(email.getText());
			conseiller.setNom(nom.getText());
			conseiller.setMotDePasse(password.getText());
			conseiller.setPrenom(prenom.getText());
			conseiller.setAgence(ga.rechercherAgence(codeAgence.getText()));

			gu.modifierConseiller(conseiller);
			System.out.println(conseiller);
			
			back();
		}
	}
	

	public void status() {

		if (desactiver.getText().equals("Désactiver")) {

			gu.activerConseiller(conseiller, false);
			desactiver.setText("Activer     ");
			desactiver.getStyleClass().remove("btnRed");
			desactiver.getStyleClass().add("btnGreen");

		} else {


			gu.activerConseiller(conseiller, true);
			desactiver.getStyleClass().remove("btnGreen");
			desactiver.getStyleClass().add("btnRed");
			desactiver.setText("Désactiver");

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
			Stage stage = (Stage) email.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean controlForm() {

		boolean valide = true;
		
		boolean nomEmpty        = ControllerSaisie.textFieldEmpty(nom, nomErr, " Le champ ne doit pas être vide");
		boolean prenomEmpty     = ControllerSaisie.textFieldEmpty(prenom, prenomErr, " Le champ ne doit pas être vide");
		boolean emailEmpty      = ControllerSaisie.textFieldEmpty(email, emailErr, " Le champ ne doit pas être vide");
		boolean codeAgenceEmpty = ControllerSaisie.textFieldEmpty(codeAgence, codeAgenceErr," Le champ ne doit pas être vide");
		boolean passwordEmpty   = ControllerSaisie.textFieldEmpty(password, motDePasseErr, " Le champ ne doit pas être vide");
		
		boolean nomAlpha    = ControllerSaisie.isAlpha(nom, nomErr, " Le champ ne doit pas contenir de chiffre");
		boolean prenomAlpha = ControllerSaisie.isAlpha(prenom, prenomErr, " Le champ ne doit pas contenir de chiffre");

		boolean emailValide      = ControllerSaisie.mailControl(email, emailErr, " L'adresse mail est invalide");
		boolean codeAgenceValide = ControllerSaisie.isNumeric(codeAgence, codeAgenceErr, " Code agence invalide");
		boolean passwordValide   = ControllerSaisie.MdpControl(password, motDePasseErr, " Le mot de passe ne doit pas dépasser 9 charactère");
		
		if (nomEmpty || prenomEmpty || emailEmpty || codeAgenceEmpty || passwordEmpty) {

			valide =  false;
		
		} else if (!nomAlpha || !prenomAlpha || !emailValide || !codeAgenceValide || !passwordValide) {

			valide =  false;
		
		} else {
			
			Agence agence = ga.rechercherAgence(codeAgence.getText());
			
			System.out.println(agence);
			if (agence != null) {

				System.out.println(agence);
				valide =  true;
				
				
			} else {
				
				codeAgenceErr.setText("Code agence invalide");
				valide =  false;
			}
		}
		System.out.println(valide);
		return valide;

	}

}
