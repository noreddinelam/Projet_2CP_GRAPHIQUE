package noyau;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class Sauvegarde implements Serializable {

	private static HashMap<Composant, ImageView> compUtilises = new HashMap<Composant,ImageView>();
	private  ArrayList<Fil> filUtilises = new ArrayList<Fil>();
	private  ArrayList<Pin> entreesCircuit = new ArrayList<Pin>();
	private  ArrayList<Affichage> sortiesCircuit = new ArrayList<Affichage>();
	private EtatLogique tableVerite[][];
	private  ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>();
	private  int nbEtages = 0;

	public static void saveCiruit(Circuit circuit, String nomFichier) {//la sauvgarde d'un objet de type circuit dans un fihcier par la methode de la serialization
		FileOutputStream fichier;
		ObjectOutputStream oo = null;
		Sauvegarde circuitSauv = new Sauvegarde();
		circuitSauv.setCompUtilises(Circuit.getCompUtilises());
		//circuitSauv.setFilUtilises(Circuit.getFilUtilises());
		circuitSauv.setEntreesCircuit(Circuit.getEntreesCircuit());
		circuitSauv.setListeEtages(Circuit.getListeEtages());
		circuitSauv.setNbEtages(Circuit.getNbEtages());
		circuitSauv.setSortiesCircuit(Circuit.getSortiesCircuit());
		circuitSauv.setTableVerite(circuit.getTableVerite());

		try {
			fichier = new FileOutputStream(nomFichier);
			oo = new ObjectOutputStream(fichier);
			oo.writeObject(circuitSauv);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				oo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void loadCiruit(String nomFichier) {//la recuperation d'un objet de type circuit dans un fihcier par la methode de la deserialization
		FileInputStream fichier;
		ObjectInputStream oo = null;
		Sauvegarde circuitSauv = null;
		//Circuit circuit = new Circuit();
		try {
			fichier = new FileInputStream(nomFichier);
			oo = new ObjectInputStream(fichier);
			circuitSauv= (Sauvegarde) oo.readObject();
			Circuit.setCompUtilises(circuitSauv.getCompUtilises());
			//Circuit.setFilUtilises(circuitSauv.getFilUtilises());
			Circuit.setEntreesCircuit(circuitSauv.getEntreesCircuit());
			Circuit.setListeEtages(circuitSauv.getListeEtages());
			Circuit.setNbEtages(circuitSauv.getNbEtages());
			Circuit.setSortiesCircuit(circuitSauv.getSortiesCircuit());
			Circuit.setTableVerite(circuitSauv.getTableVerite());

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				oo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		//return circuit;

	}

	
	public static HashMap<Composant, ImageView> getCompUtilises() {
		return compUtilises;
	}

	public static void setCompUtilises(HashMap<Composant, ImageView> compUtilises) {
		Sauvegarde.compUtilises = compUtilises;
	}

	public ArrayList<Fil> getFilUtilises() {
		return filUtilises;
	}
	public void setFilUtilises(ArrayList<Fil> filUtilises) {
		this.filUtilises = filUtilises;
	}
	public ArrayList<Pin> getEntreesCircuit() {
		return entreesCircuit;
	}
	public void setEntreesCircuit(ArrayList<Pin> entreesCircuit) {
		this.entreesCircuit = entreesCircuit;
	}
	public ArrayList<Affichage> getSortiesCircuit() {
		return sortiesCircuit;
	}
	public void setSortiesCircuit(ArrayList<Affichage> sortiesCircuit) {
		this.sortiesCircuit = sortiesCircuit;
	}
	public EtatLogique[][] getTableVerite() {
		return tableVerite;
	}
	public void setTableVerite(EtatLogique[][] tableVerite) {
		this.tableVerite = tableVerite;
	}
	public ArrayList<Sequentiels> getListeEtages() {
		return listeEtages;
	}
	public void setListeEtages(ArrayList<Sequentiels> listeEtages) {
		this.listeEtages = listeEtages;
	}
	public int getNbEtages() {
		return nbEtages;
	}
	public void setNbEtages(int nbEtages) {
		this.nbEtages = nbEtages;
	}

}
