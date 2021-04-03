package fr.afpa.beans;

public class Canard extends Volaille {

	public Canard(double poids, String numId, int age) {
		super(poids, numId, age);
	}

	// Calcul du prix d'un canard (prix par kilo)
	@Override
	public double calculPrixKilo() {
		return 0;
	}

	public void affichageVolaille() {
		System.out.println("- CANARD -");
		System.out.println("ID : " + getNumId());
		System.out.println("Poids : " + getPoids());
		System.out.println("Age : " + getAge());
	}
}
