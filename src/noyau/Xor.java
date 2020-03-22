package noyau;

public class Xor extends Portes {
	
	public Xor(int nombreEntree,String nom) {
		super(nombreEntree,nom);
	}

	public void genererSorties() { // executer la fonction du "XOR" (ou explicite )
		
		int res = entrees[0].getEtatLogiqueFil().getNum();
		for (int i = 1; i < nombreEntree; i++) {
			res=res^entrees[i].getEtatLogiqueFil().getNum();
		}	
		if(res==0) sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		else sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
	}
	
}
