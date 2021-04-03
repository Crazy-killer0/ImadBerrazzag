package fr.afpa.beans;

import java.io.Serializable;

public class CompteCourant extends Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539916557911811592L;
	private boolean Decouvert;

	public CompteCourant(Agence agence) {
		super(agence);
	}

	public CompteCourant(Agence agence, boolean decouvert) {
		super(agence);
		Decouvert = decouvert;
	}

	public boolean isDecouvert() {
		return Decouvert;
	}

	public void setDecouvert(boolean decouvert) {
		this.Decouvert = decouvert;
	}

	

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "decouvert : "+ Decouvert;
	}

	@Override
	public String getType() {

		return "Compte Courant";
	}

}
