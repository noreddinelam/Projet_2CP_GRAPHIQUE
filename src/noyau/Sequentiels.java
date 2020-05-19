package noyau;

import java.util.ArrayList;
import java.util.Collections;

import controllers.ChronogrammeController;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public abstract class Sequentiels extends Composant implements ComposantDeChronogramme {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1243246494701808257L;
	protected Fil entreeHorloge = null;
	protected EtatLogique etatPrecHorloge;
	protected Fil clear = null;
	protected Fil load = null;
	protected Front front;
	protected ArrayList<Integer> etages = new ArrayList<Integer>();
	protected EtatLogique sortieAafficher=EtatLogique.ZERO;
	protected EtatLogique sortieBar=EtatLogique.ONE;
	protected EtatLogique etatAvant=EtatLogique.ZERO;;
	double startChronoX=1;
	double startChronoY=127;
	protected EtatLogique etatPrec[] = new EtatLogique[10];
	public Sequentiels(int nombreEntree,String nom,Front front) {
		super(nombreEntree,nom);
		this.front = front ;
		clear = new Fil(null);
		clear.setEtatLogiqueFil(EtatLogique.ONE);
	}

	public abstract void genererSortiesSyncho();

	public abstract boolean validerSyncho();

	public abstract void initialiser();

	public abstract void genererSorties();

	public abstract boolean valider();

	@Override
	public void derelierComp() { // pour derelier le composant de ces fils de commandes  (le composant à supprimer)
		// TODO Auto-generated method stub
		super.derelierComp();
		if (entreeHorloge != null) { // derelier l'horloge
			if (! entreeHorloge.getSource().equals(this)) {
				entreeHorloge.derelierCompFromDestination(this);
				ImageView imageView = Circuit.getImageFromComp(this);
				Polyline polyline = entreeHorloge.polylineParPoint(lesCoordonnees.coordReelesHorloge(imageView));
				InfoPolyline info = Circuit.getInfoPolylineFromPolyline(polyline);
				if(info != null) {
					info.setRelier(false);
				}				
			}
		}
		if (clear.getSource() != null) { // derelier le clear
			if (! clear.getSource().equals(this)) {
				clear.derelierCompFromDestination(this);
				ImageView imageView = Circuit.getImageFromComp(this);
				Polyline polyline = clear.polylineParPoint(lesCoordonnees.coordReelesClear(imageView));
				InfoPolyline info = Circuit.getInfoPolylineFromPolyline(polyline);
				if(info != null) {
					info.setRelier(false);
				}				
			}
		}

	}

	@Override
	public void derelierEntreeFromComp(Fil fil) { /// enlever toute les connexions faites avec le fil passé comme parametre
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
	public void relierANouveau() { /// relier les connexions une autre fois (utilisé dans le ctrl + z)
		// TODO Auto-generated method stub
		super.relierANouveau();
		ImageView imageView = Circuit.getImageFromComp(this);
		if (entreeHorloge != null) {
			Polyline polyline = entreeHorloge.polylineParPoint(lesCoordonnees.coordReelesHorloge(imageView));
			if (polyline == null) {
				entreeHorloge = null;
			}
			else {
				Circuit.getInfoPolylineFromPolyline(polyline).setRelier(true);
				entreeHorloge.addDestination(this);
			}
		}
		Polyline polyline = clear.polylineParPoint(lesCoordonnees.coordReelesClear(imageView));
		if (polyline == null) {
			clear = new Fil(null);
		}
		else {
			Circuit.getInfoPolylineFromPolyline(polyline).setRelier(true);
			clear.addDestination(this);
		}

	}

	@Override
	public boolean isDessocier() { /// savoir si le composant est dessocié ou non
		// TODO Auto-generated method stub
		boolean dessocier = super.isDessocier();
		if (dessocier) {
			if (clear.getSource() != null || entreeHorloge != null) {
				dessocier = false;
			}
		}
		return dessocier;
	}

	@Override
	public void addEtages(ArrayList<Integer> etage) { // ajouter l'elt sequentiel à la liste des etages selon son étage ou il se trouve
		for (Integer i : etages) {
			if(! etage.contains(i+1)) {
				etage.add(i+1);
			}
		}
	}

	@Override
	public void validerComposant() { /// valider le composant et declarer les erreurs s'il ya
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
	
	@Override
	public void defaultValue() {
		// TODO Auto-generated method stub
		super.defaultValue();
		etatPrecHorloge = EtatLogique.HAUTE_IMPEDANCE;
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

	public EtatLogique getEtatPrecHorloge() {
		return etatPrecHorloge;
	}

	public void setEtatPrecHorloge(EtatLogique etatPrecHorloge) {
		this.etatPrecHorloge = etatPrecHorloge;
	}

	public Front getFront() {
		return front;
	}

	public void setFront(Front front) {
		this.front = front;
	}

}
