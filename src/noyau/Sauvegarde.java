package noyau;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Sauvegarde implements Serializable {

/*
	il faut ajouter ce qui suit :
	Circuit implements Serializable
	Composant implements Serializable
	Fil implements Serializable
	EtatLogique implements serializable



	et il faut ajouter les getters et les setters necessaire dans la classe circuit.
	setListeEtages, setCompUtilises, setFilUtilises, setEntreesCircuit, setSortiesCircuit
	setTableVerite, getNbEtages, setNbEtages : tu peux les generer par ecclipse automatiquement

	

	les fichier creer tu vas les trouver dans le chemin suivant : Projet\Projet2CP__SimCL__\src
	bien sur tu peux changer le chemin ou tu va les sauvgarder en introduisant le chemin avec des deux back slash
	et le nom du fichier bien sur , ex : C:\\Users\\asus\\Desktop\\Projet\\Projet2CP__SimCL__\\bin//"ici tu met le nom que tu veux donner au fichier"

	remarque :
	j'ai effectuer des testes sur les circuits Combinatoire car ce sont eux qui possede la methode afficher la table de verite incluse directement dans
	le circuit , par contre la methode pour tester les circuits Sequentiel consiste a utiliser l'horloge ie je n'ai pas trouver circuit.une methode donne
	pour tester les circuit Sequentiels mais normalement puisque elle marche pour les Combinatoire et les portes ca va marcher aussi pour les Sequentiel car c'est le
	meme principe.

	tu vas trouver quelques classes qui implementent l'interface serializable signale un ! mais en realite c'est pas un probleme et pour les regler il  faut avoir une Exception
	ou je vais trouver un id que je vais mettre dans une variable static mais je repete ce n'est pas un probleme d'ailleur meme mois je n'ai pas trouver cette
	excpetion.





*/




	private  ArrayList<Composant> compUtilises = new ArrayList<Composant>();
	private  ArrayList<Fil> filUtilises = new ArrayList<Fil>();
	private  ArrayList<Pin> entreesCircuit = new ArrayList<Pin>();
	private  ArrayList<Affichage> sortiesCircuit = new ArrayList<Affichage>();
	private EtatLogique tableVerite[][];
	private  ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>();
	private  int nbEtages = 0;




	public ArrayList<Composant> getCompUtilises() {
		return compUtilises;
	}
	public void setCompUtilises(ArrayList<Composant> compUtilises) {
		this.compUtilises = compUtilises;
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



	public static void saveCiruit(Circuit circuit, String nomFichier) {//la sauvgarde d'un objet de type circuit dans un fihcier par la methode de la serialization
		FileOutputStream fichier;
		ObjectOutputStream oo = null;
		Sauvegarde circuitSauv = new Sauvegarde();
		circuitSauv.setCompUtilises(Circuit.getCompUtilises());
		circuitSauv.setFilUtilises(Circuit.getFilUtilises());
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






	public static Circuit loadCiruit(String nomFichier) {//la recuperation d'un objet de type circuit dans un fihcier par la methode de la deserialization
		FileInputStream fichier;
		ObjectInputStream oo = null;
		Sauvegarde circuitSauv = null;
		Circuit circuit = new Circuit();
		try {
			fichier = new FileInputStream(nomFichier);
			oo = new ObjectInputStream(fichier);
			circuitSauv= (Sauvegarde) oo.readObject();

			Circuit.setCompUtilises(circuitSauv.getCompUtilises());
			Circuit.setFilUtilises(circuitSauv.getFilUtilises());
			Circuit.setEntreesCircuit(circuitSauv.getEntreesCircuit());
			Circuit.setListeEtages(circuitSauv.getListeEtages());
			Circuit.setNbEtages(circuitSauv.getNbEtages());
			Circuit.setSortiesCircuit(circuitSauv.getSortiesCircuit());
			circuit.setTableVerite(circuitSauv.getTableVerite());



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
		return circuit;

	}



}
