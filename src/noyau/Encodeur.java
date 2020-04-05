package noyau;

public class Encodeur extends Combinatoires {
	
	public Encodeur(int nombreEntree,String nom) { // constructeur 
		super(nombreEntree,nom);	
		nombreSortie = Integer.toBinaryString(nombreEntree-1).length();
		initSorties();
		// TODO Auto-generated constructor stub
	}
	public void genererSorties() {
		int i = 0;
		while ((i < nombreEntree )&&(entrees[i].getEtatLogiqueFil() != EtatLogique.ONE)) // recuperer le premier un dans les entrées
			i++;
		int res = Integer.parseInt(Integer.toBinaryString(i));
		numToFils(res); // distribuer la valeur resultante dans les fils de sorties
	}
	
	public boolean valider() { // verifier si les entrees du composant sont toutes reliées
		return (validerEntrees().getNum() == 1) ? true : false;
	}

	

}
