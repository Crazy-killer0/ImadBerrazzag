package fr.afpa.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public abstract class Util {

	public final static String PATH_COMPTE = "compte.sr";
	public final static String PATH_AGENCE = "agence.sr";
	public final static String PATH_CLIENT = "client.sr";
	public final static String PATH_CONSEILLER = "cons.sr";
	public final static String PATH_ADMIN = "adm.sr";

	/**
	 * Methode qui donne un random
	 * 
	 * @param min : borne minimale
	 * @param max : borne maximale
	 * @return int : notre chiffre random
	 */
	public static int random(int min, int max) {
		int rand = (int) ((Math.random() * ((max - min) + 1)) + min);
		return rand;

	}

	/**
	 * Méthode enregistrement d'une liste dans un fichier
	 * @param String path : le chemin du fichier
	 * @param LinkedHashSet<Object> listObjt  : les objets à inscrire dans notre fichier 
	 * @return boolean : symbolise l'execution
	 */
	public static boolean enregistrerFile(String path, ArrayList<Object>listObjt) {

		FileWriter fw = null;
		BufferedWriter bw = null;
		ObjectOutputStream oss = null;

		File file = new File(path);
		FileOutputStream f = null;

		try {
			f = new FileOutputStream(file);
			oss = new ObjectOutputStream(f);

			oss.writeObject(listObjt);
			f.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (oss != null) {

				try {

					oss.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

				return true;
			}
		}

		return false;

	}

	/**
	 * Methode de lecture d'un fichier 
	 * @param String path : le chemin du fichier 
	 * @return LinkedHashSet<Object> : une list De type Objet contant la data du fichier
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Object> lireFichier(String path) {

		ArrayList<Object> listdata = new ArrayList<Object>();

		File file = null;
		
		if(!new File(path).exists()) {
			
			Util.enregistrerFile(path, listdata);
			file = new File(path);
			
		} else {
			
			file = new File(path);
		}
		

		FileInputStream f = null;
		ObjectInputStream ois = null;

		try {
			
			f = new FileInputStream(file);
			ois = new ObjectInputStream(f);
			listdata = (ArrayList) ois.readObject();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			return listdata;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} finally {

			if (ois != null) {

				try {

					ois.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return listdata;

	}

}
