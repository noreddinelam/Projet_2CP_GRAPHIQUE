package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;
import noyau.Demultiplexeur;

public class ProprietesDemuxController extends ProprietesController{
	
	String bddNbEntrees[] = {"1X2","1X4","1X8","1X16"};
	int i;
	
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
    
	public void initialiser(Composant cmp) {
		/*
		 * l'initialisations des textes fr la fenetre .
		 */
		btns.add(imgMoinsNbEntrees);
		btns.add(imgPlusNbEntrees);
		this.cmp = cmp;
		i=((Demultiplexeur)cmp).getNbCommande();
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
    void annuler(ActionEvent event) { /// annuler les changements faits
    	
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
    		((Demultiplexeur)cmp).setNbCommande(i);
    		int nbSortie = (int)Math.pow(2, i);
    		cmp.setNombreSortieAndUpdateFil(nbSortie);    	
    		cmp.getLesCoordonnees().setNbCordCommandes(i);
    		cmp.getLesCoordonnees().setNbCordSorties(nbSortie);
    		Image img = new Image(cmp.generatePath());
    		ImageView imageView = Circuit.getImageFromComp(cmp);
    		imageView.setImage(img);
    		imageView.setFitHeight(img.getHeight());
    		imageView.setFitWidth(img.getWidth());
    		addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(),imageView.getLayoutY() ));
    	}
    	Stage s = (Stage)annuler.getScene().getWindow(); 
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
