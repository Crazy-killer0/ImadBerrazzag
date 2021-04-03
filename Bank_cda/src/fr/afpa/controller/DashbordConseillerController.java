package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Admin;
import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.beans.Conseiller;
import fr.afpa.beans.Utilisateur;
import fr.afpa.service.GestionCompte;
import fr.afpa.service.GestionUtilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DashbordConseillerController {

	@FXML
	Button addCompte;
	@FXML
	Button searchCompte;
	@FXML
	Button addClient;
	@FXML
	Button searchClient;
	@FXML
	Button logout;
	@FXML
	TableView<Client> listClient;
	@FXML
	TableView<Compte> listCompte;
	@FXML
	TableColumn<Client, String> prenomClient;
	@FXML
	TableColumn<Client, String> idClient;
	@FXML
	TableColumn<Compte, String> numCompte;
	@FXML
	TableColumn<Client, String> nomClient;
	@FXML
	TableColumn<Compte, String> typeCompte;
	@FXML
	TableColumn<Compte, String> soldeCompte;
	@FXML
	TextField searchTextFieldCompte;
	@FXML
	TextField searchTextFieldClient;
	
	GestionUtilisateur gu = new GestionUtilisateur();
	GestionCompte gc = new GestionCompte();
	Conseiller conseiller;
	
	/**
	 * Methode qui  nous permet de charger les data d'un conseiller
	 * @param conseiller
	 */
	public void initData(Conseiller conseiller) {

		this.conseiller = conseiller;
	
		ObservableList<Client> listDataClient = FXCollections.observableArrayList(gu.listClientParConseiller(conseiller));
		nomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
		idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		prenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		
		FilteredList<Client> filteredClient = new FilteredList<Client>(listDataClient, b -> true);
		searchTextFieldClient.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredClient.setPredicate(client -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (client.getNom().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} else if (client.getPrenom().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} else if (client.getEmail().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} 
				
				for(Compte  compte : client.getListeCompte()) {
					if(compte.getNumeroCompte().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}
				}
				return false;
			});
		});
	
		SortedList<Client> sortedClients = new SortedList<>(filteredClient);
		sortedClients.comparatorProperty().bind(listClient.comparatorProperty());
		listClient.setItems(sortedClients);
		
		
		ObservableList<Compte> listDataComptes = FXCollections.observableArrayList(gc.getListCompte());
		
		numCompte.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
	    soldeCompte.setCellValueFactory(new PropertyValueFactory<>("solde"));
		typeCompte.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		FilteredList<Compte> filteredComptes = new FilteredList<Compte>(listDataComptes, b -> true);
		searchTextFieldCompte.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredComptes.setPredicate(compte -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (compte.getNumeroCompte().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} 
				return false;
			});
		});
	
		SortedList<Compte> sortedComptes = new SortedList<>(filteredComptes);
		sortedComptes.comparatorProperty().bind(listCompte.comparatorProperty());
		listCompte.setItems(sortedComptes);
	}

	/**
	 * Methode pour la déconnexion de l'utilisateur 
	 */
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
	

	public void detailClient() {
		
		Client client = listClient.getSelectionModel().getSelectedItem();
	
		if (client != null) {

			Stage search = (Stage) listClient.getScene().getWindow();
			
			try {

		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/GestionClient.fxml"));
				Scene scene = new Scene(loader.load());
				GestionClientController controller = loader.getController();
				controller.initData(client);
				Stage stage = (Stage) listClient.getScene().getWindow();
				stage.setScene(scene);
			
			
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
	
	
	public void detailCompte() {
		
		Compte compte = listCompte.getSelectionModel().getSelectedItem();
		
		if (compte != null) {

			Stage search = (Stage) listCompte.getScene().getWindow();
			
			try {

		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DetailleCompte.fxml"));
				Scene scene = new Scene(loader.load());
				DetailleCompteController  controller = loader.getController();
				controller.initData(compte);
				Stage stage = (Stage) listCompte.getScene().getWindow();
				stage.setScene(scene);
			
			
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void ajouterClient() {
		
		Utilisateur user = conseiller;
		
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/CreationClient.fxml"));
			Scene scene = new Scene(loader.load());
			CreationClientController controller = loader.getController();
			controller.initData(user);
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setScene(scene);
	
			System.out.println();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
