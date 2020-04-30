package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.imageio.ImageIO;

import application.ClickBarDroite;
import application.ClickDroit;
import application.ClickSouris2;
import application.FenetreDesErreurs;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import noyau.Actions;
import noyau.AdditionneurN_Bites;
import noyau.AfficheurSegment;
import noyau.And;
import noyau.Bascule;
import noyau.Circuit;
import noyau.CircuitIntegre;
import noyau.Combinatoires;
import noyau.Composant;
import noyau.Compteur;
import noyau.Coordonnees;
import noyau.D;
import noyau.Decodeur;
import noyau.DemiAdditionneur;
import noyau.Demultiplexeur;
import noyau.Donnes;
import noyau.Encodeur;
import noyau.EtatLogique;
import noyau.Fil;
import noyau.Front;
import noyau.Horloge;
import noyau.InfoPolyline;
import noyau.JK;
import noyau.Multiplexeur;
import noyau.Nand;
import noyau.Nor;
import noyau.Not;
import noyau.Or;
import noyau.Pin;
import noyau.RST;
import noyau.RegistreDecalage;
import noyau.Sauvegarde;
import noyau.Sequentiels;
import noyau.SourceConstante;
import noyau.T;
import noyau.Xor;

public class HomeController extends Controller {

	Map<ImageView, Label> elemanrsMapFillMap;

	ImageView dragItem;
	private static ClickDroit clickDroitFenetre;

	private static ClickBarDroite fichierFenetre;
	private static ClickBarDroite affichageFenetre;
	private static ClickBarDroite editionFenetre;
	private static ClickBarDroite aideFenetre;
	public static File fichierCourant;
	public static Thread t1;
	Horloge horloge = null;

	// utilisé dans la sauvegarde des coordonnées

	double posX;
	double posY;

	public static boolean copyActive, copyMouse, pastButton;

	private static double ctrlX, ctrlY;
	static Composant composantCopy;

	public static Deque<Donnes> undoDeque = new LinkedList<Donnes>();

