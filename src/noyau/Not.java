package noyau;

public class Not extends Portes{
	
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

}
