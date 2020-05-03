package controllers;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import noyau.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProprietesPortesController extends ProprietesController{

	private int i; //Nombre d'entrees
	private String bddDirection[] = {"Est","Sud","West","Nord"}; //base de donn�es de directions
	private int direct; //indice 
	
    public Composant getcmp() {
		return cmp;
	}

	public void setcmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
		this.cmp = cmp;
		
		i=cmp.getNombreEntree();
		direct = cmp.getDirection();
		direction.setText(bddDirection[direct]);
		composant.setText(cmp.getClass().getSimpleName().toString());
		label.setText(cmp.getNom());
		nbEntres.setText(Integer.toString(i));
		if(i==2) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
		}
		if(i==5){
			plusNbEntrees.setVisible(false);
			imgPlusNbEntrees.setVisible(false);
		}
		}
	@FXML
    private Pane pane_proprietes;

    @FXML
    private TextField label;

    @FXML
    private Label nbEntres;

    @FXML
    private Button nextDirection;

    @FXML
    private Button previousDirection;

    @FXML
    private Button plusNbEntrees;

    @FXML
    private Button moinsNbEntrees;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private Label direction;

    @FXML
    private Label composant;

    @FXML
    private ImageView imgPlusNbEntrees;
    
    @FXML
    private ImageView imgMoinsNbEntrees;
    
    @FXML
    private ImageView imgNextDirection;

    @FXML
    private ImageView imgPreviousDirection;
    
    @FXML
    void annuler(ActionEvent event) {
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) {
    	cmp.setNom(label.getText());
    	if (cmp.isDessocier()) {
    		ImageView img= Circuit.getImageFromComp(cmp);
    		cmp.setNombreEntree(i);
    		cmp.setCord();
    		cmp.getLesCoordonnees().setNbCordEntree(i);
    		img.setImage(new Image(cmp.generatePath()));
    		((Portes)cmp).rotation(direct);
    		
    		if(cmp.getDirection() != direct) {
        		cmp.setDirection(direct);
    			removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(cmp.getSorties()[0]));
    			addAllPolylinesToWorkSpace(((Portes)cmp).generatePolyRotation(img.getLayoutX(), img.getLayoutY()));
    		}
    		//cmp.resetPolyline(Circuit.getPolylineFromFil(cmp.getSorties()[0]).get(0).getLinePrincipale(),img.getLayoutX(),img.getLayoutY());
    		switch (direct) {
    		case 0:
    			img.setRotate(0);
    			break;
    		case 1:
    			img.setRotate(90);
    			break;
    		case 2:
    			img.setRotate(180);
    			break;
    		case 3:
    			img.setRotate(270);
    			break;
    		}
    	}else {
    		this.alert();
		}
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void moinsNbEntrees(ActionEvent event) {
    	i--;
    	nbEntres.setText(Integer.toString(i));
    	plusNbEntrees.setVisible(true);
		imgPlusNbEntrees.setVisible(true);
    	if(i==2) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
		}
    }

    @FXML
    void nextDirection(ActionEvent event) {
    	direct ++;
    	if(direct > 3) direct=0;
    	direction.setText(bddDirection[direct]);
    }

    @FXML
    void plusNbEntrees(ActionEvent event) {
    	i++;
    	nbEntres.setText(Integer.toString(i));
    	moinsNbEntrees.setVisible(true);
		imgMoinsNbEntrees.setVisible(true);
		if(i==5){
			plusNbEntrees.setVisible(false);
			imgPlusNbEntrees.setVisible(false);
		}
    }

    @FXML
    void previousDirection(ActionEvent event) {
    	direct--;
    	if(direct < 0) direct = 3;
    	direction.setText(bddDirection[direct]);
    }

}
