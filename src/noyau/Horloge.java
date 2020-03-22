package noyau;

import java.util.ArrayList;

public class Horloge extends Composant implements ElementHorloge,Runnable{
	
	private EtatLogique etat = EtatLogique.ONE;
	private long temps;
	private boolean active = true;// on met l'horloge toujours active pour le test
	
	public Horloge(String nom,long temps) {
		super(0,nom);
		nombreSortie = 1;
		sorties[0] = new Fil(this);
		this.temps=temps;
	}

	
	public void executer() {
		
	}
	public void evaluer() {
		for(Sequentiels s : Circuit.getListeEtages()) /// à revoir 
			s.etatPrecHorloge = s.entreeHorloge.getEtatLogiqueFil();
		genererSorties();
		sorties[0].evaluer();
		tictac();
	}
	public void genererSorties() {
		etat = (etat == EtatLogique.ZERO) ? EtatLogique.ONE : EtatLogique.ZERO ;
		sorties[0].setEtatLogiqueFil(etat);
	}
	
	public boolean valider() {
		return false;
	}
	
	public void addEtages(ArrayList<Integer> etage) {
		if (! etage.contains(0)) {
			etage.add(0);
		}
	}


	@Override
	public void run() {
		this.evaluer();
		Circuit.initialiser();
		
		while(this.active)// tant que l'horloge est active
		{
			try {
				int i=1;// just pour revenir a la ligne a chaque front de 3 bascules
			    for(Sequentiels sequentiels : Circuit.getListeEtages())// On parcour chaque composant sequentielle
			    {
			    	System.out.print(sequentiels.sorties[0].getEtatLogiqueFil() + " |  ");
			    	
			    	if(i==3) // condition de retour a la ligne 
			    		{
			    		System.out.println("\n");
			    		i=1;
			    		}
			    	i++;
			    	
			    }   
			    this.evaluer();//on excut l'evaluation
				Thread.sleep(temps);
				
			} catch (InterruptedException e) {// exception traité par la clasee thread
				e.printStackTrace();
			}
	       
		}
	}
	
	
}
