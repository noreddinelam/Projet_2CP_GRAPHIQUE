package noyau;

import java.util.ArrayList;

import javafx.scene.shape.Polyline;

public class Encodeur extends Combinatoires {
	
	public Encodeur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom);	
		nombreSortie = Integer.toBinaryString(nombreEntree-1).length();
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
		initSorties();
		// TODO Auto-generated constructor stub
	}
	public void genererSorties() {
		int i = 0;
		while ((i < nombreEntree )&&(entrees[i].getEtatLogiqueFil() != EtatLogique.ONE)) // recuperer le premier un dans les entrées
			i++;
		int res = Integer.parseInt(Integer.toBinaryString(i));
		numToFils(res); // distribuer la valeur resultante dans les fils de sorties
	}
	
	public boolean valider() { // verifier si les entrees du composant sont toutes reliées
		return (validerEntrees().getNum() == 1) ? true : false;
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		switch (nombreSortie) {
		case 1:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(60, 37.3), 0);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 21.4), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 53.1), 1);
		}break;
		case 2:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(65, 29.7), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(65, 50.6), 1);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 14.6), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 31), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 46.3), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 63.4), 3);
			
		}break;
		case 3:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 45.1), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 64), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 82.9), 2);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 14.6), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 28.7), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 43.8), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 58.6), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 74), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 87.6), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 102.7), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 117.8), 7);
			
		}break;
		case 4:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 72.2), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 95.5), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 119.3), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 142.8), 3);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 11.2), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 24.2), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 37.4), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 50.7), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 63.6), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 76.5), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 89.5), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 102.7), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 115.4), 8);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 128.6), 9);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 141.8), 10);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 154.8), 11);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 167.4), 12);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 180.3), 13);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 182), 14);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 195.6), 15);
			
		}break;
		}
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
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x+5,y);
	}
	

}