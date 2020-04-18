package controllers;


import java.io.IOException;
import java.net.URL;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.ClickDroit;
import application.ClickDroitFil;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.sun.tools.javac.resources.compiler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import noyau.*;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;



public class HomeController extends Controller implements Initializable {
	
    Map<ImageView,Label> elemanrsMapFillMap;
    ImageView dragItem;
    private ClickDroit clickDroitFenetre;

    private double difX = 0;
    
    // utilisé dans la sauvegarde des coordonnées 
    
    double posX ;
	double posY ;
	
	
	public static Deque<Donnes> undoDeque=new LinkedList<Donnes>() ;
	
	final KeyCombination touchesDundo = new KeyCodeCombination(KeyCode.Z,
            KeyCombination.CONTROL_DOWN);
	
	private Polyline testPoly;
    
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
    private AnchorPane work;
    
    @FXML
    private ImageView camera;
    
    @FXML
    private ImageView logo;
    
    public static ImageView elementAsuprimer=null;
     
    private static ImageView elementAmodifier=null;
    /////////////////////////////Les lignes de Guide
	private Line guideX = new Line();
	private Line guideXp = new Line();
	private	Line guideY = new Line();
	private	Line guideYp = new Line();
	
	
    ///////////////////////////////////////////////
	//relier
	
	 
	 //////////////////
	 Stage stage;
    
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
    private Tab outils;
    
    @FXML
    private Tab portes;
    
    @FXML
    private Tab seq;
    
    @FXML
    private Tab comb;
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private TabPane tabPane;

    @FXML
    private Label afficheurX;

    @FXML
    private Label afficheurY;
    
