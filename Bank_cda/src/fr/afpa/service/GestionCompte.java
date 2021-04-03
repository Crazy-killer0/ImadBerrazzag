package fr.afpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.afpa.beans.Client;

//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import fr.afpa.beans.Compte;
import fr.afpa.beans.CompteCourant;
import fr.afpa.beans.EpargneInterface;
import fr.afpa.beans.Operation;
import fr.afpa.util.Util;

public class GestionCompte {

	private ArrayList<Object> listClient;
	private ArrayList<Object> listCompte;
	
	
	//FONCTION QUI CREER UN CLIENT
	/**
	 * 
	 * @param compte
	 * @param client
	 * @return
	 */
	public boolean creationCompte(Compte compte, Client client) {

		if (compte != null) {

			listClient = Util.lireFichier(Util.PATH_CLIENT);
			int index = listClient.indexOf(client);
			client = (Client) listClient.get(index);

			if (client.getListeCompte().size() < 3) {

				client.getListeCompte().add(compte);

				listClient.remove(index);

				listClient.add(index, client);

				Util.enregistrerFile(Util.PATH_CLIENT, listClient);

				return true;
			}

		}

		return false;

	}
	
	//FONCTION QUI RECHERCHE UN COMPTE SELON LE NUMERO DE COMPTE
	/**
	 * 
	 * @param numCompte
	 * @return
	 */
	public Compte rechercheCompte(String numCompte) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {
			Client client = (Client) object;

			for (Compte compte : client.getListeCompte()) {
				
				if (compte.getNumeroCompte().equals(numCompte)) {
					
					return compte;
				}
			}

		}

