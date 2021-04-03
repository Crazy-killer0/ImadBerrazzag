package fr.afpa.beans;

import java.util.Scanner;

public abstract class Volaille {

	private double poids;
	private String numId;
	private int age;

	public Volaille(String numId) {

		this.setNumId(numId);

	}

	public Volaille(double poids, String numId, int age) {

		this.setPoids(poids);
		this.setNumId(numId);
		this.setAge(age);

	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Calcul du prix d'une volaille (prix par kilo)
	public abstract double calculPrixKilo();

	public abstract void affichageVolaille();

	public Volaille rendre() {
		// TODO Auto-generated method stub
		return null;
	}



}