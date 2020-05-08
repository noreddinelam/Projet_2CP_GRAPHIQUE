package noyau;

public class RST extends Bascule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2760611765665795164L;

	public RST(String nom,Front front) {
		super(2,nom,front);
		this.icon="RST/Front_Montant.png";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void genererSortiesSyncho() {  // pour generer la sortie en mode synchrone 
		this.setEtatAvant(this.getSorties()[0].getEtatLogiqueFil());
       
		if(etatPrec[0].getNum()==0 && etatPrec[1].getNum()==1 )//R=1 S=0

		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		}
		else if(etatPrec[0].getNum()==1 && etatPrec[1].getNum()==0 )//R=0 S=1
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
	public void setCord() { /// seter les coordonnees nécessaires
		// TODO Auto-generated method stub
		super.setCord();
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 68.6), 1);
	}
	
}
