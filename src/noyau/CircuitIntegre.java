package noyau;

public class CircuitIntegre extends Composant{
	
	private  EtatLogique tableVerite[][]; // la table de verite du circuit

	public CircuitIntegre(int nombreEntree,int nombreSortie, String nom) {
		super(nombreEntree, nom);
		this.nombreSortie = nombreSortie;
		initSorties();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void genererSorties() {
		// TODO Auto-generated method stub
		String bin = concatener(entrees,nombreEntree); // concatener la valeur logique des valeurs en entree
		int s = Integer.parseInt(bin,2); // convertir le nombre binaire obtenu 
		for(int i=0;i<nombreSortie;i++) {
			sorties[i].setEtatLogiqueFil(tableVerite[s][nombreEntree+i]);
		}
	}


	@Override
	public boolean valider() {
		// TODO Auto-generated method stub
		return (super.validerEntrees().getNum() == EtatLogique.ONE.getNum()) ? true: false;	
	}


	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		
	}


	public EtatLogique[][] getTableVerite() {
		return tableVerite;
	}


	public void setTableVerite(EtatLogique[][] tableVerite) {
		this.tableVerite = tableVerite;
	}

}
