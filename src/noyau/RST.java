package noyau;

public class RST extends Bascule{

	public RST(String nom,Front front) {
		super(2,nom,front);
		// TODO Auto-generated constructor stub
	}

	public void genererSortiesSyncho() {
		 etatAvant=sorties[0].getEtatLogiqueFil();
		if(etatPrec[0].getNum()==1 && etatPrec[1].getNum()==0 )//R=1 S=0
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		}
		else if(etatPrec[0].getNum()==0 && etatPrec[1].getNum()==1 )//R=0 S=1
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
			sorties[1].setEtatLogiqueFil(EtatLogique.ZERO);
		}
		sortieAafficher=sorties[0].getEtatLogiqueFil();
		sortieBar=sorties[1].getEtatLogiqueFil();
	}

	@Override
	public void initialiser() { // initialiser les etats precedents
		// TODO Auto-generated method stub
		etatPrec[0] = entrees[0].getEtatLogiqueFil();
		etatPrec[1] = entrees[1].getEtatLogiqueFil();
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		super.setCord();
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 68.6), 1);
	}
	
}
