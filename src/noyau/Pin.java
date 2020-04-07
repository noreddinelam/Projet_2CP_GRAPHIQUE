package noyau;

import java.util.ArrayList;

public class Pin extends Composant implements Affichage,ElementHorloge{
	private boolean input ; // INPUT= vrai ->  entree // faux -> sortie 
	private EtatLogique etat = EtatLogique.ZERO;
	private boolean horloge = false ; // pour savoir si le pin fonctionne comme horloge ou pas .
	
	public boolean getInput()
	{
		return this.input;
	}
	public Pin(boolean input,String nom) {
		// TODO Auto-generated constructor stub
		super(0,nom);
		this.input = input;
		if (input) {
			nombreSortie = 1;
			sorties[0] = new Fil(this);
			Circuit.ajouterEntree(this);
		}else {
			nombreEntree = 1;
			nombreSortie = 0;
			Circuit.ajouterSortie(this);
		}
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
			if (horloge == true) {
				tictac();
			}
		}else // to be continued ...
		{
			//signaler les erreurs ..
		}
	}
	
	public void genererSorties() { // executer la fonction du pin 
		if(input) // si pin est une entree ( pour definir les entrees du circuit ) 
			sorties[0].setEtatLogiqueFil(etat); // transferer l'etat du pin au fil de sortie 
		else  // si pin est une sortie ( pour affichage de la valeur de la sortie du circuit )
			etat = entrees[0].getEtatLogiqueFil(); // transferer l'etat du fil de sortie au pin 
	}
	
	public boolean valider() {
		return true;
	}
	public boolean isInput() {
		return input;
	}
	public void setInput(boolean input) {
		this.input = input;
	}
	public EtatLogique getEtat() {
		return etat;
	}
	public void setEtat(EtatLogique etat) {
		this.etat = etat;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	public void addEtages(ArrayList<Integer> etage) {
		if (! etage.contains(0)) {
			etage.add(0);
			this.horloge = true ;
		}
	}

}
