package noyau;

import java.util.ArrayList;

import javafx.scene.shape.Polyline;

public class SourceConstante extends Composant{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5844118291526446652L;
	private EtatLogique etatLogique ;

	public SourceConstante(EtatLogique etatLogique,String nom) {
		super(0,nom);
		this.nombreSortie = 1;
		this.etatLogique = etatLogique;
		Circuit.ajouterSourceCte(this);
		sorties[0] = new Fil(this);
		sorties[0].setEtatLogiqueFil(etatLogique);
		lesCoordonnees = new LesCoordonnees(0, 1, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void genererSorties() { /// generer la sortie de la source constante
		// TODO Auto-generated method stub
		sorties[0].setEtat(etatLogique);
	}

	@Override
	public boolean valider() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String generatePath() { /// generer les chemins des images relatives au source constante
		// TODO Auto-generated method stub
		if (etatLogique == EtatLogique.ONE) {
			return "SourceConstante/VCC.png";
		}
		return "SourceConstante/MASSE.png";
	}
	
	@Override
	public ArrayList<Polyline> generatePolyline(double x,double y) { /// generer les polylines de sorties
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
	public void setCord() { // seter les coordonnées de sortie
		// TODO Auto-generated method stub
		lesCoordonnees.setCordSortieInIndex(new Coordonnees(Circuit.getImageFromComp(this).getBoundsInLocal().getWidth() / 2, Circuit.getImageFromComp(this).getBoundsInLocal().getHeight()), 0);
	}
	
	@Override
	public void resetPolyline(Polyline line, double x, double y) { /// repositionner les polylines de sorties
		// TODO Auto-generated method stub
		line.getPoints().clear();
		line.getPoints().addAll(x,y,x,y+5);
	}

	public EtatLogique getEtatLogique() {
		return etatLogique;
	}
	@Override
	public void validerComposant() {
		// TODO Auto-generated method stub
	}
	
}