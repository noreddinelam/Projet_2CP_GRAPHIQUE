package noyau;

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
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
		}break;
		case 2:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			
		}break;
		case 3:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 7);
			
		}break;
		case 4:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 8);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 9);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 10);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 11);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 12);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 13);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 14);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 15);
			
		}break;
		}
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
