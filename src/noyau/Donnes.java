package noyau;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Donnes {
	Actions typeDaction;
	ImageView composantCommeImage;
	Composant composant;
	Image image=null;
	int nombreDesEntrees=0;
	double posX=0;
	double posY=0;
	Boolean typePin;
	String label="";
	Front front=null ;
	Polyline parent = null;
	Fil fil = null;
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
	
	
	
	
}
