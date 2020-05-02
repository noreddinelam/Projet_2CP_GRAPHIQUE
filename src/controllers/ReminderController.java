package controllers;

import java.util.ArrayList;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;
import noyau.Fil;
import noyau.InfoPolyline;
import noyau.Pin;

public class ReminderController extends Controller{

	@FXML
	private Text titreAlerte;

	@FXML
	private Text messageAlerte;

	@FXML
	private Button okButton;

	@FXML
	private Text messageAlerte1;

	@FXML
	private Text messageAlerte11;

	@FXML
	void ok(ActionEvent event) {
		
		lessOpacite();
		
		Stage s = (Stage)okButton.getScene().getWindow(); 
		s.close();
	}
}
