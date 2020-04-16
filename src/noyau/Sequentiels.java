package noyau;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Sequentiels extends Composant {
	protected Fil entreeHorloge = null;
	protected EtatLogique etatPrecHorloge;
	protected Fil clear = null;
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
	
	@Override
	public void derelierComp() {
		// TODO Auto-generated method stub
		super.derelierComp();
		if (entreeHorloge != null) {
			entreeHorloge.derelierCompFromDestination(this);
		}
		if (clear != null) {
			clear.derelierCompFromDestination(this);
		}
		
	}
	
	@Override
	public void derelierEntreeFromComp(Fil fil) {
		// TODO Auto-generated method stub
		super.derelierEntreeFromComp(fil);
		if (entreeHorloge != null) {
			if (entreeHorloge.equals(fil))
				entreeHorloge.derelierCompFromDestination(this);
		}
		if (clear != null) {
			if (clear.equals(fil)) 
				clear.derelierCompFromDestination(this);
		}
	}
	
	@Override
	public void relierANouveau() {
		// TODO Auto-generated method stub
		super.relierANouveau();
		if (entreeHorloge != null) entreeHorloge.addDestination(this);
		if(clear != null) clear.addDestination(this);
	}
	
	@Override
	public boolean isDessocier() {
		// TODO Auto-generated method stub
		boolean dessocier = super.isDessocier();
		if (dessocier) {
			if (clear != null || entreeHorloge != null) {
				dessocier = false;
			}
		}
		return dessocier;
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
	public void addEtages(ArrayList<Integer> etage) { // ajouter l'elt sequentiel � la liste des etages selon son �tage ou il se trouve
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
	
}
