package controllers;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import application.ClickDroit;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import noyau.*;



public class HomeController implements Initializable {
	
    Map<ImageView,Label> elemanrsMapFillMap;
    ImageView dragItem;
    private ClickDroit clickDroitFenetre;
    private Double x,y;
    private int switching = 0; 

    
    @FXML
    private Tab comonents;

    @FXML
    private VBox vBoxComp;

    @FXML
    private Label tHex;

    @FXML
    private ImageView hex;

    @FXML
    private Label tPin;

    @FXML
    private ImageView pin;

    @FXML
    private Label tH;

    @FXML
    private ImageView clock;

    @FXML
    private Label tVcc;

    @FXML
    private ImageView vcc;

    @FXML
    private Label tMass;

    @FXML
    private ImageView mass;

    @FXML
    private Label tAnd;

    @FXML
    private ImageView and;

    @FXML
    private Label tOr;

    @FXML
    private ImageView or;

    @FXML
    private Label tXor;

    @FXML
    private ImageView xor;

    @FXML
    private Label tNand;

    @FXML
    private ImageView nand;

    @FXML
    private Label tNor;

    @FXML
    private ImageView nor;

    @FXML
    private Label tNot;

    @FXML
    private ImageView not;

    @FXML
    private Label tJk;

    @FXML
    private ImageView jk;

    @FXML
    private Label tD;

    @FXML
    private ImageView d;

    @FXML
    private Label tRs;

    @FXML
    private ImageView rs;

    @FXML
    private Label tT;

    @FXML
    private ImageView t;

    @FXML
    private Label tCpt;

    @FXML
    private ImageView cpt;

    @FXML
    private Label tRd;

    @FXML
    private ImageView registreDecalge;

    @FXML
    private Label tMux;

    @FXML
    private ImageView mux;

    @FXML
    private Label tDmux;

    @FXML
    private ImageView dmux;

    @FXML
    private Label tDEC;

    @FXML
    private ImageView dec;

    @FXML
    private Label tAddc;

    @FXML
    private ImageView addcomplet;

    @FXML
    private Label tDadd;

    @FXML
    private ImageView demiAdd;

    @FXML
    private Label tEnc;

    @FXML
    private ImageView enco;

    @FXML
    private ImageView fichier;

    @FXML
    private ImageView edition;

    @FXML
    private ImageView simulation;

    @FXML
    private ImageView affichage;

    @FXML
    private ImageView aide;

    @FXML
    private ImageView darkMode;

    @FXML
    private AnchorPane workSpace;
    
    @FXML
    private ImageView logo;
    /////////////////////////////Les lignes de Guide
	private Line guideX = new Line();
	private Line guideXp = new Line();
	private	Line guideY = new Line();
	private	Line guideYp = new Line();
    ///////////////////////////////////////////////
    @FXML
    private Label afficheurX;

    @FXML
    private Label afficheurY;
    
