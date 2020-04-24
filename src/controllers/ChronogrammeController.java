package controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;



import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.PointLight;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import noyau.Circuit;
import noyau.Composant;
import noyau.ComposantDeChronogramme;
import noyau.EtatLogique;
import noyau.Horloge;
import noyau.Pin;
import noyau.Sequentiels;
import sun.java2d.d3d.D3DSurfaceData;
import javafx.scene.input.MouseEvent;



public class ChronogrammeController implements Initializable {
	
	
	    @FXML
	    private ImageView detectionBar;
	    
	   public static ArrayList<Sequentiels> composantDechrono = new ArrayList<Sequentiels>();
	   
	    @FXML
	    private AnchorPane chronogrameField;
	    private static Horloge horlogeDecHRONO;
	    
	    @FXML
	    private  TableView<ComposantDeChronogramme> tableView;
        
	    private static TableView<ComposantDeChronogramme> extTableView;
	    @FXML
	    private  TableColumn<ComposantDeChronogramme, String> elementsColumn;

	    @FXML
	    private  TableColumn<ComposantDeChronogramme, String>  valeurQ;
	    private static AnchorPane field;
	    @FXML
	    private  TableColumn<ComposantDeChronogramme, String>  horlogeColumn;
	    @FXML
	    private TableColumn<ComposantDeChronogramme, String> valeurQbar; 

	    @FXML
	    private ImageView playButton;
	   private static ArrayList<Polyline> frontList= new ArrayList<Polyline>();

	    @FXML
	    private ImageView stopBotton;

	    @FXML
	    private ImageView pauseBotton;
        public static boolean first=true;

	    @FXML
	    private ScrollPane scrollPane;
	    @FXML
	    private Line line;

	    @FXML
	    private PointLight p1;

	    @FXML
	    private PointLight p2;

	    @FXML
	    private PointLight p3;

	    @FXML
	    private PointLight p4;

	    @FXML
	    private PointLight p5;

	    @FXML
	    private PointLight p6;

	    @FXML
	    private PointLight p7;

	    @FXML
	    private PointLight p8;

	    @FXML
	    private PointLight p9;
	    
	    double origineDeChronogramme; 
	    double translationDeSouris;
	   public static ArrayList<PointLight> pointsDeDepart=new ArrayList<PointLight>();
	    
	    @FXML
	    void arreter(MouseEvent event) {
	     	  HomeController.chrono=false;
             chronogrameField.getChildren().removeAll(frontList);
             for(int i=0 ; i<composantDechrono.size();i++)
             {
            	composantDechrono.get(i).setStartChronoX(1);
               	composantDechrono.get(i).setStartChronoY(pointsDeDepart.get(i).getLayoutY());
             }
            	 Horloge.setStartX(1);
            	 Horloge.setStartY(76);
            	 first=true;
            	 
             
	    }
	    
	    @FXML
	    void stoper(MouseEvent event) {
	    	
	    	  HomeController.chrono=false;
	    }

