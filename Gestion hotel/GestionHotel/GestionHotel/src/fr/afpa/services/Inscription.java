package fr.afpa.services;

import java.time.LocalDate;
import java.util.Random;

import java.util.Scanner;

import fr.afpa.beans.CategorieChambre;
import fr.afpa.beans.Client;
import fr.afpa.beans.Reservation;
import fr.afpa.main.Entier;

public class Inscription {
	private String numero;
	private static boolean employes = false;
	private String prenom;
	private String telephone;
	private String listReservation;
	static String[] clientId = new String[50];
	static Client[] file = new Client[50];
	private static GestionChambre hotel = new GestionChambre();
	private static Client cl = new Client();

	public void setId(String id) {

		numero = id;
	}

	public String getId() {
		return numero;
	}

	public void connexion() {

	}

	public String[] getClientId() {
		return clientId;
	}

	public static Client inscrire() {
		String str2 = "*";
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println("Bonjour,||||| bienvenue dans l'HOTEL CDA |||||");
		System.out.println("Avez-vous un code ? Si oui veuillez nous le renseigner :");
		str2 = saisieUtilisateur.next();
		if (!(str2.equals(cl.getIdClient()))) {
			String str = "";
			Client user = new Client();
			System.out.print("Inconnu.....Inscrivez vous, nom : ");
			user.setNom(saisieUtilisateur.next());
			System.out.print("... prenom : ");
			user.setPrenom(saisieUtilisateur.next());
			for (int i = 0; i < 10; i++) {
				String code = Integer.toString((int) (Math.random() * 9) + 0);
				str = str + code;
			}
			user.setIdClient(str);
			System.out.println("Votre code est : " + str);
			return user;
		}
		return cl;
	}

	public static Client login(GestionChambre hotel) {
		Client cl = new Client();
		boolean client = false;
		boolean exist = false;
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.print("Entrez votre code d'identification :");
		String demande = saisieUtilisateur.nextLine();

		if (demande.startsWith("GH") && demande.length() == 6) {
			employes = true;
			employeMenu(hotel);
		}
		
	    if (demande.length() == 10 && Entier.isInt(demande) ) {
			Reservation resa = hotel.rechercheClientParId(demande);
			if( resa != null){
				resa.afficherReservation();
				Inscription.login(hotel);
			}
			
			

			// afficheFacture(CategorieChambre catChambre, LocalDate dateDebutLD, LocalDate
			// dateFinLD, float currentTarif);

		} else {
			System.out.print("Erreur d'authentification ,Reesasayer ou inscrvez vous:");
			cl = inscrire();

		}

		return cl;
	}

	public static void affichageMenu() {
		System.out.println("_____________________MENU HOTEL CDA JAVA _____________________");
		System.out.println("   A- Afficher l'état de l'hotel");
		System.out.println("   B- Afficher le nombre de chambres reservees");
		System.out.println("   C- Afficher le nombre de chambre libre");
		System.out.println("   D- Afficher le numero de la première chambre vide");
		System.out.println("   E- Afficher le numero de la dernière chambre vide");
		System.out.println("   F- Reserver une chambre ");
		System.out.println("   G- Liberer une chambre");
		System.out.println("   H- Modifier une reservation");
		System.out.println("   I- Annuler une reservation");
		System.out.println("   J- Afficher le CA à une date donnée");
		System.out.println("   Q- Quitter");
		System.out.println("_______________________________________________________________");
	}

	public static void employeMenu(GestionChambre hotel) {
		Scanner saisieUtilisateur = new Scanner(System.in);

		String key = "";
		while (!key.equals("Q")) {
			affichageMenu();
			System.out.println("Quels informations recherchez vous ? :");
			key = saisieUtilisateur.next();
			switch (key) {
			case "A":
				hotel.afficheEtatHotel();
				break;
			case "B":
				hotel.afficherNbResa();
				break;
			case "C":
				hotel.afficherNbLibre();
				break;
			case "D":
				hotel.afficherPremiereChambre();
				break;
			case "E":
				hotel.afficherDerniereChambre();
				break;
			case "F":
				hotel.reserverChambre();
				break;
			case "G":
				hotel.libererChambre();
				break;
			case "H":
				hotel.modifierReservation();
				break;
			case "I":
				hotel.annulerReservation();
				break;
			case "J":
				hotel.afficherCa();
				break;
			}
			if (key.equals("Q")) {
				Inscription.login(hotel);
			}
		}
	}

	public static void Clientmenu() {
		System.out.print("GG menu:");
	}

	public static void ListAt() {
		for (int i = 0; i < file.length; i++) {
			if (file[i] != null) {
				System.out.println("client n°" + i + " --->" + file[i]);
			}
		}
	}

}