	////////////////////Appliquer l'animation de rotation   
    @FXML
    void mouseEnterLogo(MouseEvent event) {
    	rotationDelogo(logo,1,500);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ajouterGestWorkSpace();/////Les gestes De drag and drop 
		tracerLesGuides();//Initialisation des files de guide
		//initialisation des coordones de X et Y a 0
		afficheurX.setText("X : 0"); 
		afficheurY.setText("Y : 0");
		//Creation d'une map pour gerer les titres des composants 
		elemanrsMapFillMap = new HashMap<ImageView, Label>(){{put(hex, tHex);
    		put(pin, tPin);
    		put(clock, tH);
    		put(vcc, tVcc);
    		put(mass, tMass);
    		put(and, tAnd);
    		put(or, tOr);
    		put(nand, tNand);
    		put(nor, tNor);
    		put(xor, tXor);
    		put(not, tNot);
    		put(jk, tJk);
    		put(d, tD);
    		put(t, tT);
    		put(rs, tRs);
    		put(cpt, tCpt);
    		put(registreDecalge, tRd);
    		put(mux, tMux);
    		put(dmux, tDmux);
    		put(dec, tDEC);
    		put(enco, tEnc);
    		put(addcomplet, tAddc);
    		put(demiAdd, tDadd);}};
    		
    
    		
    	////Ajouter pour chaque Composant les gestes de drag and drop
    		
    		ajouterLeGest(hex);
    	    ajouterLeGest(pin);
    	    ajouterLeGest(clock);
    	    ajouterLeGest(vcc);
    	    ajouterLeGest(mass);
    	    ajouterLeGest(and);
    	    ajouterLeGest(or);
    	    ajouterLeGest(xor);
    	    ajouterLeGest(nor);
    	    ajouterLeGest(nand);
    	    ajouterLeGest(not);
    	    ajouterLeGest(mux);
    	    ajouterLeGest(dmux);
    	    ajouterLeGest(dec);
    	    ajouterLeGest(addcomplet);
    	    ajouterLeGest(enco);
    	    ajouterLeGest(demiAdd);
    	    ajouterLeGest(d);
    	    ajouterLeGest(jk);
    	    ajouterLeGest(t);
    	    ajouterLeGest(rs);
    	    ajouterLeGest(cpt);
    	    ajouterLeGest(registreDecalge);
		  
    	    ////////////// tracer les regles 
  
    	    tracerLesregles(workSpace);
	}
	private void ajouterGestWorkSpace() {////Methodes pour Ajouter l'interaction avec le drag and drop et les guides
		   workSpace.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
	   	        public void handle(MouseDragEvent e) {
                       workSpace.getChildren().add(guideX);
                       workSpace.getChildren().add(guideXp);
                       workSpace.getChildren().add(guideY);
                       workSpace.getChildren().add(guideYp);
                       

	   	            e.consume();
	   	        }
	   	    });
		   
		  	  workSpace.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
			        public void handle(MouseDragEvent e) {
			        	e.consume();
			        }
			    });
		  	  
		  	  
		  	  workSpace.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
		  		  public void handle(MouseDragEvent e) {
		  			  //TODO: add new instance of dragItem to rightPane
		  			  workSpace.getChildren().remove(guideX);
		  			  workSpace.getChildren().remove(guideXp);
		  			  workSpace.getChildren().remove(guideY);
		  			  workSpace.getChildren().remove(guideYp);

		  			  e.consume();
		  		  }
		  	  });
			  
			  workSpace.setOnMousePressed(new EventHandler<MouseEvent>() {
				  @Override
				  public void handle(MouseEvent event) {
					  if (clickDroitFenetre != null) {
						  Double x = clickDroitFenetre.getX(), y = clickDroitFenetre.getY(); 
						  Double mouseX = event.getScreenX() , mouseY = event.getScreenY();

						  if( (mouseX < x)  ||  (mouseX > x+162) || (mouseY < y)  ||  (mouseY > y+164) )
							  clickDroitFenetre.close();
					  }
				  }
			  });		

	}
    
	private void ajouterLeGest( ImageView elementAdrager) {//Methode d'ajout de la fonctionallité de drag and drop avant que le composant 
		//est ajoute dans le workSpace
	
	
	    elementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	            elementAdrager.setCursor(Cursor.HAND);
	            elemanrsMapFillMap.get(elementAdrager).setStyle("-fx-background-color:#000000;-fx-background-radius:10;-fx-effect:dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0.5, 2.0, 2.0)");
	            transitionDesComposants(elementAdrager);
				}
        
	    });
	    elementAdrager.setOnMouseExited(new EventHandler<MouseEvent>() {
	    	public void handle(MouseEvent e) {
	    		  elemanrsMapFillMap.get(elementAdrager).setStyle("-fx-background-color:#303337;-fx-background-radius:10;-fx-effect:dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0.5, 2.0, 2.0)");
	    	}
		});
	    
	    elementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	        	ImageView dragImageView = new ImageView();
	            System.out.println(elementAdrager.getId());
	            dragImageView.setMouseTransparent(true);
	            elementAdrager.setMouseTransparent(true);
	            elementAdrager.setCursor(Cursor.CLOSED_HAND);
	            
	            elementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	            SnapshotParameters snapParams = new SnapshotParameters();
	    	            snapParams.setFill(Color.TRANSPARENT);
	    	            dragImageView.setImage(elementAdrager.snapshot(snapParams, null));
	    	            workSpace.getChildren().add(dragImageView);
	    	            dragImageView.startFullDrag();
	    	            e.consume();
	    	        }
	    	    });
	            
	            elementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	        	Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
	    	            dragImageView.relocate(
	    	                    (int)(localPoint.getX() - dragImageView.getBoundsInLocal().getWidth() / 2),
	    	                    (int)(localPoint.getY() - dragImageView.getBoundsInLocal().getHeight() / 2 )
	    	            );
	    	            
	    	            String xString=String.valueOf(dragImageView.getLayoutX());
    	                String yString=String.valueOf(dragImageView.getLayoutY());
	    	            if((dragImageView.getLayoutX()>0 && dragImageView.getLayoutX()<1066 )&&(dragImageView.getLayoutY()>17))
	    	            {
	    	                guideX.setLayoutX(dragImageView.getLayoutX());
	    	                guideY.setLayoutY(dragImageView.getLayoutY());
	    	                guideXp.setLayoutX(dragImageView.getLayoutX()+ elementAdrager.getBoundsInLocal().getWidth()+1);
	    	                guideYp.setLayoutY(dragImageView.getLayoutY()+ elementAdrager.getBoundsInLocal().getHeight()+1);
	    	                
	    	        
	    	                afficheurX.setText("X : "+xString);
	    	                afficheurY.setText("Y : "+yString);
	    	                
	    	            }
	    	      
	    	            else 
	    	            	{
	    	            	guideX.setLayoutX(0);
	    	            	guideY.setLayoutY(0);
	    	            	guideXp.setLayoutX(0);
	    	            	guideYp.setLayoutY(0);
	    	            	afficheurX.setText("X : 0");
		    	            afficheurY.setText("Y : 0");
	    	            	}
	    	     
	    	            
	    	            e.consume();
	    	        }
	    	    });
	            
	            elementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	        	
	    	            dragItem = null;
	    	            
	    	            dragImageView.setMouseTransparent(false);
	    	           
	    	            elementAdrager.setMouseTransparent(false);
	    	            elementAdrager.setCursor(Cursor.DEFAULT);
	    	            if(e.getSceneX() <210 || e.getSceneY()<25||e.getSceneX()>1300|| e.getSceneY()>670)
	    	            	workSpace.getChildren().remove(dragImageView);
	    	            else 
	    	            {
	    	            	dragImageView.setId(elementAdrager.getId());
	    	            	instanceComposant(dragImageView);
	    	            	dragImageView.setImage(new Image(Circuit.getCompFromImage(dragImageView).generatePath()));
	    	            	ajouterLeGestApresCollage(dragImageView);
	    	            }
	    	        }
	    	    });
	            
	        }
	    });
	

	}
	
	private void ajouterLeGestApresCollage( ImageView eleementAdrager) {//Methode d'ajout de la fonctionallité de drag and drop apres que le composant 
		//est ajoute dans le workSpace
		
		Polyline a = AjouterLignesInitiale(eleementAdrager);
		workSpace.getChildren().add(a);
		
	    eleementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	            eleementAdrager.setCursor(Cursor.HAND);
	       
	       
	        }
	    });
	    
	    eleementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	        
	        	if(e.getButton() != MouseButton.SECONDARY)
	        	{
	            dragItem = eleementAdrager;
	       
	            eleementAdrager.setMouseTransparent(true);
	            eleementAdrager.setMouseTransparent(true);
	            eleementAdrager.setCursor(Cursor.CLOSED_HAND);
	            
	            
	            eleementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	       
	    	            SnapshotParameters snapParams = new SnapshotParameters();
	    	            snapParams.setFill(Color.TRANSPARENT);
	    	            eleementAdrager.setImage(eleementAdrager.snapshot(snapParams, null));
	    	            eleementAdrager.startFullDrag();
	    	            e.consume();
	    	            
	    	        }
	    	    });
	            
	        	}else
	        	{
	        		Double clicDroitX,clicDroitY;
	        		clicDroitX = e.getScreenX();
	        		clicDroitY = e.getScreenY();
	        		clickDroitFenetre = new ClickDroit(Circuit.getCompFromImage(eleementAdrager),clicDroitX,clicDroitY);
	        	}
	            eleementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	            Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
	    	            eleementAdrager.relocate(
	    	                    (int)(localPoint.getX() - eleementAdrager.getBoundsInLocal().getWidth() /2),
	    	                    (int)(localPoint.getY() - eleementAdrager.getBoundsInLocal().getHeight()/2 )
	    	            );
	    	            double x=eleementAdrager.getLayoutX()+eleementAdrager.getBoundsInLocal().getWidth() - 2;
	    	        	double y=eleementAdrager.getLayoutY()+eleementAdrager.getBoundsInLocal().getHeight()/2 - 1;
	    	        	a.relocate(x, y);
	    	            String xString=String.valueOf(eleementAdrager.getLayoutX());
    	                String yString=String.valueOf(eleementAdrager.getLayoutY());
    	                if((eleementAdrager.getLayoutX()>0 && eleementAdrager.getLayoutX()<1066 )&&(eleementAdrager.getLayoutY()>17))
	    	            {
	    	                guideX.setLayoutX(eleementAdrager.getLayoutX());
	    	                guideY.setLayoutY(eleementAdrager.getLayoutY());
	    	                guideXp.setLayoutX(eleementAdrager.getLayoutX()+ eleementAdrager.getBoundsInLocal().getWidth()+1);
	    	                guideYp.setLayoutY(eleementAdrager.getLayoutY()+ eleementAdrager.getBoundsInLocal().getHeight()+1);

	    	                afficheurX.setText("X : "+xString);
	    	                afficheurY.setText("Y : "+yString);
	    	            }
	    	            
	    	       
	    	            else 
	    	            	{
	    	            	guideX.setLayoutX(0);
	    	               	guideY.setLayoutY(0);
	    	            	guideXp.setLayoutX(0);
	    	            	guideYp.setLayoutY(0);
	    	              	afficheurX.setText("X : 0");
		    	            afficheurY.setText("Y : 0");
	    	            	}	    	        
	    	            e.consume();
	    	        }
	    	    });
	            
	            eleementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	            dragItem = null;  	 
	    	            eleementAdrager.setMouseTransparent(false);
	    	            eleementAdrager.setMouseTransparent(false);
	    	            eleementAdrager.setCursor(Cursor.DEFAULT);
	    	            if(e.getSceneX() <210 || e.getSceneY()<25||e.getSceneX()>1300|| e.getSceneY()>670)
	    	           workSpace.getChildren().remove(eleementAdrager);
	    	        }
	    	    });
	            
	        }
	    });
	

	}
	public void transitionDesComposants(Node composants) {// Methode d'animation de 'Shake'
	    int duration = 100;
	    int count = 3;

	    TranslateTransition transition1 = new TranslateTransition(Duration.millis(duration), composants);
	    transition1.setFromX(0);
	    transition1.setToX(-5);
	    transition1.setInterpolator(Interpolator.LINEAR);

	    TranslateTransition transition2 = new TranslateTransition(Duration.millis(duration), composants);
	    transition2.setFromX(-5);
	    transition2.setToX(5);
	    transition2.setDelay(Duration.millis(duration));
	    transition2.setInterpolator(Interpolator.LINEAR);
	    transition2.setCycleCount(count);

	    TranslateTransition transition3 = new TranslateTransition(Duration.millis(duration), composants);
	    transition3.setToX(0);
	    transition3.setDelay(Duration.millis((count + 1) * duration));
	    transition3.setInterpolator(Interpolator.LINEAR);

	    transition1.play();
	    transition2.play();
	    transition3.play();
	}
	 void tracerLesregles(AnchorPane w) {// Methode de tracage des Regles

	        boolean v = true;
	        for (int i = 0; i <= workSpace.getPrefWidth(); i += 25) {

	            Line p = new Line();
	         
	            p.toFront();
	            if (v) {
	                v = false;
	                p.setStyle("-fx-stroke-width: 1.5px;");
	                p.setStroke(Color.web("ffffff"));
	                p.setOpacity(1);
	                p.setLayoutX(i);
	                p.setLayoutY(0);
	                p.setStartX(0);
	                p.setStartY(0);
	                p.setEndX(0);
	                p.setEndY(15);
	                for(int j=i+4;j<i+24;j+=4)
	                {
	                	   Line ppLine=new Line();
	                	 ppLine.setStyle("-fx-stroke-width: 1.5px;");
	 	                ppLine.setStroke(Color.web("ffffff"));
	 	                ppLine.setOpacity(1);
	 	                ppLine.setLayoutX(j);
	 	                ppLine.setLayoutY(0);
	 	                ppLine.setStartX(0);
	 	                ppLine.setStartY(0);
	 	                ppLine.setEndX(0);
	 	                ppLine.setEndY(4);
	 	                w.getChildren().add(ppLine);
	                	
	                }
	            } else {
	                v = true;
	                p.setStyle("-fx-stroke-width: 1.5px;");
	                p.setStroke(Color.web("ffffff"));
	                p.setOpacity(1);
	                p.setLayoutX(i);
	                p.setLayoutY(0);
	                p.setStartX(0);
	                p.setStartY(0);
	                p.setEndX(0);
	                p.setEndY(6.5);
	                if (i < workSpace.getPrefWidth() - 6)
	                	for(int j=i+4;j<i+24;j+=4)
	                	{
	                		Line ppLine=new Line();
	                		ppLine.setStyle("-fx-stroke-width: 1.5px;");
	                		ppLine.setStroke(Color.web("ffffff"));
	                		ppLine.setOpacity(1);
	                		ppLine.setLayoutX(j);
	                		ppLine.setLayoutY(0);
	                		ppLine.setStartX(0);
	                		ppLine.setStartY(0);
	                		ppLine.setEndX(0);
	                		ppLine.setEndY(4);
	                		w.getChildren().add(ppLine);

	                	}
	                
	            }

	            p.toFront();
	            w.getChildren().add(p);
	        }

	        v = true;
	        for (int i = 0; i <= workSpace.getPrefHeight(); i += 25) {

	            Line p = new Line();
	            p.toFront();
	            if (v) {
	                v = false;
	                p.setStyle("-fx-stroke-width: 1px;");
	                p.setStroke(Color.web("ffffff")); 
	                p.setOpacity(1);
	                p.setLayoutX(0);
	                p.setLayoutY(i);
	                p.setStartX(0);
	                p.setStartY(0);
	                p.setEndX(15);
	                p.setEndY(0);
	                for(int j=i+4;j<i+24;j+=4)
	                {
	                	   Line ppLine=new Line();
	                	 ppLine.setStyle("-fx-stroke-width: 1.5px;");
	 	                ppLine.setStroke(Color.web("ffffff"));
	 	                ppLine.setOpacity(1);
	 	                ppLine.setLayoutX(0);
	 	                ppLine.setLayoutY(j);
	 	                ppLine.setStartX(0);
	 	                ppLine.setStartY(0);
	 	                ppLine.setEndX(4);
	 	                ppLine.setEndY(0);
	 	                w.getChildren().add(ppLine);
	                	
	                }
	            } else {
	                v = true;
	                p.setStyle("-fx-stroke-width: 1px;");
	                p.setStroke(Color.web("ffffff"));
	                p.setOpacity(1);
	                p.setLayoutX(0);
	                p.setLayoutY(i);
	                p.setStartX(0);
	                p.setStartY(0);
	                p.setEndX(6.5);
	                p.setEndY(0);
	                for(int j=i+4;j<i+24;j+=4)
	                {
	                	   Line ppLine=new Line();
	                	 ppLine.setStyle("-fx-stroke-width: 1.5px;");
	 	                ppLine.setStroke(Color.web("ffffff"));
	 	                ppLine.setOpacity(1);
	 	                ppLine.setLayoutX(0);
	 	                ppLine.setLayoutY(j);
	 	                ppLine.setStartX(0);
	 	                ppLine.setStartY(0);
	 	                ppLine.setEndX(4);
	 	                ppLine.setEndY(0);
	 	                w.getChildren().add(ppLine);
	                	
	                }
	            }

	            p.toFront();
	            w.getChildren().add(p);
	        }
	    }
	 
	 
	
	     
	private void rotationDelogo(ImageView image,int nombreDeboucle,int vitesse) {// Methode de rotation de logo

	     RotateTransition rotate = new RotateTransition();           	     
	        rotate.setAxis(Rotate.Z_AXIS);  	          	       
	        rotate.setByAngle(360);  	          	     
	        rotate.setCycleCount(nombreDeboucle);  
	        rotate.setDuration(Duration.millis(vitesse));   
	        rotate.setAutoReverse(false);  	              
	        rotate.setNode(image);    
	        rotate.play();
	    
	        rotate.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {			         
				        logo.setRotate(0);
				}
			});
	     
	}
 
	private void tracerLesGuides() {//Methode d'initialitaton des guides
        guideX.setStyle("-fx-stroke-width: 0.3px;");
        guideX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideX.setStroke(Color.web("ffffff")); 
        guideX.setOpacity(1);
        guideX.setLayoutY(0);
        guideX.setStartX(0);
        guideX.setStartY(0);
        guideX.setEndX(0);
        guideX.setEndY(700);	
        /////////////////////////////////////////////////////
        guideXp.setStyle("-fx-stroke-width: 0.3px;");
        guideXp.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideXp.setStroke(Color.web("ffffff")); 
        guideXp.setOpacity(1);
        guideXp.setLayoutY(0);
        guideXp.setStartX(0);
        guideXp.setStartY(0);
        guideXp.setEndX(0);
        guideXp.setEndY(700);
        /////////////////////////////////////////////////////
        guideY.setStyle("-fx-stroke-width: 0.3px;");
        guideY.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideY.setStroke(Color.web("ffffff")); 
        guideY.setOpacity(1);
    	guideY.setLayoutX(0);
        guideY.setStartX(0);
        guideY.setStartY(0);
        guideY.setEndX(1130);
        guideY.setEndY(0);
        /////////////////////////////////////////////////////
        guideYp.setStyle("-fx-stroke-width: 0.3px;");
        guideYp.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideYp.setStroke(Color.web("ffffff")); 
        guideYp.setOpacity(1);
    	guideYp.setLayoutX(0);
        guideYp.setStartX(0);
        guideYp.setStartY(0);
        guideYp.setEndX(1130);
        guideYp.setEndY(0);
        
        
	}
	private void instanceComposant(ImageView img) {
		Composant comp;
		switch (img.getId()) {
		case "hex" :{
			comp = new AfficheurSegment("");
		}break;
		case "pin" :{
			comp = new Pin(true, "");
		}break;
		case "clock" :{
			comp = new Horloge("", 1000);
		}break;
		case "vcc" :{
			comp = new SourceConstante(EtatLogique.ONE, "");
		}break;
		case "mass" :{
			comp = new SourceConstante(EtatLogique.ZERO, "");
		}break;
		case "and" :{
			comp = new And(2, "");
		}break;
		case "or" :{
			comp = new Or(2, "");
		}break;
		case "xor" :{
			comp = new Xor(2, "");
		}break;
		case "nand" :{
			comp = new Nand(2, "");
		}break;
		case "nor" :{
			comp = new Nor(2, "");
		}break;
		case "not" :{
			comp = new Not("");
		}break;
		case "jk" :{
			comp = new JK("", Front.Front_Montant);
		}break;
		case "d" :{
			comp = new D("", Front.Front_Montant);
		}break;
		case "rs" :{
			comp = new RST("", Front.Front_Montant);
		}break;
		case "t" :{
			comp = new T("", Front.Front_Montant);
		}break;
		case "cpt" :{
			comp = new Compteur(2, "", Front.Front_Montant);
		}break;
		case "registreDecalge" :{
			comp = new RegistreDecalage(2, "", true, Front.Front_Montant);
		}break;
		case "mux" :{
			comp = new Multiplexeur(2, "");
		}break;
		case "dmux" :{
			comp = new Demultiplexeur(1, "");
		}break;
		case "dec" :{
			comp = new Decodeur(1, "");
		}break;
		case "addcomplet" :{
			comp = new AdditionneurN_Bites(1, "");
		}break;
		case "demiAdd" :{
			comp = new DemiAdditionneur(1, "");
		}break;
		default : {
			comp = new Encodeur(2, "");
		}
		}
		Circuit.ajouterComposant(comp, img);
	}
	private Polyline AjouterLignesInitiale(ImageView composant) {
    	double x=composant.getLayoutX()+composant.getBoundsInLocal().getWidth();
    	double y=composant.getLayoutY()+composant.getBoundsInLocal().getHeight()/2;
    	Polyline a = new Polyline(x,y,x+12,y); //(x,y,x+12,y) avec x,y coordonnees de la sortie
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
		ajouterGeste(a);
		return a;
    }
	 public void ajouterGeste(Polyline line)
		{
			EventHandler<MouseEvent> event1 = new javafx.event.EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					
					Double x2 = event.getX();
					Double y2 = event.getY();
					for (int i = 0; i < 4; i++) {
						line.getPoints().remove((line.getPoints().size()-1));
					}
					if(Math.abs(x2-x)<10) { 
						if(Math.abs(y2-y)<10) switching = 0; 
						else switching = 1;
					}else {
						if(Math.abs(y2-y)<10) switching = 0;
					} 		
					if(switching == 0) line.getPoints().addAll(x2,y,x2,y2);
					else line.getPoints().addAll(x,y2,x2,y2);
				}
			};
			
			EventHandler<MouseEvent> event = new javafx.event.EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					int i = line.getPoints().size()-4;
					x = event.getX();
					y = event.getY();
					System.out.println(x);
					System.out.println(line.getPoints().get(i));
					System.out.println(y);
					System.out.println(line.getPoints().get(i+1)+"\nmmmmm");
					while((i!=0) && ((Math.abs(x-line.getPoints().get(i))>5) && ((Math.abs(y - line.getPoints().get(i+1)) >=5 ))))
					{
						line.getPoints().addAll(line.getPoints().get(i),line.getPoints().get(i+1));
						i=i-2;
						System.out.println(i+"Dkhal");
						System.out.println(x);
						System.out.println(line.getPoints().get(i));
						System.out.println(y);
						System.out.println(line.getPoints().get(i+1)+"\n******************");
					}

					Double x1 = line.getPoints().get(i),y1 = line.getPoints().get(i+1);
					if(Math.abs(x-line.getPoints().get(i)) < 5) {
						if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
							line.getPoints().addAll(x,y,x,y);
						}
						else {
							line.getPoints().addAll(x1,y1,x1,y,x1,y,x1,y);
						}	
					}else{
						if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
							line.getPoints().addAll(x1,y1,x,y1,x,y1,x,y1);
						}
					}
				}
			};
			line.setOnMousePressed(event);
			line.setOnMouseDragged(event1);
		}
	
}

