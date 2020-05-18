package noyau;
import java.lang.Math;
import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
public class Compteur extends Sequentiels{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7221135581703596717L;
	private int valeur = 0 ;
	private int valeurMax; 
	private boolean compter = true; // true -> compteur /  false ->decompteur	
	

	public Compteur(int nombreEntree,String nom,Front front) { // constructeur 
		super(nombreEntree,nom,front);
		load = new Fil(null);
		load.setEtatLogiqueFil(EtatLogique.ONE);
		this.nombreSortie = nombreEntree ;
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
		Double entreeDouble = Math.pow(2, nombreEntree);
		this.valeurMax = ( Integer.valueOf(entreeDouble.intValue())) -1 ;
		initSorties();
	}

	public void genererSorties() { // sert dans le mode asynchrone (juste la cmd clear) ou bien l'initialisation des entrees dans le mode synchrone
		if (clear.getEtatLogiqueFil().getNum() == 0) {// si clear est à 0
			valeur = 0;
			int bin = Integer.parseInt(Integer.toBinaryString(valeur)); // convertir la valeur du compteur en binaire
			numToFils(bin); // mettre la valeur du compteur sur les fils de sortie
		}
		else {
			initialiser(); // initialiser les etats prec pour le prochain front elle sert surtout dans le load
		}
	}
	public boolean valider() { // valider le circuit si clear est à 0 ou bien load à 0 à condition d'avoir toutes les entrees branchées
		if (clear.getEtatLogiqueFil().getNum() == 0) {
			return true;
		}
		else if (load.getEtatLogiqueFil().getNum() == 1) {
			return true;
		}
		if (load.getEtatLogiqueFil().getNum() == 0 && validerEntrees() == EtatLogique.ONE) {
			return true;
		}
		return false;
	}

	public void compter() { //  role : incremente la valeur du compteur
		if (valeur < valeurMax) 	
			valeur++;
		else 
			valeur = 0;
	}
	public void decompter() { //  role : decremente la valeur du compteur
		if (valeur > 0)
			valeur--;
		else
			valeur = valeurMax;
	}

	@Override
	public void genererSortiesSyncho() {
		// TODO Auto-generated method stub
		if(load.getEtatLogiqueFil().getNum() == 1)
		{
			if (compter == true) // compter
			{	
				this.compter();
			}
			else // compter == false (decompteur)
			{	
				this.decompter();
			}
		}
		else { // charger la valeur des entrees dans le compteur
			valeur = Integer.parseInt(this.concatener(etatPrec, nombreEntree),2);
		}
		int bin = Integer.parseInt(Integer.toBinaryString(valeur)); // convertir la valeur du compteur en binaire
		numToFils(bin); // mettre la valeur du compteur sur les fils de sortie
	}

