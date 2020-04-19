package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import noyau.Circuit;
import noyau.Composant;
import noyau.Coordonnees;
import noyau.Fil;
import noyau.InfoPolyline;

public abstract class Controller {
	protected int sauv;
	
	protected Composant cmp;
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
				guideFilX.setLayoutX(event.getX());
				guideFilY.setLayoutY(event.getY());
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
				Circuit.getFilFromPolyline(line).setSwitching(switching);

			}
			}
			
		};
		
		EventHandler<MouseEvent> event = new javafx.event.EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (! simul) {
				workSpace.getChildren().add(guideFilX);
                workSpace.getChildren().add(guideFilY);
                guideFilX.setLayoutX(event.getX());
				guideFilY.setLayoutY(event.getY());
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
				//source = Circuit.getFilFromPolyline(line).getSource();
				ArrayList<InfoPolyline> listDePolylines = Circuit.getListFromPolyline(line);
				
				////////////////////relier/////////////////////// 
				Fil filSorties = Circuit.getFilFromPolyline(line);
				//System.out.println(filSorties);
				source = filSorties.getSource();
				sortie = source.numCmpSorties(filSorties);	
				//System.out.println("array"+listDePolylines);
				//System.out.println("source "+source+"sortie  "+sortie);
				/////////////////////////////////////////////////
				x = event.getX();
				y = event.getY();
				Polyline line2 = initialser(x, y);
		    	workSpace.getChildren().add(line2);
				line2.getPoints().clear();
				line2.getPoints().addAll(line.getPoints());

				ArrayList<Double> list = new ArrayList<Double>(line.getPoints());
				int i = list.size()-2;
				
				boolean trouve = false ;
				
				while((!trouve) && i>0) {
					if((Math.abs(x - list.get(i)) < 5) && (Math.abs(y - list.get(i+1)) < 5)) {
						trouve = true;
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
					while((!trouve) && (i<list.size()-2)){
						if(Math.abs(x - list.get(i)) < 5 ) {
							trouve = true;
							x = list.get(i);
							line2.getPoints().add(i+2, x);
							line2.getPoints().add(i+3, y);
							line2.getPoints().add(i+2, x);
							line2.getPoints().add(i+3, y);	
							
						}else if (Math.abs(y - list.get(i+1)) < 5) {
							trouve = true;
							y = list.get(i+1);
							line2.getPoints().add(i+2, x);
							line2.getPoints().add(i+3, y);
							line2.getPoints().add(i+2, x);
							line2.getPoints().add(i+3, y);	
								
						}
					i = i + 2;
					}
				}
				listDePolylines.add(listDePolylines.indexOf(new InfoPolyline(line)), new InfoPolyline(line2));
				line2 = initialser(x, y);
				line.getPoints().clear();
				line.getPoints().addAll(line2.getPoints());
				ajouterGeste(line2);
			}
			}
		};
		line.setOnMousePressed(event);
		line.setOnMouseDragged(event1);
		line.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				workSpace.getChildren().remove(guideFilX);
	  			workSpace.getChildren().remove(guideFilY);

				int	der =  line.getPoints().size()-1;
				if(intersectionFilComposants(arg0.getX(),arg0.getY()) != null) {
				//if(intersectionFilComposants(line.getPoints().get(der-1),line.getPoints().get(der))) {
				if(rel == 0) {
					line.getPoints().remove(der);
					line.getPoints().remove(der-1);
					line.getPoints().remove(der-2);
					line.getPoints().remove(der-3);
				}if(rel == 1){
					/////////////////////////////relier/////////////////////////////////////
					destination = intersectionFilComposants(arg0.getX(),arg0.getY());
					Coordonnees crd = new Coordonnees(arg0.getX(),arg0.getY());
					System.out.println(destination+"       "+entree);
					Circuit.relier(source, destination, sortie, entree);
						//souuund

					playSound();
					System.out.println("trabtooo");
					//if(source.getClass().getSimpleName().equals("Pin")) ((Pin)source).setEtat(EtatLogique.ONE);
				}
				}
				if(rel != 0 ){
				der =  line.getPoints().size()-1;
				if( Math.abs(line.getPoints().get(der)-line.getPoints().get(der-2)) < 10  &&  Math.abs(line.getPoints().get(der-1)-line.getPoints().get(der-3))< 10) {
					line.getPoints().remove(der);line.getPoints().remove(der-1);}
			}
				//System.out.println(line.getPoints().size());
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
