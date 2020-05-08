package noyau;

import java.io.Serializable;

public class Coordonnees implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3810549458198793318L;
	private double x;
	private double y;
	public Coordonnees(double x,double y) { /// cette classe est utilisé pour definire les coordonnees des entrées/sorties/load..etc
		// TODO Auto-generated constructor stub
		this.x =x;
		this.y =y;
	}
	@Override
	public boolean equals(Object obj) { /// redefinire equals pour savoir si deux coordonnées sont egales ou non
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

	 public boolean semiEquals(Coordonnees crd) { /// une autre fonction utiliser dans la comparaison des coordonnées
		 if(   Math.abs(crd.getX() - this.x)<10  ||  Math.abs(crd.getY() - this.y)<5) {
			 return true;
		 }else
			 return false;
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

}
