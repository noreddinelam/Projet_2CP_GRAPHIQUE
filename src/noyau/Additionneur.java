package noyau;

import java.util.ArrayList;

import javafx.scene.shape.Polyline;

public abstract class Additionneur extends Combinatoires{
	
	protected EtatLogique retenueSortante = EtatLogique.ZERO;

	public Additionneur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom); 
		//nombreSortie = ( nombreEntree / 2 ) + 1; // calcul du nombre de sorties 
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
		initSorties(); // creer les fils de sortie 
		}
		// TODO Auto-generated constructor stub
	
	public boolean valider() { // verifier si le composant est pret a executer sa fonction logique 
							   // valider si les entrees sont pretes 
		return ( validerEntrees().getNum() == 1 ) ? true : false; 
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
}
