package noyau;

import javafx.scene.shape.Polyline;

public class SourceConstante extends Composant{
	
	private EtatLogique etatLogique ;

	public SourceConstante(EtatLogique etatLogique,String nom) {
		super(0,nom);
		this.nombreSortie = 1;
		this.etatLogique = etatLogique;
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(etatLogique);
		lesCoordonnees = new LesCoordonnees(0, 1, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void genererSorties() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean valider() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String generatePath() {
		// TODO Auto-generated method stub
		if (etatLogique == EtatLogique.ONE) {
			return "SourceConstante/VCC.png";
		}
		return "SourceConstante/MASSE.png";
	}
	
	@Override
	public Polyline generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(Circuit.getImageFromComp(this).getBoundsInLocal().getWidth() / 2, Circuit.getImageFromComp(this).getBoundsInLocal().getHeight()), 0);
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		
	}
}
