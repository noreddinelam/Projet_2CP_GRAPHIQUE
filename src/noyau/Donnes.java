package noyau;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Donnes { /// cette classe est utilisé dans l'operation du ctrl + z
	private Actions typeDaction; /// le type de l'action faite
	private ImageView composantCommeImage; /// image du composant
	private Composant composant; /// composant
	private Image image=null; 
	private int nombreDesEntrees=0; //
	private int nombreDesSorties=0; // le nombre de connexions d'un composants
	private int nombreDeCommandes=0;//
	private double posX=0; //
	private double posY=0; // la position du composant
	private Boolean typePin; // type du pin
	private String label=""; // le nom du composant
	private Front front=null ; // le front
	private Polyline parent = null; // le parent du polyline
	private Fil fil = null; // le fil à sauvegarder
	private ArrayList<InfoPolyline> arrayList = new ArrayList<InfoPolyline>(); // la liste des infoPolylines relative à un fil donné
	private ArrayList<Polyline> listPolyParent = new ArrayList<Polyline>(); // la liste des polylines parent
	private int rotation; // pour la rotation 
	private int switching = 0;
	private boolean supprime = false; // boolean utilisé dans la suppression
	private InfoPolyline infoPolyline = null;
	private ArrayList<InfoPolyline> listPolylines = null;
	private long frequence ;
	
	@Override
	public boolean equals(Object obj) { /// redifinre la methode equals pour savoir si deux données sont égales
		// TODO Auto-generated method stub
		if (((Donnes)obj).getFil() != null && fil != null) {
			if (((Donnes)obj).getFil().equals(fil)) {
				return true;
			}
		}
		return false;
	}//equals fonctionne par raport au type d'action 
	
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getNombreDesSorties() {
		return nombreDesSorties;
	}
	public void setNombreDesSorties(int nombreDesSorties) {
		this.nombreDesSorties = nombreDesSorties;
	}
	public int getNombreDeCommandes() {
		return nombreDeCommandes;
	}
	public void setNombreDeCommandes(int nombreDeCommandes) {
		this.nombreDeCommandes = nombreDeCommandes;
	}
	public ArrayList<Polyline> getListPolyParent() {
		return listPolyParent;
	}
	public void setListPolyParent(ArrayList<Polyline> listPolyParent) {
		this.listPolyParent = listPolyParent;
	}
	public ArrayList<InfoPolyline> getArrayListInfoPoly() {
		return arrayList;
	}
	public void setArrayListInfoPoly(ArrayList<InfoPolyline> arrayList) {
		this.arrayList = arrayList;
	}
	public Fil getFil() {
		return fil;
	}
	public void setFil(Fil fil) {
		this.fil = fil;
	}
	public Polyline getParent() {
		return parent;
	}
	public void setParent(Polyline parent) {
		this.parent = parent;
	}
	
	public Actions getTypeDaction() {
		return typeDaction;
	}
	public void setTypeDaction(Actions typeDaction) {
		this.typeDaction = typeDaction;
	}
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getNombreDesEntrees() {
		return nombreDesEntrees;
	}
	public void setNombreDesEntrees(int nombreDesEntrees) {
		this.nombreDesEntrees = nombreDesEntrees;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public boolean getTypePin() {
		return typePin;
	}
	public void setTypePin(boolean typePin) {
		this.typePin = typePin;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Front getFront() {
		return front;
	}
	public void setFront(Front front) {
		this.front = front;
	}
	public void setComposant(Composant composant) {
		
		this.composant = composant;
	}
	
	public Composant getComposant() {
		return composant;
	}
	public ImageView getComposantCommeImage() {
		return composantCommeImage;
	}
	public void setComposantCommeImage(ImageView composantCommeImage) {
		this.composantCommeImage = composantCommeImage;
	}
	public InfoPolyline getInfoPolyline() {
		return infoPolyline;
	}
	public void setInfoPolyline(InfoPolyline infoPolyline) {
		this.infoPolyline = infoPolyline;
	}
	public ArrayList<InfoPolyline> getListPolylines() {
		return listPolylines;
	}
	public void setListPolylines(ArrayList<InfoPolyline> listPolylines) {
		this.listPolylines = listPolylines;
	}	
	public boolean isSupprime() {
		return supprime;
	}
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	public int getSwitching() {
		return switching;
	}

	public void setSwitching(int switching) {
		this.switching = switching;
	}

	public long getFrequence() {
		return frequence;
	}

	public void setFrequence(long frequence) {
		this.frequence = frequence;
	}
	
}