    void ajouterAnimationBarDroite(ImageView imageView) {
    	imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (! imageView.getId().equals("simulation") || imageView.getId().equals("simulation") && ! simul) {
					imageView.setImage(new Image("homePage_icones/"+imageView.getId()+"Hover.png"));
				}				
			}
		});
    	imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (! imageView.getId().equals("simulation") || imageView.getId().equals("simulation") && ! simul)
					imageView.setImage(new Image("homePage_icones/"+imageView.getId() + ".png"));
			}
		});
    }
    
    void initialiseAnimationOfBarDroite() {
    	ajouterAnimationBarDroite(fichier);
    	ajouterAnimationBarDroite(edition);
    	ajouterAnimationBarDroite(simulation);
    	ajouterAnimationBarDroite(affichage);
    	ajouterAnimationBarDroite(aide);
    	ajouterAnimationBarDroite(camera);
    	camera.setCursor(Cursor.HAND);
    }
    
	////////////////////Appliquer l'animation de rotation   
    @FXML
    void mouseEnterLogo(MouseEvent event) {
    	rotationDelogo(logo,1,500);
    }
    
    @FXML
    void screenShot(MouseEvent event) {
    	captureEcran();
    }
    
    @FXML
    void onSimuler(MouseEvent event) {
    	simul = (!simul);
    	if (simul) {
    		simulation.setImage(new Image("homePage_icones/SIMULATION_OFF.png"));
			Circuit.initialiser();
		}
    	else {
    		simulation.setImage(new Image("homePage_icones/SIMULATION.png"));
			Circuit.defaultCompValue();
		}
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
    	    tracerLagrill();
		  
    	    
    	    ////////////// tracer les regles 
  
    	    tracerLesregles(workSpace);
    	    scrollPane.setHmax(1);
    	    scrollPane.setVmax(1);
    	    
    	    fichierDrawer.setDisable(true);//mettre tout les drawers en mode disable
    	    editionDrawer.setDisable(true);
    	    affichageDrawer.setDisable(true);
    	    helpDrawer.setDisable(true);
    	    
    	    

    	    tooltipInitialize();
    		rightbar(fichier, fichierDrawer,editionDrawer,affichageDrawer,helpDrawer,"/application/Fichier.fxml" );
    		rightbar(edition, editionDrawer,affichageDrawer,fichierDrawer,helpDrawer,"/application/Edition.fxml" );
    		rightbar(affichage, affichageDrawer,editionDrawer,fichierDrawer,helpDrawer,"/application/Affichage.fxml" );
    		rightbar(aide, helpDrawer,affichageDrawer,fichierDrawer,editionDrawer,"/application/Aide.fxml" );

    			workSpace.addEventHandler(MouseEvent.MOUSE_CLICKED, (ee)->{//pour fermer les drawer en cliquant sur la souris
    				if(fichierDrawer.isOpened() ||editionDrawer.isOpened() ||affichageDrawer.isOpened() || helpDrawer.isOpened()) {
    						fichierDrawer.close();
    						fichierDrawer.setOpacity(0);
    						editionDrawer.close();
    						editionDrawer.setOpacity(0);
    						affichageDrawer.close();
    						affichageDrawer.setOpacity(0);
    						helpDrawer.close();
    						helpDrawer.setOpacity(0);
    					 	fichierDrawer.setDisable(true);
    					    editionDrawer.setDisable(true);
    					    affichageDrawer.setDisable(true);
    					    helpDrawer.setDisable(true);
    				}
    				
    			
    			});
    		initialiseAnimationOfBarDroite();
	}
	@SuppressWarnings("unchecked")
	private void ajouterGestWorkSpace() {//Methodes pour Ajouter l'interaction avec le drag and drop et les guides
		   workSpace.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
	   	        public void handle(MouseDragEvent e) {
                       if (! workSpace.getChildren().contains(guideX)) workSpace.getChildren().add(guideX);
                       if (! workSpace.getChildren().contains(guideXp)) workSpace.getChildren().add(guideXp);
                       if (! workSpace.getChildren().contains(guideY)) workSpace.getChildren().add(guideY);
                       if (! workSpace.getChildren().contains(guideYp)) workSpace.getChildren().add(guideYp);
                       
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
		  			  //sari
		  		//	  workSpace.getChildren().remove(guideFilX);
		  		//	  workSpace.getChildren().remove(guideFilY);

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
					  if (clickDroitFilFenetre != null) {
						  Double x = clickDroitFilFenetre.getX(), y = clickDroitFilFenetre.getY(); 
						  Double mouseX = event.getScreenX() , mouseY = event.getScreenY();

						  if( (mouseX < x)  ||  (mouseX > x+164) || (mouseY < y)  ||  (mouseY > y+55) ) {
						  	  lineDroit.setStroke(Color.BLACK);
							  clickDroitFilFenetre.close();
						  }
					  }
				  }
			  });
			  
			  scrollPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
				    @Override
				    public void handle(KeyEvent event) {
				        if (event.isControlDown() && (event.getCode() == KeyCode.Z)) {
				            undoChanges();
				        } 
				    };
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
					elementName.setOpacity(0);
			        elementName.close();
			        elementName.setViewOrder(10);
			        elementName.setDisable(true);
				}
				
				else {
					elementName.setOpacity(1);
			        elementName.open();
			        elementName.setViewOrder(1);
			        elementName.setDisable(false);
			        element1Hide.close();
			        element1Hide.setOpacity(0);
			        element1Hide.setViewOrder(4);
			        element1Hide.setDisable(true);	
			        element2Hide.close();
			        element2Hide.setOpacity(0);
			        element2Hide.setViewOrder(4);
			        element2Hide.setDisable(true);
			        element3Hide.close();
			        element3Hide.setOpacity(0);
			        element3Hide.setViewOrder(4);
			        element3Hide.setDisable(true);

				}
			});
		
	}
	
	
	
	public void tooltipInitialize() {//utiliser pour les effets hover ou nous avons un texte en mettant la souris sur les elements

	    Tooltip fich = new Tooltip("fichier");
	   
	    
	    fich.setShowDelay(Duration.millis(0));
	    Tooltip.install(fichier, fich);
	    
	        
	    Tooltip edi = new Tooltip("edition");
	    edi.setShowDelay(Duration.millis(0));
		Tooltip.install(edition, edi);
		
		Tooltip sim = new Tooltip("simulation");
	    sim.setShowDelay(Duration.millis(0));
		Tooltip.install(simulation, sim);
		
		Tooltip aff = new Tooltip("affichage");
	    aff.setShowDelay(Duration.millis(0));
		Tooltip.install(affichage, aff);
		
		Tooltip aid = new Tooltip("aide");
	    aid.setShowDelay(Duration.millis(0));
		Tooltip.install(aide, aid);
		/*-------------------------------------*/
		
		Tooltip com = new Tooltip("Combinatoires");
		com.setShowDelay(Duration.millis(0));
		comb.setTooltip(com);
			
		Tooltip se = new Tooltip("Sequentiels");
		se.setShowDelay(Duration.millis(0));
		seq.setTooltip(se);
		
		Tooltip out = new Tooltip("Outils");
		out.setShowDelay(Duration.millis(0));
		outils.setTooltip(out);
		
		Tooltip por = new Tooltip("Portes");
		por.setShowDelay(Duration.millis(0));
		portes.setTooltip(por);
		
		/*------------------------------*/
		
	}
	
	
	
    
	private void ajouterLeGest(ImageView elementAdrager) {//Methode d'ajout de la fonctionallité de drag and drop avant que le composant 
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
					elementAdrager.setCursor(Cursor.DEFAULT);
				}
			});

			elementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					if (! simul) {
					ImageView dragImageView = new ImageView();
					dragImageView.setMouseTransparent(true);
					elementAdrager.setMouseTransparent(true);
					elementAdrager.setCursor(Cursor.CLOSED_HAND);

					elementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							if (! simul) {
							SnapshotParameters snapParams = new SnapshotParameters();
							snapParams.setFill(Color.TRANSPARENT);
							dragImageView.setImage(elementAdrager.snapshot(snapParams, null));
							workSpace.getChildren().add(dragImageView);
						   
							dragImageView.startFullDrag();
							
							e.consume();
							}
						}
					});

					elementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							if (! simul) {
							Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
							dragImageView.relocate(
									(int)(localPoint.getX() - dragImageView.getBoundsInLocal().getWidth() / 2),
									(int)(localPoint.getY() - dragImageView.getBoundsInLocal().getHeight() / 2 )
									);
                      
							String xString=String.valueOf(dragImageView.getLayoutX());
							String yString=String.valueOf(dragImageView.getLayoutY());
							if((dragImageView.getLayoutX()>0 && dragImageView.getLayoutX()<1066 )&&(dragImageView.getLayoutY()>0))
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
						}
					});

					elementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							if (! simul) {

							dragItem = null;

							dragImageView.setMouseTransparent(false);

							elementAdrager.setMouseTransparent(false);
							elementAdrager.setCursor(Cursor.DEFAULT);
							dragImageView.setId(elementAdrager.getId());
							instanceComposant(dragImageView);
							Image img = new Image(Circuit.getCompFromImage(dragImageView).generatePath());
							dragImageView.setImage(img);
							dragImageView.setFitHeight(img.getHeight());
							dragImageView.setFitWidth(img.getWidth());	
							
							System.out.println((e.getSceneX() +( dragImageView.getBoundsInLocal().getWidth()) / 2)+ "----------------------");
							if( dragImageView.getLayoutX() <= 0 ||dragImageView.getLayoutY() <= 0|| (e.getSceneX() +( dragImageView.getBoundsInLocal().getWidth()) / 2) > 1310 || e.getSceneY() + (dragImageView.getBoundsInLocal().getHeight() / 2)>700 || intersectionComposant(dragImageView))
							{
								workSpace.getChildren().remove(dragImageView);
								Circuit.removeCompFromImage(dragImageView);
							}
							else 
							{
								ArrayList<Polyline> polyline = Circuit.getCompFromImage(dragImageView).generatePolyline(dragImageView.getLayoutX(), dragImageView.getLayoutY());
								addAllPolylinesToWorkSpace(polyline);
								ajouterLeGestApresCollage(dragImageView);
								Donnes sauveGarde=new Donnes();
								sauveGarde.setTypeDaction(Actions.Creation);
								sauveGarde.setComposantCommeImage(dragImageView);
								sauveGarde.setComposant(Circuit.getCompFromImage(dragImageView));
								sauveGarde.setPosX(dragImageView.getLayoutX());
								sauveGarde.setPosY(dragImageView.getLayoutY());
								undoDeque.addFirst(sauveGarde);
								
							}
							}
						}});

				}
				}
			});
		
	}
	public Polyline tracerEntrerApresCollage(Polyline line ,Coordonnees crdDebut,boolean relocate) {
        //Trecer les lignes d'entrées apres le collage
		int i = 0;
        double x2 = crdDebut.getX();
        double y2 = crdDebut.getY();
        if(!relocate) {
		x = line.getPoints().get(line.getPoints().size()-6);
		y = line.getPoints().get(line.getPoints().size()-5);
		for (i = 0; i < 4; i++) {
			line.getPoints().remove(line.getPoints().size()-1);
		}
		int size = line.getPoints().size();
		if(nbOccPoint(line, line.getPoints().get(size-2), line.getPoints().get(size-1)) == 1 && size != 2)
			{
				if((Math.abs(line.getPoints().get(size-2)-x2)<10) && (Math.abs(line.getPoints().get(size-1)-y2)<10)) {
					line.getPoints().remove(size-2);
					line.getPoints().remove(size-2);
				}
			}
			
		if(Math.abs(x2-x)<10) { 
			if(Math.abs(y2-y)<10) switching = 0; 
			else switching = 1;
		}else {
			if(Math.abs(y2-y)<10) switching = 0;
		} 		
		
		if(switching == 0) line.getPoints().addAll(x2,y,x2,y2);
		else line.getPoints().addAll(x,y2,x2,y2);
		
        }else {
        	Circuit.getFilFromPolyline(line).getSource().resetPolyline(line, x2, y2);
        }
        return line;
	}
	
	public Polyline tracerSortieApresCollage(Polyline line ,Coordonnees crdDebut,boolean relocate) {
        //Trecer les lignes de sorties apres le collage
		int i = 0;
        double x2 = crdDebut.getX();
        double y2 = crdDebut.getY();
       // if(!relocate) {
		x = line.getPoints().get(4);
		y = line.getPoints().get(5);
		for (i = 0; i < 4; i++) {
			line.getPoints().remove((0));
		}
		if(line.getPoints().size() > 6) {
		if(nbOccPoint(line, line.getPoints().get(0), line.getPoints().get(1)) == 1){
			if((Math.abs(line.getPoints().get(0)-x2)<10) && (Math.abs(line.getPoints().get(1)-y2)<10)) {
				line.getPoints().remove(0);
				line.getPoints().remove(0);
			}
		}
		}else {
			if(!relocate) { //un seul polyline 
			if(Circuit.getListFromPolyline(line).size() > 1) { //pour ne pas supprimer le premier polyline
				Polyline line2 = Circuit.getListFromPolyline(line).get(1).getLinePrincipale();
				if((Math.abs(line.getPoints().get(line.getPoints().size()-2)-line2.getPoints().get(0))<10) && (Math.abs(line.getPoints().get(line.getPoints().size()-1)-line2.getPoints().get(1))<10)) {
					//Si le prochain polyline a les memes coordonnees de debut que celle de la derniere du polyline actuel
					if((Math.abs(line.getPoints().get(0)-x2)<5) && (Math.abs(line.getPoints().get(1)-y2)<5)) {
					//Suppression
						line.getPoints().clear();
						Circuit.getListFromPolyline(line).remove(0);
						listSorties.add(listSorties.indexOf(line), line2);
						listSorties.remove(line);
						Circuit.getInfoPolylineFromPolyline(line2).setLineParent(null);
						line = line2;
					}
				}
			}
		}else{
			if(!Circuit.getInfoPolylineFromPolyline(line).isRelier())
				Circuit.getFilFromPolyline(line).getSource().resetPolyline(line, x2, y2);
			}
		}
		if(Math.abs(x2-x)<10) { 
			if(Math.abs(y2-y)<10) switching = 0; 
			else switching = 1;
		}else {
			if(Math.abs(y2-y)<10) switching = 0;
		} 	
		if(switching == 0) {
			line.getPoints().add(0, x2);
			line.getPoints().add(1, y2);
			line.getPoints().add(2, x2);
			line.getPoints().add(3, y);
		}
		else {
			line.getPoints().add(0, x2);
			line.getPoints().add(1, y2);
			line.getPoints().add(2, x);
			line.getPoints().add(3, y2);
		}
        //}
		//else { 
		//	Circuit.getFilFromPolyline(line).getSource().resetPolyline(line, x2, y2);
		//}
        return line;
	}
