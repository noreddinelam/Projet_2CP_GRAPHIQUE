package controllers;

import noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProprietesDecodeurController extends ProprietesController{

	private String bddNbEntrees[] = {"1X2","2X4","3X8","4X16"};
	private int i;
	
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
    private ImageView imgMoinsNbEntrees;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private Label direction;

    @FXML
    private ImageView imgPlusNbEntrees;

    @FXML
    private ImageView imgNextDirection;

    @FXML
    private ImageView imgPreviousDirection;

    @FXML
    private Label composant;
	
    public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
	/*
	 * l'initialisations des textes fr la fenetre .
	 */
		btns.add(imgMoinsNbEntrees);
		btns.add(imgPlusNbEntrees);
		this.cmp = cmp;
		i=cmp.getNombreEntree();
		label.setText(cmp.getNom());
		nbEntres.setText(bddNbEntrees[i-1]);
		if(i==1) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
		}
		if(i==4){
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
    void annuler(ActionEvent event) { /// annuler les modifications faites
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
    		int nbSortie = (int)Math.pow(2, i);
    		cmp.setNombreSortieAndUpdateFil(nbSortie);
    		cmp.setNombreEntree(i);
    		cmp.getLesCoordonnees().setNbCordEntree(i);
    		cmp.getLesCoordonnees().setNbCordSorties(nbSortie);
    		Image img = new Image(cmp.generatePath());
    		ImageView imageView = Circuit.getImageFromComp(cmp);
    		imageView.setImage(img);
    		imageView.setFitHeight(img.getHeight());
    		imageView.setFitWidth(img.getWidth());
    		addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(),imageView.getLayoutY() ));
    	}
    	Stage s = (Stage)mdf.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void moinsNbEntrees(ActionEvent event) {
    //decrementer le nombre d'entrées
    	i--;
    	nbEntres.setText(bddNbEntrees[i-1]);
    	plusNbEntrees.setVisible(true);
		imgPlusNbEntrees.setVisible(true);
    	if(i==1) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
    	}
    }


    @FXML
    void plusNbEntrees(ActionEvent event) {
    	//incrementer le nombre d'entrées
    	i++;
    	nbEntres.setText(bddNbEntrees[i-1]);
    	moinsNbEntrees.setVisible(true);
		imgMoinsNbEntrees.setVisible(true);
		if(i==4){
			plusNbEntrees.setVisible(false);
			imgPlusNbEntrees.setVisible(false);
		}
    }

}
