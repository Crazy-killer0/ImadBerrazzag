package fr.afpa.beans;

import java.util.Scanner;

public class GestionVolaille {

	private final int nbMaxVolaille = 7;
	private Volaille[] listeVolaille = new Volaille[nbMaxVolaille];
	private int nbrPoulet = 0;
	private int nbrCanard = 0;
	private int nbrPaon = 0;
	private int porte_monnaie = 0;
	private final int nbMaxPaon = 2;
	private final int nbMaxPoulet = 4;
	private final int nbMaxCanard = 3;
	private double prixPoulet = 9.97;
	private double prixCanard = 11.40;
	private double abattagePoulet = 2.8;
	private double abattageCanard = 3.40;

	private int nbVolaille;

	// Le constructeur vide
	public GestionVolaille() {
	}

	// Le constructeur qui initialise le tableau
	public GestionVolaille(Volaille[] listeVolaille) {

		listeVolaille = new Volaille[7];

	}

	public Volaille[] getListeVolaille() {
		return listeVolaille;
	}

	public void setListeVolaille(Volaille[] listeVolaille) {
		this.listeVolaille = listeVolaille;
	}

	public double getPrixPoulet() {
		return prixPoulet;
	}

	public void setPrixPoulet(double prixPoulet) {
		this.prixPoulet = prixPoulet;
	}

	public double getPrixCanard() {
		return prixCanard;
	}

	public void setPrixCanard(double prixCanard) {
		this.prixCanard = prixCanard;
	}

	public double getAbattagePoulet() {
		return abattagePoulet;
	}

	public void setAbattagePoulet(double abattagePoulet) {
		this.abattagePoulet = abattagePoulet;
	}

	public double getAbattageCanard() {
		return abattageCanard;
	}

	public void setAbattageCanard(double abattageCanard) {
		this.abattageCanard = abattageCanard;
	}

	Scanner in = new Scanner(System.in);

	// Afficher le menu
	public void menu() {

		int choix = 0;

		String menu = "--------------------> ELEVAGE <-------------------- \n";
		menu = menu + "	(1) - Ajouter une volaille \n";
		menu = menu + "	(2) - Modifier poids abattage \n";
		menu = menu + "	(3) - Modifier prix du jour \n";
		menu = menu + "	(4) - Modifier poids d'une volaille \n";
		menu = menu + "	(5) - Voir le nombre de volaille par type \n";
		menu = menu + "	(6) - Voir le total de prix des volailles abattables \n";
		menu = menu + "	(7) - Vendre une volaille \n";
		menu = menu + "	(8) - Rendre un paon au parc \n";
		menu = menu + "	(9) - Quitter \n";

		System.out.println(menu);

		System.out.print("Que voulez-vous faire ? : ");
		choix = in.nextInt();
		in.nextLine();

		while (choix != 9) {
			switch (choix) {
			case 1: // Ajouter une volaille

				ajouterVolaille();

				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 2: // Modifier le poids d'abattage

				modifierPoids();
				System.out.println();
				System.out.println("Le poids actuel d'abattage d'un poulet : " + getAbattagePoulet());
				System.out.println("Le poids actuel d'abattage d'un canard : " + getAbattageCanard());
				System.out.println();
				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 3: // Modifier le prix du jour

				modifierPrixJour();
				System.out.println();
				System.out.println("Le prix du jour actuel d'un poulet : " + getPrixPoulet());
				System.out.println("Le prix du jour actuel d'un canard : " + getPrixCanard());
				System.out.println();
				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 4: // Modifier le poids d'une volaille

				modifierPoidsVolaille();

				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 5: // Afficher le nombre de volailles par type

				afficherVolailleType();

				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 6: // Afficher le total de prix des volailles abattables

				afficherTotalPrix();

				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 7: // Vendre une volaille

				vendreVolaille();

				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			case 8: // Rendre un paon au parc

				rendrePaon();

				System.out.println(menu);
				System.out.print("Souhaitez-vous autre chose ? : ");
				choix = in.nextInt();
				in.nextLine();

				break;

			default:
				System.out.println(menu);
				System.out.println("Veuillez faire un choix correct : ");
				choix = in.nextInt();
				in.nextLine();
				break;
			}
		}

		System.out.println("Au revoir et a bientot !");

	}