//talle3ha lfog
	private ArrayList<Polyline> listEntrees = new ArrayList<Polyline>();
	private ArrayList<Polyline> listSorties = new ArrayList<Polyline>();
	private boolean insererNoedDebut = true;
	private void ajouterLeGestApresCollage( ImageView eleementAdrager) {//Methode d'ajout de la fonctionallité de drag and drop apres que le composant 
		//est ajoute dans le workSpace
		
	    eleementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	            eleementAdrager.setCursor(Cursor.HAND);   
	            
	        }
	    });
	    
	    eleementAdrager.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 eleementAdrager.setCursor(Cursor.DEFAULT);
			}
		});
	    
	    eleementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent e) {
	        	if (! simul) {		
	        	posX = eleementAdrager.getLayoutX();
	   		 	posY = eleementAdrager.getLayoutY();
	        
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
	        		double clicDroitX,clicDroitY;
	        		 Composant composant=Circuit.getCompFromImage(eleementAdrager);
	        		clicDroitX = e.getScreenX();
	        		clicDroitY = e.getScreenY();
	        		elementAmodifier=eleementAdrager;	        		
	        		clickDroitFenetre = new ClickDroit(composant,clicDroitX,clicDroitY,workSpace);
	        	}
              	//traitement de pressed ajoutergest apres coallge
	        	int size = 0;
	        	Composant cmp = Circuit.getCompFromImage(eleementAdrager);
	        	Polyline line ;
	        	listEntrees.clear();
	        	Coordonnees crdDebut = new Coordonnees(0,0);
	        	int i = 0;
	        	for(i = 0; i < cmp.getNombreEntree();i++){
	        		if(cmp.getEntrees()[i] != null) {
	        			crdDebut = cmp.getLesCoordonnees().coordReelesEntrees(eleementAdrager, i);
	        			line = cmp.getEntrees()[i].polylineParPoint(crdDebut);
	        			size = line.getPoints().size();
	        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	listEntrees.add(line);
	        		}
	        	}
	        	if(cmp.getLesCoordonnees().getNbCordCommandes() != 0) {
	        		for(i = 0; i < cmp.getLesCoordonnees().getNbCordCommandes();i++){
		        		if( ((Combinatoires)cmp).getCommande()[i] != null) {
		        			crdDebut = cmp.getLesCoordonnees().coordReelesCommande(eleementAdrager, i);
		        			line = ((Combinatoires)cmp).getCommande()[i].polylineParPoint(crdDebut);
		        			size = line.getPoints().size();
		        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
		    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
		    	        	listEntrees.add(line);
		        		}
		        	}
	        	}
				if(cmp.getLesCoordonnees().getCordHorloge() != null ) {
	        		if( ((Sequentiels)cmp).getEntreeHorloge() != null) {
	        			crdDebut = cmp.getLesCoordonnees().coordReelesHorloge(eleementAdrager, i);
	        			line = ((Sequentiels)cmp).getEntreeHorloge().polylineParPoint(crdDebut);
	        			size = line.getPoints().size();
	        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	listEntrees.add(line);
	        		}

				}
				if(cmp.getLesCoordonnees().getCordClear() != null ) {
					if(((Sequentiels)cmp).getClear().getSource() != null) {
						crdDebut = cmp.getLesCoordonnees().coordReelesClear(eleementAdrager, i);
	        			line = ((Sequentiels)cmp).getClear().polylineParPoint(crdDebut);
	        			size = line.getPoints().size();
	        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	listEntrees.add(line);
					}
				}
				if(cmp.getLesCoordonnees().getCordPreset() != null) {
					if(((Bascule)cmp).getPreset().getSource() != null){
						crdDebut = cmp.getLesCoordonnees().coordReelesPreset(eleementAdrager, i);
	        			line = ((Bascule)cmp).getPreset().polylineParPoint(crdDebut);
	        			size = line.getPoints().size();
	        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	listEntrees.add(line);
	    			}
				}
				if(cmp.getLesCoordonnees().getCordLoad() != null ) {
					if(((Sequentiels)cmp).getLoad().getSource() != null) {
						crdDebut = cmp.getLesCoordonnees().coordReelesLoad(eleementAdrager, i);
	        			line = ((Sequentiels)cmp).getLoad().polylineParPoint(crdDebut);
	        			size = line.getPoints().size();
	        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	listEntrees.add(line);
					}

				}
	        	listSorties.clear();
	        	for(i = 0; i < cmp.getNombreSortie();i++){
	        			crdDebut = cmp.getLesCoordonnees().coordReelesSorties(eleementAdrager, i);
	        			line = Circuit.getPolylineFromFil(cmp.getSorties()[i]).get(0).getLinePrincipale();
	        			size = line.getPoints().size();
	        			if(insererNoedDebut) {
	        			line.getPoints().add(2,line.getPoints().get(3));
	    	        	line.getPoints().add(2,line.getPoints().get(3));
	        			}
	    	        	listSorties.add(line);
	        	}
	        	insererNoedDebut = false;
	        	//hna tekmeel
	 	        	eleementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        		public void handle(MouseEvent e) {
	        			if (! simul) {
	        				if (e.getButton() == MouseButton.PRIMARY) {

	        					Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
	        					eleementAdrager.relocate(
	        							(int)(localPoint.getX() - eleementAdrager.getBoundsInLocal().getWidth() /2),
	        							(int)(localPoint.getY() - eleementAdrager.getBoundsInLocal().getHeight()/2 )
	        							);
	        					double x=eleementAdrager.getLayoutX()+eleementAdrager.getBoundsInLocal().getWidth() - 2;
	        					double y=eleementAdrager.getLayoutY()+eleementAdrager.getBoundsInLocal().getHeight()/2 - 1;
	        					String xString=String.valueOf(eleementAdrager.getLayoutX());
	        					String yString=String.valueOf(eleementAdrager.getLayoutY());
	        					if((eleementAdrager.getLayoutX()>0 && eleementAdrager.getLayoutX()<workSpace.getMaxWidth() )&&(eleementAdrager.getLayoutY()>0))
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
	        					if(e.getSceneX() > 1275)
	        					{
	        						scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	        						scrollPane.setHvalue(scrollPane.getHvalue()+0.01);
	        					}
	        					if(e.getSceneX() < 210)
	        					{							
	        						scrollPane.setHvalue(scrollPane.getHvalue()-0.01);
	        					}
	        					if(e.getSceneY() > 700)
	        					{
	        						scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
	        						scrollPane.setVvalue(scrollPane.getVvalue()+0.01);
	        					}
	        					if(e.getSceneY() < 0)
	        					{							
	        						scrollPane.setVvalue(scrollPane.getVvalue()-0.01);
	        					}
	        					e.consume();
	        					
	        		        	Composant cmp = Circuit.getCompFromImage(eleementAdrager);
	        					//Polyline line = Circuit.getPolylineFromFil(cmp.getSorties()[0]).get(0).getLinePrincipale();
	        					//Coordonnees crdDebut = cmp.getLesCoordonnees().coordReelesSorties(eleementAdrager, 0);
	        					boolean relocate = false;
	        					/*if(Circuit.getPolylineFromFil(cmp.getSorties()[0]).size() == 1)
	        						relocate = true;
	        					
	        					tracerSortieApresCollage(line, crdDebut, relocate);*/
	        					int i = 0, j = 0 ;
	        					Coordonnees crdDebut;
	        					Polyline p;
	        					while(i < cmp.getNombreSortie()) {
	        						if(cmp.getSorties()[i] != null) {
	    	        					relocate = false;
	        							p = listSorties.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							if(Circuit.getPolylineFromFil(cmp.getSorties()[i]).size() == 1)
	    	        						{relocate = true;}
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesSorties(eleementAdrager, i);
	        							p = tracerSortieApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        						i++;
	        					}    
	        					i = 0;j = 0 ;
	        					relocate = false;
	        					while(i < cmp.getNombreEntree()) {
	        						if(cmp.getEntrees()[i] != null) {
	        							p = listEntrees.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesEntrees(eleementAdrager, i);
	        							p = tracerEntrerApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        						i++;
	        					}   
	        					i = 0;
	        					while(i < cmp.getLesCoordonnees().getNbCordCommandes()) {
	        						if(((Combinatoires)cmp).getCommande()[i] != null) {
	        							p = listEntrees.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesCommande(eleementAdrager, i);
	        							p = tracerEntrerApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        						i++;
	        					}
	        					if(cmp.getLesCoordonnees().getCordHorloge() != null ) {
	        						if( ((Sequentiels)cmp).getEntreeHorloge() != null) {
	        							p = listEntrees.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesHorloge(eleementAdrager, i);
	        							p = tracerEntrerApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        					}
	        					if(cmp.getLesCoordonnees().getCordClear() != null ) {
	        						if(((Sequentiels)cmp).getClear().getSource() != null) {
	        							p = listEntrees.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesClear(eleementAdrager, i);
	        							p = tracerEntrerApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        					}
	        					if(cmp.getLesCoordonnees().getCordPreset() != null ) {
	        						if(((Bascule)cmp).getPreset().getSource() != null){
	        							p = listEntrees.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesPreset(eleementAdrager, i);
	        							p = tracerEntrerApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        					}
	        					if(cmp.getLesCoordonnees().getCordLoad() != null ) {
	        						if(((Sequentiels)cmp).getLoad().getSource()!= null) {
	        							p = listEntrees.get(j);
	        							switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesLoad(eleementAdrager, i);
	        							p = tracerEntrerApresCollage(p, crdDebut, relocate);
	        							Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
	        						}
	        					}
	        				}
	        			}
	        		}
	        	});
					eleementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							if (! simul) {
							dragItem = null;  
							if(posX != eleementAdrager.getLayoutX() || posY != eleementAdrager.getLayoutY())
							{
							Donnes sauveGarde=new Donnes();
							sauveGarde.setTypeDaction(Actions.Mouvement);
							sauveGarde.setComposantCommeImage(eleementAdrager);
							sauveGarde.setPosX(posX);
							sauveGarde.setPosY(posY);
							undoDeque.addFirst(sauveGarde);
							}
							eleementAdrager.setMouseTransparent(false);
							eleementAdrager.setMouseTransparent(false);
							eleementAdrager.setCursor(Cursor.DEFAULT);
							if( eleementAdrager.getLayoutX() <= 0 ||eleementAdrager.getLayoutY() <= 0|| (e.getSceneX() +( eleementAdrager.getBoundsInLocal().getWidth()) / 2) > 1300 || e.getSceneY() + (eleementAdrager.getBoundsInLocal().getHeight() / 2)>700 || intersectionComposant(eleementAdrager))
							{
								eleementAdrager.setLayoutX(posX);
								eleementAdrager.setLayoutY(posY);
								
							}
							else {
								posX = eleementAdrager.getLayoutX();
								posY = eleementAdrager.getLayoutY();
							}
							insererNoedDebut = true;
							}
						}
					});
				}else {
					if (eleementAdrager.getId().equals("pin")) {
						Pin pin = (Pin) Circuit.getCompFromImage(eleementAdrager);
						if (pin.getInput()) {
							eleementAdrager.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent e) {
									// TODO Auto-generated method stub

									if (pin.getEtat() == EtatLogique.ONE) {
										pin.setEtat(EtatLogique.ZERO);
									} 
									else {
										pin.setEtat(EtatLogique.ONE);
									}
									pin.evaluer();
									eleementAdrager.setCursor(Cursor.HAND);
								}
							});
						}
					}
				}
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
		 System.out.println(w.getPrefHeight());
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
        guideX.setStyle("-fx-stroke-width: 1.5px;");
        guideX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideX.setStroke(Color.web("303337")); 
        guideX.setOpacity(1);
        guideX.setLayoutY(0);
        guideX.setStartX(0);
        guideX.setStartY(0);
        guideX.setEndX(0);
        guideX.setEndY(workSpace.getMaxHeight());	
        /////////////////////////////////////////////////////
        guideXp.setStyle("-fx-stroke-width: 1.5px;");
        guideXp.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideXp.setStroke(Color.web("303337")); 
        guideXp.setOpacity(1);
        guideXp.setLayoutY(0);
        guideXp.setStartX(0);
        guideXp.setStartY(0);
        guideXp.setEndX(0);
        guideXp.setEndY(workSpace.getMaxHeight());
        /////////////////////////////////////////////////////
        guideY.setStyle("-fx-stroke-width: 1.5px;");
        guideY.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideY.setStroke(Color.web("303337")); 
        guideY.setOpacity(1);
    	guideY.setLayoutX(0);
        guideY.setStartX(0);
        guideY.setStartY(0);
        guideY.setEndX(workSpace.getMaxWidth());
        guideY.setEndY(0);
        /////////////////////////////////////////////////////
        guideYp.setStyle("-fx-stroke-width: 1.5px;");
        guideYp.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideYp.setStroke(Color.web("303337")); 
        guideYp.setOpacity(1);
    	guideYp.setLayoutX(0);
        guideYp.setStartX(0);
        guideYp.setStartY(0);
        guideYp.setEndX(workSpace.getMaxWidth());
        guideYp.setEndY(0);
        /////////////////////////////////////////////////////
        guideFilY.setStyle("-fx-stroke-width: 1.5px;");
        guideFilX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideFilX.setStroke(Color.web("303337")); 
        guideFilX.setOpacity(1);
        guideFilX.setLayoutY(0);
        guideFilX.setStartX(0);
        guideFilX.setStartY(0);
        guideFilX.setEndX(0);
        guideFilX.setEndY(workSpace.getMaxHeight());
        /////////////////////////////////////////////////////
        guideFilY.setStyle("-fx-stroke-width: 1.5px;");
        guideFilY.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideFilY.setStroke(Color.web("303337")); 
        guideFilY.setOpacity(1);
        guideFilY.setLayoutX(0);
        guideFilY.setStartX(0);
        guideFilY.setStartY(0);
        guideFilY.setEndX(workSpace.getMaxWidth());
        guideFilY.setEndY(0);
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
	/*private Polyline AjouterLignesInitiale(ImageView composant) {
    	double x=composant.getLayoutX()+composant.getBoundsInLocal().getWidth()-5;
    	double y=composant.getLayoutY()+composant.getBoundsInLocal().getHeight()/2;
    	Polyline a = new Polyline(x,y,x+8,y); //(x,y,x+8,y) avec x,y coordonnees de la sortie
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
		ajouterGeste(a);
		testPoly = a;
		return a;
    }*/


	 private boolean intersectionComposant(ImageView image) {
		 boolean trouv = false;
		Collection<ImageView> list = Circuit.getCompUtilises().values();
		Iterator<ImageView> iterator = list.iterator();
		ImageView img;
		while(iterator.hasNext() && ! trouv) {
			img = iterator.next();
			if (img != image && intersectionCoordone(img , image)) {
				trouv = true;
			}
		}
		return trouv;
	}
	 
	 
	private boolean intersectionCoordone(ImageView origin,ImageView copie) {
		
		boolean verifX = false;
		boolean verifY = false;
		Double x1 = origin.getLayoutX();
		Double y1 = origin.getLayoutY();
		Double x2 = copie.getLayoutX();
		Double y2 = copie.getLayoutY();
		if (origin.getFitWidth() < copie.getFitWidth()) {
			if ((x1>=x2 && x1<=x2 + copie.getFitWidth())||(x1+origin.getFitWidth() >= x2 && x1+origin.getFitWidth() <= x2 + copie.getFitWidth())) {
				verifX = true;
			}
		}
		else {
			if ((x2>=x1 && x2<=x1 + origin.getFitWidth())||(x2+copie.getFitWidth() >= x1 && x2+copie.getFitWidth() <= x1 + origin.getFitWidth())) {
				verifX = true;
			}
		}
		if (verifX) {
			if (origin.getFitHeight() < copie.getFitHeight()) {
				if ((y1>=y2 && y1<=y2 + copie.getFitHeight())||(y1+origin.getFitHeight() >= y2 && y1+origin.getFitHeight() <= y2 + copie.getFitHeight())) {
					verifY = true;
				}
			}
			else {
				if ((y2>=y1 && y2<=y1 + origin.getFitHeight())||(y2+copie.getFitHeight() >= y1 && y2+copie.getFitHeight() <= y1 + origin.getFitHeight())) {
					verifY = true;
				}
			}
		}
		if (verifX && verifY) {
			return true;
		}
		return false;
	}

	public int nbOccPoint(Polyline line,double x, double y) {
		ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
		int i = 0, nb=0;
		while(i < list.size()) {
			if((list.get(i)==x) && (list.get(i+1)==y ))
				{
					nb=nb+1;
				}
		i=i+2;
		}
		return nb;
	}
	 void tracerLagrill() {
	        for (int i = 0; i <= workSpace.getPrefWidth(); i += 15 ) {
	            Line l1 = new Line();
	            l1.toBack();
	            l1.setStyle("-fx-stroke-width: 0.5px;");
	            l1.setStroke(Color.web("ffffff"));
	            l1.setOpacity(0.8);
	            l1.setLayoutX(i);
	            l1.setLayoutY(0);
	            l1.setStartX(0);
	            l1.setStartY(0);
	            l1.setEndX(0);
	            l1.setEndY(workSpace.getPrefHeight());
	            l1.getStrokeDashArray().addAll(2.2d);
	            workSpace.getChildren().add(l1);
	        }
	        for (int j = 0; j <= workSpace.getPrefHeight(); j += 15 ) {
	            Line l2 = new Line();
	            l2.toBack();
	            l2.setStyle("-fx-stroke-width: 0.5px;");
	            l2.setStroke(Color.web("ffffff"));
	            l2.setOpacity(0.8);
	            l2.setLayoutX(0);
	            l2.setLayoutY(j);
	            l2.setStartX(0);
	            l2.setStartY(0);
	            l2.setEndX(workSpace.getPrefWidth());
	            l2.setEndY(0);
	            l2.getStrokeDashArray().addAll(2.2d);

	            workSpace.getChildren().add(l2);
	        }
	    }

	 private void undoChanges()
	 {
	
		 if(! undoDeque.isEmpty())
		 {
			
			 
			 Donnes sauveGarde;
			 sauveGarde= undoDeque.removeFirst();
			 switch(sauveGarde.getTypeDaction())
			 {
	
			 case Mouvement :
			 {
				 sauveGarde.getComposantCommeImage().setLayoutX(sauveGarde.getPosX());
				 sauveGarde.getComposantCommeImage().setLayoutY(sauveGarde.getPosY());
			 }break;
			 case Creation :
			 {
				
				 System.out.println(sauveGarde.getComposant().toString());
				 workSpace.getChildren().remove(sauveGarde.getComposantCommeImage());
				 
				ArrayList<Polyline> lineListe= Circuit.supprimerComp(sauveGarde.getComposant());
				 for(Polyline line : lineListe)
					 workSpace.getChildren().remove(line);
			
				
			 }break;
			 case Modification :
			 {
				 ImageView imageDeComposant= sauveGarde.getComposantCommeImage();
				 Composant composant= Circuit.getCompFromImage( imageDeComposant);
				 imageDeComposant.setImage(sauveGarde.getImage());
	    		 composant.setNombreEntree(sauveGarde.getNombreDesEntrees());
				 if(imageDeComposant.getId().equals("pin")) ((Pin)composant).setInput(sauveGarde.getTypePin());
	             if(composant.getClass().isAssignableFrom(Sequentiels.class)) ((Sequentiels)composant).setFront(sauveGarde.getFront());
				 
			 }break;
			 case Supression:
			 {
				 ImageView imageDeComposant= sauveGarde.getComposantCommeImage();
				 workSpace.getChildren().add(imageDeComposant);
				 imageDeComposant.setLayoutX(sauveGarde.getPosX());
				 imageDeComposant.setLayoutY(sauveGarde.getPosY());
				 Circuit.ajouterComposant(sauveGarde.getComposant(), imageDeComposant);
				 ArrayList<Polyline> polyline = Circuit.getCompFromImage(imageDeComposant).generatePolyline(imageDeComposant.getLayoutX(), imageDeComposant.getLayoutY());
				addAllPolylinesToWorkSpace(polyline);
				sauveGarde.getComposant().relierANouveau();
			 }break;
			 
			 
			default:
				break;
			 
			 }
		 }
	 }
 public static void sauveGarderModification()
 {
	 Composant composant=Circuit.getCompFromImage(elementAmodifier);
		Donnes sauveGarde= new Donnes();
		sauveGarde.setTypeDaction(Actions.Modification);
		sauveGarde.setComposantCommeImage(elementAmodifier);
		sauveGarde.setImage(elementAmodifier.getImage());
		sauveGarde.setNombreDesEntrees(composant.getNombreEntree());
     if(elementAmodifier.getId().equals("pin")) sauveGarde.setTypePin(((Pin)composant).isInput());
     if(composant.getClass().isAssignableFrom(Sequentiels.class)) sauveGarde.setFront(((Sequentiels)composant).getFront());
     undoDeque.addFirst(sauveGarde);
     elementAmodifier=null;
 }
	
	 public void captureEcran() {
		 WritableImage image = workSpace.snapshot(new SnapshotParameters(), null);
		 File file = new File("D:\\Shot.jpg");
		 try {
			 ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		 }catch (IOException e) {
			e.printStackTrace();
		 }
	}

	 public static void sauveGarderSupression()
	 {

			Donnes sauveGarde= new Donnes();
			sauveGarde.setTypeDaction(Actions.Supression);
			sauveGarde.setComposantCommeImage(elementAsuprimer);
			sauveGarde.setComposant(Circuit.getCompFromImage(elementAsuprimer));
			sauveGarde.setPosX(elementAsuprimer.getLayoutX());
			sauveGarde.setPosY(elementAsuprimer.getLayoutY());
	        undoDeque.addFirst(sauveGarde);
	        elementAsuprimer=null;
	 }
	 

}

