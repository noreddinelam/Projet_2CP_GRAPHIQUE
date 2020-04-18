package noyau;

public abstract class Combinatoires extends Composant {
	protected Fil [] commande = {null};
	
	public Combinatoires(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
	}
	
	public abstract void genererSorties(); 
	public abstract boolean valider();
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + String.valueOf(getNombreEntree())+"X"+String.valueOf(getNombreSortie())+".png";
	}

	public Fil[] getCommande() {
		return commande;
	}

	public void setCommande(Fil[] commande) {
		this.commande = commande;
	}
	
}
