package noyau;

public class Xor extends Portes {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2121421558559170522L;

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
	
	@Override
	public void setCord() { /// seter les coordonnées nécessaires 
		// TODO Auto-generated method stub
		super.setCord();
		switch (nombreEntree) {
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 16.5), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 66.6), 1);
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 16.9), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 41.7), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 66.5), 2);
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 14.3), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 32.9), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 49.3), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 67.7), 3);
		}break;
		case 5:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 7.7), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 24.1), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 40.7), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 57.3), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 73.1), 4);
		}break;
		}
		rotation(direction); /// faire une rotation pour la porte s'il est nécessaire
	}
	
}
