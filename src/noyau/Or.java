package noyau;

public class Or extends Portes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 87427809768695712L;

	public Or(int nombreEntree,String nom) {
		super(nombreEntree,nom);
	}
	public void genererSorties() { // executer la fonction du "OR" 
		int res=0,i=0;
		while(i<nombreEntree && res!=1)
		{
			res=res|entrees[i].getEtatLogiqueFil().getNum();
			i++;
		}
		
	if(res==0) sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
	else sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
	}
	
	@Override
	public void setCord() {/// seter les coordonnées nécessaires
		// TODO Auto-generated method stub
		super.setCord();
		switch (nombreEntree) {
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 17.5), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 65.5), 1);
			
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 17.1), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 41.5), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 65.1), 2);
			
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 17.1), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 33.3), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 49.9), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 65.3), 3);
			
		}break;
		case 5:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 17.4), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 29.4), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 41.5), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 53.3), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 65.3), 4);
			
		}break;
		}
		rotation(direction);
	}
}
