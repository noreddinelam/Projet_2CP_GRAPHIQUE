package noyau;

public abstract class Combinatoires extends Composant {
	protected Fil [] commande = {null};
	
	public Combinatoires(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
	}
	
	public abstract void genererSorties(); 
	public abstract boolean valider();
	

}
