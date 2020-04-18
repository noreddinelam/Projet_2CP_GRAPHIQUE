package noyau;

import java.util.ArrayList;

import javafx.scene.shape.Polyline;

public abstract class Additionneur extends Combinatoires{
	
	protected EtatLogique retenueSortante = EtatLogique.ZERO;

	public Additionneur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom); 
		}
	
	public boolean valider() { // verifier si le composant est pret a executer sa fonction logique 
							   // valider si les entrees sont pretes 
		return ( validerEntrees().getNum() == 1 ) ? true : false; 
	}
}