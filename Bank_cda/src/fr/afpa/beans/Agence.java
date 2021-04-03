package fr.afpa.beans;

import java.io.Serializable;

public class Agence implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6837290812187948748L;
	
	private String nom;
	private String codeAgence;
	private Adresse adresse;
	private static int numeroAgence = 100;
	private boolean isActive;
	
	
	
	public Agence(String nom, Adresse adresse) {
		this.nom = nom;
		this.adresse = adresse;
		this.codeAgence = ""+(++numeroAgence);
		this.isActive = true;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence() {
		this.codeAgence = ""+(++numeroAgence);
	}



	public static int getNumeroAgence() {
		return numeroAgence;
	}

	public static void setNumeroAgence(int numeroAgences) {
		numeroAgence = numeroAgences;
	}

	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

	@Override
	public String toString() {
		return "Agence [nom=" + nom + ", codeAgence=" + codeAgence + ", adresse=" + adresse + ", isActive=" + isActive
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		Agence agc = (Agence)obj;
		if(this.getCodeAgence().equals(agc.getCodeAgence())) {
			return true;
		}
		return false;
	}

	
	
	
}
