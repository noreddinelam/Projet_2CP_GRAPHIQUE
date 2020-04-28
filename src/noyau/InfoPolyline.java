package noyau;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class InfoPolyline implements Serializable{
	private  transient Polyline linePrincipale;
	private ArrayList<Double> noeudLinePrincipale = new ArrayList<Double>();
	private  transient Polyline lineParent = null;
	private ArrayList<Double> noeudLineParent = new ArrayList<Double>();
	private int switching = 0;
	private int nbFils = 0;
	private boolean relier = false;
	private Composant destination;
	private int entre;
	public InfoPolyline(Polyline linePrincipale) {
		this.linePrincipale = linePrincipale;
		if (linePrincipale != null) {
			this.noeudLinePrincipale.addAll(linePrincipale.getPoints());
		}
	}
	
	public InfoPolyline(Polyline linePrincipale, Polyline lineParent, int switching, int nbFils) {
		super();
		this.linePrincipale = linePrincipale;
		this.lineParent = lineParent;
		this.switching = switching;
		this.nbFils = nbFils;
		if (linePrincipale != null) {			
			this.noeudLinePrincipale.addAll(linePrincipale.getPoints());
		}
		if (lineParent != null) {
			this.noeudLineParent.addAll(lineParent.getPoints());
		}
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		InfoPolyline infoPolyline = (InfoPolyline)obj;
		return (infoPolyline.getLinePrincipale() == linePrincipale);
	}
	public Polyline getLinePrincipale() {
		return linePrincipale;
	}
	public void setLinePrincipale(Polyline linePrincipale) {
		this.linePrincipale = linePrincipale;
	}
	public Polyline getLineParent() {
		return lineParent;
	}
	public void setLineParent(Polyline lineParent) {
		this.lineParent = lineParent;
	}
	public int getSwitching() {
		return switching;
	}
	public void setSwitching(int switching) {
		this.switching = switching;
	}
	public int getNbFils() {
		return nbFils;
	}
	public void setNbFils(int nbFils) {
		this.nbFils = nbFils;
	}
	

	public void supprimerPremierNoeuds() {
		Coordonnees crdPere = new Coordonnees(0,0),crdDebt = new Coordonnees(linePrincipale.getPoints().get(0),linePrincipale.getPoints().get(1)),
					crdAvant = new Coordonnees(lineParent.getPoints().get(0),lineParent.getPoints().get(1));
		int i = 2;
		while(i < lineParent.getPoints().size()) {
			crdPere.setX(lineParent.getPoints().get(i));
			crdPere.setY(lineParent.getPoints().get(i+1));
			if(crdDebt.getX() == crdPere.getX() && crdDebt.getY() == crdPere.getY()) {
					crdAvant.setX(lineParent.getPoints().get(i-2));
					crdAvant.setY(lineParent.getPoints().get(i-1));
					if(crdAvant.semiEquals(new Coordonnees(lineParent.getPoints().get(i+4),lineParent.getPoints().get(i+5)))) {//probleme de changement des noeuds
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);
					}else {
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);
					}			
				break;
			}
			i = i + 2;	
		}
	}
	public void supprimerPremierNoeuds2() {
		Coordonnees crdPere = new Coordonnees(0,0),crdDebt = new Coordonnees(linePrincipale.getPoints().get(0),linePrincipale.getPoints().get(1)),
					crdAvant = new Coordonnees(lineParent.getPoints().get(0),lineParent.getPoints().get(1));
		int i = 2;
		while(i < lineParent.getPoints().size()) {
			crdPere.setX(lineParent.getPoints().get(i));
			crdPere.setY(lineParent.getPoints().get(i+1));
			if(crdDebt.getX() == crdPere.getX() && crdDebt.getY() == crdPere.getY()) {
					crdAvant.setX(lineParent.getPoints().get(i-2));
					crdAvant.setY(lineParent.getPoints().get(i-1));
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);
						lineParent.getPoints().remove(i);		
				break;
			}
			i = i + 2;	
		}
	}
	public boolean isRelier() {
		return relier;
	}

	public void setRelier(boolean relier) {
		this.relier = relier;
	}

	public int getEntre() {
		return entre;
	}

	public void setEntre(int entre) {
		this.entre = entre;
	}

	public Composant getDestination() {
		return destination;
	}

	public void setDestination(Composant destination) {
		this.destination = destination;
	}
	public void copierRelierInfo(InfoPolyline infoOrig) {
		this.relier =infoOrig.isRelier();
		this.destination =infoOrig.getDestination();
		this.entre = infoOrig.getEntre();
	}
	public ArrayList<Double> getNoeudLinePrincipale() {
		return noeudLinePrincipale;
	}

	public void setNoeudLinePrincipale(ArrayList<Double> noeudLinePrincipale) {
		this.noeudLinePrincipale = noeudLinePrincipale;
	}

	public ArrayList<Double> getNoeudLineParent() {
		return noeudLineParent;
	}

	public void setNoeudLineParent(ArrayList<Double> noeudLineParent) {
		this.noeudLineParent = noeudLineParent;
	}
	
	public void refrechPoints() {
		if (linePrincipale != null) {
			noeudLinePrincipale = new ArrayList<Double>(linePrincipale.getPoints());
		}
		if (lineParent != null) {
			noeudLineParent = new ArrayList<Double>(lineParent.getPoints());
		}
	}
}
