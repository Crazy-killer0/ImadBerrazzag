package fr.afpa.beans;

import java.io.Serializable;

public class PELImp extends Compte implements EpargneInterface , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970127107899327858L;
	
	final double TAUX = 2.5;
	
	public PELImp(Agence agence) {
		super(agence);
	
	}

	@Override
	public double calculFrais() {
		
		return super.getFRAIS_DE_TENUE()+ (getSolde()*TAUX/100) ;
	}

	@Override
	public String getType() {
		return "PEL";
	}

	
	
}
