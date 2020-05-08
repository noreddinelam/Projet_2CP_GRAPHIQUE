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
	public String generatePath() { /// generer les images relatives aux composant
		// TODO Auto-generated method stub
		return "Not/Not"+Integer.toString(direction)+".png";
	}
	
	@Override
	public void setCord() { /// seter les coordonnées nécessaires
		// TODO Auto-generated method stub
		super.setCord();
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0,17), 0) ;
		rotation(direction);
	}

}
