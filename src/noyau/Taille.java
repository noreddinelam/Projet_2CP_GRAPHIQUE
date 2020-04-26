package noyau;

import java.io.Serializable;

public class Taille implements Serializable{
	private double height;
	private double width;
	public Taille(double height,double width) {
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
}
