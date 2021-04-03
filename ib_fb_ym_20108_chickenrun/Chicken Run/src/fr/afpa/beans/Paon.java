package fr.afpa.beans;

public class Paon extends Volaille implements RendrePaon {

	public Paon(String numId) {
		super(numId);
	}

	private String numId;

	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	@Override
	public double calculPrixKilo() {
		return 0;
	}

	public void affichageVolaille() {
		System.out.println(" - PAON - ID : " + getNumId());
	}

	@Override
	public Volaille rendreP() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
