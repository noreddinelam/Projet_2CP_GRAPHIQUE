package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import noyau.Composant;
import noyau.EtatLogique;
import noyau.Pin;
import javafx.scene.input.MouseEvent;



public class ChronogrammeController implements Initializable {
	
	
	    @FXML
	    private ImageView detectionBar;
	    
	    @FXML
	    private AnchorPane chronogrameField;
	    
	    @FXML
	    private TableView<Composant> tableView;

	    @FXML
	    private TableColumn<String, Composant> elementsColumn;

	    @FXML
	    private TableColumn<String, Composant> valuesColumn;
	    
	    @FXML
	    private TableColumn<String, Composant> valeurSuivie; 

	    @FXML
	    private ImageView playButton;

	    @FXML
	    private ImageView stopBotton;

	    @FXML
	    private ImageView pauseBotton;
        

	    @FXML
	    private ScrollPane scrollPane;
	    
	    
	    double origineDeChronogramme; 
	    double translationDeSouris;
	  
	 
	
	    @FXML
	    void onMouseDragged(MouseEvent event) {
	    	detectionBar.setOnMouseDragged(imageOnMouseDraggedEventHandler);
	    
			 

	    }

	    @FXML
	    void onMousePressed(MouseEvent event) {
	    	detectionBar.setOnMousePressed(imageOnMousePressedEventHandler);
	    	 
	    }
	    
	    EventHandler<MouseEvent> imageOnMousePressedEventHandler = 
	            new EventHandler<MouseEvent>() {
	     
	            @Override
	            public void handle(MouseEvent t) {
	                origineDeChronogramme = t.getSceneX();
	                translationDeSouris = ((ImageView)(t.getSource())).getTranslateX();
	           
	            }
	        };
	        
	     EventHandler<MouseEvent> imageOnMouseDraggedEventHandler = 
	                new EventHandler<MouseEvent>() {
	         
	                @Override
	                public void handle(MouseEvent t) {
	                	
	                    double distancePpX = t.getSceneX() - origineDeChronogramme;
	                    double valeurDeTranslation = translationDeSouris + distancePpX;             
	                     if((valeurDeTranslation <1330)&&(valeurDeTranslation >0))
	                    ((ImageView)(t.getSource())).setTranslateX(valeurDeTranslation);
	                     
	                }
	            };
       
         

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		 
		  
        scrollPane.setPannable(true);
  			//Pin pin=new Pin(true, "Pin");
  		//	Pin pin1=new Pin(true, "Pin1");
        //	 elementsColumn.setCellValueFactory(new PropertyValueFactory<String,Composant>("nom"));
// 		//	valuesColumn.setCellValueFactory(new PropertyValueFactory<String,Composant>("sorties"));
// 			pin.setSorties(EtatLogique.ONE);
// 			pin1.setSorties(EtatLogique.ZERO);
// 	    	tableView.getItems().add(pin);
// 	    	tableView.getItems().add(pin1);
    	scrollPane.setPannable(false);
 	  
 	     
		}

	  
	 
}

