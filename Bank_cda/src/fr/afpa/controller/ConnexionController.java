package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Admin;
import fr.afpa.beans.Client;
import fr.afpa.beans.Conseiller;
import fr.afpa.beans.Utilisateur;
import fr.afpa.service.GestionUtilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnexionController {

	@FXML
	TextField login;
	@FXML
	Button connexion;
	@FXML
	PasswordField password;
	@FXML
	Label erreurLogin;
	@FXML
	TextField codeVerif;
	@FXML
	Button valider;

	GestionUtilisateur gu = new GestionUtilisateur();

	Utilisateur user;
	
	public void authentification() {

		if (!login.getText().equals("") && !password.getText().equals("")) {

			if (login.getLength() == 10 && ControllerSaisie.isInt(login.getText())) {

				Client client = gu.rechercherClientByLogin(login.getText());

				if (client != null && client.getMotDePasse().equals(password.getText())) {

					try {

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashbordClient.fxml"));

						Scene scene = new Scene(loader.load());
						DashbordClientController controller = loader.getController();
						controller.initData(client);
						Stage stage = (Stage) login.getScene().getWindow();
						stage.setScene(scene);
						stage.show();

					} catch (IOException e) {

						e.printStackTrace();
					}

				} else {

				}

			} else if (ControllerSaisie.verifLoginConseiller(login.getText())) {

				Conseiller conseiller = gu.rechercherConseillerByLogin(login.getText());
				System.out.println(conseiller);
				if (conseiller != null && conseiller.getMotDePasse().equals(password.getText())) {

					System.out.println(conseiller);
					try {

						user = conseiller;
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashordConseiller.fxml"));
						Scene scene = new Scene(loader.load());
						DashbordConseillerController controller = loader.getController();
						controller.initData(conseiller);
						Stage stage = (Stage) connexion.getScene().getWindow();
						stage.setScene(scene);
						stage.show();

					} catch (IOException e) {

						e.printStackTrace();
					}

				}

			} else if (ControllerSaisie.verifLoginAdmin(login.getText())) {

				Admin admin = gu.rechercherAdminByLogin(login.getText());

				if (admin != null && admin.getMotDePasse().equals(password.getText())) {

					gu.generDoubleaUTH(admin);

					FXMLLoader loaderAuth = new FXMLLoader(getClass().getResource("/fr/apfa/view/AuthentificationPopUp.fxml"));
					Scene sceneAuth = null;

					try {

						sceneAuth = new Scene(loaderAuth.load());
						ConnexionController controller = loaderAuth.getController();
						controller.initDataLoginAdmin(admin);

					} catch (IOException e1) {

						e1.printStackTrace();
					}

					Stage auth = new Stage();

					auth.initOwner((Stage) connexion.getScene().getWindow());
					auth.initModality(Modality.APPLICATION_MODAL);
					auth.setScene(sceneAuth);
					auth.showAndWait();

				}

			}
		}

	}

	public void initDataLoginAdmin(Admin admin) {

		codeVerif.setUserData(admin);
	}

	public void verifDoubleAuth() {

		if (codeVerif.getText().equals(((Admin) codeVerif.getUserData()).getMdpDoubleAuth())) {

			Stage auth = (Stage) codeVerif.getScene().getWindow();

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashbordAdmin.fxml"));
				Scene scene = new Scene(loader.load());
				DashbordAdminController controller = loader.getController();
				controller.initData();
				Stage stage = (Stage) (((Stage) codeVerif.getScene().getWindow()).getOwner());
				stage.setScene(scene);
				auth.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
