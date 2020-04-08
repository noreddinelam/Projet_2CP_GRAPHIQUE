package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import javafx.event.*;

public class ImgController implements Initializable{

	String imgSrc;
	private Double x,y;
	int switching = 0;

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
		Image m = new Image(imgSrc);
		img.setImage(m);
	}
	@FXML
	private Pane pane;
	@FXML
	private ImageView img;
	@FXML
	private Polyline line;

	@FXML
	void press(MouseEvent event) {
		int i = line.getPoints().size()-4;
		x = event.getX();
		y = event.getY();
		System.out.println(x);
		System.out.println(line.getPoints().get(i));
		System.out.println(y);
		System.out.println(line.getPoints().get(i+1)+"\nmmmmm");
		while((i!=0) && ((Math.abs(x-line.getPoints().get(i))>5) && ((Math.abs(y - line.getPoints().get(i+1)) >=5 ))))
		{
			line.getPoints().addAll(line.getPoints().get(i),line.getPoints().get(i+1));
			i=i-2;
			System.out.println(i+"Dkhal");
			System.out.println(x);
			System.out.println(line.getPoints().get(i));
			System.out.println(y);
			System.out.println(line.getPoints().get(i+1)+"\n******************");
		}

		Double x1 = line.getPoints().get(i),y1 = line.getPoints().get(i+1);
		if(Math.abs(x-line.getPoints().get(i)) < 5) {
			if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
				line.getPoints().addAll(x,y,x,y);
			}
			else {
				line.getPoints().addAll(x1,y1,x1,y,x1,y,x1,y);
			}	
		}else{
			if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
				line.getPoints().addAll(x1,y1,x,y1,x,y1,x,y1);
			}
		}
		/*line.getPoints().addAll(x1,y1,x1,y,x1,y,x1,y);
    	}else if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
    		line.getPoints().addAll(x1,y1,x,y1,x,y1,x,y1);
    	} else line.getPoints().addAll(x,y,x,y);*/
		//line.getPoints().addAll(x1,y1,x1,y1,x1,y1);
		/*if((x2-x)-(y2-y)>=0) { 
    		line.getPoints().addAll(x2,y,x2,y2);
    	}else {
    		line.getPoints().addAll(x,y2,x2,y2);
    	}*/

	}
	
	public void ajouterGeste(Polyline line)
	{
		EventHandler<MouseEvent> event1 = new javafx.event.EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				Double x2 = event.getX();
				Double y2 = event.getY();
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
				int i = line.getPoints().size()-4;
				x = event.getX();
				y = event.getY();
				System.out.println(x);
				System.out.println(line.getPoints().get(i));
				System.out.println(y);
				System.out.println(line.getPoints().get(i+1)+"\nmmmmm");
				while((i!=0) && ((Math.abs(x-line.getPoints().get(i))>5) && ((Math.abs(y - line.getPoints().get(i+1)) >=5 ))))
				{
					line.getPoints().addAll(line.getPoints().get(i),line.getPoints().get(i+1));
					i=i-2;
					System.out.println(i+"Dkhal");
					System.out.println(x);
					System.out.println(line.getPoints().get(i));
					System.out.println(y);
					System.out.println(line.getPoints().get(i+1)+"\n******************");
				}

				Double x1 = line.getPoints().get(i),y1 = line.getPoints().get(i+1);
				if(Math.abs(x-line.getPoints().get(i)) < 5) {
					if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
						line.getPoints().addAll(x,y,x,y);
					}
					else {
						line.getPoints().addAll(x1,y1,x1,y,x1,y,x1,y);
					}	
				}else{
					if(Math.abs(y - line.getPoints().get(i+1)) < 5) {
						line.getPoints().addAll(x1,y1,x,y1,x,y1,x,y1);
					}
				}
			}
		};
		line.setOnMousePressed(event);
		//a.addEventHandler(MouseEvent.MOUSE_DRAGGED, event1);
		line.setOnMouseDragged(event1);
	}
	

	@FXML
	void dragg(MouseEvent event) {
		Double x2 = event.getX();
		Double y2 = event.getY();
		for (int i = 0; i < 4; i++) {
			line.getPoints().remove(line.getPoints().size()-1);
		}
		/*if((x2-x)<10) { 
    		if((y2-y)<10) switching = 0; 
        	else switching = 1;
    	}else {
    		if((y2-y)<10) switching = 0;
    	} 		*/
		//System.out.println(Math.abs(x2-x));
		//System.out.println(Math.abs(y2-y));
		/*if((Math.abs(x2-x)<2) || (Math.abs(y2-y)<2)) {
    		if((x2-x)-(y2-y)>=0) { 
        		switching = 1;
        	}else {
        		switching = 0;
        	}*/
		if(Math.abs(x2-x)<10) { 
			if(Math.abs(y2-y)<10) switching = 0; 
			else switching = 1;
		}else {
			if(Math.abs(y2-y)<10) switching = 0;
		} 		


		if(switching == 0) line.getPoints().addAll(x2,y,x2,y2);
		else line.getPoints().addAll(x,y2,x2,y2);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		while(line.getPoints().size()>4) {
			line.getPoints().remove(line.getPoints().size()-1);
		}
		//declaration du polyline
		Polyline a = new Polyline(410,113,422.0,113); //(x,y,x+12,y) avec x,y coordonnees de la sortie
		a.setStrokeWidth(3);
		a.setSmooth(true);
		a.setStrokeType(StrokeType.CENTERED);
		a.setCursor(Cursor.HAND);
		ajouterGeste(a);
		pane.getChildren().add(a);
	}
}
