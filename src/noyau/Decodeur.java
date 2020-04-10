package noyau;


import java.lang.Math;
import java.util.spi.LocaleServiceProvider;

import javax.swing.tree.TreePath;

import javafx.scene.shape.Polyline;
public class Decodeur extends Combinatoires{	
	
	
	public Decodeur(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
		this.nombreSortie = (int) Math.pow(2, nombreEntree);
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
		initSorties(); // creer des fils de sorties
	}

	
	public void genererSorties() { 
		String bin = concatener(entrees,nombreEntree); // concatener la valeur logique des valeurs en entree
		int s = Integer.parseInt(bin,2); // convertir le nombre binaire obtenu 
		for(int i = 0 ; i < nombreSortie ; i++) { // mettre à 1 l'etat du fil correspondant en sortie 
			if (i == s)
				sorties[i].setEtatLogiqueFil(EtatLogique.ONE);
			else 
				sorties[i].setEtatLogiqueFil(EtatLogique.ZERO);
		}
		
	}
	
	public boolean valider() { // verifier si les entrees sont toutes validées
		return (validerEntrees().getNum() == 1)  ? true : false;
	}

	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		switch (nombreEntree) {
		case 1:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
		}break;
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 7);
			
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 7);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 8);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 9);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 10);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 11);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 12);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 13);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 14);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 15);
			
		}break;
		}
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
