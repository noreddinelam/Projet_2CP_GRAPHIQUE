package controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import noyau.Actions;
import noyau.Bascule;
import noyau.Circuit;
import noyau.Combinatoires;
import noyau.Composant;
import noyau.Donnes;
import noyau.Fil;
import noyau.InfoPolyline;
import noyau.Sequentiels;

public class ClickDroitFilController {

	private Polyline line;
	
	private static AnchorPane workSpace;
	
    public static AnchorPane getPane() {
		return workSpace;
	}

	public static void setPane(AnchorPane workSpace) {
		ClickDroitFilController.workSpace = workSpace;
	}

	public Polyline getLine() {
		return line;
	}

	public void setLine(Polyline line) {
		this.line = line;
	}

	@FXML
    private Pane pane;

    @FXML
    private Button supprimer;

    @FXML
    private Button supprimerTous;

    @FXML
    void supprimer(ActionEvent event) { /// supprimer le fil d'un composant
    	InfoPolyline infoLine = Circuit.getInfoPolylineFromPolyline(line);
    	if (infoLine.getLineParent() != null && infoLine.getNbFils() == 0) {
    		sauveGarderSuppressionFil(infoLine);
		}
    	supprimer(infoLine);
    	Stage s = (Stage)supprimer.getScene().getWindow();
    	s.close();
    }

    @FXML
    void supprimerTous(ActionEvent event) { /// appliquer une suppression totale des fils
    	ArrayList<InfoPolyline> list = Circuit.getListFromPolyline(line);
    	sauveGarderSuppressionToutFil(new ArrayList<InfoPolyline>(list)); /// faire une sauvegarder qui va aider dans le ctrl z
    	int i = list.size()-1;
    	while(i >= 0) {
    		InfoPolyline infoLine = list.get(i);
    		supprimer(infoLine);
    	  i--;
		}
		Stage s = (Stage)supprimer.getScene().getWindow(); 
    	s.close();
    }
    public static void supprimer(InfoPolyline infoLine) { /// la fonction responsable de suppression des fils graphique et noyau
    	if(infoLine!=null) {
    	Polyline line = infoLine.getLinePrincipale();
    	if(infoLine.getNbFils() == 0 ) { //On peut le supprimer
    		
    		if(infoLine.isRelier()){ // derelier la connexion avec le fil supprimé
    			Fil fil = new Fil(null);
    			if(infoLine.getEntre() >= 0) {
    				infoLine.getDestination().getEntrees()[infoLine.getEntre()] = null;
				}else if(-5 < infoLine.getEntre()) {
					((Combinatoires)infoLine.getDestination()).getCommande()[Math.abs(infoLine.getEntre())-1] = null;
				}else if(infoLine.getEntre() == -5) {
					((Sequentiels)infoLine.getDestination()).setEntreeHorloge(null);
				}else if(infoLine.getEntre() == -6) {
					((Sequentiels)infoLine.getDestination()).setClear(fil);
				}else if(infoLine.getEntre() == -7) {
					((Bascule)infoLine.getDestination()).setPreset(fil);
				}else if(infoLine.getEntre() == -8) {
					((Sequentiels)infoLine.getDestination()).setLoad(fil);
				}
    			
    			Circuit.getFilFromPolyline(line).getDestination().remove(infoLine.getDestination());
    		}
    		
    		if(infoLine.getLineParent() != null ) {//n'est pas la racine
    			infoLine.supprimerPremierNoeuds();
    			workSpace.getChildren().remove(line);
    			Circuit.getListFromPolyline(line).remove(new InfoPolyline(line));
    			infoLine = Circuit.getInfoPolylineFromPolyline(infoLine.getLineParent());
    			infoLine.setNbFils(infoLine.getNbFils() - 1);
    		}else {//la racine
    			infoLine.setNbFils(0);
    			infoLine.setRelier(false);
    			Composant cmpSource = Circuit.getFilFromPolyline(line).getSource();
    			Fil filDeline = Circuit.getFilFromPolyline(line);
    			double posX = Circuit.getImageFromComp(cmpSource).getLayoutX()+cmpSource.getLesCoordonnees().getCordSortieInIndex(cmpSource.numCmpSorties(filDeline)).getX() ;
    			double posY = Circuit.getImageFromComp(cmpSource).getLayoutY()+cmpSource.getLesCoordonnees().getCordSortieInIndex(cmpSource.numCmpSorties(filDeline)).getY() ;
    			cmpSource.resetPolyline(line, posX, posY);
    		}
    	}
    	line.setStroke(Color.BLACK);
    	}
    }
    
    public void sauveGarderSuppressionFil(InfoPolyline infoPolyline) { /// appliquer une sauvegarde de suppression fil
		Donnes sauveGarde= new Donnes();
		if (infoPolyline.getLineParent() != null) {
			Polyline parent = new Polyline();
			parent.getPoints().addAll(infoPolyline.getLineParent().getPoints());
			sauveGarde.setParent(parent);
		}
		sauveGarde.setFil(Circuit.getFilFromPolyline(infoPolyline.getLinePrincipale()));
		sauveGarde.setTypeDaction(Actions.SuppressionFil);
		sauveGarde.setInfoPolyline(infoPolyline);
		HomeController.undoDeque.addFirst(sauveGarde);
	}
    
    public void sauveGarderSuppressionToutFil(ArrayList<InfoPolyline> infoPolylines) { /// appliquer une sauvegarde de suppression totale des fils
    	Donnes donnes = new Donnes(); /// donnees est la classe essentiel dans l'operation du ctrl + z
    	ArrayList<Polyline> result=new ArrayList<Polyline>();
    	for (InfoPolyline infoPolyline : infoPolylines) {
    		if (infoPolyline.getLineParent() != null) {
    			Polyline parent = new Polyline();
    			parent.getPoints().addAll(infoPolyline.getLineParent().getPoints());
    			result.add(parent);
			}
		}
    	donnes.setArrayListInfoPoly(infoPolylines);
    	donnes.setListPolyParent(result);
    	donnes.setFil(Circuit.getFilFromPolyline(infoPolylines.get(0).getLinePrincipale()));
    	donnes.setTypeDaction(Actions.SuppressionToutFil);
    	HomeController.undoDeque.addFirst(donnes);
    }

}
