package noyau;

import java.util.ArrayList;
import javafx.scene.shape.Polyline;

public class InfoPolyline {
	private Polyline linePrincipale;
	private Polyline lineParent = null;
	private ArrayList<Polyline> linesFils = new ArrayList();
	private int switching = 0;
	private int nbFils = 0;
	private boolean relier = false;
	private Composant destination;
	private int entre;
	public InfoPolyline(Polyline linePrincipale) {
		this.linePrincipale = linePrincipale;
	}
	
	public InfoPolyline(Polyline linePrincipale, Polyline lineParent, int switching, int nbFils) {
		super();
		this.linePrincipale = linePrincipale;
		this.lineParent = lineParent;
		this.switching = switching;
		this.nbFils = nbFils;
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

	public ArrayList<Polyline> getLinesFils() {
		return linesFils;
	}

	public void setLinesFils(ArrayList<Polyline> linesFils) {
		this.linesFils = linesFils;
	}
	public void ajouterFils(Polyline line) {
		linesFils.add(line);
		nbFils++;
	}
	public void supprimerFils(Polyline line) {
		if(!linesFils.remove(line)) {
			nbFils--;
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
}
