package fr.afpa.beans;

import java.io.Serializable;
import java.util.ArrayList;

import fr.afpa.util.Util;

public abstract class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255660872880543459L;

    private static final double FRAIS_DE_TENUE = 25;

	private Agence agence;
	private String numeroCompte;
	private double solde;
	private boolean isActive;
	private ArrayList<Operation> listOperations;

	public Compte(Agence agence) {

		this.agence = agence;
		this.solde = 10;
		this.setActive(true);
		this.numeroCompte = "" + Util.random(100000, 999999) + "" + Util.random(10000, 99999);
		listOperations = new ArrayList<Operation>();

	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static double getFRAIS_DE_TENUE() {
		return FRAIS_DE_TENUE;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public ArrayList<Operation> getListOperations() {
		return listOperations;
	}

	public void setListOperations(ArrayList<Operation> listOperations) {
		this.listOperations = listOperations;
	}
	

	public abstract String getType();

	@Override
	public boolean equals(Object arg0) {
		Compte compte = (Compte) arg0;

		if (this.getNumeroCompte().equals(compte.numeroCompte)) {

			return true;
		}
		return false;

	}

	
	
	@Override
	public String toString() {
		return getType() + " [ numeroCompte=" + numeroCompte + ", solde=" + solde + ", isActive=" + isActive
				+ ", CodeAgence= " + agence.getNumeroAgence() + " ]";
	}
	

}
