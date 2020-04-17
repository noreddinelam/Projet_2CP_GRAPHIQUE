package controllers;

import java.io.IOException;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Desktop;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.ClickBarDroite;
import application.ClickDroit;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;




public class HomeController implements Initializable {
	
    Map<ImageView,Label> elemanrsMapFillMap;
    ImageView dragItem;
    private ClickDroit clickDroitFenetre;
    private ClickBarDroite clickBar;
    private double x,y;
    private int switching = 0;
    private boolean simul = false;
    
    // utilisé dans la sauvegarde des coordonnées 
    double posX ;
	double posY ;
	
	Stage homeWindow;
	Scene homeScene;
	
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
    
    private static ImageView elementSeclecionner;
    
    
    @FXML
    private AnchorPane workSpace;
    
    @FXML
    private AnchorPane work;
    
    
    @FXML
    private static AnchorPane workSpaceTemp = new AnchorPane();
    
    
    
    @FXML
    private ImageView logo;
    /////////////////////////////Les lignes de Guide
	private Line guideX = new Line();
	private Line guideXp = new Line();
	private	Line guideY = new Line();
	private	Line guideYp = new Line();
	
	private Line guideFilX = new Line();
	private Line guideFilY = new Line();
    ///////////////////////////////////////////////
	//relier
	 private Composant source;
	 private Composant destination;
	 private int entree;
	 private int sortie;
	 private int rel;
	 //////////////////

    
    
    
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
    private  Label afficheurX;

    @FXML
    private  Label afficheurY;
    
    private static boolean copierActive;
    
    
    ClickBarDroite editionFenetre;
    
    
	////////////////////Appliquer l'animation de rotation   
    @FXML
    void mouseEnterLogo(MouseEvent event) {
    	rotationDelogo(logo,1,500);
    }
    
    @FXML
    void onSimuler(MouseEvent event) {
    	simul = (!simul);
    }
    
    public  void setHomeControllerStage(Stage homeWindow) {
    	this.homeWindow = homeWindow;
    }
    
    
    public  void setHomeControllerScene(Scene homeScene ) {
    	this.homeScene = homeScene;
    }
    
    
    
    public Stage getHomeStage() {
    	return this.homeWindow;
    }
    
    
    public void setCopierActive(boolean copierActive) {
    	this.copierActive = copierActive;
    }
    
    public boolean getCopierActive() {
    	return this.copierActive;
    }
    
    
    
    public void setWorkSpace(AnchorPane workSpace) {
    	this.workSpace = workSpace;
    }
    
    
    
    public AnchorPane getWorkSpace() {
    	return workSpace;
    }
    
    
    public void addToworkSpace(Node e) {
    	workSpace.getChildren().add(e);
    }
    
