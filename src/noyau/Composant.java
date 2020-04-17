package noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.relation.Role;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;


public abstract class Composant implements Serializable{
	/*--------- Attributs -------------*/
	protected String nom; // pour le label
	protected String icon; // le lien vers l'icone
	protected Fil entrees[]= new Fil[32] ;
	protected Fil sorties[] = new Fil[32];
	protected EtatLogique etatFinal[] = new EtatLogique[32];
	protected int nombreEntree;
	protected int nombreSortie;
	protected boolean sleep = false;
	protected Direction direction = Direction.Est;
	protected LesCoordonnees lesCoordonnees ;
	
	
	
	
	
	public Composant(int nombreEntree,String nom) {
		this.nombreEntree = nombreEntree;
		this.nom =nom;
		Arrays.fill(etatFinal, EtatLogique.HAUTE_IMPEDANCE);
	}
	/*--------- setters & getters--------------*/
	public String getNom() {
		return nom;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
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
	/*--------- Methodes --------------*/
	public void evaluer() {
		if(valider()) // si le composant est pret 
		{
			genererSorties(); //executer sa fonction logique et mettre le resultat sur le fil de sortie 
			
			for (int i = 0; i < nombreSortie; i++) 
			{
				if(sorties[i].getEtatLogiqueFil().getNum() != etatFinal[i].getNum())  //verifier si l'etat precedent du composant a chang� ou non 
				{
					etatFinal[i]=sorties[i].getEtatLogiqueFil(); //mettre a jour l'etat final du composant 
					sorties[i].evaluer(); //passer au composant suivant reli� au fil de sortie 
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
		if(i < nombreSortie) //  mettre les fils restants � 0
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
		while(i<nombreEntree) {
			if(entrees[i] == null) // verifier si toutes les entrees du composants sont reli�es a un autre composant 
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
	
	public void addEtages(ArrayList<Integer> etage) { // pour la construction des etages pour le sequentiels
		for (int i = 0; i < nombreEntree; i++) {
			entrees[i].addEtages(etage);
		}
	}
	public void defaultValue() {
		for (int i = 0; i < nombreSortie; i++) {
			etatFinal[i] = EtatLogique.HAUTE_IMPEDANCE;
		}
	}
	
	public abstract String generatePath();
	
	public abstract void resetPolyline(Polyline line , double x,double y);
	
	public abstract ArrayList<Polyline> generatePolyline(double x,double y);
	
	public  void derelierComp() { // pour derelier le composant de ces fils d'entrees  (le composant � supprimer)
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] != null) {
				entrees[i].derelierCompFromDestination(this);
			}
		}
	}
	
	public void derelierEntreeFromComp(Fil fil) { // pour enlever une le fil donn� des entrees du composant
		for (int i = 0; i < nombreEntree; i++) {
			if (entrees[i] != null) {
				if (entrees[i].equals(fil)) {
					entrees[i] = null;
				}
			}
		}
	}
	
	public void relierANouveau() { // elle permet de relier � nouveau le composant si il est derelier de ces fils
		for (int i = 0; i < nombreEntree; i++) {
			entrees[i].addDestination(this);
		}
	}
	public abstract void setCord();
	public LesCoordonnees getLesCoordonnees() {
		return lesCoordonnees;
	}
	public void setLesCoordonnees(LesCoordonnees lesCoordonnees) {
		this.lesCoordonnees = lesCoordonnees;
	}
	
	public Fil getFilSortie(int i) {
		if(i < sorties.length) {/// ngoul l sari
			return sorties[i];
		}else {
			return null;
		}
	}
	public int numCmpEntrees(Fil fil) {
		int i = 0;
		while(i<nombreSortie) {
			if(fil == sorties[i])
				return i;
		i++;

		}
		return 0;
	}
	
	public int numCmpSorties(Fil fil) {
		int i = 0;
		while(i<nombreSortie) {
			if(fil == sorties[i])
				return i;
		i++;
		}
		return 0;
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
	
}
