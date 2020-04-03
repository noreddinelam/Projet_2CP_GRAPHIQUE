package noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Circuit implements Serializable{
	
	private static ArrayList<Composant> compUtilises = new ArrayList<Composant>(); // tout les composants du circuit
	private static ArrayList<Fil> filUtilises = new ArrayList<Fil>(); // tout les fils du circuit
	private static ArrayList<Pin> entreesCircuit = new ArrayList<Pin>(); // toutes les entrees du circuit
	private static ArrayList<Affichage> sortiesCircuit = new ArrayList<Affichage>(); // toutes les sorties du circuit
	private EtatLogique tableVerite[][]; // la table de verite du circuit
	private static ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>(); // la liste des etages pour les elts sequentiels 
	private static int nbEtages = 0; // nombre des etages
	
	
	public static void ajouterComposant(Composant comp) { // ajouter un composant à la liste des composants utilisés
		compUtilises.add(comp);
	}
	public static void ajouterFil(Fil fil) { // ajouter un fil à la liste des fils 
		filUtilises.add(fil);
	}
	public static void ajouterEntree(Pin pin) { // ajouter une entree à la liste des entrees
		entreesCircuit.add(pin);
		
	}
	public static void ajouterSortie(Affichage compAff) { // ajouter une sorties à la liste des sorties
		sortiesCircuit.add(compAff);
	}
	public void relier(Composant compS,Composant compD, int s, int e) {// pour relier deux composants 
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
	private EtatLogique[] convertir(int i , int nbits) // 
	{
		EtatLogique tableVerite[]= new EtatLogique[nbits];
		int j=0;
		String bin = Integer.toBinaryString(i); 
		for( j=0;j < bin.length() ; j++)
		{
			
			if(bin.charAt(bin.length()-j-1)=='0') tableVerite[nbits-j-1]=EtatLogique.ZERO;
			else tableVerite[nbits-j-1]=EtatLogique.ONE;
		}
		for(int k=j ; k<nbits; k++) tableVerite[nbits-k-1]=EtatLogique.ZERO;
		
		return tableVerite;
	}
	
	public void tableVerite()
	{
		int nbEntrees = entreesCircuit.size();
		int nbSorties = sortiesCircuit.size();
		int c= nbEntrees+nbSorties;
		int j,k=0;
		double l=Math.pow(2, nbEntrees);
		tableVerite = new EtatLogique[(int)l][c];
		EtatLogique ligne[] ;
		for (Pin pin : entreesCircuit) {
			System.out.print(pin.nom+"  ");
		}
		for (Affichage pin : sortiesCircuit) {
			System.out.print(((Pin)pin).nom + "  ");
		}
		System.out.println();
		for(int  i=0;i<l;i++)
		{
			ligne = Arrays.copyOf(convertir(i, nbEntrees), c);
			j=0;
			for ( Pin pin : entreesCircuit){
				pin.setEtat(ligne[j]);
				pin.evaluer();// a changer
				j++;
			}
			for ( Affichage aff : sortiesCircuit){
				Pin pin = (Pin) aff ;
				ligne[j]=pin.getEtat();
				j++;
			}
			tableVerite[i]=Arrays.copyOf(ligne, c);
		}
	}
	
	public void afficher() {
		for (int i = 0; i < tableVerite.length; i++) {
			for (int j = 0; j < tableVerite[i].length; j++) {
				System.out.print(tableVerite[i][j].getNum() + " | ");
			}
			System.out.println();
		}
	}
	public static ArrayList<Composant> getCompUtilises() {
		return compUtilises;
	}
	public static ArrayList<Fil> getFilUtilises() {
		return filUtilises;
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
	public static void initialiser() {// à completer au fur et mesure .
		for (Pin pin : entreesCircuit) {
			pin.evaluer();
		}
		
		ArrayList<Integer> etage = new ArrayList<Integer>();
		ArrayList<Integer> tmp = null ;
		int max;
		for (int i = 0; i < listeEtages.size(); i++) {
			for (Sequentiels b : listeEtages) {
				b.entreeHorloge.addEtages(etage);
				tmp = new ArrayList<Integer>(etage);
				max = Collections.max(tmp);
				nbEtages = (nbEtages < max ) ? max : nbEtages ;
				b.setEtages(tmp);
				etage.clear();
			}
		}
		
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
	public static void setCompUtilises(ArrayList<Composant> compUtilises) {
		Circuit.compUtilises = compUtilises;
	}
	public static void setFilUtilises(ArrayList<Fil> filUtilises) {
		Circuit.filUtilises = filUtilises;
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
