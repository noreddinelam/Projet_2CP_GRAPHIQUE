package noyau;

public class Or extends Portes{
	
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
}
