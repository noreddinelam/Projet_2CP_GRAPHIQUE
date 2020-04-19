package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;

public class ProprietesHorlogeController extends ProprietesController{

    @FXML
    private TextField label;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private TextField frequance;

    @FXML
    void annuler(ActionEvent event) {
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) {
    	cmp.setNom(label.getText());
    	Stage s = (Stage)mdf.getScene().getWindow(); 
    	s.close();
    }

	@Override
	public void initialiser(Composant cmp) {
	this.cmp=cmp;
	label.setText(cmp.getNom());
	
		
	}

}
