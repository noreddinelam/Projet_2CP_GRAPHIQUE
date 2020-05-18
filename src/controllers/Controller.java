package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import application.ClickDroitFil;
import application.ClickSouris2;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import noyau.Actions;
import noyau.Bascule;
import noyau.Circuit;
import noyau.Combinatoires;
import noyau.Composant;
import noyau.Coordonnees;
import noyau.Donnes;
import noyau.Fil;
import noyau.InfoPolyline;
import noyau.Pin;
import noyau.Sequentiels;

public abstract class Controller {
/*		La classe controller est la classe la plus generale dans le package controllers , 
 * tous les controlleurs Herite de cette classe, utilisée pour regroupée des attributs et des methodes.
 */
	protected int sauv;

	protected  Composant cmp; //utilisé si un composant est selectionné 
	protected static ClickDroitFil clickDroitFilFenetre; 
	protected static Polyline lineDroit;  //utilisé si un fil (Polyline)  est selectionné
	public static Stage homeWindow;
	public static Scene homeScene;
	protected static ClickSouris2 clickSouris2; //

	protected double x, y;
	protected int switching = 0; //La position du polyline en cas de mouvements 

	protected static Line guideFilX = new Line(); //Les guides du fil (du traçage) 
	protected static Line guideFilY = new Line(); 

	protected Composant source; //La relation entre les composants

	public static boolean simul = false;  // true si :mode de creation , faux sinon 
	protected static ArrayList<Pin> ListTextPin = null; //la liste des pins quand l'utilisateur selectionne l'ordre des entrées (table de verite ou circuit personnaliséé)
	protected static ArrayList<Text> ListText = null;   //la liste des numeros quand l'utilisateur selectionne l'ordre des entrées (table de verite ou circuit personnaliséé)
	
	//pour les pins et les numeros de sorties 
	protected static ArrayList<Pin> ListTextPin2 = null;
	protected static ArrayList<Text> ListText2 = null;

	protected Composant destination; //La relation entre les composants
	protected int entree; // le numero de l'entrée
	protected int sortie; // le numero de la sortie
	protected int rel;

	protected static ArrayList<Button> rightBareButtons = new ArrayList<Button>() ; //pour les desactivés en mode de creation
	
	protected Circle relieCercle ; //quand un fil est relié , un cercle est generé au niveau du l'entrée
	
	protected FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

	@FXML
	protected AnchorPane workSpace;
	
	URL url;

	public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public AnchorPane getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(AnchorPane workSpace) {
		this.workSpace = workSpace;
	}

	public void removeAllPolylinesFromWorkSpace(ArrayList<Polyline> arrayList) {
		for (Polyline polyline : arrayList) {
			workSpace.getChildren().remove(polyline);
		}
	}

	public void addAllPolylinesToWorkSpace(ArrayList<Polyline> arrayList) {
		for (Polyline polyline : arrayList) {
			polyline.setSmooth(true);
			polyline.setStrokeWidth(3);
			polyline.setStrokeType(StrokeType.CENTERED);
			polyline.setCursor(Cursor.HAND);
			workSpace.getChildren().add(0,polyline);
			ajouterGeste(polyline);
		}
	}

	boolean draggLine = true;

