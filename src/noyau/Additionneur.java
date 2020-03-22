package noyau;

public abstract class Additionneur extends Combinatoires{
	
	protected EtatLogique retenueSortante = EtatLogique.ZERO;

	public Additionneur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom); 
		nombreSortie = ( nombreEntree / 2 ) + 1;
		initSorties();
		}
		// TODO Auto-generated constructor stub
	
	public boolean valider() { // verifier si le composant est pret a executer sa fonction logique 
							   // valider si les entrees sont pretes 
		return ( validerEntrees().getNum() == 1 ) ? true : false; 
	}
}
