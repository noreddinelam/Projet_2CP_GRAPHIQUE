package controllers;

import javafx.event.ActionEvent;
import noyau.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProprietesCompteurController extends ProprietesController{

	int i;
	String bddFront[] = {"Montant","Descendant"};
	int frnt;
	String bddCmp[] = {"Compteur","D�compteur"};
	int cmpt;
		
    public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp){
		this.cmp = cmp;
		label.setText(cmp.getNom());		
		i=cmp.getNombreEntree();
		nbBits.setText(Integer.toString(i));
		frnt=((Compteur)cmp).getFront().ordinal();
		front.setText(bddFront[frnt]);
		cmpt = (((Compteur)cmp).getCompter() == true) ? 0 : 1 ;
		compteur.setText(bddCmp[cmpt]);		
		if(i==2) {
			moinsNbBits.setVisible(false);
			imgMoinsNbBits.setVisible(false);
		}
		if(i==4){
			plusNbBits.setVisible(false);
			imgPlusNbBits.setVisible(false);
		}
	}
	@FXML
    private TextField label;

    @FXML
    private Button plusNbBits;

    @FXML
    private Button moinsNbBits;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private Label nbBits;

    @FXML
    private ImageView imgPlusNbBits;

    @FXML
    private ImageView imgMoinsNbBits;

    @FXML
    private Button nextFront;

    @FXML
    private Button previousFront;

    @FXML
    private ImageView imgPreviousFront;

    @FXML
    private ImageView imgNextFront;

    @FXML
    private Label front;

    @FXML
    private Label compteur;

    @FXML
    private Button nextCompteur;

    @FXML
    private Button previousCompteur;

    @FXML
    private ImageView imgPreviousCompteur;

    @FXML
    private ImageView imgNextCompteur;

    @FXML
    private Label composant;

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {
    	cmp.setNom(label.getText());
    	if (cmp.isDessocier()) {
    		removeAllPolylinesFromWorkSpace(Circuit.supprimerAllPolylinesForCompounent(cmp));
    		cmp.setNombreEntree(i);
    		cmp.setNombreSortieAndUpdateFil(i);
    		if(frnt == 0)
    			((Compteur)cmp).setFront(Front.Front_Montant);
    		else
    			((Compteur)cmp).setFront(Front.Front_Descendant);
    		if(cmpt == 0)
    			((Compteur)cmp).setCompter(true);
    		else
    			((Compteur)cmp).setCompter(false);
    		((Compteur)cmp).setValeurMax((int) (Math.pow(2, i) -1));
    		cmp.getLesCoordonnees().setNbCordEntree(i);
    		cmp.getLesCoordonnees().setNbCordSorties(i);
    		Image img = new Image(cmp.generatePath());
    		ImageView imageView = Circuit.getImageFromComp(cmp);
    		imageView.setImage(img);
    		imageView.setFitHeight(img.getHeight());
    		imageView.setFitWidth(img.getWidth());
    		addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(),imageView.getLayoutY() ));
    	}	else {
    		this.alert();
		}
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void moinsNbBits(ActionEvent event) {
    	i--;
    	nbBits.setText(Integer.toString(i));
    	plusNbBits.setVisible(true);
		imgPlusNbBits.setVisible(true);
    	if(i==2) {
			moinsNbBits.setVisible(false);
			imgMoinsNbBits.setVisible(false);
    	}
    }

    @FXML
    void nextCompteur(ActionEvent event) {
      	cmpt++;
    	if(cmpt > 1 ) cmpt=0;
    	compteur.setText(bddCmp[cmpt]);
    }

    @FXML
    void nextFront(ActionEvent event) {
    	frnt++;
    	if(frnt > 1) frnt=0;
    	front.setText(bddFront[frnt]);
    }

    @FXML
    void plusNbBits(ActionEvent event) {
       	i++;
    	nbBits.setText(Integer.toString(i));
    	moinsNbBits.setVisible(true);
		imgMoinsNbBits.setVisible(true);
		if(i==4){
			plusNbBits.setVisible(false);
			imgPlusNbBits.setVisible(false);
		}
    }

    @FXML
    void previousCompteur(ActionEvent event) {
    	cmpt--;
    	if(cmpt < 0 ) cmpt=1;
    	compteur.setText(bddCmp[cmpt]);
    }

    @FXML
    void previousFront(ActionEvent event) {
    	frnt--;
    	if(frnt < 0 ) frnt=1;
    	front.setText(bddFront[frnt]);
    }
    
    

}
