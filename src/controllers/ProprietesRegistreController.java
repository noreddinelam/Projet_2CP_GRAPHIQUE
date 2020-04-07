package controllers;

import javafx.event.ActionEvent;
import noyau.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProprietesRegistreController extends ProprietesController{

	int i;
	String bddFront[] = {"Montant","Descendant"};
	int frnt;
	String bddDecalage[] = {"Gauche","Droite"};
	int dcl;
	
	 public Composant getCmp() {
			return cmp;
		}

		public void setCmp(Composant cmp) {
			this.cmp = cmp;
		}

		public void initialiser(Composant cmp){
			this.cmp = cmp;
			label.setText(cmp.getNom());
			i=((RegistreDecalage)cmp).getTaille();
			nbBits.setText(Integer.toString(i));
			frnt=((RegistreDecalage)cmp).getFront().ordinal();
			front.setText(bddFront[frnt]);
			dcl = (((RegistreDecalage)cmp).isDecalageDroite() == true) ? 0 : 1 ;
			Decalage.setText(bddDecalage[dcl]);
			
			if(i==0) {
				moinsNbBits.setVisible(false);
				imgMoinsNbBits.setVisible(false);
			}
			if(i==5){
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
    private Label Decalage;

    @FXML
    private Button nextDecalage;

    @FXML
    private Button previousDecalage;

    @FXML
    private ImageView imgPreviousDecalage;

    @FXML
    private ImageView imgNextDecalage;

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void moinsNbBits(ActionEvent event) {
    	i--;
    	nbBits.setText(Integer.toString(i));
    	plusNbBits.setVisible(true);
		imgPlusNbBits.setVisible(true);
    	if(i==0) {
			moinsNbBits.setVisible(false);
			imgMoinsNbBits.setVisible(false);
    	}
    }

    @FXML
    void nextDecalage(ActionEvent event) {
    	dcl++;
    	if(dcl > 1 ) dcl=0;
    	Decalage.setText(bddDecalage[dcl]);
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
		if(i==5){
			plusNbBits.setVisible(false);
			imgPlusNbBits.setVisible(false);
		}
    }

    @FXML
    void previousDecalage(ActionEvent event) {
    	dcl--;
    	if(dcl < 0 ) dcl=1;
    	Decalage.setText(bddDecalage[dcl]);
    }

    @FXML
    void previousFront(ActionEvent event) {
    	frnt--;
    	if(frnt < 0 ) frnt=1;
    	front.setText(bddFront[frnt]);
    }

}
