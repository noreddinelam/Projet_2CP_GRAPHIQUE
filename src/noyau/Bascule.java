package noyau;

import java.util.ArrayList;

import javafx.scene.shape.Polyline;

public abstract class Bascule extends Sequentiels{
	
	protected Fil preset = null;
		
	public Bascule(int nombreEntree,String nom,Front front) { // constructeur
		super(nombreEntree,nom,front);
		preset = new Fil(null); // initialiser le fil preset 
		preset.setEtatLogiqueFil(EtatLogique.ONE);
		nombreSortie = 2; 
		// initialiser les etats des fils de sorties de bascules car le probleme c'est que si deux bascules sont au meme etage et relier
		// directement alors il y'aura un probleme dans l'execution .
		lesCoordonnees = new LesCoordonnees(nombreEntree, 2, 0);
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
		sorties[1] = new Fil(this);
		sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
	}

	public void genererSorties() // executer dans le cas des cmd asynchrones
	{
		if(clear.getEtatLogiqueFil().getNum()==0)
		{
			sorties[0].setEtatLogiqueFil(EtatLogique.ZERO);
			sorties[1].setEtatLogiqueFil(EtatLogique.ONE);
		}else
		{
			if(preset.getEtatLogiqueFil().getNum()==0)
			{
				sorties[0].setEtatLogiqueFil(EtatLogique.ONE);
				sorties[1].setEtatLogiqueFil(EtatLogique.ZERO);
			}else
			{
				initialiser(); // initialiser les etats prec des entrees pour l'execution apres dans le mode synchrone 
			}
		}
	}
	
	public boolean valider() { // valider la bascule si ces entrees sont reliées  
		boolean f=false;
		f = (super.validerEntrees() == EtatLogique.ONE) ? true : false ;
		return f;
	}
	
	public boolean validerSyncho() { // valider la bascule en mode synchrone
		boolean f = false;
		if (super.validerEntrees() == EtatLogique.ONE) { // les entrees sont valides
			if(preset.getEtatLogiqueFil()==EtatLogique.ONE && clear.getEtatLogiqueFil()==EtatLogique.ONE) { // mode synchrone
				if (entreeHorloge != null) { // l'horloge est reliée 
					switch (front) {
					case Front_Descendant:{
						if(entreeHorloge.getEtatLogiqueFil() == EtatLogique.ZERO )
						{
							if (etatPrecHorloge == EtatLogique.ONE) { // sert pour la validation du front
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
		}
		return f;
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + front.toString() + ".png";
	}
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 32.1), 0);
		lesCoordonnees.setCordClear(new Coordonnees(37.8, 0));
		lesCoordonnees.setCordPreset(new Coordonnees(61.4, 0));
		lesCoordonnees.setCordHorloge(new Coordonnees(50.5,103));
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(102, 32.3), 0);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(102, 68.6), 1);
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		setCord();	
		Polyline polyline = null;
		double posX ;
		double posY ;
		ArrayList<Polyline> result = new ArrayList<Polyline>();
		ArrayList<InfoPolyline> listPolylines ;
		for (int i = 0; i < 2; i++) {
			listPolylines = new ArrayList<InfoPolyline>();
			posX = x+lesCoordonnees.getCordSortieInIndex(i).getX() ;
			posY = y + lesCoordonnees.getCordSortieInIndex(i).getY();
			polyline = new Polyline(posX ,posY,posX+5,posY);
			listPolylines.add(new InfoPolyline(polyline));
			result.add(polyline);
			Circuit.ajouterFil(sorties[i], listPolylines); 
		}		
		return result;
	}
	
	public Fil getPreset() {
		return preset;
	}

	public void setPreset(Fil preset) {
		this.preset = preset;
	}
}
