package noyau;


import java.lang.Math;
import java.util.spi.LocaleServiceProvider;

import javax.swing.tree.TreePath;
public class Decodeur extends Combinatoires{	
	
	
	public Decodeur(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
		this.nombreSortie = (int) Math.pow(2, nombreEntree);
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

	

}