	final KeyCombination touchesDundo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);

	public static ImageView elementSeclecionner;

	public static ImageView elementAsuprimer = null;

	private static ImageView elementAmodifier = null;
	///////////////////////////// Les lignes de Guide
	private static Line guideX = new Line();
	private static Line guideXp = new Line();
	private static Line guideY = new Line();
	private static Line guideYp = new Line();
	private static boolean copierActive;

	private ArrayList<Polyline> listEntrees = new ArrayList<Polyline>();
	private ArrayList<Polyline> listSorties = new ArrayList<Polyline>();
	private boolean insererNoedDebut = true;

	/*------------------------------------edition----------------------------*/

	static boolean cc;
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

	public static boolean chrono = false;

	@FXML
	private Button coller;

	@FXML
	private Button supprimerTout;

	// -------------------------------------------------------------------------------------------------------------------------------

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

	// -----------------------------------------------------------------------

	/*---------------------------affichage--------------------------------*/
	@FXML
	private Button tableVerite;

	@FXML
	private Button chronogramme;

	// -----------------------------------------------------------------------

	/*------------------------about --------------------------------*/

	@FXML
	private Button lien;

	@FXML
	private Button aideOnline;

	@FXML
	private Button guideUser;

	@FXML
	private Button about;

	// -------------------------------------------------------------------

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

	public static boolean horloged = false;

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

	static public ImageView horlogeDeCercuit;

	@FXML
	private AnchorPane work;

	@FXML
	private ImageView camera;

	@FXML
	private ImageView logo;

	///////////////////////////// Les lignes de Guide

	///////////////////////////////////////////////
	// relier

	//////////////////
	Stage stage;

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
		imageView.setOnMouseEntered(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent arg0){
			// TODO Auto-generated method stub
			if(!imageView.getId().equals("simulation")||imageView.getId().equals("simulation")&&!simul){
				imageView.setImage(new Image("homePage_icones/"+imageView.getId()+"Hover.png"));}
		}

		});

		imageView.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override public void handle(MouseEvent arg0){
				// TODO Auto-generated method stub
				if(!imageView.getId().equals("simulation")||imageView.getId().equals("simulation")&&!simul)
					imageView.setImage(new Image("homePage_icones/"+imageView.getId()+".png"));
			}
		}
				);
	}

	void initialiseAnimationOfBarDroite(){
		ajouterAnimationBarDroite(fichier);
		ajouterAnimationBarDroite(edition);
		ajouterAnimationBarDroite(simulation);
		ajouterAnimationBarDroite(affichage);
		ajouterAnimationBarDroite(aide);
		ajouterAnimationBarDroite(camera);
		camera.setCursor(Cursor.HAND);

	}



	@FXML void mouseEnterLogo(MouseEvent event){ // ajouter une rotation pour le logo
		rotationDelogo(logo,1,500,true);
	}

	@FXML void screenShot(MouseEvent event){
		final FileChooser fileChooser=new FileChooser();File f=fileChooser.showSaveDialog(homeWindow);
		if(f!=null){
			captureEcran(f.getAbsolutePath());
		}
	}

	@FXML
	void onSimuler(MouseEvent event) { /// pour lancer la simulation ou l'arreter
		Circuit.clearException();
		simul = (!simul);
		if (simul) {
			if (clickSouris2 != null)
				clickSouris2.close();
			Circuit.validerCircuits();
			if ( Circuit.isThereAnyException()) {
				if (Circuit.isThereAnyError()) {
					simul = false;
					simulation.setImage(new Image("homePage_icones/simulation.png"));
				}
				else {
					simulation.setImage(new Image("homePage_icones/SIMULATION_ON.png"));
					if(! horloged)	Circuit.initialiser();
					else {
						horloge=((Horloge)Circuit.getCompFromImage(horlogeDeCercuit));
						horloge.setImage(horlogeDeCercuit);
						t1=new Thread(horloge);
						t1.start();
					}
					rotationDelogo(logo,1,1000,false);
				}
				new FenetreDesErreurs(homeWindow);
			}
			else {
				simulation.setImage(new Image("homePage_icones/SIMULATION_ON.png"));
				if(! horloged)	Circuit.initialiser();

				else {
					horloge=((Horloge)Circuit.getCompFromImage(horlogeDeCercuit));

					horloge.setImage(horlogeDeCercuit);
					t1 = new Thread(horloge);
					t1.start();
				}
				rotationDelogo(logo, 1, 1000, false);
			}

		}
		else {
			simulation.setImage(new Image("homePage_icones/SIMULATION.png"));
			Circuit.defaultCompValue();

			if( horloged)
			{
				try {
					horlogeDeCercuit.setImage( new Image("/Horloge/0.png"));

					horloge.stop();
					t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	public void setHomeControllerStage(Stage w) {
		homeWindow = w;
	}


	public void setHomeControllerScene(Scene scene) {
		homeScene = scene;
	}

	public Stage getHomeStage() {
		return homeWindow;
	}

	public static void setCopierActive(boolean c) {
		copierActive = c;
	}

	public static boolean getCopierActive() {
		return copierActive;
	}


	public void inisialiser() { /// pour l'initialisation des effets de la fenetre principale (affichage des guides ajout de
		/// l'operation du drag and drop pour tout les composants			
		ajouterGestWorkSpace(); /// ajouter des listners pour capter les operations (clic , drag .. etc)
		tracerLesGuides(); /// tracer les guides qui aide l'user pour connaitre la position de l'elt
		afficheurX.setText("X : 0");
		afficheurY.setText("Y : 0");
		// Creation d'une map pour gerer les titres des composants
		elemanrsMapFillMap = new HashMap<ImageView, Label>() {
			{
				put(hex, tHex);

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
				put(demiAdd, tDadd);
			}
		};
		//// Ajouter pour chaque Composant les gestes de drag and drop

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

		tracerLesregles(workSpace); /// tracer les regles qui entoure la fenetre et ils aident aussi dans le
		/// deplacement
		scrollPane.setHmax(1);
		scrollPane.setVmax(1);
		tooltipInitialize();
		initialiseAnimationOfBarDroite();

		fichierFenetre = new ClickBarDroite(1055, 50, "Fichier.fxml", homeWindow, workSpace, afficheurX, afficheurY,
				scrollPane);
		editionFenetre = new ClickBarDroite(1055, 115, "Edition.fxml", homeWindow, workSpace, afficheurX, afficheurY,
				scrollPane);
		affichageFenetre = new ClickBarDroite(1055, 255, "Affichage.fxml", homeWindow, workSpace, afficheurX,
				afficheurY, scrollPane);
		aideFenetre = new ClickBarDroite(1055, 300, "Aide.fxml", homeWindow, workSpace, afficheurX, afficheurY,
				scrollPane);

		ClickBarDroite tableauFenetres[] = { fichierFenetre, editionFenetre, affichageFenetre, aideFenetre };

		for (ClickBarDroite click : tableauFenetres) {
			click.close();
		}
		rightbar(fichier, fichierFenetre, tableauFenetres);
		rightbar(edition, editionFenetre, tableauFenetres);
		rightbar(affichage, affichageFenetre, tableauFenetres);
		rightbar(aide, aideFenetre, tableauFenetres);

		workSpace.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!simul) {
					ctrlX = event.getX();
					ctrlY = event.getY();
					if(cc && elementSeclecionner != null) {
						cc = false;
					}
					for(ClickBarDroite click : tableauFenetres) {
						click.close();
						tooltipInitialize();
					}
					if (clickDroitFenetre != null) {
						Double x = clickDroitFenetre.getX(), y = clickDroitFenetre.getY(); 

						Double mouseX = event.getScreenX() , mouseY = event.getScreenY();		
						if( (mouseX < x - 10)  ||  (mouseX > x+172) || (mouseY < y - 10)  ||  (mouseY > y+174) )
						{//162     164
							clickDroitFenetre.close();
							clickDroitFenetre = null;
						}
					}
						if (clickDroitFilFenetre != null) {
							Double x = clickDroitFilFenetre.getX(), y = clickDroitFilFenetre.getY();
							Double mouseX = event.getScreenX(), mouseY = event.getScreenY();
							if ((mouseX < x) || (mouseX > x + 164) || (mouseY < y) || (mouseY > y + 55)) {
								lineDroit.setStroke(Color.BLACK);
								clickDroitFilFenetre.close();
								clickDroitFilFenetre = null;
							}
						}
						if (clickSouris2 != null) {
							clickSouris2.close();
							clickSouris2 = null;
						}
						if(event.getButton()==MouseButton.SECONDARY  && (clickDroitFenetre == null  ) && (clickDroitFilFenetre == null )) {
							if (! simul) {
								if (event.getScreenX() > 1135) {
									if (event.getScreenY() > 640) {
										clickSouris2 = new ClickSouris2(event.getScreenX()-160, event.getScreenY()-55, workSpace, homeWindow);
									}
									else {
										clickSouris2 = new ClickSouris2(event.getScreenX()-160, event.getScreenY(), workSpace, homeWindow);
									}
								}
								else {
									if (event.getScreenY() > 640) {
										clickSouris2 = new ClickSouris2(event.getScreenX(), event.getScreenY()-55, workSpace, homeWindow);
									}
									else {
										clickSouris2 = new ClickSouris2(event.getScreenX(), event.getScreenY(), workSpace, homeWindow);
									}
									clickSouris2.show();

								}
							}
						}
					}
				}
			});
		}

		private void ajouterGestWorkSpace() {// Methodes pour Ajouter l'interaction avec le drag and drop et les guides
			workSpace.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
				@Override
				public void handle(MouseDragEvent e) {
					if (!workSpace.getChildren().contains(guideX))
						workSpace.getChildren().add(guideX);
					if (!workSpace.getChildren().contains(guideXp))
						workSpace.getChildren().add(guideXp);
					if (!workSpace.getChildren().contains(guideY))
						workSpace.getChildren().add(guideY);
					if (!workSpace.getChildren().contains(guideYp))
						workSpace.getChildren().add(guideYp);

					e.consume();
				}
			});

			workSpace.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
				@Override
				public void handle(MouseDragEvent e) {
					// TODO: add new instance of dragItem to rightPane
					workSpace.getChildren().remove(guideX);
					workSpace.getChildren().remove(guideXp);
					workSpace.getChildren().remove(guideY);
					workSpace.getChildren().remove(guideYp);
					// sari
					// workSpace.getChildren().remove(guideFilX);
					// workSpace.getChildren().remove(guideFilY);

					e.consume();
				}
			});

			scrollPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (!simul) {
						if (event.isControlDown() && (event.getCode() == KeyCode.Z)) {
							undoChanges(workSpace);
						}
						if (event.isControlDown() && (event.getCode() == KeyCode.X)) {
							System.out.println("the cut operation ");
							copierActive = true;
							copyActive = true;
							ImageView sauv = elementSeclecionner;
							workSpace.getChildren().remove(elementSeclecionner);
							Composant composantCouper = Circuit.getCompFromImage(elementSeclecionner);
							composantCopy = composantCouper;
							ArrayList<Polyline> lineListe = Circuit.supprimerComp(composantCouper);
							for (Polyline line : lineListe)
								workSpace.getChildren().remove(line);
							elementSeclecionner = sauv;

						}

						if (event.isControlDown() && (event.getCode() == KeyCode.C)) {
							if (elementSeclecionner != null) {
								System.out.println("control + c are pressed !");
								System.out.println("l'element selectionner est : " + elementSeclecionner.getId());
								setCopierActive(true);

							}
							if (event.isControlDown() && (event.getCode() == KeyCode.V)) {
								CopyUses();
							}
						}
					}
				};
			}
					);


			workSpace.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
				if (copyMouse && elementSeclecionner != null) {
					copyMouse = false;
					ctrlX = event.getX();
					ctrlY = event.getY();
					CopyUses();
				}
			});
		}


		public void rightbar(ImageView icon, ClickBarDroite cc, ClickBarDroite tableauDeFenetres[]) {
			icon.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					for (ClickBarDroite click : tableauDeFenetres) {
						if (cc.equals(click)) {
							if (cc.isShowing())
								cc.close();
							else
								cc.show();
						} else
							click.close();
					}
				}
			}
					);
		}



		public void tooltipInitialize() {// utiliser pour les effets hover ou nous avons un texte en mettant la souris
			// sur les elements

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
				@Override
				public void handle(MouseEvent e) {
					elementAdrager.setCursor(Cursor.HAND);
					elemanrsMapFillMap.get(elementAdrager).setStyle("-fx-background-color:#000000;-fx-background-radius:10;-fx-effect:dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0.5, 2.0, 2.0)");
					transitionDesComposants(elementAdrager);
				}

			});
			elementAdrager.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					elemanrsMapFillMap.get(elementAdrager).setStyle("-fx-background-color:#303337;-fx-background-radius:10;-fx-effect:dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0.5, 2.0, 2.0)");
					elementAdrager.setCursor(Cursor.DEFAULT);
				}
			});

			elementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					if (! simul) {

						ImageView dragImageView = new ImageView();
						dragImageView.setMouseTransparent(true);
						// dragImageView.setViewOrder(1); //l'ordre
						dragImageView.toFront();
						elementAdrager.setMouseTransparent(true);
						elementAdrager.setCursor(Cursor.CLOSED_HAND);

						elementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
							@Override
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
							@Override
							public void handle(MouseEvent e) {

								if (! simul) {
									Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
									dragImageView.relocate(
											(int)(localPoint.getX() - dragImageView.getBoundsInLocal().getWidth() / 2),
											(int)(localPoint.getY() - dragImageView.getBoundsInLocal().getHeight() / 2 )
											);

									String xString=String.valueOf(dragImageView.getLayoutX());
									String yString=String.valueOf(dragImageView.getLayoutY());
									if((dragImageView.getLayoutX()>0 && dragImageView.getLayoutX()<workSpace.getMaxWidth() )&&(dragImageView.getLayoutY()>0))
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
							@Override
							public void handle(MouseEvent e) {
								if (!simul) {
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
									if( dragImageView.getLayoutX() <= 0 ||dragImageView.getLayoutY() <= 0|| (e.getSceneX() +( dragImageView.getBoundsInLocal().getWidth()) / 2) > 1310 || e.getSceneY() + (dragImageView.getBoundsInLocal().getHeight() / 2)>700 || intersectionComposant(dragImageView)||( dragImageView.getId().equals("clock") && ( horloged)))
									{
										workSpace.getChildren().remove(dragImageView);
										Circuit.removeCompFromImage(dragImageView);
									}
									else
									{

										if( dragImageView.getId().equals("clock")  ) {
											horloged =true;
											horlogeDeCercuit=dragImageView;

										}
										Composant cmp = Circuit.getCompFromImage(dragImageView);
										ArrayList<Polyline> polyline = cmp.generatePolyline(dragImageView.getLayoutX(), dragImageView.getLayoutY());
										addAllPolylinesToWorkSpace(polyline);
										if(cmp.getClass().getSimpleName().equals("CircuitIntegre")){
											ArrayList<Circle> listeCircles = ((CircuitIntegre)cmp).generateCercles(dragImageView.getLayoutX(), dragImageView.getLayoutY());

											for (Circle circle : listeCircles) {
												workSpace.getChildren().add(circle);
											}
										}
										ajouterLeGestApresCollage(dragImageView);
										Donnes sauveGarde=new Donnes();
										sauveGarde.setTypeDaction(Actions.Creation);
										sauveGarde.setComposantCommeImage(dragImageView);
										sauveGarde.setComposant(cmp);
										sauveGarde.setPosX(dragImageView.getLayoutX());
										sauveGarde.setPosY(dragImageView.getLayoutY());
										undoDeque.addFirst(sauveGarde);

									}
								}
							}
						});

					}
				}
			});

		}

		public Polyline tracerEntrerApresCollage(Polyline line, Coordonnees crdDebut, boolean relocate) { /// Trecer les
			/// lignes
			/// d'entrées
			/// apres le
			/// collage
			int i = 0;
			double x2 = crdDebut.getX();
			double y2 = crdDebut.getY();
			x = line.getPoints().get(line.getPoints().size() - 6);
			y = line.getPoints().get(line.getPoints().size() - 5);
			for (i = 0; i < 4; i++) {
				line.getPoints().remove(line.getPoints().size() - 1);
			}
			int size = line.getPoints().size();
			if (line.getPoints().size() >= 4) {
				if (nbOccPoint(line, line.getPoints().get(size - 2), line.getPoints().get(size - 1)) == 1 && size != 2) {
					if ((Math.abs(line.getPoints().get(size - 2) - x2) < 10)
							&& (Math.abs(line.getPoints().get(size - 1) - y2) < 10)) {
						line.getPoints().remove(size - 2);
						line.getPoints().remove(size - 2);
					}
				}
			} else {
				int sizeArray = Circuit.getListFromPolyline(line).size();
				if (Circuit.getListFromPolyline(line).size() > 1) { // pour ne pas supprimer le premier polyline
					// Polyline line2 =
					// Circuit.getListFromPolyline(line).get(sizeArray-2).getLinePrincipale();
					Polyline line2 = Circuit.getInfoPolylineFromPolyline(line).getLineParent();
					if ((Math.abs(line.getPoints().get(0) - line2.getPoints().get(line2.getPoints().size() - 2)) < 6)
							&& (Math.abs(
									line.getPoints().get(1) - line2.getPoints().get(line2.getPoints().size() - 1)) < 6)) {
						// Si le prochain polyline a les memes coordonnees de debut que celle de la
						// derniere du polyline actuel
						if ((Math.abs(line.getPoints().get(0) - x2) < 6) && (Math.abs(line.getPoints().get(1) - y2) < 6)) {
							// Suppression
							line.getPoints().clear();
							line2.getPoints().remove(line2.getPoints().size() - 1);
							line2.getPoints().remove(line2.getPoints().size() - 1);
							line2.getPoints().remove(line2.getPoints().size() - 1);
							line2.getPoints().remove(line2.getPoints().size() - 1);
							InfoPolyline info = Circuit.getInfoPolylineFromPolyline(line2);
							info.setNbFils(info.getNbFils() - 1);
							info.copierRelierInfo(Circuit.getInfoPolylineFromPolyline(line));
							Circuit.getListFromPolyline(line).remove(sizeArray - 1);
							listEntrees.add(listEntrees.indexOf(line), line2);
							listEntrees.remove(line);
							line = line2;
						}
					}
				}
			}

			if (Math.abs(x2 - x) < 10) {
				if (Math.abs(y2 - y) < 10)
					switching = 0;
				else
					switching = 1;
			} else {
				if (Math.abs(y2 - y) < 10)
					switching = 0;
			}

			if (switching == 0)
				line.getPoints().addAll(x2, y, x2, y2);
			else
				line.getPoints().addAll(x, y2, x2, y2);
			return line;
		}

		public Polyline tracerSortieApresCollage(Polyline line, Coordonnees crdDebut, boolean relocate) {// Trecer les
			// lignes de
			// sorties apres
			// le collage
			int i = 0;
			double x2 = crdDebut.getX();
			double y2 = crdDebut.getY();
			if (!relocate) {
				x = line.getPoints().get(4);
				y = line.getPoints().get(5);

				for (i = 0; i < 4; i++) {
					line.getPoints().remove((0));
				}
				if (line.getPoints().size() > 6) {
					if (nbOccPoint(line, line.getPoints().get(0), line.getPoints().get(1)) == 1) {
						if ((Math.abs(line.getPoints().get(0) - x2) < 10)
								&& (Math.abs(line.getPoints().get(1) - y2) < 10)) {
							line.getPoints().remove(0);
							line.getPoints().remove(0);
						}
					}
				} else {
					if (Circuit.getListFromPolyline(line).size() > 1) { // pour ne pas supprimer le premier polyline
						Polyline line2 = Circuit.getListFromPolyline(line).get(1).getLinePrincipale();
						if((Math.abs(line.getPoints().get(line.getPoints().size()-2)-line2.getPoints().get(0))<10) && (Math.abs(line.getPoints().get(line.getPoints().size()-1)-line2.getPoints().get(1))<10)) {
							if((Math.abs(line.getPoints().get(0)-x2)<5) && (Math.abs(line.getPoints().get(1)-y2)<5)) {
								//Suppression
								workSpace.getChildren().remove(line);

								line.getPoints().clear();
								Circuit.getListFromPolyline(line).remove(0);
								listSorties.add(listSorties.indexOf(line), line2);
								listSorties.remove(line);
								Circuit.getInfoPolylineFromPolyline(line2).setLineParent(null);
								line = line2;
							}
						}
					}
				}
			} else {
				if (!Circuit.getInfoPolylineFromPolyline(line).isRelier()) {
					Circuit.getFilFromPolyline(line).getSource().resetPolyline(line, x2, y2);

				}else {
					x = line.getPoints().get(4);
					y = line.getPoints().get(5);
					for (i = 0; i < 4; i++) {
						line.getPoints().remove((0));
					}
					if ((Math.abs(line.getPoints().get(0) - x2) < 10) && (Math.abs(line.getPoints().get(1) - y2) < 10)
							&& line.getPoints().size() > 2) {
						line.getPoints().remove(0);
						line.getPoints().remove(0);
					}
					relocate = false;
				}
			}
			if(!relocate) {
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

			}
			return line;
		}

		private void ajouterLeGestApresCollage( ImageView eleementAdrager) {//Methode d'ajout de la fonctionallité de drag and drop apres que le composant
			//est ajoute dans le workSpace

			eleementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
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
				@Override
				public void handle(MouseEvent e) {
					if (! simul) {


						posX = eleementAdrager.getLayoutX();
						posY = eleementAdrager.getLayoutY();

						if (e.getButton() != MouseButton.SECONDARY) {
							if (clickDroitFenetre != null) {
								clickDroitFenetre.close();
							}
							dragItem = eleementAdrager;
							eleementAdrager.setMouseTransparent(true);
							eleementAdrager.setMouseTransparent(true);
							eleementAdrager.setCursor(Cursor.CLOSED_HAND);
							elementSeclecionner = eleementAdrager;
							eleementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent e) {

									SnapshotParameters snapParams = new SnapshotParameters();
									snapParams.setFill(Color.TRANSPARENT);
									eleementAdrager.setImage(eleementAdrager.snapshot(snapParams, null));
									eleementAdrager.startFullDrag();
									e.consume();
								}

							});
						}else{

							double clicDroitX,clicDroitY;
							Composant composant=Circuit.getCompFromImage(eleementAdrager);
							clicDroitX = e.getScreenX();
							clicDroitY = e.getScreenY();

							if (clickDroitFenetre != null)
								clickDroitFenetre.close();
							if (clicDroitX > 1100) {
								if (clicDroitY > 500) {
									clickDroitFenetre = new ClickDroit(composant,clicDroitX-150,clicDroitY-150,workSpace, homeWindow);
								}
								else {
									clickDroitFenetre = new ClickDroit(composant,clicDroitX-150,clicDroitY,workSpace, homeWindow);
								}
							}
							else {
								if (clicDroitY > 500) {
									clickDroitFenetre = new ClickDroit(composant,clicDroitX,clicDroitY-150,workSpace, homeWindow);
								}
								else {
									clickDroitFenetre = new ClickDroit(composant,clicDroitX,clicDroitY,workSpace, homeWindow);
								}
							}

							clickDroitFenetre.show();

							if (clickSouris2 != null) {
								clickSouris2.close();
							}
							elementAmodifier = eleementAdrager;
							elementSeclecionner = eleementAdrager;
						}

						refrechLists(eleementAdrager); /// kayna lteht
						//hna tekmeel
						eleementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() { /// si le composant est dragé .
							@Override
							public void handle(MouseEvent e) {
								if (!simul) {
									if (e.getButton() == MouseButton.PRIMARY) {
										//ajout des points
										addPoints();/// ajouter les points
										Composant ci=Circuit.getCompFromImage(eleementAdrager);
										if (ci.getClass().getSimpleName().equals("CircuitIntegre")) {
											CircuitIntegre circuitIntegre = ((CircuitIntegre)ci);
											circuitIntegre.resetCirclesPosition(eleementAdrager.getLayoutX(), eleementAdrager.getLayoutY());																		
											//										workSpace.getChildren().removeAll(((CircuitIntegre)ci).getListeCercles());
											//										workSpace.getChildren().addAll(((CircuitIntegre)ci).generateCercles(eleementAdrager.getLayoutX(), eleementAdrager.getLayoutY()));
										}
										Point2D localPoint = workSpace
												.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
										eleementAdrager.relocate(
												(int) (localPoint.getX()
														- eleementAdrager.getBoundsInLocal().getWidth() / 2),
												(int) (localPoint.getY()
														- eleementAdrager.getBoundsInLocal().getHeight() / 2));
										double x = eleementAdrager.getLayoutX()
												+ eleementAdrager.getBoundsInLocal().getWidth() - 2;
										double y = eleementAdrager.getLayoutY()
												+ eleementAdrager.getBoundsInLocal().getHeight() / 2 - 1;

										String xString = String.valueOf(eleementAdrager.getLayoutX());
										String yString = String.valueOf(eleementAdrager.getLayoutY());
										if ((eleementAdrager.getLayoutX() > 0
												&& eleementAdrager.getLayoutX() < workSpace.getMaxWidth())
												&& (eleementAdrager.getLayoutY() > 0)) {
											guideX.setLayoutX(eleementAdrager.getLayoutX());
											guideY.setLayoutY(eleementAdrager.getLayoutY());
											guideXp.setLayoutX(eleementAdrager.getLayoutX()
													+ eleementAdrager.getBoundsInLocal().getWidth() + 1);
											guideYp.setLayoutY(eleementAdrager.getLayoutY()
													+ eleementAdrager.getBoundsInLocal().getHeight() + 1);
											afficheurX.setText("X : " + xString);
											afficheurY.setText("Y : " + yString);

										}

										else {
											guideX.setLayoutX(0);
											guideY.setLayoutY(0);
											guideXp.setLayoutX(0);
											guideYp.setLayoutY(0);
											afficheurX.setText("X : 0");
											afficheurY.setText("Y : 0");

										}
										if (e.getSceneX() > 1275) {
											scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
											scrollPane.setHvalue(scrollPane.getHvalue() + 0.01);
										}
										if (e.getSceneX() < 210) {
											scrollPane.setHvalue(scrollPane.getHvalue() - 0.01);
										}
										if (e.getSceneY() > 700) {
											scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
											scrollPane.setVvalue(scrollPane.getVvalue() + 0.01);
										}
										if (e.getSceneY() < 0) {
											scrollPane.setVvalue(scrollPane.getVvalue() - 0.01);
										}
										e.consume();
										updatePolyline(eleementAdrager);

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

									updatePolyline(eleementAdrager);

								}
							}


						});
						eleementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								if (! simul) {
									dragItem = null;
									if(posX != eleementAdrager.getLayoutX() || posY != eleementAdrager.getLayoutY())
									{
										Donnes sauveGarde=new Donnes();
										sauveGarde.setTypeDaction(Actions.Mouvement);
										undoDeque.remove(sauveGarde);
										sauveGarde.setComposantCommeImage(eleementAdrager);
										sauveGarde.setPosX(posX);
										sauveGarde.setPosY(posY);
										undoDeque.addFirst(sauveGarde);
									}
									eleementAdrager.setMouseTransparent(false);
									eleementAdrager.setCursor(Cursor.DEFAULT);
									if( eleementAdrager.getLayoutX() <= 0 ||eleementAdrager.getLayoutY() <= 0|| (e.getSceneX() +( eleementAdrager.getBoundsInLocal().getWidth()) / 2) > 1300 || e.getSceneY() + (eleementAdrager.getBoundsInLocal().getHeight() / 2)>700 || intersectionComposant(eleementAdrager))
									{
										eleementAdrager.setLayoutX(posX);
										eleementAdrager.setLayoutY(posY);
										updatePolyline(eleementAdrager);
										afficheurX.setText(String.valueOf(posX));
										afficheurY.setText(String.valueOf(posY));
										workSpace.getChildren().remove(guideX);
										workSpace.getChildren().remove(guideXp);
										workSpace.getChildren().remove(guideY);
										workSpace.getChildren().remove(guideYp);
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
						if(ListTextPin == null) {
							if (eleementAdrager.getId().equals("pin")) {
								Pin pin = (Pin) Circuit.getCompFromImage(eleementAdrager);
								if (pin.getInput()) {
									eleementAdrager.setOnMouseClicked(new EventHandler<MouseEvent>() {
										@Override
										public void handle(MouseEvent e) {
											// TODO Auto-generated method stub
											if(simul && ListTextPin == null) {
												if (pin.getEtat() == EtatLogique.ONE) {
													pin.setEtat(EtatLogique.ZERO);
												}
												else {
													pin.setEtat(EtatLogique.ONE);
												}
												pin.evaluer();
												eleementAdrager.setCursor(Cursor.HAND);
											}
										}
									});
								}
							}
						}else {
							Composant compos = Circuit.getCompFromImage(eleementAdrager);
							if(compos.getClass().getSimpleName().equals("Pin")) {
								if( ((Pin)compos).isInput()) {
									if(!ListTextPin.contains(compos)){
										Text number = new Text();
										number.setLayoutX(eleementAdrager.getLayoutX()+3);
										number.setLayoutY(eleementAdrager.getLayoutY()+16);
										String id = Integer.toString(ListTextPin.size()+1);
										number.setText(id);
										number.setId(id);
										number.setFont(Font.font("Calisto MT",FontWeight.BOLD,18));
										workSpace.getChildren().add(number);
										ListTextPin.add((Pin)compos);
										ListText.add(number);
									}else {
										Alert alert = new Alert(AlertType.CONFIRMATION);
										alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
										alert.setTitle("Confirmation");
										alert.setHeaderText("Refaire l'ordre ");
										alert.setContentText("Cette entré est deja selectionnée, Voulez vous reordonner les entrées ?");
										Optional<ButtonType> result = alert.showAndWait();
										if(result.get() == ButtonType.OK) {

											for (Text num : ListText) {
												workSpace.getChildren().remove(num);
											}
											//	        							for (Pin pin : ListTextPin) {
											//	        								workSpace.getChildren().remove(pin); /// a voir avec sari
											//	        							}
											ListText.clear();
											ListTextPin.clear();
										}
									}
								}
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
					for (int j = i + 4; j < i + 24; j += 4) {
						Line ppLine = new Line();
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
						for (int j = i + 4; j < i + 24; j += 4) {
							Line ppLine = new Line();
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
					for (int j = i + 4; j < i + 24; j += 4) {
						Line ppLine = new Line();
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
					for (int j = i + 4; j < i + 24; j += 4) {
						Line ppLine = new Line();
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

		private void rotationDelogo(ImageView image,int nombreDeboucle,int vitesse, boolean returne) {// Methode de rotation de logo

			RotateTransition rotate = new RotateTransition();
			rotate.setAxis(Rotate.Z_AXIS);
			if(returne)
				rotate.setByAngle(360);
			else
				rotate.setByAngle(1440);
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
			case "hex": {
				comp = new AfficheurSegment("");
			}
			break;
			case "pin": {
				comp = new Pin(true, "");
			}
			break;
			case "clock": {
				comp = new Horloge("", 1000);
			}
			break;
			case "vcc": {
				comp = new SourceConstante(EtatLogique.ONE, "");
			}
			break;
			case "mass": {
				comp = new SourceConstante(EtatLogique.ZERO, "");
			}
			break;
			case "and": {
				comp = new And(2, "");
			}
			break;
			case "or": {
				comp = new Or(2, "");
			}
			break;
			case "xor": {
				comp = new Xor(2, "");
			}
			break;
			case "nand": {
				comp = new Nand(2, "");
			}
			break;
			case "nor": {
				comp = new Nor(2, "");
			}
			break;
			case "not": {
				comp = new Not("");
			}
			break;
			case "jk": {
				comp = new JK("", Front.Front_Montant);
			}
			break;
			case "d": {
				comp = new D("", Front.Front_Montant);
			}
			break;
			case "rs": {
				comp = new RST("", Front.Front_Montant);
			}
			break;
			case "t": {
				comp = new T("", Front.Front_Montant);
			}
			break;
			case "cpt": {
				comp = new Compteur(2, "", Front.Front_Montant);
			}
			break;
			case "registreDecalge": {
				comp = new RegistreDecalage(2, "", true, Front.Front_Montant);
			}
			break;
			case "mux": {
				comp = new Multiplexeur(2, "");
			}
			break;
			case "dmux": {
				comp = new Demultiplexeur(1, "");
			}
			break;
			case "dec": {
				comp = new Decodeur(1, "");
			}
			break;
			case "addcomplet": {
				comp = new AdditionneurN_Bites(1, "");
			}
			break;
			case "demiAdd": {
				comp = new DemiAdditionneur(1, "");
			}
			break;
			default: {
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
			while (iterator.hasNext() && !trouv) {
				img = iterator.next();
				if (img != image && intersectionCoordone(img, image)) {
					trouv = true;
				}
			}
			return trouv;
		}
		private boolean intersectionCoordone(ImageView origin, ImageView copie) {
			boolean verifX = false;
			boolean verifY = false;
			Double x1 = origin.getLayoutX();
			Double y1 = origin.getLayoutY();
			Double x2 = copie.getLayoutX();
			Double y2 = copie.getLayoutY();
			if (origin.getFitWidth() < copie.getFitWidth()) {
				if ((x1 >= x2 && x1 <= x2 + copie.getFitWidth())
						|| (x1 + origin.getFitWidth() >= x2 && x1 + origin.getFitWidth() <= x2 + copie.getFitWidth())) {
					verifX = true;
				}
			} else {
				if ((x2 >= x1 && x2 <= x1 + origin.getFitWidth())
						|| (x2 + copie.getFitWidth() >= x1 && x2 + copie.getFitWidth() <= x1 + origin.getFitWidth())) {
					verifX = true;
				}
			}
			if (verifX) {
				if (origin.getFitHeight() < copie.getFitHeight()) {
					if ((y1 >= y2 && y1 <= y2 + copie.getFitHeight()) || (y1 + origin.getFitHeight() >= y2
							&& y1 + origin.getFitHeight() <= y2 + copie.getFitHeight())) {
						verifY = true;
					}
				} else {
					if ((y2 >= y1 && y2 <= y1 + origin.getFitHeight()) || (y2 + copie.getFitHeight() >= y1
							&& y2 + copie.getFitHeight() <= y1 + origin.getFitHeight())) {
						verifY = true;
					}
				}
			}
			if (verifX && verifY) {
				return true;
			}
			return false;
		}

		public int nbOccPoint(Polyline line, double x, double y) {
			ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
			int i = 0, nb = 0;
			while (i < list.size()) {
				if ((list.get(i) == x) && (list.get(i + 1) == y)) {
					nb = nb + 1;
				}
				i = i + 2;
			}
			return nb;
		}
		///////////////////////////////////////////////////////////-> Bar Droite <-/////////////////////////////////////////////////////////////


	    @FXML
	    void supprimerTout(ActionEvent event) {   	
	    	Stage stage = (Stage) supprimerTout.getScene().getWindow(); 	
	    	stage.close();
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		
			if (! simul) {			
				if(!(Circuit.getCompUtilises().isEmpty())) {				
				alert.setContentText("Voullez vous vraimment supprimer toute la zone du circuit ");
				Optional<ButtonType> result = alert.showAndWait();	    		
						if(result.get() == ButtonType.OK){
						Circuit.clearCircuit();
						workSpace.getChildren().clear();
						horloged = false;
						tracerLesregles(workSpace);	
						}
				}
				else {
					alert.setAlertType(AlertType.INFORMATION);
					alert.setContentText("le circuit est deja vide !");
					alert.show();
		    	}
			}
	    else {
	    	alert.setAlertType(AlertType.INFORMATION);
			alert.setContentText("veuillez desactiver l'etat de simulation");
			alert.show();
	    	
	    }
			
}
	    

	    @FXML
	    public void copier(ActionEvent event) {
	    	Stage s = (Stage) copier.getScene().getWindow();
    		s.close();
	    	if(elementSeclecionner != null) {
	    		setCopierActive(true);
	    		
	    	}
	    }
	    public void coller(ActionEvent event) {
	    		cc = true;
	    		CopyUses();
	   	    	Stage stage = (Stage) coller.getScene().getWindow();
	   	    	stage.close();		
	    }
	    
	    
	    
	    public void CopyUses() {
	    	if (elementSeclecionner != null) {
	    		if(!pastButton) {
	    			ImageView dragImageView = new ImageView();
	    			dragImageView.setLayoutX(ctrlX);
	    			dragImageView.setLayoutY(ctrlY);
	    			dragImageView.setId(elementSeclecionner.getId());
	    			instanceComposant(dragImageView);		
	    			if(!copyActive)
	    				composantCopy = Circuit.getCompFromImage(elementSeclecionner);
	    			Composant cmp2 = Circuit.getCompFromImage(dragImageView);
	    			cmp2.setDirection(composantCopy.getDirection());
	    			cmp2.setIcon(composantCopy.getIcon());
	    			cmp2.setLesCoordonnees(composantCopy.getLesCoordonnees());
	    			cmp2.setNom(composantCopy.getNom());
	    			cmp2.setNombreEntree(composantCopy.getNombreEntree());
	    			cmp2.setNombreSortieAndUpdateFil(composantCopy.getNombreSortie());
	    			if(cmp2.getClass().getSimpleName().equals("Multiplexeur")){
	    				((Multiplexeur)cmp2).setNbCommande(((Multiplexeur)composantCopy).getNbCommande());
	    				cmp2.getLesCoordonnees().setNbCordCommandes(composantCopy.getLesCoordonnees().getNbCordCommandes());	
	    			}
	    			else
	    				if(cmp2.getClass().getSimpleName().equals("Demultiplexeur")){
	    					((Demultiplexeur)cmp2).setNbCommande(((Demultiplexeur)composantCopy).getNbCommande());
	    					cmp2.getLesCoordonnees().setNbCordCommandes(composantCopy.getLesCoordonnees().getNbCordCommandes());	
	    				}

	    			cmp2.setCord();
	    			cmp2.getLesCoordonnees().setNbCordEntree(composantCopy.getNombreEntree());
	    			cmp2.getLesCoordonnees().setNbCordSorties(composantCopy.getNombreSortie());
	    			dragImageView.setImage(elementSeclecionner.getImage());
	    			dragImageView.setFitHeight(elementSeclecionner.getImage().getHeight());
	    			dragImageView.setFitWidth(elementSeclecionner.getImage().getWidth());		
	    			workSpace.getChildren().add(dragImageView);
	    			ArrayList<Polyline> polyline = Circuit.getCompFromImage(dragImageView).generatePolyline(dragImageView.getLayoutX(), dragImageView.getLayoutY());
	    			addAllPolylinesToWorkSpace(polyline);
	    			ajouterLeGestApresCollage(dragImageView);
	    			elementSeclecionner = dragImageView;
	    		}
	    	}

	    }

	    /*-----------------------------------------------------*/
	    
	    @FXML
	    void annuler(ActionEvent event) {
	    	//System.out.println("le boutton annuler est clique");
	    	((Stage)annuler.getScene().getWindow()).close();
	    	undoChanges(workSpace);

	    }
	    

		/*-----------------------------------------------------*/

		

		@FXML
		void supprimer(ActionEvent event) { /// pour appliquer une suppression sur
			    	((Stage)supprimer.getScene().getWindow()).close();

			if (elementSeclecionner != null) {
				cmp = Circuit.getCompFromImage(elementSeclecionner);
				elementAsuprimer = elementSeclecionner;
				sauveGarderSupression();
				if(elementAsuprimer.getId().equals("clock"))
				{
					HomeController.horloged =false;
					HomeController.horlogeDeCercuit =null;
				}
				workSpace.getChildren().remove(elementSeclecionner);
				removeAllPolylinesFromWorkSpace(Circuit.supprimerComp(cmp));
				elementAsuprimer=null;
			}
		}



		@FXML
		void couper(ActionEvent event) {
			copierActive = true;
			copyActive = true;
			ImageView sauv = elementSeclecionner;
			workSpace.getChildren().remove(elementSeclecionner);
			Composant composantCouper = Circuit.getCompFromImage(elementSeclecionner);
			composantCopy = composantCouper;
			ArrayList<Polyline> lineListe = Circuit.supprimerComp(composantCouper);
			for (Polyline line : lineListe)
				workSpace.getChildren().remove(line);

			elementSeclecionner = sauv;

			Stage s = (Stage) couper.getScene().getWindow();
			s.close();

		}


		/*----------------------fichier------------------------------------*/

		@FXML
		void fermer(ActionEvent event) {

			Stage stage = (Stage) fermer.getScene().getWindow();
			Alert alertQ = new Alert(AlertType.CONFIRMATION);
			alertQ.setContentText("Voullez vous vraimment quitter ! ");
			alertQ.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

			Optional<ButtonType> resultQ = alertQ.showAndWait();
			if (resultQ.get() == ButtonType.OK) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Voullez vous sauvgarder ce circuit");
				alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					if (fichierCourant == null) {
						final FileChooser fileChooser = new FileChooser();
						fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
						fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
						File f = fileChooser.showSaveDialog(homeWindow);
						if (f != null) {

							Sauvegarde sauvegarde = new Sauvegarde();
							sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
							Alert a = new Alert(AlertType.INFORMATION);
							a.setContentText("le circuit est bien sauvgarde");
							a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

							a.showAndWait();
						}
					} else {
						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("le circuit est bien sauvgarde");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

						a.showAndWait();
					}
				}
				Stage s = (Stage) stage.getOwner();
				s.close();
			}
		}

		@FXML
		void nouveau(ActionEvent event) {
			Stage stage = (Stage) nouveau.getScene().getWindow();
			stage.close();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Voullez vous sauvgarder ce circuit");
			alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (fichierCourant == null) {
					final FileChooser fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
					File f = fileChooser.showSaveDialog(homeWindow);
					if (f != null) {
						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("le circuit est bien sauvgarde");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.showAndWait();
					}
				} else {
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
					Alert a = new Alert(AlertType.INFORMATION);
					a.setContentText("le circuit est bien sauvgarde");
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.showAndWait();
				}
			}
			Circuit.clearCircuit();
			fichierCourant = null;
			workSpace.getChildren().clear();
			horloged = false;
			tracerLesregles(workSpace);
	    }

	 
	 
	    
	  

		@FXML
		void ouvrir(ActionEvent event) {
			/*
			 * final DirectoryChooser directoryChooser = new DirectoryChooser(); final File
			 * selectedDirectory = directoryChooser.showDialog(homeWindow);
			 */
			if (!Circuit.getCompUtilises().isEmpty()) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Voullez vous sauvgarder ce circuit");
				alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					if (fichierCourant == null) {
						final FileChooser fileChooser = new FileChooser();
						File f = fileChooser.showSaveDialog(homeWindow);
						if (f != null) {
							Sauvegarde sauvegarde = new Sauvegarde();
							sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
							Alert a = new Alert(AlertType.INFORMATION);
							a.setContentText("le circuit est bien sauvgarde");
							a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
							a.showAndWait();
						}
					} else {
						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("le circuit est bien sauvgarde");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.showAndWait();

					}
				}
			}
			final FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(
					new File(System.getProperty("user.home"))
					);                 
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("SIM", "*.sim")
					);
			File f = fileChooser.showOpenDialog(homeWindow);
			if (f != null) {
				Circuit.clearCircuit();
				workSpace.getChildren().clear();
				horloged = false;
				tracerLesregles(workSpace);
				Sauvegarde.loadCiruit(f.getAbsolutePath());
				ajouterElements();
				fichierCourant = f;
			}
		}

		@FXML
		void save(ActionEvent event) {
			if (Circuit.getCompUtilises().isEmpty()) {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("le circuit est vide y a rien a sauvgarder");
				a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				a.showAndWait();
			} else {
				if (fichierCourant == null) {
					final FileChooser fileChooser = new FileChooser();
					File f = fileChooser.showSaveDialog(homeWindow);
					if (f != null) {
						fichierCourant = f;
						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("le circuit est bien sauvgarde");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.showAndWait();
					}
				} 
				else {
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(fichierCourant.getAbsolutePath() + ".sim");
					Alert a = new Alert(AlertType.INFORMATION);
					a.setContentText("le circuit est bien sauvgarde");
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.showAndWait();
				}
			}
		}

		@FXML
		void saveAs(ActionEvent event) { /// la fonctionnalité de sauvegarder as
			final FileChooser fileChooser = new FileChooser();
			File f = fileChooser.showSaveDialog(homeWindow);
			if (f != null) {
				System.out.println("the name of the file is : " + f.getAbsolutePath());
				Sauvegarde sauvegarde = new Sauvegarde();
				sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
				fichierCourant = f;
			}
		}

		@FXML
		void importer(ActionEvent event) { /// la fonctionalité importer circuit integré
			final FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(
					new File(System.getProperty("user.home"))
					);
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("INT", "*.int")
					);

			File f = fileChooser.showOpenDialog(homeWindow);
			if (f != null) {
				FileInputStream fichier ;
				ObjectInputStream oo = null;
				CircuitIntegre circuitIntegre;
				try {
					fichier = new FileInputStream(f.getAbsolutePath());
					oo = new ObjectInputStream(fichier);
					circuitIntegre = (CircuitIntegre) oo.readObject();
					ImageView imageView = new ImageView(new Image(circuitIntegre.generatePath()));
					imageView.setLayoutX(10);
					imageView.setLayoutY(10);
					imageView.setFitHeight(imageView.getImage().getHeight());
					imageView.setFitWidth(imageView.getImage().getWidth());
					imageView.setId("CircuitIntegre");
					Circuit.ajouterComposant(circuitIntegre, imageView);
					workSpace.getChildren().add(imageView);
					ajouterLeGestApresCollage(imageView);
					addAllPolylinesToWorkSpace(circuitIntegre.generatePolyline(imageView.getLayoutX(), imageView.getLayoutY()));
					workSpace.getChildren().addAll(circuitIntegre.generateCercles(imageView.getLayoutX(), imageView.getLayoutY()));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				finally {
					try {
						oo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}


		@FXML
		void encapsulerEtSauvgarder(ActionEvent event) {
			final FileChooser fileChooser = new FileChooser();

			File f = fileChooser.showSaveDialog(homeWindow);
			if (f != null) {
				FileOutputStream fichier ;
				ObjectOutputStream oo = null;
				CircuitIntegre ci = new CircuitIntegre(Circuit.getEntreesCircuit().size(),Circuit.getSortiesCircuit().size(), f.getAbsolutePath());
				Circuit.tableVerite(Circuit.getEntreesCircuit());
				ci.setTableVerite(Circuit.getTableVerite());
				Circuit.defaultCompValue();
				try {
					fichier = new FileOutputStream(f.getAbsolutePath()+".int");
					oo = new ObjectOutputStream(fichier);
					oo.writeObject(ci);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						oo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}


		/*---------------------------affichage--------------------------------*/

		@FXML

		void chronogramme(ActionEvent event) { /// charger la fenetre du chronogramme
			if (simul && !Circuit.getListeEtages().isEmpty() && horloged) {
				try {
					Stage s = (Stage) chronogramme.getScene().getWindow();
					s.close();
					ChronogrammeController.setHorlogeDecHRONO((Horloge) Circuit.getCompFromImage(horlogeDeCercuit));
					int i = 0;
					while (i < 10 && i < Circuit.getListeEtages().size()) {
						if (!Circuit.getListeEtages().get(i).getClass().getSimpleName().equals("Compteur"))
							ChronogrammeController.composantDechrono.add(Circuit.getListeEtages().get(i));
						i++;
					}
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Chronogramme.fxml"));
					Parent root = fxmlLoader.load();
					Stage stage = new Stage();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.UNDECORATED);
					scene.setFill(Color.TRANSPARENT);
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				Alert a = new Alert(AlertType.ERROR);
				a.setHeaderText("Chronogramme erreur");
				a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				a.setTitle("Chronogramme");
				a.setContentText(! simul ? "Veulliez Simuler le Circuit D'abord": Circuit.getListeEtages().isEmpty() ?"Le Circuit ne contient Aucun Element Sequentiel ! "
						+ "Ajouter des Elements pour visualiser Le Chronogramme" : "Le Plan de travaille ne contient aucune horloge"
						+ " ajouter une Horloge au Plan pour visualiser le Chronogramme");
				transitionDesComposants(clock);
				a.showAndWait();

			}

		}

		@FXML
		void tableDeVerite(ActionEvent event) { /// charger la fenetre du table de verité
			Stage stage1 = (Stage) tableVerite.getScene().getWindow();
			stage1.close();
			if (simul) {
				if(Circuit.getEntreesCircuit().size() != 0) {
					if(ListTextPin == null) {
						//	    			simul = true;
						ListTextPin = new ArrayList<Pin>();
						ListText = new ArrayList<Text>();
						try {
							Stage s = (Stage) tableVerite.getScene().getWindow();
							s.close();
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Reminder.fxml"));
							Parent root = fxmlLoader.load();
							Stage stage = new Stage();
							Scene scene = new Scene(root);
							stage.setScene(scene);
							//stage.setTitle("la table de verité");
							stage.setTitle("Remarque");
							stage.setResizable(false);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.show();
						} catch(Exception e) {
							e.printStackTrace();
						}
						tableVerite.setText("  Générer la table");
						tableVerite.setAlignment(Pos.BASELINE_LEFT);
					}else { //Generer la table et aller vers l'etat normal
						//Generer La table de verité
						if(ListTextPin.size() != 0) {
							Circuit.tableVerite(ListTextPin);
							Circuit.defaultCompValue(); //Tous noir
							//Supprimer les numeros
							for (Text num : ListText) {
								workSpace.getChildren().remove(num);
							}

							simul = false;		//Mode normal
							//mode normal (opacité 1)
							for (Entry<Composant, ImageView> entry : Circuit.getCompUtilises().entrySet()) {
								entry.getValue().setOpacity(1);
							}
							for (Entry<Fil, ArrayList<InfoPolyline>> entry : Circuit.getfilUtilises().entrySet()) {

								for (InfoPolyline info : entry.getValue()) {
									info.getLinePrincipale().setOpacity(1);
								}
							}
							//La fenetre de la table de verité
							try {
								Stage s = (Stage) tableVerite.getScene().getWindow();
								s.close();
								FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/TableDeVerite.fxml"));
								Parent root = fxmlLoader.load();
								TableDeVeriteController c = fxmlLoader.getController();
								ListTextPin.clear();
								ListText.clear();
								ListText = null;
								ListTextPin = null; //pas de liste
								Stage stage = new Stage();
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.setTitle("la table de verité");
								//stage.setTitle("Remarque");
								stage.setResizable(false);
								stage.initModality(Modality.APPLICATION_MODAL);
								stage.show();
							} catch(Exception e) {
								e.printStackTrace();
							}
							//Boutton Table de verité (Mode normal)
							tableVerite.setText("  Table de vérité");
							tableVerite.setAlignment(Pos.BASELINE_LEFT);

						}else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Liste d'entrées vide");
							//alert.getDialogPane().setStyle("-fx-background-color : #000000");
							alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
							//alert.getDialogPane().getExpandableContent().setStyle("-fx-background-color : #000000");
							alert.setHeaderText("Aucune entrée selectionnée  ");
							alert.setContentText("Il faut que vous seléctionner au moins une entrée pour générer la table de verité !");
							alert.showAndWait();
						}
					}
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					alert.setTitle("Pas d'entrées dans le circuit");
					alert.setHeaderText("Pas d'entrées dans le circuit");
					alert.setContentText("Il faut exister au moins une entrée (Pin d'entré )dans le circuit pour générer la table de verité !");
					alert.showAndWait();
				}
			}
			else {
				Alert a = new Alert(AlertType.ERROR);
				a.setHeaderText("Table de verité erreur");
				a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				a.setTitle("Table De Verité");
				a.setContentText("Veulliez Simuler le Circuit D'abord");
				a.showAndWait();
			}
		}



		/*------------------------about --------------------------------*/


		@FXML
		void aboutSimulIni(ActionEvent event) {
			try {

				Stage s = (Stage) about.getScene().getWindow();
				s.close();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/About.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("/styleFile/about.css").toExternalForm());
				stage.setScene(scene);
				stage.setTitle("About SimulINI");
				stage.initOwner(s.getOwner());
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




		/*-----------------------------click souris 2----------------------*/


		@FXML
		private Button undoParSouris;

		@FXML
		private Button collerParSouris;

		@FXML
		void annulerParSouris(ActionEvent event) {
			undoChanges(workSpace);
		}

		@FXML
		void collerParSouris(ActionEvent event) {
			copyMouse = true;
			pastButton = false;
			Stage s = (Stage)collerParSouris.getScene().getWindow();
			s.close();
		}
		/*------------------------------------------------------*/

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

		private void undoChanges(AnchorPane workSpace)
		{
			if(! undoDeque.isEmpty())
			{
				Donnes sauveGarde;
				sauveGarde= undoDeque.removeFirst();
				switch(sauveGarde.getTypeDaction())
				{
				case Mouvement :
				{
					refrechLists(sauveGarde.getComposantCommeImage());
					//removePoints();
					// addPoints();
					sauveGarde.getComposantCommeImage().setLayoutX(sauveGarde.getPosX());
					sauveGarde.getComposantCommeImage().setLayoutY(sauveGarde.getPosY());
					updatePolyline(sauveGarde.getComposantCommeImage());

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
				case SuppressionFil :{
					InfoPolyline infoPolyline = sauveGarde.getInfoPolyline();
					Fil filSortieFil =  sauveGarde.getFil();
					supprimerSauvegarde(infoPolyline, sauveGarde.getParent(), filSortieFil);

				}break;
				case CreationFil:
				{
					ClickDroitFilController.setPane(workSpace);
					InfoPolyline infoLine = Circuit.getInfoPolylineFromPolyline(sauveGarde.getParent());
					//if(infoLine != null)
					ClickDroitFilController.supprimer(infoLine);
				}break;
				case SuppressionToutFil :{
					ArrayList<InfoPolyline> arrayList = sauveGarde.getArrayListInfoPoly();
					ArrayList<Polyline> arrayParent= sauveGarde.getListPolyParent();
					Fil filSortieFil =  sauveGarde.getFil();
					int size =  arrayParent.size();
					for (int i = 0; i < size; i++) {
						supprimerSauvegarde(arrayList.get(i+1), arrayParent.get(i), filSortieFil);
					}
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

		public void captureEcran(String path) {
			WritableImage image = workSpace.snapshot(new SnapshotParameters(), null);
			File file = new File(path+".png");
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
		}


		private void updatePolyline(ImageView eleementAdrager) {
			Composant cmp = Circuit.getCompFromImage(eleementAdrager);
			boolean relocate = false;
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
		ArrayList<Polyline> sauv = new ArrayList<Polyline>();

		public void ajouterElements() {
			for (ImageView img : Circuit.getCompUtilises().values()) {
				if (img.getId().equals("clock")) {
					horloged = true;
					horlogeDeCercuit = img;
				}
				else if(img.getId().equals("CircuitIntegre")) {
					ArrayList<Circle> arrayList = ((CircuitIntegre)Circuit.getCompFromImage(img)).generateCercles(img.getLayoutX(), img.getLayoutY());
					for (Circle circle : arrayList) {
						workSpace.getChildren().add(circle);
					}
				}

				workSpace.getChildren().add(img);
				ajouterLeGestApresCollage(img);
			}
			Polyline principale;
			Polyline parent;
			for (ArrayList<InfoPolyline> list : Circuit.getfilUtilises().values()) {
				for (InfoPolyline infoPolyline : list) {
					principale = initialser(0, 0);
					principale.getPoints().clear();
					principale.getPoints().addAll(infoPolyline.getNoeudLinePrincipale());
					Polyline polySauv=containsPolyInSauv(principale);
					if ( polySauv== null) {

						workSpace.getChildren().add(principale);
						ajouterGeste(principale);
						sauv.add(principale);
						infoPolyline.setLinePrincipale(principale);
					}
					else {
						infoPolyline.setLinePrincipale(polySauv);
					}
					if (! infoPolyline.getNoeudLineParent().isEmpty()) {
						parent = initialser(0, 0);
						parent.getPoints().clear();
						parent.getPoints().addAll(infoPolyline.getNoeudLineParent());
						Polyline polySauv2=containsPolyInSauv(parent);
						if ( polySauv2== null) {

							workSpace.getChildren().add(parent);
							ajouterGeste(parent);
							sauv.add(parent);
							infoPolyline.setLineParent(parent);
						}
						else {
							infoPolyline.setLineParent(polySauv2);
						}
					}
					else {
						infoPolyline.setLineParent(null);
					}
				}
			}
		}

		public Polyline containsPolyInSauv(Polyline polyline) {
			for (Polyline polyline2 : sauv) {
				if (equalsPolylines(polyline, polyline2)) {
					return polyline2;
				}
			}
			return null;
		}

		public boolean equalsPolylines(Polyline line1,Polyline line2) {

			int i = 0;
			if (line1 == null || line2 == null) {
				return false;
			}
			if (line1.getPoints().size() != line2.getPoints().size()) {
				return false;
			}
			while (i < line1.getPoints().size()) {
				if (line1.getPoints().get(i) != line2.getPoints().get(i)) {
					return false;
				}
				i++;
			}
			return true;
		}
		public void addPoints() {
			Polyline line;
			int i = 0,size = 0;
			if(insererNoedDebut) {
				for( i = 0; i < listSorties.size();i++){
					line = listSorties.get(i);
					if(Circuit.getListFromPolyline(line).size()>1 || Circuit.getInfoPolylineFromPolyline(line).isRelier()) {
						line.getPoints().add(2,line.getPoints().get(3));
						line.getPoints().add(2,line.getPoints().get(3));
					}
				}
				for(i = 0;i < listEntrees.size();i++) {
					line = listEntrees.get(i);
					size = line.getPoints().size();
					line.getPoints().add(size-3,line.getPoints().get(size - 2));
					line.getPoints().add(size-3,line.getPoints().get(size - 2));
				}
				insererNoedDebut = false;
			}
		}

		public void refrechLists(ImageView eleementAdrager) {
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
					System.out.println("dkhal"+line);
					listEntrees.add(line);
				}
			}
			if(cmp.getLesCoordonnees().getNbCordCommandes() != 0) {
				for(i = 0; i < cmp.getLesCoordonnees().getNbCordCommandes();i++){
					if( ((Combinatoires)cmp).getCommande()[i] != null) {
						crdDebut = cmp.getLesCoordonnees().coordReelesCommande(eleementAdrager, i);
						line = ((Combinatoires)cmp).getCommande()[i].polylineParPoint(crdDebut);
						listEntrees.add(line);
					}
				}
			}
			if(cmp.getLesCoordonnees().getCordHorloge() != null ) {
				if( ((Sequentiels)cmp).getEntreeHorloge() != null) {
					crdDebut = cmp.getLesCoordonnees().coordReelesHorloge(eleementAdrager, i);
					line = ((Sequentiels)cmp).getEntreeHorloge().polylineParPoint(crdDebut);

					listEntrees.add(line);
				}

			}
			if(cmp.getLesCoordonnees().getCordClear() != null ) {
				if(((Sequentiels)cmp).getClear().getSource() != null) {
					crdDebut = cmp.getLesCoordonnees().coordReelesClear(eleementAdrager, i);
					line = ((Sequentiels)cmp).getClear().polylineParPoint(crdDebut);
					listEntrees.add(line);
				}
			}
			if(cmp.getLesCoordonnees().getCordPreset() != null) {
				if(((Bascule)cmp).getPreset().getSource() != null){
					crdDebut = cmp.getLesCoordonnees().coordReelesPreset(eleementAdrager, i);
					line = ((Bascule)cmp).getPreset().polylineParPoint(crdDebut);
					listEntrees.add(line);
				}
			}
			if(cmp.getLesCoordonnees().getCordLoad() != null ) {
				if(((Sequentiels)cmp).getLoad().getSource() != null) {
					crdDebut = cmp.getLesCoordonnees().coordReelesLoad(eleementAdrager, i);
					line = ((Sequentiels)cmp).getLoad().polylineParPoint(crdDebut);
					listEntrees.add(line);
				}

			}
			listSorties.clear();
			for(i = 0; i < cmp.getNombreSortie();i++){
				crdDebut = cmp.getLesCoordonnees().coordReelesSorties(eleementAdrager, i);
				line = Circuit.getPolylineFromFil(cmp.getSorties()[i]).get(0).getLinePrincipale();
				size = line.getPoints().size();
				listSorties.add(line);
			}
		}

		public void setAffX(Label affX) {
			afficheurX = affX;
		}

		public void setAffY(Label affY) {
			afficheurY = affY;
		}

		public void setScrollPane(ScrollPane scrollPane) {
			this.scrollPane = scrollPane;
		}

		public static void remplacerLineUndoDeque(Polyline line1,Polyline line2) {
			for ( Donnes donnes : undoDeque) {
				if(donnes.getTypeDaction().equals(Actions.CreationFil)) {
					if(donnes.getParent() == line1) {
						donnes.setParent(line2);
					}
				}
			}
		}
		public static void remplacerLineUndoDequeSupprimer(Polyline line1,Polyline line2) {
			for ( Donnes donnes : undoDeque) {
				if(donnes.getTypeDaction().equals(Actions.SuppressionFil)) {
					if(donnes.getInfoPolyline().getLineParent() == line1) {
						donnes.getInfoPolyline().setLineParent(line2);
					}
				}
			}
		}
	
	
	public void supprimerSauvegarde(InfoPolyline infoPolyline,Polyline paren,Fil filSortieFil) {
		if (infoPolyline.getLineParent() != null) {
			InfoPolyline parent = Circuit.getInfoPolylineFromPolyline(infoPolyline.getLineParent());
			parent.setNbFils(parent.getNbFils()+1);
			infoPolyline.getLineParent().getPoints().clear();
			infoPolyline.getLineParent().getPoints().addAll(paren.getPoints());
			ArrayList<InfoPolyline> resArrayList =  Circuit.getListFromPolyline(parent.getLinePrincipale());
			resArrayList.add(resArrayList.indexOf(parent)+1,infoPolyline);
		}
		workSpace.getChildren().add(infoPolyline.getLinePrincipale());
		if (infoPolyline.isRelier()) {
			Composant source = filSortieFil.getSource();
			int sortie = source.numCmpSorties(filSortieFil);
			if(entree >= 0) {
				Circuit.relier(source, infoPolyline.getDestination(), sortie, infoPolyline.getEntre());
			}else if(-5 < infoPolyline.getEntre()) {
				Circuit.relierCommand(source,((Combinatoires)infoPolyline.getDestination()), sortie, Math.abs(infoPolyline.getEntre())-1);
			}else if(infoPolyline.getEntre() == -5) {
				Circuit.relierHorloge(((Sequentiels)infoPolyline.getDestination()), source, sortie);
			}else if(infoPolyline.getEntre() == -6) {
				Circuit.relierClear(((Sequentiels)infoPolyline.getDestination()), source, sortie);
			}else if(infoPolyline.getEntre() == -7) {
				Circuit.relierPreset(((Bascule)infoPolyline.getDestination()), source, sortie);
			}else if(infoPolyline.getEntre() == -8) {
				Circuit.relierLoad(((Sequentiels)infoPolyline.getDestination()), source, sortie);
			}
			filSortieFil.addDestination(infoPolyline.getDestination());
		}
	}
	/*public void removePoints() {
		Polyline line;
		int i = 0,size = 0;
			for( i = 0; i < listSorties.size();i++){
				line = listSorties.get(i);
				line.getPoints().remove(0);
				line.getPoints().remove(0);
				}
			/*for(i = 0;i < listEntrees.size();i++) {
				line = listEntrees.get(i);
				size = line.getPoints().size();
				line.getPoints().add(size-3,line.getPoints().get(size - 2));
				line.getPoints().add(size-3,line.getPoints().get(size - 2));
			}
	}*/
	}
