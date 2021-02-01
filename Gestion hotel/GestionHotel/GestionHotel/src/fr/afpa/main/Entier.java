package fr.afpa.main;

public class Entier {
	public static boolean isInt(String chaine){
		boolean valeur = true;
		char[] tab = chaine.toCharArray();

		for(char carac : tab){
		if(!Character.isDigit(carac) && valeur){ valeur = false; }
		}

		return valeur;
		}
}
