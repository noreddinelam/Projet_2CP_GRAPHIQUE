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
import javafx.geometry.Point3D;
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
	private String bddDirection[] = {"Est","Sud","West","Nord"}; //base de données de directions
	private int direct; //indice qui gere la direction
	
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
	
    public Composant getcmp() {
		return cmp;
	}

	public void setcmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
		//initialiser les textes de la fenetre
		btns.add(imgMoinsNbEntrees);
		btns.add(imgNextDirection);
		btns.add(imgPlusNbEntrees);
		btns.add(imgPreviousDirection);
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
		if (! cmp.isDessocier()) { // voir si le composant est dessocié
			nextDirection.setDisable(true);
			previousDirection.setDisable(true);
			plusNbEntrees.setDisable(true);
			moinsNbEntrees.setDisable(true);
			applyOpaciteForImages(btns);
		}
	}
    
    @FXML
    void annuler(ActionEvent event) { /// annuler les modifications faites
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) { /// appliquer les modifications faites
    //Sauvgarder les changements 
    	cmp.setNom(label.getText());
    	if (cmp.isDessocier()) {
    		HomeController.sauveGarderModification();
    		ImageView img= Circuit.getImageFromComp(cmp);
    		cmp.setNombreEntree(i);
    		cmp.getLesCoordonnees().setNbCordEntree(i);  		
    		//Direction
    		if(cmp.getDirection() != direct) {
    			HomeController.sauveGarderRotation(cmp, img, cmp.getDirection());
    			cmp.setDirection(direct);
    			removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(cmp.getSorties()[0]));
    			Image image = new Image(cmp.generatePath());
    			img.setImage(image);
    			img.setFitHeight(image.getHeight());
    			img.setFitWidth(image.getWidth());
    			addAllPolylinesToWorkSpace(cmp.generatePolyline(img.getLayoutX(), img.getLayoutY()));
    		}
    		else {
    			cmp.setCord();
    			Image image = new Image(cmp.generatePath());
    			img.setImage(image);
    			img.setFitHeight(image.getHeight());
    			img.setFitWidth(image.getWidth());
			}
    	}
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();

    }

    @FXML
    void moinsNbEntrees(ActionEvent event) { /// changer le nombre d'entrees
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
    void nextDirection(ActionEvent event) { /// changer la direction
    	direct ++;
    	if(direct > 3) direct=0;
    	direction.setText(bddDirection[direct]);
    }

    @FXML
    void plusNbEntrees(ActionEvent event) { /// changer le nombre d'entrees
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
    void previousDirection(ActionEvent event) { /// changer la direction
    	direct--;
    	if(direct < 0) direct = 3;
    	direction.setText(bddDirection[direct]);
    }

}
