package noyau;

import javafx.scene.image.ImageView;

public class Not extends Portes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3351932009983831005L;

	public Not(String nom) {
		super(1,nom);
		
	}
	public void genererSorties() {	// Role : executer la fonction "NOT" (inversement)
		if(entrees[0].getEtatLogiqueFil().getNum()==1) sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		else sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return "Not/Not.png";
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		super.setCord();
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0,img.getBoundsInLocal().getHeight()/2), 0) ;
	}

}
