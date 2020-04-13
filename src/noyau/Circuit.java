package noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Circuit implements Serializable{
	
	private static HashMap<Composant, ImageView> compUtilises = new HashMap<Composant,ImageView>();
	//private static ArrayList<Composant> compUtilises = new ArrayList<Composant>(); // tout les composants du circuit
	//private static ArrayList<Fil> filUtilises = new ArrayList<Fil>(); // tout les fils du circuit
	private static HashMap<Fil, ArrayList<Polyline>> filUtilises = new HashMap<Fil,ArrayList<Polyline>>();
	private static ArrayList<Pin> entreesCircuit = new ArrayList<Pin>(); // toutes les entrees du circuit
	private static ArrayList<Affichage> sortiesCircuit = new ArrayList<Affichage>(); // toutes les sorties du circuit
	private EtatLogique tableVerite[][]; // la table de verite du circuit
	private static ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>(); // la liste des etages pour les elts sequentiels 
	private static int nbEtages = 0; // nombre des etages
	
	
	public static void ajouterComposant(Composant comp,ImageView img) { // ajouter un composant à la liste des composants utilisés
		//compUtilises.add(comp);
		compUtilises.put(comp, img);
	}
	public static void ajouterFil(Fil fil,ArrayList<Polyline> polyline) { // ajouter un fil à la liste des fils 
		//filUtilises.add(fil);
		filUtilises.put(fil, polyline);
	}
	public static void ajouterEntree(Pin pin) { // ajouter une entree à la liste des entrees
		entreesCircuit.add(pin);
		
	}
	public static void ajouterSortie(Affichage compAff) { // ajouter une sorties à la liste des sorties
		sortiesCircuit.add(compAff);
	}
	public static  void relier(Composant compS,Composant compD, int s, int e) {// pour relier deux composants 
		Fil f = compS.sorties[s]; // recuperer le fil de sortie du composant source
		compD.entrees[e] = f; // mettre le fil dans l'entree e du composant de destination 
		f.addDestination(compD); // ajouter une destination au fil
		
	}
	public void relierCommand(Composant compS,Combinatoires compD, int s, int e) { // pour relier une commande pour mux et demux
		Fil f = compS.sorties[s];
		compD.commande[e] = f;
		f.addDestination(compD);		
	}
	public void relierHorloge(Sequentiels composant,Composant horloge,int sortie) { // pour relier une horloge pour les elts sequentiels
		Fil fil = horloge.sorties[sortie];
		composant.entreeHorloge = fil;
		fil.addDestination(composant);
		if (! listeEtages.contains(composant)) { // ajouter le composant à la liste des étages si il ne se trouve pas
			listeEtages.add(composant);
		}
	}
	public void relierClear(Sequentiels composant,Composant clear,int sortie) { // pour relier un clear pour les elts sequentiels
		Fil fil = clear.sorties[sortie];
		composant.clear = fil;
		fil.addDestination(composant);
	}
	public void relierLoad(Sequentiels composant,Composant load,int sortie) { // pour relier un load pour les registre decalage et compteur
		Fil fil = load.sorties[sortie];
		if (composant.getClass().getSimpleName().equals("RegistreDecalage")) {
			((RegistreDecalage)composant).setLoad(fil);
		}
		else {
			((Compteur)composant).setLoad(fil);
		}
		fil.addDestination(composant);
	}
	public void relierPreset(Bascule bascule,Composant preset,int sortie) { // pour relier un preset pour les bascules
		Fil fil = preset.sorties[sortie];
		bascule.setPreset(fil);
		fil.addDestination(bascule);
	}
	private EtatLogique[] convertir(int i , int nbits) // elle transforme une ligne dans de la table en etat  logique pour generer les sorties
	{
		EtatLogique tableVerite[]= new EtatLogique[nbits]; // initialiser un tableau de etat logique qui sera le resultat de la conversion
		int j=0;
		String bin = Integer.toBinaryString(i); // transformer le numero de la ligne en binaire 
		for( j=0;j < bin.length() ; j++) // initialiser les cases de 0 au nb bit de la ligne avec soit ZERO ou ONE 
		{	
			if(bin.charAt(bin.length()-j-1)=='0') tableVerite[nbits-j-1]=EtatLogique.ZERO;
			else tableVerite[nbits-j-1]=EtatLogique.ONE;
		}
		for(int k=j ; k<nbits; k++) tableVerite[nbits-k-1]=EtatLogique.ZERO; // continuer l'initialisation des cases restantes
		
		return tableVerite;
	}
	
	public void tableVerite() // generer la table de verité du ciruit
	{
		int nbEntrees = entreesCircuit.size(); // nombre d'entrees du circuit
		int nbSorties = sortiesCircuit.size(); // nombre de sortiees du circuit
		int c= nbEntrees+nbSorties; // la taille de la table de verité
		int j,k=0;
		double l=Math.pow(2, nbEntrees); // calculer le nombre de lignes de la table de verité
		tableVerite = new EtatLogique[(int)l][c]; // initialiser la taille de la table
		EtatLogique ligne[] ; // sert à stocker une ligne 
		for (Pin pin : entreesCircuit) { // affichage des labels des pins d'entrees
			System.out.print(pin.nom+"  ");
		}
		for (Affichage pin : sortiesCircuit) { // affichage des labels des pins de sorties 
			System.out.print(((Pin)pin).nom + "  ");
		}
		System.out.println();
		for(int  i=0;i<l;i++)
		{
			ligne = Arrays.copyOf(convertir(i, nbEntrees), c);
			j=0;
			for ( Pin pin : entreesCircuit){ // evaluer les pins d'entrees
				pin.setEtat(ligne[j]);
				pin.evaluer();// a changer
				j++;
			}
			for ( Affichage aff : sortiesCircuit){ // recuperer l'etat des pins de sorties
				Pin pin = (Pin) aff ;
				ligne[j]=pin.getEtat();
				j++;
			}
			tableVerite[i]=Arrays.copyOf(ligne, c); // inserer la ligne dans la table de verité
		}
	}
	
	public void afficher() { // pour l'affichage en mode console de la table de vérité 
		for (int i = 0; i < tableVerite.length; i++) {
			for (int j = 0; j < tableVerite[i].length; j++) {
				System.out.print(tableVerite[i][j].getNum() + " | ");
			}
			System.out.println();
		}
	}
	public static void initialiser() {// à completer au fur et mesure .
		for (Pin pin : entreesCircuit) { // initialiser les pins d'entrees pour le commencement de la simulation
			if(pin.getInput() == true) pin.evaluer();  //a na7i
		}
		
		ArrayList<Integer> etage = new ArrayList<Integer>();
		ArrayList<Integer> tmp = null ;
		int max;
		for (int i = 0; i < listeEtages.size(); i++) { // former les étages du circuit pour l'execution et la generation du chronogramme
			for (Sequentiels b : listeEtages) {
				b.entreeHorloge.addEtages(etage);
				tmp = new ArrayList<Integer>(etage);
				max = Collections.max(tmp); // avoir le nombre des etages
				nbEtages = (nbEtages < max ) ? max : nbEtages ;
				b.setEtages(tmp);
				etage.clear();
			}
		}
		
	}
	public static ImageView getImageFromComp(Composant comp) { // recuperer l'image associé à un composant .
		return compUtilises.get(comp);
	}
	public static Composant getCompFromImage(ImageView img) { // recuperer le composant associé à une image .
		Composant composant = null;
		for (Entry<Composant, ImageView> entry : compUtilises.entrySet()) {
			if (entry.getValue().equals(img)) {
				composant = entry.getKey();
				break;
			}
		}
		return composant;
	}

	public static ArrayList<Polyline> getPolylineFromFil(Fil fil) { // recuperer l'image associé à un composant .
		return filUtilises.get(fil);
	}
	public static Fil getFilFromPolyline(Polyline ligne) { // recuperer le composant associé à une image .
		Fil fil = null;
		for (Entry<Fil, ArrayList<Polyline>> entry : filUtilises.entrySet()) {
			if (entry.getValue().contains(ligne)) {
				fil = entry.getKey();
				break;
			}
		}
		return fil;
	}
	public static void defaultCompValue() {
		for (Fil fil : filUtilises.keySet()) {
			fil.defaultValue();
		}
		for(Composant composant : compUtilises.keySet()) {
			composant.defaultValue();
		}
	}
	
	public static void removeImageFromComp(Composant comp) {
		compUtilises.remove(comp);
	}
	public static void removeCompFromImage(ImageView img) { // recuperer le composant associé à une image .
		compUtilises.remove(getCompFromImage(img));
	}

	
	public static HashMap<Fil, ArrayList<Polyline>> getFilUtilises() {
		return filUtilises;
	}
	public static void setFilUtilises(HashMap<Fil, ArrayList<Polyline>> filUtilises) {
		Circuit.filUtilises = filUtilises;
	}
	public static ArrayList<Pin> getEntreesCircuit() {
		return entreesCircuit;
	}
	public static ArrayList<Affichage> getSortiesCircuit() {
		return sortiesCircuit;
	}
	public EtatLogique[][] getTableVerite() {
		return tableVerite;
	}
	public static ArrayList<Sequentiels> getListeEtages() {
		return listeEtages;
	}
	public static void setListeEtages(ArrayList<Sequentiels> listeEtages) {
		Circuit.listeEtages = listeEtages;
	}
	public static int getNbEtages() {
		return nbEtages;
	}
	public static void setNbEtages(int nbEtages) {
		Circuit.nbEtages = nbEtages;
	}
	public static HashMap<Composant, ImageView> getCompUtilises() {
		return compUtilises;
	}
	public static void setCompUtilises(HashMap<Composant, ImageView> compUtilises) {
		Circuit.compUtilises = compUtilises;
	}
	
	public static void setEntreesCircuit(ArrayList<Pin> entreesCircuit) {
		Circuit.entreesCircuit = entreesCircuit;
	}
	public static void setSortiesCircuit(ArrayList<Affichage> sortiesCircuit) {
		Circuit.sortiesCircuit = sortiesCircuit;
	}
	public void setTableVerite(EtatLogique[][] tableVerite) {
		this.tableVerite = tableVerite;
	}
	
	
}
