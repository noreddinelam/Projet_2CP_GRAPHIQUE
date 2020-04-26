package noyau;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map.Entry;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sauvegarde implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2097504601089191547L;
	private  HashMap<Composant,Coordonnees> compUtilises = new HashMap<Composant, Coordonnees>();
	private  HashMap<Composant,Taille> lesTailles = new HashMap<Composant, Taille>();
	private  HashMap<Fil,ArrayList<InfoPolyline>> filUtilises = new HashMap<Fil, ArrayList<InfoPolyline>>();
	private  ArrayList<Pin> entreesCircuit = new ArrayList<Pin>();
	private  ArrayList<Affichage> sortiesCircuit = new ArrayList<Affichage>();
	private ArrayList<SourceConstante> sourceConstantes = new ArrayList<SourceConstante>();
	private EtatLogique tableVerite[][];
	private  ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>();
	private  int nbEtages = 0;

	public  void saveCiruit(String nomFichier) {//la sauvgarde d'un objet de type circuit dans un fihcier par la methode de la serialization
		FileOutputStream fichier ;
		ObjectOutputStream oo = null;
		for(Entry<Composant, ImageView> liste : Circuit.getCompUtilises().entrySet()){
			compUtilises.put(liste.getKey(), new Coordonnees(liste.getValue().getLayoutX(), liste.getValue().getLayoutY()));
			lesTailles.put(liste.getKey(),new Taille(liste.getValue().getFitHeight(), liste.getValue().getFitWidth()));
		}	
		this.setFilUtilises(Circuit.getFilUtilises());
		for (Fil fil : filUtilises.keySet()) {
			for (InfoPolyline infoPolyline : filUtilises.get(fil)) {
				infoPolyline.refrechPoints();
			}
		}
		this.setEntreesCircuit(Circuit.getEntreesCircuit());
		this.setListeEtages(Circuit.getListeEtages());
		this.setNbEtages(Circuit.getNbEtages());
		this.setSortiesCircuit(Circuit.getSortiesCircuit());
		this.setTableVerite(Circuit.getTableVerite());
		this.setSourceConstantes(Circuit.getListSouceCte());

		try {
			fichier = new FileOutputStream(nomFichier);
			oo = new ObjectOutputStream(fichier);
			oo.writeObject(this);

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

	public ArrayList<SourceConstante> getSourceConstantes() {
		return sourceConstantes;
	}

	public void setSourceConstantes(ArrayList<SourceConstante> sourceConstantes) {
		this.sourceConstantes = sourceConstantes;
	}
	public static  void loadCiruit(String nomFichier) {//la recuperation d'un objet de type circuit dans un fihcier par la methode de la deserialization
		FileInputStream fichier ;
		ObjectInputStream oo = null;
		Sauvegarde circuitSauv = null;
		try {
			fichier = new FileInputStream(nomFichier);
			oo = new ObjectInputStream(fichier);
			circuitSauv= (Sauvegarde) oo.readObject();
			HashMap<Composant, ImageView> compMap = new HashMap<Composant, ImageView>();
			for (Entry<Composant,Coordonnees> entry : circuitSauv.compUtilises.entrySet()) {
				ImageView imageView = new ImageView();
				imageView.setImage(new Image(entry.getKey().generatePath()));
				imageView.setLayoutX(entry.getValue().getX());
				imageView.setLayoutY(entry.getValue().getY());
				imageView.setFitHeight(circuitSauv.lesTailles.get(entry.getKey()).getHeight());
				imageView.setFitWidth(circuitSauv.lesTailles.get(entry.getKey()).getWidth());
				compMap.put(entry.getKey(), imageView);
			}
			Circuit.setCompUtilises(compMap); 
			Circuit.setFilUtilises(circuitSauv.getFilUtilises());
			Circuit.setEntreesCircuit(circuitSauv.getEntreesCircuit());
			Circuit.setListeEtages(circuitSauv.getListeEtages());
			Circuit.setNbEtages(circuitSauv.getNbEtages());
			Circuit.setSortiesCircuit(circuitSauv.getSortiesCircuit());
			Circuit.setTableVerite(circuitSauv.getTableVerite());
			Circuit.setListSouceCte(circuitSauv.getSourceConstantes());

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

	
	public  HashMap<Composant, Coordonnees> getCompUtilises() {
		return compUtilises;
	}

	public  void setCompUtilises(HashMap<Composant, Coordonnees> compUtilises) {
		this.compUtilises = compUtilises;
	}

	public HashMap<Fil, ArrayList<InfoPolyline>> getFilUtilises() {
		return filUtilises;
	}
	public void setFilUtilises(HashMap<Fil, ArrayList<InfoPolyline>> hashMap) {
		this.filUtilises = hashMap;
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
