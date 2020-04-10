package noyau;
import java.lang.Math;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;


public class Demultiplexeur extends Combinatoires{
	
	private int nbCommande;

	public Demultiplexeur(int nbCommande,String nom) { // constructeur
		super(1,nom);
		this.nbCommande = nbCommande;
		commande=new Fil[nbCommande];
		Double entreeDouble=Math.pow(2, nbCommande);
		nombreSortie = Integer.valueOf(entreeDouble.intValue()) ;
		lesCoordonnees = new LesCoordonnees(1, nombreSortie, nbCommande);
		initSorties();
	}

	public void genererSorties() {
		
		int sortieActif=  Integer.parseInt(concatener(commande, nbCommande),2); //concatener les commandes//covertir les commandes en un entier

		for(int i = 0 ; i < nombreSortie ; i++) {//parcourir les sorties en mettant le fil correspondant a 1 et le reste a zero
			if (i == sortieActif)
				sorties[i].setEtatLogiqueFil(entrees[0].getEtatLogiqueFil());
			else 
				sorties[i].setEtatLogiqueFil(EtatLogique.ZERO);
		}
		
	}
	public boolean valider() { // tester si le composant est pret a executer sa fonction (tester les entrees et les sorties )
		return (this.validerCommandes().getNum()==1? true : false)
				&&
				(validerEntrees().getNum()==1 
						|| 
						validerEntrees().getNum() ==0 ? true: false);
	}
	
	public EtatLogique validerCommandes() { // role : valider les commandes ( si toutes les commandes sont valides retourner 1) 
		int i =0;
		while(i<nbCommande) {
			if(commande[i] == null) // tester si les fils de sorties  sont a null 
				return null;
			if(commande[i].getEtatLogiqueFil().equals(EtatLogique.HAUTE_IMPEDANCE)) // tester si l'etat  des fils de sorties est "Haute impedance" 
				return EtatLogique.HAUTE_IMPEDANCE;
			if(commande[i].getEtatLogiqueFil().equals(EtatLogique.ERROR)) // tester s'il existe une erreur dans chaque fil 
				return EtatLogique.ERROR;
			i++;
		}
		return EtatLogique.ONE;
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, img.getBoundsInLocal().getHeight() / 2), 0);
		switch (nbCommande) {
		case 1:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
		}break;
		case 2:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 1);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			
		}break;
		case 3:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 2);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 7);
			
		}break;
		case 4:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 4);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 7);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 8);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 9);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 10);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 11);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 12);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 13);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 14);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(0, 0), 15);
			
		}break;
		}
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}
}
