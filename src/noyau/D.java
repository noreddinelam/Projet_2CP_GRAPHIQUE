package noyau;

public class D extends Bascule{
	
	public D(String nom,Front front) {
		super(1,nom,front);
		// TODO Auto-generated constructor stub
	}

	public void genererSortiesSyncho() { // generer la sortie de la bascule D
		this.setEtatAvant(this.getSorties()[0].getEtatLogiqueFil());
		if(etatPrec[0].getNum()==1)
		{
	
			sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
			sorties[1].setEtatLogiqueFil(EtatLogique.ZERO);
		}
		else 
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		}
		sortieAafficher=sorties[0].getEtatLogiqueFil();
		sortieBar=sorties[1].getEtatLogiqueFil();
	}

	@Override
	public void initialiser() { // initialiser l'etat precedent
		// TODO Auto-generated method stub
		etatPrec[0] = entrees[0].getEtatLogiqueFil();
	}
	
	
}
