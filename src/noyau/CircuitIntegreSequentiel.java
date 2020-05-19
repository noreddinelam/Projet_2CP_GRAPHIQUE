package noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class CircuitIntegreSequentiel extends Sequentiels implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1477243170878754006L;
	private  ArrayList<Composant> compUtilises = new ArrayList<Composant>();// tout les composants du circuit
	private  ArrayList<Fil> filUtilises = new ArrayList<Fil>();// tout les fils du circuit
	private  ArrayList<Pin> entreesCircuit = new ArrayList<Pin>(); // toutes les entrees du circuit
	private  ArrayList<Pin> sortiesCircuit = new ArrayList<Pin>(); // toutes les sorties du circuit
	private  ArrayList<Sequentiels> listeEtages = new ArrayList<Sequentiels>(); // la liste des etages pour les elts sequentiels 
	private  int nbEtages = 0; // nombre des etages	
	private  ArrayList<SourceConstante> listSouceCte = new ArrayList<SourceConstante>();//liste des sources constants
	private transient ArrayList<Circle> listeCercles = new ArrayList<Circle>();
	private Pin horloge;
	public CircuitIntegreSequentiel(String nom) {
		// TODO Auto-generated constructor stub
		super(0, nom,null);
		nombreEntree = entreesCircuit.size();
		nombreSortie = sortiesCircuit.size();
		lesCoordonnees = new LesCoordonnees(10, 10, 0);
	}
	
	@Override
	public void defaultValue() { /// affecter les valeurs par defaut aux composant et fils du circuit integré
		// TODO Auto-generated method stub
		for (Fil fil : filUtilises) {
			fil.setEtat(EtatLogique.HAUTE_IMPEDANCE);
		}
		for(Composant composant : compUtilises) {
			composant.defaultValue();
		}
	}

	@Override
	public void genererSorties() { /// generer la sortie du circuit sequentiel
		// TODO Auto-generated method stub
		initialiser();
	}
	@Override
	public ArrayList<Polyline> generatePolyline(double x, double y) { /// generer les polylines de sorties 
		// TODO Auto-generated method stub
		setCord();	
		Polyline polyline = null;
		double posX ;
		double posY ;
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> listPolylines ;
		for (int i = 0; i < nombreSortie; i++) {
			listPolylines = new ArrayList<InfoPolyline>();
			posX = x+ lesCoordonnees.getCordSortieInIndex(i).getX() ;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			polyline = new Polyline(posX ,posY,posX+14,posY);
			listPolylines.add(new InfoPolyline(polyline));
			reslut.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines); 
		}		
		return reslut;
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) { /// changer la position des polylines de sorties
		// TODO Auto-generated method stub
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x+14,y);
	}

	@Override
	public boolean valider() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String generatePath() { /// generer le chemin vers l'image
		// TODO Auto-generated method stub
		return "CircuitIntegre/HorlogeFront_Montant.png";
	}

	@Override
	public void setCord() { /// seter les coorconnes du circuit integre
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 8.2), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 23.4), 1);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 39.6), 2);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 54.6), 3);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 69.5), 4);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 85.3), 5);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 100.2), 6);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 115.2), 7);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 131.2), 8);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 146.4), 9);

		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 8.2), 0);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 23.4), 1);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 39.6), 2);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 54.6), 3);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 69.5), 4);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 85.3), 5);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 100.2), 6);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 115.2), 7);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 131.2), 8);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 146.4), 9);
		
		lesCoordonnees.setCordHorloge(new Coordonnees(42, 160));
		
	}
	
	public void refreshSortie() { /// pour mettre à jour les valeurs de sorties 
		for(int i=0;i<nombreSortie;i++) // initialiser les fils de sortie
			sorties[i].setEtatLogiqueFil(sortiesCircuit.get(i).entrees[0].getEtat());
	}
	public ArrayList<Circle> generateCercles(double x,double y) { /// generer les circles d'entrees et sorties du circuit integré
		// TODO Auto-generated method stub
		listeCercles = new ArrayList<Circle>();
		Circle circle = null;
		double posX ;
		double posY ;
		for (int i = 0; i < nombreSortie; i++) { /// generer les circles de sorties du circuit 
			posX = x + lesCoordonnees.getCordSortieInIndex(i).getX()+4;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			circle = new Circle(5); 
			circle.setFill(Color.BLACK);
			circle.setSmooth(true);
			circle.setLayoutX(posX);
			circle.setLayoutY(posY);
			listeCercles.add(circle);
		}		
		for (int i = 0; i < nombreEntree; i++) { /// generer les circles d'entrees du circuit
			posX = x + lesCoordonnees.getCordEntreeInIndex(i).getX()+5;
			posY = y + lesCoordonnees.getCordEntreeInIndex(i).getY() ;
			circle = new Circle(5);
			circle.setFill(Color.BLACK);
			circle.setSmooth(true);
			circle.setLayoutX(posX);
			circle.setLayoutY(posY);
			listeCercles.add(circle);
		}		
		return listeCercles;
	}
	
	public void resetCirclesPosition(double x,double y) { /// repositionner les circles 
		for (int i = 0; i < nombreSortie; i++) {
			listeCercles.get(i).setLayoutX( x + lesCoordonnees.getCordSortieInIndex(i).getX()+4);
			listeCercles.get(i).setLayoutY( y + lesCoordonnees.getCordSortieInIndex(i).getY());
		}
		int j = 0;
		for (int i = nombreSortie; i < nombreEntree+nombreSortie; i++) {
			listeCercles.get(i).setLayoutX(x + lesCoordonnees.getCordEntreeInIndex(j).getX()+5);
			listeCercles.get(i).setLayoutY(y + lesCoordonnees.getCordEntreeInIndex(j).getY());
			j++;
		}
	}
	public void evaluer() {
		if(valider()) // si le composant est pret 
		{
			genererSorties(); //executer sa fonction logique 
		}
		for (int i = 0; i < nombreSortie; i++) 
		{
			if(sorties[i].getEtatLogiqueFil().getNum() != sortiesCircuit.get(i).getEtat().getNum())  //verifier si l'etat precedent du composant a changé ou non 
			{
				sorties[i].setEtatLogiqueFil(sortiesCircuit.get(i).getEtat()); //mettre a jour l'etat final du composant 
				sorties[i].evaluer(); //passer au composant suivant relié au fil de sortie 
			}
		}	
	}
	
	public void  tictac() { // sert dans l'execution des composants sequentiels qui sont relier avec une horloge
		int etage = 0 ;
		while(etage <= nbEtages) { // executer par etages
			for (Sequentiels b : listeEtages) { // executer tout les composants sequentiels dans un étage donné
			
				if (b.getEtages().contains(etage) && b.validerSyncho() && b.sleep == false) { // verifier si l'elt existe dans l'etage à executer 																						  // et verifier si il peut etre executé
					b.genererSortiesSyncho(); // generer les sorties en mode synchrone
					b.sleep = true ;
					
				}
			}
			for (Sequentiels b : listeEtages) { // evaluer tout les composant executé dans l'etage present
				if (b.getEtages().contains(etage)) {
				
					b.evaluer();
				}
			}
			etage ++ ;
		}
	}

	@Override
	public void genererSortiesSyncho() { /// generer la sortie en cas ou on a un front d'horloge 
		for (int i=0;i<nombreEntree;i++) {
			entreesCircuit.get(i).evaluer();	
		}

		if(entreeHorloge.getEtat().getNum() != horloge.getEtat().getNum()) { /// verifier si on a un front de l'horloge
			horloge.setEtat(entreeHorloge.getEtatLogiqueFil());
			for(Sequentiels s : listeEtages) /// initialiser les etats precedents des fils horloge de chaque composant
				s.etatPrecHorloge = s.entreeHorloge.getEtatLogiqueFil();
			horloge.evaluer();
			tictac();
		}
	}

	@Override
	public boolean validerSyncho() { /// retourner le sleep à faux pour executer le composant au prochain front
		// TODO Auto-generated method stub
		sleep = false ;
		return true;
	}

	@Override
	public void initialiser() { /// initialiser les entrees du composant et la liste des sources constantes utillisées
		// TODO Auto-generated method stub
		
		for (int i=0;i<nombreEntree;i++) {
			entreesCircuit.get(i).setEtat(entrees[i].getEtatLogiqueFil());
		}
		horloge.evaluer();
		for (int i=0;i<listSouceCte.size();i++) {
			listSouceCte.get(i).evaluer();	
		}
	}
	
	public void initSortiesElemenets() { // faire le meme travail que initialiser du circuit
		int j = 0;
		for (Sequentiels sequentiels : listeEtages) {
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
		}
	}
	
	public void forcerInit() { /// forcer initialisation des états précédents
		for(Sequentiels s : listeEtages) /// initialiser les etats precedents des fils horloge de chaque composant
			s.etatPrecHorloge = s.entreeHorloge.getEtatLogiqueFil();
	}
	
	public void initS() {
		for(Composant cmp : compUtilises) {
			if(cmp.getClass().getSimpleName().equals("Pin")) {
				if( ((Pin)cmp).getInput()) 
					cmp.initialiserSortieParZero();
			}else if(cmp.getClass().getSimpleName().equals("SourceConstante")) {
				cmp.initialiserSortieParZero();
			}
		}
	}
	public Pin getHorloge() {
		return horloge;
	}

	public void setHorloge(Pin horloge) {
		this.horloge = horloge;
	}

	public ArrayList<Circle> getListeCercles() {
		return listeCercles;
	}

	public void setListeCercles(ArrayList<Circle> listeCercles) {
		this.listeCercles = listeCercles;
	}
	
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
		nombreEntree = entreesCircuit.size();
	}

	public ArrayList<Pin> getSortiesCircuit() {
		return sortiesCircuit;
	}

	public void setSortiesCircuit(ArrayList<Pin> sortiesCircuit) {
		this.sortiesCircuit = sortiesCircuit;
		nombreSortie = sortiesCircuit.size();
		initSorties();
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

	public ArrayList<SourceConstante> getListSouceCte() {
		return listSouceCte;
	}

	public void setListSouceCte(ArrayList<SourceConstante> listSouceCte) {
		this.listSouceCte = listSouceCte;
	}
}
