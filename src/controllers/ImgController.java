package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

public class ImgController {

	String imgSrc;
	private Double x,y,x2,y2;
	
    public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
		Image m = new Image(imgSrc);
		img.setImage(m);
	}

	@FXML
    private ImageView img;
	@FXML
    private Polyline line;

    @FXML
    void press(MouseEvent event) {
    	x = event.getX();
    	y = event.getY();
    	x2 = event.getX();
    	y2 = event.getY();
    	if((x2-x)-(y2-y)>=0) { 
    		line.getPoints().addAll(x2,y,x2,y2);
    	}else {
    		line.getPoints().addAll(x,y2,x2,y2);
    	}
    }

    @FXML
    void release(MouseEvent event) {
    /*	Double x2 = event.getX();
    	Double y2 = event.getY();
    	if((x2-x)-(y2-y)>=0) { 
    		line.getPoints().addAll(x2,y,x2,y2);
    	}else {
    		line.getPoints().addAll(x,y2,x2,y2);
    	}*/
    }
    @FXML
    void dragg(MouseEvent event) {
    	x2 = event.getX();
    	y2 = event.getY();
    }

}
