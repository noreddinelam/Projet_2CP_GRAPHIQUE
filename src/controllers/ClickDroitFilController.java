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
import noyau.Circuit;
import noyau.Composant;
import noyau.Fil;
import noyau.InfoPolyline;

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
    	supprimer(infoLine);
    	Stage s = (Stage)supprimer.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void supprimerTous(ActionEvent event) {
    	ArrayList<InfoPolyline> list = Circuit.getListFromPolyline(line);
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
    			infoLine.getDestination().getEntrees()[infoLine.getEntre()] = null;
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
