package noyau;

import java.util.ArrayList;

import controllers.ChronogrammeController;
import controllers.HomeController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Horloge extends Composant implements ElementHorloge,Runnable,ComposantDeChronogramme{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4974039401043051548L;
	public static EtatLogique etat = EtatLogique.ZERO;
	public static long temps=1000;
	public volatile static boolean active = true;// on met l'horloge toujours active pour le test
	transient private ImageView image;
	static double startX=1;
	static double startY=76;
	protected EtatLogique sortieAafficher;
	protected EtatLogique sortieBar;


	public Horloge(String nom,long tempsDeHorloge) {
		super(0,nom);
		nombreSortie = 1;
		sorties[0] = new Fil(this);
		temps=tempsDeHorloge;
		lesCoordonnees = new LesCoordonnees(0, 1, 0);
		this.icon="Horloge/1.png";
	}

	@Override
	public void evaluer() { /// la fonction d'evaluation de l'horloge
		for(Sequentiels s : Circuit.getListeEtages()) /// initialiser les etats precedents des fils horloge de chaque composant
			s.etatPrecHorloge = s.entreeHorloge.getEtatLogiqueFil();
		genererSorties(); // generer un front
		sorties[0].evaluer(); // sert pour initialser le fil de sortie de l'horloge 
		tictac(); // c'est la methode responsable de l'execution en mode synchrone des composants sequentiels
	}
	@Override
	public void genererSorties() { // inverser l'etat de l'horloge pour generer sorte de front
		etat = (etat == EtatLogique.ZERO) ? EtatLogique.ONE : EtatLogique.ZERO ;
		sorties[0].setEtatLogiqueFil(etat);
		sortieAafficher=etat;
		sortieBar=etat.getNum()==1 ? EtatLogique.ZERO: EtatLogique.ONE;
	}

	@Override
	public boolean valider() { // � voir apres si elle est n�cessaire
		return false;
	}

	@Override
	public void addEtages(ArrayList<Integer> etage) { // sert pour la creation des etages dans la simulation
		if (! etage.contains(0)) {
			etage.add(0);
		}
	}

	@Override
	public void run() { /// la fonction qui execute le thread de l'horloge
		active=true;	
		Circuit.initialiser();
		while(active)// tant que l'horloge est active
		{
			if(ChronogrammeController.resimul && HomeController.chrono) { /// verifier si on vient de lancer le chrono ou bien le relancer
				Circuit.defaultValuePin(); /// affecter la valeur par defaut aux pin 
				Circuit.defaultValueSeq(); /// affecter la valeur par defaut aux elts sequentiels			            		
				ChronogrammeController.resimul=false;

			}
			this.evaluer(); /// evaluer l'horloge
			image.setImage(new Image(generatePath()) ); /// changer l'image de l'horloge selon son etat
			if(HomeController.chrono) 
			{		
				Platform.runLater(new Runnable() { /// utiliser dans le tracage du chronogramme
					@Override public void run() {
						ChronogrammeController.tracerFront(etat);
						ChronogrammeController.refrecher();
						ChronogrammeController.valeurSuivi();			                   
						if(etat.getNum()==1)    ChronogrammeController.lightBoxH.setStyle("-fx-background-color:#90EE90;-fx-background-radius:15");
						else ChronogrammeController.lightBoxH.setStyle("-fx-background-color:#303337;-fx-background-radius:15"); 	    
					}
				});
			}
			try {

				Thread.sleep(temps);
			} catch (InterruptedException e) {// exception trait� par la clasee thread

				e.printStackTrace();
			}
		}
	}

	@Override
	public void defaultValue() { /// afecter les valeur par defaut � l'horloge
		// TODO Auto-generated method stub
		super.defaultValue();
		etat = EtatLogique.ZERO;
	}

	@Override
	public String generatePath() { /// generer les images relatives � l'horloge
		// TODO Auto-generated method stub
		if (etat == EtatLogique.ONE) {
			return "Horloge/1.png";
		}
		return "Horloge/0.png";
	}

	@Override
	public void setCord() { /// seter les coordonn�es de sorties de l'horloge
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth(), img.getBoundsInLocal().getHeight() / 2), 0);
	}

	public void stop() {
		active=false;
	}
	@Override
	public void validerComposant() {
		// TODO Auto-generated method stub
	}
	public ImageView getImage() {
		return image;
	}


	public void setImage(ImageView image) {
		this.image = image;
	}

	public EtatLogique getEtat() {
		return etat;
	}


	public void setEtat(EtatLogique etat) {
		this.etat = etat;
	}


	public static  double getStartX() {
		return startX;
	}


	public static void setStartX(double startXh) {
		startX = startXh;
	}


	public static double getStartY() {
		return startY;
	}


	public static void setStartY(double startYh) {
		startY = startYh;
	}


	@Override
	public EtatLogique getSortieAafficher() {
		return sortieAafficher;
	}


	public void setSortieAafficher(EtatLogique sortieAafficher) {
		this.sortieAafficher = sortieAafficher;
	}


	@Override
	public EtatLogique getSortieBar() {
		return sortieBar;
	}


	public void setSortieBar(EtatLogique sortieBar) {
		this.sortieBar = sortieBar;
	}

	public static long getTemps() {
		return temps;
	}

	public static void setTemps(long temps) {
		Horloge.temps = temps;
	}
}