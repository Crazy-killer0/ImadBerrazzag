package fr.afpa.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import fr.afpa.beans.Admin;
import fr.afpa.beans.Client;
import fr.afpa.beans.Compte;
import fr.afpa.beans.Conseiller;
import fr.afpa.beans.Operation;
import fr.afpa.beans.Utilisateur;
import fr.afpa.util.Util;

public class GestionFile {
	private ArrayList<Object> listClient;
	
	//FONCTION QUI GENERE LE PDF DES INFOS DU CLIENT
	/**
	 * 
	 * @param user
	 */
	public void pdfReleveClient(Client user) {
		if (user != null) {

			if (user instanceof Client) {
				PdfWriter writer = null;
				PdfDocument pdf = null;
				Document pdfDoc = null;
				try {
					writer = new PdfWriter("CDABANK_client.pdf");
					pdf = new PdfDocument(writer);
					pdfDoc = new Document(pdf);
					Paragraph titre = new Paragraph("CDA BANK - Ficher Client").setFontSize(10f).setBold()
							.setTextAlignment(TextAlignment.CENTER);
					Image img = new Image(ImageDataFactory
							.create("LogoCDABANK.png"))
									.setWidth(110f);
					
					genererQRCode(user);
					Image QRCODE = new Image(ImageDataFactory
							.create("qrcode.jpg"))
									.setWidth(110f).setFixedPosition(450, 700);
					

					Paragraph numeroClient = new Paragraph("Numero Client : "+user.getLogin())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph nom = new Paragraph("Nom : "+user.getNom()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph prenom = new Paragraph("Prenom : "+user.getPrenom())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph dateDeNaissance = new Paragraph("Date de naissance : "+ user.getDateNaissance())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph trait = new Paragraph(
							"______________________________________________________________________________")
									.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
									.setTextAlignment(TextAlignment.LEFT);
					Paragraph listeCompte = new Paragraph("Liste de compte ")
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph section = new Paragraph("Numero de compte                                     Solde ")
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);

					
					
					
					pdfDoc.add(img).add(titre).add(QRCODE).add(numeroClient).add(nom).add(prenom).add(dateDeNaissance)
					.add(trait).add(listeCompte).add(trait).add(section).add(trait);
					
					
					
					ArrayList<Compte> listeCompteClient = user.getListeCompte();
					
					for (Compte compte : listeCompteClient) {
						System.out.println("test");
						if (compte.getSolde()>0) {
							Paragraph listCompte1 = new Paragraph(compte.getNumeroCompte() +"                                              "+ compte.getSolde()+"                                    :)")
									.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
									.setTextAlignment(TextAlignment.LEFT);
							pdfDoc.add(listCompte1);
						}
						else {
							Paragraph listCompte1 = new Paragraph(compte.getNumeroCompte() +"                                              "+ compte.getSolde()+"                                    :(")
									.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
									.setTextAlignment(TextAlignment.LEFT);
							pdfDoc.add(listCompte1);
						}
						
					}
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (pdfDoc != null) {
						pdfDoc.close();
					}
				}

			}

		}
	}
	
	//FONCTION QUI GENERE UN PDF DES OPERATIONS DU COMPTE
	/**
	 * 
	 * @param user
	 * @param compte
	 */
	public void pdfReleveCompte(Client user, Compte compte) {
		if (user != null) {

			if (user instanceof Client) {
				PdfWriter writer = null;
				PdfDocument pdf = null;
				Document pdfDoc = null;
				try {
					writer = new PdfWriter("CDABANK_releveCompte.pdf");
					pdf = new PdfDocument(writer);
					pdfDoc = new Document(pdf);
					Paragraph titre = new Paragraph("CDA BANK - Ficher Client").setFontSize(10f).setBold()
							.setTextAlignment(TextAlignment.CENTER);

					Image img = new Image(ImageDataFactory
							.create("LogoCDABANK.png"))
									.setWidth(110f);
					
					genererQRCode(user);
					Image QRCODE = new Image(ImageDataFactory
							.create("qrcode.jpg"))
									.setWidth(110f).setFixedPosition(450, 700);
					

					Paragraph numeroClient = new Paragraph("Numero Client : "+user.getLogin())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph nom = new Paragraph("Nom : "+user.getNom()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph prenom = new Paragraph("Prenom : "+user.getPrenom())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph dateDeNaissance = new Paragraph("Date de naissance : "+ user.getDateNaissance())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph trait = new Paragraph(
							"______________________________________________________________________________")
									.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
									.setTextAlignment(TextAlignment.LEFT);
					Paragraph listeCompte = new Paragraph("Compte : "+compte.getNumeroCompte())
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);
					Paragraph section = new Paragraph("Numero d'operation            Date d'opération             Fin d'operation            Montant ")
							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setTextAlignment(TextAlignment.LEFT);

					
					
					
					pdfDoc.add(img).add(titre).add(QRCODE).add(numeroClient).add(nom).add(prenom).add(dateDeNaissance)
					.add(trait).add(listeCompte).add(trait).add(section).add(trait);
					
					
					
					ArrayList<Operation> listeCompteClient = compte.getListOperations();
					for (Operation operation : listeCompteClient) {
						if (operation.getMontant()>0) {
							Paragraph listCompte1 = new Paragraph(operation.getNumeroOperation() +"  : "+operation.getDateOperation()+ " - "+ operation.getDateFinOperation()+"   : " + operation.getMontant()+" €")
									.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
									.setTextAlignment(TextAlignment.LEFT);
							pdfDoc.add(listCompte1);
						}
						else {
							Paragraph listCompte1 = new Paragraph(operation.getNumeroOperation() +"  : "+operation.getDateOperation()+ " - "+ operation.getDateFinOperation()+"   : " + operation.getMontant()+" €")
									.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
									.setTextAlignment(TextAlignment.LEFT);
							pdfDoc.add(listCompte1);
						}
						
					}
					
					
//					
//					Paragraph listCompte2 = new Paragraph("Compte PEL: "+ user.getLogin())
//							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
//							.setTextAlignment(TextAlignment.LEFT);
//					Paragraph listCompte3 = new Paragraph("Compte Livret A: "+ user.getLogin())
//							.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
//							.setTextAlignment(TextAlignment.LEFT);
					
					
					

					

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (pdfDoc != null) {
						pdfDoc.close();
					}
				}

			}

		}
	}
	public static void genererQRCode(Client client) {
	        
		String path ="qrcode.jpg"; 
	        String data = "";
	        
	            data = data + "BEGIN:VCARD\r\n"
	                    + "NOM: " + client.getNom() + "\r\n " 
	            		+ "PRENOM: "+client.getPrenom() + "\r\n"
	                    + "EMAIL: " + client.getEmail() + "\r\n"
	                    + "CODE CLIENT: " + client.getIdClient() + "\r\n"
	                    + "LOGIN: " + client.getLogin() + "\r\n";
	            try {
	                BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 400, 400);
	                MatrixToImageWriter.writeToPath(matrix, "png",Paths.get(path));
	                BufferedImage bf = ImageIO.read(new FileInputStream(path));
	                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));
	                Result result = new MultiFormatReader().decode(bitmap);
	                System.out.println(result);
	                
	            } catch (WriterException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (NotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        
	        
	}
	
}
