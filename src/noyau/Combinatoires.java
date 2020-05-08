package noyau;

public abstract class Combinatoires extends Composant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6434251292315326757L;
	protected Fil commande[] = new Fil[5];
	
	public Combinatoires(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
	}
	
	public abstract void genererSorties(); 
	public abstract boolean valider();
	
	@Override
	public String generatePath() { /// generer l'image du composant combinatoire
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
