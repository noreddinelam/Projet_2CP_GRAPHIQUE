package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReminderController extends Controller{ /// il sert comme une alerte pour definire des taches que l'user doit appliquer

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
	void ok(ActionEvent event) { /// voir si user à clicker sur le bouton OK de la fenetre du reminder
		
		lessOpacite(); /// deminuer l'opacité des composants
		
		Stage s = (Stage)okButton.getScene().getWindow(); 
		s.close();
	}
}
