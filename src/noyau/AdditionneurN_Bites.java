package noyau;

public class AdditionneurN_Bites extends Additionneur {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1442604529556819263L;

	public AdditionneurN_Bites(int nombreEntree,String nom) {
		super(nombreEntree*2 + 1,nom);
		nombreSortie = nombreEntree + 1;
		lesCoordonnees = new LesCoordonnees(this.nombreEntree, nombreSortie, 0);
		initSorties();
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
	public String generatePath() { /// generer les chemins des images de l'additionneur
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + String.valueOf((getNombreEntree() -1) / 2)+".png";
	}
	
	@Override
	public void setCord() { /// utiliser pour definire les coordonnees 
		// TODO Auto-generated method stub
		switch (nombreEntree) {
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 18.6), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 49.8), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 34.7), 2);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(61, 32.4), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(62, 11.4), 1);
		}break;
		case 5:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 12), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 24), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 50.4), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 61.6), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 36.6), 4);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(68, 32.4), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(68, 44.6), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(69, 8.5), 2);
		}break;
		case 7:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 7.4), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 19.3), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 31.3), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 67.8), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 80.7), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 92.4), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0,50), 6);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(78, 35.7), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(78, 49.8), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(78, 63.6), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(79, 11.6), 3);
		}break;
		case 9:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 9.3), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 23.1), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 36.9), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 50.5), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 87.8), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 101.6), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1,115.2), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 128.5), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 69), 8);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 49.2), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 62.7), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 75.7), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(84, 90), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(85, 14.9), 4);
		}break;
		case 11:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 9.5), 0);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 23.1), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 36.7), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 50.4), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 63), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 94.2), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1,107.9), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 121.5), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 135.1), 8);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(1, 149.7), 9);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 78.6), 10);
			
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(90, 48.9), 0);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(90, 62.5), 1);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(90, 76.1), 2);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(90, 89.7), 3);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(90, 103.8), 4);
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(91, 15.7), 5);
		}break;
		}
	}
}
