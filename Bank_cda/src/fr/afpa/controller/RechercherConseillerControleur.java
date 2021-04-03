package fr.afpa.controller;

import java.io.IOException;
import fr.afpa.service.GestionUtilisateur;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RechercherConseillerControleur {
	@FXML
	TextField eMail;

	@FXML
	Button valider;
	@FXML
	Button annuler;
	@FXML
	Label mailErr;
	GestionUtilisateur gu = new GestionUtilisateur();
	public void rechercher() {

		if (controlForm()) {

			String mail = eMail.getText();

			if (gu.rechercherConseiller(mail) != null) {

				AfficherInfo();

			} else {
				System.out.print("Existe pas");
				back();
			}

		}

	}

	public void back() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DashbordAdmin.fxml"));
			Scene scene = new Scene(loader.load());
			DashbordAdminController controller = loader.getController();
			controller.initData();
			Stage stage = (Stage) valider.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AfficherInfo() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ModificationConseiller.fxml"));
			Scene scene = new Scene(loader.load());
			Stage stage = (Stage) eMail.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean controlForm() {
		boolean valide = true;

		boolean mailEmpty = ControllerSaisie.textFieldEmpty(eMail, mailErr, " Le champ ne doit pas être vide");
		

		if (mailEmpty) {

			valide = false;

		}  else {

			valide = true;
		}

		return valide;
	}

}
