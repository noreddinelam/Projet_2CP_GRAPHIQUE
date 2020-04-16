package noyau;
import java.lang.Math;
import java.util.ArrayList;

import javafx.scene.shape.Polyline;
public class Compteur extends Sequentiels{

	private int valeur = 0 ;
	private int valeurMax; 
	private Fil load = null;
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
	
	public boolean getCompter() {
		return compter;
	}

	public void genererSorties() { // sert dans le mode asynchrone (juste la cmd clear) ou bien l'initialisation des entrees dans le mode synchrone
		if (clear.getEtatLogiqueFil().getNum() == 0) {// si clear est � 0
			valeur = 0;
			int bin = Integer.parseInt(Integer.toBinaryString(valeur)); // convertir la valeur du compteur en binaire
			numToFils(bin); // mettre la valeur du compteur sur les fils de sortie
		}
		else {
			initialiser(); // initialiser les etats prec pour le prochain front elle sert surtout dans le load
		}
	}
	public boolean valider() { // valider le circuit si clear est � 0 ou bien load � 0 � condition d'avoir toutes les entrees branch�es 
		if (clear.getEtatLogiqueFil().getNum() == 0) {
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
	public boolean validerSyncho() { // valider le compteur soit dans le mode synchrone ou load � 0 et toutes les entr�es sont toutes valid�es
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
	public void initialiser() { // initialiser les etats precedents qui servent si le load � 0
		// TODO Auto-generated method stub
		for (int i = 0; i < nombreEntree; i++) {
			etatPrec[i] = entrees[i].getEtatLogiqueFil();
		}
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return "Compteur/"+String.valueOf((int) Math.pow(2, nombreEntree)) + front.toString() + ".png";
	}
	
	@Override
	public void setCord() {
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
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) {
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
	public void derelierComp() {
		// TODO Auto-generated method stub
		super.derelierComp();
		if (load != null) {
			load.derelierCompFromDestination(this);
		}
	}
	
	public Fil getLoad() {
		return load;
	}
	public void setLoad(Fil load) {
		this.load = load;
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

}
