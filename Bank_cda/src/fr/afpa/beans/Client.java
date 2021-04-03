package fr.afpa.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


import fr.afpa.util.Util;

public class Client  extends Utilisateur implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7671762815319917130L;
	
	private String idClient;
	private LocalDate DateNaissance;
	private ArrayList<Compte> listeCompte;
	private Adresse adresse;
	private Agence agence;
	private Conseiller conseiller;
	
	
	public Client(String nom, String prenom, String email, LocalDate dateNaissance, Adresse adresse, Agence agence, Conseiller conseiller) {
		
		super(nom, prenom, email);
		this.setIdClient((generIdClient()));
		this.DateNaissance = dateNaissance;
		this.adresse = adresse;
		this.setAgence(agence);
		super.setLogin(this.generLogin());
		super.setMotDePasse(super.generMdp());
		this.conseiller = conseiller;
		this.listeCompte = new ArrayList<Compte>();
	}
	
	public Client() {
		super();
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArrayList<Compte> getListeCompte() {
		return listeCompte;
	}

	public void setListeCompte(ArrayList<Compte> listeCompte) {
		this.listeCompte = listeCompte;
	}

	public LocalDate getDateNaissance() {
		return DateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.DateNaissance = dateNaissance;
	}
	
	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	
	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	@Override
	public String toString() {
		return "Client [ID client=" +idClient+ " Login= "+super.getLogin() + ", MDP=" + super.getMotDePasse()+ ", Nom=" + super.getNom()+ ", Prenom=" + super.getPrenom()+ ", DateNaissance=" + 
				DateNaissance + ", listeCompte=" + listeCompte +  ", Email=" + super.getEmail() +  ", adresse=" + adresse + ", agence=" + agence + "]";
	}

	@Override
	public String generLogin() {
		
		
		String loginClient = "" + Util.random(10000, 99999) + ""+ Util.random(10000, 99999);
		
		return loginClient;
		
	}
	
	
	@Override
	public String generIdClient() {
			
		String loginClient = "CB" + Util.random(10000, 99999) + ""+ Util.random(0, 9);	
		return loginClient;
		
	}

	@Override
	public boolean equals(Object obj) {
	
		Client client = (Client)obj;
		if(this.getNom().equals(client.getNom())) {
			
			return true;
		}
		return false ;
	}

	


	
	
}

