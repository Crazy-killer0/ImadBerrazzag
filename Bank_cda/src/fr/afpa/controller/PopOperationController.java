package fr.afpa.controller;

import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.service.GestionCompte;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PopOperationController {
	@FXML
	TextField montant;
	@FXML
	Button valider;
	Compte compte;
	GestionCompte gc = new GestionCompte();
	public void initData(Compte compte) {
		
		this.compte = compte;
		
	}
	
	public void operation(Boolean positif) {
		Client clientemet = gc.rechercheClientParCompte(compte.getNumeroCompte());
		int valeur = Integer.parseInt(montant.getText());	
		System.out.println("Virement effectuer");
		gc.virement(compte, compte, valeur, clientemet, clientemet);
	}
	
}