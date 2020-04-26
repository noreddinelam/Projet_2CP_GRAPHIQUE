package noyau;

import java.util.ArrayList;

import com.sun.glass.ui.Size;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public abstract class Portes extends Composant {
	
	public Portes(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
		nombreSortie=1;
		Fil f= new Fil(this);
		sorties[0]=f;
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
	}
	
	public abstract void genererSorties();
	
	
	public boolean valider() { // valider les entrees 
		return (super.validerEntrees() == EtatLogique.ONE) ? true: false;	
	}
	
	public void validerComposant() {
		// TODO Auto-generated method stub
		ArrayList<ExceptionProgramme> arrayList = new ArrayList<ExceptionProgramme>();
		int j=0,k = 0;
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] == null) {
				arrayList.add(new EntreeManquante(TypesExceptions.ERREUR, this, i));
				j++;
			}
			else if(entrees[i].equals(sorties[0])) {
				arrayList.add(new SortieReinjecter(TypesExceptions.ALERTE, this, i, 0));
				k++;
			}
		}
		if (j == nombreEntree) {
			Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE, this));
		}
		else {
			if (arrayList.size() != 0 && k == 0) {
				Circuit.ajouterCompErrone(this);
			}
			Circuit.AjouterListeException(arrayList);
		}
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + (String.valueOf(getNombreEntree()))+".png";
	}
	
	public void setCord() {
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(),img.getBoundsInLocal().getHeight()/2), 0) ;
	}
	
	
}