	public void ajouterGeste(Polyline line) {
	//la fonction responsable de la gestion des fils : 
		EventHandler<MouseEvent> event1 = new javafx.event.EventHandler<MouseEvent>() {
		/* "On Dragg" d'un fil (Polyline) :
		 * Fonctionnement : a chaque fois on fait bouger un fil, on supprime les 2 premier noeuds , et on insers 2 autre qui depant 
		 * de la position de la souris avec un certain traitement spécefique . 
		 */
		
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				// les guides :
				if (!simul) { 
					if (event.getButton() == MouseButton.PRIMARY) {
						guideFilX.setLayoutX(event.getX());
						guideFilY.setLayoutY(event.getY());
						double x2 = event.getX();
						double y2 = event.getY();
						switching = Circuit.getInfoPolylineFromPolyline(line).getSwitching();
						for (int i = 0; i < 4; i++) {
							line.getPoints().remove((line.getPoints().size() - 1));
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
						Circuit.getInfoPolylineFromPolyline(line).setSwitching(switching);
					}
				}
			}

		};

		EventHandler<MouseEvent> event = new javafx.event.EventHandler<MouseEvent>() {
		/*"on press"  d'un fil (Polyline) :
		 * quand on clic sur un polyline ,un autre polyline est creé,ajouté au workspace et ajouté
		 * comme fils au polyline premier avec un certain traitement specifique ".  
		 */
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (!simul) {
					if (event.getButton() == MouseButton.PRIMARY) {
						if (! workSpace.getChildren().contains(guideFilX)) {
							workSpace.getChildren().add(guideFilX);
						}
						if (! workSpace.getChildren().contains(guideFilY)) {
							workSpace.getChildren().add(guideFilY);
						}
						if (relieCercle != null) {
						    workSpace.getChildren().remove(relieCercle);
							relieCercle = null;
						}
						guideFilX.setLayoutX(event.getX());
						guideFilY.setLayoutY(event.getY());
						ArrayList<InfoPolyline> listDePolylines = Circuit.getListFromPolyline(line);

						//////////////////// relier///////////////////////
						Fil filSorties = Circuit.getFilFromPolyline(line);
						source = filSorties.getSource();
						sortie = source.numCmpSorties(filSorties);
						/////////////////////////////////////////////////
						
						x = event.getX();
						y = event.getY();
						Polyline line2 = initialser(x, y);
						workSpace.getChildren().add(0,line2);
						line2.getPoints().clear();
						line2.getPoints().addAll(line.getPoints());
						Circuit.remplacerPere(line, line2);
						HomeController.remplacerLineUndoDeque(line, line2);
						HomeController.remplacerLineUndoDequeSupprimer(line, line2);
						ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
						int i = list.size() - 2;

						boolean trouve = false;

						//Pour trouver le noeud ou l'utilisateur a click
						while ((!trouve) && i > 0) {
							if ((Math.abs(x - list.get(i)) <= 10) && (Math.abs(y - list.get(i + 1)) <= 10)) {
								trouve = true;
								x = list.get(i);
								y = list.get(i + 1);
								line2.getPoints().add(i, x);
								line2.getPoints().add(i + 1, y);
								line2.getPoints().add(i, x);
								line2.getPoints().add(i + 1, y);
							}
							i = i - 2;
						}

						i = 2;
						if (!trouve) {
							while ((!trouve) && (i < list.size() )) {
								if (Math.abs(x - list.get(i)) <= 10) {
									if( (list.get(i-1)<y && y <list.get(i+1)) || (list.get(i+1)<y  && y<list.get(i-1) ))
									{
										trouve = true;
										x = list.get(i);
										line2.getPoints().add(i , x);
										line2.getPoints().add(i + 1, y);
										line2.getPoints().add(i , x);
										line2.getPoints().add(i + 1, y);
									}
								} else if (Math.abs(y - list.get(i + 1)) <= 10) {
									if( (list.get(i-2)<x && x <list.get(i) ) || (list.get(i)<x && x<list.get(i-2)) ) {
										trouve = true;
										y = list.get(i + 1);
										line2.getPoints().add(i , x);
										line2.getPoints().add(i + 1, y);
										line2.getPoints().add(i , x);
										line2.getPoints().add(i + 1, y);
									}
								}
								i = i + 2;
							}
						}
						InfoPolyline infoLine1 = Circuit.getInfoPolylineFromPolyline(line);
						InfoPolyline infoLine2 = new InfoPolyline(line2, infoLine1.getLineParent(), 0,
								infoLine1.getNbFils() + 1); // line2 est pere , donc incrementer le nombre de fils
						infoLine2.copierRelierInfo(infoLine1);
						infoLine1.setLineParent(line2); // line 2 est le pere de line 1
						infoLine1.setNbFils(0);
						infoLine1.setRelier(false);

						listDePolylines.add(listDePolylines.indexOf(infoLine1), infoLine2);
						Polyline line3 = initialser(x, y);
						line.getPoints().clear();
						line.getPoints().addAll(line3.getPoints());
						ajouterGeste(line2);
					} else {
						double clicDroitX, clicDroitY;
						clicDroitX = event.getScreenX();
						clicDroitY = event.getScreenY();
						lineDroit = line;
						line.setStroke(Color.web("00000070"));
						if(clickSouris2 != null)
							clickSouris2.close();
						if (clickDroitFilFenetre != null) {
							clickDroitFilFenetre.close();
						}
						if (clicDroitX > 1145) {
							if (clicDroitY > 640) {
								clickDroitFilFenetre = new ClickDroitFil(line,workSpace,clicDroitX-150,clicDroitY-50, homeWindow);
							}
							else {
								clickDroitFilFenetre = new ClickDroitFil(line,workSpace,clicDroitX-150,clicDroitY, homeWindow);
							}
						}
						else {
							if (clicDroitY > 640) {
								clickDroitFilFenetre = new ClickDroitFil(line,workSpace,clicDroitX,clicDroitY-50, homeWindow);
							}
							else {
								clickDroitFilFenetre = new ClickDroitFil(line,workSpace,clicDroitX,clicDroitY, homeWindow);
							}
						}
					}
				}
			}
		};
		line.setOnMousePressed(event);
		line.setOnMouseDragged(event1);
		line.setOnMouseReleased(new EventHandler<MouseEvent>() {
		/*Fonctionnement :"La relation " entre composants ,Si le polyline est relaché dans un autre composant
		 * alors: 
		 * 		Si relaché dans une entré ,une relation se fait entre le composant source du polyline ,et ce dernier .
		 * 		Sinon : le polyline est supprimé .
		 */
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				draggLine = true;
				if (!simul) {
					if (arg0.getButton() == MouseButton.PRIMARY) {
						workSpace.getChildren().remove(guideFilX);
						workSpace.getChildren().remove(guideFilY);
						int der = line.getPoints().size() - 1;
						if (( destination = intersectionFilComposants(arg0.getX(), arg0.getY())) != null) {
							/*rel : si 0 n'est pas relié a une entrée ( polyline à supprimé ) ,
							*		si 1 
							*/

							//Polyline n'est pas relaché dans un composant .
							if (rel == 0) {
								// suppression du fil
								InfoPolyline infoline = Circuit.getInfoPolylineFromPolyline(line);
								infoline.supprimerPremierNoeuds();
								workSpace.getChildren().remove(line);
								Circuit.getListFromPolyline(line).remove(new InfoPolyline(line));
								infoline = Circuit.getInfoPolylineFromPolyline(infoline.getLineParent());
								infoline.setNbFils(infoline.getNbFils() - 1);
								line.getPoints().clear();
							} else if (rel == 1) {
								///////////////////////////// relier/////////////////////////////////////
								/*
								 * entree >= 0 :entres 
								 * -4 < entree < 0 :commandes 
								 * entree = -4 :horloge 
								 * entree = -5 :clear 
								 * entree = -6 :preset
								 *  entree = -7 :load
								 */
								if (entree >= 0) {
									Circuit.relier(source, destination, sortie, entree);
									playSound();
								} else if (-5 < entree) {
									Circuit.relierCommand(source, ((Combinatoires) destination), sortie,
											Math.abs(entree) - 1);
									playSound();
								} else if (entree == -5) {
									Circuit.relierHorloge(((Sequentiels) destination), source, sortie);
									playSound();
								} else if (entree == -6) {
									Circuit.relierClear(((Sequentiels) destination), source, sortie);
									playSound();
								} else if (entree == -7) {
									Circuit.relierPreset(((Bascule) destination), source, sortie);
									playSound();
								} else if (entree == -8) {
									Circuit.relierLoad(((Sequentiels) destination), source, sortie);
									playSound();
								}
								InfoPolyline infoLine = Circuit.getInfoPolylineFromPolyline(line);
								infoLine.setRelier(true);
								infoLine.setDestination(destination);
								infoLine.setEntre(entree);

								fadeTransition.setNode(relieCercle);
								fadeTransition.setFromValue(1);
								fadeTransition.setToValue(0);
								fadeTransition.play();
								
							}
						}else {
							der =  line.getPoints().size()-1;
							if( Math.abs(line.getPoints().get(der)-line.getPoints().get(der-2)) < 10  &&  Math.abs(line.getPoints().get(der-1)-line.getPoints().get(der-3)) < 10 ) {
								if(Math.abs(line.getPoints().get(der-4)-line.getPoints().get(der-2)) < 10  &&  Math.abs(line.getPoints().get(der-5)-line.getPoints().get(der-3)) < 10)
								{
									ClickDroitFilController.setPane(workSpace);
									ClickDroitFilController.supprimer(Circuit.getInfoPolylineFromPolyline(line));
									line.getPoints().clear();
								}
								else {
									line.getPoints().remove(der);
									line.getPoints().remove(der-1);
								}
							}
						}
						//sauvgarder la creation du fil (CTRL Z)
 						if(line.getPoints().size()!=0) 
							sauvgardeCreationFil(line);
						
					}
				}
			}

		});
	}
	public void playSound() {
	//le son quand un fil est relié
		try {
			url =getClass().getResource("sound.wav");
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Composant intersectionFilComposants(Double x, Double y) {
	/*Role : Pour avoir si un fil est relaché dans l'un des composants du Workspace 
	 */
		Composant cmp = null;
		boolean trouv = false;
		Collection<ImageView> list = Circuit.getCompUtilises().values();
		Iterator<ImageView> iterator = list.iterator();
		ImageView img;
		while (iterator.hasNext() && !trouv) {
			img = iterator.next();
			if ((rel = intersectionFilComposant(img, x, y)) != -1) {
				trouv = true;
				cmp = Circuit.getCompFromImage(img);
			}
		}
		return cmp;
	}

	public int intersectionFilComposant(ImageView imgCmp, double Xfil, double Yfil) {
		/*Role : Pour avoir si un fil est relaché dans l'espace d'un composant ou pas 
		 */
		
		/*
		 * entree >= 0 :entres -4 =< 
		 * entree < 0 :commandes 
		 * entree = -5 :horloge 
		 * entree = -6 :clear 
		 * entree = -7 :preset 
		 * entree = -8 :load
		 */
		double XImg = imgCmp.getLayoutX();
		double Yimg = imgCmp.getLayoutY();
		double ci = 0; 
		if(imgCmp.getId().equals("CircuitIntegre") || imgCmp.getId().equals("CircuitIntegreSequentiel")) //un traitement pour les coordonnées des circuit integrées
			ci = 5;
		
		if ((Xfil >= XImg-ci) && (XImg + imgCmp.getFitWidth() > Xfil) && (Yfil >= Yimg)
				&& (Yimg + imgCmp.getFitHeight() > Yfil)) {
			Composant cmp = Circuit.getCompFromImage(imgCmp);
			Coordonnees tabCoord[] = cmp.getLesCoordonnees().getCordEntree();
			int nbCord = cmp.getNombreEntree();
			Coordonnees crd = new Coordonnees(Xfil, Yfil);
			boolean trouve = false;
			int i = 0;
			while (i < nbCord && trouve == false) {
				Coordonnees crdTab = new Coordonnees(tabCoord[i].getX() + imgCmp.getLayoutX(),
						tabCoord[i].getY() + imgCmp.getLayoutY());
				if (crdTab.equals(crd)) {
					if (cmp.getEntrees()[i] != null)
						return 0;
					trouve = true;
					entree = i;
					relieCercle(crdTab.getX(), crd.getY());
					workSpace.getChildren().add(relieCercle);
				}
				i++;
			}
			if (!trouve) {
				i = 0;
				tabCoord = cmp.getLesCoordonnees().getCordCommandes();
				nbCord = cmp.getLesCoordonnees().getNbCordCommandes();
				while (i < nbCord && trouve == false) {
					Coordonnees crdTab = new Coordonnees(tabCoord[i].getX() + imgCmp.getLayoutX(),
							tabCoord[i].getY() + imgCmp.getLayoutY());
					if (crdTab.equals(crd)) {
						if (((Combinatoires) cmp).getCommande()[i] != null)
							return 0;
						trouve = true;
						entree = -i - 1;
						relieCercle(crdTab.getX(), crd.getY());
						workSpace.getChildren().add(relieCercle);
					}
					i++;
				}
				Coordonnees crdTab;
				if (cmp.getLesCoordonnees().getCordHorloge() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordHorloge().getX() + imgCmp.getLayoutX(),
							cmp.getLesCoordonnees().getCordHorloge().getY() + imgCmp.getLayoutY());
					if (crdTab.equals(crd)) {
						if (((Sequentiels) cmp).getEntreeHorloge() != null)
							return 0;
						trouve = true;
						entree = -5;
						relieCercle(crdTab.getX(), crd.getY());
						workSpace.getChildren().add(relieCercle);
					}
				}
				if (cmp.getLesCoordonnees().getCordClear() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordClear().getX() + imgCmp.getLayoutX(),
							cmp.getLesCoordonnees().getCordClear().getY() + imgCmp.getLayoutY());
					if (crdTab.equals(crd)) {
						if (((Sequentiels) cmp).getClear().getSource() != null)
							return 0;
						trouve = true;
						entree = -6;
						relieCercle(crdTab.getX(), crd.getY());
						workSpace.getChildren().add(relieCercle);
					}
				}
				if (cmp.getLesCoordonnees().getCordPreset() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordPreset().getX() + imgCmp.getLayoutX(),
							cmp.getLesCoordonnees().getCordPreset().getY() + imgCmp.getLayoutY());
					if (crdTab.equals(crd)) {
						if (((Bascule) cmp).getPreset().getSource() != null)
							return 0;
						trouve = true;
						entree = -7;
						relieCercle(crdTab.getX(), crd.getY());
						workSpace.getChildren().add(relieCercle);
					}
				}
				if (cmp.getLesCoordonnees().getCordLoad() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordLoad().getX() + imgCmp.getLayoutX(),
							cmp.getLesCoordonnees().getCordLoad().getY() + imgCmp.getLayoutY());
					if (crdTab.equals(crd)) {
						if (((Sequentiels) cmp).getLoad().getSource() != null)
							return 0;
						trouve = true;
						entree = -8;
						relieCercle(crdTab.getX(), crd.getY());
						workSpace.getChildren().add(relieCercle);
					}
				}
			}
			if (trouve)
				return 1;
			else
				return 0;
		} else
			return -1;
	}

	public Polyline initialser(double x, double y) {
		//Creer un polyline .
		Polyline a = new Polyline(x, y, x, y, x, y);
		a.toBack();
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
		ajouterGeste(a);
		return a;
	}	
	
	public void sauvgardeCreationFil(Polyline line) {
		/* Role : sauvgarder une creation d'un polyline dans la pile UndoChanges */
		Donnes sauvgarde = new Donnes();
		sauvgarde.setFil(Circuit.getFilFromPolyline(line));
		sauvgarde.setParent(line);
		sauvgarde.setInfoPolyline(Circuit.getInfoPolylineFromPolyline(line));
		sauvgarde.setTypeDaction(Actions.CreationFil);
		HomeController.undoDeque.addFirst(sauvgarde);
	}
	public void lessOpacite() {
	/* Role :parcourir les composants et les fils et les mettre transparents*/
		for (Entry<Composant, ImageView> entry : Circuit.getCompUtilises().entrySet()) {
			Composant cmp = entry.getKey();
			if (! cmp.getClass().getSimpleName().equals("Pin") ) {
				entry.getValue().setOpacity(0.4);
			}
		}
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : Circuit.getfilUtilises().entrySet()) {

			for (InfoPolyline info : entry.getValue()) {
				info.getLinePrincipale().setOpacity(0.4);
			}
		}
	}

	public static ArrayList<Button> getRightBareButtons() {
		return rightBareButtons;
	}

	public static void setRightBareButtons(ArrayList<Button> rightBareButtons) {
		Controller.rightBareButtons = rightBareButtons;
	}
	
	public void relieCercle(double x, double y) {
	/*Role : creer une cercle quand un fil est relié */
		relieCercle = new Circle();
		relieCercle.setRadius(5);
		relieCercle.setFill(Color.TRANSPARENT);
		relieCercle.setStroke(Color.CYAN);
		relieCercle.setStrokeWidth(2);
		relieCercle.setSmooth(true);
		relieCercle.setLayoutX(0);
		relieCercle.setLayoutY(0);
		relieCercle.setCenterX(x);
		relieCercle.setCenterY(y);
	}
}
