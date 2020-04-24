package noyau;

import java.util.ArrayList;
import java.util.Collections;

import controllers.ChronogrammeController;

public abstract class Sequentiels extends Composant implements ComposantDeChronogramme {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	protected Fil entreeHorloge = null;
	protected EtatLogique etatPrecHorloge;
	protected Fil clear = null;
	protected Fil load = null;
	protected Front front;
	protected ArrayList<Integer> etages = new ArrayList<Integer>();
	protected EtatLogique etatPrec[];
    protected EtatLogique sortieAafficher=EtatLogique.ONE;
    protected EtatLogique sortieBar=EtatLogique.ZERO;
	  EtatLogique etatAvant;
	double startChronoX=1;
	double startChronoY=127;
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
			if (clear.getSource() != null || entreeHorloge != null) {
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

	

	public EtatLogique getSortieAafficher() {
		return sortieAafficher;
	}

	public void setSortieAafficher(EtatLogique sortieAafficher) {
		this.sortieAafficher = sortieAafficher;
	}

	public EtatLogique getSortieBar() {
		return sortieBar;
	}

	public void setSortieBar(EtatLogique sortieBar) {
		this.sortieBar = sortieBar;
	}

	public double getStartChronoX() {
		return startChronoX;
	}

	public void setStartChronoX(double startChronoX) {
		this.startChronoX = startChronoX;
	}

	public double getStartChronoY() {
		return startChronoY;
	}

	public void setStartChronoY(double startChronoY) {
		this.startChronoY = startChronoY;
	}

	public EtatLogique[] getEtatPrec() {
		return etatPrec;
	}

	public void setEtatPrec(EtatLogique[] etatPrec) {
		this.etatPrec = etatPrec;
	}

	public EtatLogique getEtatAvant() {
		return etatAvant;
	}

	public void setEtatAvant(EtatLogique etatAvant) {
		this.etatAvant = etatAvant;
	}
	
	
   
	
}
