package noyau;

public abstract class Bascule extends Sequentiels{
	
	protected Fil preset = null;
		
	public Bascule(int nombreEntree,String nom,Front front) { // constructeur
		super(nombreEntree,nom,front);
		preset = new Fil(null); // initialiser le fil preset 
		preset.setEtatLogiqueFil(EtatLogique.ONE);
		nombreSortie = 2; 
		// initialiser les etats des fils de sorties de bascules car le probleme c'est que si deux bascules sont au meme etage et relier
		// directement alors il y'aura un probleme dans l'execution .
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		sorties[1] = new Fil(this);
		sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
	}

	public void genererSorties() // executer dans le cas des cmd asynchrones
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
				initialiser(); // initialiser les etats prec des entrees pour l'execution apres dans le mode synchrone 
			}
		}
	}
	
	public boolean valider() { // valider la bascule si ces entrees sont reliées  
		boolean f=false;
		f = (super.validerEntrees() == EtatLogique.ONE) ? true : false ;
		return f;
	}
	
	public boolean validerSyncho() { // valider la bascule en mode synchrone
		boolean f = false;
		if (super.validerEntrees() == EtatLogique.ONE) { // les entrees sont valides
			if(preset.getEtatLogiqueFil()==EtatLogique.ONE && clear.getEtatLogiqueFil()==EtatLogique.ONE) { // mode synchrone
				if (entreeHorloge != null) { // l'horloge est reliée 
					switch (front) {
					case Front_Descendant:{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ZERO )
						{
							if (etatPrecHorloge == EtatLogique.ONE) { // sert pour la validation du front
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
	
	public Fil getPreset() {
		return preset;
	}

	public void setPreset(Fil preset) {
		this.preset = preset;
	}

}
