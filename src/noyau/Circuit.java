package noyau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class Circuit {
	
	private static HashMap<Composant, ImageView> compUtilises = new HashMap<Composant,ImageView>();// tout les composants du circuit
	private static HashMap<Fil, ArrayList<InfoPolyline>> filUtilises = new HashMap<Fil,ArrayList<InfoPolyline>>();// tout les fils du circuit
	private static ArrayList<Pin> entreesCircuit = new ArrayList<Pin>(); // toutes les entrees du circuit
	public static ArrayList<Pin> sortiesCircuit = new ArrayList<Pin>(); // toutes les sorties du circuit
	public static EtatLogique tableVerite[][]; // la table de verite du circuit
	private static ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>(); // la liste des etages pour les elts sequentiels 
	private static int nbEtages = 0; // nombre des etages	
	private static ArrayList<SourceConstante> listSouceCte = new ArrayList<SourceConstante>();//liste des sources constants
	private static ArrayList<ExceptionProgramme> circuitException = new ArrayList<ExceptionProgramme>();
	private static ArrayList<Composant> composantsErronee = new ArrayList<Composant>();

	
	public static void ajouterCompErrone(Composant composant) { /// pour ajouter des composant erronnes
		composantsErronee.add(composant);
	}
	
	public static void AjouterListeException(ArrayList<ExceptionProgramme> exceptionProgramme) { /// ajouter une liste d'exception à la liste des exceptions du circuit
		circuitException.addAll(exceptionProgramme);
	}
	
	public static void AjouterUneException(ExceptionProgramme exceptionProgramme) {/// ajouter une exception a la liste des exceptions
		circuitException.add(exceptionProgramme);
	}
	
	public static void clearException() { /// vider la liste des exceptions
		circuitException.clear();
	}
	
	public static boolean isThereAnyError() { /// rend vrai s'il ya des erreurs dans le circuit
		for (ExceptionProgramme exceptionProgramme : circuitException) {
			if (exceptionProgramme.getTypesExceptions()==TypesExceptions.ERREUR) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isThereAnyException() { /// voir si la liste des exceptions est vide ou non
		return ((circuitException.size() != 0) ? true : false);
	}
	
	public static ArrayList<ExceptionProgramme> getListeExceptionProgrammes() {
		return circuitException;
	}
	
	public static void validerCircuits() { /// elle est utilisée pour vous dire es que un circuit est validé ou non
		composantsErronee.clear();
		if (compUtilises.isEmpty()) { /// erreur si le circuit est vide
			circuitException.add(new CircuitVide(TypesExceptions.ERREUR));
		}
		else {
			if (entreesCircuit.size() == 0) { /// alerte si le circuit ne contient aucun element qui se change
				boolean stop = false;
				for (Composant composant : compUtilises.keySet()) {
					if (composant.getClass().getSimpleName().equals("Horloge")) {
						stop = true;
						break;
					}
				}
				if (!stop) {
					circuitException.add(new AucuneEntree(TypesExceptions.ALERTE));
				}
			}
			for (Composant composant : compUtilises.keySet()) { /// une boucle pour detecter les erreurs dans chaque composant
				composant.validerComposant();
			}
			ArrayList<Polyline> result;
			for (Composant composant : composantsErronee) { /// colorer en rouge les fils ou il y'a les erreurs
				for (int i = 0; i < composant.getNombreSortie(); i++) {
					result = getListePolylineFromFil(composant.getFilSortieByNum(i));
					for (Polyline polyline : result) {
						polyline.setStroke(Color.RED);
					}
				}
			}
		}
	}
	
	public static void defaultColorFil() { /// re-affecter les couleurs par defaut aux fils
		ArrayList<Polyline> result;
		for (Composant composant : composantsErronee) {
			for (int i = 0; i < composant.getNombreSortie(); i++) {
				result = getListePolylineFromFil(composant.getFilSortieByNum(i));
				for (Polyline polyline : result) {
					polyline.setStroke(Color.BLACK);
				}
			}
		}
	}
	
	public static void defaultValueSeq() { /// affecter les valeurs par defaut aux elements sequentiels
		for (Sequentiels sequentiels : listeEtages) {
			sequentiels.defaultValue();
		}
	}
	
	
	public static void ajouterComposant(Composant comp,ImageView img) { // ajouter un composant à la liste des composants utilisés
		compUtilises.put(comp, img);
	}
	public static void ajouterFil(Fil fil,ArrayList<InfoPolyline> polyline) { // ajouter un fil à la liste des fils 
		filUtilises.put(fil, polyline);
	}
	public static void ajouterEntree(Pin pin) { // ajouter une entree à la liste des entrees
		entreesCircuit.add(pin);
		
	}
	public static void ajouterSourceCte(SourceConstante cte) { // ajouter une entree à la liste des entrees
		listSouceCte.add(cte);
	}
	public static void supprimerSourceCte(SourceConstante cte) {
		listSouceCte.remove(cte);
	}
	public static void ajouterSortie(Pin compAff) { // ajouter une sorties à la liste des sorties
		sortiesCircuit.add(compAff);
	}
	public static  void relier(Composant compS,Composant compD, int s, int e) {// pour relier deux composants 
		Fil f = compS.sorties[s]; // recuperer le fil de sortie du composant source
		compD.entrees[e] = f; // mettre le fil dans l'entree e du composant de destination 
		f.addDestination(compD); // ajouter une destination au fil
		
	}
	public static void relierCommand(Composant compS,Combinatoires compD, int s, int e) { // pour relier une commande pour mux et demux
		Fil f = compS.sorties[s];
		compD.commande[e] = f;
		f.addDestination(compD);		
	}
	public static void relierHorloge(Sequentiels composant,Composant horloge,int sortie) { // pour relier une horloge pour les elts sequentiels
		Fil fil = horloge.sorties[sortie];
		composant.entreeHorloge = fil;
		fil.addDestination(composant);
		if (! listeEtages.contains(composant)) { // ajouter le composant à la liste des étages si il ne se trouve pas
			listeEtages.add(composant);
		}
	}
	public static void relierClear(Sequentiels composant,Composant clear,int sortie) { // pour relier un clear pour les elts sequentiels
		Fil fil = clear.sorties[sortie];
		composant.clear = fil;
		fil.addDestination(composant);
	}
	public static void relierLoad(Sequentiels composant,Composant load,int sortie) { // pour relier un load pour les registre decalage et compteur
		Fil fil = load.sorties[sortie];
		if (composant.getClass().getSimpleName().equals("RegistreDecalage")) {
			((RegistreDecalage)composant).setLoad(fil);
		}
		else {
			((Compteur)composant).setLoad(fil);
		}
		fil.addDestination(composant);
	}
	public static void relierPreset(Bascule bascule,Composant preset,int sortie) { // pour relier un preset pour les bascules
		Fil fil = preset.sorties[sortie];
		bascule.setPreset(fil);
		fil.addDestination(bascule);
	}
	private static EtatLogique[] convertir(int i , int nbits) // elle transforme une ligne dans de la table en etat  logique pour generer les sorties
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
	
	public static void tableVerite(ArrayList<Pin> entreesCircuit,ArrayList<Pin> sortiesCircuit) // generer la table de verité du ciruit
	{
		int nbEntrees = entreesCircuit.size(); // nombre d'entrees du circuit
		int nbSorties = sortiesCircuit.size(); // nombre de sortiees du circuit
		int c= nbEntrees+nbSorties; // la taille de la table de verité
		int j,k=0;
		double l=Math.pow(2, nbEntrees); // calculer le nombre de lignes de la table de verité
		tableVerite = new EtatLogique[(int)l][c]; // initialiser la taille de la table
		EtatLogique ligne[] ; // sert à stocker une ligne 
		/*for (Pin pin : entreesCircuit) { // affichage des labels des pins d'entrees
			System.out.print(pin.nom+"  ");
		}*/
		/*for (Affichage pin : sortiesCircuit) { // affichage des labels des pins de sorties 
			System.out.print(((Pin)pin).nom + "  ");
		}*/
		for (SourceConstante cte : listSouceCte) {
			cte.evaluer();
		}
		for(int i=0;i<l;i++)
		{
			ligne = Arrays.copyOf(convertir(i, nbEntrees), c);
			j=0;
			for ( Pin pin : entreesCircuit){ // evaluer les pins d'entrees
				pin.setEtat(ligne[j]);
				pin.evaluer();// a changer
				j++;
			}
			for ( Pin pin : sortiesCircuit){ // recuperer l'etat des pins de sorties
				ligne[j]=pin.getEtat();
				j++;
			}
			tableVerite[i]=Arrays.copyOf(ligne, c); // inserer la ligne dans la table de verité
		}
	}
	
	public static void afficher() { // pour l'affichage en mode console de la table de vérité 
		for (int i = 0; i < tableVerite.length; i++) {
			for (int j = 0; j < tableVerite[i].length; j++) {
				System.out.print(tableVerite[i][j].getNum() + " | ");
			}
			System.out.println();
		}
	}
	public static void initialiser() {// à completer au fur et mesure .
		for (Pin pin : entreesCircuit) { // initialiser les pins d'entrees pour le commencement de la simulation
			pin.evaluer();  
		}
		for (SourceConstante cte : listSouceCte) { // initialiser les pins d'entrees pour le commencement de la simulation
			cte.evaluer(); 
		}
		int j = 0;
		for (Sequentiels sequentiels : listeEtages) { // initialiser les sorties des elements sequentiels
			for (int i = 0; i < sequentiels.getNombreSortie(); i++) {
				if (sequentiels.getClass().getSuperclass().equals(Bascule.class)) {
					if (j == 0) {
						sequentiels.getSorties()[i].setEtatLogiqueFil(EtatLogique.ZERO);
						j++;
					}
					else {
						sequentiels.getSorties()[i].setEtatLogiqueFil(EtatLogique.ONE);
						j=0;
					}
				}
				else {
					sequentiels.getSorties()[i].setEtatLogiqueFil(EtatLogique.ZERO);
				}
				sequentiels.getSorties()[i].evaluer();
			}
			if (sequentiels.getClass().equals(CircuitIntegreSequentiel.class)) {
				((CircuitIntegreSequentiel)sequentiels).initSortiesElemenets();
			}
		}
		ArrayList<Integer> etage = new ArrayList<Integer>();
		ArrayList<Integer> tmp = null ;
		int max;
		for (int i = 0; i < listeEtages.size(); i++) { // former les étages du circuit pour l'execution et la generation du chronogramme
			for (Sequentiels b : listeEtages) {
				b.entreeHorloge.addEtages(etage);
				tmp = new ArrayList<Integer>(etage);
				if (tmp.size() != 0) {
					max = Collections.max(tmp); // avoir le nombre des etages
					nbEtages = (nbEtages < max ) ? max : nbEtages ;
				}
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

	public static ArrayList<InfoPolyline> getPolylineFromFil(Fil fil) { // recuprer les infos polyline liée à un fil .
		return filUtilises.get(fil);
	}
	public static Fil getFilFromPolyline(Polyline ligne) { // recuperer le composant associé à une image .
		Fil fil = null;
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : filUtilises.entrySet()) {
			if (entry.getValue().contains(new InfoPolyline(ligne))) {	
				fil = entry.getKey();
				break;
			}
		}
		return fil;
	}
	public static void defaultCompValue() {/// affecter les valeurs par defaut à tout les composants
		for (Fil fil : filUtilises.keySet()) {
			fil.defaultValue();
		}
		for(Composant composant : compUtilises.keySet()) {
			composant.defaultValue();
		}
	}
	
	public static ArrayList<InfoPolyline> getListFromPolyline(Polyline ligne) { // recuperer le composant associé à une image .
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : filUtilises.entrySet()) {
			if (entry.getValue().contains(new InfoPolyline(ligne))) {
				return entry.getValue();
			}
		}
		return new ArrayList<InfoPolyline>();
	}
	public static InfoPolyline getInfoPolylineFromPolyline(Polyline ligne) { // recuperer la classe infoPolyline qui contient des infos necessaire pour les fils
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : filUtilises.entrySet()) {
			if (entry.getValue().contains(new InfoPolyline(ligne))) {
				for (InfoPolyline info : entry.getValue()) {
					if(info.equals(new InfoPolyline(ligne))) {
						return info;
					}
				}
			}
		}
		return null;
	}
	public static void removeImageFromComp(Composant comp) { /// supprimer le couple image composant en utilisant le composant
		compUtilises.remove(comp);
	}
	public static void removeCompFromImage(ImageView img) { // recuperer le composant associé à une image .
		compUtilises.remove(getCompFromImage(img));
	}
	
	public static ArrayList<Polyline> supprimerComp(Composant composant) { /// utiliser dans la suppression des composants
		
		composant.derelierComp(); /// derelier le composant de toutes ces connexions
		compUtilises.remove(composant); /// supprimer le composant
		if (composant.getClass().getSimpleName().equals("Pin")) { /// verifier si c'est un pin et le supprimer soit de la liste des entrees ou de sorties
			Pin pin = (Pin)composant;
			if (pin.isInput()) {
				entreesCircuit.remove(pin);
			}
			else {
				sortiesCircuit.remove(pin);
			}
		}
		else { 
			if (composant.getClass().getSimpleName().equals("SourceConstante")) /// enlever le composant de la liste des source constante
				listSouceCte.remove(composant);
		}
		if ((composant.getClass().getSuperclass().equals(Bascule.class)) || (composant.getClass().getSuperclass().equals(Sequentiels.class))) {
			listeEtages.remove(composant); /// enlever le composant de la liste des composant sequentiels
		}
		Fil sortieFil;
		ArrayList<Composant> arrayList ;
		ArrayList<Polyline> listPolylines = new ArrayList<Polyline>();
		for (int i = 0; i < composant.getNombreSortie(); i++) { /// derelier les composants qui sont au tour du composant à supprimer
			sortieFil = composant.getFilSortieByNum(i);
			listPolylines.addAll(getListePolylineFromFil(sortieFil));
			filUtilises.remove(sortieFil);
			arrayList = sortieFil.getDestination();
			for (Composant destination : arrayList) {
				destination.derelierEntreeFromComp(sortieFil);
			}
			arrayList.clear();
		}
		return listPolylines;
	}
	
	public static ArrayList<Polyline> getListePolylineFromFil(Fil fil) { // recuperer les polylines d'un fil donnee
		ArrayList<Polyline> result = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> arrayList =  filUtilises.get(fil);
		if (arrayList != null) {
			for (InfoPolyline polyline : arrayList) {
				result.add(polyline.getLinePrincipale());
			}			
		}
		return result;
	}
	
	public static ArrayList<Polyline> supprimerAllPolylinesForCompounent(Composant composant) { /// supprimer tout les fils de sorties du composant du Hashmap de fils
		ArrayList<Polyline> result = new ArrayList<Polyline>();
		for (int i = 0; i < composant.getNombreSortie(); i++) {
			result.addAll(getListePolylineFromFil(composant.getFilSortieByNum(i)));
			filUtilises.remove(composant.getFilSortieByNum(i));
		}
		return result;
	}
	
	public static void remplacerPere(Polyline line1, Polyline line2) { /// remplacer le pere d'un fil par un autre fil
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : filUtilises.entrySet()) {
				for (InfoPolyline info : entry.getValue()) {
					if(info.getLineParent() == line1) {
						info.setLineParent(line2);
					}
				}
		}
	}
	
	public static void clearCircuit() { /// vider tout le contenu du circuit
		compUtilises.clear();
		entreesCircuit.clear();
		sortiesCircuit.clear();
		listeEtages.clear();
		nbEtages =0 ;
		listSouceCte.clear();
		composantsErronee.clear();
		circuitException.clear();
		filUtilises.clear();
	}

	public static void defaultValuePin() { /// affecter les valeurs par defaut aux pins
		for(Pin pin : sortiesCircuit)
			pin.defaultValue();
	}
	
	
	public static int occurencePinHorlogee() { /// calculer le nombre de pin qui servent comme horloge dans le circuit
		int i = 0;
		for (Pin pin : entreesCircuit) {
			if(pin.isHorloge()) 
				i++;
		}
		return i ;
	}
	
	public static HashMap<Fil, ArrayList<InfoPolyline>> getfilUtilises() {
		return filUtilises;
	}
	public static void creerCircuitIntegrer(String nom) {
		CircuitIntegre ci = new CircuitIntegre(entreesCircuit.size(),sortiesCircuit.size(), nom);
		ci.setTableVerite(tableVerite);
	}

	public static ArrayList<SourceConstante> getListSouceCte() {
		return listSouceCte;
	}

	public static void setListSouceCte(ArrayList<SourceConstante> listSouceCte) {
		Circuit.listSouceCte = listSouceCte;
	}
	
	public static HashMap<Fil, ArrayList<InfoPolyline>> getFilUtilises() {
		return filUtilises;
	}
	public static void setFilUtilises(HashMap<Fil, ArrayList<InfoPolyline>> arrayList) {
		Circuit.filUtilises = arrayList;
	}
	public static ArrayList<Pin> getEntreesCircuit() {
		return entreesCircuit;
	}
	public static ArrayList<Pin> getSortiesCircuit() {
		return sortiesCircuit;
	}
	public static EtatLogique[][] getTableVerite() {
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
	public static void setSortiesCircuit(ArrayList<Pin> sortiesCircuit) {
		Circuit.sortiesCircuit = sortiesCircuit;
	}
	public static void setTableVerite(EtatLogique[][] tableVerite) {
		Circuit.tableVerite = tableVerite;
	}
	
}