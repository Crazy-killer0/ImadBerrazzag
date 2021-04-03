package fr.afpa.controller;

import java.io.IOException;

import javax.annotation.Generated;

import fr.afpa.beans.Admin;
import fr.afpa.beans.Agence;
import fr.afpa.beans.Conseiller;
import fr.afpa.service.GestionAgence;
import fr.afpa.service.GestionUtilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreationConseillerController {

	@FXML
	Button logout;
	@FXML
	Button back;
	@FXML
	Button confirmer;
	@FXML
	Button user;
	@FXML
	RadioButton isAdmin;
	@FXML
	TextField prenom;
	@FXML
	TextField email;
	@FXML
	TextField codeAgence;
	@FXML
	TextField nom;
	@FXML
	Label nomErr;
	@FXML
	Label prenomErr;
	@FXML
	Label emailErr;
	@FXML
	Label codeAgenceErr;

	GestionUtilisateur gu = new GestionUtilisateur();
	GestionAgence ga = new GestionAgence();

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
			e.printStackTrace();
		}
	}

	public void creationConseiller() {

		if(controlForm()) {
			
			if (isAdmin.isSelected()) {

				Admin admin = new Admin(nom.getText(), prenom.getText(), email.getText());

				if (gu.creationUtilsateur(admin)) {

					back();

				}

			} else {

				Agence agence = ga.rechercherAgence(codeAgence.getText());

				Conseiller conseiller = new Conseiller(nom.getText(), prenom.getText(), email.getText(), agence);
				if (gu.creationUtilsateur(conseiller)) {
					back();

				}

			}
		}
		

	}

	public boolean controlForm() {

		boolean valide = true;
		
		boolean nomEmpty        = ControllerSaisie.textFieldEmpty(nom, nomErr, " Le champ ne doit pas être vide");
		boolean prenomEmpty     = ControllerSaisie.textFieldEmpty(prenom, prenomErr, " Le champ ne doit pas être vide");
		boolean emailEmpty      = ControllerSaisie.textFieldEmpty(email, emailErr, " Le champ ne doit pas être vide");
		boolean codeAgenceEmpty = ControllerSaisie.textFieldEmpty(codeAgence, codeAgenceErr," Le champ ne doit pas être vide");

		boolean nomAlpha    = ControllerSaisie.isAlpha(nom, nomErr, " Le champ ne doit pas contenir de chiffre");
		boolean prenomAlpha = ControllerSaisie.isAlpha(prenom, prenomErr, " Le champ ne doit pas contenir de chiffre");

		boolean emailValide      = ControllerSaisie.mailControl(email, emailErr, " L'adresse mail est invalide");
		boolean codeAgenceValide = ControllerSaisie.isNumeric(codeAgence, codeAgenceErr, " Code agence invalide");

		if (nomEmpty || prenomEmpty || emailEmpty || codeAgenceEmpty) {

			valide =  false;
		
		} else if (!nomAlpha || !prenomAlpha || !emailValide || !codeAgenceValide) {

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
