package fr.afpa.beans;

public class Chambre {
	private static int nbChambre = 1;
	public static int nbReservee;
	public static int nbOcuppee;
	private int numero;
	private Reservation[] listeReservation;
	private String etat;

	public Chambre(Reservation[] listeR, String unEtat ) {
		numero = nbChambre;
		listeReservation = listeR;
		etat = unEtat;	
		nbChambre++;
	}
	
	public Chambre() {
		setNbChambre(1);
		listeReservation = new Reservation[3];
		etat = "libre";
	}

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int num) {
		numero = num;
	}

	public Reservation[] getListeReservation() {
		return listeReservation;
	}

	public void setListeReservation(Reservation[] listeR) {
		listeReservation = listeR;
	}

	public int getNbChambre() {
		return nbChambre;
	}

	public void setNbChambre(int nbc) {
		nbChambre = nbc;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public void afficherChambre() {
		System.out.println(" Chambre n° " + numero );
		for (int i = 0; i < listeReservation.length; i++) {
			if(listeReservation[i] != null ) {
				listeReservation[i].afficherReservation();
			}else {
				System.out.println("[espace vide]");
			}
		}
	}
}
