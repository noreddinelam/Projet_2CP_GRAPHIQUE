package noyau;


import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public abstract class Bascule extends Sequentiels{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2271314050334630883L;
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
	public void defaultValue() { /// affecter les valeurs par defaut à ce composant sequentiel
		// TODO Auto-generated method stub
		sortieAafficher = EtatLogique.ZERO;
		etatAvant = EtatLogique.ZERO;
		sortieBar = EtatLogique.ONE;
		super.defaultValue();
		sorties[0].setEtat(EtatLogique.ZERO);
		sorties[1].setEtat(EtatLogique.ONE);
	}
	
	@Override
	public String generatePath() { /// generer le chemin de l'image des bascules
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + "/" + front.toString() + ".png";
	}
	@Override
	public void setCord() { /// seter les coordonnées de sorties/clear/preset
		// TODO Auto-generated method stub
		lesCoordonnees.setCordEntreeInIndex(new Coordonnees(0, 32.1), 0);
		lesCoordonnees.setCordClear(new Coordonnees(37.8, 0));
		lesCoordonnees.setCordPreset(new Coordonnees(61.4, 0));
		lesCoordonnees.setCordHorloge(new Coordonnees(50.5,103));
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(102, 32.3), 0);
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(102, 68.6), 1);
	}
	
	@Override
	public void derelierComp() { /// pour derelier le composant des fils entrees
		// TODO Auto-generated method stub
		super.derelierComp();
		if (preset.getSource() != null) { /// si le preset est relié
			if (! preset.getSource().equals(this)) { /// si la source est differente de lui meme
				preset.derelierCompFromDestination(this); /// derelier le preset de cette bascule
				ImageView imageView = Circuit.getImageFromComp(this);
				Polyline polyline = preset.polylineParPoint(lesCoordonnees.coordReelesPreset(imageView));
				InfoPolyline info = Circuit.getInfoPolylineFromPolyline(polyline);
				if(info != null) {
					info.setRelier(false);
				}				
			}
		}
	}
	@Override
	public void derelierEntreeFromComp(Fil fil) { /// pour derelier toutes les connexions avec le fil passé en parametre
		// TODO Auto-generated method stub
		super.derelierEntreeFromComp(fil);
		if (preset.equals(fil)) {
			preset = new Fil(null);
			preset.setEtat(EtatLogique.ONE);
		}
	}
	@Override
	public void relierANouveau() { /// relier des connexions qui sont supprimer (cette fonction est utilisé dans la fonction de ctrl + z)
		// TODO Auto-generated method stub
		super.relierANouveau();
		ImageView imageView = Circuit.getImageFromComp(this);
		Polyline polyline = preset.polylineParPoint(lesCoordonnees.coordReelesPreset(imageView));
		if (polyline == null) {
			preset = new Fil(null);
		}
		else {
			Circuit.getInfoPolylineFromPolyline(polyline).setRelier(true);
			preset.addDestination(this);
		}
	}
	
	@Override
	public boolean isDessocier() { /// retourne vrai si le composant est relié
		// TODO Auto-generated method stub
		boolean dessocier =super.isDessocier();
		if (dessocier) {
			if (preset.getSource() != null) {
				dessocier = false;
			}
		}
		return dessocier;
	}
	
	public Fil getPreset() {
		return preset;
	}

	public void setPreset(Fil preset) {
		this.preset = preset;
	}

}

