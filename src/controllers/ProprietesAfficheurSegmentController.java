package controllers;


import noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProprietesAfficheurSegmentController extends ProprietesController{
	/*
	 * Controlleur de la fenetre Proprietes Afficheur Hex
	 */

	public Composant getNot() {
		return cmp;
	}

	public void setNot(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
		this.cmp = cmp;          
		composant.setText("Afficheur HEX"); //le nom de la fenetre
	}

	@FXML
	private TextField label;

	@FXML
	private Button mdf;

	@FXML
	private Button annuler;

	@FXML
	private Label composant;
	
	@FXML
	void annuler(ActionEvent event) {
		Stage s = (Stage)annuler.getScene().getWindow(); 
		s.close();
	}

	@FXML
	void modifier(ActionEvent event) {
		cmp.setNom(label.getText()); //Modifier les changements
		Stage s = (Stage)annuler.getScene().getWindow(); 
		s.close();
	}
}
