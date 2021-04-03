package fr.afpa.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.afpa.beans.Admin;
import fr.afpa.beans.Adresse;
import fr.afpa.beans.Agence;
import fr.afpa.beans.Client;
import fr.afpa.beans.Conseiller;
import fr.afpa.beans.Utilisateur;
import fr.afpa.service.GestionAgence;
import fr.afpa.service.GestionUtilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CreationClientController {

	@FXML
	Button logout;
	@FXML
	Button back;
	@FXML
	DatePicker dateN;
	@FXML
	Label prenomErr;
	@FXML
	Label nomErr;
	@FXML
	Label mailErr;
	@FXML
	Label numRueErr;
	@FXML
	Label nomRueErr;
	@FXML
	Label paysErr;
	@FXML
	Label villeErr;
	@FXML
	Label cPErr;
	@FXML
	Label codeAgenceErr;
	@FXML
	TextField codePostal;
	@FXML
	TextField codeAgence;
	@FXML
	TextField prenom;
	@FXML
	TextField nom;
	@FXML
	TextField mail;
	@FXML
	TextField numRue;
	@FXML
	TextField nomRue;
	@FXML
	TextField ville;
	@FXML
	TextField pays;

	Utilisateur user;
	GestionUtilisateur gu = new GestionUtilisateur();
	GestionAgence ga = new GestionAgence();
	

	public void initData(Utilisateur user) {

		this.user = user;

		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			@Override
			public String toString(LocalDate date) {
				 if (date != null) {
	                    return dateTimeFormatter.format(date);
	                } else {
	                    return "";
	                }
			}

			@Override
			public LocalDate fromString(String string) {
				 if (string != null && !string.isEmpty()) {
					 
	                    return LocalDate.parse(string, dateTimeFormatter);
	                    
	                } else {
	                	
	                    return null;
			        }
			    }
			
		};
		
		dateN.setConverter(converter);
	}

	public void creationClient() {
	
		System.out.println(controlForm());
		if(controlForm()) {
			
			Agence agence = ga.rechercherAgence(codeAgence.getText());
			
			if(user instanceof Admin) {
				user = new Conseiller(user.getNom(), user.getPrenom(), user.getEmail(), agence);
			}
			System.out.println(user);
			Adresse adresse = new Adresse(numRue.getText(), nomRue.getText(), codePostal.getText(), ville.getText(), pays.getText());
			Client client = new Client(nom.getText(), prenom.getText(), mail.getText(), dateN.getValue(), adresse, agence, (Conseiller) user);
			gu.creationUtilsateur(client);
			System.out.println(client);
			back();
			
		}
	}
	
	public void deconnecter() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/EspaceConnexion.fxml"));
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

		if (user instanceof Conseiller) {

			
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashordConseiller.fxml"));
				
				Scene scene = new Scene(loader.load());
				Stage stage = (Stage) logout.getScene().getWindow();
				DashbordConseillerController controller = loader.getController();
				controller.initData((Conseiller)user);
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashbordAdmin.fxml"));
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

	}
	
	/**
	 * Methode qui controle le formulaire
	 * @return boolean : status du controle
	 */
	public boolean controlForm() {

		boolean valide = true;
		
		boolean nomEmpty        = ControllerSaisie.textFieldEmpty(nom, nomErr, " Le champ ne doit pas être vide");
		boolean prenomEmpty     = ControllerSaisie.textFieldEmpty(prenom, prenomErr, " Le champ ne doit pas être vide");
		boolean emailEmpty      = ControllerSaisie.textFieldEmpty(mail, mailErr, " Le champ ne doit pas être vide");
		boolean codeAgenceEmpty = ControllerSaisie.textFieldEmpty(codeAgence, codeAgenceErr," Le champ ne doit pas être vide");
		boolean nomRueEmpty     = ControllerSaisie.textFieldEmpty(nomRue, nomRueErr, " Le champ ne doit pas être vide");
		boolean numRueEmpty     = ControllerSaisie.textFieldEmpty(numRue, numRueErr, " Le champ ne doit pas être vide");
		boolean villeEmpty      = ControllerSaisie.textFieldEmpty(ville, villeErr, " Le champ ne doit pas être vide");
		boolean paysEmpty       = ControllerSaisie.textFieldEmpty(pays, paysErr, " Le champ ne doit pas être vide");
		boolean cpEmpty         = ControllerSaisie.textFieldEmpty(codePostal, cPErr, " Le champ ne doit pas être vide");
		
		boolean nomAlpha    = ControllerSaisie.isAlpha(nom, nomErr, " Le champ ne doit pas contenir de chiffre");
		boolean prenomAlpha = ControllerSaisie.isAlpha(prenom, prenomErr, " Le champ ne doit pas contenir de chiffre");
		boolean villeAlpha   = ControllerSaisie.isAlpha(ville, villeErr, " Le champ ne doit pas contenir de chiffre");
		boolean paysAlpha    = ControllerSaisie.isAlpha(pays, paysErr, " Le champ ne doit pas contenir de chiffre");
		
		boolean mailValide      = ControllerSaisie.mailControl(mail, mailErr, " L'adresse mail est invalide");
		boolean codeAgenceValide = ControllerSaisie.isNumeric(codeAgence, codeAgenceErr, " Code agence invalide");

		if (nomEmpty || prenomEmpty || emailEmpty || codeAgenceEmpty ||nomRueEmpty|| numRueEmpty || villeEmpty || paysEmpty || cpEmpty) {

			valide =  false;
		
		} else if (!nomAlpha || !prenomAlpha || !mailValide || !codeAgenceValide || !villeAlpha || !paysAlpha) {

			valide =  false;
		
		} else {
			
			Agence agence = ga.rechercherAgence(codeAgence.getText());
			
			System.out.println(agence);
			if (agence != null && dateN.getValue()!= null) {

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