	// Ajouter une volaille
	public void ajouterVolaille() {
		if (verificationTableau(listeVolaille) == true) {
			System.out.println("Veuillez entrer le nombre correspondant : ");
			System.out.println("1 - Ajouter un jeune poulet");
			System.out.println("2 - Ajouter un jeune canard");
			System.out.println("3 - Ajouter un paon");
			System.out.println("4 - Annuler");
			int choice = in.nextInt();
			in.nextLine();
			String numID;
			int age;
			double poids;
			switch (choice) {
			case 1:
				System.out.println("________ AJOUT POULET ________");
				if (verificationTypeVolaille(nbMaxPoulet, nbrPoulet) == true) {// FAIRE LA VERIFICATION
					nbrPoulet++;
					System.out.println("Entrer l'ID du poulet : ");
					numID = in.nextLine();
					age = 3;

					System.out.println("Entrer le poids du poulet : ");
					poids = in.nextDouble();

					while (poids <= 0) {
						System.out.print("Veuillez entrer un poids correct (sup�rieur � 0) : ");
						poids = in.nextDouble();
					}

					in.nextLine();

					Poulet jeunePoulet = new Poulet(poids, numID, age);
					for (int i = 0; i < listeVolaille.length; i++) {
						if (listeVolaille[i] == null) {
							listeVolaille[i] = jeunePoulet;
							break;
						}
					}
				} else {
					System.out.println("Nombre maximum de Poulet atteint !");
				}
				break;
			case 2:
				System.out.println("________ AJOUT CANARD ________");
				if (verificationTypeVolaille(nbMaxCanard, nbrCanard) == true) {// FAIRE LA VERIFICATION
					nbrCanard++;
					System.out.println("Entrer l'ID du canard : ");
					numID = in.nextLine();
					age = 3;

					System.out.println("Entrer le poids du canard : ");
					poids = in.nextDouble();

					while (poids <= 0) {
						System.out.print("Veuillez entrer un poids correct (sup�rieur � 0) : ");
						poids = in.nextDouble();
					}

					in.nextLine();

					Canard jeuneCanard = new Canard(poids, numID, age);
					for (int i = 0; i < listeVolaille.length; i++) {
						if (listeVolaille[i] == null) {
							listeVolaille[i] = jeuneCanard;
							break;
						}
					}
				} else {
					System.out.println("Nombre maximum de canard atteint !");
				}
				break;
			case 3:
				System.out.println("________ AJOUT PAON ________");
				if (verificationTypeVolaille(nbMaxPaon, nbrPaon) == true) {// FAIRE LA VERIFICATION
					nbrPaon++;
					System.out.println("Entrer l'ID du paon : ");
					numID = in.nextLine();

					Paon paon = new Paon(numID);
					for (int i = 0; i < listeVolaille.length; i++) {
						if (listeVolaille[i] == null) {
							listeVolaille[i] = paon;
							break;
						}
					}
				} else {
					System.out.println("Nombre maximum de Paon atteint !");
				}
				break;
			case 4:
				System.out.println("Vous avez annulee");
				break;
			default:
				System.out.println("Erreur de saisie");

				break;
			}
		} else {
			System.out.println("Plus de place pour d'autres Volaille !");
		}

	}

	// fonction de verification
	public boolean verificationTypeVolaille(int max, int nbrVerif) {
		if (nbrVerif > max) {
			return false;
		}
		return true;
	}

