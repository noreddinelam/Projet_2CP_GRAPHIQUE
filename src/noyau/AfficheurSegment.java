package noyau;

public class AfficheurSegment extends Composant implements Affichage{
	
	protected int valeur; // la valeur qui sera stocké dans le registre segment 
	
	public AfficheurSegment(int nombreEntree,String nom) {
		super(nombreEntree,nom);
		nombreSortie = 0;
		Circuit.ajouterSortie(this);//ajouter le composant aux elts de sorties dans circuits 
		// TODO Auto-generated constructor stub
	}
	
	public void evaluer() {
		
	}
	public void genererSorties() {
		
	}
	
	public boolean valider() {
		if (validerEntrees() == EtatLogique.ONE) { // verifier si les entrees sont toutes reliées .
			return true;
		}
		return false;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
