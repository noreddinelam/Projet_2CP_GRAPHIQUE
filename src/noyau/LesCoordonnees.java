package noyau;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class LesCoordonnees implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2271723942606943555L;
	private Coordonnees cordEntree[] = new Coordonnees[32]; // coordonees des entrees
	private int nbCordEntree=0 ;
	private Coordonnees cordSorties[] = new Coordonnees[32]; // coordonees des sorties
	private int nbCordSorties=0 ;
	private Coordonnees cordCommandes[] = new Coordonnees[32]; // coordonees des cmd
	private int nbCordCommandes=0;
	private Coordonnees cordLoad = null; /// coordonnees du load
	private Coordonnees cordClear = null; /// coordonnees du clear
	private Coordonnees cordPreset = null; /// coordonnees du preset
	private Coordonnees cordHorloge = null; /// cordonnees de l'horloge
	public LesCoordonnees(int nbCordEntree, int nbCordSorties, int nbCordCommandes) {
		this.nbCordEntree = nbCordEntree;
		this.nbCordSorties = nbCordSorties;
		this.nbCordCommandes = nbCordCommandes;

	}
	
	public int indexCoord(Coordonnees crd) { /// savoir le numero d'entree qui contient les coordonnées passé comme parametre
		int i = 0;
		while(i < nbCordEntree) {
			if(cordEntree[i].equals(crd)) {
				return i;}
		i++;
		}
		return -1;
	}

	public Coordonnees coordReelesSorties(ImageView image,int sortie) { /// avoir les coordonnees reelles des sorties
		if(sortie < nbCordSorties) 
			return new Coordonnees(cordSorties[sortie].getX() + image.getLayoutX(), cordSorties[sortie].getY() + image.getLayoutY());
		else return null;
	}
	
	public Coordonnees coordReelesEntrees(ImageView image,int entre) {/// avoir les coordonnees reelles des entrees
		if(entre < nbCordEntree)
			return new Coordonnees(cordEntree[entre].getX() + image.getLayoutX(), cordEntree[entre].getY() + image.getLayoutY());
		else return null;
	}
	public Coordonnees coordReelesCommande(ImageView image,int entre) { /// avoir les coordonnees reelles des commandes
		if(entre < nbCordCommandes)
			return new Coordonnees(cordCommandes[entre].getX() + image.getLayoutX(), cordCommandes[entre].getY() + image.getLayoutY());
		else return null;
	}
	public Coordonnees coordReelesHorloge(ImageView image) { /// avoir les coordonnees reelles de l'horloge
		if(cordHorloge != null)
			return new Coordonnees(cordHorloge.getX() + image.getLayoutX(), cordHorloge.getY() + image.getLayoutY());
		else return null;
	}	
	public Coordonnees coordReelesClear(ImageView image) { /// avoir les coordonnees reelles du clear
		if(cordClear != null)
			return new Coordonnees(cordClear.getX() + image.getLayoutX(), cordClear.getY() + image.getLayoutY());
		else return null;
	}	
	public Coordonnees coordReelesPreset(ImageView image) { /// avoir les coordonnees reelles du preset
		if(cordPreset != null)
			return new Coordonnees(cordPreset.getX() + image.getLayoutX(), cordPreset.getY() + image.getLayoutY());
		else return null;
	}	
	public Coordonnees coordReelesLoad(ImageView image) { /// avoir les coordonnees reelles du load
		if(cordLoad != null)
			return new Coordonnees(cordLoad.getX() + image.getLayoutX(), cordLoad.getY() + image.getLayoutY());
		else return null;
	}	
	public void rotationXY(ImageView imageView) { /// faire une rotation du composant
		double perm ;
		for(int i=0;i<nbCordEntree;i++) { /// inverser les coordonnées x et y des entrees
			perm = cordEntree[i].getX();
			cordEntree[i].setX(cordEntree[i].getY());
			cordEntree[i].setY(perm);
		}
		cordSorties[0].setX(imageView.getFitWidth()/2);
		cordSorties[0].setY(imageView.getFitHeight());	
	}
	
	public void rotationXX() { /// faire une rotation mirroir selon axe Y
		double x = cordSorties[0].getX();
		cordSorties[0].setX(cordEntree[0].getX());
		for(int i=0;i<nbCordEntree;i++) {
			cordEntree[i].setX(x);
		}
		
	}
	
	public void rotationYY() {/// faire une rotation mirroir selon axe X
		double y = cordSorties[0].getY();
		cordSorties[0].setY(cordEntree[0].getY());
		for(int i=0;i<nbCordEntree;i++) {
			cordEntree[i].setY(y);
		}
	}
	
	public void rotationXYPin(ImageView imageView) { /// rotation du pin
		if (nbCordEntree == 0) {
			cordSorties[0].setX(imageView.getFitWidth() );
			cordSorties[0].setY(imageView.getFitHeight() /2 );	
		}
		else {
			cordEntree[0].setX(imageView.getFitWidth() );
			cordEntree[0].setY(imageView.getFitHeight() /2);
		}
	}
	
	public void rotationXXPin() { /// rotation mirroir du pin selon axe Y
		if (nbCordEntree != 0) {
			cordEntree[0].setX(0);
		}
		else {
			cordSorties[0].setX(0);
		}
	}
	
	public void rotationYYPin() { /// rotation mirroir du pin selon axe X
		if (nbCordEntree != 0) {
			cordEntree[0].setY(0);
		}
		else {
			cordSorties[0].setY(0);
		}
	}
	
	public void setCordEntreeInIndex(Coordonnees coordonnees,int entree) {
		cordEntree[entree] = coordonnees;
	}
	public void setCordSortieInIndex(Coordonnees coordonnees,int sortie) {
		cordSorties[sortie] = coordonnees;
	}
	public void setCordCmdInIndex(Coordonnees coordonnees,int cmd) {
		cordCommandes[cmd] = coordonnees;
	}
	public void setCordLoad(Coordonnees coordonnees) {
		cordLoad = coordonnees;
	}
	public void setCordPreset(Coordonnees coordonnees) {
		cordPreset = coordonnees;
	}
	public void setCordClear(Coordonnees coordonnees) {
		cordClear= coordonnees;
	}
	public void setCordHorloge(Coordonnees coordonnees) {
		cordHorloge= coordonnees;
	}
	
	
	public Coordonnees getCordEntreeInIndex(int entree) {
		return cordEntree[entree];
	}
	public Coordonnees getCordSortieInIndex(int sortie) {
		return cordSorties[sortie];
	}
	public Coordonnees getCordCmdInIndex(int cmd) {
		return cordCommandes[cmd];
	}
	public Coordonnees getCordLoad() {
		return cordLoad;
	}
	public Coordonnees getCordPreset() {
		return cordPreset;
	}
	public Coordonnees getCordClear() {
		return cordClear;
	}
	public Coordonnees getCordHorloge() {
		return cordHorloge;
	}

	public Coordonnees[] getCordEntree() {
		return cordEntree;
	}

	public int getNbCordEntree() {
		return nbCordEntree;
	}

	public Coordonnees[] getCordSorties() {
		return cordSorties;
	}

	public int getNbCordSorties() {
		return nbCordSorties;
	}

	public Coordonnees[] getCordCommandes() {
		return cordCommandes;
	}

	public int getNbCordCommandes() {
		return nbCordCommandes;
	}
	
	public void setCordEntree(Coordonnees[] cordEntree) {
		this.cordEntree = cordEntree;
	}

	public void setNbCordEntree(int nbCordEntree) {
		this.nbCordEntree = nbCordEntree;
	}

	public void setCordSorties(Coordonnees[] cordSorties) {
		this.cordSorties = cordSorties;
	}

	public void setNbCordSorties(int nbCordSorties) {
		this.nbCordSorties = nbCordSorties;
	}

	public void setCordCommandes(Coordonnees[] cordCommandes) {
		this.cordCommandes = cordCommandes;
	}

	public void setNbCordCommandes(int nbCordCommandes) {
		this.nbCordCommandes = nbCordCommandes;
	}
}