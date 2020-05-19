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
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.sun.glass.ui.Size;

import application.ClickBarDroite;
import application.ClickDroit;
import application.ClickDroitLabel;
import application.ClickSouris2;
import application.FenetreDesErreurs;
import application.Main;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
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
import noyau.CircuitIntegreSequentiel;
import noyau.Combinatoires;
import noyau.Composant;
import noyau.Compteur;
import noyau.Coordonnees;
import noyau.D;
import noyau.Decodeur;
import noyau.DemiAdditionneur;
import noyau.Demultiplexeur;
import noyau.Donnes;
import noyau.EditableDraggableText;
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
	private ClickDroitLabel clickDroitLabel=null;
	private static ClickBarDroite fichierFenetre;
	private static ClickBarDroite affichageFenetre;
	private static ClickBarDroite editionFenetre;
	private static ClickBarDroite aideFenetre;
	public static File fichierCourant;
	public static Thread t1;
	Horloge horloge = null;
	boolean ctrlz = false;
	ArrayList<Polyline> sauv = new ArrayList<Polyline>();
	int switchingZ = 0;
	public static Polyline selectionne = new Polyline();
	private static boolean select = false;
	
	double posX; // utilisé dans la sauvegarde des coordonnées
 	double posY; // utilisé dans la sauvegarde des coordonnées

	ArrayList<Text> listDesNoms = new ArrayList<Text>();
	public static ArrayList<Button> btnsToHide = new ArrayList<Button>();

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
	
	private String fileToUpload;
	
	@FXML
	private Button annuler;

	@FXML
	private Button undoParSouris;

	@FXML
	private Button collerParSouris;

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
	private Label title;

	@FXML
	private ImageView titleImageView;

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
	@FXML
	private VBox rightVBox;

	@FXML
	private VBox vbar;

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
	@FXML
	private AnchorPane ExitBtn;

	@FXML
	private ImageView exitIcon;

	@FXML
	private Label LabelDeSauve;
	private static Label saveLabel;
	@FXML
	private AnchorPane hideBtn;

	@FXML
	void clickHide(MouseEvent event) { /// si on click sur le bouton hide/show de la fenetreS
		homeWindow.setIconified(true);;
	}
	@FXML
	void enterHide(MouseEvent event) { /// ajout d'un effet au bouton hide/show
		hideBtn.setStyle("-fx-background-color:grey");


	}
	@FXML
	void exitHide(MouseEvent event) {
		hideBtn.setStyle("-fx-background-color:transparent");
	}
	@FXML
	void ClickExit(MouseEvent event) { /// clicker sur le bouton fermer de la fenetre
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(Circuit.getCompUtilises().isEmpty() ? "Voulez vous vraiment quitter" :"Voulez vous sauvegarder ce circuit avant de quitter ?");
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.getButtonTypes().clear();
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.setX(homeWindow.getX()+500);
		alert.setY(homeWindow.getY()+250);
		ButtonType buttonTypeNon = new ButtonType( Circuit.getCompUtilises().isEmpty() ? "Oui":"Non");
		ButtonType buttonTypeSauvgarder = null;
		ButtonType buttonTypeCancel = new ButtonType("Annuler");
		alert.getButtonTypes().setAll(buttonTypeNon, buttonTypeCancel);
		if(! Circuit.getCompUtilises().isEmpty())
		{
			buttonTypeSauvgarder = new ButtonType("Sauvegarder");
			alert.getButtonTypes().add(buttonTypeSauvgarder);
		}
		Optional<ButtonType> result = alert.showAndWait();	
		if(result.get() != buttonTypeCancel) {
			if (result.get() == buttonTypeSauvgarder){
				if (fichierCourant == null) {
					final FileChooser fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
					File f = fileChooser.showSaveDialog(homeWindow);
					if (f != null) {

						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
						Alert a = new Alert(AlertType.INFORMATION);
						a.initOwner(homeWindow);
						a.initStyle(StageStyle.UTILITY);
						a.setContentText("Le circuit est bien sauvegardé");
						a.initOwner(homeWindow);
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.initStyle(StageStyle.UTILITY);
						a.showAndWait();
					}
				} else {
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
					Alert a = new Alert(AlertType.INFORMATION);
					a.initOwner(homeWindow);
					a.initStyle(StageStyle.UTILITY);
					a.setContentText("Le circuit est bien sauvegardé");
					a.initOwner(homeWindow);
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.initStyle(StageStyle.UTILITY);
					a.showAndWait();
				}
			}

			if ( HomeController.horloged && Controller.simul) {
				Horloge horloge = ((Horloge) Circuit.getCompFromImage(HomeController.horlogeDeCercuit));

				try {
					horloge.stop();
					HomeController.t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			javafx.application.Platform.exit();
		}

	}

	@FXML
	void enterExit(MouseEvent event) { /// ajouter un effet sur le bouton de fermeture de la fenetre
		ExitBtn.setStyle("-fx-background-color:B53737");
	}

	@FXML
	void exitExit(MouseEvent event) {
		exitIcon.setImage(new Image("/homePage_icones/Exit.png"));
		ExitBtn.setStyle("-fx-background-color:transparent");
	}

	void ajouterAnimationBarDroite(ImageView imageView) { /// ajouter l'operation hover sur les boutons de la bar droite
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

	void initialiseAnimationOfBarDroite(){ /// pour appliquer l'effet des icones de la bar droite sur tout les boutons
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

	@FXML void screenShot(MouseEvent event){ /// faire une capture du circuit
		final FileChooser fileChooser=new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("PNG", "*.png")
				);
		File f=fileChooser.showSaveDialog(homeWindow);
		if(f!=null){
			captureEcran(f.getAbsolutePath());
		}
	}

	@FXML
	void onSimuler(MouseEvent event) { /// pour lancer la simulation ou l'arreter
		Circuit.clearException();
		simul = (!simul);
		workSpace.getChildren().remove(selectionne);
		closeRightWindows();
		if (simul) { /// verifier si on a passé au mode simulation
			edition.setDisable(true);
			edition.setOpacity(0.4);
			affichage.setOpacity(1);
			affichage.setDisable(false);

			Controller.getRightBareButtons().get(0).setOpacity(1);
			Controller.getRightBareButtons().get(0).setDisable(false);

			if (clickSouris2 != null)
				clickSouris2.close();
			if (clickDroitLabel != null)
				clickDroitLabel.close();
			Circuit.validerCircuits(); /// valider le circuit et detecter les erreurs qu'il y'a
			if ( Circuit.isThereAnyException()) { /// verifier s'il ya des erreurs
				if (Circuit.isThereAnyError()) {/// s'il ya des erreurs
					simul = false;
					simulation.setImage(new Image("homePage_icones/simulation.png"));
					edition.setDisable(false);
					edition.setOpacity(1);
					affichage.setOpacity(0.4);
					affichage.setDisable(true);
					Controller.getRightBareButtons().get(0).setOpacity(0.4);
					Controller.getRightBareButtons().get(0).setDisable(true);
				}
				else { /// si aucun probleme 
					remplireNomPinEtAfficher(); /// affichage des labels des pins
					simulation.setImage(new Image("homePage_icones/SIMULATION_ON.png"));
					if(! horloged)	Circuit.initialiser();
					else {
						horloge=((Horloge)Circuit.getCompFromImage(horlogeDeCercuit));
						horloge.setImage(horlogeDeCercuit);
						t1=new Thread(horloge);
						t1.start();
						t1.setPriority(Thread.MIN_PRIORITY);
						
					}
					rotationDelogo(logo,1,1000,false); /// faire une rotation du logo
					if(Circuit.getListeEtages().size()==0 && !horloged) {
						rightBareButtons.get(1).setDisable(false);
						rightBareButtons.get(1).setOpacity(1);
						rightBareButtons.get(2).setDisable(true);
						rightBareButtons.get(2).setOpacity(0.4);

					}else {
						rightBareButtons.get(1).setDisable(true);
						rightBareButtons.get(1).setOpacity(0.4);
						rightBareButtons.get(2).setDisable(false);
						rightBareButtons.get(2).setOpacity(1);
					}
				}
				new FenetreDesErreurs(homeWindow); /// affichage de la fenetre des problemes
			}
			else {
				remplireNomPinEtAfficher();
				simulation.setImage(new Image("homePage_icones/SIMULATION_ON.png"));
				if(! horloged)	Circuit.initialiser();

				else {
					horloge=((Horloge)Circuit.getCompFromImage(horlogeDeCercuit));

					horloge.setImage(horlogeDeCercuit);
					t1 = new Thread(horloge);
					t1.start();
					t1.setPriority(Thread.MIN_PRIORITY);
				}
				rotationDelogo(logo, 1, 1000, false);
				if(Circuit.getListeEtages().size()==0 && !horloged) {
					rightBareButtons.get(1).setDisable(false);
					rightBareButtons.get(1).setOpacity(1);
					rightBareButtons.get(2).setDisable(true);
					rightBareButtons.get(2).setOpacity(0.4);

				}else {
					rightBareButtons.get(1).setDisable(true);
					rightBareButtons.get(1).setOpacity(0.4);
					rightBareButtons.get(2).setDisable(false);
					rightBareButtons.get(2).setOpacity(1);
				}
			}
		}
		else {
			Controller.getRightBareButtons().get(0).setText("   Circuit personnalisé");
			Controller.getRightBareButtons().get(0).setAlignment(Pos.BASELINE_LEFT);
			Controller.getRightBareButtons().get(1).setText("  Table de vérité");
			Controller.getRightBareButtons().get(1).setAlignment(Pos.BASELINE_LEFT);
			showButtonsFile();
			remouveNomPin();
			affichage.setOpacity(0.4);
			affichage.setDisable(true);
			Controller.getRightBareButtons().get(0).setOpacity(0.4);
			Controller.getRightBareButtons().get(0).setDisable(true);
			edition.setDisable(false);
			edition.setOpacity(1);
			simulation.setImage(new Image("/homePage_icones/simulation.png"));
			Circuit.defaultCompValue();
			if(ListTextPin != null ) {
				opacityElements4();
			}
			if( horloged)
			{
				try {
                    t1.join(1);
					horlogeDeCercuit.setImage( new Image("/Horloge/0.png"));
					horloge.stop();
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void inisialiser() { /// pour l'initialisation des effets de la fenetre principale (affichage des guides ajout de
		/// l'operation du drag and drop pour tout les composants			
		affichage.setOpacity(0.4);
		affichage.setDisable(true);
		ajouterGestWorkSpace(); /// ajouter des listners pour capter les operations (clic , drag .. etc)
		tracerLesGuides(); /// tracer les guides qui aide l'user pour connaitre la position de l'elt
		afficheurX.setText("X : 0");
		afficheurY.setText("Y : 0");
		saveLabel=LabelDeSauve;

		//opacityBouttons(simul); //les bouttons qui ne fonctionnent que en mode simulation
		// Creation d'une map pour gerer les titres des composants
		elemanrsMapFillMap = new HashMap<ImageView, Label>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7398456253712524242L;

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
				put(titleImageView,title);
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
		ajouterLeGestLabel(titleImageView);
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
		ajouterLeGestWindow();
		workSpace.setOnMousePressed(new EventHandler<MouseEvent>() { /// si l'user clique sur le workspace
			@Override
			public void handle(MouseEvent event) {

				if(!select) {
					workSpace.getChildren().remove(selectionne);
					elementAsuprimer = null;
				}
				
				if(clickDroitLabel != null) clickDroitLabel.close();
				for(ClickBarDroite click : tableauFenetres) {
					click.close();
					tooltipInitialize();
				}
				if (!simul ) { /// verifier si on est dans le mode de simulation ou non

					ctrlX = event.getX();
					ctrlY = event.getY();
					if(cc && elementSeclecionner != null) {
						cc = false;
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
							if (event.getScreenX() > 1145) {
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
		importCircuit();
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
						if(elementSeclecionner != null) {
							workSpace.getChildren().remove(selectionne);
							copierActive = true;
							copyActive = true;
							ImageView sauv = elementSeclecionner;
							elementAsuprimer = elementSeclecionner;
							sauveGarderSupression();
							workSpace.getChildren().remove(elementSeclecionner);
							Composant composantCouper = Circuit.getCompFromImage(elementSeclecionner);
							supprimerDequeFilProbleme(composantCouper);
							composantCopy = composantCouper;
							ArrayList<Polyline> lineListe = Circuit.supprimerComp(composantCouper);
							for (Polyline line : lineListe)
								workSpace.getChildren().remove(line);
							if(elementSeclecionner.getId().equals("clock"))
							{
								HomeController.horloged =false;
								HomeController.horlogeDeCercuit =null; 
							}
							else if (elementSeclecionner.getId().equals("CircuitIntegre")) {
								ArrayList<Circle> arrayList = ((CircuitIntegre)composantCouper).getListeCercles();
								for (Circle circle : arrayList) {
									workSpace.getChildren().remove(circle);
								}
							} 
							else if (elementSeclecionner.getId().equals("CircuitIntegreSequentiel")) {
								ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)composantCouper).getListeCercles();
								for (Circle circle : arrayList) {
									workSpace.getChildren().remove(circle);
								}
							} 
							elementSeclecionner = sauv;
						}
					}

					if (event.isControlDown() && (event.getCode() == KeyCode.C)) {
						if (elementSeclecionner != null) {
							setCopierActive(true);
							copyActive = false;
						}
					}
					if (event.isControlDown() && (event.getCode() == KeyCode.V)) {
						CopyUses();
					}
					if (event.getCode() == KeyCode.DELETE) {
						workSpace.getChildren().remove(selectionne);
						if (elementAsuprimer != null) {
							Composant cmp = Circuit.getCompFromImage(elementAsuprimer);
							supprimerDequeFilProbleme(cmp);
							sauveGarderSupression();		
							if(elementAsuprimer.getId().equals("clock"))
							{
								horloged =false;
								horlogeDeCercuit =null; 
							}
							else if (elementAsuprimer.getId().equals("CircuitIntegre")) {
								ArrayList<Circle> arrayList = ((CircuitIntegre)cmp).getListeCercles();
								for (Circle circle : arrayList) {
									workSpace.getChildren().remove(circle);
								}
							} 
							else if (elementAsuprimer.getId().equals("CircuitIntegreSequentiel")) {
								ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)cmp).getListeCercles();
								for (Circle circle : arrayList) {
									workSpace.getChildren().remove(circle);
								}
							} 
							workSpace.getChildren().remove(elementAsuprimer);
							removeAllPolylinesFromWorkSpace(Circuit.supprimerComp(cmp));	
							elementAsuprimer = null;
							elementSeclecionner = null;
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

	public void rightbar(ImageView icon, ClickBarDroite cc, ClickBarDroite tableauDeFenetres[]) { /// responsable de l'affichage et fermeture des fenetres de la bar droite
		icon.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (clickDroitFenetre != null) {
					clickDroitFenetre.close();
				}
				if (clickDroitFilFenetre != null) {
					clickDroitFilFenetre.close();
				}
				if (clickSouris2 != null) {
					clickSouris2.close();
				}
				for (ClickBarDroite click : tableauDeFenetres) {
					if (cc.equals(click)) {
						if (cc.isShowing())
							cc.close();
						else 
						{
							cc.show();
							if (simul) {
								hideButtonsFile();
							}
						}

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
		//	fich.setShowDelay(Duration.millis(0));
		Tooltip.install(fichier, fich);


		Tooltip edi = new Tooltip("edition");
		//		edi.setShowDelay(Duration.millis(0));
		Tooltip.install(edition, edi);

		Tooltip sim = new Tooltip("simulation");
		//	sim.setShowDelay(Duration.millis(0));
		Tooltip.install(simulation, sim);

		Tooltip aff = new Tooltip("affichage");
		//		aff.setShowDelay(Duration.millis(0));
		Tooltip.install(affichage, aff);

		Tooltip aid = new Tooltip("aide");
		//		aid.setShowDelay(Duration.millis(0));
		Tooltip.install(aide, aid);

		Tooltip capture = new Tooltip("camera");
		//		capture.setShowDelay(Duration.millis(0));
		Tooltip.install(camera, capture);
		/*-------------------------------------*/

		Tooltip com = new Tooltip("Combinatoires");
		//		com.setShowDelay(Duration.millis(0));
		comb.setTooltip(com);

		Tooltip se = new Tooltip("Sequentiels");
		//		se.setShowDelay(Duration.millis(0));
		seq.setTooltip(se);

		Tooltip out = new Tooltip("Outils");
		//		out.setShowDelay(Duration.millis(0));
		outils.setTooltip(out);

		Tooltip por = new Tooltip("Portes");
		//		por.setShowDelay(Duration.millis(0));
		portes.setTooltip(por);

		/*------------------------------*/

	}


	private void ajouterLeGest(ImageView elementAdrager) {//Methode d'ajout de la fonctionallité de drag and drop avant que le composant
		//est ajouté dans le workSpace


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

		elementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() { /// clicker sur un composant
			@Override
			public void handle(MouseEvent e) {
				if (! simul) {

					ImageView dragImageView = new ImageView();
					dragImageView.setMouseTransparent(true);
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

					elementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() { /// deposer un composant dans le workspace
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

								if( dragImageView.getLayoutX() <= 0 ||dragImageView.getLayoutY() <= 0|| (e.getSceneX() +( dragImageView.getBoundsInLocal().getWidth()) / 2) > 1300 || e.getSceneY() + (dragImageView.getBoundsInLocal().getHeight() / 2)>720 || intersectionComposant(dragImageView)||( dragImageView.getId().equals("clock") && ( horloged)))

								{
									workSpace.getChildren().remove(dragImageView);
									Circuit.supprimerComp(Circuit.getCompFromImage(dragImageView));
									workSpace.getChildren().remove(guideX);
									workSpace.getChildren().remove(guideXp);
									workSpace.getChildren().remove(guideY);
									workSpace.getChildren().remove(guideYp);
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

	public Polyline tracerEntrerApresCollage(Polyline line, Coordonnees crdDebut, boolean relocate) { /// Trecer les fils  d'entrees
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
				Polyline line2 = Circuit.getInfoPolylineFromPolyline(line).getLineParent();
				if ((Math.abs(line.getPoints().get(0) - line2.getPoints().get(line2.getPoints().size() - 2)) < 6)
						&& (Math.abs(
								line.getPoints().get(1) - line2.getPoints().get(line2.getPoints().size() - 1)) < 6)) {
					// Si le prochain polyline a les memes coordonnees de debut que celle de la
					// derniere du polyline actuel
					if ((Math.abs(line.getPoints().get(0) - x2) < 6) && (Math.abs(line.getPoints().get(1) - y2) < 6)) {
						// Suppression
						SupprimerPereUndoChanges(line);
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

	public void SupprimerPereUndoChanges(Polyline line1) { /// utilisé pour regler les problèmes dans l'operation du ctrl + z
		for (Donnes donnes : undoDeque) { 
			if(donnes.getInfoPolyline() != null && !donnes.isSupprime() ) {
				if(donnes.getInfoPolyline().getLineParent() == line1) {
					donnes.setSupprime(true);
					SupprimerPereUndoChanges(donnes.getInfoPolyline().getLinePrincipale());
				}
			}
			if(donnes.getTypeDaction().equals(Actions.SuppressionToutFil)) {
				for (InfoPolyline info : donnes.getArrayListInfoPoly()) {
					if(info.getLineParent() == line1) {
						donnes.setSupprime(true);
						SupprimerPereUndoChanges(info.getLinePrincipale());
					}
				}
			}
		}
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
							SupprimerPereUndoChanges(line);
							Circuit.getListFromPolyline(line).remove(new InfoPolyline(line));
							line.getPoints().clear();
							listSorties.add(listSorties.indexOf(line), line2);
							listSorties.remove(line);
							Circuit.getInfoPolylineFromPolyline(line2).setLineParent(null);
							line = line2;
						}
					}
				}
			}
		}else {
			SupprimerPereUndoChanges(line);
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
			if(!ctrlz) {
				if(Math.abs(x2-x)<10) {
					if(Math.abs(y2-y)<10) switching = 0;
					else switching = 1;
				}else {
					if(Math.abs(y2-y)<10) switching = 0;
				}
			}else {
				switching = switchingZ;
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

		eleementAdrager.setOnMouseEntered(new EventHandler<MouseEvent>() { // ajouter un effet quand on rentre avec la souris dans l'image
			@Override
			public void handle(MouseEvent e) {
				eleementAdrager.setCursor(Cursor.HAND);
				select = true;
			}
		});

		eleementAdrager.setOnMouseExited(new EventHandler<MouseEvent>() { // ajouter un effey quand on sort de l'image

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				eleementAdrager.setCursor(Cursor.DEFAULT);
				select = false;
			}
		});



		eleementAdrager.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (! simul) {
					copierActive = false;
					posX = eleementAdrager.getLayoutX();
					posY = eleementAdrager.getLayoutY();
					afficheurX.setText("X : " + posX);
					afficheurY.setText("Y : " + posY);
					selectionne(eleementAdrager);
					if (e.getButton() != MouseButton.SECONDARY) {
						if(clickDroitFenetre != null) clickDroitFenetre.close();
						if(clickDroitLabel!=null) {
							clickDroitLabel.close();
							clickDroitLabel=null;
						}
						dragItem = eleementAdrager;
						eleementAdrager.setMouseTransparent(true);
						eleementAdrager.setMouseTransparent(true);
						eleementAdrager.setCursor(Cursor.CLOSED_HAND);
						elementSeclecionner = eleementAdrager;
						elementAsuprimer = eleementAdrager;
						eleementAdrager.setOnDragDetected(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								SnapshotParameters snapParams = new SnapshotParameters();
								snapParams.setFill(Color.TRANSPARENT);
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
						if (clicDroitX > 1140) { /// faire un traitement pour l'affichage de la fenetre du click droit du composant
							if (clicDroitY > 550) {
								clickDroitFenetre = new ClickDroit(composant,clicDroitX-150,clicDroitY-150,workSpace, homeWindow);
							}
							else {
								clickDroitFenetre = new ClickDroit(composant,clicDroitX-150,clicDroitY,workSpace, homeWindow);
							}
						}
						else {
							if (clicDroitY > 550) {
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

					refrechLists(eleementAdrager); /// refrecher la liste des points dans le polylines
					eleementAdrager.setOnMouseDragged(new EventHandler<MouseEvent>() { /// si le composant est dragé .
						@Override
						public void handle(MouseEvent e) {
							if (!simul) {	
								workSpace.getChildren().remove(selectionne);
								if (e.getButton() == MouseButton.PRIMARY) {
									addPoints();/// ajouter les points
									Composant ci=Circuit.getCompFromImage(eleementAdrager);
									if (ci.getClass().getSimpleName().equals("CircuitIntegre")) {
										CircuitIntegre circuitIntegre = ((CircuitIntegre)ci);
										circuitIntegre.resetCirclesPosition(eleementAdrager.getLayoutX(), eleementAdrager.getLayoutY());																		
									}
									else if(ci.getClass().equals(CircuitIntegreSequentiel.class)) {
										CircuitIntegreSequentiel circuitIntegre = ((CircuitIntegreSequentiel)ci);
										circuitIntegre.resetCirclesPosition(eleementAdrager.getLayoutX(), eleementAdrager.getLayoutY());
									}
									Point2D localPoint = workSpace
											.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
									eleementAdrager.relocate(
											(int) (localPoint.getX()
													- eleementAdrager.getBoundsInLocal().getWidth() / 2),
											(int) (localPoint.getY()
													- eleementAdrager.getBoundsInLocal().getHeight() / 2));

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
									if (e.getSceneX() > 1275) { /// pour faire le defilemen du scroll Pane
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

								else /// seter les coordonnées des composants
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
								if(e.getSceneY() < 55)
								{
									scrollPane.setVvalue(scrollPane.getVvalue()-0.01);
								}
								e.consume();

								updatePolyline(eleementAdrager);

							}
						}
					});
					eleementAdrager.setOnMouseReleased(new EventHandler<MouseEvent>() { /// deposer un composant dans le workspace
						@Override
						public void handle(MouseEvent e) {
							if (! simul) {
								dragItem = null;
								if(posX != eleementAdrager.getLayoutX() || posY != eleementAdrager.getLayoutY()) /// verifier si la position du composant a été modifié
								{
									Donnes sauveGarde=new Donnes(); /// faire une sauvegarde de mouvement
									sauveGarde.setTypeDaction(Actions.Mouvement);
									sauveGarde.setComposantCommeImage(eleementAdrager);
									sauveGarde.setSwitching(switchingZ);											
									sauveGarde.setPosX(posX);
									sauveGarde.setPosY(posY);
									undoDeque.addFirst(sauveGarde);
								}
								eleementAdrager.setMouseTransparent(false);
								eleementAdrager.setCursor(Cursor.DEFAULT);
								if( eleementAdrager.getLayoutX() <= 0 ||eleementAdrager.getLayoutY() <= 0|| (e.getSceneX() +( eleementAdrager.getBoundsInLocal().getWidth()) / 2) > 1300 || e.getSceneY() + (eleementAdrager.getBoundsInLocal().getHeight() / 2)>720 || intersectionComposant(eleementAdrager))
									/// verifier si le composant a été déposé dans une zone interdite
								{
									eleementAdrager.setLayoutX(posX); //
									eleementAdrager.setLayoutY(posY); // rendre le composant à sa place précedante
									updatePolyline(eleementAdrager);
									afficheurX.setText(String.valueOf(posX));
									afficheurY.setText(String.valueOf(posY));
									workSpace.getChildren().remove(guideX);
									workSpace.getChildren().remove(guideXp);
									workSpace.getChildren().remove(guideY);
									workSpace.getChildren().remove(guideYp);
									Composant composant = Circuit.getCompFromImage(eleementAdrager);
									if (composant.getClass().equals(CircuitIntegre.class)) {
										((CircuitIntegre)composant).resetCirclesPosition(posX, posY);
									}
									else if (composant.getClass().equals(CircuitIntegreSequentiel.class)) {
										((CircuitIntegreSequentiel)composant).resetCirclesPosition(posX, posY);
									}
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
					if(ListTextPin == null) { /// pour donner la main à l'utilisateur pour changer la valeur stocké dans le pin
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
						Composant compos = Circuit.getCompFromImage(eleementAdrager); /// pour donner la main pour sélectionner l'ordre des pins d'entrées et sorties
						if(compos.getClass().getSimpleName().equals("Pin")) {
							if( ((Pin)compos).isInput()) { /// verifier si c'est un pin d'entrée
								if(!ListTextPin.contains(compos)){
									Text number = new Text();
									number.setLayoutX(eleementAdrager.getLayoutX()-9);
									number.setLayoutY(eleementAdrager.getLayoutY() + 15);
									String id = Integer.toString(ListTextPin.size()+1);
									number.setText(id);
									number.setId(id);
									number.setFont(Font.font("Calisto MT",FontWeight.BOLD,18));
									workSpace.getChildren().add(number);
									ListTextPin.add((Pin)compos);
									ListText.add(number);
								}else {
									Alert alert = new Alert(AlertType.CONFIRMATION);
									alert.initOwner(homeWindow);
									alert.initStyle(StageStyle.UTILITY);
									alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
									alert.setTitle("Confirmation");
									alert.setHeaderText("Refaire l'ordre ");
									alert.setContentText("Cette entrée est déja sélectionnée, voulez-vous réordonner les entrées ?");
									alert.initStyle(StageStyle.UTILITY);
									alert.initOwner(homeWindow);
									alert.setX(homeWindow.getX()+500);
									alert.setY(homeWindow.getY()+250);
									Optional<ButtonType> result = alert.showAndWait();
									if(result.get() == ButtonType.OK) {

										for (Text num : ListText) {
											workSpace.getChildren().remove(num);
										}
										ListText.clear();
										ListTextPin.clear();
									}
								}
							}
							else { /// pin de sortie
								if(!ListTextPin2.contains(compos)){
									Text number = new Text();
									number.setLayoutX(eleementAdrager.getLayoutX() - 9  );
									number.setLayoutY(eleementAdrager.getLayoutY() +  15 );
									String id = Integer.toString(ListTextPin2.size()+1);
									number.setText(id);
									number.setId(id);
									number.setFont(Font.font("Calisto MT",FontWeight.BOLD,18));
									number.setFill(Color.web("#8B0000"));
									workSpace.getChildren().add(number);
									ListTextPin2.add((Pin)compos);
									ListText2.add(number);
								}else { /// afficher une alerte si un pin est re-sélectionné
									Alert alert = new Alert(AlertType.CONFIRMATION);
									alert.initOwner(homeWindow);
									alert.initStyle(StageStyle.UTILITY);
									alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
									alert.setTitle("Confirmation");
									alert.setHeaderText("Refaire l'ordre ");
									alert.initOwner(homeWindow);
									alert.setContentText("Cette entrée est déja sélectionnée, voulez-vous réordonner les entrées ?");
									alert.initStyle(StageStyle.UTILITY);
									alert.setX(homeWindow.getX()+500);
									alert.setY(homeWindow.getY()+250);
									Optional<ButtonType> result = alert.showAndWait();
									if(result.get() == ButtonType.OK) {

										for (Text num : ListText2) {
											workSpace.getChildren().remove(num);
										}
										ListText2.clear();
										ListTextPin2.clear();
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

	private void instanceComposant(ImageView img) { /// cette fonction est utilisé pour faire une instance d'un composant selon une image donné
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
		}break;
		case "CircuitIntegre" : {
			comp = new CircuitIntegre(0, 0, "CircuitIntegre");
		}
		break;
		default: {
			comp = new Encodeur(2, "");
		}
		}
		Circuit.ajouterComposant(comp, img); /// ajout du composant au hashmap des composant utilisé
	}

	private boolean intersectionComposant(ImageView image) { /// savoir s'il y'a une intersection entre les composants
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
	private boolean intersectionCoordone(ImageView origin, ImageView copie) { /// voir s'il y'a intersection entre deux images dans le workspace
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

	public int nbOccPoint(Polyline line, double x, double y) { /// compter le nombre de points qui ont les coordonnées passées comme parametre
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
	void supprimerTout(ActionEvent event) { /// faire une suppression total du circuit
		workSpace.getChildren().remove(selectionne);
		Stage stage = (Stage) supprimerTout.getScene().getWindow(); 	
		stage.close();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.setX(homeWindow.getX()+500);
		alert.setY(homeWindow.getY()+250);
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		if (! simul) {			
			if(!(Circuit.getCompUtilises().isEmpty())) {				
				alert.setContentText("Voulez-vous vraiment supprimer toute la zone du circuit ");
				Optional<ButtonType> result = alert.showAndWait();	    		
				if(result.get() == ButtonType.OK){
					Circuit.clearCircuit();
					undoDeque.clear();
					workSpace.getChildren().clear();
					horloged = false;
					horlogeDeCercuit = null;
					tracerLesregles(workSpace);	
				}
			}
			else {
				alert.setAlertType(AlertType.INFORMATION);
				alert.setContentText("Le circuit est déja vide !");
				alert.show();
			}
		}
		else {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setContentText("Veuillez désactiver le mode de simulation");
			alert.show();

		}

	}


	@FXML
	public void copier(ActionEvent event) { /// pour copier un composant avec la bar droite
		Stage s = (Stage) copier.getScene().getWindow();
		s.close();
		if(elementSeclecionner != null) { /// verifier si un composant est sélectionné
			setCopierActive(true);
			copyActive = false ;
		}
	}
	public void coller(ActionEvent event) { /// coller le composant avec la bar droite
		cc = true;
		CopyUses();
		Stage stage = (Stage) coller.getScene().getWindow();
		stage.close();		
	}

	public void CopyUses() { /// la fonction qui fait la copie
		if (elementSeclecionner != null && copierActive) { /// verifier s'il y'a un elt sélectionné
			if(!pastButton) {
				if (! elementSeclecionner.getId().equals("CircuitIntegreSequentiel") && ((elementSeclecionner.getId().equals("clock") && ( ! horloged)) || (!elementSeclecionner.getId().equals("clock")))) {
					if(!copyActive)
						composantCopy = Circuit.getCompFromImage(elementSeclecionner);
					if(composantCopy != null) /// verifier si le composant existe
					{
						ImageView dragImageView = new ImageView();
						dragImageView.setLayoutX(ctrlX);
						dragImageView.setLayoutY(ctrlY);
						dragImageView.setId(elementSeclecionner.getId());
						instanceComposant(dragImageView);		
						Composant cmp2 = Circuit.getCompFromImage(dragImageView);
						sauveGardeCopier(dragImageView,cmp2);
						cmp2.setDirection(composantCopy.getDirection());
						cmp2.setIcon(composantCopy.getIcon());
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
							else if(cmp2.getClass().getSimpleName().equals("Pin")) {
								boolean input = ((Pin)composantCopy).getInput();
								((Pin)cmp2).setInput(input);
								if (! input) {
									Circuit.getEntreesCircuit().remove((Pin)cmp2);
									Circuit.getSortiesCircuit().add((Pin)cmp2);
								}
							}
							else if(cmp2.getClass().getSimpleName().equals("CircuitIntegre")) {
								((CircuitIntegre)cmp2).setTableVerite(((CircuitIntegre)composantCopy).getTableVerite());
								((CircuitIntegre)cmp2).setCord();
								ArrayList<Circle> reList = ((CircuitIntegre)cmp2).generateCercles(dragImageView.getLayoutX(), dragImageView.getLayoutY());
								for (Circle circle : reList) {
									workSpace.getChildren().add(circle);
								}
							}
							else if(cmp2.getClass().equals(Horloge.class)) {
								horloged = true;
								horlogeDeCercuit = dragImageView;
							}
						cmp2.getLesCoordonnees().setNbCordEntree(composantCopy.getNombreEntree());
						cmp2.getLesCoordonnees().setNbCordSorties(composantCopy.getNombreSortie());
						dragImageView.setImage(elementSeclecionner.getImage());
						dragImageView.setFitHeight(elementSeclecionner.getImage().getHeight());
						dragImageView.setFitWidth(elementSeclecionner.getImage().getWidth());		
						workSpace.getChildren().add(dragImageView);
						ArrayList<Polyline> polyline = Circuit.getCompFromImage(dragImageView).generatePolyline(dragImageView.getLayoutX(), dragImageView.getLayoutY());
						addAllPolylinesToWorkSpace(polyline);
						ajouterLeGestApresCollage(dragImageView); /// ajouter les gestes nécessaires pour que l'image marche
						elementSeclecionner = dragImageView;
						//copierActive = false;
					}

				}
			}
		}

	}

	/*-----------------------------------------------------*/

	@FXML
	void annuler(ActionEvent event) { /// annuler une operation avec la bar droite
		((Stage)annuler.getScene().getWindow()).close();
		undoChanges(workSpace);

	}


	/*-----------------------------------------------------*/


	@FXML
	void supprimer(ActionEvent event) { /// pour appliquer une suppression sur
		((Stage)supprimer.getScene().getWindow()).close();
		workSpace.getChildren().remove(selectionne);
		if (elementAsuprimer != null) {
			cmp = Circuit.getCompFromImage(elementAsuprimer);
			supprimerDequeFilProbleme(cmp); /// supprimer des erreurs dues à la suppression d'un composant
			sauveGarderSupression(); /// sauvegarder une suppression
			if(elementAsuprimer.getId().equals("clock"))
			{
				HomeController.horloged =false;
				HomeController.horlogeDeCercuit =null;
			}
			else if (elementAsuprimer.getId().equals("CircuitIntegre")) {
				ArrayList<Circle> arrayList = ((CircuitIntegre)cmp).getListeCercles();
				for (Circle circle : arrayList) {
					workSpace.getChildren().remove(circle);
				}
			} 
			else if (elementAsuprimer.getId().equals("CircuitIntegreSequentiel")) {
				ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)cmp).getListeCercles();
				for (Circle circle : arrayList) {
					workSpace.getChildren().remove(circle);
				}
			}
			workSpace.getChildren().remove(elementAsuprimer);
			removeAllPolylinesFromWorkSpace(Circuit.supprimerComp(cmp));
			elementAsuprimer=null;
			elementSeclecionner = null;
		}
	}



	@FXML
	void couper(ActionEvent event) { /// couper un composant
		workSpace.getChildren().remove(selectionne);
		copierActive = true;
		copyActive = true;
		ImageView sauv = elementSeclecionner;
		elementAsuprimer = elementSeclecionner;
		workSpace.getChildren().remove(elementSeclecionner);
		Composant composantCouper = Circuit.getCompFromImage(elementSeclecionner);
		supprimerDequeFilProbleme(composantCouper);
		sauveGarderSupression();
		if(elementSeclecionner.getId().equals("clock"))
		{
			HomeController.horloged =false;
			HomeController.horlogeDeCercuit =null; 
		}
		else if (elementSeclecionner.getId().equals("CircuitIntegre")) { /// verifier si c'est un circuit intégré
			ArrayList<Circle> arrayList = ((CircuitIntegre)composantCouper).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		else if (elementSeclecionner.getId().equals("CircuitIntegreSequentiel")) { /// verifier si c'est un circuit intégré sequentiel
			ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)composantCouper).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
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
	void fermer(ActionEvent event) { /// fermer la fenetres à partir de la bar droite
		workSpace.getChildren().remove(selectionne); /// enlever le cadre
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(Circuit.getCompUtilises().isEmpty() ? "Voulez-vous vraiment quitter ?" :"Voulez-vous sauvegarder ce circuit avant de quitter ?");
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.getButtonTypes().clear();
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.setX(homeWindow.getX()+500);
		alert.setY(homeWindow.getY()+250);
		ButtonType buttonTypeNon = new ButtonType( Circuit.getCompUtilises().isEmpty() ? "Oui":"Non");
		ButtonType buttonTypeSauvgarder = null;
		ButtonType buttonTypeCancel = new ButtonType("Annuler");
		alert.getButtonTypes().setAll(buttonTypeNon, buttonTypeCancel);
		if(! Circuit.getCompUtilises().isEmpty()) /// si le circuit n'est pas vide
		{
			buttonTypeSauvgarder = new ButtonType("Sauvgarder");
			alert.getButtonTypes().add(buttonTypeSauvgarder);
		}
		Optional<ButtonType> result = alert.showAndWait();	
		if(result.get() != buttonTypeCancel) { /// voir qu'elle est le bouton cliqué
			if (result.get() == buttonTypeSauvgarder){
				if (fichierCourant == null) {
					final FileChooser fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
					File f = fileChooser.showSaveDialog(homeWindow);
					if (f != null) {

						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
						Alert a = new Alert(AlertType.INFORMATION);
						a.initOwner(homeWindow);
						a.initStyle(StageStyle.UTILITY);
						a.setContentText("Le circuit est bien sauvegardé");
						a.initOwner(homeWindow);
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.initStyle(StageStyle.UTILITY);
						a.setX(homeWindow.getX()+500);
						a.setY(homeWindow.getY()+250);
						a.showAndWait();
					}
				} else { /// afficher une alerte pour indiquer que le circuit est bien sauvegardé
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
					Alert a = new Alert(AlertType.INFORMATION);
					a.initOwner(homeWindow);
					a.initStyle(StageStyle.UTILITY);
					a.setContentText("Le circuit est bien sauvegardé");
					alert.initOwner(homeWindow);
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.initStyle(StageStyle.UTILITY);
					a.setX(homeWindow.getX()+500);
					a.setY(homeWindow.getY()+250);
					a.showAndWait();
				}
			}

			if ( HomeController.horloged && Controller.simul) {
				Horloge horloge = ((Horloge) Circuit.getCompFromImage(HomeController.horlogeDeCercuit));

				try {
					HomeController.t1.join(1);
					horloge.stop();
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			javafx.application.Platform.exit();
		}
	}



	@FXML
	void nouveau(ActionEvent event) { /// créer un nouveau espace pour construire de nouveaux circuits
		workSpace.getChildren().remove(selectionne);
		Stage stage = (Stage) nouveau.getScene().getWindow();
		stage.close();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.setContentText("Voulez-vous sauvegarder ce circuit ?");
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		if(! Circuit.getCompUtilises().isEmpty()) /// verifier si le circuit n'est pas vide
		{
			ButtonType buttonTypeNon = new ButtonType("Non");
			ButtonType buttonTypeCancel = new ButtonType("Annuler");
			ButtonType buttonTypeSauvgarder  = null ;
			alert.getButtonTypes().setAll(buttonTypeNon, buttonTypeCancel);
			buttonTypeSauvgarder = new ButtonType("Sauvegarder");
			alert.getButtonTypes().add(buttonTypeSauvgarder);
			Optional<ButtonType> result = alert.showAndWait();
			if ( result.get() !=buttonTypeCancel)
			{
				afficheurX.setText("X : " + 0);
				afficheurY.setText("Y : " + 0);
				if (result.get() ==buttonTypeSauvgarder) {
					if (fichierCourant == null) {
						final FileChooser fileChooser = new FileChooser();
						fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
						fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
						File f = fileChooser.showSaveDialog(homeWindow);
						if (f != null) {
							Sauvegarde sauvegarde = new Sauvegarde();
							sauvegarde.saveCiruit(f.getAbsolutePath());
							Alert a = new Alert(AlertType.INFORMATION);
							a.initOwner(homeWindow);
							a.initStyle(StageStyle.UTILITY);
							a.setContentText("Le circuit est bien sauvegardé");
							a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
							a.showAndWait();		
						}
					} else {
						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
						Alert a = new Alert(AlertType.INFORMATION);
						a.initOwner(homeWindow);
						a.initStyle(StageStyle.UTILITY);
						a.setContentText("Le circuit est bien sauvegardé");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.showAndWait();
					}
				}
				saveLabel.setText("Non Sauvegardé");
				Circuit.clearCircuit();	/// vider tout le contenu du circuit dans le noyau
				fichierCourant = null;
				workSpace.getChildren().clear();
				horloged = false;
				horlogeDeCercuit=null;
				tracerLesregles(workSpace);
				undoDeque.clear();
			}
		}
		else {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.initOwner(homeWindow);
			alert2.initStyle(StageStyle.UTILITY);
			alert2.setContentText("Le circuit est déja vide !!");
			alert2.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
			alert2.showAndWait();
		}
	}

	@FXML
	void ouvrir(ActionEvent event) { /// ouvrir un circuit dans l'application
		workSpace.getChildren().remove(selectionne);
		Stage stage = (Stage) ouvrir.getScene().getWindow();
		stage.close();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.setContentText(Circuit.getCompUtilises().isEmpty() ?"Voulez-vous vraiment ouvrir un projet ?":"Voulez-vous sauvegarder ce circuit ?");
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		alert.initStyle(StageStyle.UTILITY);
		alert.initOwner(homeWindow);
		alert.setX(homeWindow.getX()+500);
		alert.setY(homeWindow.getY()+250);
		ButtonType buttonTypeNon = new ButtonType(Circuit.getCompUtilises().isEmpty() ? "Oui" : "Non");
		ButtonType buttonTypeCancel = new ButtonType("Annuler");
		ButtonType buttonTypeSauvgarder  = null ;
		alert.getButtonTypes().setAll(buttonTypeNon, buttonTypeCancel);
		if(! Circuit.getCompUtilises().isEmpty())
		{
			buttonTypeSauvgarder = new ButtonType("Sauvegarder");
			alert.getButtonTypes().add(buttonTypeSauvgarder);
		}
		Optional<ButtonType> result = alert.showAndWait();
		if ( result.get() !=buttonTypeCancel)
		{
			afficheurX.setText("X : " + 0);
			afficheurY.setText("Y : " + 0);
			if (result.get() ==buttonTypeSauvgarder) {
				if (fichierCourant == null) {
					FileChooser fileChooser = new FileChooser();
					File f = fileChooser.showSaveDialog(homeWindow);
					fileChooser.setInitialDirectory(
							new File(System.getProperty("user.home"))
							);                 
					fileChooser.getExtensionFilters().addAll(
							new FileChooser.ExtensionFilter("SIM", "*.sim")
							);
					if (f != null) {
						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(f.getAbsolutePath());
						Alert a = new Alert(AlertType.INFORMATION);
						a.initOwner(homeWindow);
						a.initStyle(StageStyle.UTILITY);
						a.setContentText("Le circuit est bien sauvegardé");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.initStyle(StageStyle.UTILITY);
						a.setX(homeWindow.getX()+500);
						a.setY(homeWindow.getY()+250);
						a.initOwner(homeWindow);
						fichierCourant=f;
						saveLabel.setText(f.getName());
						a.showAndWait();
					}
				} else {
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
					Alert a = new Alert(AlertType.INFORMATION);
					a.initOwner(homeWindow);
					a.initStyle(StageStyle.UTILITY);
					a.setContentText("Le circuit est bien sauvegardé");
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.initStyle(StageStyle.UTILITY);
					a.setX(homeWindow.getX()+500);
					a.setY(homeWindow.getY()+250);
					a.initOwner(homeWindow);
					a.showAndWait();
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
				undoDeque.clear();
				horloged = false;
				tracerLesregles(workSpace);
				Sauvegarde.loadCiruit(f.getAbsolutePath());
				ajouterElements();
				fichierCourant = f;
				saveLabel.setText(f.getName());
			}
		}
	}

	@FXML
	void save(ActionEvent event) { /// sauvegarder le circuit dans le fichier courant ou bien un nouveau fichier si ce dernier est vide
		workSpace.getChildren().remove(selectionne);
		Stage stage = (Stage) sauvegarder.getScene().getWindow();
		stage.close();
		if (Circuit.getCompUtilises().isEmpty()) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.initOwner(homeWindow);
			a.initStyle(StageStyle.UTILITY);
			a.setContentText("Le circuit est vide, y'a rien à sauvegarder");
			a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
			a.initStyle(StageStyle.UTILITY);
			a.setX(homeWindow.getX()+500);
			a.setY(homeWindow.getY()+250);
			a.initOwner(homeWindow);
			a.showAndWait();
		} else {
			if (fichierCourant == null) {
				final FileChooser fileChooser = new FileChooser();
				fileChooser.setInitialDirectory(
						new File(System.getProperty("user.home"))
						);                 
				fileChooser.getExtensionFilters().addAll(
						new FileChooser.ExtensionFilter("SIM", "*.sim")
						);
				File f = fileChooser.showSaveDialog(homeWindow);
				if (f != null) {
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(f.getAbsolutePath());
					Alert a = new Alert(AlertType.INFORMATION);
					a.initOwner(homeWindow);
					a.initStyle(StageStyle.UTILITY);
					a.setContentText("Le circuit est bien sauvegardé");
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.initStyle(StageStyle.UTILITY);
					a.setX(homeWindow.getX()+500);
					a.setY(homeWindow.getY()+250);
					a.initOwner(homeWindow);
					fichierCourant=f;
					saveLabel.setText(f.getName());
					a.showAndWait();
				}
			} 
			else {
				Sauvegarde sauvegarde = new Sauvegarde(); /// une classe pour sauvegarder le circuit dans un fichier
				sauvegarde.saveCiruit(fichierCourant.getAbsolutePath());
				Alert a = new Alert(AlertType.INFORMATION);
				a.initOwner(homeWindow);
				a.initStyle(StageStyle.UTILITY);
				a.setContentText("Le circuit est bien sauvegardé");
				a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				a.initStyle(StageStyle.UTILITY);
				a.initOwner(homeWindow);
				a.setX(homeWindow.getX()+500);
				a.setY(homeWindow.getY()+250);
				a.showAndWait();

			}
		}
	}

	@FXML
	void saveAs(ActionEvent event) { /// la fonctionnalité de sauvegarder as
		Stage stage = (Stage) sauvComme.getScene().getWindow();
		stage.close();
		if(!Circuit.getCompUtilises().isEmpty())
		{
			workSpace.getChildren().remove(selectionne);
			final FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(
					new File(System.getProperty("user.home"))
					);                 
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("SIM", "*.sim")
					);
			File f = fileChooser.showSaveDialog(homeWindow);
			if (f != null) {
				Sauvegarde sauvegarde = new Sauvegarde();
				sauvegarde.saveCiruit(f.getAbsolutePath());
				fichierCourant = f;
				saveLabel.setText(f.getName());
			}
		}else {
			Alert a = new Alert(AlertType.INFORMATION);
			a.initOwner(homeWindow);
			a.initStyle(StageStyle.UTILITY);
			a.setContentText("Le circuit est vide, rien à sauvegarder");
			a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
			a.show();

		}
	}


	@FXML
	void importer(ActionEvent event) { /// la fonctionalité importer circuit intégré
		Stage stage = (Stage)importer.getScene().getWindow();
		stage.close();
		workSpace.getChildren().remove(selectionne);
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
			Composant cmp; 
			try {
				fichier = new FileInputStream(f.getAbsolutePath());
				oo = new ObjectInputStream(fichier);
				cmp = (Composant)oo.readObject();
				if(cmp.getClass().getSimpleName().equals("CircuitIntegre")) { /// verifier si c'est un circuit intïégré simple
					CircuitIntegre circuitIntegre = (CircuitIntegre)cmp;
					circuitIntegre.setNom(f.getName().substring(0, f.getName().length() - 4));
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
				}else {/// verifier si c'est un circuit intégré sequentiel
					CircuitIntegreSequentiel ciq = (CircuitIntegreSequentiel)cmp;
					ImageView imageView = new ImageView(new Image(ciq.generatePath()));
					ciq.setNom(f.getName().substring(0, f.getName().length() - 4));
					imageView.setLayoutX(10);
					imageView.setLayoutY(10);
					imageView.setFitHeight(imageView.getImage().getHeight());
					imageView.setFitWidth(imageView.getImage().getWidth());
					imageView.setId("CircuitIntegreSequentiel");
					ciq.defaultValue();
					Circuit.ajouterComposant(ciq, imageView);
					workSpace.getChildren().add(imageView);
					ajouterLeGestApresCollage(imageView);
					addAllPolylinesToWorkSpace(ciq.generatePolyline(imageView.getLayoutX(), imageView.getLayoutY()));
					workSpace.getChildren().addAll(ciq.generateCercles(imageView.getLayoutX(), imageView.getLayoutY()));
				}
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
	void encapsulerEtSauvgarder(ActionEvent event) { /// elle est utilisé pour sauvegarder un circuit personnalisé
		workSpace.getChildren().remove(selectionne);
		FileOutputStream fichier ;
		ObjectOutputStream oo = null;
		CircuitIntegre ci = null;
		CircuitIntegreSequentiel ciq = null;
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(homeWindow);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Plusieurs éléments horloges");
		alert.setContentText("Le circuit doit contenir un seul élément horloge ");
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		alert.initStyle(StageStyle.UTILITY);
		alert.setX(homeWindow.getX()+500);
		alert.initOwner(homeWindow);
		alert.setY(homeWindow.getY()+250);
		if (simul) { /// verifier si on est dans le mode de simulation
			if(Circuit.getEntreesCircuit().size() <= 10 && Circuit.getSortiesCircuit().size() <= 10) {
				if(ListTextPin == null && ListTextPin2 == null) { /// verifier si les deux listes entrees / sorties sont vides
					ListTextPin = new ArrayList<Pin>();
					ListText = new ArrayList<Text>();

					ListTextPin2 = new ArrayList<Pin>();
					ListText2 = new ArrayList<Text>();
					try {
						Stage s = (Stage) encapsuler.getScene().getWindow();
						s.close();
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/ReminderCI.fxml"));
						Parent root = fxmlLoader.load();
						Stage stage = new Stage();
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("Remarques");
						stage.setResizable(false);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initOwner(homeWindow);
						stage.centerOnScreen();
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
					encapsuler.setText("  Encapsuler");
					encapsuler.setAlignment(Pos.BASELINE_LEFT);
				}else {
					if(ListTextPin.size() == Circuit.getEntreesCircuit().size() && ListTextPin2.size() == Circuit.getSortiesCircuit().size()) {
						/// verifier si tout les entrees / sorties sont sélectionnées
						if(Circuit.getListeEtages().size()==0 && !horloged) {
							if (Circuit.getEntreesCircuit().size() != 0 && Circuit.getSortiesCircuit().size() != 0) {
								ci = new CircuitIntegre(ListTextPin.size(),ListTextPin2.size(), "CircuitIntegre");
								Collections.reverse(ListTextPin);
								Circuit.tableVerite(ListTextPin,ListTextPin2);
								ci.setTableVerite(Circuit.getTableVerite());
							}
							else {
								Alert a = new Alert(AlertType.ERROR);
								a.initOwner(homeWindow);
								a.initStyle(StageStyle.UTILITY);
								a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
								a.setTitle("Entrée / sortie manquante");
								a.setHeaderText("Pas d'entrées ou de sorties dans le circuit");
								a.setContentText("Il faut qu'il existe au moins une entrée et une sortie pour générer la table de vérité");
								a.initStyle(StageStyle.UTILITY);
								a.showAndWait();
							}
						}else {
							if(!horloged) { /// verifier si le circuit est alimenté par un pin d'horloge
								if(Circuit.occurencePinHorlogee() == 1) { /// si le nombre de pin d'horloge égal à 1
									ciq = new CircuitIntegreSequentiel("CircuitIntegreSequentiel");
									ArrayList<Pin> entreCircuit = new ArrayList<Pin>();
									Pin pin2 = new Pin(true, "Pin");
									Pin sauvPin = null;
									for (Pin pin : ListTextPin) {
										if(!pin.isHorloge()) {
											entreCircuit.add(pin);
										}else {
											Circuit.getEntreesCircuit().remove(pin2);
											pin2.getSorties()[0] = pin.getSorties()[0];
											ciq.setHorloge(pin2);
											sauvPin = pin;
										}
									}
									ArrayList<Composant> arrayList = new ArrayList<Composant>(Circuit.getCompUtilises().keySet());
									arrayList.add(pin2);
									arrayList.remove(sauvPin);
									ciq.setCompUtilises(arrayList);
									ciq.setEntreesCircuit(entreCircuit);
									ciq.setSortiesCircuit(ListTextPin2);
									ciq.setListSouceCte(Circuit.getListSouceCte());
									ciq.setListeEtages(Circuit.getListeEtages());
									ciq.setNbEtages(Circuit.getNbEtages());
									ciq.setFilUtilises(new ArrayList<Fil>(Circuit.getfilUtilises().keySet()));
								}else {
									alert.showAndWait();
								}
							}else { /// le circuit possede une horloge
								if(Circuit.occurencePinHorlogee() == 0) { /// verifier si le nombre de pin égal à zero
									ciq = new CircuitIntegreSequentiel("CircuitIntegreSequentiel");
									ArrayList<Pin> entreCircuit = new ArrayList<Pin>(ListTextPin);
									Pin pinHorloge = new Pin(true, "horloge");
									Circuit.getEntreesCircuit().remove(pinHorloge);
									Circuit.getCompUtilises().remove(pinHorloge);
									Horloge horloge = null;
									for (Composant cmp : Circuit.getCompUtilises().keySet()) {
										if(cmp.getClass().getSimpleName().equals("Horloge")) {
											pinHorloge.getSorties()[0] = cmp.getSorties()[0];
											horloge = (Horloge)cmp;
											break;
										}
									}
									ciq.setHorloge(pinHorloge);
									ArrayList<Composant> arrayList = new ArrayList<Composant>(Circuit.getCompUtilises().keySet());
									arrayList.add(pinHorloge);
									arrayList.remove(horloge);
									ciq.setCompUtilises(arrayList);
									ciq.setEntreesCircuit(entreCircuit);
									ciq.setSortiesCircuit(ListTextPin2);
									ciq.setListeEtages(Circuit.getListeEtages());
									ciq.setNbEtages(Circuit.getNbEtages());
									ciq.setFilUtilises(new ArrayList<Fil>(Circuit.getfilUtilises().keySet()));
									ciq.setListSouceCte(Circuit.getListSouceCte());
								}else {
									alert.showAndWait();
								}
							}
						}
						opacityElements4();

						final FileChooser fileChooser = new FileChooser(); /// sert à la specification de l'emplacement où il faut mettre le circuit
						fileChooser.setInitialDirectory(
								new File(System.getProperty("user.home"))
								);               
						fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("INT", "*.int")
								);/// ajouter l'extension des fichiers
						if (ciq != null || ci != null) {
							File f = fileChooser.showSaveDialog(homeWindow);
							if (f != null) {
								try {
									fichier = new FileOutputStream(f.getAbsolutePath());
									oo = new ObjectOutputStream(fichier);
									if(ci!=null) {
										oo.writeObject(ci);
									}else if(ciq != null){
										oo.writeObject(ciq);
									}
									encapsuler.setText("  Circuit personnalisé");
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
					}else {
						Alert a = new Alert(AlertType.WARNING);
						a.initOwner(homeWindow);
						a.initStyle(StageStyle.UTILITY);
						a.setHeaderText("Circuit intégré erreur");
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.setTitle("Circuit Intégré");
						a.setContentText("Entrée ou sortie non sélectionnée");
						a.initOwner(homeWindow);
						a.initStyle(StageStyle.UTILITY);
						a.setX(homeWindow.getX()+500);
						a.setY(homeWindow.getY()+250);
						a.showAndWait();
					}
				}
			}
			else {
				Alert a = new Alert(AlertType.INFORMATION);
				a.initOwner(homeWindow);
				a.initStyle(StageStyle.UTILITY);
				a.setHeaderText("Circuit intégré");
				a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				a.setTitle("Circuit Intégré");
				a.setContentText("Le circuit contient plus de 10 entrées ou sorties");
				a.initOwner(homeWindow);
				a.initStyle(StageStyle.UTILITY);
				a.showAndWait();
			}
		}
		Stage s = (Stage) encapsuler.getScene().getWindow();
		s.close();
	}

	@FXML
	void chronogramme(ActionEvent event) { /// charger la fenetre du chronogramme
		Stage s = (Stage) chronogramme.getScene().getWindow();
		s.close();
		if (horloged) { /// verifier s'il existe une horloge dans le workspace
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/elementDechronogramme.fxml"));
				Parent root = fxmlLoader.load();
				ElementChronoController controller = fxmlLoader.getController();
				controller.setParentStage(homeWindow);
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.TRANSPARENT);
				scene.setFill(Color.TRANSPARENT);
				stage.initOwner(homeWindow);
				stage.setX(homeWindow.getX()+350);
				stage.setY(homeWindow.getY()+100);
				scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						stage.setOpacity(1.0);
					}
				});
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else { /// afficher une alerte si le plan de travail ne contient pas d'horloge
			Alert a = new Alert(AlertType.ERROR);
			a.initOwner(homeWindow);
			a.initStyle(StageStyle.UTILITY);
			a.setHeaderText("Chronogramme erreur");
			a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
			a.setTitle("Chronogramme");
			a.setContentText( "L'espace de travail ne contient aucune horloge"
					+ " ajouter une horloge à l'éspace pour visualiser le chronogramme");
			transitionDesComposants(clock);
			a.initOwner(homeWindow);
			a.initStyle(StageStyle.UTILITY);
			a.setX(homeWindow.getX()+500);
			a.setY(homeWindow.getY()+250);
			a.showAndWait();
		}
	}

	@FXML
	void tableDeVerite(ActionEvent event) { /// charger la fenetre du table de vérité
		Stage stage1 = (Stage) tableVerite.getScene().getWindow();
		stage1.close();
		if (simul) {
			if(Circuit.getEntreesCircuit().size() != 0 && Circuit.getSortiesCircuit().size() !=0) { /// vérifier s'il existe des entree et sorties
				if(ListTextPin == null && ListTextPin2 == null) {
					ListTextPin = new ArrayList<Pin>();
					ListText = new ArrayList<Text>();
					ListTextPin2 = new ArrayList<Pin>();
					ListText2 = new ArrayList<Text>();
					try {
						Stage s = (Stage) tableVerite.getScene().getWindow();
						s.close();
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Reminder.fxml"));
						Parent root = fxmlLoader.load();
						Stage stage = new Stage();
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("Remarque");
						stage.setResizable(false);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initOwner(homeWindow);
						stage.initStyle(StageStyle.UTILITY);
						stage.setX(homeWindow.getX()+500);
						stage.setY(homeWindow.getY()+250);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
					//Bouton Table de vérité (Mode normal)
					tableVerite.setText("  Générer la table");
					tableVerite.setAlignment(Pos.BASELINE_LEFT);
				}else { //Generer la table et aller vers l'etat normal
					//Generer La table de vérité
					if(ListTextPin.size() != 0 && ListTextPin2.size()!=0) {
						Circuit.tableVerite(ListTextPin,ListTextPin2);
						Circuit.defaultCompValue(); //Tous noir
						Circuit.initialiser();
						//Supprimer les numeros
						//La fenetre de la table de vérité
						try {
							Stage s = (Stage) tableVerite.getScene().getWindow();
							s.close();
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/TableDeVerite.fxml"));
							Parent root = fxmlLoader.load();
							opacityElements4();
							Stage stage = new Stage();
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.setTitle("La table de vérité");
							stage.setX(homeWindow.getX()+350);
							stage.setY(homeWindow.getY()+200);
							stage.setResizable(false);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.getIcons().add(new Image("/homePage_icones/miniLogo.png"));
							stage.initOwner(homeWindow);
							stage.show();
						} catch(Exception e) {
							e.printStackTrace();
						}
						//Bouton Table de vérité (Mode normal)
						tableVerite.setText("  Table de vérité");
						tableVerite.setAlignment(Pos.BASELINE_LEFT);

					}
					else { /// afficher une alerte si l'user n'a sélectionné aucune entrée et sortie
						Alert alert = new Alert(AlertType.ERROR);
						alert.initOwner(homeWindow);
						alert.initStyle(StageStyle.UTILITY);
						alert.setTitle("Liste d'entrées vide");
						alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						alert.setHeaderText("Pas de sélection ");
						alert.setContentText("Il faut sélectionner au moins une entrée et une sortie pour générer la table de vérité !");
						alert.initStyle(StageStyle.UTILITY);
						alert.showAndWait();
					}
				}

			}else {/// afficher une alerte si la liste des entrees ou sorties est vide
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(homeWindow);
				alert.initStyle(StageStyle.UTILITY);
				alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				alert.setTitle("Entrée / sortie manquante");
				alert.setHeaderText("Pas d'entrées ou de sorties dans le circuit");
				alert.setContentText("Il faut qu'il existe au moins une entrée et une sortie pour générer la table de vérité !");
				alert.initStyle(StageStyle.UTILITY);
				alert.showAndWait();
			}
		}
	}

	public void opacityElements4(){ /// enlever la numerotation des pins entree et sorties
		for (Text num : ListText) {
			workSpace.getChildren().remove(num);
		}
		for (Text num : ListText2) {
			workSpace.getChildren().remove(num);
		}

		for (Entry<Composant, ImageView> entry : Circuit.getCompUtilises().entrySet()) {
			entry.getValue().setOpacity(1);
		}
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : Circuit.getfilUtilises().entrySet()) {

			for (InfoPolyline info : entry.getValue()) {
				info.getLinePrincipale().setOpacity(1);
			}
		}

		ListText = null;
		ListTextPin = null;

		ListText2 = null;
		ListTextPin2 = null; 
	}

	@FXML
	void aboutSimulIni(ActionEvent event) { /// afficher la fenetre about simulini 
		workSpace.getChildren().remove(selectionne);
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
			stage.getIcons().add(new Image("/homePage_icones/miniLogo.png"));
			stage.initOwner(s.getOwner());
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void enligne(String l) {//une methode utilisé pour ouvrir un lien dans le navigateur par defaut
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

	public void siteWeb(ActionEvent event) { /// afficher le site web
		enligne("https://simulini.netlify.com");
	}
	
	public void aideEnLigne(ActionEvent event) {/// affiche l'aide en ligne
		enligne("https://simulini.netlify.com/page-2/");
	}

	@FXML
	void annulerParSouris(ActionEvent event) { /// faire l'operation du ctrl + z avec le click droit de la souris
		Stage stage = (Stage)undoParSouris.getScene().getWindow();
		stage.close();
		undoChanges(workSpace);
	}

	@FXML
	void collerParSouris(ActionEvent event) { /// coller un composant en utilisant la souris
		copyMouse = true;
		pastButton = false;
		Stage s = (Stage)collerParSouris.getScene().getWindow();
		s.close();
	}

	void tracerLagrill() { /// tracer la grille du workspace
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

	private void undoChanges(AnchorPane workSpace) /// la fonction qui traite les operation du ctrl + z
	{
		if(! undoDeque.isEmpty()) /// si la historique est vide
		{
			workSpace.getChildren().remove(selectionne);
			Donnes sauveGarde;
			sauveGarde= undoDeque.removeFirst();
			while(sauveGarde.isSupprime() && !undoDeque.isEmpty() ) { /// pour supprimer des problemes qui peuvent subvenir 
				sauveGarde= undoDeque.removeFirst();
			}

			if(!sauveGarde.isSupprime()) /// verifier si la sauvegarde n'est pas supprimé
			{
				switch(sauveGarde.getTypeDaction())
				{
				case Mouvement : /// pour le mouvement d'un composant
				{
					ctrlz = true;
					switchingZ = sauveGarde.getSwitching();;
					refrechLists(sauveGarde.getComposantCommeImage());
					SupprimerPoint();
					ImageView imageView = sauveGarde.getComposantCommeImage();
					imageView.setLayoutX(sauveGarde.getPosX());
					imageView.setLayoutY(sauveGarde.getPosY());
					updatePolyline(imageView);
					Composant composant = Circuit.getCompFromImage(imageView);
					if (composant.getClass().equals(CircuitIntegre.class)) {
						((CircuitIntegre)composant).resetCirclesPosition(imageView.getLayoutX(), imageView.getLayoutY());
					}
					else if (composant.getClass().equals(CircuitIntegreSequentiel.class)) {
						((CircuitIntegreSequentiel)composant).resetCirclesPosition(imageView.getLayoutX(), imageView.getLayoutY());
					}
					ctrlz = false;
				}break;
				case Creation : /// la creation d'un composant ( déposé la 1iere fois le composant dans le workspace)
				{
					workSpace.getChildren().remove(sauveGarde.getComposantCommeImage());
					ArrayList<Polyline> lineListe= Circuit.supprimerComp(sauveGarde.getComposant());
					for(Polyline line : lineListe)
						workSpace.getChildren().remove(line);

					if(sauveGarde.getComposant().getClass().getSimpleName().equals("Horloge")) {
						horloged = false;
						horlogeDeCercuit = null;
					}
				}break;
				case Modification : /// la modification des propriétés d'un composant
				{
					ImageView imageDeComposant= sauveGarde.getComposantCommeImage();
					Composant composant= Circuit.getCompFromImage( imageDeComposant);
					if(!composant.getClass().equals(Horloge.class)) {
						imageDeComposant.setImage(sauveGarde.getImage());
						imageDeComposant.setFitHeight(sauveGarde.getImage().getHeight());
						imageDeComposant.setFitWidth(imageDeComposant.getImage().getWidth());
						removeAllPolylinesFromWorkSpace(Circuit.supprimerAllPolylinesForCompounent(composant));
						composant.setNombreEntree(sauveGarde.getNombreDesEntrees());
						composant.setNombreSortie(sauveGarde.getNombreDesSorties());
						composant.getLesCoordonnees().setNbCordEntree(sauveGarde.getNombreDesEntrees());
						composant.getLesCoordonnees().setNbCordSorties(sauveGarde.getNombreDesSorties());
						if (composant.getClass().equals(Multiplexeur.class)) {
							((Multiplexeur)composant).setNbCommande(sauveGarde.getNombreDeCommandes());
							composant.getLesCoordonnees().setNbCordCommandes(sauveGarde.getNombreDeCommandes());
						}
						else if(composant.getClass().equals(Demultiplexeur.class)){
							((Demultiplexeur)composant).setNbCommande(sauveGarde.getNombreDeCommandes());
							composant.getLesCoordonnees().setNbCordCommandes(sauveGarde.getNombreDeCommandes());
						}
						else if(composant.getClass().equals(Pin.class)) {
							if (sauveGarde.getTypePin()){
								if (! ((Pin)composant).getInput()) {
									Circuit.getEntreesCircuit().add((Pin)composant);
									Circuit.getSortiesCircuit().remove((Pin)composant);
								}
							}
							else {
								if (((Pin)composant).getInput()) {
									Circuit.getEntreesCircuit().remove((Pin)composant);
									Circuit.getSortiesCircuit().add((Pin)composant);
								}
							}
							((Pin)composant).setInput(sauveGarde.getTypePin());
						}
						else if(composant.getClass().isAssignableFrom(Sequentiels.class)) ((Sequentiels)composant).setFront(sauveGarde.getFront());
						addAllPolylinesToWorkSpace(composant.generatePolyline(imageDeComposant.getLayoutX(), imageDeComposant.getLayoutY()));
					}
					else Horloge.setTemps(sauveGarde.getFrequence());
				}break;
				case Supression: /// la suppression d'un composant
				{
					ImageView imageDeComposant= sauveGarde.getComposantCommeImage();
					workSpace.getChildren().add(imageDeComposant);
					imageDeComposant.setLayoutX(sauveGarde.getPosX());
					imageDeComposant.setLayoutY(sauveGarde.getPosY());
					Circuit.ajouterComposant(sauveGarde.getComposant(), imageDeComposant);
					ArrayList<Polyline> polyline = Circuit.getCompFromImage(imageDeComposant).generatePolyline(imageDeComposant.getLayoutX(), imageDeComposant.getLayoutY());
					addAllPolylinesToWorkSpace(polyline);
					sauveGarde.getComposant().relierANouveau();
					if (sauveGarde.getComposant().getClass().equals(CircuitIntegre.class)) {
						ArrayList<Circle> resCircles = ((CircuitIntegre)sauveGarde.getComposant()).generateCercles(imageDeComposant.getLayoutX(), imageDeComposant.getLayoutY());
						for (Circle circle : resCircles) {
							workSpace.getChildren().add(circle);
						}
					}
					else if (sauveGarde.getComposant().getClass().equals(CircuitIntegreSequentiel.class)) {
						ArrayList<Circle> resCircles = ((CircuitIntegreSequentiel)sauveGarde.getComposant()).generateCercles(imageDeComposant.getLayoutX(), imageDeComposant.getLayoutY());
						for (Circle circle : resCircles) {
							workSpace.getChildren().add(circle);
						}
					}else if(sauveGarde.getComposant().getClass().getSimpleName().equals("Horloge")) {
						horloged = true;
						horlogeDeCercuit = sauveGarde.getComposantCommeImage();
					}
					else if(sauveGarde.getComposant().getClass().getSimpleName().equals("Pin"))
					{
						Pin pin = (Pin)sauveGarde.getComposant();
						if (pin.isInput()) {
							Circuit.getEntreesCircuit().add(pin);
						}
						else {
							Circuit.getSortiesCircuit().add(pin);
						}
					}
					else if(sauveGarde.getComposant().getClass().getSimpleName().equals("SourceConstante")){
						Circuit.getListSouceCte().add((SourceConstante)sauveGarde.getComposant());
					}
					else if(sauveGarde.getComposant().getClass().getSuperclass().equals(Sequentiels.class) || sauveGarde.getComposant().getClass().getSuperclass().equals(Bascule.class)){
						if (((Sequentiels)sauveGarde.getComposant()).getEntreeHorloge() != null) {
							Circuit.getListeEtages().add((Sequentiels)sauveGarde.getComposant());
						}
					}
				}break;
				case SuppressionFil :{ /// la suppression d'un fil
					InfoPolyline infoPolyline = sauveGarde.getInfoPolyline();
					Fil filSortieFil =  sauveGarde.getFil();
					supprimerSauvegarde(infoPolyline, sauveGarde.getParent(), filSortieFil);

				}break;
				case CreationFil: /// la creation d'un fil
				{
					ClickDroitFilController.setPane(workSpace);
					InfoPolyline infoLine = Circuit.getInfoPolylineFromPolyline(sauveGarde.getParent());
					//InfoPolyline infoLine = sauveGarde.getInfoPolyline();
					ClickDroitFilController.supprimer(infoLine);
				}break;
				case SuppressionToutFil :{ /// la suppression de tout les fils
					ArrayList<InfoPolyline> arrayList = sauveGarde.getArrayListInfoPoly();
					ArrayList<Polyline> arrayParent= sauveGarde.getListPolyParent();
					Fil filSortieFil =  sauveGarde.getFil();
					int size =  arrayParent.size();
					for (int i = 0; i < size; i++) {
						supprimerSauvegarde(arrayList.get(i+1), arrayParent.get(i), filSortieFil);
					}
				}break;
				case Copier :{ /// la copier d'un composant
					removeAllPolylinesFromWorkSpace(Circuit.supprimerComp(sauveGarde.getComposant()));
					workSpace.getChildren().remove(sauveGarde.getComposantCommeImage());
					if(sauveGarde.getComposantCommeImage().getId().equals("clock"))
					{
						HomeController.horloged =false;
						HomeController.horlogeDeCercuit =null; 
					}
					else if (sauveGarde.getComposantCommeImage().getId().equals("CircuitIntegre")) {
						ArrayList<Circle> arrayList = ((CircuitIntegre)cmp).getListeCercles();
						for (Circle circle : arrayList) {
							workSpace.getChildren().remove(circle);
						}
					} 
					else if (sauveGarde.getComposantCommeImage().getId().equals("CircuitIntegreSequentiel")) {
						ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)cmp).getListeCercles();
						for (Circle circle : arrayList) {
							workSpace.getChildren().remove(circle);
						}
					}
				}break;
				case Rotation :{ /// la rotation d'un composant
					ImageView img = sauveGarde.getComposantCommeImage();
					Composant composant = sauveGarde.getComposant(); 
					composant.setDirection(sauveGarde.getRotation());
					removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(composant.getSorties()[0]));
					Image image = new Image(composant.generatePath());
					img.setImage(image);
					img.setFitHeight(image.getHeight());
					img.setFitWidth(image.getWidth());
					addAllPolylinesToWorkSpace(composant.generatePolyline(img.getLayoutX(), img.getLayoutY()));
				}
				default:
					break;

				}
			}
		}
	}
	public static void sauveGarderModification() /// utilisé pour sauvegarder des modifications dans les proprietés d'un compsant 
	{
		Composant composant=Circuit.getCompFromImage(elementAmodifier);
		Donnes sauveGarde= new Donnes();

		sauveGarde.setTypeDaction(Actions.Modification);
		sauveGarde.setComposantCommeImage(elementAmodifier);
		sauveGarde.setImage(elementAmodifier.getImage());
		sauveGarde.setNombreDesEntrees(composant.getNombreEntree());
		sauveGarde.setNombreDesSorties(composant.getNombreSortie());
		if (composant.getClass().getSimpleName().equals("Multiplexeur")) { /// sauvegarde du nombre des commandes dans le cas de mux / dmx
			sauveGarde.setNombreDeCommandes(((Multiplexeur)composant).getNbCommande()); 
		}
		else if(composant.getClass().getSimpleName().equals("Demultiplexeur")){
			sauveGarde.setNombreDeCommandes(((Demultiplexeur)composant).getNbCommande()); 
		}
		else if(composant.getClass().equals(Pin.class)) sauveGarde.setTypePin(((Pin)composant).isInput());
		else if(composant.getClass().isAssignableFrom(Sequentiels.class)) sauveGarde.setFront(((Sequentiels)composant).getFront());
		else if(composant.getClass().equals(Horloge.class)) sauveGarde.setFrequence(Horloge.getTemps());
		undoDeque.addFirst(sauveGarde);
		elementAmodifier=null;
	}

	public void captureEcran(String path) { /// faire une capture du circuit
		WritableImage image = workSpace.snapshot(new SnapshotParameters(), null);
		File file = new File(path);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		}catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void sauveGarderSupression() /// pour sauvegarder la suppression d'un composant
	{
		Donnes sauveGarde= new Donnes();
		sauveGarde.setTypeDaction(Actions.Supression);
		sauveGarde.setComposantCommeImage(elementAsuprimer);
		sauveGarde.setComposant(Circuit.getCompFromImage(elementAsuprimer));
		sauveGarde.setPosX(elementAsuprimer.getLayoutX());
		sauveGarde.setPosY(elementAsuprimer.getLayoutY());
		undoDeque.addFirst(sauveGarde);
	}


	private void updatePolyline(ImageView eleementAdrager) { /// refrescher les polylines utilisés au cas où le composant c'est déplacé
		Composant cmp = Circuit.getCompFromImage(eleementAdrager);
		boolean relocate = false;
		int i = 0, j = 0 ;
		Coordonnees crdDebut;
		Polyline p;
		while(i < cmp.getNombreSortie()) { /// repositionner les polylines de sorties
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
		while(i < cmp.getNombreEntree()) {/// repositionner les polylines des entrees
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
		while(i < cmp.getLesCoordonnees().getNbCordCommandes()) {/// repositionner les polylines des commandes
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
		if(cmp.getLesCoordonnees().getCordHorloge() != null ) {/// repositionner le polyline de l'horloge
			if( ((Sequentiels)cmp).getEntreeHorloge() != null) {
				p = listEntrees.get(j);
				switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
				j++;
				crdDebut = cmp.getLesCoordonnees().coordReelesHorloge(eleementAdrager);

				p = tracerEntrerApresCollage(p, crdDebut, relocate);
				Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
			}
		}
		if(cmp.getLesCoordonnees().getCordClear() != null ) {/// repositionner le polyline du clear
			if(((Sequentiels)cmp).getClear().getSource() != null) {
				p = listEntrees.get(j);
				switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
				j++;
				crdDebut = cmp.getLesCoordonnees().coordReelesClear(eleementAdrager);

				p = tracerEntrerApresCollage(p, crdDebut, relocate);
				Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
			}
		}
		if(cmp.getLesCoordonnees().getCordPreset() != null ) {/// repositionner le polyline du preset
			if(((Bascule)cmp).getPreset().getSource() != null){
				p = listEntrees.get(j);
				switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
				j++;
				crdDebut = cmp.getLesCoordonnees().coordReelesPreset(eleementAdrager);
				p = tracerEntrerApresCollage(p, crdDebut, relocate);
				Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
			}
		}
		if(cmp.getLesCoordonnees().getCordLoad() != null ) {/// repositionner le polyline du load
			if(((Sequentiels)cmp).getLoad().getSource()!= null) {
				p = listEntrees.get(j);
				switching = Circuit.getInfoPolylineFromPolyline(p).getSwitching();
				j++;
				crdDebut = cmp.getLesCoordonnees().coordReelesLoad(eleementAdrager);
				p = tracerEntrerApresCollage(p, crdDebut, relocate);
				Circuit.getInfoPolylineFromPolyline(p).setSwitching(switching);
			}
		}
	}

	public void ajouterElements() { /// cette fonction est utilisé pour ajouter les composants une fois que le circuit a été½ chargé½
		for (ImageView img : Circuit.getCompUtilises().values()) {
			if (img.getId().equals("clock")) { /// verifier s'il ya une horloge dans le circuit à charger
				horloged = true;
				horlogeDeCercuit = img;
			}
			else if(img.getId().equals("CircuitIntegre")) {
				ArrayList<Circle> arrayList = ((CircuitIntegre)Circuit.getCompFromImage(img)).generateCercles(img.getLayoutX(), img.getLayoutY());
				for (Circle circle : arrayList) {
					workSpace.getChildren().add(circle);
				}
			}
			else if(img.getId().equals("CircuitIntegreSequentiel")) {
				ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)Circuit.getCompFromImage(img)).generateCercles(img.getLayoutX(), img.getLayoutY());
				for (Circle circle : arrayList) {
					workSpace.getChildren().add(circle);
				}
			}

			workSpace.getChildren().add(img);
			ajouterLeGestApresCollage(img);
		}
		Polyline principale;
		Polyline parent;
		for (ArrayList<InfoPolyline> list : Circuit.getfilUtilises().values()) { /// retracer les polylines 
			for (InfoPolyline infoPolyline : list) {
				principale = initialser(0, 0);
				principale.getPoints().clear();
				principale.getPoints().addAll(infoPolyline.getNoeudLinePrincipale());
				Polyline polySauv=containsPolyInSauv(principale);
				if ( polySauv== null) {
					workSpace.getChildren().add(0,principale);
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
						workSpace.getChildren().add(0,parent);
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

	public Polyline containsPolyInSauv(Polyline polyline) { /// savoir si il y'a un polyline qui est égal à celui passé comme parametre
		for (Polyline polyline2 : sauv) {
			if (equalsPolylines(polyline, polyline2)) {
				return polyline2;
			}
		}
		return null;
	}

	public boolean equalsPolylines(Polyline line1,Polyline line2) { /// elle verifier si deux polylines sont egaux a notre maniere

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

	public void addPoints() { /// ajouter les points nécessaires pour que le polylines fonctionne correctement
		Polyline line;
		int i = 0,size = 0;
		if(insererNoedDebut) {
			for( i = 0; i < listSorties.size();i++){
				line = listSorties.get(i);
				if(Circuit.getListFromPolyline(line).size()>1 || Circuit.getInfoPolylineFromPolyline(line).isRelier()) {
					line.getPoints().add(2,line.getPoints().get(3));
					line.getPoints().add(2,line.getPoints().get(3));
					if(i == 0) {
						switchingZ = Circuit.getInfoPolylineFromPolyline(line).getSwitching();
					}
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

	public void refrechLists(ImageView eleementAdrager) { /// refrcher la liste des polylines des differentes connexions du composant
		int size = 0;
		Composant cmp = Circuit.getCompFromImage(eleementAdrager);
		Polyline line ;
		listEntrees.clear();
		Coordonnees crdDebut = new Coordonnees(0,0);
		int i = 0;
		for(i = 0; i < cmp.getNombreEntree();i++){ /// refrecher la liste des polylines des entrees
			if(cmp.getEntrees()[i] != null) {
				crdDebut = cmp.getLesCoordonnees().coordReelesEntrees(eleementAdrager, i);
				line = cmp.getEntrees()[i].polylineParPoint(crdDebut);
				listEntrees.add(line);
			}
		}
		if(cmp.getLesCoordonnees().getNbCordCommandes() != 0) { /// refrecher la liste des polylines des sorties
			for(i = 0; i < cmp.getLesCoordonnees().getNbCordCommandes();i++){
				if( ((Combinatoires)cmp).getCommande()[i] != null) {
					crdDebut = cmp.getLesCoordonnees().coordReelesCommande(eleementAdrager, i);
					line = ((Combinatoires)cmp).getCommande()[i].polylineParPoint(crdDebut);
					listEntrees.add(line);
				}
			}
		}
		if(cmp.getLesCoordonnees().getCordHorloge() != null ) { /// refrecher le polyline de l'horloge
			if( ((Sequentiels)cmp).getEntreeHorloge() != null) {
				crdDebut = cmp.getLesCoordonnees().coordReelesHorloge(eleementAdrager);
				line = ((Sequentiels)cmp).getEntreeHorloge().polylineParPoint(crdDebut);
				listEntrees.add(line);
			}

		}
		if(cmp.getLesCoordonnees().getCordClear() != null ) { /// refrecher le polyline du clear
			if(((Sequentiels)cmp).getClear().getSource() != null) {
				crdDebut = cmp.getLesCoordonnees().coordReelesClear(eleementAdrager);
				line = ((Sequentiels)cmp).getClear().polylineParPoint(crdDebut);
				listEntrees.add(line);
			}
		}
		if(cmp.getLesCoordonnees().getCordPreset() != null) { /// refrecher le polyline du preset
			if(((Bascule)cmp).getPreset().getSource() != null){
				crdDebut = cmp.getLesCoordonnees().coordReelesPreset(eleementAdrager);
				line = ((Bascule)cmp).getPreset().polylineParPoint(crdDebut);
				listEntrees.add(line);
			}
		}
		if(cmp.getLesCoordonnees().getCordLoad() != null ) { /// refrecher le polyline du load
			if(((Sequentiels)cmp).getLoad().getSource() != null) {
				crdDebut = cmp.getLesCoordonnees().coordReelesLoad(eleementAdrager);
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

	public static void remplacerLineUndoDeque(Polyline line1,Polyline line2) { /// remplacer une polyline par un autre dans la pile de l'historique dans le cas de creation de fil
		for ( Donnes donnes : undoDeque) {
			if(donnes.getTypeDaction().equals(Actions.CreationFil)) {
				if(donnes.getParent() == line1) {
					donnes.setParent(line2);
				}
			}
		}
	}
	public static void remplacerLineUndoDequeSupprimer(Polyline line1,Polyline line2) { /// remplacer une polyline par un autre dans la pile de l'historique dans le cas de suppression de fil
		for ( Donnes donnes : undoDeque) {
			if(donnes.getTypeDaction().equals(Actions.SuppressionFil)) {
				if(donnes.getInfoPolyline().getLineParent() == line1) {
					donnes.getInfoPolyline().setLineParent(line2);
				}
			}else if (donnes.getTypeDaction().equals(Actions.SuppressionToutFil)) {
				for (InfoPolyline info : donnes.getArrayListInfoPoly()) {
					if(info.getLineParent() == line1) {
						info.setLineParent(line2);
					}
				}
			}
		}
	}

	public void supprimerSauvegarde(InfoPolyline infoPolyline,Polyline paren,Fil filSortieFil) { /// utiliser pour supprimmer des cas qui posent erreurs dans l'operation du ctrl + z
		if (infoPolyline.getLineParent() != null) {
			InfoPolyline parent = Circuit.getInfoPolylineFromPolyline(infoPolyline.getLineParent());
			if(parent == null)
				return;
			parent.setNbFils(parent.getNbFils()+1);
			infoPolyline.getLineParent().getPoints().clear();
			infoPolyline.getLineParent().getPoints().addAll(paren.getPoints());
			ArrayList<InfoPolyline> resArrayList =  Circuit.getListFromPolyline(parent.getLinePrincipale());
			resArrayList.add(resArrayList.indexOf(parent)+1,infoPolyline);
		}
		workSpace.getChildren().add(0,infoPolyline.getLinePrincipale());
		if (infoPolyline.isRelier()) {
			Composant source = filSortieFil.getSource();
			int sortie = source.numCmpSorties(filSortieFil);
			entree = infoPolyline.getEntre();
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

	private void ajouterLeGestLabel( ImageView node) { /// pour ajouter le gestion du label ajoutés
		node.setOnMouseEntered(new EventHandler<MouseEvent>() { /// ajouter un effet si on rentre dans le label
			@Override
			public void handle(MouseEvent e) {
				node.setCursor(Cursor.HAND);
				elemanrsMapFillMap.get(node).setStyle("-fx-background-color:#000000;-fx-background-radius:10;-fx-effect:dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0.5, 2.0, 2.0)");
				transitionDesComposants(node);
			}

		});
		node.setOnMouseExited(new EventHandler<MouseEvent>() { /// rendre les valeurs par defaut si on sort du label
			@Override
			public void handle(MouseEvent e) {
				elemanrsMapFillMap.get(node).setStyle("-fx-background-color:#303337;-fx-background-radius:10;-fx-effect:dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0.5, 2.0, 2.0)");
				node.setCursor(Cursor.DEFAULT);
			}
		});


		node.setOnMousePressed(new EventHandler<MouseEvent>() { /// gestion du click sur le label
			public void handle(MouseEvent e) {
				ImageView dragImageView=new ImageView();
				dragImageView.setMouseTransparent(true);
				node.setMouseTransparent(true);
				node.setCursor(Cursor.CLOSED_HAND);	            
				node.setOnDragDetected(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {                   
						SnapshotParameters snapParams = new SnapshotParameters();
						snapParams.setFill(Color.TRANSPARENT);
						dragImageView.setImage(node.snapshot(snapParams, null));
						workSpace.getChildren().add(dragImageView);
						dragImageView.startFullDrag();
						e.consume();
					}
				});

				node.setOnMouseDragged(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						Point2D localPoint = workSpace.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));
						dragImageView.relocate(
								(int)(localPoint.getX() - dragImageView.getBoundsInLocal().getWidth() / 2),
								(int)(localPoint.getY() - dragImageView.getBoundsInLocal().getHeight() / 2)
								);
						e.consume();
					}
				});

				node.setOnMouseReleased(new EventHandler<MouseEvent>() { /// ajouter un une gestion dans la deposiion du label
					public void handle(MouseEvent e) {
						if( dragImageView.getLayoutX() > 0 &&dragImageView.getLayoutY() > 0&& (e.getSceneX() +( dragImageView.getBoundsInLocal().getWidth()) / 2) < 1310 && e.getSceneY() + (dragImageView.getBoundsInLocal().getHeight() / 2)<700 && ! intersictionLabel(dragImageView))

						{	    	        			    	        	
							EditableDraggableText text=new EditableDraggableText(dragImageView.getLayoutX(),dragImageView.getLayoutY(),"T");
							workSpace.getChildren().add(text);
							text.getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() { ///gestion du click long pour ecrire dans le label
								@Override
								public void handle(MouseEvent arg0) {
									if(clickDroitLabel != null) clickDroitLabel.close();
									if(clickDroitFenetre != null) clickDroitFenetre.close();
									if(clickSouris2 != null) clickSouris2.close();
									if(arg0.getButton().equals(MouseButton.SECONDARY))
									{
										if (arg0.getScreenX() > 1145) { /// une gestion d'affichage de la fenetre du click droit du label
											if (arg0.getScreenY() > 700) {
												clickDroitLabel = new ClickDroitLabel((TextField)text.getChildren().get(0),arg0.getScreenX()-150,arg0.getScreenY()-50,workSpace,homeWindow);
											}
											else {
												clickDroitLabel = new ClickDroitLabel((TextField)text.getChildren().get(0),arg0.getScreenX()-150,arg0.getScreenY(),workSpace,homeWindow);
											}
										}
										else {
											if (arg0.getScreenY() > 700) {
												clickDroitLabel = new ClickDroitLabel((TextField)text.getChildren().get(0),arg0.getScreenX(),arg0.getScreenY()-50,workSpace,homeWindow);
											}
											else {
												clickDroitLabel = new ClickDroitLabel((TextField)text.getChildren().get(0),arg0.getScreenX(),arg0.getScreenY(),workSpace,homeWindow);
											}
										}
										arg0.consume();
									}
								}
							});
						}
						dragImageView.setMouseTransparent(false);
						node.setMouseTransparent(false);
						node.setCursor(Cursor.DEFAULT);
						workSpace.getChildren().remove(dragImageView);
					}
				});
			}
		});
	}

	private boolean intersictionLabel(ImageView imgCmp) { /// savoir s'il ya une intersection entre des labels
		for(ImageView image : Circuit.getCompUtilises().values())
		{
			if(imgCmp.getBoundsInParent().intersects(image.getBoundsInParent()))
				return true;
		}
		return false;
	}

	public void closeRightWindows() { /// fermer les fenetres ouvertes du click droit
		if (fichierFenetre != null) {
			fichierFenetre.close();
		}
		if (editionFenetre != null) {
			editionFenetre.close();
		}
		if (aideFenetre != null) {
			aideFenetre.close();
		}
		if (affichage != null) {
			affichageFenetre.close();
		}
	}

	public void SupprimerPoint() { /// supprimer un point s'il pose un probleme
		Polyline line;
		int i = 0;
		for( i = 0; i < listSorties.size();i++){
			line = listSorties.get(i);
			if(Circuit.getListFromPolyline(line).size()>1) { 
				Coordonnees crd = new Coordonnees(line.getPoints().get(4),line.getPoints().get(5));
				if(nbOccPoint(line, crd.getX(), crd.getY())==1) {
					line.getPoints().remove(0);
					line.getPoints().remove(0);
				}
			}
		}
	}

	public static void sauveGardeCopier(ImageView imageView , Composant composant) { /// faire une sauvegarde des infos nécéssaires pour le ctrl + z de la copie
		Donnes donnes = new Donnes();
		donnes.setTypeDaction(Actions.Copier);
		donnes.setComposantCommeImage(imageView);
		donnes.setComposant(composant);
		undoDeque.addFirst(donnes);
	}

	public static void sauveGarderRotation(Composant composant,ImageView imageView,int rotation) { /// faire une sauvegarde des infos nécéssaires pour le ctrl + z de la rotation
		Donnes donnes = new Donnes();
		HomeController.supprimerDequeFilProbleme(composant);
		donnes.setTypeDaction(Actions.Rotation);
		donnes.setComposant(composant);
		donnes.setRotation(rotation);
		donnes.setComposantCommeImage(imageView);
		undoDeque.addFirst(donnes);
	}

	public void ajouterLeGestWindow() { /// pour eviter les problemes dans le positionnement des fenetres
		homeWindow.xProperty().addListener((obs, oldVal, newVal) -> {
			if(clickDroitFenetre != null) clickDroitFenetre.close();
			if(clickDroitFilFenetre != null) clickDroitFilFenetre.close();
			if(clickSouris2 != null) clickSouris2.close();
			if (clickDroitLabel != null) clickDroitLabel.close();
			fichierFenetre.setX(newVal.doubleValue()+1055);
			editionFenetre.setX(newVal.doubleValue()+1055);
			affichageFenetre.setX(newVal.doubleValue()+1055);
			aideFenetre.setX(newVal.doubleValue()+1055);
		});
		homeWindow.yProperty().addListener((obs, oldVal, newVal) -> {
			if(clickDroitFenetre != null) clickDroitFenetre.close();
			if(clickDroitFilFenetre != null) clickDroitFilFenetre.close();
			if(clickSouris2 != null) clickSouris2.close();
			if (clickDroitLabel != null) clickDroitLabel.close();
			fichierFenetre.setY(newVal.doubleValue()+50);
			editionFenetre.setY(newVal.doubleValue()+115);
			affichageFenetre.setY(newVal.doubleValue()+255);
			aideFenetre.setY(newVal.doubleValue()+300);
		});
	}

	public static void supprimerDequeFilProbleme(Composant composant){ /// suppression des problemes des fils dans l'historique
		Donnes donnes;
		boolean boucle = true;
		for (Fil fil : composant.getSorties()) {
			donnes = new Donnes();
			donnes.setFil(fil);
			while(boucle) {
				boucle = undoDeque.remove(donnes);
			}
		}
	}

	public void remplireNomPinEtAfficher() { /// afficher les noms des pins dans le mode de simulation
		Text text;
		ImageView imageView;
		for (Composant composant : Circuit.getCompUtilises().keySet()) {
			if (composant.getClass().equals(Pin.class)) {
				Pin pin = (Pin)composant;
				imageView = Circuit.getImageFromComp(pin);
				text = new Text(pin.getNom());
				text.setFont(Font.font("Calisto MT",FontWeight.NORMAL,20));
				text.setFill(Color.web("#e0e0d1"));
				if (pin.getInput()) {
					if (pin.getDirection() != 3) {
						text.setLayoutX(imageView.getLayoutX());
						text.setLayoutY(imageView.getLayoutY()-3);
					}
					else {
						text.setLayoutX(imageView.getLayoutX());
						text.setLayoutY(imageView.getLayoutY()+imageView.getFitHeight() + 18);
					}
				}
				else {
					if (pin.getDirection() == 1) {
						text.setLayoutX(imageView.getLayoutX());
						text.setLayoutY(imageView.getLayoutY()-3);
					}
					else {
						text.setLayoutX(imageView.getLayoutX());
						text.setLayoutY(imageView.getLayoutY()+imageView.getFitHeight() + 18);
					}
				}
				listDesNoms.add(text);
				workSpace.getChildren().add(text);
			}
			else if (composant.getClass().equals(CircuitIntegre.class)) {
				imageView = Circuit.getImageFromComp(composant);
				text = new Text(composant.getNom());
				text.setFont(Font.font("Calisto MT",FontWeight.NORMAL,20));
				text.setFill(Color.web("#e0e0d1"));
				text.setLayoutX(imageView.getLayoutX());
				text.setLayoutY(imageView.getLayoutY()-4);
				listDesNoms.add(text);
				workSpace.getChildren().add(text);
			}
			else if (composant.getClass().equals(CircuitIntegreSequentiel.class)) {
				imageView = Circuit.getImageFromComp(composant);
				text = new Text(composant.getNom());
				text.setFont(Font.font("Calisto MT",FontWeight.NORMAL,20));
				text.setFill(Color.web("#e0e0d1"));
				text.setLayoutX(imageView.getLayoutX());
				text.setLayoutY(imageView.getLayoutY()-4);
				listDesNoms.add(text);
				workSpace.getChildren().add(text);
			}
		}
	}

	public void remouveNomPin() { /// supprimer le nom des pin apres le retoure dans le mode de creation de circuit
		workSpace.getChildren().removeAll(listDesNoms);
		listDesNoms.clear();
	}

	public void hideButtonsFile() { /// desactiver des boutons et elle est utilisï¿½ dans le mode de simulation
		for (Button button : btnsToHide) {
			button.setDisable(true);
			button.setOpacity(0.4);
		}
	}

	public void showButtonsFile() { /// activer les boutons désactivé lors de la simulation
		for (Button button : btnsToHide) {
			button.setDisable(false);
			button.setOpacity(1);
		}
	}

	public void selectionne(ImageView image) { /// afficher un cadre au tour d'un composant sélectionné
		selectionne.setStroke(Color.DARKGRAY);
		selectionne.setStrokeWidth(2);
		selectionne.getPoints().clear();
		selectionne.getPoints().addAll(image.getLayoutX()-10,image.getLayoutY()-10);
		selectionne.getPoints().addAll(image.getLayoutX()+image.getFitWidth()+10,image.getLayoutY()-10);
		selectionne.getPoints().addAll(image.getLayoutX()+image.getFitWidth()+10,image.getLayoutY()+image.getFitHeight()+10);
		selectionne.getPoints().addAll(image.getLayoutX()-10,image.getLayoutY()+image.getFitHeight()+10);
		selectionne.getPoints().addAll(image.getLayoutX()-10,image.getLayoutY()-10);
		DropShadow sh = new DropShadow();
		sh.setOffsetX(0);
		sh.setOffsetY(3);
		selectionne.setEffect(sh);
		if(!workSpace.getChildren().contains(selectionne)) {
			workSpace.getChildren().add(selectionne);
		}
	}
	public static void stuckOverFlowAlert() {
		Alert a = new Alert(AlertType.ERROR);
		a.initOwner(homeWindow);
		a.initStyle(StageStyle.UTILITY);
		a.setHeaderText("Boucle infinie");
		//a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		a.setTitle("Boucle infinie");
		a.setContentText( "Le circuit entraine une boucle infinie !");
		a.showAndWait();
	}
	public void importCircuit() {
		if (fileToUpload != null) {
			int lengthFile = fileToUpload.length();
			String extension = fileToUpload.substring(lengthFile- 3,lengthFile);
			if (extension.equals("sim")) {
				Sauvegarde.loadCiruit(fileToUpload);
				ajouterElements();
				fichierCourant = new File(fileToUpload);
				saveLabel.setText(fichierCourant.getName());
			}
			else if(extension.equals("int")) {
				FileInputStream fichier ;
				ObjectInputStream oo = null;
				Composant cmp; 
				File f = new File(fileToUpload);
				try {
					fichier = new FileInputStream(fileToUpload);
					oo = new ObjectInputStream(fichier);
					cmp = (Composant)oo.readObject();
					if(cmp.getClass().getSimpleName().equals("CircuitIntegre")) { /// verifier si c'est un circuit intïégré simple
						CircuitIntegre circuitIntegre = (CircuitIntegre)cmp;
						circuitIntegre.setNom(f.getName().substring(0, f.getName().length() - 4));
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
					}else {/// verifier si c'est un circuit intégré sequentiel
						CircuitIntegreSequentiel ciq = (CircuitIntegreSequentiel)cmp;
						ImageView imageView = new ImageView(new Image(ciq.generatePath()));
						ciq.setNom(f.getName().substring(0, f.getName().length() - 4));
						imageView.setLayoutX(10);
						imageView.setLayoutY(10);
						imageView.setFitHeight(imageView.getImage().getHeight());
						imageView.setFitWidth(imageView.getImage().getWidth());
						imageView.setId("CircuitIntegreSequentiel");
						ciq.defaultValue();
						Circuit.ajouterComposant(ciq, imageView);
						workSpace.getChildren().add(imageView);
						ajouterLeGestApresCollage(imageView);
						addAllPolylinesToWorkSpace(ciq.generatePolyline(imageView.getLayoutX(), imageView.getLayoutY()));
						workSpace.getChildren().addAll(ciq.generateCercles(imageView.getLayoutX(), imageView.getLayoutY()));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			else {
				Alert a = new Alert(AlertType.WARNING);
				a.setContentText("Le type du fichier n'est pas supporté !!");
				a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
				a.initStyle(StageStyle.UTILITY);
				a.showAndWait();
			}
		}
	}

	public Button getNouveau() {
		return nouveau;
	}

	public void setNouveau(Button nouveau) {
		this.nouveau = nouveau;
	}

	public Button getOuvrir() {
		return ouvrir;
	}

	public void setOuvrir(Button ouvrir) {
		this.ouvrir = ouvrir;
	}

	public Button getFermer() {
		return fermer;
	}

	public void setFermer(Button fermer) {
		this.fermer = fermer;
	}

	public Button getSauvegarder() {
		return sauvegarder;
	}

	public void setSauvegarder(Button sauvegarder) {
		this.sauvegarder = sauvegarder;
	}

	public Button getImporter() {
		return importer;
	}

	public void setImporter(Button importer) {
		this.importer = importer;
	}

	public Button getSauvComme() {
		return sauvComme;
	}

	public void setSauvComme(Button sauvComme) {
		this.sauvComme = sauvComme;
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

	public void setAffX(Label affX) {
		afficheurX = affX;
	}

	public void setAffY(Label affY) {
		afficheurY = affY;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public Button getEncapsuler() {
		return encapsuler;
	}

	public void setEncapsuler(Button encapsuler) {
		this.encapsuler = encapsuler;
	}

	public Button getTableVerite() {
		return tableVerite;
	}

	public void setTableVerite(Button tableVerite) {
		this.tableVerite = tableVerite;
	}

	public Button getChronogramme() {
		return chronogramme;
	}

	public void setChronogramme(Button chronogramme) {
		this.chronogramme = chronogramme;
	}
	public String getFileToUpload() {
		return fileToUpload;
	}
	public void setFileToUpload(String fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	
}
