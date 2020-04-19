package noyau;

import java.util.ArrayList;

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
	public ArrayList<Polyline> generatePolyline(double x,double y) {
		// TODO Auto-generated method stub
		setCord();	
		ArrayList<Polyline> reslut = new ArrayList<Polyline>();
		double posX = x+lesCoordonnees.getCordSortieInIndex(0).getX() ;
		double posY = y + lesCoordonnees.getCordSortieInIndex(0).getY();
		Polyline polyline = new Polyline(posX ,posY,posX,posY+5);
		ArrayList<InfoPolyline> listPolylines = new ArrayList<InfoPolyline>();
		listPolylines.add(new InfoPolyline(polyline));
		reslut.add(polyline);
		Circuit.ajouterFil(sorties[0], listPolylines);
		return reslut;
	}
	
	@Override
	public void setCord() {
		// TODO Auto-generated method stub
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(Circuit.getImageFromComp(this).getBoundsInLocal().getWidth() / 2, Circuit.getImageFromComp(this).getBoundsInLocal().getHeight()), 0);
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) {
		// TODO Auto-generated method stub
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x,y+5);
	}

	public EtatLogique getEtatLogique() {
		return etatLogique;
	}
	
}