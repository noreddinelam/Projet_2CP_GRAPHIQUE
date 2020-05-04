package noyau;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Donnes {
	private Actions typeDaction;
	private ImageView composantCommeImage;
	private Composant composant;
	private Image image=null;
	private int nombreDesEntrees=0;
	private int nombreDesSorties=0;
	private int nombreDeCommandes=0;
	private double posX=0;
	private double posY=0;
	private Boolean typePin;
	private String label="";
	private Front front=null ;
	private Polyline parent = null;
	private Fil fil = null;
	private ArrayList<InfoPolyline> arrayList = new ArrayList<InfoPolyline>();
	private ArrayList<Polyline> listPolyParent = new ArrayList<Polyline>();
	private int rotation;
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
	InfoPolyline infoPolyline = null;
	ArrayList<InfoPolyline> listPolylines = null;
	
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
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (((Donnes)obj).getTypeDaction().equals(this.getTypeDaction())) {
			if(this.composantCommeImage != null && ((Donnes)obj).getComposantCommeImage() != null) {
				return ((Donnes)obj).getComposantCommeImage().getId().equals(this.composantCommeImage.getId());
			}
		}
		return false;
	}//equals fonctionne par raport au type d'action 	
}