	// FONCTION VERIFICATION TABLEAU
	public boolean verificationTableau(Volaille[] tab) {
		int casepleine = 0;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] != null) {
				casepleine++;
			}
		}
		if (casepleine == 7) {
			return false;
		} else {
			return true;
		}
	}

	// Modifier le poids d'abattage
	public void modifierPoids() {

		int choixType = 0;

		double temp = 0;

		System.out.println();
		System.out.println("Vous voulez changer le poids d'abattage de quel espece ? ");
		System.out.println("(1) - Poulet");
		System.out.println("(2) - Canard");
		System.out.println("(3) - Quitter");
		System.out.println();
		System.out.print("Votre choix : ");
		choixType = in.nextInt();

		System.out.println();
		while (choixType != 3) {
			if (choixType == 1) {

				System.out.print("Quel est le nouveau poids d'abattage d'un poulet ? : ");
				temp = in.nextDouble();

				while (temp <= 0) {
					System.out.print("Veuillez entrez un poids correct (sup�rieur � 0) : ");
					temp = in.nextDouble();
				}

				setAbattagePoulet(temp);

				modifierPoids();
				break;

			} else if (choixType == 2) {

				System.out.print("Quel est le nouveau poids d'abattage d'un canard ? : ");
				temp = in.nextDouble();

				while (temp <= 0) {
					System.out.print("Veuillez entrez un poids correct (sup�rieur � 0) : ");
					temp = in.nextDouble();
				}

				setAbattageCanard(temp);
				modifierPoids();
				break;

			} else {

				System.out.println("Veuillez faire un choix correct ! ");
				modifierPoids();
				break;

			}
		}

		// Retour en arri�re

	}

	// Modifier le prix du jour
	public void modifierPrixJour() {

		int choixType = 0;

		double temp = 0;

		System.out.println();
		System.out.println("Vous voulez changer le prix du jour de quel esp�ce ? ");
		System.out.println("(1) - Poulet");
		System.out.println("(2) - Canard");
		System.out.println("(3) - Quitter");
		System.out.println();
		System.out.print("Votre choix : ");
		choixType = in.nextInt();

		System.out.println();
		while (choixType != 3) {
			if (choixType == 1) {

				System.out.print("Quel est le nouveau prix du jour d'un poulet ? : ");
				temp = in.nextDouble();

				while (temp <= 0) {
					System.out.print("Veuillez entrez un prix correct (sup�rieur � 0) : ");
					temp = in.nextDouble();
				}

				setPrixPoulet(temp);
				modifierPrixJour();
				break;

			} else if (choixType == 2) {

				System.out.print("Quel est le nouveau prix du jour d'un canard ? : ");
				temp = in.nextDouble();

				while (temp <= 0) {
					System.out.print("Veuillez entrez un prix correct (sup�rieur � 0) : ");
					temp = in.nextDouble();
				}

				setPrixCanard(temp);
				modifierPrixJour();
				break;

			} else {

				System.out.println("Veuillez faire un choix correct ! ");
				modifierPrixJour();
				break;

			}
		}

		// Retour en arri�re

	}

	// Modifier le poids d'une volaille
	public void modifierPoidsVolaille() {

		String verifNumId;

		double temp = 0;

		System.out.print("Quel est son num�ro d'identification ? : ");
		verifNumId = in.nextLine();

		boolean find = false;

		for (int i = 0; i < listeVolaille.length; i++) {

			if (listeVolaille[i] != null && listeVolaille[i].getNumId().equals(verifNumId)) {

				System.out.print("Volaille trouver ! Donnez son nouveau poids : ");
				temp = in.nextDouble();

				while (temp <= 0) {
					System.out.print("Veuillez entrez un poids correct : ");
					temp = in.nextDouble();
				}

				listeVolaille[i].setPoids(temp);
				find = true;
				break;

			}
		}

		if (find == false) {
			System.out.println("Volaille introuvable ! Essayer � nouveau !");
			modifierPoidsVolaille();
		}

	}

	// Afficher le nombre de volailles par type
	public void afficherVolailleType() {
		System.out.println("Nombre de Poulet : " + nbrPoulet);
		System.out.println("Nombre de Canard : " + nbrCanard);
		System.out.println("Nombre de Paon : " + nbrPaon);

	}

	// Afficher le total de prix des volailles abattables
	public void afficherTotalPrix() {
		double compteurPoulet = 0;
		double compteurCanard = 0;

		for (int i = 0; i < listeVolaille.length; i++) {
			if (listeVolaille[i] instanceof Poulet) {
				if (listeVolaille[i].getPoids() >= abattagePoulet) {
					compteurPoulet = compteurPoulet + (listeVolaille[i].getPoids() * prixPoulet);
				}

			}
			if (listeVolaille[i] instanceof Canard) {
				if (listeVolaille[i].getPoids() >= abattageCanard) {
					compteurCanard = compteurCanard + (listeVolaille[i].getPoids() * prixCanard);
				}

			}

		}
		System.out.println("Prix total des Volaille abattable : " + (compteurCanard + compteurPoulet));
	}

	// Vendre une volaille
	public void vendreVolaille() {
		System.out.println("Quel type de volaille voulez vous vendre ? ");
		System.out.println(" 1 : Poulet ");
		System.out.println(" 2 : Canard");
		System.out.println("Quel type de volaille voulez vous vendre ? ");
		String choix = in.nextLine();
		System.out.println("Liste des animaux que vous pouvez vendre :  ");
		if(choix.equalsIgnoreCase("Poulet") || choix.equals("1")) {
			for(int i =0; i<listeVolaille.length;i++) {
				if(listeVolaille[i] instanceof Poulet) {
					listeVolaille[i].affichageVolaille();
				}}
			
		}
		else if(choix.equalsIgnoreCase("Canard") || choix.equals("2")) {
			for(int i =0; i<listeVolaille.length;i++) {
				if(listeVolaille[i] instanceof Canard) {
					listeVolaille[i].affichageVolaille();
				}}
		}
		else {
			System.out.println("Desol� nous n'avons pas compris votre demande... ");
			menu();
		}
		System.out.println("Tapper l'id de la bete � vendre :  ");
		choix = in.nextLine();
		for (int i = 0; i < listeVolaille.length; i++) {
			if (choix.equals(listeVolaille[i].getNumId()) && listeVolaille[i] instanceof Poulet) {

				porte_monnaie = (int) (porte_monnaie + listeVolaille[i].getPoids() * prixPoulet);

				System.out.println("Fond : " + porte_monnaie);
				listeVolaille[i] = null;
				System.out.println("VENDU ! ");
				nbrPoulet --;
				break;
			} else if (choix.equals(listeVolaille[i].getNumId()) && listeVolaille[i] instanceof Canard) {
				porte_monnaie = (int) (porte_monnaie + listeVolaille[i].getPoids() * prixCanard);
				System.out.println("Fond : " + porte_monnaie);
				listeVolaille[i] = null;
				System.out.println("VENDU ! ");
				nbrCanard --;
				break;
			} else {
				System.out.println("D�sol� nous n'avons pas compris votre demande... ");
			}
		}

	}

	// Rendre un paon au parc
	public void rendrePaon() {
		for(int i =0; i<listeVolaille.length;i++) {
			if(listeVolaille[i] instanceof Paon) {
				listeVolaille[i].affichageVolaille();
				
			}}
		System.out.println("Tapper l'id du paon � rendre :  ");
		String choix = in.nextLine();
		for(int i = 0; i<listeVolaille.length;i++) {
			if(choix.equals(listeVolaille[i].getNumId()) ) {
				listeVolaille[i] =null;
				nbrPaon --;
				System.out.println("Paon Rendu au parc merci pour votre don !");
				break;
			}
		}

	}

	public void affichage() {
		for (int i = 0; i < listeVolaille.length; i++) {
			if (listeVolaille[i] != null) {
				listeVolaille[i].affichageVolaille();
			}
		}
	}

	
}