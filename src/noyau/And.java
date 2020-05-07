package noyau;

public class And extends Portes {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6769294463092098669L;

	public And(int nombreEntree,String nom) { // constructeur
		super(nombreEntree,nom);
	}
	
	public void genererSorties() { // role : executer la fonction logique "ET logique" 

		int res=1,i=0;
		while(i<nombreEntree && res!=0)
		{
			res = res & entrees[i].getEtatLogiqueFil().getNum();
			i++;
		}
		if(res==0) sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		else sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
			
	}
	
	@Override
	public void setCord() {// seter les coordonnees de la porte AND
		// TODO Auto-generated method stub
		super.setCord();
		switch (nombreEntree) {
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 21.5), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 59.3), 1);
			
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 20.1), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 40.5), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 59.6), 2);
			
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 13.2), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 30.1), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 47.4), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 64.4), 3);
			
		}break;
		case 5:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 12), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 26.5), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 40.5), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 54.1), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 68.1), 4);
			
		}break;
		}
		rotation(direction); /// appliquer une rotation dan
	}

}
