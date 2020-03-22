package noyau;


public class Multiplexeur extends Combinatoires {
	
	
	
	private int nbCommande;
	
	public Multiplexeur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom);//initialiser le nombre d'entrees 
		// TODO Auto-generated constructor stub
		
		this.nbCommande = Integer.toBinaryString(nombreEntree-1).length();//initialiser le nombre de commandes
		commande=new Fil[this.nbCommande];
		nombreSortie = 1;//le multiplexeur possede une seule sortie
		Fil fil = new Fil(this);
		sorties[0]=fil;
	}
	
	
	
	public void genererSorties() { // role executer le fonction du composant 
		
		int numeroDeEntreeActif= Integer.parseInt(concatener(commande, this.nbCommande),2); //determiner le numero de sortie activee
		sorties[0].setEtatLogiqueFil(entrees[numeroDeEntreeActif].getEtatLogiqueFil());		
		
	}
	public boolean valider() { // tester si le composant est pret 
		return (this.validerCommandes().equals(EtatLogique.ONE)? true : false) // tester si les commandes sont valides
				&&
				(validerEntrees().equals(EtatLogique.ONE)  // tester si les entrees sont valides 
						|| 
						validerEntrees().equals(EtatLogique.ZERO) ? true: false); // tester si LessExpression sorties sont valides 
		
		
	}
	
	public EtatLogique validerCommandes() { // Role : valider les commandes ( si toutes les commandes sont valides retourner 1) 
		int i =0;
		while(i<nbCommande) {
			if(commande[i].getEtatLogiqueFil()== null) // tester si les fils sont a null 
				return null;
			if(commande[i].getEtatLogiqueFil().equals(EtatLogique.HAUTE_IMPEDANCE)) //tester  l'etat logique HAUTE IMPEDANCE 
				return EtatLogique.HAUTE_IMPEDANCE;
			if(commande[i].getEtatLogiqueFil().equals(EtatLogique.ERROR))  //tester  l'etat logique ERRR 
				return EtatLogique.ERROR;
			i++;
		}
		return EtatLogique.ONE;
	}
	

}
