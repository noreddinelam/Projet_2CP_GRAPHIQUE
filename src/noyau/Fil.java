package noyau;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.collections.ArrayListenerHelper;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;

public class Fil implements Serializable{
	private Composant source = null;
	private ArrayList<Composant> destination = null;
	private EtatLogique etat = EtatLogique.HAUTE_IMPEDANCE;
	
	private int switching = 0; //utiliser pour la gestion des fils 

	public Fil(Composant source) { // constructeur 
		this.source = source; 
		destination =new ArrayList<Composant>();
		//Circuit.ajouterFil(this);
	}

	public void evaluer() {  
		if(valider()) // si le fil est pret 
		{
			ArrayList<InfoPolyline> line = Circuit.getPolylineFromFil(this);
			for (InfoPolyline polyline : line) {
				if(etat.getNum() == 1) {
					polyline.getLinePrincipale().setStroke(Color.DARKGREEN);
				}
				if(etat.getNum() == 0){
					polyline.getLinePrincipale().setStroke(Color.DARKRED);
				}
			}
			for (Composant composant : destination) // parcourir les destinations
				composant.evaluer(); // evaluer les composants de destination
		}else
		{

		}
	}

	public boolean valider() { // tester si le fil est pret 
		return (this.sortieReinjecter())&&(this.validerEntreeSorties()) // tester si le fil est reinjecté en entree /sortie injectée en entree
				&&
				(this.getEtatLogiqueFil().getNum() == EtatLogique.ERROR.getNum() // tester l'etat ERREUR
						|| 
						this.getEtatLogiqueFil().getNum() == EtatLogique.HAUTE_IMPEDANCE.getNum() ? false: true); // tester L'etat HAUTE_IMPEDANCE
		
	}
	
	public boolean validerEntreeSorties() { // verifier si une on a relier deux composants sortie avec sortie .
		for(Composant c: this.destination) {
			for(Fil f : c.sorties) {
				if(this.equals(f))
					return false;
			}
		}
		return true;
	}
	
	public boolean sortieReinjecter() { //verifier si une sortie est reinjectée en entree 
		for(Fil f : this.source.entrees)
			if(this.equals(f))
				return false;
		
		return true;
	}
	
	public void addDestination(Composant comp){ // ajouter un composant vers la liste des destinations
		if (destination.contains(comp) == false) {
			destination.add(comp);
		}
	}

	public EtatLogique getEtatLogiqueFil() {
		return this.etat;
	}

	public void setEtatLogiqueFil(EtatLogique etat) {
		this.etat = etat;
	}
	
	public void defaultValue() {
		etat = EtatLogique.HAUTE_IMPEDANCE;
		ArrayList<InfoPolyline> line = Circuit.getPolylineFromFil(this);

		for (InfoPolyline polyline : line) {
			polyline.getLinePrincipale().setStroke(Color.BLACK);
			}
		}
	///// les methodes de suppression
	public void derelierCompFromDestination(Composant composant) {
		destination.remove(composant);
	}
	
	public void derelierCompFromSource() {
		source = null;
	}
	//////////////////////

	public Composant getSource() {
		return source;
	}

	public void setSource(Composant source) {
		this.source = source;
	}

	public ArrayList<Composant> getDestination() {
		return destination;
	}

	public void setDestination(ArrayList<Composant> destination) {
		this.destination = destination;
	}

	public void addEtages(ArrayList<Integer> etage) {
		source.addEtages(etage);
	}
	public Polyline polylineParPoint(Coordonnees crdrech) {
		Coordonnees crd = new Coordonnees(0, 0);
		ArrayList<InfoPolyline> list = Circuit.getPolylineFromFil(this);
		for (InfoPolyline line : list) {
			int i = 0;
			while(i < line.getLinePrincipale().getPoints().size()) {
				crd.setX(line.getLinePrincipale().getPoints().get(i));
				crd.setY(line.getLinePrincipale().getPoints().get(i+1));
				if(crd.equals(crdrech)){
					return line.getLinePrincipale();
				}
				i=i+2;
			}
		}
		return null;
	}

	public EtatLogique getEtat() {
		return etat;
	}

	public void setEtat(EtatLogique etat) {
		this.etat = etat;
	}

	public int getSwitching() {
		return switching;
	}

	public void setSwitching(int switching) {
		this.switching = switching;
	}
	
}
