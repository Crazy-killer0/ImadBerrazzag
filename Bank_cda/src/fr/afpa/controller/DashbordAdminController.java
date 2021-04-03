package fr.afpa.controller;

import java.io.IOException;


import fr.afpa.beans.Admin;
import fr.afpa.beans.Agence;
import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.beans.Conseiller;
import fr.afpa.beans.Utilisateur;
import fr.afpa.service.GestionAgence;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashbordAdminController {

	@FXML
	Button logout;
	@FXML
	Button searchCompte;
	@FXML
	Button addAgence;
	@FXML
	Button searchAgence;
	@FXML
	Button searchClient;
	@FXML
	Button addClient;
	@FXML
	Button addConseiller;
	@FXML
	Button searchConseiller;
	@FXML
	TableView<Agence> listAgence;
	@FXML
	TableColumn<Agence, String> codeAgence;
	@FXML
	TableColumn<Agence, String> nomAgence;
	@FXML
	TableView<Conseiller> listConseiller;
	@FXML
	TableColumn<Conseiller, String> nomConseiller;
	@FXML
	TableColumn<Conseiller, String> prenomConseiller;
	@FXML
	TableColumn<Conseiller, String> idConseiller;
	@FXML
	TableColumn<Conseiller, String> mailConseiller;
	@FXML
	TextField codeAgenceRecherche;
	@FXML
	TextField searchTextField;
	@FXML
	TextField searchTextFieldConseiller;
	@FXML
	TextField searchTextFieldClient;
	@FXML
	TableView<Client> listClient;
	@FXML
	TableColumn<Client, String> nomClient;
	@FXML
	TableColumn<Client, String> prenomClient;
	@FXML
	TableColumn<Client, String> idClient;
	@FXML
	TextField searchTextFieldCompte;
	@FXML
	TableView<Compte> listCompte;
	@FXML
	TableColumn<Compte, String> numCompteSearchCompte;
	@FXML
	TableColumn<Compte, String> typeCompteSearchCompte;
	@FXML
	TableColumn<Compte, String> soldeCompteSearchCompte;
	
	GestionUtilisateur gu = new GestionUtilisateur();
	GestionAgence ga = new GestionAgence();
	GestionCompte gc = new GestionCompte();

	/**
	 * 
	 */
	public void initData() {
		
		ObservableList<Agence> listDataAgences = FXCollections.observableArrayList(ga.getListAgences());
		ObservableList<Conseiller> listDataConseillers = FXCollections.observableArrayList(gu.getListConseiller());
		
		codeAgence.setCellValueFactory(new PropertyValueFactory<>("codeAgence"));
		nomAgence.setCellValueFactory(new PropertyValueFactory<>("nom"));

		idConseiller.setCellValueFactory(new PropertyValueFactory<>("login"));
		nomConseiller.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomConseiller.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		mailConseiller.setCellValueFactory(new PropertyValueFactory<>("email"));

		// search bar agence
		FilteredList<Agence> filteredAgence = new FilteredList<Agence>(listDataAgences, b -> true);
		searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredAgence.setPredicate(agence -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String str = newValue.toLowerCase();

				if (agence.getNom().toLowerCase().contains(str)) {
					return true;
				} else if (agence.getCodeAgence().toLowerCase().contains(str)) {
					return true;
				}
				return false;
			});
		});

		SortedList<Agence> sortedAgences = new SortedList<>(filteredAgence);
		sortedAgences.comparatorProperty().bind(listAgence.comparatorProperty());
		listAgence.setItems(sortedAgences);

		// search bar conseiller
		FilteredList<Conseiller> filteredConseillers = new FilteredList<Conseiller>(listDataConseillers, b -> true);
		searchTextFieldConseiller.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredConseillers.setPredicate(conseiller -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (conseiller.getNom().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} else if (conseiller.getPrenom().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} else if (conseiller.getEmail().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				} else if (conseiller.getLogin().toLowerCase().contains(lowerCaseFilter)) {

					return true;

				}
				return false;
			});
		});

		SortedList<Conseiller> sortedConseillers = new SortedList<>(filteredConseillers);
		sortedConseillers.comparatorProperty().bind(listConseiller.comparatorProperty());
		listConseiller.setItems(sortedConseillers);
	}

	/**
	 * 
	 */
	public void ajouterAgence() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/CreationAgence.fxml"));
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
	 * 
	 */
	public void ajouterConseiller() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/CreationConseiller.fxml"));
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
	 * 
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

	/**
	 * 
	 */
	public void modifAgence() {

		Agence agence = listAgence.getSelectionModel().getSelectedItem();

		if (agence != null) {

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/ModifierAgence.fxml"));
				Scene scene = new Scene(loader.load());
				ModificationAgenceController controller = loader.getController();
				controller.initData(agence);
				Stage stage = (Stage) logout.getScene().getWindow();
				stage.setScene(scene);
		
				System.out.println(agence);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 
	 */
	public void modifConseiller() {
		
		Conseiller conseiller = listConseiller.getSelectionModel().getSelectedItem();
		
		if (conseiller != null) {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/ModificationConseiller.fxml"));

			try {
				
				Scene scene = new Scene(loader.load());
				ModficationConseillerController controller = loader.getController();
				controller.initData(conseiller);
				Stage stage = (Stage) logout.getScene().getWindow();
				stage.setScene(scene);
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 
	 */
	public void ajouterClient() {
		
		Utilisateur user = new Admin();
		
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
	
	/**
	 * 
	 */
	public void rechercherClient() {
		
		try {
			
			FXMLLoader loaderRechercher = new FXMLLoader(getClass().getResource("/fr/afpa/view/RechercherClient.fxml"));
			Scene sceneSearch = new Scene(loaderRechercher.load());
			DashbordAdminController controller = loaderRechercher.getController();
			controller.initListClient();
			
			Stage search = new Stage();

			search.initOwner((Stage) logout.getScene().getWindow());
			search.initModality(Modality.APPLICATION_MODAL);
			search.setScene(sceneSearch);
			search.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 */
	public void initListClient() {
		
		ObservableList<Client> listDataClient = FXCollections.observableArrayList(gu.getListClient());
		
		nomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    prenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		
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
		
	}
	
	/**
	 * 
	 */
	public void detailClient() {
		
		Client client = listClient.getSelectionModel().getSelectedItem();
	
		if (client != null) {

			Stage search = (Stage) listClient.getScene().getWindow();
			
			try {

		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/GestionClient.fxml"));
				Scene scene = new Scene(loader.load());
				GestionClientController controller = loader.getController();
				controller.initData(client);
				Stage stage = (Stage) (((Stage) listClient.getScene().getWindow()).getOwner());
				stage.setScene(scene);
				search.close();
			
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
	
	/**
	 * 
	 */
	public void rechercherCompte() {
		
		try {
			
			FXMLLoader loaderRechercher = new FXMLLoader(getClass().getResource("/fr/afpa/view/RechercherCompte.fxml"));
			Scene sceneSearch = new Scene(loaderRechercher.load());
			DashbordAdminController controller = loaderRechercher.getController();
			controller.initListCompte();
			
			Stage search = new Stage();

			search.initOwner((Stage) logout.getScene().getWindow());
			search.initModality(Modality.APPLICATION_MODAL);
			search.setScene(sceneSearch);
			search.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 
	 */
	public void initListCompte() {
		
		ObservableList<Compte> listDataComptes = FXCollections.observableArrayList(gc.getListCompte());
		numCompteSearchCompte.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
	    soldeCompteSearchCompte.setCellValueFactory(new PropertyValueFactory<>("solde"));
		typeCompteSearchCompte.setCellValueFactory(new PropertyValueFactory<>("type"));
		
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
	 * 
	 */
	public void detailCompte() {
		
		Compte compte = listCompte.getSelectionModel().getSelectedItem();
		
		if (compte != null) {

			Stage search = (Stage) listCompte.getScene().getWindow();
			
			try {

		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DetailleCompte.fxml"));
				Scene scene = new Scene(loader.load());
				DetailleCompteController  controller = loader.getController();
				controller.initData(compte);
				Stage stage = (Stage) (((Stage) listCompte.getScene().getWindow()).getOwner());
				stage.setScene(scene);
				search.close();
			
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
