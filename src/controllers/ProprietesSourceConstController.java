package controllers;

import javafx.application.Platform;
import application.*;
import javafx.event.ActionEvent;
import noyau.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProprietesSourceConstController extends ProprietesController {

	private Direction bddDirection[] = { Direction.Nord, Direction.Est, Direction.West, Direction.Sud }; // base de
																											// données
																											// de
																											// directions
	private int direct; // indice

	public Composant getcmp() {
		return cmp;
	}

	public void setcmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) {
		this.cmp = cmp;
		SourceConstante sourceconstanteComposant = (SourceConstante) cmp;
		if (sourceconstanteComposant.getEtatLogique() != EtatLogique.ONE) {
			composant.setText("Masse");
		} else {
			composant.setText("VCC");
		}

		direct = 0;
//		composant.setText(cmp.getClass().getSimpleName().toString());
		label.setText(cmp.getNom());

	}

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

	@FXML
	void annuler(ActionEvent event) {
		Stage s = (Stage) annuler.getScene().getWindow();
		s.close();
	}

	@FXML
	void modifier(ActionEvent event) {

		cmp.setNom(label.getText());
		if (cmp.isDessocier()) {
			cmp.setCord();
			Circuit.getImageFromComp(cmp).setImage(new Image(cmp.generatePath()));
		} else {
			this.alert();
		}
		Stage s = (Stage) annuler.getScene().getWindow();
		s.close();
	}

	@FXML
	void nextDirection(ActionEvent event) {
		direct++;
		if (direct > 3)
			direct = 0;
		direction.setText(bddDirection[direct].toString());
	}

	@FXML
	void previousDirection(ActionEvent event) {
		direct--;
		if (direct < 0)
			direct = 3;
		direction.setText(bddDirection[direct].toString());
	}

}
