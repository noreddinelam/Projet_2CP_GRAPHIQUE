package noyau;


import java.lang.Math;
import java.util.ArrayList;
import java.util.spi.LocaleServiceProvider;

import javax.swing.tree.TreePath;

import javafx.scene.shape.Polyline;
public class Decodeur extends Combinatoires{	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1808981700392692475L;

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
	public void setCord() { /// seter les coordonnées d'entrees/sorties
		// TODO Auto-generated method stub
		switch (nombreEntree) {
		case 1:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 37.3), 0);

			lesCoordonnees.setCordSortieInIndex(new Coordonnees(60, 21.4), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(60, 53.1), 1);
		}break;
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 29.7), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 50.6), 1);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(65, 14.6), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(65, 31), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(65, 46.3), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(65, 63.4), 3);
			
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 45.1), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 64), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 82.9), 2);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 14.6), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 28.7), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 43.8), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 58.6), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 74), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 87.6), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 102.7), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(87, 117.8), 7);
			
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 72.2), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 95.5), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 119.3), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 142.8), 3);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 11.2), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 24.2), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 37.4), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 50.7), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 63.6), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 76.5), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 89.5), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 102.7), 7);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 115.4), 8);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 128.6), 9);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 141.8), 10);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 154.8), 11);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 167.4), 12);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 180.3), 13);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 193.8), 14);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(101, 206.2), 15);
			
		}break;
		}
	}

}