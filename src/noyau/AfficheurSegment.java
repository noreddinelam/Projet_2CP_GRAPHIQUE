package noyau;

import javafx.scene.shape.Polyline;

public class AfficheurSegment extends Composant implements Affichage{
	
	protected int valeur; // la valeur qui sera stock� dans le registre segment 
	
	public AfficheurSegment(String nom) {
		super(4,nom);
		nombreSortie = 0;
		Circuit.ajouterSortie(this);//ajouter le composant aux elts de sorties dans circuits 
		lesCoordonnees = new LesCoordonnees(4, 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void evaluer() {
		
	}
	public void genererSorties() {
		
	}
	
	public boolean valider() {
		if (validerEntrees() == EtatLogique.ONE) { // verifier si les entrees sont toutes reli�es .
			return true;
		}
		return false;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return "AfficheurSegment/"+String.valueOf(valeur)+ ".png";
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 19.8), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 37.5), 1);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 56.6), 2);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 74.7), 3);
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}

}