    /*
    public void setElementSelecionner(ImageView elementSeclecionner) {
    	this.elementSeclecionner = elementSeclecionner;
    }
    
    public ImageView getElementSelecionner() {
    	return this.elementSeclecionner;
    }
    */
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	
	}
	
	
	
	public void inisialiser() {
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
    	    scrollPane.setHmax(1);
    	    scrollPane.setVmax(1);
    	    tooltipInitialize();
    	    
    	    
    	    copierCollerParBouttons();
    	    
    	
    	   

    		
    	    ClickBarDroite fichierFenetre = new ClickBarDroite(1140, 100, "Fichier.fxml", homeWindow, workSpace);
    	     editionFenetre = new ClickBarDroite(1140, 180, "Edition.fxml", homeWindow, workSpace);
    	    ClickBarDroite affichageFenetre = new ClickBarDroite(1140, 300, "Affichage.fxml", homeWindow, workSpace);
    	    ClickBarDroite aideFenetre = new ClickBarDroite(1140, 350, "Aide.fxml", homeWindow, workSpace);
    	    
			  //click = new ClickBar(1000, 100);
    	    
    	    ClickBarDroite tableauFenetres[] = {fichierFenetre,editionFenetre, affichageFenetre, aideFenetre };
    	    
    	    for(ClickBarDroite click : tableauFenetres) {
				click.close();
			}
    		rightbar(fichier, fichierFenetre, tableauFenetres );
    		rightbar(edition,editionFenetre ,tableauFenetres );
    		rightbar(affichage, affichageFenetre ,tableauFenetres);
    		rightbar(aide, aideFenetre, tableauFenetres );

			
    	    	    
    	    workSpace.setOnMousePressed(new EventHandler<MouseEvent>() {
				  @Override
				  public void handle(MouseEvent event) {
					for(ClickBarDroite click : tableauFenetres) {
						click.close();
						tooltipInitialize();
					}
				  }
			  });	
    	    
    	/*    
    	    workSpace.setOnMouseExited(new EventHandler<MouseEvent>() {
    	    	
				  public void handle(MouseEvent event) {
					if(event.getSceneY() <20) {
						for(ClickBarDroite click : tableauFenetres) {
							click.close();
						}
					}
				 
    	    	
				  }
    	    	
    	    });*/
    	    
    	    

		
	}
	
	
	
	
	
	
	
	
	
	
	
	private void ajouterGestWorkSpace() {////Methodes pour Ajouter l'interaction avec le drag and drop et les guides
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
				  }
			  });		
	
	}
	
	
	
	
	public void rightbar(ImageView icon,ClickBarDroite cc, ClickBarDroite tableauDeFenetres[]) {
		  
		  icon.setOnMousePressed(new EventHandler<MouseEvent>() {
			  @Override
			  public void handle(MouseEvent event) {
				 
				  for(ClickBarDroite click :tableauDeFenetres ) {
					  if(cc.equals(click)) {
						  if(cc.isShowing()) {
							  cc.close();
							  

						  }
						  else {
							  cc.show();
						//  tooltipInitialize();
						  }
					  }
					  else
						  click.close();
				  }
				  
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
								for(Polyline line : polyline ) {
									line.setSmooth(true);
									line.setStrokeWidth(3);
									line.setStrokeType(StrokeType.CENTERED);
									line.setCursor(Cursor.HAND);
									workSpace.getChildren().add(line);
									ajouterGeste(line);
								}

								ajouterLeGestApresCollage(dragImageView);
							}
							}
						}});

				}
				}
			});
		
	}
	
	
	
	public void tracerEntrerApresCollage(Polyline line ,Coordonnees crdDebut,boolean relocate) {
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
	}
	
	public void tracerSortieApresCollage(Polyline line ,Coordonnees crdDebut,boolean relocate) {
        int i = 0;

        double x2 = crdDebut.getX();
        double y2 = crdDebut.getY();
        if(!relocate) {
        	/*if(line.getPoints().size()<5)
        		line.getPoints().addAll(line.getPoints().get(2),line.getPoints().get(3));*/
		x = line.getPoints().get(4);
		y = line.getPoints().get(5);
		
		for (i = 0; i < 4; i++) {
			line.getPoints().remove((0));
		}
		System.out.println("hna"+nbOccPoint(line, line.getPoints().get(0), line.getPoints().get(1)));
		if(nbOccPoint(line, line.getPoints().get(0), line.getPoints().get(1)) == 1){
			if((Math.abs(line.getPoints().get(0)-x2)<10) && (Math.abs(line.getPoints().get(1)-y2)<10)) {
				line.getPoints().remove(0);
				line.getPoints().remove(0);
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
		
        }else {
        	Circuit.getFilFromPolyline(line).getSource().resetPolyline(line, x2, y2);
        }
	}
	
	
	
	
	
	
	
	
	
	private ArrayList<Polyline> listEntrees = new ArrayList<Polyline>();
	private ArrayList<Polyline> listSorties = new ArrayList<Polyline>();
	
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
	            
				elementSeclecionner = eleementAdrager;
				/*---*/

	            
	            
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
	        		clicDroitX = e.getScreenX();
	        		clicDroitY = e.getScreenY();
	        		clickDroitFenetre = new ClickDroit(Circuit.getCompFromImage(eleementAdrager),clicDroitX,clicDroitY);
	        	}
	        	//traitement de pressed ajoutergest apres coallge
	        	int size = 0;
	        	Composant cmp = Circuit.getCompFromImage(eleementAdrager);
	        	Polyline line ;
	        	listEntrees.clear();
	        	Coordonnees crdDebut = new Coordonnees(0,0);
	        	int i = 0;
	        	for(i = 0; i < cmp.getNombreEntree();i++){
	        		System.out.println(cmp.getNombreEntree());
	        		if(cmp.getEntrees()[i] != null) {
	        			crdDebut = cmp.getLesCoordonnees().coordReelesEntrees(eleementAdrager, i);
	        			line = cmp.getEntrees()[i].polylineParPoint(crdDebut);
	        			size = line.getPoints().size();
	        			line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	line.getPoints().add(size-3,line.getPoints().get(size - 2));
	    	        	listEntrees.add(line);
	        		}
	        	}
	        	listSorties.clear();
	        	for(i = 0; i < cmp.getNombreSortie();i++){
	        			//crdDebut = cmp.getLesCoordonnees().coordReelesSorties(eleementAdrager, i);
	        			line = Circuit.getPolylineFromFil(cmp.getSorties()[i]).get(0).getLinePrincipale();
	        			//size = line.getPoints().size();
	        			line.getPoints().add(2,line.getPoints().get(3));
	    	        	line.getPoints().add(2,line.getPoints().get(3));
	    	        	listSorties.add(line);
	        	}
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
	        						afficheurX.setText("X : "+ xString);
	        						
	        						afficheurY.setText("Y : "+ yString);
	        						
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
	        					int i = 0,j = 0 ;
	        					Coordonnees crdDebut;
	        					Polyline p;
	        					while(i < cmp.getNombreSortie()) {
	        						if(cmp.getSorties()[i] != null) {
	        							switching = cmp.getSorties()[i].getSwitching();
	        							p = listSorties.get(j);
	        							if(Circuit.getPolylineFromFil(cmp.getSorties()[i]).size() == 1)
	    	        						{relocate = true;}
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesSorties(eleementAdrager, i);
	        							tracerSortieApresCollage(p, crdDebut, relocate);
	        							cmp.getSorties()[i].setSwitching(switching);
	        						}
	        						i++;
	        					}    
	        					i = 0;j = 0 ;
	        					relocate = false;
	        					while(i < cmp.getNombreEntree()) {
	        						if(cmp.getEntrees()[i] != null) {
	        							switching = cmp.getEntrees()[i].getSwitching();
	        							p = listEntrees.get(j);
	        							j++;
	        							crdDebut = cmp.getLesCoordonnees().coordReelesEntrees(eleementAdrager, i);
	        							tracerEntrerApresCollage(p, crdDebut, relocate);
	        							cmp.getEntrees()[i].setSwitching(switching);
	        						}
	        						i++;
	        					}    
	        				}
	        			}
	        		}
	        	});
					eleementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							if (! simul) {
							dragItem = null;  	 
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
        guideX.setStyle("-fx-stroke-width: 0.3px;");
        guideX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideX.setStroke(Color.web("ffffff")); 
        guideX.setOpacity(1);
        guideX.setLayoutY(0);
        guideX.setStartX(0);
        guideX.setStartY(0);
        guideX.setEndX(0);
        guideX.setEndY(workSpace.getMaxHeight());	
        /////////////////////////////////////////////////////
        guideXp.setStyle("-fx-stroke-width: 0.3px;");
        guideXp.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideXp.setStroke(Color.web("ffffff")); 
        guideXp.setOpacity(1);
        guideXp.setLayoutY(0);
        guideXp.setStartX(0);
        guideXp.setStartY(0);
        guideXp.setEndX(0);
        guideXp.setEndY(workSpace.getMaxHeight());
        /////////////////////////////////////////////////////
        guideY.setStyle("-fx-stroke-width: 0.3px;");
        guideY.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideY.setStroke(Color.web("ffffff")); 
        guideY.setOpacity(1);
    	guideY.setLayoutX(0);
        guideY.setStartX(0);
        guideY.setStartY(0);
        guideY.setEndX(workSpace.getMaxWidth());
        guideY.setEndY(0);
        /////////////////////////////////////////////////////
        guideYp.setStyle("-fx-stroke-width: 0.3px;");
        guideYp.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideYp.setStroke(Color.web("ffffff")); 
        guideYp.setOpacity(1);
    	guideYp.setLayoutX(0);
        guideYp.setStartX(0);
        guideYp.setStartY(0);
        guideYp.setEndX(workSpace.getMaxWidth());
        guideYp.setEndY(0);
        /////////////////////////////////////////////////////
        guideFilX.setStyle("-fx-stroke-width: 0.3px;");
        guideFilX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideFilX.setStroke(Color.web("ffffff")); 
        guideFilX.setOpacity(1);
        guideFilX.setLayoutY(0);
        guideFilX.setStartX(0);
        guideFilX.setStartY(0);
        guideFilX.setEndX(0);
        guideFilX.setEndY(workSpace.getMaxHeight());
        /////////////////////////////////////////////////////
        guideFilY.setStyle("-fx-stroke-width: 0.3px;");
        guideFilY.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
        guideFilY.setStroke(Color.web("ffffff")); 
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
	private Polyline AjouterLignesInitiale(ImageView composant) {
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
    }
	public Polyline initialser(double x, double y) {
		Polyline a = new Polyline(x,y,x,y,x,y); 
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
    	workSpace.getChildren().add(a);
    	ajouterGeste(a);
		return a;
	}
		
	 public void ajouterGeste(Polyline line)
		{
		 if (! simul) {
		 EventHandler<MouseEvent> event1 = new javafx.event.EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					//les guides :
					guideFilX.setLayoutX(event.getSceneX()-180);
					guideFilY.setLayoutY(event.getSceneY());
					double x2 = event.getX();
					double y2 = event.getY();
				//	Double x2 = event.getSceneX()-180;
				//	Double y2 = event.getSceneY();
					//if(intersectionFilComposants(event.getSceneX()-180,event.getSceneY())) {
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
					workSpace.getChildren().add(guideFilX);
                    workSpace.getChildren().add(guideFilY);
                    guideFilX.setLayoutX(event.getSceneX()-180);
					guideFilY.setLayoutY(event.getSceneY());
					//relier
					
					/*source = Circuit.getFilFromPolyline(line).getSource();
					sortie = 0; 	
					//
                    
					int i = line.getPoints().size()-2;
					x = event.getX();
					y = event.getY();
					while((i!=0) && ((Math.abs(x-line.getPoints().get(i))>5) && ((Math.abs(y - line.getPoints().get(i+1)) >=5 ))))
					{
						line.getPoints().addAll(line.getPoints().get(i),line.getPoints().get(i+1));
						i=i-2;
					}

					double x1 = line.getPoints().get(i),y1 = line.getPoints().get(i+1);
					if(Math.abs(x-line.getPoints().get(i)) < 5) {
						if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
							line.getPoints().addAll(x1,y1,x1,y1,x1,y1);
						}
						else {
							line.getPoints().addAll(x1,y1,x1,y,x1,y,x1,y);
						}	
					}else{
						if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
							line.getPoints().addAll(x1,y1,x,y1,x,y1,x,y1);
						}
					}*/
					source = Circuit.getFilFromPolyline(line).getSource();
					x = event.getX();
					y = event.getY();
					Polyline line2 = initialser(x, y);
					line2.getPoints().clear();
					line2.getPoints().addAll(line.getPoints());
					//workSpace.getChildren().add(line2);
					ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
					int i = 0;
					boolean trouve = false ;
					
					while((!trouve) && i<list.size()) {
						if((Math.abs(x - list.get(i)) < 5) && (Math.abs(y - list.get(i+1)) < 5)) {
							trouve = true;
							x = list.get(i);
							y = list.get(i+1);
						}
						i = i+2;
					}
					i = 0;
					if(!trouve) {
						while((!trouve) && i<list.size()) {
							if(Math.abs(x - list.get(i)) < 5 ) {
								trouve = true;
								x = list.get(i);
							}else if (Math.abs(y - list.get(i+1)) < 5) {
								trouve = true;
								y = list.get(i+1);
							}
						i = i + 2;
						}
					}
					line2 = initialser(x, y);
					line.getPoints().clear();
					line.getPoints().addAll(line2.getPoints());
					//ajouterGeste(line2);
					ajouterGeste(line2);					
				}
			};
			line.setOnMousePressed(event);
			//a.addEventHandler(MouseEvent.MOUSE_DRAGGED, event1);
			line.setOnMouseDragged(event1);
			line.setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					workSpace.getChildren().remove(guideFilX);
		  			workSpace.getChildren().remove(guideFilY);

					int	der =  line.getPoints().size()-1;
					if(intersectionFilComposants(arg0.getSceneX()-180,arg0.getSceneY()) != null) {
					//if(intersectionFilComposants(line.getPoints().get(der-1),line.getPoints().get(der))) {
						//System.out.println("reeeeeeeeel"+rel);
					if(rel == 0) {
						line.getPoints().remove(der);line.getPoints().remove(der-1);line.getPoints().remove(der-2);line.getPoints().remove(der-3);
					}if(rel == 1) {
						destination = intersectionFilComposants(arg0.getSceneX()-180,arg0.getSceneY());
						Coordonnees crd = new Coordonnees(arg0.getSceneX()-180,arg0.getSceneY());
						//entree = destination.getLesCoordonnees().indexCoord(crd);
						Circuit.relier(source, destination, sortie, entree);
						System.out.println("trabtooo");
						playSound();
						if(source.getClass().getSimpleName().equals("Pin")) ((Pin)source).setEtat(EtatLogique.ZERO);
					}
					}else {
					der =  line.getPoints().size()-1;
					if( Math.abs(line.getPoints().get(der)-line.getPoints().get(der-2)) < 10  &&  Math.abs(line.getPoints().get(der-1)-line.getPoints().get(der-3))< 10) {
						line.getPoints().remove(der);line.getPoints().remove(der-1);}
				}
					System.out.println(line.getPoints().size());
				}
			});
		}
		/*line.setOnMouseDragEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
			      guideFilX.setLayoutX(event.getX());
			      guideFilY.setLayoutY(event.getY());
			}
		});*/
		}
	 public void playSound() {
		    try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("../src/1.wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
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
	 
	 private Composant intersectionFilComposants(Double x, Double y) {
		 Composant cmp = null;
		 boolean trouv = false;
		Collection<ImageView> list = Circuit.getCompUtilises().values();
		Iterator<ImageView> iterator = list.iterator();
		ImageView img;
		while(iterator.hasNext() && ! trouv) {
			img = iterator.next();
			if (intersectionFilComposant(img, x, y) != -1) {
				trouv = true;
				cmp = Circuit.getCompFromImage(img);
				rel = intersectionFilComposant(img, x, y);
				System.out.println(rel);
			}
		}
		return cmp;
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
	public int intersectionFilComposant(ImageView imgCmp,double Xfil,double Yfil) {
		Double XImg = imgCmp.getLayoutX();
		Double Yimg = imgCmp.getLayoutY();
		
		if(( Xfil >= XImg  )  &&  (XImg+imgCmp.getFitWidth() > Xfil) && ( Yfil >= Yimg)  &&  (Yimg+imgCmp.getFitHeight() > Yfil) ) {
			Composant cmp = Circuit.getCompFromImage(imgCmp);
			Coordonnees tabEntrees[] = cmp.getLesCoordonnees().getCordEntree();
			int nbCord = cmp.getLesCoordonnees().getNbCordEntree();
			Coordonnees crd = new Coordonnees(Xfil,Yfil);
			boolean trouve = false;
			int i = 0;
			while( i < nbCord && trouve == false) { 
				Coordonnees crdTab = new Coordonnees(tabEntrees[i].getX() + imgCmp.getLayoutX(), tabEntrees[i].getY() + imgCmp.getLayoutY());				
				System.out.println(Xfil+" "+crdTab.getX()+" "+Yfil+" "+crdTab.getY()+"layaoutx"+imgCmp.getLayoutX()+"layaouty"+imgCmp.getLayoutY());
				if( crdTab.equals(crd) ) { 
					trouve =true;
					entree=i; 
				}
				i++;
			}
			if(trouve)
				return 1;
			else return 0;
		}else return -1;
		
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
	
	/*------------------------------------edition----------------------------*/
	
	  	@FXML
	    private Button annuler;

	    @FXML
	    private Button dupliquer;
	    

	    @FXML
	    private Button couper;

	    @FXML
	    private Button selectionnerTout;

	    @FXML
	    private Button copier;

	    @FXML
	    private Button supprimer;

	    @FXML
	    private Button coller;

	    @FXML
	    private Button supprimerTout;

	    @FXML
	    void supprimerTout(ActionEvent event) {
	    	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Voullez vous vraimment supprmer tout !");
			Optional<ButtonType> result = alert.showAndWait();	    		
			if(result.get() == ButtonType.OK){
				ClickBarDroite.getWorkStageFromRightBar().getChildren().clear();
				Circuit.getCompUtilises().clear();
				Circuit.getFilUtilises().clear();
			}
	    	
	    }
	    
	    
	    
	    
	    
	    @FXML
	    public void copier(ActionEvent event) {
					    	System.out.println("l'element est bien selecltionner : "+elementSeclecionner.getId());
					    	setCopierActive(true);

	    }
	    
	    
	  
	    
	    
	    
	    
	    static boolean cc;
	    
	    public void coller(ActionEvent event) {
	    		cc = true;
	   	    	Stage stage = (Stage) coller.getScene().getWindow();
	   	    	stage.close();
		
	    }
	    
	   
		
	    
	    
	    
	    public void copierCollerParBouttons() {
	    	final KeyCombination kb1 = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
	    	final KeyCombination kb2 = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY);
	    	
	    	
	    	workSpace.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
	    	    if(cc) {	
	    	    	cc = false;
	    	    	x = (int) event.getSceneX()-200;
					 y= (int) event.getSceneY();
	    	    	ImageView dragImageView = new ImageView();
					dragImageView.setLayoutX(x);
					dragImageView.setLayoutY(y);
					dragImageView.setId(elementSeclecionner.getId());
					instanceComposant(dragImageView);		
					Composant cmp = Circuit.getCompFromImage(elementSeclecionner);
					Composant cmp2 = Circuit.getCompFromImage(dragImageView);
	
					cmp2.setDirection(cmp.getDirection());
					cmp2.setIcon(cmp.getIcon());
					cmp2.setLesCoordonnees(cmp.getLesCoordonnees());
					cmp2.setNom(cmp.getNom());
					cmp2.setNombreEntree(cmp.getNombreEntree());
					cmp2.setNombreSortie(cmp.getNombreSortie());
					cmp2.setCord();
					cmp2.generatePolyline(x, y);	
					Image img = new Image(Circuit.getCompFromImage(elementSeclecionner).generatePath());
					dragImageView.setImage(img);
					dragImageView.setFitHeight(img.getHeight());
					dragImageView.setFitWidth(img.getWidth());		
					workSpace.getChildren().add(dragImageView);

					ArrayList<Polyline> polyline = Circuit.getCompFromImage(dragImageView).generatePolyline(dragImageView.getLayoutX(), dragImageView.getLayoutY());
					for(Polyline line : polyline ) {
						line.setSmooth(true);
						line.setStrokeWidth(3);
						line.setStrokeType(StrokeType.CENTERED);
						line.setCursor(Cursor.HAND);
						workSpace.getChildren().add(line);
						ajouterGeste(line);
					}

					ajouterLeGestApresCollage(dragImageView);	
						
	    	    	
	    	    }
	    		
	    	
	    	});
	    	
	    	homeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				 public void handle(final KeyEvent keyEvent) {
				   if (kb1.match(keyEvent)) {
				    System.out.println("control + c are pressed !");
				    System.out.println("l'element selectionner est : "+ elementSeclecionner.getId());
				    setCopierActive(true);	  
				    keyEvent.consume();
				   }
				   
				    keyEvent.consume();

				   
				   
				   workSpace.setOnMousePressed(new EventHandler<MouseEvent>() {
						  @Override
						  public void handle(MouseEvent event) {
							  
							 x = (int) event.getSceneX()-200;
							 y= (int) event.getSceneY();
							
						  }
					  });				   
				   if (kb2.match(keyEvent) && getCopierActive()) {

					   
					   
					    System.out.println("control + v are pressed !");	    
						ImageView dragImageView = new ImageView();
						dragImageView.setLayoutX(x);
						dragImageView.setLayoutY(y);
						dragImageView.setId(elementSeclecionner.getId());
						instanceComposant(dragImageView);		
						Composant cmp = Circuit.getCompFromImage(elementSeclecionner);
						Composant cmp2 = Circuit.getCompFromImage(dragImageView);
		
						cmp2.setDirection(cmp.getDirection());
						cmp2.setIcon(cmp.getIcon());
						cmp2.setLesCoordonnees(cmp.getLesCoordonnees());
						cmp2.setNom(cmp.getNom());
						cmp2.setNombreEntree(cmp.getNombreEntree());
						cmp2.setNombreSortie(cmp.getNombreSortie());
						cmp2.setCord();
						cmp2.generatePolyline(x, y);	
						Image img = new Image(Circuit.getCompFromImage(elementSeclecionner).generatePath());
						dragImageView.setImage(img);
						dragImageView.setFitHeight(img.getHeight());
						dragImageView.setFitWidth(img.getWidth());		
						workSpace.getChildren().add(dragImageView);

						ArrayList<Polyline> polyline = Circuit.getCompFromImage(dragImageView).generatePolyline(dragImageView.getLayoutX(), dragImageView.getLayoutY());
						for(Polyline line : polyline ) {
							line.setSmooth(true);
							line.setStrokeWidth(3);
							line.setStrokeType(StrokeType.CENTERED);
							line.setCursor(Cursor.HAND);
							workSpace.getChildren().add(line);
							ajouterGeste(line);
						}

						ajouterLeGestApresCollage(dragImageView);	
						
						//ajouterLeGestApresCollage(dragImageView);
					    //keyEvent.consume();
					   }
					
				   
				   
				   
				 }
				 
				});
	    	
	    	
	    	
	    	 
	    	
	    }
	    
	    
	    
	    
	    
	    
	    

	    /*----------------------fichier------------------------------------*/
	    
	    @FXML
	    private Button nouveau;

	    @FXML
	    private Button importer;

	    @FXML
	    private Button ouvrir;

	    @FXML
	    private Button fermer;

	    @FXML
	    private Button sauvegarder;

	    @FXML
	    private Button encapsuler;

	    @FXML
	    private Button sauvComme;

	    @FXML
	    void fermer(ActionEvent event) {
	    	Stage stage = (Stage) fermer.getScene().getWindow();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Voullez vous vraimment quitter ! ");
			Optional<ButtonType> result = alert.showAndWait();	    		
			if(result.get() == ButtonType.OK){
			Stage s = (Stage) stage.getOwner();
			s.close();
			
			}
	    }

	    @FXML
	    void nouveau(ActionEvent event) {
	    	 try {
	    		 Stage stage = new Stage();
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Home.fxml"));
			        Parent root =(Parent)fxmlLoader.load();
			        HomeController m =(HomeController)fxmlLoader.getController();
			        m.setHomeControllerStage(stage);
			        m.inisialiser();
			       
			        Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
			        stage.setScene(scene);  
			        stage.setResizable(false);

			        stage.show();
			    } catch(Exception e) {
			        e.printStackTrace();
			    }
			 
	    }
	    @FXML
	    void ouvrir(ActionEvent event) {
	    	/*final DirectoryChooser directoryChooser = new DirectoryChooser();
	            final File selectedDirectory = directoryChooser.showDialog(homeWindow);*/
	            final FileChooser fileChooser = new FileChooser();
	            fileChooser.setInitialDirectory(
	                    new File(System.getProperty("user.home"))
	                );                 
	                fileChooser.getExtensionFilters().addAll(
	                    new FileChooser.ExtensionFilter("BIN", "*.bin")
	                );
	            
	            File f = fileChooser.showOpenDialog(homeWindow);
	            Circuit circuit = Sauvegarde.loadCiruit(f.getAbsolutePath());
	            if(circuit != null)
	            	System.out.println("le circuit est bien charge :)");

	    	
	    }
	    
	    
	    
	    
	    @FXML
	    void save(ActionEvent event) {
	    	
	    	if(Circuit.getCompUtilises().isEmpty()) {
	    		Alert a = new Alert(AlertType.INFORMATION);
		    	a.setContentText("le circuit est vide y a rien a sauvgarder");

		    	a.show();
	    	}
	    	else
	    	{
	    		Alert a = new Alert(AlertType.INFORMATION);
		    	a.setContentText("le circuit est bien sauvgarde");

		    	a.show();
	    	}
	    	
	    	

	    }
	    
	    	
	    
	    
	    @FXML
	    void saveAs(ActionEvent event) {

            final FileChooser fileChooser = new FileChooser();
            File f = fileChooser.showSaveDialog(homeWindow);
            System.out.println("the name of the file is : "+f.getAbsolutePath());
    		Circuit circuit = new Circuit();
            Sauvegarde.saveCiruit(circuit, f.getAbsolutePath()+".bin");

	    }
	    
	    @FXML
	    void importer(ActionEvent event) {
	    	
	    	 final FileChooser fileChooser = new FileChooser();
	            fileChooser.setInitialDirectory(
	                    new File(System.getProperty("user.home"))
	                );                 
	                fileChooser.getExtensionFilters().addAll(
	                    new FileChooser.ExtensionFilter("BIN", "*.bin")
	                );
	            
	            File f = fileChooser.showOpenDialog(homeWindow);
	            Circuit circuit = Sauvegarde.loadCiruit(f.getAbsolutePath());
	            if(circuit != null)
	            	System.out.println("le circuit est bien charge :)");

	    }
	    
	    
	    @FXML
	    void encapsulerEtSauvgarder(ActionEvent event) {
	    	final FileChooser fileChooser = new FileChooser();
            File f = fileChooser.showSaveDialog(homeWindow);
            System.out.println("the name of the file is : "+f.getAbsolutePath());
    		Circuit circuit = new Circuit();
            Sauvegarde.saveCiruit(circuit, f.getAbsolutePath()+".bin");

	    }

	    
	    
	    

	    
	    /*---------------------------affichage--------------------------------*/
	    @FXML
	    private Button tableVerite;

	    @FXML
	    private Button chronogramme;

	    @FXML
	    void chronogramme(ActionEvent event) {
	    	 try {
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Chronogramme.fxml"));
			        Parent root = fxmlLoader.load();
			        Stage stage = new Stage();
			        Scene scene = new Scene(root);
			        stage.setScene(scene);  
			        stage.setTitle("le chronogramme");
			        stage.setResizable(false);
				    stage.initModality(Modality.APPLICATION_MODAL);

			        stage.show();
			    } catch(Exception e) {
			        e.printStackTrace();
			    	}

	    }

	    @FXML
	    void tableDeVerite(ActionEvent event) {
	    	 try {
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/TableDeVerite.fxml"));
			        Parent root = fxmlLoader.load();
			        Stage stage = new Stage();
			        Scene scene = new Scene(root);
			        stage.setScene(scene);  
			        stage.setTitle("la table de verité");
			        stage.setResizable(false);
				    stage.initModality(Modality.APPLICATION_MODAL);

			        stage.show();
			    } catch(Exception e) {
			        e.printStackTrace();
			    	}

	    }
	
	
	
	/*------------------------about --------------------------------*/
	    
	    @FXML
	    private Button lien;
	    
	    @FXML
	    private Button aideOnline;

	    @FXML
	    private Button guideUser;

	    @FXML
	    private Button about;

	    @FXML
	    void aboutSimulIni(ActionEvent event) {
	    	 try {
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/About.fxml"));
			        Parent root1 = fxmlLoader.load();
			        Stage stage = new Stage();
			        Scene scene = new Scene(root1);
					scene.getStylesheets().add(getClass().getResource("/styleFile/about.css").toExternalForm());
			        stage.setScene(scene);  
			        stage.setTitle("About SimulINI");
			        stage.setResizable(false);
				    stage.initModality(Modality.APPLICATION_MODAL);
			        stage.show();
			    } catch(Exception e) {
			        e.printStackTrace();
			    }

	    }

	    

	   
	public void enligne(String l) {//une methode utilise pour ouvrir un lien dans le navigateur par defaut
			try {
				Desktop.getDesktop().browse(new URL(l).toURI());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void siteWeb(ActionEvent event) {
			enligne("https://simulini.netlify.com");
		}
		
		public void aideEnLigne(ActionEvent event) {
			enligne("https://simulini.netlify.com/page-2/");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

