package fr.afpa.beans;

import java.io.Serializable;

public abstract class Utilisateur implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 698183096044016967L;

	private String Nom;
	private String Prenom;
	private String Email;
	private String MotDePasse;
	private String Login;
	private boolean isActive;

	public Utilisateur() {
	}

	public Utilisateur(String nom, String prenom, String email) {
		super();

		this.isActive = true;
		Nom = nom;
		Prenom = prenom;
		Email = email;
		MotDePasse = generMdp();
	}

	public String getMotDePasse() {
		return MotDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		MotDePasse = motDePasse;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public abstract String generLogin();
	public abstract String generIdClient();

	public String generMdp() {
		String chara = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!%$";
		StringBuffer pass = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			int r = (int) Math.floor(Math.random() * (chara.length() - 1));
			pass.append(chara.charAt(r));
		}
		String mdp = pass.toString();
		return mdp;
	}

	@Override
	public String toString() {
		return " [Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + ", MotDePasse=" + MotDePasse
				+ ", Login=" + Login + ", isActive=" + isActive + "]";
	}



}
