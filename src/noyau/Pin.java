package noyau;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Pin extends Composant implements Affichage,ElementHorloge{
	private boolean input ; // INPUT= vrai ->  entree // faux -> sortie 
	private EtatLogique etat = EtatLogique.ZERO;
	private boolean horloge = false ; // pour savoir si le pin fonctionne comme horloge ou pas .
	
	public boolean getInput()
	{
		return this.input;
	}
	public Pin(boolean input,String nom) {
		// TODO Auto-generated constructor stub
		super(0,nom);
		this.input = input;
		if (input) { // pour verifier si c'est un pin d'entree ou de sortie
			nombreSortie = 1;
			sorties[0] = new Fil(this);
			Circuit.ajouterEntree(this);
			lesCoordonnees = new LesCoordonnees(0,1,0);
		}else {
			nombreEntree = 1;
			nombreSortie = 0;
			Circuit.ajouterSortie(this);
			lesCoordonnees = new LesCoordonnees(1,0,0);
		}
	}
	
	public void evaluer() {
		if(valider()) // si le composant est pret 
		{
			genererSorties(); //executer sa fonction logique et mettre le resultat sur le fil de sortie 
			
			for (int i = 0; i < nombreSortie; i++) 
			{
				if(sorties[i].getEtatLogiqueFil().getNum() != etatFinal[i].getNum())  //verifier si l'etat precedent du composant a changé ou non 
				{
					etatFinal[i]=sorties[i].getEtatLogiqueFil(); //mettre a jour l'etat final du composant 
					sorties[i].evaluer(); //passer au composant suivant relié au fil de sortie 
				}
			}
			if (horloge == true) { // verifier si le pin sert comme horloge ou pas
				tictac();
			}
		}else // to be continued ...
		{
			//signaler les erreurs ..
		}
	}
	
	public void genererSorties() { // executer la fonction du pin 
		if(input) // si pin est une entree ( pour definir les entrees du circuit ) 
			sorties[0].setEtatLogiqueFil(etat); // transferer l'etat du pin au fil de sortie 
		else  // si pin est une sortie ( pour affichage de la valeur de la sortie du circuit )
			etat = entrees[0].getEtatLogiqueFil(); // transferer l'etat du fil de sortie au pin 
	}
	
	public boolean valider() {
		return true;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	public void addEtages(ArrayList<Integer> etage) { // sert pour la creation des etages dans la simulation
		if (! etage.contains(0)) {
			etage.add(0);
			this.horloge = true ; // mettre le pin comme etant une horloge
		}
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		String path;
		switch (etat) {
		case ONE:{
			path = "Pin/1";
		}break;

		default:{
			path = "Pin/0";
		}break;
		}
		if (input) {
			return path + "Input" +".png";
		}
		return path + "Output" +".png";
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		if (input) {
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth() / 2, img.getBoundsInLocal().getHeight()), 0);
		}
		else {
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(img.getBoundsInLocal().getWidth() / 2, img.getBoundsInLocal().getHeight()), 0);
		}
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		setCord();
		double posX = x+lesCoordonnees.getCordSortieInIndex(0).getX();
		double posY = y+lesCoordonnees.getCordSortieInIndex(0).getY();
		if (input) {
			Polyline polyline = new Polyline(posX,posY,posX,posY+5);
			ArrayList<Polyline> listPolylines = new ArrayList<Polyline>();
			listPolylines.add(polyline);
			Circuit.ajouterFil(sorties[0], listPolylines);
			return polyline;
		}		
		return null;
	}
	public boolean isInput() {
		return input;
	}
	public void setInput(boolean input) {
		this.input = input;
	}
	public EtatLogique getEtat() {
		return etat;
	}
	public void setEtat(EtatLogique etat) {
		this.etat = etat;
		Circuit.getImageFromComp(this).setImage(new Image(generatePath()));
	}
	
}
