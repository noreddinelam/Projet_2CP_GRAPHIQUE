package noyau;

public class DemiAdditionneur extends Additionneur{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5601887539655929033L;

	public DemiAdditionneur(int nombreEntree,String nom) { // constructeur
		super(nombreEntree*2,nom);
		nombreSortie = nombreEntree + 1 ;
		lesCoordonnees = new LesCoordonnees(nombreEntree, nombreSortie, 0);
		initSorties();
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
	public String generatePath() { /// generer le chemin des images relatives aux add
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + String.valueOf(getNombreEntree() / 2)+".png";
	}
	
	@Override
	public void setCord() {/// seter les coordonnées d'entrees/sorties/retenue
		// TODO Auto-generated method stub
		switch (nombreEntree) {
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 18.6), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 49), 1);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(60, 32.4), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(61, 11.4), 1);
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 12), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 24), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 50.4), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 61.6), 3);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(67, 32.4), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(67, 44.6), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(68, 8.5), 2);
		}break;
		case 6:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 7.4), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 19.3), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 31.3), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 67.8), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 80.7), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 92.4), 5);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(77, 35.7), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(77, 49.8), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(77, 63.6), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(78, 11.6), 3);
		}break;
		case 8:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 9.3), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 23.1), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 36.9), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 50.5), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 87.8), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 101.6), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0,115.2), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 128.5), 7);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 49.2), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 62.7), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 75.7), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 90), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 14.9), 4);
		}break;
		case 10:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 9.5), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 23.1), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 36.7), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 50.4), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 63), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 94.2), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0,107.9), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 121.5), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 135.1), 8);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 149.7), 9);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(89, 48.9), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(89, 62.5), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(89, 76.1), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(89, 89.7), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(89, 103.8), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(90, 15.7), 5);
		}break;
		}
	}
}
