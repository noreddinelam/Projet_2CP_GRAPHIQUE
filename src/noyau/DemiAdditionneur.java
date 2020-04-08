package noyau;

public class DemiAdditionneur extends Additionneur{
	
	public DemiAdditionneur(int nombreEntree,String nom) { // constructeur
		super(nombreEntree*2,nom);
		// TODO Auto-generated constructor stub
	}

	public void genererSorties() {	
		Fil tab1[] = new Fil[32];
		Fil tab2[] = new Fil[32];		
		System.arraycopy(entrees,0,tab1, 0,nombreEntree/2); //recuperer le premier mot dans tab1
		System.arraycopy(entrees,nombreEntree/2,tab2, 0,nombreEntree-1); //recuperer le deuxieme mot dans tab2
		String bin1 = concatener(tab1,nombreEntree/2);
		int s1=Integer.parseInt(bin1,2); //convertir le premier mot en entier
		bin1 = concatener(tab2,nombreEntree/2);
		int s2=Integer.parseInt(bin1,2); //convertir le deuxieme mot en entier
		s1 += s2; //faire l'addition entre les deux mots
		numToFils(Integer.parseInt(Integer.toBinaryString(s1))); //affecter le resultats aux sorties	
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + String.valueOf(getNombreEntree() / 2)+".png";
	}
}
