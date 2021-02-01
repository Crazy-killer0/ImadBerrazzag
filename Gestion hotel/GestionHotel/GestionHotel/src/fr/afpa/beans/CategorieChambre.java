package fr.afpa.beans;

import fr.afpa.services.GestionChambre;

public class CategorieChambre {
	private static int nbCategorie = 1;
	private int id;
	private String nom;
	private String superficie;
	private String vue;
	private float tarif;
	private String[] options;
	private String occupation;
	private Chambre[] listeChambres;

	public CategorieChambre(String unNom, String uneSuperficie, String uneVue, float unTarif, String[] desOptions, String uneOccupation, Chambre[] listeCh) {
		id = nbCategorie;
		nom = unNom;
		superficie = uneSuperficie;
		vue = uneVue;
		tarif = unTarif;
		options = desOptions;
		occupation = uneOccupation;
		listeChambres = listeCh;
		nbCategorie++;
	}

	public CategorieChambre() {
		nom = new String("");
		superficie = new String("");
		vue = new String("");
		tarif = 0;
		options = new String[10];
		listeChambres = new Chambre[15];
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String uneOccupation) {
		this.occupation = uneOccupation;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String unNom) {
		nom = unNom;
	}

	public String getSuperficie() {
		return superficie;
	}

	public void setSuperficie(String uneSuperficie) {
		superficie = uneSuperficie;
	}

	public String getVue() {
		return vue;
	}

	public void setVue(String uneVue) {
		vue = uneVue;
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float unTarif) {
		tarif = unTarif;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] desOptions) {
		options = desOptions;
	}

	public Chambre[] getListeChambres() {
		return listeChambres;
	}

	public void setListeChambres(Chambre[] listeCh) {
		listeChambres = listeCh;
	}

	public void afficheCategorieChambre() {

		System.out.println("----------------------------------------------");
		System.out.println("Type n° " + id + " " + nom + " " + tarif + " euros \n" + occupation + "\n "
				+ "Superficie : " + superficie + "\n " + "Vue : " + vue + "\n " + "Liste options :");
		afficheOptions();

	}

	public void afficheOptions() {
		for (int i = 0; i < options.length; i++) {
			System.out.println("   " + options[i]);

		}
	}

	public void afficheCategorieChambreAvecChambre() {

		afficheCategorieChambre();
		for (int i = 0; i < listeChambres.length; i++) {
			if(listeChambres[i] != null) {
				listeChambres[i].afficherChambre();
			}
		}

	}

}
