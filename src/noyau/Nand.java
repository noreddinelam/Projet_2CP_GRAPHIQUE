package noyau;

public class Nand extends And{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5992267225135063709L;

	public Nand(int nombreEntree,String nom) {
		super(nombreEntree,nom);
	}

	public void genererSorties() {
		//Utiliser generer sortie de "AND"
		super.genererSorties();
		//inverser la valeur du resultat 
		if(sorties[0].getEtatLogiqueFil().getNum()==1) sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		else sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
	}
}
