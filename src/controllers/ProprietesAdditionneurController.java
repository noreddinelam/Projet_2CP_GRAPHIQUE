package controllers;

import javafx.application.Platform;
import application.*;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

public class ProprietesAdditionneurController extends ProprietesController{

	private int i; //Nombre de bits
	private int nbEntree;
	private int direct; //indice 
	
    public Composant getcmp() {
		return cmp;
	}

	public void setcmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
		this.cmp = cmp;
		sauv = cmp.getNombreEntree();
		i=cmp.getNombreEntree();
		direct = 0;
		composant.setText(cmp.getClass().getSimpleName().toString());
		label.setText(cmp.getNom());
		if(cmp.getClass().getSimpleName().equals("DemiAdditionneur"))
    	{
			i=i/2;
			nbEntres.setText(Integer.toString(i));
			composant.setText("Demi Additionneur");
    	}
    	else {
    		i=(i-1)/2;
    		nbEntres.setText(Integer.toString(i));
    		composant.setText("Additionneur Complet");
    	}
		if(i==1) {
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
    	if((cmp.getNombreEntree() != i*2) && (cmp.getNombreEntree() != i*2+1))
    	{
    		if (cmp.isDessocier()) {
    			removeAllPolylinesFromWorkSpace(Circuit.supprimerAllPolylinesForCompounent(cmp));
    			if(cmp.getClass().getSimpleName().equals("DemiAdditionneur"))
    			{
    				cmp.setNombreEntree(i*2);
    				cmp.getLesCoordonnees().setNbCordEntree(i*2);
    			}
    			else {
    				cmp.setNombreEntree(i*2 + 1);
    				cmp.getLesCoordonnees().setNbCordEntree(i*2+1);
    			}   
    			cmp.getLesCoordonnees().setNbCordSorties(i+1);
    			Circuit.getImageFromComp(cmp).setImage(new Image(cmp.generatePath()));
    			Image image = new Image(cmp.generatePath());
    			ImageView imageView = Circuit.getImageFromComp(cmp);
    			imageView.setImage(image);
    			imageView.setFitHeight(image.getHeight());
    			imageView.setFitWidth(image.getWidth());
    			cmp.setNombreSortieAndUpdateFil(i+1);
    			addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(),imageView.getLayoutY() ));
    			
    		}
    		else {
    		this.alert();
    		}
    	}
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void moinsNbEntrees(ActionEvent event) {
    	i --;
    	nbEntres.setText(Integer.toString(i));
    	plusNbEntrees.setVisible(true);
		imgPlusNbEntrees.setVisible(true);
    	if(i==1) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
		}
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


}
