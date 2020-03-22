package noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.relation.Role;


public abstract class Composant implements Serializable{
	/*--------- Attributs -------------*/
	protected String nom;
	protected String icon;
	protected Fil entrees[]= new Fil[32] ;
	protected Fil sorties[] = new Fil[32];
	protected EtatLogique etatFinal[] = new EtatLogique[32];
	protected int nombreEntree;
	protected int nombreSortie;
	protected boolean sleep = false;
	/*--------- Methodes --------------*/
	
	public Composant(int nombreEntree,String nom) {
		this.nombreEntree = nombreEntree;
		this.nom =nom;
		Arrays.fill(etatFinal, EtatLogique.HAUTE_IMPEDANCE);
		Circuit.ajouterComposant(this);
	}
	
	public void evaluer() {
		if(valider()) // si le composant est pret 
		{
			genererSorties(); //executer sa fonction logique et mettre le resultat sur le fil de sortie 
			
			for (int i = 0; i < nombreSortie; i++) 
			{
				if(sorties[i].getEtatLogiqueFil().getNum() != etatFinal[i].getNum())  //verifier si l'etat precedent du composant a changé ou non 
				{
					etatFinal[i]=sorties[i].getEtatLogiqueFil(); //mettre a jour l'etat final du composant 
					sorties[i].evaluer(); //passer au composant suivant relié au fil de sortie 
				}
			}
		}else // to be continued ...
		{
			//signaler les erreurs ..
		}
	}

	public abstract void genererSorties();
	public abstract boolean valider();  // verifier si le composant est pret a executer sa fonction logique 
	   							        // valider si les entrees et les sorties sont pretes 
	
	public void numToFils(int res) {  // role : transformer la valeur contenue dans "res" vers les fils de sortie 
		int i = 0;
		int bit = 0;
		while((i < nombreSortie)&&(res != 0)) { 
			bit = res % 10 ; 
			res = res / 10 ;  
			if (bit == 0)
				sorties[i].setEtatLogiqueFil(EtatLogique.ZERO);
			else 
				{sorties[i].setEtatLogiqueFil(EtatLogique.ONE);}

			i++;
		}
		if(i < nombreSortie) //  mettre les fils restants à 0
			for (int j = i; j < nombreSortie; j++) {
				sorties[j].setEtatLogiqueFil(EtatLogique.ZERO);
			}
	}
	
	public String concatener(Fil[] tabFils,int tai) { // Role : convertir les etats logiques des fils du "tabFils" de taille "tai" 
													  //        en binaire et les concatener 
		int res = 0;
		for (int i = 0; i < tai; i++) {
			res =  res*10 +tabFils[tai-i-1].getEtatLogiqueFil().getNum();
		}
		return Integer.toString(res);
	}
	

	
	public EtatLogique validerEntrees() { //role :  valider si les entrees du composant sont pretes 
		int i =0;
		while(i<nombreEntree) {
			if(entrees[i].getEtatLogiqueFil()== null) // verifier si toutes les entrees du composants sont reliées a un autre composant 
				return null;
			if(entrees[i].getEtatLogiqueFil().getNum() == EtatLogique.HAUTE_IMPEDANCE.getNum()) //  verifier si le fil d'entree est en haute impedence . 
				return EtatLogique.HAUTE_IMPEDANCE;
			if(entrees[i].getEtatLogiqueFil().getNum() == EtatLogique.ERROR.getNum()) // verifier si le fil d'entree contient une erreur .
				return EtatLogique.ERROR;
			i++;
		}
		return EtatLogique.ONE;
		
	}
	
	public void initSorties() {
		for(int i=0;i<nombreSortie;i++) // initialiser les fils de sortie
			sorties[i]=new Fil(this);
	}
	
	public void addEtages(ArrayList<Integer> etage) {
		for (int i = 0; i < nombreEntree; i++) {
			entrees[i].addEtages(etage);
		}
	}
	
}
