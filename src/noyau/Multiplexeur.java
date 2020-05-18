package noyau;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Multiplexeur extends Combinatoires {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 695849189053262617L;
	private int nbCommande;
	
	public Multiplexeur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom);//initialiser le nombre d'entrees 
		// TODO Auto-generated constructor stub
		
		this.nbCommande = Integer.toBinaryString(nombreEntree-1).length();//initialiser le nombre de commandes
		nombreSortie = 1;//le multiplexeur possede une seule sortie
		Fil fil = new Fil(this);
		sorties[0]=fil;
		lesCoordonnees = new LesCoordonnees(nombreEntree, 1, nbCommande);
	}
	
	public void genererSorties() { // role executer le fonction du composant 
		
		int numeroDeEntreeActif= Integer.parseInt(concatener(commande, this.nbCommande),2); //determiner le numero de sortie activee
		String binaryString = Integer.toBinaryString(numeroDeEntreeActif);
		for (int i = binaryString.length(); i < nbCommande; i++) {
			binaryString = '0' + binaryString;
		}
		StringBuilder stringBuilder = new StringBuilder(binaryString);
		numeroDeEntreeActif = Integer.valueOf(stringBuilder.reverse().toString(), 2);
		sorties[0].setEtatLogiqueFil(entrees[numeroDeEntreeActif].getEtatLogiqueFil());		
		
	}
	public boolean valider() { // tester si le composant est pret
		if ((this.validerCommandes() == EtatLogique.ONE)&&(super.validerEntrees() == EtatLogique.ONE)) {
			return true;
		}
		return false;
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

	@Override
	public void setCord() { /// seter les coordonnées d'entrees/sorties/commandes
		// TODO Auto-generated method stub
		
		switch (nbCommande) {
		case 1:{			
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 16.8), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 48.1), 1);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(25.1, 75), 0);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(50, 32.8), 0);
		}break;
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 14.9), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 31.3), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 48.1), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 65), 3);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(15.6, 88), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(34.7, 88), 1);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(52, 40.3), 0);
			
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
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 57.6), 0);
			
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
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 98.4), 0);
			
		}break;
		}
	}
	
	public  void derelierComp() { // pour derelier le composant de ces fils de commandes  (le composant à supprimer)
		super.derelierComp();
		for (int i = 0; i < nbCommande; i++) {
			if (commande[i] != null) {
				if (! commande[i].getSource().equals(this)) {
					commande[i].derelierCompFromDestination(this);
					ImageView imageView = Circuit.getImageFromComp(this);
					Polyline polyline = commande[i].polylineParPoint(lesCoordonnees.coordReelesCommande(imageView, i));
					InfoPolyline info = Circuit.getInfoPolylineFromPolyline(polyline);
					if(info != null) {
						info.setRelier(false);
					}				
				}
			}
		}
	}
	
	@Override
	public void derelierEntreeFromComp(Fil fil) { /// enlever toute les connexions faites avec le fil passé comme parametre
		// TODO Auto-generated method stub
		super.derelierEntreeFromComp(fil);
		for (int i = 0; i < nbCommande; i++) {
			if (commande[i] != null) {
				if (commande[i].equals(fil)) {
					commande[i] = null;
				}
			}
		}
	}
	
	@Override
	public void relierANouveau() { /// relier les connexions une autre fois (utilisé dans le ctrl + z)
		// TODO Auto-generated method stub
		super.relierANouveau();
		for (int i = 0; i < nbCommande; i++) {
			if (commande[i] != null) {	
				ImageView imageView = Circuit.getImageFromComp(this);
				Polyline polyline = commande[i].polylineParPoint(lesCoordonnees.coordReelesCommande(imageView, i));
				if (polyline == null) {
					commande[i] = null;
				}
				else {
					Circuit.getInfoPolylineFromPolyline(polyline).setRelier(true);
					commande[i].addDestination(this);
				}
			}
		}
	}
	
	@Override
	public boolean isDessocier() { /// savoir si le composant est dessocié ou non
		// TODO Auto-generated method stub
		boolean dessocier =  super.isDessocier();
		if (dessocier) {
			int i = 0;
			while ( (i < nbCommande) && (dessocier == true)) {
				if (commande[i] != null) {
					dessocier = false;
				}
				else i++;
			}
		}
		return dessocier;
	}
	
	@Override
	public void validerComposant() { /// valider le composant et declarer les erreurs s'il ya
		// TODO Auto-generated method stub
		ArrayList<ExceptionProgramme> arrayList = new ArrayList<ExceptionProgramme>();
		for (int i = 0; i < nombreEntree; i++) { /// validation des entrees 
			if (entrees[i] == null) {
				arrayList.add(new EntreeManquante(TypesExceptions.ERREUR, this, i));
			}
		}
		for (int i = 0; i < nbCommande; i++) { /// validation des sorties
			if (commande[i] == null) {
				arrayList.add(new CommandeManquante(TypesExceptions.ERREUR, this, i));
			}
		}
		if (arrayList.size() == nombreEntree + nbCommande) {
			Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE, this));
		}
		else {
			if (arrayList.size() != 0) {
				Circuit.AjouterListeException(arrayList);
				Circuit.ajouterCompErrone(this);
			}
		}
	}
	
	public int getNbCommande() {
		return nbCommande;
	}

	public void setNbCommande(int nbCommande) {
		this.nbCommande = nbCommande;
	}

}