package fr.afpa.controller;

import java.io.IOException;

import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.beans.Operation;
import fr.afpa.service.GestionCompte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class VirementController {
	@FXML
	TextField montantVirement;
	@FXML
	Button back;
	@FXML
	TextField comptExt;
	@FXML
	Button valider;
	@FXML
	Button annuler;
	@FXML
	Button logout;
	@FXML
	TableColumn<Operation, String> finOperation;
	@FXML
	TableColumn<Operation, Double> operation;
	@FXML
	TableColumn<Operation, String> solde;
	@FXML
	TableView <Operation>listOperationCompte;
	
	Compte compte;
	
	GestionCompte gc = new GestionCompte();
	
	public void initData(Compte compte) {
		
		this.compte = compte;
		ObservableList<Operation> listDataOperations = FXCollections.observableArrayList(compte.getListOperations());
		//typeCompte.setText(compte.getType()); 
		
		
		finOperation.setCellValueFactory(new PropertyValueFactory<>("dateFinOperation"));
		solde.setCellValueFactory(new PropertyValueFactory<>("montant"));
		operation.setCellValueFactory(new PropertyValueFactory<>("numeroOperation"));
		listOperationCompte.setItems(listDataOperations);

	}
	public void retour() {

		try {
			Client client = (Client)  logout.getUserData();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DetailleCompte.fxml"));
			DashbordClientController controller = loader.getController();
			controller.initData(client);
			Scene scene = new Scene(loader.load());
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void effectuerVirement() {
		
		
		Client clientemet = gc.rechercheClientParCompte(compte.getNumeroCompte());
		String numcomptExt = comptExt.getText();
		int montant = Integer.parseInt(montantVirement.getText());
		Compte compteBenef = gc.rechercheCompte(numcomptExt);
		Client benf = gc.rechercheClientParCompte(numcomptExt); 
		System.out.println("Virement effectuer");
		gc.virement(compte, compteBenef, montant, clientemet, benf);
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
}
