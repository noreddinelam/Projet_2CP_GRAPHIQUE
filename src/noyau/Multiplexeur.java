package noyau;

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
		lesCoordonnees = new LesCoordonnees(nombreEntree, 0, nbCommande);
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
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(), img.getBoundsInLocal().getHeight() / 2), 0);
		switch (nbCommande) {
		case 1:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
		}break;
		case 2:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 1);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			
		}break;
		case 3:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 2);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 7);
			
		}break;
		case 4:{
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(0, 0), 4);
			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 8);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 9);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 10);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 11);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 12);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 13);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 14);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 0), 15);
			
		}break;
		}
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
