package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Agence;
import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DashbordClientController {

	@FXML
	Button logout;
	@FXML
	Button account;
	@FXML
	TableView<Compte> listCompte;
	@FXML
	TableColumn<Compte, String> typeCompte;
	@FXML
	TableColumn<Compte, String> numCompte;
	@FXML
	TableColumn<Compte, String> solde;

	/**
	 * 
	 * @param client
	 */
	public void initData(Client client) {

		logout.setUserData(client);
		typeCompte.setCellValueFactory(new PropertyValueFactory<>("type"));
		numCompte.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
		solde.setCellValueFactory(new PropertyValueFactory<>("solde"));

		ObservableList<Compte> list = FXCollections.observableArrayList();
		list.addAll(client.getListeCompte());
		listCompte.setItems(list);
		
		

	}
	
	
	public void detailleCompte() {
	
		Compte compte = listCompte.getSelectionModel().getSelectedItem();
		System.out.println(compte);

		
		

		if (compte != null) {

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DetailleCompte.fxml"));
				Scene scene = new Scene(loader.load());
				
				DetailleCompteController controller = loader.getController();
				controller.initData(compte);
				//initData(client);
				Stage stage = (Stage) logout.getScene().getWindow();
				stage.setScene(scene);
		
				System.out.println("");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
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


	

}
