package controllers;


import javafx.event.ActionEvent;
import noyau.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProprietesSourceConstController extends ProprietesController {																										// direction
	
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
	private Button mdf;

	@FXML
	private Button annuler;

	@FXML
	private Label direction;

	@FXML
	private Label composant;

	@FXML
	private ImageView imgPlusNbEntrees;

	@FXML
	private ImageView imgMoinsNbEntrees;

	@FXML
	private ImageView imgNextDirection;

	@FXML
	private ImageView imgPreviousDirection;
	
	public Composant getcmp() {
		return cmp;
	}

	public void setcmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) { /// initialiser le nécessaire pour la fenetre
		this.cmp = cmp;
		SourceConstante sourceconstanteComposant = (SourceConstante) cmp;
		if (sourceconstanteComposant.getEtatLogique() != EtatLogique.ONE) {
			composant.setText("Masse"); ///voir si il s'agit d'une masse
		} else {
			composant.setText("VCC"); /// voir s'il s'agit d'un vcc
		}

		label.setText(cmp.getNom());
		if (! cmp.isDessocier()) {
			applyOpaciteForImages(btns);
		}

	}

	@FXML
	void annuler(ActionEvent event) { /// annuler les modifications faites
		Stage s = (Stage) annuler.getScene().getWindow();
		s.close();
	}

	@FXML
	void modifier(ActionEvent event) { /// appliquer les modifications faites

		cmp.setNom(label.getText());
		if (cmp.isDessocier()) {
			HomeController.sauveGarderModification();
			cmp.setCord();
			Circuit.getImageFromComp(cmp).setImage(new Image(cmp.generatePath()));
		} 
		Stage s = (Stage) mdf.getScene().getWindow();
		s.close();
	}
}
