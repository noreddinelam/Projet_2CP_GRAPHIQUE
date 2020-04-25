package noyau;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class CircuitIntegre extends Composant{
	
	private  EtatLogique tableVerite[][]; // la table de verite du circuit
	private boolean sequeniel;
	private ArrayList<Circle> arrayList = new ArrayList<Circle>();
	private Front front;
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
		if (sequeniel) {
			return "Horloge"+front+".png";
		}
		return "Simple.png";
	}


	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 8.2), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 23.4), 1);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 39.6), 2);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 54.6), 3);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 69.5), 4);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 85.3), 5);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 100.2), 6);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 115.2), 7);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 131.2), 8);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 146.4), 9);
		
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 8.2), 0);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 23.4), 1);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 39.6), 2);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 54.6), 3);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 69.5), 4);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 85.3), 5);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 100.2), 6);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 115.2), 7);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 131.2), 8);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(98, 146.4), 9);
		
		if (sequeniel) {
			lesCoordonnees.setCordHorloge(new Coordonnees(48.7, 163));
		}
		
	}


	public EtatLogique[][] getTableVerite() {
		return tableVerite;
	}


	public void setTableVerite(EtatLogique[][] tableVerite) {
		this.tableVerite = tableVerite;
	}

}
