package fr.afpa.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerSaisie {

	public static boolean isNumeric(TextField str, Label errLabel, String err) {

		boolean isNum = false;

		for (Character c : str.getText().toCharArray()) {

			if (Character.isDigit(c)) {
				isNum = true;
			} else {
				errLabel.setText(err);
				return false;
			}
		}

		return isNum;

	}

	public static boolean mailControl(TextField mail, Label errLabel, String err) {
		if (!(mail.getText().contains("@")) || !(mail.getText().contains("."))) {
			errLabel.setText(err);
			return false;
		}
		return true;
	}

	public static boolean MdpControl(TextField mdp, Label errLabel, String err) {
		if (mdp.getText().length() < 10 && mdp.getText().length() > 0) {
			return true;
		}
		errLabel.setText(err);
		return false;
	}

	public static boolean saisieLettres(TextField chaine, Label errLabel, String err) {
		boolean valeur = true;
		char[] tab = chaine.getText().toCharArray();

		for (char carac : tab) {
			if (Character.isDigit(carac)) {
				errLabel.setText(err);
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAlpha(TextField textField, Label errLabel, String err) {
		
		String str = textField.getText();
		
		for (Character c : str.toCharArray()) {
			if(Character.isDigit(c)) {
				errLabel.setText(err);
				return false;
			}
		}
		
		
		return true;
		
	}

	public static boolean isInt(String chaine) {
		boolean valeur = true;
		char[] tab = chaine.toCharArray();

		for (char carac : tab) {
			if (!Character.isDigit(carac) && valeur) {
				valeur = false;
			}
		}

		return valeur;
	}

	public static boolean verifLoginConseiller(String login) {
		boolean isConseiller = true;

		String partiEniter = login.substring(2);
		if (login.startsWith("CO") && login.length() == 6 && isInt(partiEniter)) {
			System.out.println(isConseiller);
			return isConseiller;
		} else {
			isConseiller = false;
			System.out.println(isConseiller);
			return isConseiller;
		}
	}

	public static boolean verifLoginAdmin(String login) {
		boolean isAdmin = true;

		String partiEniter = login.substring(3);
		if (login.startsWith("ADM") && login.length() == 5 && isInt(partiEniter)) {
			System.out.println(isAdmin);
			return isAdmin;
		} else {
			isAdmin = false;
			System.out.println(isAdmin);
			return isAdmin;
		}
	}

	public static boolean textFieldEmpty(TextField textField, Label errLabel, String erreur) {

		if (textField.getText().isEmpty()) {

			errLabel.setText(erreur);
			return true;
			
		} else {

			errLabel.setText("");
			return false;
		}

	}
	
	

}
