package noyau;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class RegistreDecalage extends Sequentiels {
	
	private int taille;
	private EtatLogique[] valeur;
	private boolean decalageDroite; // si true dec droite sinon dec gauche
	private Fil load ;
	public RegistreDecalage(int taille,String nom,boolean dec,Front front) { // constructeur 
		super(taille+1,nom,front);
		valeur = new EtatLogique[taille];
		load = new Fil(null);
		load.setEtatLogiqueFil(EtatLogique.ONE);
		this.nombreSortie = 1;
		this.decalageDroite = dec ;
		this.taille = taille ;
		Arrays.fill(valeur, EtatLogique.ZERO);
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(EtatLogique.HAUTE_IMPEDANCE);
		lesCoordonnees = new LesCoordonnees(nombreEntree, 1, 0);
	}

	public void genererSorties() { // executer une des fonction du registre ( decalage a droite , remise a zero )
		if(this.clear.getEtatLogiqueFil().getNum()==0)
		{
			Arrays.fill(valeur, EtatLogique.ZERO);//initialiser le tableau avec ZERO
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		}
		else {
			initialiser(); // initialisation des etats precedents
		}
			
	}
	
	public void decalageDroite() {
		EtatLogique bit = entrees[0].getEtatLogiqueFil();
		EtatLogique res = valeur[taille-1]; // recuperer le bit de sortie a partir du bit du  poids faible 
		
		for (int i=taille - 1 ; i> 0  ;i--)
		{
			valeur[i] = valeur[i-1]; // decaler les bits a droite 
		}
		valeur[0] = bit ; // decaler le bit d'entree vers le poids faible du registre
		sorties[0].setEtatLogiqueFil(res);// mettre la valeur du dernier bit (bit faible) dans le fil .
	}
	
	public void decalageGauche() {
		EtatLogique bit = entrees[0].getEtatLogiqueFil();
		EtatLogique res = valeur[0]; // recuperer le bit de sortie a partir du bit du  poids fort 
		
		for (int i=0 ; i<taille-1  ;i++)
		{
			valeur[i] = valeur[i+1]; // decaler les bits a gauche 
		}
		valeur[taille-1] = bit ; // decaler le bit d'entree vers le poids fort du registre
		sorties[0].setEtatLogiqueFil(res);// mettre la valeur du dernier bit (bit fort) dans le fil .
	}
	
	@Override
	public EtatLogique validerEntrees() {
		// TODO Auto-generated method stub
		if(entrees[0] == null) // verifier si  l'entree du composants est reliée a un autre composant 
			return null;
		if(entrees[0].getEtatLogiqueFil().getNum() == EtatLogique.HAUTE_IMPEDANCE.getNum()) //  verifier si le fil d'entree est en haute impedence . 
			return EtatLogique.HAUTE_IMPEDANCE;
		if(entrees[0].getEtatLogiqueFil().getNum() == EtatLogique.ERROR.getNum()) // verifier si le fil d'entree contient une erreur .
			return EtatLogique.ERROR;
		return EtatLogique.ONE;
	}

	public boolean valider() { // valider le circuit si clear est à 0 ou bien load à 0 à condition d'avoir toutes les entrees branchées 
		if (clear.getEtatLogiqueFil().getNum() == 0) {
			return true;
		}
		if (load.getEtatLogiqueFil().getNum() == 0 && super.validerEntrees() == EtatLogique.ONE) {
			return true;
		}
		return false;
	}
	public int valeur() { // Role : recuperer la valeur contenue dans le registre 
		int res = 0 ;
		int p = 1 ;
		for (int i = taille -1; i >= 0; i--) 
		{
			res = res + valeur[i].getNum() * p  ;
			p *= 10 ;
		}
		return res ;
	}

	@Override
	public void genererSortiesSyncho() { // pour generer la sortie en mode synchrone ou load à 0
		// TODO Auto-generated method stub
		if (this.load.getEtatLogiqueFil().getNum() == 0) {
			for (int i = 1; i < nombreEntree; i++) {
				valeur[i-1] = entrees[i].getEtatLogiqueFil();
			}
			sorties[0].setEtatLogiqueFil(valeur[taille-1]);
		}
		else {
			if (decalageDroite) { // decalage droite
				decalageDroite();
			}
			else { //decalage gauche 
				decalageGauche();
			}
		}
	}

	@Override
	public boolean validerSyncho() {
		// TODO Auto-generated method stub
		boolean f = false;
		if (clear.getEtatLogiqueFil().getNum() == 1) { // valider le compteur soit dans le mode synchrone ou load à 0 et toutes les entrées sont toutes validées
			if((load.getEtatLogiqueFil().getNum() == 1 && validerEntrees().getNum()== 1)||(load.getEtatLogiqueFil().getNum() == 0 && super.validerEntrees() == EtatLogique.ONE))// verification de l'horloge à revoir .
				if (entreeHorloge != null) {
					switch (front) {
					case Front_Descendant:{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ZERO )
						{
							if (etatPrecHorloge == EtatLogique.ONE) {
								f = true;
							}
						}
						else {
							sleep = false ;
							etatPrecHorloge = EtatLogique.ONE;
						}
					}break;
					case Front_Montant :{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ONE )
						{
							if (etatPrecHorloge == EtatLogique.ZERO) {
								f = true;
							}
						}
						else {
							sleep = false ;
							etatPrecHorloge = EtatLogique.ZERO;
						}
					}break;
					}
				}
		}
		return f;
	}
	
	@Override
	public void initialiser() {// initialiser les etats precedents qui servent si le load à 0
		// TODO Auto-generated method stub
		for (int i = 1; i < nombreEntree; i++) {
			etatPrec[i] = entrees[i].getEtatLogiqueFil();
		}
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		String path = "RegistreDecalage/"+String.valueOf(taille);
		if (decalageDroite) {
			path = path + "Droite";
		}
		else {
			path = path + "Gauche";
		}
		return path+front.toString()+".png";
	}

	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		ImageView imageView = Circuit.getImageFromComp(this);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(imageView.getFitWidth(),imageView.getFitHeight() / 2), 0);
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 21.1), 0);
		lesCoordonnees.setCordHorloge(new Coordonnees(0, 44.5));
		switch (taille) {
		case 2:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(72.9, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(20.6, 0), 2);
			
			lesCoordonnees.setCordLoad(new Coordonnees(37.5, 66));
			lesCoordonnees.setCordClear(new Coordonnees(65, 66));
		}break;
		case 3:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(73.1, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(47.9, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(22.9, 0), 3);
			
			lesCoordonnees.setCordLoad(new Coordonnees(37.5, 66));
			lesCoordonnees.setCordClear(new Coordonnees(65, 66));
		}break;
		case 4:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(95.2, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(69.9, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(44.5, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(19.4, 0), 4);
			
			lesCoordonnees.setCordLoad(new Coordonnees(38.7, 66));
			lesCoordonnees.setCordClear(new Coordonnees(76.6, 66));
		}break;
		case 5:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(95, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(76.9, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(58.6, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(40.5, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(22.2, 0), 5);
			
			lesCoordonnees.setCordLoad(new Coordonnees(38.6, 66));
			lesCoordonnees.setCordClear(new Coordonnees(76.4, 66));
		}break;
		case 6:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(112.8, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(94.9, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(76.5, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(58.4, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(39.7, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(21.6, 0), 6);
			
			lesCoordonnees.setCordLoad(new Coordonnees(39.5, 66));
			lesCoordonnees.setCordClear(new Coordonnees(88.3, 66));
		}break;
		case 7:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(130.9, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(112.8, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(94.4, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(76.5, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(57.9, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(39.5, 0), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(21.3, 0), 7);
			
			lesCoordonnees.setCordLoad(new Coordonnees(52.5, 66));
			lesCoordonnees.setCordClear(new Coordonnees(101.3, 66));
		}break;
		case 8:{
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(131, 0), 1);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(114.8, 0), 2);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(99.3, 0), 3);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(83.4, 0), 4);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(67.1, 0), 5);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(51.2, 0), 6);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(35.13, 0), 7);
			lesCoordonnees.setCordEntreeInIndex(new Coordonnees(18.8, 0), 8);
			
			lesCoordonnees.setCordLoad(new Coordonnees(52.5, 66));
			lesCoordonnees.setCordClear(new Coordonnees(101.3, 66));
		}break;
		}
	}
	
	@Override
	public void derelierComp() {
		// TODO Auto-generated method stub
		super.derelierComp();
		if (load != null) {
			load.derelierCompFromDestination(this);
		}
	}
	
	@Override
	public void derelierEntreeFromComp(Fil fil) {
		// TODO Auto-generated method stub
		super.derelierEntreeFromComp(fil);
		if (load != null) {
			if (load.equals(fil)) {
				load.derelierCompFromDestination(this);
			}
		}
	}
	
	@Override
	public void relierANouveau() {
		// TODO Auto-generated method stub
		super.relierANouveau();
		if(load != null) load.addDestination(this);
	}
	
	@Override
	public boolean isDessocier() {
		// TODO Auto-generated method stub
		boolean dessocier =super.isDessocier();
		if (dessocier) {
			if (load != null) {
				dessocier = false;
			}
		}
		return dessocier;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}

	public void setValeur(EtatLogique[] valeur) {
		this.valeur = valeur;
	}

	public void setDecalageDroite(boolean decalageDroite) {
		this.decalageDroite = decalageDroite;
	}

	public void setLoad(Fil load) {
		this.load = load;
	}
	public int getTaille() {
		return taille;
	}
	public boolean isDecalageDroite() {
		return decalageDroite;
	}
	
}
