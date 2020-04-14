package noyau;

import javafx.scene.shape.Polyline;

public class InfoPolyline {
	private Polyline linePrincipale;
	private Polyline lineParent;
	private int switching = 0;
	private int nbFils = 0;
	public InfoPolyline(Polyline linePrincipale) {
		this.linePrincipale = linePrincipale;
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
	
	
	
}
