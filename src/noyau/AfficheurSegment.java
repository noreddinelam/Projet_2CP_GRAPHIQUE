package noyau;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class AfficheurSegment extends Composant{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1307600965697277067L;
	protected int valeur; // la valeur qui sera stocké dans le registre segment 
	
	public AfficheurSegment(String nom) {
		super(4,nom);
		nombreSortie = 0;
		lesCoordonnees = new LesCoordonnees(4, 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void evaluer() {/// afficher la valeur de l'afficheur segments
		if (valider()) { /// si le composant est valider 
			genererSorties(); /// generer les images
		}
	}
	@Override
	public void genererSorties() { /// pour generer la valeur a afficher dans l'afficheur segment
		valeur = Integer.valueOf(concatener(entrees, 4),2);
		ImageView imageView = Circuit.getImageFromComp(this);
		if (imageView != null) {
			imageView.setImage(new Image(generatePath()));
		}
	}
	
	@Override
	public boolean valider() {
		if (validerEntrees() == EtatLogique.ONE) { // verifier si les entrees sont toutes reliées .
			return true;
		}
		return false;
	}
	
	@Override
	public void defaultValue() { // retourner le composant à sa valeur par defaut
		// TODO Auto-generated method stub
		valeur = 0;
		ImageView img = Circuit.getImageFromComp(this);
		Image image = new Image("AfficheurSegment/0.png");
		if (img != null) {
			img.setImage(image);
			img.setFitHeight(image.getHeight());
			img.setFitWidth(image.getWidth());
		}
	}
	
	@Override
	public String generatePath() { /// generer le chemin des images relatives à l'afficheur segments
		// TODO Auto-generated method stub
		return "AfficheurSegment/"+String.valueOf(valeur)+ ".png";
	}
	
	@Override
	public void setCord() { /// seter les coordonnees des entrees pour l'afficheur segment
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 19.8), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 37.5), 1);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 56.6), 2);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 74.7), 3);
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) { /// reset les polylines 
		// TODO Auto-generated method stub
		
	}

}