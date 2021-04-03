package fr.afpa.beans;

public class Poulet extends Volaille {

	public Poulet(double poids, String numId, int age) {
		super(poids, numId, age);
	}

	// Calcul du prix d'un poulet (prix par kilo)
	@Override
	public double calculPrixKilo() {
		return 0;
	}

	public void affichageVolaille() {
		System.out.println(" - POULET -");
		System.out.println("ID : " + getNumId());
		System.out.println("Poids : " + getPoids());
		System.out.println("Age : " + getAge());
	}

}
