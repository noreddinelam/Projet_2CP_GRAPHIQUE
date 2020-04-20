package noyau;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Horloge extends Composant implements ElementHorloge,Runnable{
	private EtatLogique etat = EtatLogique.ZERO;
	private long temps;
	private volatile boolean active = true;// on met l'horloge toujours active pour le test
	private ImageView image;
	
	public Horloge(String nom,long temps) {
		super(0,nom);
		nombreSortie = 1;
		sorties[0] = new Fil(this);
		this.temps=temps;
		lesCoordonnees = new LesCoordonnees(0, 1, 0);
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
        active=true;
		etat = EtatLogique.ONE;
		this.evaluer();
		Circuit.initialiser();
		while(this.active)// tant que l'horloge est active
		{	
			
			this.evaluer();//on excut l'evaluation
			
			image.setImage(this.getSorties()[0].getEtatLogiqueFil().getNum() ==0 ? new Image("/Horloge/0.png") : new Image("/Horloge/1.png"));
			try {
//				int i=1;// just pour revenir a la ligne a chaque front de 3 bascules
//			    for(Sequentiels sequentiels : Circuit.getListeEtages())// On parcour chaque composant sequentielle
//			    {
//			    	if (sequentiels.getClass().getSimpleName().equals("Compteur")) {
//			    		System.out.print(((Compteur)sequentiels).getValeur() + " | ");
//					}
//			    	else if(sequentiels.getClass().getSimpleName().equals("T")){
//			    		System.out.print(sequentiels.sorties[0].getEtatLogiqueFil() + " |  ");
//					}
//			    	else {
//			    		System.out.print("Valeur : " + ((RegistreDecalage)sequentiels).valeur()+ " |  " + sequentiels.sorties[0].getEtatLogiqueFil() + " |  ");
//					}
//			    	
//			    	if(i==1) // condition de retour a la ligne en fonctions du nombre elt contenu dans la liste eds etages
//			    		{
//			    		System.out.println("\n");
//			    		i=1;
//			    		}
//			    	i++;
//			    	
//			    }   
			    
				Thread.sleep(temps);
				
			} catch (InterruptedException e) {// exception traité par la clasee thread
				e.printStackTrace();
			}
	       
		}
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		if (etat == EtatLogique.ONE) {
			return "Horloge/1.png";
		}
		return "Horloge/0.png";
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(), img.getBoundsInLocal().getHeight() / 2), 0);
	}


	public ImageView getImage() {
		return image;
	}


	public void setImage(ImageView image) {
		this.image = image;
	}

  public void stop() {
	  active=false;
  }
  
	
}
