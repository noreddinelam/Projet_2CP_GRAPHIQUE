package noyau;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Pin extends Composant implements Affichage,ElementHorloge,ComposantDeChronogramme{
	private boolean input ; // INPUT= vrai ->  entree // faux -> sortie 
	private EtatLogique etat = EtatLogique.ZERO;
	private boolean horloge = false ; // pour savoir si le pin fonctionne comme horloge ou pas .
	private double startX;
	private double startY;
	boolean changed=false;
	public boolean getInput()
	{
		return this.input;
	}
	public Pin(boolean input,String nom) {
		// TODO Auto-generated constructor stub
		super(0,nom);
		this.input = input;
		if (input) { // pour verifier si c'est un pin d'entree ou de sortie
			nombreSortie = 1;
			sorties[0] = new Fil(this);
			Circuit.ajouterEntree(this);
			lesCoordonnees = new LesCoordonnees(0,1,0);
		}else {
			nombreEntree = 1;
			nombreSortie = 0;
			Circuit.ajouterSortie(this);
			lesCoordonnees = new LesCoordonnees(1,0,0);
		}
		this.icon="Pin/0Input.png";
	}
	
	@Override
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
			if (horloge == true) { // verifier si le pin sert comme horloge ou pas
				tictac();
			}
		}else // to be continued ...
		{
			//signaler les erreurs ..
		}
	}
	
	@Override
	public void genererSorties() { // executer la fonction du pin 
		if(input) // si pin est une entree ( pour definir les entrees du circuit ) 
			sorties[0].setEtatLogiqueFil(etat); // transferer l'etat du pin au fil de sortie 
		else {   // si pin est une sortie ( pour affichage de la valeur de la sortie du circuit )
			changed= etat.getNum() == entrees[0].getEtatLogiqueFil().getNum() ? false : true;
			etat = entrees[0].getEtatLogiqueFil(); // transferer l'etat du fil de sortie au pin 			
			ImageView img = Circuit.getImageFromComp(this);
			img.setImage(new Image(generatePath()));
		
		}
	}
	
	@Override
	public boolean valider() {
		return true;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addEtages(ArrayList<Integer> etage) { // sert pour la creation des etages dans la simulation
		if (! etage.contains(0)) {
			etage.add(0);
			this.horloge = true ; // mettre le pin comme etant une horloge
		}
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		String path;
		switch (etat) {
		case ONE:{
			path = "Pin/1";
		}break;

		default:{
			path = "Pin/0";
		}break;
		}
		if (input) {
		
			return path + "Input" +".png";
		}
	
		return path + "Output" +".png";
		
		
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		ImageView img = Circuit.getImageFromComp(this);
		if (input) {
			lesCoordonnees.setCordSortieInIndex(new Coordonnees(img.getBoundsInLocal().getWidth() / 2, img.getBoundsInLocal().getHeight()), 0);
		}
		else {
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(img.getBoundsInLocal().getWidth() / 2, img.getBoundsInLocal().getHeight()), 0);
		}
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		setCord();
		double posX = x+lesCoordonnees.getCordSortieInIndex(0).getX();
		double posY = y+lesCoordonnees.getCordSortieInIndex(0).getY();
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		if (input) {
			Polyline polyline = new Polyline(posX,posY,posX,posY+5);
			polyline.setStrokeWidth(3);
			ArrayList<InfoPolyline> listPolylines = new ArrayList<InfoPolyline>();
			listPolylines.add(new InfoPolyline(polyline));
			Circuit.ajouterFil(sorties[0], listPolylines);
			reslut.add(polyline);
		}		
		return reslut;
	}

	@Override
	public void defaultValue() {
		// TODO Auto-generated method stub
		if (input) {
			super.defaultValue();
		}
		etat = EtatLogique.ZERO;
		changed=false;
		ImageView img = Circuit.getImageFromComp(this);
		Image image = new Image(generatePath());
		img.setImage(image);
		
	}
	@Override
	public void resetPolyline(Polyline line , double x,double y) {
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x,y+5);
	}
	@Override
	public void validerComposant() {
		// TODO Auto-generated method stub
		if (!input) {
			if (entrees[0] == null) {
				Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE, this));
			}
		}
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
		Circuit.getImageFromComp(this).setImage(new Image(generatePath()));
	}
	@Override
	public EtatLogique getSortieAafficher() {
		// TODO Auto-generated method stub
		return etat;
	}
	@Override
	public EtatLogique getSortieBar() {
		// TODO Auto-generated method stub
		return this.etat.getNum()==0? EtatLogique.ONE : EtatLogique.ZERO;
	}
	public double getStartChronoX() {
		return startX;
	}
	public void setStartChronoX(double startX) {
		this.startX = startX;
	}
	public double getStartChronoY() {
		return startY;
	}
	public void setStartChronoY(double startY) {
		this.startY = startY;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	
	
	
}