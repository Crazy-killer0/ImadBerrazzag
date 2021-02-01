package fr.afpa.beans;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Client client;
	//private float facture;
	private Chambre chambre;
	private CategorieChambre catChambre;
	
	
	public Reservation(LocalDate deb , LocalDate fin, Client cl){
	
		dateDebut = deb;
		dateFin = fin;
		client = cl;	
	}
	
	public Reservation(LocalDate dateD, LocalDate dateF, Client cl, Chambre ch,
			CategorieChambre catCh) {
		
		dateDebut = dateD;
		dateFin = dateF;
		client = cl;
		chambre = ch;
		catChambre = catCh;
	}

	public LocalDate getDateDebut(){
		return dateDebut;
	}
	
	public void setDateDebut(LocalDate dd) {
		dateFin = dd;
	}
	
	
	public LocalDate getDateFin(){
		return dateFin;
	}
	
	public void setDateFin(LocalDate df) {
		dateFin = df;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client cl) {
		client = cl;
	}
	
//	public float getFacture() {
//		return facture;
//	}
//
//	public void setFacture(float facture) {
//		this.facture = facture;
//	}
	
	public void afficherReservation() {
		
		System.out.println(chambre.getNumero() + " " + client.getIdClient() + " " + client.getNom() + " du " 
				+ dateDebut + " au " + dateFin + " pour un montant de " + catChambre.getTarif() * (ChronoUnit.DAYS.between(dateDebut, dateFin)+1) );
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public CategorieChambre getCatChambre() {
		return catChambre;
	}

	public void setCatChambre(CategorieChambre catChambre) {
		this.catChambre = catChambre;
	}

	
	
}
