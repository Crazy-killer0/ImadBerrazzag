package fr.afpa.main;

import fr.afpa.beans.GestionVolaille;

public class Main {

	public static void main(String[] args) {

		GestionVolaille gestionVolaille = new GestionVolaille();

		gestionVolaille.menu();
		gestionVolaille.affichage();

	}

}
