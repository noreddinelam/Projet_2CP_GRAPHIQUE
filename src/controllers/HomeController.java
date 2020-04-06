package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HomeController implements Initializable {



    
    Node dragItem;
    
    @FXML
    private ImageView hex;

    @FXML
    private ImageView pin;

    @FXML
    private ImageView clock;

    @FXML
    private ImageView vcc;

    @FXML
    private ImageView mass;

    @FXML
    private ImageView and;

    @FXML
    private ImageView or;

    @FXML
    private ImageView xor;

    @FXML
    private ImageView nand;

    @FXML
    private ImageView nor;

    @FXML
    private ImageView not;

    @FXML
    private ImageView jk;

    @FXML
    private ImageView d;

    @FXML
    private ImageView rs;

    @FXML
    private ImageView t;

    @FXML
    private ImageView cpt;

    @FXML
    private ImageView registreDecalge;

    @FXML
    private ImageView mux;

    @FXML
    private ImageView dmux;

    @FXML
    private ImageView dec;

    @FXML
    private ImageView addcomplet;

    @FXML
    private ImageView demiAdd;

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
    private Tab comonents;
    
    @FXML
    private VBox vBoxComp;
    
    @FXML
    private JFXDrawer fichierDrawer;
    
    @FXML
    private JFXDrawer editionDrawer;
    
    @FXML
    private VBox vbar;
    
    @FXML
    private JFXDrawer affichageDrawer;
    
    @FXML
    private JFXDrawer helpDrawer;
    

   
    
    
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
    
    Tooltip.install(fichier, new Tooltip("fichier"));
	Tooltip.install(edition, new Tooltip("edition"));
	Tooltip.install(simulation, new Tooltip("simulation"));
	Tooltip.install(affichage, new Tooltip("affichage"));
	Tooltip.install(aide, new Tooltip("aide"));
	
	
	
	
	rightbar(fichier, fichierDrawer,editionDrawer,affichageDrawer,helpDrawer,"/application/Fichier.fxml" );
	rightbar(edition, editionDrawer,affichageDrawer,fichierDrawer,helpDrawer,"/application/Edition.fxml" );
	rightbar(affichage, affichageDrawer,editionDrawer,fichierDrawer,helpDrawer,"/application/Affichage.fxml" );
	rightbar(aide, helpDrawer,affichageDrawer,fichierDrawer,editionDrawer,"/application/Aide.fxml" );

		workSpace.addEventHandler(MouseEvent.MOUSE_CLICKED, (ee)->{
			if(fichierDrawer.isOpened() ||editionDrawer.isOpened() ||affichageDrawer.isOpened() || helpDrawer.isOpened()) {
				fichierDrawer.close();
				fichierDrawer.setOpacity(0);
				
				editionDrawer.close();
				editionDrawer.setOpacity(0);
				
				affichageDrawer.close();
				affichageDrawer.setOpacity(0);
				
				helpDrawer.close();
				helpDrawer.setOpacity(0);
			}
			
		
		});

	    
	}
	
	
	
	public void rightbar(ImageView icon,JFXDrawer elementName, JFXDrawer element1Hide, JFXDrawer element2Hide, JFXDrawer element3Hide, String s) {
		Pane lay = null;
		try {
			lay = FXMLLoader.load(getClass().getResource(s));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		elementName.setSidePane(lay);
		elementName.setOpacity(0);

			icon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
				if(elementName.isOpened()) {
					FadeTransition fade = new FadeTransition();  
		         	fade.setDuration(Duration.millis(200)); 
		         	fade.setDelay(Duration.millis(200));
			        fade.setFromValue(10);  
			        fade.setToValue(0);    
			        fade.setCycleCount(0);  
			        fade.setAutoReverse(true);     
			        fade.setNode(elementName);    
			        fade.play();
			        elementName.close();
				}
				else {
					FadeTransition fade = new FadeTransition();  
		         	fade.setDuration(Duration.millis(200)); 
		         	fade.setDelay(Duration.millis(200));
			        fade.setFromValue(0);  
			        fade.setToValue(10);    
			        fade.setCycleCount(0);  
			        fade.setAutoReverse(true);     
			        fade.setNode(elementName);      
			        fade.play();	
			        elementName.open();
			        //elementName.setViewOrder(1);
			        element1Hide.close();
			        element1Hide.setOpacity(0);
			        //element1Hide.setViewOrder(4);
					
			        element2Hide.close();
			        element2Hide.setOpacity(0);
			       // element2Hide.setViewOrder(4);

			        
			        element3Hide.close();
			        element3Hide.setOpacity(0);
			       // element3Hide.setViewOrder(4);


				}
			});
			
		
	}
	
	
	
    
	private void ajouterLeGest( Node elementAdrager) {
	
	
	    elementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	            elementAdrager.setCursor(Cursor.HAND);
	        }
	    });
	    
	    elementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	        	ImageView dragImageView = new ImageView();
	        	System.out.println("mee p");
	            dragItem = elementAdrager;
	            dragImageView.setMouseTransparent(true);
	            elementAdrager.setMouseTransparent(true);
	            elementAdrager.setCursor(Cursor.CLOSED_HAND);
	            
	            
	            elementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	        	System.out.println("meee D");
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
	    	                    (int)(localPoint.getX() - dragImageView.getBoundsInLocal().getWidth() ),
	    	                    (int)(localPoint.getY() - dragImageView.getBoundsInLocal().getHeight() )
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
	        
	        	System.out.println("mee p");
	            dragItem = eleementAdrager;
	            eleementAdrager.setMouseTransparent(true);
	            eleementAdrager.setMouseTransparent(true);
	            eleementAdrager.setCursor(Cursor.CLOSED_HAND);
	            
	            
	            eleementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	        	System.out.println("meee D");
	    	            SnapshotParameters snapParams = new SnapshotParameters();
	    	            snapParams.setFill(Color.TRANSPARENT);
	    	            eleementAdrager.setImage(eleementAdrager.snapshot(snapParams, null));
	    	            workSpace.getChildren().add(eleementAdrager);

	    	            eleementAdrager.startFullDrag();
	    	            e.consume();
	    	        }
	    	    });
	            
	            eleementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent e) {
	    	            Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
	    	            eleementAdrager.relocate(
	    	                    (int)(localPoint.getX() - eleementAdrager.getBoundsInLocal().getWidth() ),
	    	                    (int)(localPoint.getY() - eleementAdrager.getBoundsInLocal().getHeight() )
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
	
	
	
	
	
	

	
}

