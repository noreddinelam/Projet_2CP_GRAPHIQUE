package noyau;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.collections.ArrayListenerHelper;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;

public class Fil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1017277749047557467L;
	private Composant source = null;
	private ArrayList<Composant> destination = null;
	private EtatLogique etat = EtatLogique.HAUTE_IMPEDANCE;
	
	private int switching = 0; //utiliser pour la gestion des fils dans l'affichage

	public Fil(Composant source) { // constructeur 
		this.source = source; 
		destination =new ArrayList<Composant>();
	}

	public void evaluer() {  
		if(valider()) // si le fil est pret 
		{
			ArrayList<InfoPolyline> line = Circuit.getPolylineFromFil(this);
			if(line != null) {
				for (InfoPolyline polyline : line) {
					if(etat.getNum() == 1) {
						polyline.getLinePrincipale().setStroke(Color.LIGHTGREEN);
					}
					if(etat.getNum() == 0){
						polyline.getLinePrincipale().setStroke(Color.DARKGREEN.darker());
					}
				}
			}
			for (Composant composant : destination) // parcourir les destinations
				composant.evaluer(); // evaluer les composants de destination
		}
	}

	public boolean valider() { // tester si le fil est pret 
		if (etat == EtatLogique.ERROR || etat == EtatLogique.HAUTE_IMPEDANCE ) {
			return false;
		}
		return true;	
	}

	public void addDestination(Composant comp){ // ajouter un composant vers la liste des destinations
		if (destination.contains(comp) == false) {
			destination.add(comp);
		}
	}

	public void defaultValue() { /// affecter la valeur par defaut au fil
		etat = EtatLogique.HAUTE_IMPEDANCE;
		ArrayList<InfoPolyline> line = Circuit.getPolylineFromFil(this);

		for (InfoPolyline polyline : line) {
			polyline.getLinePrincipale().setStroke(Color.BLACK);
		}
	}
	
	public void derelierCompFromDestination(Composant composant) {///// les methodes de suppression
		destination.remove(composant);
	}
	
	public void derelierCompFromSource() {
		source = null;
	}

	public void addEtages(ArrayList<Integer> etage) {
		source.addEtages(etage);
	}
	public Polyline polylineParPoint(Coordonnees crdrech) {	/// savoir quel est le polyline qui contient le point pass� comme parametre
		Coordonnees crd = new Coordonnees(0, 0);		
		ArrayList<InfoPolyline> list = Circuit.getPolylineFromFil(this);
		if (list != null) {
			for (InfoPolyline line : list) {			
				int size = line.getLinePrincipale().getPoints().size();			
				crd.setX(line.getLinePrincipale().getPoints().get(size - 2));			
				crd.setY(line.getLinePrincipale().getPoints().get(size - 1));			
				if(crd.equals(crdrech)){				
					return line.getLinePrincipale();			
				}		
			}
		}
		return null;	
	}
	public void initialiserSortieParZero(){
		for (Composant composant : destination) 
			composant.initialiserSortieParZero(); 
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
	
	public EtatLogique getEtatLogiqueFil() {
		return this.etat;
	}

	public void setEtatLogiqueFil(EtatLogique etat) {
		this.etat = etat;
	}
	
	
}