package fr.afpa.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.beans.Operation;
import fr.afpa.service.GestionCompte;
import fr.afpa.service.GestionUtilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DetailleCompteController {

	@FXML
	Label typeCompte;
	@FXML
	Label numCompte;
	@FXML
	Label soldeCompte;
	@FXML
	Label MontantOperationAVenir;
	@FXML
	TableView<Operation> listO;
	@FXML
	TableColumn<Operation, String> columnTypeOperation;
	@FXML
	TableColumn<Operation, Double> columnMontantOperation;
	@FXML
	TableColumn<Operation, String> columnNumeroOperation;
	@FXML
	TableColumn<Operation, LocalDateTime> columnDateOperation;
	@FXML
	Button ButtonVirement;
	@FXML
	Button ButtonAlimenter;
	@FXML
	Button ButtonRetirer;
	@FXML
	Button logout;
	@FXML
	TableView <Operation> listOperationComptes;
	
	Compte compte;
	
	GestionUtilisateur gc = new GestionUtilisateur();
	
/**
 * 
 * @param compte
 */
	public void initData(Compte compte) {
		
		this.compte = compte;
		ObservableList<Operation> listDataOperations = FXCollections.observableArrayList(compte.getListOperations());
        ButtonVirement.setUserData(gc.rechercheCompte(compte.getNumeroCompte()));
        typeCompte.setText(compte.getType());
        numCompte.setText(compte.getNumeroCompte());
        soldeCompte.setText(String.valueOf(compte.getSolde()));
        // MontantOp�rationAVenir.setText(compte.get));


        columnMontantOperation.setCellValueFactory(new PropertyValueFactory<>("montant"));
        columnNumeroOperation.setCellValueFactory(new PropertyValueFactory<>("numeroOperation"));
        columnDateOperation.setCellValueFactory(new PropertyValueFactory<>("dateFinOperation"));

        listOperationComptes.setItems(listDataOperations);
		

	}

	public void back() {

		try {
			Client client = (Client)  ButtonVirement.getUserData();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/DashbordClient.fxml"));
			DashbordClientController controller = loader.getController();
			System.out.println("back " + client);
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
	public void retirer() {
		boolean positif = false;
		try {FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/PopOperation.fxml"));
		PopOperationController controller = loader.getController();
		
		controller.initData(compte);
		Scene scene;
		scene = new Scene(loader.load());
		Stage stage = (Stage) ButtonVirement.getScene().getWindow();
		stage.setScene(scene);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void alimenter() {
		boolean positif = true;
		try {FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/PopOperation.fxml"));
		Scene scene;
		scene = new Scene(loader.load());
		Stage stage = (Stage) logout.getScene().getWindow();
		stage.setScene(scene);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
	public void virement() {
		
		
		try {FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/view/Virement.fxml"));
			Scene scene;
			scene = new Scene(loader.load());
			
			VirementController controller = loader.getController();
			
			controller.initData(compte);
			Stage stage = (Stage) ButtonVirement.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ModificationAgenceController controller = loader.getController();
		//controller.initData(agence);
		
		
	}
}
