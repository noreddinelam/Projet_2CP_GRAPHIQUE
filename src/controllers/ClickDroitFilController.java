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
import noyau.Bascule;
import noyau.Circuit;
import noyau.Combinatoires;
import noyau.Composant;
import noyau.Fil;
import noyau.InfoPolyline;
import noyau.Sequentiels;

public class ClickDroitFilController {

	private Polyline line;
	
	private AnchorPane workSpace;
	
    public AnchorPane getPane() {
		return workSpace;
	}

	public void setPane(AnchorPane workSpace) {
		this.workSpace = workSpace;
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
    void supprimer(ActionEvent event) {
    	InfoPolyline infoLine = Circuit.getInfoPolylineFromPolyline(line);
    	System.out.println(infoLine.isRelier());
    	supprimer(infoLine);
    	Stage s = (Stage)supprimer.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void supprimerTous(ActionEvent event) {
    	ArrayList<InfoPolyline> list = Circuit.getListFromPolyline(line);
    	System.out.println("contenu : "+ list);
    	int i = list.size()-1;
    	while(i >= 0) {
    		InfoPolyline infoLine = list.get(i);
    		supprimer(infoLine);

    	  i--;

		}
		Stage s = (Stage)supprimer.getScene().getWindow(); 
    	s.close();
    }
    public void supprimer(InfoPolyline infoLine) {
    	Polyline line = infoLine.getLinePrincipale();
    	if(infoLine.getNbFils() == 0 ) { //On peut le supprimer
    		
    		if(infoLine.isRelier()){ //relier a une entrée
    			Fil fil = new Fil(null);
    			if(infoLine.getEntre() >= 0) {
    				infoLine.getDestination().getEntrees()[infoLine.getEntre()] = null;
				}else if(-5 < infoLine.getEntre()) {
					infoLine.getDestination().getEntrees()[Math.abs(infoLine.getEntre())-1] = null;
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
    			infoLine.setRelier(false);
    		}
    		
    		if(infoLine.getLineParent() != null ) {//n'est pas la racine
    			infoLine.supprimerPremierNoeuds();
    			workSpace.getChildren().remove(line);
    			Circuit.getListFromPolyline(line).remove(new InfoPolyline(line));
    			infoLine = Circuit.getInfoPolylineFromPolyline(infoLine.getLineParent());
    			infoLine.setNbFils(infoLine.getNbFils() - 1);
    			line.getPoints().clear();
    		}else {//la racine
    			infoLine.setNbFils(0);
    			Composant cmpSource = Circuit.getFilFromPolyline(line).getSource();
    			Fil filDeline = Circuit.getFilFromPolyline(line);
    			double posX = Circuit.getImageFromComp(cmpSource).getLayoutX()+cmpSource.getLesCoordonnees().getCordSortieInIndex(cmpSource.numCmpSorties(filDeline)).getX() ;
    			double posY = Circuit.getImageFromComp(cmpSource).getLayoutY()+cmpSource.getLesCoordonnees().getCordSortieInIndex(cmpSource.numCmpSorties(filDeline)).getY() ;
    			cmpSource.resetPolyline(line, posX, posY);
    		}
    	}else {
    		line.setStroke(Color.BLACK);
    	} 		
    }

}
