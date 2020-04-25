package noyau;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class CircuitIntegre extends Composant{
	
	private  EtatLogique tableVerite[][]; // la table de verite du circuit
	private boolean sequentiel ;
	private ArrayList<Circle> listeCercles = new ArrayList<Circle>();
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
	public boolean valider() {
		// TODO Auto-generated method stub
		return (super.validerEntrees().getNum() == EtatLogique.ONE.getNum()) ? true: false;	
	}


	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		
	}


	public EtatLogique[][] getTableVerite() {
		return tableVerite;
	}


	public void setTableVerite(EtatLogique[][] tableVerite) {
		this.tableVerite = tableVerite;
	}


	public boolean isSequentiel() {
		return sequentiel;
	}


	public void setSequentiel(boolean sequentiel) {
		this.sequentiel = sequentiel;
	}


	public ArrayList<Circle> getListeCercles() {
		return listeCercles;
	}


	public void setListeCercles(ArrayList<Circle> listeCercles) {
		this.listeCercles = listeCercles;
	}
	
	public ArrayList<Circle> generateCercles(double x,double y) {
		// TODO Auto-generated method stub
		//setCord();	
		Circle circle = null;
		double posX ;
		double posY ;
		for (int i = 0; i < nombreSortie; i++) {
			posX = x+ lesCoordonnees.getCordSortieInIndex(i).getX() +5;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			circle = new Circle(5); 
			circle.setFill(Color.BLACK);
			circle.setSmooth(true);
			circle.setLayoutX(posX);
			circle.setLayoutY(posY);
			listeCercles.add(circle);
		}		
		for (int i = 0; i < nombreEntree; i++) {
			posX = x+ lesCoordonnees.getCordEntreeInIndex(i).getX() -5;
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
}
