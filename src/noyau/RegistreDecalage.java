package noyau;

import java.util.Arrays;

public class RegistreDecalage extends Sequentiels {
	
	private int taille;
	private EtatLogique[] valeur;
	private boolean decalageDroite;
	private Fil load ;
	public RegistreDecalage(int taille,String nom,boolean dec,Front front) { // constructeur 
		super(taille+1,nom,front);
		valeur = new EtatLogique[taille];
		load = new Fil(null);
		load.setEtatLogiqueFil(EtatLogique.ONE);
		this.nombreSortie = 1;
		this.decalageDroite = dec ;
		this.taille = taille ;
		Arrays.fill(valeur, EtatLogique.ZERO);
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(EtatLogique.HAUTE_IMPEDANCE);
	}
	
	public void genererSorties() { // executer une des fonction du registre ( decalage a droite , remise a zero )
		if(this.clear.getEtatLogiqueFil().getNum()==0)
		{
			Arrays.fill(valeur, EtatLogique.ZERO);//initialiser le tableau avec ZERO
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		}
		else {
			initialiser();
		}
			
	}
	
	public void decalageDroite() {
		EtatLogique bit = entrees[0].getEtatLogiqueFil();
		EtatLogique res = valeur[taille-1]; // recuperer le bit de sortie a partir du bit du  poids faible 
		
		for (int i=taille - 1 ; i> 0  ;i--)
		{
			valeur[i] = valeur[i-1]; // decaler les bits a droite 
		}
		valeur[0] = bit ; // decaler le bit d'entree vers le poids faible du registre
		sorties[0].setEtatLogiqueFil(res);// mettre la valeur du dernier bit (bit faible) dans le fil .
	}
	
	public void decalageGauche() {
		EtatLogique bit = entrees[0].getEtatLogiqueFil();
		EtatLogique res = valeur[0]; // recuperer le bit de sortie a partir du bit du  poids fort 
		
		for (int i=0 ; i<taille-1  ;i++)
		{
			valeur[i] = valeur[i+1]; // decaler les bits a gauche 
		}
		valeur[taille-1] = bit ; // decaler le bit d'entree vers le poids fort du registre
		sorties[0].setEtatLogiqueFil(res);// mettre la valeur du dernier bit (bit fort) dans le fil .
	}
	
	@Override
	public EtatLogique validerEntrees() {
		// TODO Auto-generated method stub
		if(entrees[0] == null) // verifier si  l'entree du composants est reliée a un autre composant 
			return null;
		if(entrees[0].getEtatLogiqueFil().getNum() == EtatLogique.HAUTE_IMPEDANCE.getNum()) //  verifier si le fil d'entree est en haute impedence . 
			return EtatLogique.HAUTE_IMPEDANCE;
		if(entrees[0].getEtatLogiqueFil().getNum() == EtatLogique.ERROR.getNum()) // verifier si le fil d'entree contient une erreur .
			return EtatLogique.ERROR;
		return EtatLogique.ONE;
	}

	public boolean valider() {
		/*if (clear.getEtatLogiqueFil().getNum() == 1 && load.getEtatLogiqueFil().getNum() == 1) { // registre à decalge en mode synchrone
			if(validerEntrees().getNum() == 1 && entreeHorloge != null) // verifier si les entrees sont valides "valideEntrees"
																		// verification de l'horloge à revoir .
				switch (front) {
				case Front_Descendant:{
					if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ZERO )
						return true;
				}break;
				case Front_Montant :{
					if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ONE )
						return true;
				}break;
				}
		}
		else if (super.validerEntrees().getNum() == 1) { // mode asynchrone .
			return true;
		}
		return false;*/
		if (clear.getEtatLogiqueFil().getNum() == 0) {
			return true;
		}
		if (load.getEtatLogiqueFil().getNum() == 0 && super.validerEntrees() == EtatLogique.ONE) {
			return true;
		}
		return false;
	}
	public int valeur() { // Role : recuperer la valeur contenue dans le registre 
		int res = 0 ;
		int p = 1 ;
		for (int i = taille -1; i >= 0; i--) 
		{
			res = res + valeur[i].getNum() * p  ;
			p *= 10 ;
		}
		return res ;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public void setValeur(EtatLogique[] valeur) {
		this.valeur = valeur;
	}

	public void setDecalageDroite(boolean decalageDroite) {
		this.decalageDroite = decalageDroite;
	}

	public void setLoad(Fil load) {
		this.load = load;
	}

	@Override
	public void genererSortiesSyncho() {
		// TODO Auto-generated method stub
		if (this.load.getEtatLogiqueFil().getNum() == 0) {
			for (int i = 1; i < nombreEntree; i++) {
				valeur[i-1] = entrees[i].getEtatLogiqueFil();
			}
			sorties[0].setEtatLogiqueFil(valeur[taille-1]);
		}
		else {
			if (decalageDroite) { // decalage droite
				decalageDroite();
			}
			else { //decalage gauche 
				decalageGauche();
			}
		}
	}

	@Override
	public boolean validerSyncho() {
		// TODO Auto-generated method stub
		boolean f = false;
		if (clear.getEtatLogiqueFil().getNum() == 1) { // registre à decalge en mode synchrone
			// verifier si les entrees sont valides "valideEntrees"
			if((load.getEtatLogiqueFil().getNum() == 1 && validerEntrees().getNum()== 1)||(load.getEtatLogiqueFil().getNum() == 0 && super.validerEntrees() == EtatLogique.ONE))// verification de l'horloge à revoir .
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
		return f;
	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		for (int i = 1; i < nombreEntree; i++) {
			etatPrec[i] = entrees[i].getEtatLogiqueFil();
		}
	}

	
}
