package noyau;

public abstract class Portes extends Composant {
	
	public Portes(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
		nombreSortie=1;
		Fil f= new Fil(this);
		sorties[0]=f;
	}
	
	public abstract void genererSorties();
	
	
	public boolean valider() { // valider les entrees 
		return (super.validerEntrees().getNum() == EtatLogique.ONE.getNum()) ? true: false;
		
	}
	
	
	

}
