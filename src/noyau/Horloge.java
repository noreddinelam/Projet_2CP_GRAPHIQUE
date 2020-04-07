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
		for(Sequentiels s : Circuit.getListeEtages()) /// initialiser les etats precedents des fils horloge de chaque composant
			s.etatPrecHorloge = s.entreeHorloge.getEtatLogiqueFil();
		genererSorties(); // generer un front
		sorties[0].evaluer(); // sert pour initialser le fil de sortie de l'horloge 
		tictac(); // c'est la methode responsable de l'execution en mode synchrone des composants sequentiels
	}
	public void genererSorties() { // inverser l'etat de l'horloge pour generer sorte de front
		etat = (etat == EtatLogique.ZERO) ? EtatLogique.ONE : EtatLogique.ZERO ;
		sorties[0].setEtatLogiqueFil(etat);
	}
	
	public boolean valider() { // à voir apres si elle est nécessaire
		return false;
	}
	
	public void addEtages(ArrayList<Integer> etage) { // sert pour la creation des etages dans la simulation
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
			this.evaluer();//on excut l'evaluation
			try {
				int i=1;// just pour revenir a la ligne a chaque front de 3 bascules
			    for(Sequentiels sequentiels : Circuit.getListeEtages())// On parcour chaque composant sequentielle
			    {
			    	if (sequentiels.getClass().getSimpleName().equals("Compteur")) {
			    		System.out.print(((Compteur)sequentiels).getValeur() + " | ");
					}
			    	else if(sequentiels.getClass().getSimpleName().equals("T")){
			    		System.out.print(sequentiels.sorties[0].getEtatLogiqueFil() + " |  ");
					}
			    	else {
			    		System.out.print("Valeur : " + ((RegistreDecalage)sequentiels).valeur()+ " |  " + sequentiels.sorties[0].getEtatLogiqueFil() + " |  ");
					}
			    	
			    	if(i==1) // condition de retour a la ligne en fonctions du nombre elt contenu dans la liste eds etages
			    		{
			    		System.out.println("\n");
			    		i=1;
			    		}
			    	i++;
			    	
			    }   
			    
				Thread.sleep(temps);
				
			} catch (InterruptedException e) {// exception traité par la clasee thread
				e.printStackTrace();
			}
	       
		}
	}
	
	
}
