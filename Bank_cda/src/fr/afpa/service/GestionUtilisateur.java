package fr.afpa.service;

import java.time.LocalDate;


import java.time.LocalDate;
import java.util.ArrayList;
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

import fr.afpa.beans.Admin;
import fr.afpa.beans.Agence;
import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.beans.Conseiller;
import fr.afpa.beans.Operation;
import fr.afpa.beans.Utilisateur;
import fr.afpa.util.Util;

public class GestionUtilisateur {

	private ArrayList<Object> listConseiller;
	private ArrayList<Object> listClient;
	private ArrayList<Object> listAdmin;

	/**
	 * Methode pour ajouter un utilisateur
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean creationUtilsateur(Utilisateur user) {

		if (user != null) {

			if (user instanceof Conseiller) {

				listConseiller = Util.lireFichier(Util.PATH_CONSEILLER);

				Conseiller.setCodeBase(1000 + listConseiller.size());
				user.setLogin(user.generLogin());

				listConseiller.add(user);

				Util.enregistrerFile(Util.PATH_CONSEILLER, listConseiller);

				return true;

			} else if (user instanceof Client) {

				listClient = Util.lireFichier(Util.PATH_CLIENT);
				user.setLogin(user.generLogin());


				listClient.add(user);

				Util.enregistrerFile(Util.PATH_CLIENT, listClient);

				return true;

			} else if (user instanceof Admin) {

				listAdmin = Util.lireFichier(Util.PATH_ADMIN);
				listAdmin.add(user);
				Util.enregistrerFile(Util.PATH_ADMIN, listAdmin);

				return true;

			}
		}

		return false;

	}

	/**
	 * 
	 * @param admin
	 */
	public void generDoubleaUTH(Admin admin) {
		String chara = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!%$";
		StringBuffer pass = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			int r = (int) Math.floor(Math.random() * (chara.length() - 1));
			pass.append(chara.charAt(r));
		}
		String temp = pass.toString();

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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(admin.getEmail()));
			message.setSubject("Mot de passse authentification");
			message.setText("Tres cher " + admin.getNom() + " " + admin.getPrenom()+"."
					+"\n"+ "Voici ci-joint votre mot de passe d'authentification :  " + temp
					+"\n"+ "CDA BANK A VOTRE SERVICE");
			admin.setMdpDoubleAuth(temp);
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode pour rechercher un conseiller � l'aide d'une adressse email
	 * 
	 * @param email : String l'adresse du conseiller
	 * @return Conseiller : l'objet trouver ou null si l'objet n'est trouver
	 */
	public Conseiller rechercherConseiller(String email) {

		listConseiller = Util.lireFichier(Util.PATH_CONSEILLER);

		for (Object object : listConseiller) {

			if (object instanceof Conseiller && ((Conseiller) object).getEmail().equals(email)) {
				return (Conseiller) object;
			}
		}

		return null;
	}

	/**
	 * Methode pour activer ou d�sactiver un conseiller
	 * 
	 * @param conseiller : le conseiller a activer ou d�sactiver
	 * @param status     : boolean qui donne le status pour l'activation ou la
	 *                   d�sactivation
	 */
	public void activerConseiller(Conseiller conseiller, boolean status) {

		if (conseiller != null) {

			listConseiller = Util.lireFichier(Util.PATH_CONSEILLER);
			conseiller.setActive(status);
			int index = listConseiller.indexOf(conseiller);
			listConseiller.remove(index);

			listConseiller.add(index, conseiller);

			Util.enregistrerFile(Util.PATH_CONSEILLER, listConseiller);

		}

	}

	/**
	 * 
	 * @param conseiller
	 */
	public void modifierConseiller(Conseiller conseiller) {

		listConseiller = Util.lireFichier(Util.PATH_CONSEILLER);


		int index = listConseiller.indexOf(conseiller);
		listConseiller.remove(index);

		listConseiller.add(index, conseiller);

		Util.enregistrerFile(Util.PATH_CONSEILLER, listConseiller);

	}


	/**
	 * 
	 * @param Login
	 * @return
	 */
	public Conseiller rechercherConseillerByLogin(String Login) {

		listConseiller = Util.lireFichier(Util.PATH_CONSEILLER);

		for (Object object : listConseiller) {

			if (object instanceof Conseiller && ((Conseiller) object).getLogin().equalsIgnoreCase(Login)) {
				return (Conseiller) object;
			}
		}

		return null;

	}

	/**
	 * 
	 * @param Login
	 * @return
	 */
	public Client rechercherClientByLogin(String Login) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {

			if (object instanceof Client && ((Client) object).getLogin().equalsIgnoreCase(Login)) {
				return (Client) object;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param Login
	 * @return
	 */
	public Admin rechercherAdminByLogin(String Login) {

		listAdmin = Util.lireFichier(Util.PATH_ADMIN);

		for (Object object : listAdmin) {

			if (object instanceof Admin && ((Admin) object).getLogin().equalsIgnoreCase(Login)) {
				return (Admin) object;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param nom
	 * @param email
	 * @param login
	 * @return
	 */
	public Client rechercherClient(String nom, String numCompte, String ID) {

		if (numCompte != null) {
			return rechercheCompte(numCompte);
		}

		else if (ID != null) {
			return rechercheClientId(ID);
		} else if (nom != null) {
			return rechercheClientNom(nom);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param nom
	 * @return
	 */
	public Client rechercheClientNom(String nom) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {

			if (object instanceof Client && ((Client) object).getNom().equals(nom)) {
				return (Client) object;
			}
		}
		return null;

	}
	
	public Client rechercheCompte(String numCompte) {

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
	/**
	 * 
	 * @param email
	 * @return
	 */
	public Client rechercheClientEmail(String email) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {

			if (object instanceof Client && ((Client) object).getEmail().equals(email)) {
				return (Client) object;
			}
		}
		return null;

	}

	/**
	 * 
	 * @param login
	 * @return
	 */
	public Client rechercheClientLogin(String login) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {

			if (object instanceof Client && ((Client) object).getLogin().equals(login)) {
				return (Client) object;
			}
		}
		return null;

	}

	/**
	 * 
	 * @param client
	 * @param status
	 */
	public void activerClient(Client client, boolean status) {

		if (client != null) {

			listClient = Util.lireFichier(Util.PATH_CLIENT);
			client.setActive(status);
			int index = listClient.indexOf(client);
			listClient.remove(index);

			listClient.add(index, client);

			Util.enregistrerFile(Util.PATH_CLIENT, listClient);

		}

	}
	
	public Client rechercheClientId(String ID) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		for (Object object : listClient) {

			if (object instanceof Client && ((Client) object).getIdClient().equals(ID)) {
				return (Client) object;
			}
		}
		return null;

	}

	/**
	 * 
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param dateDeNaissance
	 * @param agence
	 * @param mdp
	 * @param client
	 */
	public void modifierClient(String nom, String prenom, String email, LocalDate dateDeNaissance, Agence agence,
			String mdp, Client client) {

		listClient = Util.lireFichier(Util.PATH_CLIENT);

		client.setNom(nom);
		client.setEmail(email);
		client.setDateNaissance(dateDeNaissance);
		client.setPrenom(prenom);
		client.setAgence(agence);
		client.setMotDePasse(mdp);

		int index = listClient.indexOf(client);
		listClient.remove(index);

		listClient.add(index, client);

		Util.enregistrerFile(Util.PATH_CLIENT, listClient);

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Client> getListClient() {

		listClient = Util.lireFichier(Util.PATH_CLIENT);
		ArrayList<Client> listDataClient = new ArrayList<Client>();

		for (Object object : listClient) {

			listDataClient.add((Client) object);

		}

		return listDataClient;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Conseiller> getListConseiller() {

		listConseiller = Util.lireFichier(Util.PATH_CONSEILLER);
		ArrayList<Conseiller> listDataConseillers = new ArrayList<Conseiller>();

		for (Object object : listConseiller) {

			listDataConseillers.add((Conseiller) object);

		}

		return listDataConseillers;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Admin> getListAdmin() {

		listAdmin = Util.lireFichier(Util.PATH_ADMIN);
		ArrayList<Admin> listDataAdmin = new ArrayList<Admin>();

		for (Object object : listAdmin) {

			listDataAdmin.add((Admin) object);

		}

		return listDataAdmin;

	}
	
	public ArrayList<Client> listClientParConseiller(Conseiller conseiller) {
		
		listClient = Util.lireFichier(Util.PATH_CLIENT);
		
		ArrayList<Client> listeclient = new ArrayList<Client>() ;
		for (Object object : listClient) {
			
			if(object instanceof Client && ((Client) object).getConseiller().equals(conseiller)) {
				
				listeclient.add((Client) object);
				
				
				
			}
		}
		return listeclient;
		
	}

}
