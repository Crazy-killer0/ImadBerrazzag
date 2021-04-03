package fr.afpa.beans;

public class LivretA extends Compte implements EpargneInterface {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3942302087845489188L;
	
	final double TAUX = 10;
	
	public LivretA(Agence agence) {
		super(agence);
	
	}

	@Override
	public double calculFrais() {
		return super.getFRAIS_DE_TENUE() + (getSolde()*TAUX/100) ;
		
	}



	@Override
	public String getType() {
	
		return "Livre A";
	}

}
