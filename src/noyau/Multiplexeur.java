package noyau;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Multiplexeur extends Combinatoires {
	
	private int nbCommande;
	
	public Multiplexeur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom);//initialiser le nombre d'entrees 
		// TODO Auto-generated constructor stub
		
		this.nbCommande = Integer.toBinaryString(nombreEntree-1).length();//initialiser le nombre de commandes
		commande=new Fil[this.nbCommande];
		nombreSortie = 1;//le multiplexeur possede une seule sortie
		Fil fil = new Fil(this);
		sorties[0]=fil;
		lesCoordonnees = new LesCoordonnees(nombreEntree, 1, nbCommande);
	}
	
	
	
	public void genererSorties() { // role executer le fonction du composant 
		
		int numeroDeEntreeActif= Integer.parseInt(concatener(commande, this.nbCommande),2); //determiner le numero de sortie activee
		sorties[0].setEtatLogiqueFil(entrees[numeroDeEntreeActif].getEtatLogiqueFil());		
		
	}
	public boolean valider() { // tester si le composant est pret 
		return (this.validerCommandes().equals(EtatLogique.ONE)? true : false) // tester si les commandes sont valides
				&&
				(validerEntrees().equals(EtatLogique.ONE)  // tester si les entrees sont valides 
						|| 
						validerEntrees().equals(EtatLogique.ZERO) ? true: false); // tester si LessExpression sorties sont valides 
		
		
	}
	
	public EtatLogique validerCommandes() { // Role : valider les commandes ( si toutes les commandes sont valides retourner 1) 
		int i =0;
		while(i<nbCommande) {
			if(commande[i] == null) // tester si les fils sont a null 
				return null;
			if(commande[i].getEtatLogiqueFil().equals(EtatLogique.HAUTE_IMPEDANCE)) //tester  l'etat logique HAUTE IMPEDANCE 
				return EtatLogique.HAUTE_IMPEDANCE;
			if(commande[i].getEtatLogiqueFil().equals(EtatLogique.ERROR))  //tester  l'etat logique ERRR 
				return EtatLogique.ERROR;
			i++;
		}
		return EtatLogique.ONE;
	}



	public int getNbCommande() {
		return nbCommande;
	}



	public void setNbCommande(int nbCommande) {
		this.nbCommande = nbCommande;
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(), (img.getBoundsInLocal().getHeight()-10) / 2), 0);
		switch (nbCommande) {
		case 1:{			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 16.8), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 48.1), 1);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(25.1, 75), 0);
		}break;
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 14.9), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 31.3), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 48.1), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 65), 3);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(15.6, 88), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(34.7, 88), 1);
			
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 14), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 27.2), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 39.3), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 52.2), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 64), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 76.2), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 89.2), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 101.2), 7);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(16.3, 122), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(32, 122), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(47.5, 122), 2);
			
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 6.7), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 18.6), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 30.5), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 42.4), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 55), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 66.5), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 78.4), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 90.4), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 101), 8);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 113.4), 9);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 126), 10);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 137.4), 11);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 149.3), 12);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 161.9), 13);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 173.8), 14);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 185.7), 15);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(18.1, 202), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(34, 202), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(50.1, 202), 2);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(66.1, 202), 3);
			
		}break;
		}
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		setCord();	
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		double posX = x+lesCoordonnees.getCordSortieInIndex(0).getX() ;
		double posY = y + lesCoordonnees.getCordSortieInIndex(0).getY();
		Polyline polyline = new Polyline(posX ,posY,posX+5,posY);
		ArrayList<InfoPolyline> listPolylines = new ArrayList<InfoPolyline>();
		listPolylines.add(new InfoPolyline(polyline));
		reslut.add(polyline);
		Circuit.ajouterFil(sorties[0], listPolylines);
		return reslut;
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	

}
