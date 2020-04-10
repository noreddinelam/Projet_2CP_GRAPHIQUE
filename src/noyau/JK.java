package noyau;

public class JK extends Bascule{

	
 public JK(String nom,Front front) {
		super(2,nom,front);
		// TODO Auto-generated constructor stub
	}

 public void genererSortiesSyncho() {
	 if(etatPrec[0].getNum()==1)//j=1
	 {
		 if(etatPrec[1].getNum()==1)//k=1
		 {
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
		 else //K==0
		 {
			 sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
			 sorties[1].setEtatLogiqueFil(EtatLogique.ZERO);
		 }
	 }
	 else //j=0
		 if(etatPrec[1].getNum()==1) //k==1
		 {
			 sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			 sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		 }
}

	@Override
	public void initialiser() { // initialiser les etats prec des entrees pour l'execution en mode synchrone
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
