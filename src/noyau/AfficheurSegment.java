package noyau;

public class AfficheurSegment extends Composant implements Affichage{
	
	protected int valeur;
	
	public AfficheurSegment(int nombreEntree,String nom) {
		super(nombreEntree,nom);
		nombreSortie = 0;
		Circuit.ajouterSortie(this);
		// TODO Auto-generated constructor stub
	}
	
	public void evaluer() {
		
	}
	public void genererSorties() {
		
	}
	
	public boolean valider() {
		return false;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
