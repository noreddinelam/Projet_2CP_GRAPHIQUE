package noyau;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;


public class CircuitIntegre extends Composant implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1334102629412553408L;
	private  EtatLogique tableVerite[][]; // la table de verite du circuit integre 
	private transient ArrayList<Circle> listeCercles = new ArrayList<Circle>(); /// la liste des circles utilisées dans les entrees
	public CircuitIntegre(int nombreEntree,int nombreSortie, String nom) {
		super(nombreEntree, nom);
		this.nombreSortie = nombreSortie;
		lesCoordonnees = new LesCoordonnees(10, 10, 0);
		initSorties();
	}


	@Override
	public void genererSorties() {
		// TODO Auto-generated method stub
		String bin = concatener(entrees,nombreEntree); // concatener la valeur logique des valeurs en entree
		int s = Integer.parseInt(bin,2); // convertir le nombre binaire obtenu 
		for(int i=0;i<nombreSortie;i++) {
			sorties[i].setEtatLogiqueFil(tableVerite[s][nombreEntree+i]);
		}
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x, double y) { /// generer les polylines de sorties 
		// TODO Auto-generated method stub
		setCord();	/// seter les coordonnees d'entrees et de sorties
		Polyline polyline = null;
		double posX ;
		double posY ;
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> listPolylines ;
		for (int i = 0; i < nombreSortie; i++) { /// creer les polylines
			listPolylines = new ArrayList<InfoPolyline>();
			posX = x+ lesCoordonnees.getCordSortieInIndex(i).getX() ;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			polyline = new Polyline(posX ,posY,posX+14,posY);
			listPolylines.add(new InfoPolyline(polyline));
			reslut.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines); 
		}		
		return reslut;
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) { /// utiliser dans le mouvement des composants
		// TODO Auto-generated method stub
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x+14,y);
	}


	@Override
	public boolean valider() { /// valider le composant si toutes ses entrees sont reliées
		// TODO Auto-generated method stub
		return (super.validerEntrees().getNum() == EtatLogique.ONE.getNum()) ? true: false;	
	}


	@Override
	public String generatePath() { /// generer l'image du circuit integré
		// TODO Auto-generated method stub
		return "CircuitIntegre/Simple.png";
	}


	@Override
	public void setCord() {/// seter les coordonnees des entrees est sorties du composant
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 8.2), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 23.4), 1);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 39.6), 2);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 54.6), 3);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 69.5), 4);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 85.3), 5);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 100.2), 6);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 115.2), 7);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 131.2), 8);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(-5, 146.4), 9);
		
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 8.2), 0);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 23.4), 1);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 39.6), 2);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 54.6), 3);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 69.5), 4);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 85.3), 5);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 100.2), 6);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 115.2), 7);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 131.2), 8);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(80, 146.4), 9);
		
	}
	
	public ArrayList<Circle> generateCercles(double x,double y) { /// generer les circles 
		// TODO Auto-generated method stub
		listeCercles = new ArrayList<Circle>();
		Circle circle = null;
		double posX ;
		double posY ;
		for (int i = 0; i < nombreSortie; i++) { /// les circles de sorties
			posX = x + lesCoordonnees.getCordSortieInIndex(i).getX()+4;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			circle = new Circle(5); 
			circle.setFill(Color.BLACK);
			circle.setSmooth(true);
			circle.setLayoutX(posX);
			circle.setLayoutY(posY);
			listeCercles.add(circle);
		}		
		for (int i = 0; i < nombreEntree; i++) { /// les circles d'entrées
			posX = x + lesCoordonnees.getCordEntreeInIndex(i).getX()+5;
			posY = y + lesCoordonnees.getCordEntreeInIndex(i).getY() ;
			circle = new Circle(5);
			circle.setFill(Color.BLACK);
			circle.setSmooth(true);
			circle.setLayoutX(posX);
			circle.setLayoutY(posY);
			listeCercles.add(circle);
		}		
		return listeCercles;
	}
	
	public void resetCirclesPosition(double x,double y) { /// utilser dans le changement de position des circles
		for (int i = 0; i < nombreSortie; i++) {
			listeCercles.get(i).setLayoutX( x + lesCoordonnees.getCordSortieInIndex(i).getX()+4);
			listeCercles.get(i).setLayoutY( y + lesCoordonnees.getCordSortieInIndex(i).getY());
		}
		int j = 0;
		for (int i = nombreSortie; i < nombreEntree+nombreSortie; i++) {
			listeCercles.get(i).setLayoutX(x + lesCoordonnees.getCordEntreeInIndex(j).getX()+5);
			listeCercles.get(i).setLayoutY(y + lesCoordonnees.getCordEntreeInIndex(j).getY());
			j++;
		}
	}
	
	public EtatLogique[][] getTableVerite() {
		return tableVerite;
	}


	public void setTableVerite(EtatLogique[][] tableVerite) {
		this.tableVerite = tableVerite;
	}


	public ArrayList<Circle> getListeCercles() {
		return listeCercles;
	}


	public void setListeCercles(ArrayList<Circle> listeCercles) {
		this.listeCercles = listeCercles;
	}
}
