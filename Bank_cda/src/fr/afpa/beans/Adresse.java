package fr.afpa.beans;

import java.io.Serializable;

public class Adresse implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1947906389615878060L;
	
	private String NumeroRue;
	private String nomRue;
	private String CodePopstal;
	private String Ville;
	private String pays;
	
	
	
	public Adresse() {
		super();
	}
	
	public Adresse(String numeroRue,String nomRue, String codePopstal, String ville, String pays) {
		super();
		this.NumeroRue = numeroRue;
		this.nomRue = nomRue;
		this.CodePopstal = codePopstal;
		this.Ville = ville;
		this.pays = pays;
	}
	
	public String getNumeroRue() {
		return NumeroRue;
	}
	public void setNumeroRue(String numeroRue) {
		this.NumeroRue = numeroRue;
	}
	public String getCodePopstal() {
		return CodePopstal;
	}
	public void setCodePopstal(String codePopstal) {
		this.CodePopstal = codePopstal;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		this.Ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	@Override
	public String toString() {
		return "Adresse [NumeroRue=" + NumeroRue + ", nomRue=" + nomRue + ", CodePopstal=" + CodePopstal + ", Ville="
				+ Ville + ", pays=" + pays + "]";
	}

	
	
	
	
	

}
