package noyau;

import java.util.ArrayList;

public abstract class Bascule extends Sequentiels{
	
	
	public Bascule(int nombreEntree,String nom,Front front) {
		super(nombreEntree,nom,front);
		preset = new Fil(null);
		preset.setEtatLogiqueFil(EtatLogique.ONE);
		nombreSortie = 2;
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		sorties[1] = new Fil(this);
		sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
	}

	protected Fil preset = null;
	
	public void genererSorties()
	{
		if(clear.getEtatLogiqueFil().getNum()==0)
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		}else
		{
			if(preset.getEtatLogiqueFil().getNum()==0)
			{
				sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
				sorties[1].setEtatLogiqueFil(EtatLogique.ZERO);
			}else
			{
				initialiser();
			}
		}
	}
	
	public boolean valider() {
		boolean f=false;
		f = (super.validerEntrees() == EtatLogique.ONE) ? true : false ;
		return f;
	}
	
	public boolean validerSyncho() {
		boolean f = false;
		if (super.validerEntrees() == EtatLogique.ONE) {
			if(preset.getEtatLogiqueFil()==EtatLogique.ONE && clear.getEtatLogiqueFil()==EtatLogique.ONE) {
				if (entreeHorloge != null) {
					switch (front) {
					case Front_Descendant:{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ZERO )
						{
							if (etatPrecHorloge == EtatLogique.ONE) {
								f = true;
							}
						}
						else {
							sleep = false ;
							etatPrecHorloge = EtatLogique.ONE;
						}
					}break;
					case Front_Montant :{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ONE )
						{
							if (etatPrecHorloge == EtatLogique.ZERO) {
								f = true;
							}
						}
						else {
							sleep = false ;
							etatPrecHorloge = EtatLogique.ZERO;
						}
					}break;
					}
				}
			}
		}
		return f;
	}
	

}
