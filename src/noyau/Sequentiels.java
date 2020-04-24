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
	protected EtatLogique etatPrec[] = new EtatLogique[10];
	
	public Sequentiels(int nombreEntree,String nom,Front front) {
		super(nombreEntree,nom);
		this.front = front ;
		clear = new Fil(null);
		clear.setEtatLogiqueFil(EtatLogique.ONE);
	
	}
	
	@Override
	public void derelierComp() {
		// TODO Auto-generated method stub
		super.derelierComp();
		if (entreeHorloge != null) {
			if (! entreeHorloge.getSource().equals(this)) {
				entreeHorloge.derelierCompFromDestination(this);
			}
		}
		if (clear.getSource() != null) {
			if (! clear.getSource().equals(this)) {
				clear.derelierCompFromDestination(this);
			}
		}
		
	}
	
	@Override
	public void derelierEntreeFromComp(Fil fil) {
		// TODO Auto-generated method stub
		super.derelierEntreeFromComp(fil);
		if (entreeHorloge != null) {
			if (entreeHorloge.equals(fil))
				entreeHorloge = null;
		}
		if (clear.equals(fil)) { 
			clear = new Fil(null);
			clear.setEtat(EtatLogique.ONE);
		}
	}
	
	@Override
	public void relierANouveau() {
		// TODO Auto-generated method stub
		super.relierANouveau();
		if (entreeHorloge != null) entreeHorloge.addDestination(this);
		clear.addDestination(this);
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
	
	@Override
	public void validerComposant() {
		// TODO Auto-generated method stub
		ArrayList<ExceptionProgramme> arrayList = new ArrayList<ExceptionProgramme>();
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] == null) {
				arrayList.add(new EntreeManquante(TypesExceptions.ERREUR, this, i));
			}
		}
		if (arrayList.size() == nombreEntree) {
			if (entreeHorloge != null) {
				Circuit.AjouterListeException(arrayList);
				Circuit.ajouterCompErrone(this);
			}
			else {
				Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE, this));
			}
			
		}
		else {
			if (entreeHorloge == null) {
				arrayList.add(new HorlogeManquante(TypesExceptions.ERREUR, this));
				Circuit.ajouterCompErrone(this);
			}
			else if(arrayList.size() != 0) {
				Circuit.ajouterCompErrone(this);
			}
			Circuit.AjouterListeException(arrayList);	
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
