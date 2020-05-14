package controllers;

import javafx.event.ActionEvent;
import noyau.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProprietesAdditionneurController extends ProprietesController{
	/*
	 * Controlleur de la fenetre Proprietes Additionneur
	 */

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
	
	private int i; //Nombre de bits
    public Composant getcmp() {
		return cmp;
	}

	public void setcmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) { /// initialiser les champs nécessaires pour la fenetre
		btns.add(imgPlusNbEntrees);
		btns.add(imgMoinsNbEntrees);
		this.cmp = cmp;
		sauv = cmp.getNombreEntree();
		i=cmp.getNombreEntree();  
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
		if (! cmp.isDessocier()) {
			plusNbEntrees.setDisable(true);
			moinsNbEntrees.setDisable(true);
			applyOpaciteForImages(btns);
		}
	}
	
    @FXML
    void annuler(ActionEvent event) { /// annuler la modification
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) {
    //Sauvgarder les changements 
    	cmp.setNom(label.getText());
    		if (cmp.isDessocier()) {
    			HomeController.sauveGarderModification();
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
    	Stage s = (Stage)mdf.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void moinsNbEntrees(ActionEvent event) {
    //Decrementer le nombre de bits
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
    //incrementer le nombre de bits
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
