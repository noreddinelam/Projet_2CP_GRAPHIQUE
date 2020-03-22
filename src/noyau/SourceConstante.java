package noyau;

public class SourceConstante extends Composant{
	
	private EtatLogique etatLogique ;

	public SourceConstante(EtatLogique etatLogique,String nom) {
		super(0,nom);
		this.nombreSortie = 1;
		this.etatLogique = etatLogique;
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(etatLogique);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void genererSorties() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean valider() {
		// TODO Auto-generated method stub
		return true;
	}

}
