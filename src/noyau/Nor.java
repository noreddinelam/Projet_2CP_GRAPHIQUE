package noyau;

public class Nor extends Or {
	public Nor(int nombreEntree,String nom) {
		super(nombreEntree,nom);
		// TODO Auto-generated constructor stub
	}

	public void genererSorties() {
		//Utiliser generer sortie de "OR"
		super.genererSorties();
		//inverser la valeur du resultat 
		if(sorties[0].getEtatLogiqueFil().getNum()==1) sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		else sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
	}

}
