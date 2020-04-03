package noyau;
import java.lang.Math;
public class Compteur extends Sequentiels{

	private int valeur = 0 ;
	private int valeurMax; 
	private Fil load = null;
	private boolean compter = true; // true -> compteur /  false ->decompteur	
	

	public Compteur(int nombreEntree,String nom,Front front) { // constructeur 
		super(nombreEntree,nom,front);
		load = new Fil(null);
		load.setEtatLogiqueFil(EtatLogique.ONE);
		this.nombreSortie = nombreEntree ;
		Double entreeDouble = Math.pow(2, nombreEntree);
		this.valeurMax = ( Integer.valueOf(entreeDouble.intValue())) -1 ;
		initSorties();
	}

	public void genererSorties() { // sert dans le mode asynchrone (juste la cmd clear) ou bien l'initialisation des entrees dans le mode synchrone
		if (clear.getEtatLogiqueFil().getNum() == 0) {// si clear est à 0
			valeur = 0;
			int bin = Integer.parseInt(Integer.toBinaryString(valeur)); // convertir la valeur du compteur en binaire
			numToFils(bin); // mettre la valeur du compteur sur les fils de sortie
		}
		else {
			initialiser(); // initialiser les etats prec pour le prochain front elle sert surtout dans le load
		}
	}
	public boolean valider() { // valider le circuit si clear est à 0 ou bien load à 0 à condition d'avoir toutes les entrees branchées 
		if (clear.getEtatLogiqueFil().getNum() == 0) {
			return true;
		}
		if (load.getEtatLogiqueFil().getNum() == 0 && validerEntrees() == EtatLogique.ONE) {
			return true;
		}
		return false;
	}

	public void compter() { //  role : incremente la valeur du compteur
		if (valeur < valeurMax) 	
			valeur++;
		else 
			valeur = 0;
	}
	public void decompter() { //  role : decremente la valeur du compteur
		if (valeur > 0)
			valeur--;
		else
			valeur = valeurMax;
	}

	@Override
	public void genererSortiesSyncho() {
		// TODO Auto-generated method stub
		if(load.getEtatLogiqueFil().getNum() == 1)
		{
			if (compter == true) // compter
			{	
				this.compter();
			}
			else // compter == false (decompteur)
			{	
				this.decompter();
			}
		}
		else { // charger la valeur des entrees dans le compteur
			valeur = Integer.parseInt(this.concatener(etatPrec, nombreEntree),2);
		}
		int bin = Integer.parseInt(Integer.toBinaryString(valeur)); // convertir la valeur du compteur en binaire
		numToFils(bin); // mettre la valeur du compteur sur les fils de sortie
	}

	@Override
	public boolean validerSyncho() { // valider le compteur soit dans le mode synchrone ou load à 0 et toutes les entrées sont toutes validées
		boolean f = false;
		if(clear.getEtatLogiqueFil()==EtatLogique.ONE) {
			if((load.getEtatLogiqueFil() == EtatLogique.ONE) || ((load.getEtatLogiqueFil() == EtatLogique.ZERO)&&(validerEntrees() == EtatLogique.ONE))) {
				
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

	@Override
	public void initialiser() { // initialiser les etats precedents qui servent si le load à 0
		// TODO Auto-generated method stub
		for (int i = 0; i < nombreEntree; i++) {
			etatPrec[i] = entrees[i].getEtatLogiqueFil();
		}
	}
	
	public Fil getLoad() {
		return load;
	}
	public void setLoad(Fil load) {
		this.load = load;
	}
	public boolean isCompter() {
		return compter;
	}
	public void setCompter(boolean compter) {
		this.compter = compter;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

}
