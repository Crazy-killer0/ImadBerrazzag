package fr.afpa.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Conseiller extends Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3635856592024178011L;

	private static int codeBase = 1000;
	private Agence agence;
	private ArrayList<Client> listClient;

	public Conseiller() {
		super();
	}

	public Conseiller(String nom, String prenom, String email, Agence agence) {

		super(nom, prenom, email);
		this.agence = agence;
		super.setLogin(this.generLogin());
		super.setMotDePasse(super.generMdp());
	

	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}


	@Override
	public String generLogin() {
		return "CO" + (++codeBase);
	}

	@Override
	public String toString() {
		return super.toString() + "Conseiller [agence=" + agence + "]";
	}

	public static int getCodeBase() {
		return codeBase;
	}

	public static void setCodeBase(int codeBase) {
		Conseiller.codeBase = codeBase;
	}

	@Override
	public boolean equals(Object arg0) {

		Conseiller cons = (Conseiller) arg0;

		if (this.getLogin().equals(cons.getLogin())) {

			return true;
		}
		return false;
	}

	@Override
	public String generIdClient() {
		// TODO Auto-generated method stub
		return null;
	}

}
