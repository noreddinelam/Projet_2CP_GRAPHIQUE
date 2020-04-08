package noyau;

public class AdditionneurN_Bites extends Additionneur {
	public AdditionneurN_Bites(int nombreEntree,String nom) {
		super(nombreEntree*2 + 1,nom);
		// TODO Auto-generated constructor stub
	}

	public void genererSorties() {	
			Fil tab1[] = new Fil[32];
			Fil tab2[] = new Fil[32];  //initialise deux tableau pour la recuperation des deux mots
			System.arraycopy(entrees,0,tab1, 0,(nombreEntree-1)/2);  //recuperer le premier mot dans le tableau tab1
			System.arraycopy(entrees,(nombreEntree-1)/2,tab2, 0,nombreEntree-2);  //recuperer le deuxieme mot dans le tableau tab2
			String bin1 = concatener(tab1,(nombreEntree-1)/2);  //concatener les valeurs contenues dans le tab1
			int s1=Integer.parseInt(bin1,2);  //convertir le premier mot en entiers
			bin1 = concatener(tab2,(nombreEntree-1)/2);  //concatener les valeurs contenues dans le tab2
			int s2=Integer.parseInt(bin1,2);  //convertir le deuxieme mot en entier
			int r = (entrees[nombreEntree-1].getEtatLogiqueFil().getNum() == 1) ? 1 : 0;  // recuperer la retenue 
			r+=s1+s2;  //faire l'addition entre les deux mots et la retenue
			numToFils(Integer.parseInt(Integer.toBinaryString(r)));  //affeceter les resultats aux sorties			
			
		}

	public boolean valider() { // verifier si le composant est pret a executer sa fonction logique 
		   					   // valider si les entrees sont pretes 
		return (validerEntrees().getNum() == 1 ) ? true : false;
	}
		
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + String.valueOf((getNombreEntree() -1) / 2)+".png";
	}
}