	@Override
	public boolean validerSyncho() { // valider le compteur soit dans le mode synchrone ou load à 0 et toutes les entrées sont toutes validées
		boolean f = false;
		if(clear.getEtatLogiqueFil()==EtatLogique.ONE) {
			if((load.getEtatLogiqueFil() == EtatLogique.ONE) || ((load.getEtatLogiqueFil() == EtatLogique.ZERO)&&(validerEntrees() == EtatLogique.ONE))) {
				
				if (entreeHorloge != null) {
					switch (front) {
					case Front_Descendant:{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ZERO )
						{
							if (etatPrecHorloge == EtatLogique.ONE) {
								f = true;
							}
						}
						else {
							sleep = false ;
							etatPrecHorloge = EtatLogique.ONE;
						}
					}break;
					case Front_Montant :{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ONE )
						{
							if (etatPrecHorloge == EtatLogique.ZERO) {
								f = true;
							}
						}
						else {
							sleep = false ;
							etatPrecHorloge = EtatLogique.ZERO;
						}
					}break;
					}
				}
			}
		}
		return f;
	}

	@Override
	public void initialiser() { // initialiser les etats precedents qui servent si le load à 0
		// TODO Auto-generated method stub
		if (load.getEtatLogiqueFil() == EtatLogique.ZERO) {
			for (int i = 0; i < nombreEntree; i++) {
				etatPrec[i] = entrees[i].getEtatLogiqueFil();
			}
		}
	}
	
	@Override
	public String generatePath() { /// generer le chemin de l'image relative au compteur
		// TODO Auto-generated method stub
		String path = "Compteur/"+String.valueOf((int) Math.pow(2, nombreEntree)) + front.toString();
		path += (compter) ? "Compteur" : "Decompteur";
		return  path + ".png";
	}
	
	@Override
	public void setCord() { /// seter les coordonnees relative aux entrees et sorties de tout les composants
		// TODO Auto-generated method stub
		switch (nombreEntree) {
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(70.3, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(42, 0), 1);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(70.3, 55), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(42, 55), 1);
			
			lesCoordonnees.setCordHorloge(new Coordonnees(0, 20.8));
			
			lesCoordonnees.setCordLoad(new Coordonnees(119, 16.9));
			
			lesCoordonnees.setCordClear(new Coordonnees(119, 35.6));
			
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(107.5, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(78.9, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(50, 0), 2);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(107.5, 68), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(78.9, 68), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(50, 68), 2);
			
			lesCoordonnees.setCordHorloge(new Coordonnees(0, 20.2));
			
			lesCoordonnees.setCordLoad(new Coordonnees(148, 23));
			
			lesCoordonnees.setCordClear(new Coordonnees(148, 42.2));
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(140, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(108, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(76.3, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(44.5, 0), 3);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(140, 82), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(108, 82), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(76.3, 82), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(44.5, 82), 3);
			
			lesCoordonnees.setCordHorloge(new Coordonnees(0, 20.6));
			
			lesCoordonnees.setCordLoad(new Coordonnees(185, 27));
			
			lesCoordonnees.setCordClear(new Coordonnees(185, 53.8));
		}break;
		}
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) { /// repositionner les polylines de sortie
		// TODO Auto-generated method stub
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x,y+5);
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) { /// generer les polylines de sorties
		// TODO Auto-generated method stub
		setCord();	
		Polyline polyline = null;
		double posX ;
		double posY ;
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> listPolylines ;
		for (int i = 0; i < nombreSortie; i++) {
			listPolylines = new ArrayList<InfoPolyline>();
			posX = x+lesCoordonnees.getCordSortieInIndex(i).getX() ;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			polyline = new Polyline(posX ,posY,posX,posY+5);
			listPolylines.add(new InfoPolyline(polyline));
			reslut.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines); 
		}		
		return reslut;
	}
	

	@Override
	public void derelierComp() {/// pour derelier le composants de ces fils
		// TODO Auto-generated method stub
		super.derelierComp();
		if (load.getSource() != null) {
			if (! load.getSource().equals(this)) {
				load.derelierCompFromDestination(this);
				ImageView imageView = Circuit.getImageFromComp(this);
				Polyline polyline = load.polylineParPoint(lesCoordonnees.coordReelesLoad(imageView));
				InfoPolyline info = Circuit.getInfoPolylineFromPolyline(polyline);
				if(info != null) {
					info.setRelier(false);
				}				
			}
		}
	}
	
	@Override
	public void derelierEntreeFromComp(Fil fil) { /// derelier les connexions ou il y'a le fil passé comme parametre
		// TODO Auto-generated method stub
		super.derelierEntreeFromComp(fil);
		if (load.equals(fil)) {
			load = new Fil(null);
			load.setEtat(EtatLogique.ONE);
		}
	}
	
	@Override
	public void relierANouveau() { /// relier les connexions une autre fois (utiliser dans le ctrl+z)
		// TODO Auto-generated method stub
		super.relierANouveau();
		ImageView imageView = Circuit.getImageFromComp(this);
		Polyline polyline = load.polylineParPoint(lesCoordonnees.coordReelesLoad(imageView));
		if (polyline == null) {
			load= new Fil(null);
		}
		else {
			Circuit.getInfoPolylineFromPolyline(polyline).setRelier(true);
			load.addDestination(this);
		}
	}
	
	@Override
	public boolean isDessocier() { /// retourne vrai si le composant est dessocié
		// TODO Auto-generated method stub
		boolean dessocier = super.isDessocier();
		if (dessocier) {
			if (load.getSource() != null) {
				dessocier = false;
			}
		}
		return dessocier;
	}
	
	@Override
	public void defaultValue() { /// affecter les valeurs par defaut au compteur
		// TODO Auto-generated method stub
		super.defaultValue();
		valeur = 0;
	}
	
	@Override
	public void validerComposant() { /// retourne vrai si le composant est relié
		// TODO Auto-generated method stub
		if (entreeHorloge == null) {
			Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE,this));
		}
	}
	
	@Override
	public void initSorties() { /// initialiser les fils de sorties
		for(int i=0;i<nombreSortie;i++) { // initialiser les fils de sortie
			sorties[i]=new Fil(this);
			sorties[i].setEtatLogiqueFil(EtatLogique.ZERO);
		}
	}
	
	public boolean isCompter() {
		return compter;
	}
	public void setCompter(boolean compter) {
		this.compter = compter;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getValeurMax() {
		return valeurMax;
	}

	public void setValeurMax(int valeurMax) {
		this.valeurMax = valeurMax;
	}
	
	public boolean getCompter() {
		return compter;
	}

}