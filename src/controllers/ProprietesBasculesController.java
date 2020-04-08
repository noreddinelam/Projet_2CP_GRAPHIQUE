package controllers;

import javafx.event.ActionEvent;
import noyau.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProprietesBasculesController extends ProprietesController {

	private String bddNbFront[] = {"Montant","Descendant"};
	private int i;
	
	
	public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
		this.cmp = cmp;
		composant.setText(cmp.getClass().getSimpleName());
		i=((Bascule)cmp).getFront().ordinal();
		label.setText(cmp.getNom());
		front.setText(bddNbFront[i]);
	}
	
    @FXML
    private TextField label;

    @FXML
    private Button nextFront;

    @FXML
    private Button previousFront;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private Label front;

    @FXML
    private ImageView imgNextFront;

    @FXML
    private ImageView imgPreviousFront;

    @FXML
    private Label composant;

    @FXML
    void annuler(ActionEvent event) {
    	
    }

    @FXML
    void modifier(ActionEvent event) {
    }

    @FXML
    void nextFront(ActionEvent event) {
    	i++;
    	if(i > 1) i=0;
    	front.setText(bddNbFront[i]);
    }

    @FXML
    void previousFront(ActionEvent event) {
    	i--;
    	if(i < 0) i=1;
    	front.setText(bddNbFront[i]);
    }

}
