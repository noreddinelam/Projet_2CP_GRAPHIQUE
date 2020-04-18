package noyau;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Sequentiels extends Composant {
	protected Fil entreeHorloge = null;
	protected EtatLogique etatPrecHorloge;
	protected Fil clear = null;
	protected Fil load = null;
	protected Front front;
	protected ArrayList<Integer> etages = new ArrayList<Integer>();
	protected EtatLogique etatPrec[];
	
	public Sequentiels(int nombreEntree,String nom,Front front) {
		super(nombreEntree,nom);
		etatPrec = new EtatLogique[nombreEntree];
		this.front = front ;
		clear = new Fil(null);
		clear.setEtatLogiqueFil(EtatLogique.ONE);
		
		// EN CAS D'ERREUR INATENDUE :
//		for (int i = 0; i < nombreEntree; i++) {
//			etatPrec[i] = EtatLogique.ZERO;
//		}
	}
	
	
	public Front getFront() {
		return front;
	}


	public void setFront(Front front) {
		this.front = front;
	}


	public abstract void genererSortiesSyncho();
	
	public abstract boolean validerSyncho();
	
	public abstract void initialiser();
	
	/*--------- Methodes --------------*/
	public abstract void genererSorties();
	public abstract boolean valider();
	
	@Override
	public void addEtages(ArrayList<Integer> etage) { // ajouter l'elt sequentiel à la liste des etages selon son étage ou il se trouve
		for (Integer i : etages) {
			if(! etage.contains(i+1)) {
				etage.add(i+1);
			}
		}
	}
	
	public ArrayList<Integer> getEtages() {
		return etages;
	}

	public void setEtages(ArrayList<Integer> etages) {
		this.etages = etages;
	}


	public Fil getClear() {
		return clear;
	}


	public void setClear(Fil clear) {
		this.clear = clear;
	}


	public Fil getEntreeHorloge() {
		return entreeHorloge;
	}


	public void setEntreeHorloge(Fil entreeHorloge) {
		this.entreeHorloge = entreeHorloge;
	}


	public Fil getLoad() {
		return load;
	}


	public void setLoad(Fil load) {
		this.load = load;
	}
	
}
