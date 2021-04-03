package fr.afpa.beans;

public class Admin extends Utilisateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4164789368793968236L;
	private String MdpDoubleAuth;
	

	public Admin( String nom, String prenom, String email) {
		super(  nom, prenom, email);
		super.setMotDePasse(super.generMdp());
		setLogin(generLogin());
		
	}

	public Admin() {
	
	}

	public String getMdpDoubleAuth() {
		return MdpDoubleAuth;
	}

	public void setMdpDoubleAuth(String mdpDoubleAuth) {
		MdpDoubleAuth = mdpDoubleAuth;
	}



	@Override
	public String generLogin() {
		String login = "ADM";
		for (int i =0; i <2; i++) {
			String code = Integer.toString((int) (Math.random()*9)+0);
			login = login + code;
			}
		
	
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public String toString() {
	
		return super.toString() + " code double : " + MdpDoubleAuth;
	}

	@Override
	public String generIdClient() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
