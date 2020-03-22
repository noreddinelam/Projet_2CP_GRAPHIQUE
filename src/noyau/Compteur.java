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

	public void genererSorties() { // role : executer une des fonctions du compteur (compter,decompter,remise a zero ou chargement) 
								   //        et mettre le resultat dans les fils de sortie
		if (!(this.clear.getEtatLogiqueFil().getNum() == 0))  // clear==1
		{
			if  (!(this.load.getEtatLogiqueFil().getNum() == 0)) // load == 1
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
			else // load == zero
			{
				valeur = Integer.parseInt(this.concatener(entrees, nombreEntree),2);// recuperer la valeur d'entree 
			}
		}
		else // clear == zero
		{
			valeur = 0;
		}
	
		int bin = Integer.parseInt(Integer.toBinaryString(valeur)); // convertir la valeur du compteur en binaire
		numToFils(bin); // mettre la valeur du compteur sur les fils de sortie

	}
	public boolean valider() {
		if (clear.getEtatLogiqueFil().getNum() == 1 && load.getEtatLogiqueFil().getNum() == 1) {// mode synchrone
			if(entreeHorloge != null) { // horloge reliée 
					switch (front) { // verification de l'etat de l'horloge .
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
		}
		else if(load.getEtatLogiqueFil().getNum() == 0 && clear.getEtatLogiqueFil().getNum() == 1){ // mode asynchrone 
			if(validerEntrees().getNum() == 1) return true; //verification de toutes les entrees .
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

	@Override
	public void genererSortiesSyncho() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validerSyncho() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
