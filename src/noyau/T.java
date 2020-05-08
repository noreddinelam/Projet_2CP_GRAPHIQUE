package noyau;

public class T extends Bascule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -602634299692305167L;

	public T(String nom,Front front) {
		super(1,nom,front);
		this.icon="T/Front_Montant.png";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void genererSortiesSyncho() { // pour generer la sortie en mode synchrone 
		this.setEtatAvant(this.getSorties()[0].getEtatLogiqueFil());
		if (etatPrec[0] == EtatLogique.ONE) {
			if(sorties[0].getEtatLogiqueFil().getNum()==1)
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
		sortieAafficher=sorties[0].getEtatLogiqueFil();
		sortieBar=sorties[1].getEtatLogiqueFil();
	}

	@Override
	public void initialiser() { /// initialiser l'etat precedent
		// TODO Auto-generated method stub
		etatPrec[0] = entrees[0].getEtatLogiqueFil();
	}
	
	
}