	    @FXML
	    void play(MouseEvent event) {
	
	 
           HomeController.chrono=true;
           
	    }
	
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
	         pointsDeDepart.addAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9));
			extTableView=tableView;
		    field= chronogrameField;
		    
        	elementsColumn.setCellValueFactory(dat-> new SimpleStringProperty(dat.getValue().getNom()));
    		valeurQ.setCellValueFactory(dat-> new SimpleStringProperty(String.valueOf(dat.getValue().getSortieAafficher().getNum())));
 		   valeurQbar.setCellValueFactory(dat-> new SimpleStringProperty(String.valueOf(dat.getValue().getSortieBar().getNum())));
 		  tableView.getItems().add(horlogeDecHRONO);
 	      for(int i=0;i<composantDechrono.size();i++)
 				 {
 					tableView.getItems().add(composantDechrono.get(i));
 					composantDechrono.get(i).setStartChronoX(1);
 				   composantDechrono.get(i).setStartChronoY(pointsDeDepart.get(i).getLayoutY());
 					
 				 }
 		  
 				   
		}

	  public static void refrecher() {
		  extTableView.refresh();


	  }
	  public static void tracerFront(EtatLogique etatDeHorloge) {
		  Polyline horHPolyline=new Polyline();
		  Polyline verHPolyline=new Polyline();
		  horHPolyline.setStrokeWidth(3);
		  verHPolyline.setStrokeWidth(3);
	if(etatDeHorloge.getNum() == 0)
	{
		
		  if(first) Horloge.setStartY(Horloge.getStartY()-22);
			 verHPolyline.getPoints().addAll(new Double[] {
					 Horloge.getStartX(),Horloge.getStartY(),
					Horloge.getStartX(),Horloge.getStartY()+22
			 });
			 Horloge.setStartY(Horloge.getStartY()+22);
			 horHPolyline.getPoints().addAll(new Double[] {
					 Horloge.getStartX(),Horloge.getStartY(),
					 Horloge.getStartX()+25,Horloge.getStartY(),
			 });
			 Horloge.setStartX(Horloge.getStartX()+25);
	}
	else
	{
		 verHPolyline.getPoints().addAll(new Double[] {
				 Horloge.getStartX(),Horloge.getStartY(),
					Horloge.getStartX(),Horloge.getStartY()-22
		 });
		 Horloge.setStartY(Horloge.getStartY()-22);
		 horHPolyline.getPoints().addAll(new Double[] {
				 Horloge.getStartX(),Horloge.getStartY(),
				 Horloge.getStartX()+25,Horloge.getStartY(),
		 });
		 Horloge.setStartX(Horloge.getStartX()+25);
	}
	
		 for(int i=0;i<composantDechrono.size();i++)
		 {
	     

			 EtatLogique etat= composantDechrono.get(i).getSortieAafficher();
			  Polyline horPolyline=new Polyline();
			  Polyline verPolyline=new Polyline();
			  horPolyline.setStroke(Color.GREEN);
			  verPolyline.setStroke(Color.GREEN);
			  horPolyline.setStrokeWidth(3);
			  verPolyline.setStrokeWidth(3);
			  if(composantDechrono.get(i).getFront().getNum()==etatDeHorloge.getNum())
			  {
		
			 if(composantDechrono.get(i).getEtatAvant().getNum() != etat.getNum())
			 {
				 if(etat.getNum()==1)
				 {
					 verPolyline.getPoints().addAll(new Double[] {
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY()-22
					 });
					 composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
					 horPolyline.getPoints().addAll(new Double[] {
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
							 composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
					 });
					 composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);
				 }
				 else {
					  if(first) composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
					 verPolyline.getPoints().addAll(new Double[] {
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY()+22
					 });
					 composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()+22);
					 horPolyline.getPoints().addAll(new Double[] {
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
							 composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
					 });
					 composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);
					 
				 }
				 
				 field.getChildren().add(verPolyline);
				 frontList.add(verPolyline);
			 }
			 else {
				 if(etat.getNum()==1 && first) composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
				  horPolyline.getPoints().addAll(new Double[] {
						 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
						 composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
				 });
				 composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);
				 
			 }
		 }
			  else {
				 if(etat.getNum()==1 && first) composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
				  horPolyline.getPoints().addAll(new Double[] {
							 composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
							 composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
					 });
					 composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);
				  
			  }
		     
		 field.getChildren().add(horPolyline);
		 frontList.add(horPolyline);
			 
		 }	  
		     field.getChildren().add(horHPolyline);
		  	 field.getChildren().add(verHPolyline);
		  	 frontList.add(horHPolyline);
		  	frontList.add(verHPolyline);
		 first=false;
	  }

	public static Horloge getHorlogeDecHRONO() {
		return horlogeDecHRONO;
	}

	public static void setHorlogeDecHRONO(Horloge horlogeDecHRONO) {
		ChronogrammeController.horlogeDecHRONO = horlogeDecHRONO;
	}
	  
}

