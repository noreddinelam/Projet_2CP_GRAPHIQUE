package noyau;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Donnes {
	Actions typeDaction;
	ImageView composant;
	Image image=null;
	int nombreDesEntrees=0;
	double posX=0;
	double posY=0;
	Boolean typePin;
	String label="";
	Front front=null ;
	
	public Actions getTypeDaction() {
		return typeDaction;
	}
	public void setTypeDaction(Actions typeDaction) {
		this.typeDaction = typeDaction;
	}
	public ImageView getComposant() {
		return composant;
	}
	public void setComposant(ImageView composant) {
		this.composant = composant;
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
	
	
}
