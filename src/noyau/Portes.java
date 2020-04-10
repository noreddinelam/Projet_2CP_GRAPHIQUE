package noyau;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public abstract class Portes extends Composant {
	
	public Portes(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
		nombreSortie=1;
		Fil f= new Fil(this);
		sorties[0]=f;
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
	}
	
	public abstract void genererSorties();
	
	
	public boolean valider() { // valider les entrees 
		return (super.validerEntrees().getNum() == EtatLogique.ONE.getNum()) ? true: false;	
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + (String.valueOf(getNombreEntree()))+".png";
	}
	
	public void setCord() {
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(),img.getBoundsInLocal().getHeight()/2), 0) ;
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		setCord();	
		double posX = x+lesCoordonnees.getCordSortieInIndex(0).getX() ;
		double posY = y + lesCoordonnees.getCordSortieInIndex(0).getY();
		Polyline polyline = new Polyline(posX ,posY,posX+5,posY);
		Circuit.ajouterFil(sorties[0], polyline);
		return polyline;
	}
}
