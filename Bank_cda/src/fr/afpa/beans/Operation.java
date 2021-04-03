package fr.afpa.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import fr.afpa.util.Util;

public class Operation implements Serializable{
	private LocalDateTime dateOperation;
	private String numeroOperation;
	private double montant;
	private LocalDateTime dateFinOperation;
	
	
	public Operation(double montant) {
		this.dateOperation = LocalDateTime.now();
		this.numeroOperation = ""+ Util.random(10000,99999)+""+ Util.random(10000,99999);
		this.montant = montant;
		this.dateFinOperation = dateOperation.plusDays(2);
	}
	
	public LocalDateTime getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(LocalDateTime dateOperation) {
		this.dateOperation = dateOperation;
	}
	public String getNumeroOperation() {
		return numeroOperation;
	}
	public void setNumeroOperation(String numeroOperation) {
		this.numeroOperation = numeroOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public LocalDateTime getDateFinOperation() {
		return dateFinOperation;
	}
	public void setDateFinOperation(LocalDateTime dateFinOperation) {
		this.dateFinOperation = dateFinOperation;
	}

	@Override
	public String toString() {
		return "Operation [dateOperation=" + dateOperation + ", numeroOperation=" + numeroOperation + ", montant="
				+ montant + ", dateFinOperation=" + dateFinOperation + "]";
	}
	
	
}
