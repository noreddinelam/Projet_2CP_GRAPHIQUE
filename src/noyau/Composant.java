package noyau;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import controllers.HomeController;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;


public abstract class Composant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6537732401092535901L;
	/*--------- Attributs -------------*/
	protected String nom=this.getClass().getSimpleName(); // pour le label
	protected String icon="T/Front_Montant.png"; // le lien vers l'icone
	protected Fil entrees[]= new Fil[32] ;
	protected Fil sorties[] = new Fil[32];
	protected EtatLogique etatFinal[] = new EtatLogique[32];
	protected int nombreEntree;
	protected int nombreSortie;
	protected boolean sleep = false;
	protected boolean init = false;
	protected boolean stuckOverFlow = false;
	protected int direction = 0;
	protected LesCoordonnees lesCoordonnees ;
	protected int nbEx = 0 ; //regler la boucle infinie de la formation des etages 
		
	public Composant(int nombreEntree,String nom) {
		this.nombreEntree = nombreEntree;
		if (!nom.isEmpty())this.nom =nom;
		Arrays.fill(etatFinal, EtatLogique.HAUTE_IMPEDANCE);
	}
	public abstract void setCord();
	public abstract void genererSorties();
	public abstract boolean valider();  // verifier si le composant est pret a executer sa fonction logique 
										// valider si les entrees et les sorties sont pretes 

	public void evaluer() {
		if(valider() && !stuckOverFlow) // si le composant est pret 
		{
			try {
				genererSorties(); //executer sa fonction logique et mettre le resultat sur le fil de sortie 

				for (int i = 0; i < nombreSortie; i++) 
				{
					if(sorties[i].getEtatLogiqueFil().getNum() != etatFinal[i].getNum())  //verifier si l'etat precedent du composant a changé ou non 
					{
						etatFinal[i]=sorties[i].getEtatLogiqueFil(); //mettre a jour l'etat final du composant 
						sorties[i].evaluer(); //passer au composant suivant relié au fil de sortie 
					}
				}		
			} catch (StackOverflowError e) {
				// TODO: handle exception
				stuckOverFlow = true;		
			}
		}
	}
	
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
	
	public String concatener(EtatLogique[] tabFils,int tai) { // Role : convertir les etats logiques des fils du "tabFils" de taille "tai" 
		//        en binaire et les concatener 
		int res = 0;
		for (int i = 0; i < tai; i++) {
			res =  res*10 +tabFils[tai-i-1].getNum();
		}
		return Integer.toString(res);
	}
	
	public EtatLogique validerEntrees() { //role :  valider si les entrees du composant sont pretes 
		int i =0;
		EtatLogique etatLogique = EtatLogique.ONE;
		while(i<nombreEntree && etatLogique==EtatLogique.ONE) {
			if(entrees[i] == null) // verifier si toutes les entrees du composants sont reliées a un autre composant 
				etatLogique = null;
			else if(entrees[i].getEtatLogiqueFil().getNum() == EtatLogique.HAUTE_IMPEDANCE.getNum()) //  verifier si le fil d'entree est en haute impedence . 
				etatLogique = EtatLogique.HAUTE_IMPEDANCE;
			else if(entrees[i].getEtatLogiqueFil().getNum() == EtatLogique.ERROR.getNum()) // verifier si le fil d'entree contient une erreur .
				etatLogique = EtatLogique.HAUTE_IMPEDANCE;
			i++;
		}
		return etatLogique;
		
	}
	
	public void initSorties() {
		for(int i=0;i<nombreSortie;i++) // initialiser les fils de sortie
			sorties[i]=new Fil(this);
	}

	public void addEtages(ArrayList<Integer> etage) { // pour la construction des etages pour le sequentiels
		if(nbEx <= Circuit.getListeEtages().size() ) {
			nbEx++;
			for (int i = 0; i < nombreEntree; i++) {
				entrees[i].addEtages(etage);
			}
		}
	}
	public void defaultValue() {//affecter la valeur par defaut au composant apres la simulation 
		init = false;
		nbEx = 0;
		stuckOverFlow = false;
		for (int i = 0; i < nombreSortie; i++) {
			etatFinal[i] = EtatLogique.HAUTE_IMPEDANCE;
		}
	}
	
	public abstract String generatePath();
	
	public void resetPolyline(Polyline line , double x,double y) { /// repositioner les polylines
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x+5,y);
	}
	
	public ArrayList<Polyline> generatePolyline(double x,double y) { /// generer les polylines de sorties du composant
		// TODO Auto-generated method stub
		setCord();	
		Polyline polyline = null;
		double posX ;
		double posY ;
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> listPolylines ;
		for (int i = 0; i < nombreSortie; i++) {
			listPolylines = new ArrayList<InfoPolyline>();
			posX = x+ lesCoordonnees.getCordSortieInIndex(i).getX() ;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			polyline = new Polyline(posX ,posY,posX+5,posY);
			polyline.toBack();
			listPolylines.add(new InfoPolyline(polyline));
			reslut.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines);;
		}		
		return reslut;
	}
	
	public  void derelierComp() { // pour derelier le composant de ces fils d'entrees  (le composant à supprimer)
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] != null) {
				if (! entrees[i].getSource().equals(this)) { // pour savoir si une entree est relié avec une sortie du mm composant
					entrees[i].derelierCompFromDestination(this);
					ImageView imageView = Circuit.getImageFromComp(this);
					Polyline polyline = entrees[i].polylineParPoint(lesCoordonnees.coordReelesEntrees(imageView, i));
					InfoPolyline info = Circuit.getInfoPolylineFromPolyline(polyline);
					if(info != null) {
						info.setRelier(false);
					}					
				}
			}
		}
	}
	
	public void derelierEntreeFromComp(Fil fil) { // pour enlever une le fil donné des entrees du composant
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] != null) {
				if (entrees[i].equals(fil)) {
					entrees[i] = null;
				}
			}
		}
	}
	
	public void relierANouveau() { // elle permet de relier à nouveau le composant si il est derelier de ces fils
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] != null) {
				ImageView imageView = Circuit.getImageFromComp(this);
				Polyline polyline = entrees[i].polylineParPoint(lesCoordonnees.coordReelesEntrees(imageView, i));
				if (polyline == null) {
					entrees[i] = null;
				}
				else {
					Circuit.getInfoPolylineFromPolyline(polyline).setRelier(true);
					Circuit.getInfoPolylineFromPolyline(polyline).setDestination(this);
					entrees[i].addDestination(this);
				}
			}
		}
	}
	
	public Fil getFilSortie(int i) {
		if(i < sorties.length) {/// ngoul l sari
			return sorties[i];
		}else {
			return null;
		}
	}
	public int numCmpEntrees(Fil fil) { /// recuperer le numero de l'entree au il y'a le fil passe comme parametre
		int i = 0;
		while(i<nombreSortie) {
			if(fil == sorties[i])
				return i;
		i++;

		}
		return -1;
	}
	
	public int numCmpSorties(Fil fil) {/// recuperer le numero de la sortie au il y'a le fil passe comme parametre
		int i = 0;
		while(i<nombreSortie) {
			if(fil == sorties[i])
				return i;
		i++;
		}
		return -1;
	}
	 
	public boolean isDessocier() { /// retourne vrai si le composant est derelier
		boolean dessocier = true ;
		int i = 0;
		while(( i < nombreEntree) && (dessocier == true)) { /// savoir si les entrees de ce composants sont reliées
			if (entrees[i] != null) {
				dessocier = false;
			}
			else i++;
		}
		i = 0;
		while((i < nombreSortie) && (dessocier == true) ) /// /// savoir si les sorties de ce composants sont reliées
		{
			if (sorties[i].getDestination().size() != 0) {
				dessocier = false ;
			}
			else i++;
		}
		return dessocier;
		
	}
	
	public void validerComposant() { /// utiliser pour detecter les erreurs relative à un composant donné
		// TODO Auto-generated method stub
		ArrayList<ExceptionProgramme> arrayList = new ArrayList<ExceptionProgramme>();
		for (int i = 0; i < nombreEntree; i++) { /// savoir si une entree est manquante
			if (entrees[i] == null) {
				arrayList.add(new EntreeManquante(TypesExceptions.ERREUR, this, i));
			}
		}
		if (arrayList.size() == nombreEntree) { /// si toutes les entrees sont dereliées
			Circuit.AjouterUneException(new ComposantNonRelier(TypesExceptions.ALERTE, this));
		}
		else {
			if (arrayList.size() != 0) {
				Circuit.AjouterListeException(arrayList);
				Circuit.ajouterCompErrone(this);
			}
		}
	}

	public void setNombreSortieAndUpdateFil(int nombreSortie) { /// mettre à jour le nombre de sortie et generer les fils nécessaires
		this.nombreSortie = nombreSortie;
		for (int i = 0; i < nombreSortie; i++) {
			sorties[i] = new Fil(this);
		}
	}
	public void initialiserSortieParZero() {
		if(!init) // si le composant est pret 
		{
			init = true;
			for (int i = 0; i < nombreSortie; i++) 
			{
				sorties[i].setEtatLogiqueFil(EtatLogique.ZERO); 
				sorties[i].initialiserSortieParZero(); 
			}		
		}
	}

	public String getNom() {
		return nom;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNombreEntree() {
		return nombreEntree;
	}

	public void setNombreEntree(int nombreEntree) {
		this.nombreEntree = nombreEntree;
	}
	
	public int getNombreSortie() {
		return nombreSortie;
	}
	public void setNombreSortie(int nombreSortie) {
		this.nombreSortie = nombreSortie;
	}
	public Fil getFilSortieByNum(int i) {
		return sorties[i];
	}
	public Fil[] getEntrees() {
		return entrees;
	}
	public void setEntrees(Fil[] entrees) {
		this.entrees = entrees;
	}
	public Fil[] getSorties() {
		return sorties;
	}
	public void setSorties(Fil[] sorties) {
		this.sorties = sorties;
	}
	
	public LesCoordonnees getLesCoordonnees() {
		return lesCoordonnees;
	}
	public void setLesCoordonnees(LesCoordonnees lesCoordonnees) {
		this.lesCoordonnees = lesCoordonnees;
	}
	public int getNbEx() {
		return nbEx;
	}
	public void setNbEx(int nbEx) {
		this.nbEx = nbEx;
	}
	
}