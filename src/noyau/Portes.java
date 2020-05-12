package noyau;

import java.util.ArrayList;

import com.sun.glass.ui.Size;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public abstract class Portes extends Composant {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439201796119744528L;

	public Portes(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
		nombreSortie=1;
		Fil f= new Fil(this);
		sorties[0]=f;
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
	}
	
	public abstract void genererSorties();
	
	public boolean valider() { // valider les entrees 
		return (super.validerEntrees() == EtatLogique.ONE) ? true: false;	
	}
	
	public void validerComposant() { /// pour valider la porte si toutes les entrees sont relier et declarer les erreurs s'il yz
		// TODO Auto-generated method stub
		ArrayList<ExceptionProgramme> arrayList = new ArrayList<ExceptionProgramme>();
		int j=0,k = 0;
		for (int i = 0; i < nombreEntree; i++) { 
			if (entrees[i] == null) {
				arrayList.add(new EntreeManquante(TypesExceptions.ERREUR, this, i));
				j++;
			}
			else if(entrees[i].equals(sorties[0])) {
				arrayList.add(new SortieReinjecter(TypesExceptions.ALERTE, this, i, 0));
				k++;
			}
		}
		if (j == nombreEntree) {
			Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE, this));
		}
		else {
			if (arrayList.size() != 0 && k == 0) {
				Circuit.ajouterCompErrone(this);
			}
			Circuit.AjouterListeException(arrayList);
		}
	}
	
	@Override
	public String generatePath() {/// generer l'image relative au pin
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + (String.valueOf(getNombreEntree()))+Integer.toString(direction)+".png";
	}
	
	public void setCord() { /// seter les coordonnées nécessaires
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(),img.getBoundsInLocal().getHeight()/2), 0) ;
	}
	
	public void rotation(int direc) { /// pour faire une rotation de la porte
		ImageView imageView = Circuit.getImageFromComp(this);
		switch (direc) {
		case 1:
			lesCoordonnees.rotationXY(imageView);
			break;
		case 2:
			lesCoordonnees.rotationXX();
			break;
		case 3:
			lesCoordonnees.rotationXY(imageView);
			lesCoordonnees.rotationYY();
			break;
		}
	}
	public void resetPolyline(Polyline line , double x,double y) { /// repositionner les polylines de sorties selon la direction
		line.getPoints().clear();
		switch (direction) {
		case 0:
			line.getPoints().addAll(x,y,x+5,y);
			break;
		case 1:
			line.getPoints().addAll(x,y,x,y+5);
			break;
		case 2:
			line.getPoints().addAll(x,y,x-5,y);
			break;
		case 3:
			line.getPoints().addAll(x,y,x,y-5);
			break;
			default:
				break;
		}
	}
	
	public ArrayList<Polyline> generatePolyline(double x,double y) { /// generer les polylines de sorties selon la direction du composant
		// TODO Auto-generated method stub
		setCord();	
		Polyline polyline = null;
		double posX ;
		double posY ;
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> listPolylines ;
		for (int i = 0; i < nombreSortie; i++) {
			listPolylines = new ArrayList<InfoPolyline>();
			posX = x+ lesCoordonnees.getCordSortieInIndex(i).getX() ;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			switch (direction) {
			case 0:
				polyline = new Polyline(posX ,posY,posX+5,posY);
				break;
			case 1:
				polyline = new Polyline(posX ,posY,posX,posY+5);
				break;
			case 2:
				polyline = new Polyline(posX ,posY,posX-5,posY);
				break;
			case 3:
				polyline = new Polyline(posX ,posY,posX,posY-5);
				break;
				default:
					break;
			}
			
			polyline.toBack();
			listPolylines.add(new InfoPolyline(polyline));
			reslut.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines);;
		}		
		return reslut;
	}
	
}