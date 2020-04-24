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
		for (Entry<Composant, ImageView> entry : Circuit.getCompUtilises().entrySet()) {
			Composant cmp = entry.getKey();
			if (! cmp.getClass().getSimpleName().equals("Pin") ) {
				entry.getValue().setOpacity(0.4);
			}else {
				if(! ((Pin)cmp).isInput()) 
					entry.getValue().setOpacity(0.4);
			}
		}
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : Circuit.getfilUtilises().entrySet()) {

			for (InfoPolyline info : entry.getValue()) {
				info.getLinePrincipale().setOpacity(0.4);
			}
		}
		Stage s = (Stage)okButton.getScene().getWindow(); 
		s.close();
	}

}
