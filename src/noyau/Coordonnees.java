package noyau;

import java.io.Serializable;

public class Coordonnees implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3810549458198793318L;
	private double x;
	private double y;
	public Coordonnees(double x,double y) {
		// TODO Auto-generated constructor stub
		this.x =x;
		this.y =y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if(  ( Math.abs(((Coordonnees)obj).getX() - this.x)<=10)  &&   Math.abs(((Coordonnees)obj).getY() - this.y)<=10) {
			{
				return true;
			}
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "X : "+x+" Y : "+y;
	}

	 public boolean semiEquals(Coordonnees crd) {
		 if(   Math.abs(crd.getX() - this.x)<10  ||  Math.abs(crd.getY() - this.y)<5) {
			 return true;
		 }else
			 return false;
	 }
}
