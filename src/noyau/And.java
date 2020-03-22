package noyau;

public class And extends Portes {
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

}