		return null;
	}
	//RECHERCHE CLIENT PAR COMPTE 
	/**
	 * 
	 * @param numCompte
	 * @return
	 */
	public Client rechercheClientParCompte(String numCompte) {
		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {
			Client client = (Client) object;

			for (Compte compte : client.getListeCompte()) {
				
				if (compte.getNumeroCompte().equals(numCompte)) {
					
					return client;
				}

			}
		}
		return null;
		
	}
	
	//FONCTION QUI CHANGE L'ETAT DU DECOUVERT D'UN COMPTE
	/**
	 * 
	 * @param compte
	 * @param status
	 * @param client
	 * @return
	 */
	public boolean decouvertAutoriser(Compte compte, boolean status, Client client) {
		if (compte instanceof CompteCourant) {

			if (client != null) {

				listClient = Util.lireFichier(Util.PATH_CLIENT);
				int index = listClient.indexOf(client);
				client = (Client) listClient.get(index);

				if (compte != null) {

					if (client.getListeCompte().contains(compte)) {

						int indexCompte = client.getListeCompte().indexOf(compte);

						client.getListeCompte().remove(indexCompte);

						((CompteCourant) compte).setDecouvert(status);

						client.getListeCompte().add(indexCompte, compte);

						listClient.remove(index);

						listClient.add(index, client);

						Util.enregistrerFile(Util.PATH_CLIENT, listClient);
					}
				}

			}
		}
		return false;
	}

	//FONCTION QUI AJOUTE OU RETIRE UN MONTANT D'UN COMPTE 
	/**
	 * 
	 * @param montant
	 * @param compte
	 * @param client
	 * @return
	 */
	public boolean Operation(double montant, Compte compte, Client client) {

		if (compte != null) {
			if (compte.isActive()==true) {
				
			
			listClient = Util.lireFichier(Util.PATH_CLIENT);

			int index = listClient.indexOf(client);
			client = (Client) listClient.get(index);

			int indexCompte = client.getListeCompte().indexOf(compte);

			compte.getListOperations().add(new Operation(montant));
			compte.setSolde(compte.getSolde() + montant);

			if (compte.getSolde() < 0
					&& (!(compte instanceof CompteCourant) || !((CompteCourant) compte).isDecouvert())) {

				return false;

			} else {

				client.getListeCompte().remove(indexCompte);

				client.getListeCompte().add(indexCompte, compte);

				listClient.remove(index);
				listClient.add(index, client);

				Util.enregistrerFile(Util.PATH_CLIENT, listClient);
				return true;

			}

		}

		return false;
		}
		return false;
	}
	
	//FONCTION QUI EFFECTUE UN VIREMENT ENTRE DEUX COMPTE D'UN SOLDE DEMANDE
	/**
	 * 
	 * @param compteEmet
	 * @param compteBenef
	 * @param montant
	 * @param clientEmet
	 * @param clientRecev
	 */
	public void virement(Compte compteEmet, Compte compteBenef, double montant, Client clientEmet, Client clientRecev) {
		if (montant>0) {
			
		
			listClient = Util.lireFichier(Util.PATH_CLIENT);
	
			int indexBenef = listClient.indexOf(clientRecev);
			clientRecev = (Client) listClient.get(indexBenef);
	
			int indexEmet = listClient.indexOf(clientEmet);
			clientEmet = (Client) listClient.get(indexEmet);
	
			int indexCompteRecev = clientRecev.getListeCompte().indexOf(compteBenef);
	
			int indexCompteEmet = clientEmet.getListeCompte().indexOf(compteEmet);
	
			if (compteBenef != null && compteEmet != null) {
				if (compteBenef.isActive()==true && compteEmet.isActive()==true) {
					
				
				
//				Operation operation = new Operation(-1 * montant);
	
				compteEmet.getListOperations().add(new Operation(-1 * montant));
				System.out.println(compteEmet.getSolde());
				compteEmet.setSolde(compteEmet.getSolde() + (montant * -1));
				System.out.println(compteEmet.getSolde());
				
	
//				operation.setMontant(montant);
				compteBenef.getListOperations().add(new Operation(montant));
				System.out.println(compteBenef.getSolde());
				compteBenef.setSolde(compteBenef.getSolde() + montant);
				System.out.println(compteBenef.getSolde());
	
				clientRecev.getListeCompte().remove(indexCompteRecev);
				
				clientRecev.getListeCompte().add(indexCompteRecev, compteBenef);
				
				clientEmet.getListeCompte().remove(indexCompteEmet);
	
				clientEmet.getListeCompte().add(indexCompteEmet, compteEmet);
	
				listClient.remove(indexEmet);
				listClient.add(indexEmet, clientEmet);
				
				
				listClient.remove(indexBenef);
				listClient.add(indexBenef, clientRecev);
				Util.enregistrerFile(Util.PATH_CLIENT, listClient);
				notifMailOperation(compteEmet,compteBenef,new Operation(-1 * montant),clientEmet,clientRecev);
				}
	
			}
		}
	}

	//FONCTION QUI ENVOIE UN MAIL APRES AVOIR EFECTUE UNE OPERATION
	/**
	 * 
	 * @param comptEmet
	 * @param comptbenef
	 * @param operation
	 */
	public void notifMailOperation(Compte comptEmet,Compte comptbenef,Operation operation,Client clientEmet, Client clientBenef) {
		String username = "cda20108afpa@gmail.com";
		String password = "Cda20108.";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clientEmet.getEmail()));
			message.setSubject("Nouvelle operation sur votre compte de type "+ comptEmet.getType());
			message.setText("Tres cher " + clientEmet.getNom() + " " + clientEmet.getPrenom()
					+ ", une nouvelle operation a  ete detecte  sur votre compte : "+comptEmet.getNumeroCompte()+"\n" +" Virement a " + clientBenef.getPrenom()+
						", pour un montant de "	+ operation.getMontant() 
					+"\n"+ " CDA BANK A VOTRE SERVICE");
			
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//FONCTION QUI RETOURNE LES OPERATIONS SUR 90 JOURS
	/**
	 * 
	 * @param compte
	 * @return
	 */
	public ArrayList<Operation> lastOperations(Compte compte) {

		ArrayList<Operation> listOperations = new ArrayList<Operation>();

		if (compte != null) {

			for (Operation operation : compte.getListOperations()) {

				if ((operation.getDateFinOperation().isBefore(LocalDateTime.now()))
						&& operation.getDateFinOperation().isAfter(LocalDateTime.now().minusDays(-90))) {

					listOperations.add(operation);

				}
			}

		}

		return listOperations;

	}
	//FONCTION QUI DECLANCHE DES FRAIS SELON LE COMPTE
	/**
	 * 
	 */
	public void declancherFrais() {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {

			if (object instanceof EpargneInterface) {

				Operation(((EpargneInterface) object).calculFrais() * -1, (Compte) object, (Client) object);

			} else {

				Operation(Compte.getFRAIS_DE_TENUE(), (Compte) object, (Client) object);
			}

		}

	}
	
	//FONCTION QUI RETOUNRE LES COMPTES D'UN CLIENT 
	/**
	 * 
	 * @return
	 */
	public ArrayList<Compte> getListCompte() {

		listClient = Util.lireFichier(Util.PATH_CLIENT);
		ArrayList<Compte> listDataCompte = new ArrayList<Compte>();

		for (Object object : listClient) {

			Client client = (Client) object;

			for (Compte compte : client.getListeCompte()) {

				listDataCompte.add(compte);

			}

		}

		return listDataCompte;

	}

}