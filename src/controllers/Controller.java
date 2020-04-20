package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.ClickDroitFil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import noyau.Bascule;
import noyau.Circuit;
import noyau.Combinatoires;
import noyau.Composant;
import noyau.Coordonnees;
import noyau.Fil;
import noyau.InfoPolyline;
import noyau.Sequentiels;

public abstract class Controller {
	protected int sauv;
	
	protected Composant cmp;
    protected ClickDroitFil clickDroitFilFenetre;
    protected Polyline lineDroit;

	//protected AnchorPane workSpace;
	
	protected double x,y;
    protected int switching = 0; 
    
    protected Line guideFilX = new Line();
	protected Line guideFilY = new Line();
	
	 protected Composant source;

    protected boolean simul = false;
    
    protected Composant destination;
	 protected int entree;
	 protected int sortie;
	 protected int rel;
	 
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
	
	 public void ajouterGeste(Polyline line)
		{
		 EventHandler<MouseEvent> event1 = new javafx.event.EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					//les guides :
					if (! simul) {
						if (event.getButton() == MouseButton.PRIMARY) { 
							guideFilX.setLayoutX(event.getSceneX()-180);
							guideFilY.setLayoutY(event.getSceneY());
							double x2 = event.getX();
							double y2 = event.getY();
							switching = Circuit.getInfoPolylineFromPolyline(line).getSwitching();
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
							Circuit.getInfoPolylineFromPolyline(line).setSwitching(switching);
						}
					}
				}

		 };
			
			EventHandler<MouseEvent> event = new javafx.event.EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					if (! simul) {
						if (event.getButton() == MouseButton.PRIMARY)  {
					workSpace.getChildren().add(guideFilX);
                   workSpace.getChildren().add(guideFilY);
                   guideFilX.setLayoutX(event.getSceneX()-180);
					guideFilY.setLayoutY(event.getSceneY());
					ArrayList<InfoPolyline> listDePolylines = Circuit.getListFromPolyline(line);
					
					////////////////////relier/////////////////////// 
					Fil filSorties = Circuit.getFilFromPolyline(line);
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
	
					ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
					int i = list.size()-2;
					
					boolean trouve = false ;
					
					while((!trouve) && i>0) {
						if((Math.abs(x - list.get(i)) < 5) && (Math.abs(y - list.get(i+1)) < 5)) {
							trouve = true;
							System.out.println("dkhal 1");
							x = list.get(i);
							y = list.get(i+1);
							line2.getPoints().add(i, x);
							line2.getPoints().add(i+1, y);
							line2.getPoints().add(i, x);
							line2.getPoints().add(i+1, y);
						}
						i = i-2;
					}
					i = 0;
					if(!trouve) {
						while((!trouve)){
							if(Math.abs(x - list.get(i)) < 5 ) {
								trouve = true;
								System.out.println("dkhal 1");
								x = list.get(i);
								line2.getPoints().add(i+2, x);
								line2.getPoints().add(i+3, y);
								line2.getPoints().add(i+2, x);
								line2.getPoints().add(i+3, y);	
								
							}else if (Math.abs(y - list.get(i+1)) < 5) {
								trouve = true;
								System.out.println("dkhal 1");
								y = list.get(i+1);
								line2.getPoints().add(i+2, x);
								line2.getPoints().add(i+3, y);
								line2.getPoints().add(i+2, x);
								line2.getPoints().add(i+3, y);	
									
							}
						i = i + 2;
						}
					}
					InfoPolyline infoLine1 = Circuit.getInfoPolylineFromPolyline(line);
					InfoPolyline infoLine2 = new InfoPolyline(line2,infoLine1.getLineParent(),0,infoLine1.getNbFils()+1); //line2 est pere , donc incrementer le nombre de fils
					infoLine2.copierRelierInfo(infoLine1);
					infoLine1.setLineParent(line2); //line 2 est le pere de line 1
					infoLine1.setNbFils(0);
					infoLine1.setRelier(false);
					
					listDePolylines.add(listDePolylines.indexOf(infoLine1), infoLine2);
					Polyline line3 = initialser(x, y);
					line.getPoints().clear();
					line.getPoints().addAll(line3.getPoints());
					ajouterGeste(line2);
					}else {
						 double clicDroitX,clicDroitY;
					 		clicDroitX = event.getScreenX();
					 		clicDroitY = event.getScreenY();
					 		lineDroit = line;
					 		line.setStroke(Color.web("00000070"));
					 		clickDroitFilFenetre = new ClickDroitFil(line,workSpace,clicDroitX,clicDroitY);
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
					if(arg0.getButton() == MouseButton.PRIMARY) {
					workSpace.getChildren().remove(guideFilX);
		  			workSpace.getChildren().remove(guideFilY);

					int	der =  line.getPoints().size()-1;
					if(intersectionFilComposants(arg0.getSceneX()-180,arg0.getSceneY()) != null) {
					//if(intersectionFilComposants(line.getPoints().get(der-1),line.getPoints().get(der))) {
					if(rel == 0) {
						line.getPoints().remove(der);line.getPoints().remove(der-1);line.getPoints().remove(der-2);line.getPoints().remove(der-3);
					}if(rel == 1){
						/////////////////////////////relier/////////////////////////////////////
						destination = intersectionFilComposants(arg0.getSceneX()-180,arg0.getSceneY());
						/*   		entree >= 0   :entres
						 *    -4 <= entree < 0	  :commandes
						 * 			entree = -5	  :horloge
						 * 			entree = -6	  :clear
						 * 			entree = -7   :preset
						 * 			entree = -8   :load*/
						if(entree >= 0) {
							Circuit.relier(source, destination, sortie, entree);
							System.out.println("trabtooo entre");
							playSound();
						}else if(-5 < entree) {
							Circuit.relierCommand(source,((Combinatoires)destination), sortie, Math.abs(entree)-1);
							System.out.println("trabtooo commande");
							playSound();
						}else if(entree == -5) {
							Circuit.relierHorloge(((Sequentiels)destination), source, sortie);
							System.out.println("trabtooo horloge");
							playSound();
						}else if(entree == -6) {
							Circuit.relierClear(((Sequentiels)destination), source, sortie);
							System.out.println("trabtooo clear");	
							playSound();
						}else if(entree == -7) {
							Circuit.relierPreset(((Bascule)destination), source, sortie);
							System.out.println("trabtooo preset");
							playSound();
						}else if(entree == -8) {
							Circuit.relierLoad(((Sequentiels)destination), source, sortie);
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
					if( Math.abs(line.getPoints().get(der)-line.getPoints().get(der-2)) < 10  &&  Math.abs(line.getPoints().get(der-1)-line.getPoints().get(der-3))< 10) {
						line.getPoints().remove(der);line.getPoints().remove(der-1);}
				}
					//System.out.println(line.getPoints().size());
				}
				}
			});
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
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/1.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
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
	
	public int intersectionFilComposant(ImageView imgCmp,double Xfil,double Yfil) {
	/*   		entree >= 0   :entres
	 * 	  -4 =< entree  < 0	  :commandes
	 * 			entree = -5	  :horloge
	 * 			entree = -6	  :clear
	 * 			entree = -7   :preset
	 * 			entree = -8   :load
	 */
		Double XImg = imgCmp.getLayoutX();
		Double Yimg = imgCmp.getLayoutY();
		
		if(( Xfil >= XImg  )  &&  (XImg+imgCmp.getFitWidth() > Xfil) && ( Yfil >= Yimg)  &&  (Yimg+imgCmp.getFitHeight() > Yfil) ) {
			Composant cmp = Circuit.getCompFromImage(imgCmp);
			Coordonnees tabCoord[] = cmp.getLesCoordonnees().getCordEntree();
			int nbCord = cmp.getLesCoordonnees().getNbCordEntree();
			Coordonnees crd = new Coordonnees(Xfil,Yfil);
			boolean trouve = false;
			int i = 0;
			while( i < nbCord && trouve == false) { 
				Coordonnees crdTab = new Coordonnees(tabCoord[i].getX() + imgCmp.getLayoutX(), tabCoord[i].getY() + imgCmp.getLayoutY());				
				if( crdTab.equals(crd) ) { 
					trouve =true;
					entree=i; 
				}
				i++;
			}
			if(!trouve) {
				i=0;
				tabCoord = cmp.getLesCoordonnees().getCordCommandes();
				nbCord = cmp.getLesCoordonnees().getNbCordCommandes();
				while( i < nbCord && trouve == false) { 
					Coordonnees crdTab = new Coordonnees(tabCoord[i].getX() + imgCmp.getLayoutX(), tabCoord[i].getY() + imgCmp.getLayoutY());				
					if( crdTab.equals(crd) ) { 
						trouve =true;
						entree= -i-1; 
					}
					i++;
				}
				Coordonnees crdTab ;
				if(cmp.getLesCoordonnees().getCordHorloge() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordHorloge().getX() + imgCmp.getLayoutX(), cmp.getLesCoordonnees().getCordHorloge().getY() + imgCmp.getLayoutY());				
					if( crdTab.equals(crd) ) { 
						trouve =true;
						entree= -5; 
					}
				}
				if(cmp.getLesCoordonnees().getCordClear() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordClear().getX() + imgCmp.getLayoutX(), cmp.getLesCoordonnees().getCordClear().getY() + imgCmp.getLayoutY());				
					if( crdTab.equals(crd) ) { 
						trouve =true;
						entree= -6; 
					}
				}
				if(cmp.getLesCoordonnees().getCordPreset() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordPreset().getX() + imgCmp.getLayoutX(), cmp.getLesCoordonnees().getCordPreset().getY() + imgCmp.getLayoutY());				
					if( crdTab.equals(crd) ) { 
						trouve =true;
						entree= -7; 
					}
				}
				if(cmp.getLesCoordonnees().getCordLoad() != null && !trouve) {
					crdTab = new Coordonnees(cmp.getLesCoordonnees().getCordLoad().getX() + imgCmp.getLayoutX(), cmp.getLesCoordonnees().getCordLoad().getY() + imgCmp.getLayoutY());				
					if( crdTab.equals(crd) ) { 
						trouve =true;
						entree= -8; 
					}
				}
			}
			if(trouve)
				return 1;
			else return 0;
		}else return -1;
		
	}
	
	public Polyline initialser(double x, double y) {
		Polyline a = new Polyline(x,y,x,y,x,y); 
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
    	ajouterGeste(a);
		return a;
	}
}
