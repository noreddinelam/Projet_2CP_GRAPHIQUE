package noyau;
import java.lang.Math;
import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;


public class Demultiplexeur extends Combinatoires{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8309920624094733575L;
	private int nbCommande;

	public Demultiplexeur(int nbCommande,String nom) { // constructeur
		super(1,nom);
		this.nbCommande = nbCommande;
		Double entreeDouble=Math.pow(2, nbCommande);
		nombreSortie = Integer.valueOf(entreeDouble.intValue()) ;
		lesCoordonnees = new LesCoordonnees(1, nombreSortie, nbCommande);
		initSorties();
	}

	public void genererSorties() {
		
		int sortieActif=  Integer.parseInt(concatener(commande, nbCommande),2); //concatener les commandes//covertir les commandes en un entier
		String binaryString = Integer.toBinaryString(sortieActif);
		for (int i = binaryString.length(); i < nbCommande; i++) {
			binaryString = '0' + binaryString;
		}
		StringBuilder stringBuilder = new StringBuilder(binaryString);
		sortieActif = Integer.valueOf(stringBuilder.reverse().toString(), 2);
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
	public void setCord() { /// seter les coordonnées d'entrees/sorties/commandes
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, (img.getBoundsInLocal().getHeight() -10) / 2), 0);
		switch (nbCommande) {
		case 1:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(50, 16.8), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(50, 48.1), 1);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(25.1, 75), 0);
		}break;
		case 2:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(52, 14.9), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(52, 31.3), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(52, 48.1), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(52, 65), 3);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(15.6, 88), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(34.7, 88), 1);
			
		}break;
		case 3:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 14), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 27.2), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 39.3), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 52.2), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 64), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 76.2), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 89.2), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(64, 101.2), 7);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(16.3, 122), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(32, 122), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(47.5, 122), 2);
			
		}break;
		case 4:{
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 6.7), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 18.6), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 30.5), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 42.4), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 55), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 66.5), 5);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 78.4), 6);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85,90.4), 7);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 101), 8);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 113.4), 9);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 126), 10);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 137.4), 11);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 149.3), 12);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 161.9), 13);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 173.8), 14);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 185.7), 15);
			
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(18.1, 202), 0);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(34, 202), 1);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(50.1, 202), 2);
			lesCoordonnees.setCordCmdInIndex(new Coordonnees(66.1, 202), 3);
			
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
	public void derelierEntreeFromComp(Fil fil) { /// dererlier le composant des connexions faite avec le fil passé comme parametre
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
	public void relierANouveau() { /// relier le composant une autre fois ( utilisé dans le ctrl + z
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
	public boolean isDessocier() { /// savoir si le composant est dessocié
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
	public void validerComposant() { /// savoir si le composant est validé
		// TODO Auto-generated method stub
		ArrayList<ExceptionProgramme> arrayList = new ArrayList<ExceptionProgramme>();
		for (int i = 0; i < nombreEntree; i++) { /// validation des entrees 
			if (entrees[i] == null) {
				arrayList.add(new EntreeManquante(TypesExceptions.ERREUR, this, i));
			}
		}
		for (int i = 0; i < nbCommande; i++) { /// validation des commandes
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