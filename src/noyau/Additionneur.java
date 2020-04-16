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
			polyline = new Polyline(posX ,posY,posX+5,posY);
			listPolylines.add(new InfoPolyline(polyline));
			reslut.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines); 
		}		
		return reslut;
	}
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
}
