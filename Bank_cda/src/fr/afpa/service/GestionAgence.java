package fr.afpa.service;

import java.util.ArrayList;

import fr.afpa.beans.Adresse;
import fr.afpa.beans.Agence;
import fr.afpa.util.Util;

public class GestionAgence {
	private ArrayList<Object> listAgence;
	
	
	public boolean creationAgence(Agence agence) {

		if (agence != null) {

			if (agence instanceof Agence) {

				listAgence = Util.lireFichier(Util.PATH_AGENCE);

				Agence.setNumeroAgence(100 + listAgence.size());

				agence.setCodeAgence();
				listAgence.add(agence);

				Util.enregistrerFile(Util.PATH_AGENCE, listAgence);

				return true;

			} 
		}

		return false;
	}
	
	
	public Agence rechercherAgence(String codeagence) {
		
		listAgence = Util.lireFichier(Util.PATH_AGENCE);
		
		
		for (Object object : listAgence) {
			
			if(object instanceof Agence && ((Agence) object).getCodeAgence().equals(codeagence)) {
				return (Agence) object;
			}
		}
		
		return null;
		
		
	}
	
	
	public void modifierAgence(Agence agence) {
		listAgence = Util.lireFichier(Util.PATH_AGENCE);

		int index = listAgence.indexOf(agence);
		listAgence.remove(index);
		listAgence.add(index, agence);
		Util.enregistrerFile(Util.PATH_AGENCE, listAgence);
		
	}
	
	public void etatAgence(Agence agence, boolean etat) {
		if (agence != null) {
			
			listAgence = Util.lireFichier(Util.PATH_AGENCE);
			
			agence.setActive(etat);
			int index = listAgence.indexOf(agence);
			listAgence.remove(index);
			listAgence.add(index, agence);
			Util.enregistrerFile(Util.PATH_AGENCE, listAgence);
			
		}
	}
	
	
	public ArrayList<Agence> getListAgences() {

		listAgence = Util.lireFichier(Util.PATH_AGENCE);
		ArrayList<Agence> listDataAgence = new ArrayList<Agence>();

		for (Object object : listAgence) {

			listDataAgence.add((Agence) object);

		}

		return listDataAgence;

	}
	
	
}
