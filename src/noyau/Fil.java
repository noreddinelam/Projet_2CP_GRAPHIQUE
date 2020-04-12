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

	public Fil(Composant source) { // constructeur 
		this.source = source; 
		destination =new ArrayList<Composant>();
		//Circuit.ajouterFil(this);
	}

	public void evaluer() {  
		if(valider()) // si le fil est pret 
		{
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
	
	public boolean validerEntreeSorties() { 
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
		ArrayList<Polyline> line = Circuit.getPolylineFromFil(this);

		for (Polyline polyline : line) {
			if(etat.getNum() == 1) {
				polyline.setStroke(Color.DARKGREEN);
			}
			if(etat.getNum() == 0){
				polyline.setStroke(Color.DARKRED);
			}
		}
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

	public void addEtages(ArrayList<Integer> etage) {
		source.addEtages(etage);
	}
	
}
