package noyau;

public class T extends Bascule{
	
	public T(String nom,Front front) {
		super(1,nom,front);
		// TODO Auto-generated constructor stub
	}

	public void genererSortiesSyncho() {
		if(etatPrec[0].getNum()==1)
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		}
		else 
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
			sorties[1].setEtatLogiqueFil(EtatLogique.ZERO);
		}
	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		etatPrec[0] = entrees[0].getEtatLogiqueFil();
	}
	
	
}
