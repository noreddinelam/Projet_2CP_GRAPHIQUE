package noyau;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Sequentiels extends Composant {
	protected Fil entreeHorloge = null;
	protected EtatLogique etatPrecHorloge;
	protected Fil clear = null;
	protected Front front;
	protected ArrayList<Integer> etages = new ArrayList<Integer>();
	protected EtatLogique etatPrec[] = new EtatLogique[32];
	
	public Sequentiels(int nombreEntree,String nom,Front front) {
		super(nombreEntree,nom);
		this.front = front ;
		clear = new Fil(null);
		clear.setEtatLogiqueFil(EtatLogique.ONE);
		
		// EN CAS D'ERREUR INATENDUE :
//		for (int i = 0; i < nombreEntree; i++) {
//			etatPrec[i] = EtatLogique.ZERO;
//		}
	}
	
	public abstract void genererSortiesSyncho();
	
	public abstract boolean validerSyncho();
	
	public abstract void initialiser();
	
	/*--------- Methodes --------------*/
	public abstract void genererSorties();
	public abstract boolean valider();
	
	public ArrayList<Integer> getEtages() {
		return etages;
	}

	public void setEtages(ArrayList<Integer> etages) {
		this.etages = etages;
	}
	
	@Override
	public void addEtages(ArrayList<Integer> etage) {
		for (Integer i : etages) {
			if(! etage.contains(i+1)) {
				etage.add(i+1);
			}
		}
	}
}
