package controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import noyau.Composant;


public class HomeController implements Initializable {
	
  
	
	
    Map<ImageView,Label> elemanrsMapFillMap;
    Node dragItem;
    
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
    void mouseEntered(MouseDragEvent event) {
    	   workSpace.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
   	        public void handle(MouseDragEvent e) {
   	            workSpace.setStyle("-fx-border-color:red;-fx-border-width:2;-fx-border-style:solid;");
   	            e.consume();
   	        }
   	    });

    }

    @FXML
    void mouseExited(MouseDragEvent event) {
        workSpace.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
	        public void handle(MouseDragEvent e) {
	            workSpace.setStyle("-fx-border-style:none;");
	            e.consume();
	        }
	    });

    }

    @FXML
    void mouseReleased(MouseDragEvent event) {
    	  workSpace.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
  	        public void handle(MouseDragEvent e) {
  	            //TODO: add new instance of dragItem to rightPane
  	        	System.out.println("m done");
  	            e.consume();
  	        }
  	    });

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
	}
    
	private void ajouterLeGest( ImageView elementAdrager) {
	
	
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
	        	System.out.println("mee p");
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
	    	                    (int)(localPoint.getX() - dragImageView.getBoundsInLocal().getWidth() / 2 ),
	    	                    (int)(localPoint.getY() - dragImageView.getBoundsInLocal().getHeight() / 2)
	    	            );
	    	            e.consume();
	    	        }
	    	    });
	            
	            elementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	        	
	    	            dragItem = null;
	    	            
	    	            dragImageView.setMouseTransparent(false);
	    	          
	    	            elementAdrager.setMouseTransparent(false);
	    	            elementAdrager.setCursor(Cursor.DEFAULT);
	    	            if(e.getSceneX() <240)
	    	    	          workSpace.getChildren().remove(dragImageView);
	    	            else ajouterLeGestApresCollage(dragImageView);
	    	        }
	    	    });
	            
	        }
	    });
	

	}
	
	private void ajouterLeGestApresCollage( ImageView eleementAdrager) {
		
		
	    eleementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	            eleementAdrager.setCursor(Cursor.HAND);
	       
	       
	        }
	    });
	    
	    eleementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	        
	   
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
	            
	            eleementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	            Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
	    	            eleementAdrager.relocate(
	    	                    (int)(localPoint.getX() - eleementAdrager.getBoundsInLocal().getWidth() /2),
	    	                    (int)(localPoint.getY() - eleementAdrager.getBoundsInLocal().getHeight()/2 )
	    	            );
	    	            e.consume();
	    	        }
	    	    });
	            
	            eleementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	            dragItem = null;  	            
	    	            eleementAdrager.setMouseTransparent(false);
	    	            eleementAdrager.setMouseTransparent(false);
	    	            eleementAdrager.setCursor(Cursor.DEFAULT);
	    	            if(e.getSceneX() <240)
	    	           workSpace.getChildren().remove(eleementAdrager);
	    	        }
	    	    });
	            
	        }
	    });
	

	}
	public void transitionDesComposants(Node composants) {
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
	

	
}

