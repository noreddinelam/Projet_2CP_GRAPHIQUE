package noyau;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class LesCoordonnees implements Serializable{
	private Coordonnees cordEntree[] = new Coordonnees[32]; // coordonees des entrees
	private int nbCordEntree ;
	private Coordonnees cordSorties[] = new Coordonnees[32]; // coordonees des sorties
	private int nbCordSorties ;
	private Coordonnees cordCommandes[] = new Coordonnees[32]; // coordonees des cmd
	private int nbCordCommandes;
	private Coordonnees cordLoad = null;
	private Coordonnees cordClear = null;
	private Coordonnees cordPreset = null;
	private Coordonnees cordHorloge = null;
	public LesCoordonnees(int nbCordEntree, int nbCordSorties, int nbCordCommandes) {
		this.nbCordEntree = nbCordEntree;
		this.nbCordSorties = nbCordSorties;
		this.nbCordCommandes = nbCordCommandes;

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
	
	public int indexCoord(Coordonnees crd) {
		int i = 0;
		while(i < nbCordEntree) {
			if(cordEntree[i].equals(crd)) {
				return i;}
		i++;
		}
		return -1;
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
	public Coordonnees coordReelesSorties(ImageView image,int sortie) {
		if(sortie < nbCordSorties)
			return new Coordonnees(cordSorties[sortie].getX() + image.getLayoutX(), cordSorties[sortie].getY() + image.getLayoutY());
		else return null;
	}
	
	public Coordonnees coordReelesEntrees(ImageView image,int entre) {
		if(entre < nbCordEntree)
			return new Coordonnees(cordEntree[entre].getX() + image.getLayoutX(), cordEntree[entre].getY() + image.getLayoutY());
		else return null;
	}
	public Coordonnees coordReelesCommande(ImageView image,int entre) {
		if(entre < nbCordCommandes)
			return new Coordonnees(cordCommandes[entre].getX() + image.getLayoutX(), cordCommandes[entre].getY() + image.getLayoutY());
		else return null;
	}
	public Coordonnees coordReelesHorloge(ImageView image,int entre) {
		if(cordHorloge != null)
			return new Coordonnees(cordHorloge.getX() + image.getLayoutX(), cordHorloge.getY() + image.getLayoutY());
		else return null;
	}	
	public Coordonnees coordReelesClear(ImageView image,int entre) {
		if(cordClear != null)
			return new Coordonnees(cordClear.getX() + image.getLayoutX(), cordClear.getY() + image.getLayoutY());
		else return null;
	}	
	public Coordonnees coordReelesPreset(ImageView image,int entre) {
		if(cordPreset != null)
			return new Coordonnees(cordPreset.getX() + image.getLayoutX(), cordPreset.getY() + image.getLayoutY());
		else return null;
	}	
	public Coordonnees coordReelesLoad(ImageView image,int entre) {
		if(cordLoad != null)
			return new Coordonnees(cordLoad.getX() + image.getLayoutX(), cordLoad.getY() + image.getLayoutY());
		else return null;
	}	
}