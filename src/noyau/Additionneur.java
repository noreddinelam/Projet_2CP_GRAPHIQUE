package noyau;

import java.util.ArrayList;


public abstract class Additionneur extends Combinatoires{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6160822012127544835L;
	protected EtatLogique retenueSortante = EtatLogique.ZERO;

	public Additionneur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom); 
		}
	
	public boolean valider() { // verifier si le composant est pret a executer sa fonction logique 
							   // valider si les entrees sont pretes 
		return ( validerEntrees() == EtatLogique.ONE ) ? true : false; 
	}
	
}