package noyau;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class AfficheurSegment extends Composant implements Affichage{
	
	protected int valeur; // la valeur qui sera stocké dans le registre segment 
	
	public AfficheurSegment(String nom) {
		super(4,nom);
		nombreSortie = 0;
		Circuit.ajouterSortie(this);//ajouter le composant aux elts de sorties dans circuits 
		lesCoordonnees = new LesCoordonnees(4, 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void evaluer() {
		if (valider()) {
			genererSorties();
		}
	}
	public void genererSorties() {
		valeur = Integer.valueOf(concatener(entrees, 4),2);
		ImageView imageView = Circuit.getImageFromComp(this);
		imageView.setImage(new Image(generatePath()));
	}
	
	public boolean valider() {
		if (validerEntrees() == EtatLogique.ONE) { // verifier si les entrees sont toutes reliées .
			return true;
		}
		return false;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void defaultValue() {
		// TODO Auto-generated method stub
		valeur = 0;
		ImageView img = Circuit.getImageFromComp(this);
		Image image = new Image("AfficheurSegment/0.png");
		img.setImage(image);
		img.setFitHeight(image.getHeight());
		img.setFitWidth(image.getWidth());
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return "AfficheurSegment/"+String.valueOf(valeur)+ ".png";
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 19.8), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 37.5), 1);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 56.6), 2);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 74.7), 3);
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}

}
