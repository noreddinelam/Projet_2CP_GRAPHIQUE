package controllers;

import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.ClickDroitFil;
import application.ClickSouris2;
import javafx.application.Platform;
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
	protected int sauv;

	protected  Composant cmp;
	protected static ClickDroitFil clickDroitFilFenetre;
	protected static Polyline lineDroit;
	public static Stage homeWindow;
	public static Scene homeScene;
	protected static ClickSouris2 clickSouris2;

	// protected AnchorPane workSpace;

	protected double x, y;
	protected int switching = 0;

	protected static Line guideFilX = new Line();
	protected static Line guideFilY = new Line();

	protected Composant source;

	public static boolean simul = false;
	protected static ArrayList<Pin> ListTextPin = null;
	protected static ArrayList<Text> ListText = null;
	
	//pour les sorties 
	protected static ArrayList<Pin> ListTextPin2 = null;
	protected static ArrayList<Text> ListText2 = null;

	protected Composant destination;
	protected int entree;
	protected int sortie;
	protected int rel;

	protected static ArrayList<Button> rightBareButtons = new ArrayList<Button>() ;
	
	protected Circle relieCercle ;

	@FXML
	protected AnchorPane workSpace;

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
			workSpace.getChildren().add(polyline);
			ajouterGeste(polyline);
		}
	}

	boolean draggLine = true;

	public void ajouterGeste(Polyline line) {
		EventHandler<MouseEvent> event1 = new javafx.event.EventHandler<MouseEvent>() {
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
						guideFilX.setLayoutX(event.getX());
						guideFilY.setLayoutY(event.getY());
						ArrayList<InfoPolyline> listDePolylines = Circuit.getListFromPolyline(line);

						//////////////////// relier///////////////////////
						Fil filSorties = Circuit.getFilFromPolyline(line);
						System.out.println(line + "linnnne" + filSorties);
						source = filSorties.getSource();
						sortie = source.numCmpSorties(filSorties);
						/////////////////////////////////////////////////
						x = event.getX();
						y = event.getY();
						Polyline line2 = initialser(x, y);
						workSpace.getChildren().add(line2);
						line2.getPoints().clear();
						line2.getPoints().addAll(line.getPoints());
						Circuit.remplacerPere(line, line2);
						HomeController.remplacerLineUndoDeque(line, line2);
						HomeController.remplacerLineUndoDequeSupprimer(line, line2);
						ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
						int i = list.size() - 2;

						boolean trouve = false;

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

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				draggLine = true;
				if (!simul) {
					if (arg0.getButton() == MouseButton.PRIMARY) {
						workSpace.getChildren().remove(guideFilX);
						workSpace.getChildren().remove(guideFilY);
						int der = line.getPoints().size() - 1;

						if (intersectionFilComposants(arg0.getX(), arg0.getY()) != null) {
							// if(intersectionFilComposants(line.getPoints().get(der-1),line.getPoints().get(der)))
							// {
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
								destination = intersectionFilComposants(arg0.getX(), arg0.getY());
								/*
								 * entree >= 0 :entres -4 < entree < 0 :commandes entree = -4 :horloge entree =
								 * -5 :clear entree = -6 :preset entree = -7 :load
								 */
								if (entree >= 0) {
									Circuit.relier(source, destination, sortie, entree);
									System.out.println("trabtooo entre");
									playSound();
								} else if (-5 < entree) {
									Circuit.relierCommand(source, ((Combinatoires) destination), sortie,
											Math.abs(entree) - 1);
									System.out.println("trabtooo commande");
									playSound();
								} else if (entree == -5) {
									Circuit.relierHorloge(((Sequentiels) destination), source, sortie);
									System.out.println("trabtooo horloge");
									playSound();
								} else if (entree == -6) {
									Circuit.relierClear(((Sequentiels) destination), source, sortie);
									System.out.println("trabtooo clear");
									playSound();
								} else if (entree == -7) {
									Circuit.relierPreset(((Bascule) destination), source, sortie);
									System.out.println("trabtooo preset");
									playSound();
								} else if (entree == -8) {
									Circuit.relierLoad(((Sequentiels) destination), source, sortie);
									System.out.println("trabtooo load");
									playSound();
								}
								InfoPolyline infoLine = Circuit.getInfoPolylineFromPolyline(line);
								infoLine.setRelier(true);
								infoLine.setDestination(destination);
								infoLine.setEntre(entree);
								//souuund
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
						//sauvgaaarder la creation du fil
						if(line.getPoints().size()!=0) 
							sauvgardeCreationFil(line);
						
					}
				}
			}

		});
	}
	public void playSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("src/1.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	private Composant intersectionFilComposants(Double x, Double y) {
		Composant cmp = null;
		boolean trouv = false;
		Collection<ImageView> list = Circuit.getCompUtilises().values();
		Iterator<ImageView> iterator = list.iterator();
		ImageView img;
		System.out.println("X : " + x + " Y : " + y);
		while (iterator.hasNext() && !trouv) {
			img = iterator.next();
			if (intersectionFilComposant(img, x, y) != -1) {
				trouv = true;
				cmp = Circuit.getCompFromImage(img);
				rel = intersectionFilComposant(img, x, y);
			}
		}
		return cmp;
	}

	public int intersectionFilComposant(ImageView imgCmp, double Xfil, double Yfil) {
		/*
		 * entree >= 0 :entres -4 =< entree < 0 :commandes entree = -5 :horloge entree =
		 * -6 :clear entree = -7 :preset entree = -8 :load
		 */
		Double XImg = imgCmp.getLayoutX();
		Double Yimg = imgCmp.getLayoutY();
		if ((Xfil >= XImg) && (XImg + imgCmp.getFitWidth() > Xfil) && (Yfil >= Yimg)
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
		Polyline a = new Polyline(x, y, x, y, x, y);
		// a.setViewOrder(1); //l'ordre
		a.toBack();
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
		ajouterGeste(a);
		return a;

	}	
	public void sauvgardeCreationFil(Polyline line) {
		Donnes sauvgarde = new Donnes();
		sauvgarde.setFil(Circuit.getFilFromPolyline(line));
		sauvgarde.setParent(line);
		sauvgarde.setInfoPolyline(Circuit.getInfoPolylineFromPolyline(line));
		sauvgarde.setTypeDaction(Actions.CreationFil);
		HomeController.undoDeque.addFirst(sauvgarde);

	}
	public void lessOpacite() {
	//parcourir les composants et les fils et les mettre transparents
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
		relieCercle = new Circle();
		relieCercle.setRadius(6);
		relieCercle.setFill(Color.TRANSPARENT);
		relieCercle.setStroke(Color.YELLOW);
		relieCercle.setStrokeWidth(2);
		relieCercle.setSmooth(true);
		relieCercle.setLayoutX(0);
		relieCercle.setLayoutY(0);
		relieCercle.setCenterX(x);
		relieCercle.setCenterY(y);
	}
	
	
}
