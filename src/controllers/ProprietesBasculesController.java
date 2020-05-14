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

public class ProprietesBasculesController extends ProprietesController {

	private String bddNbFront[] = {"Montant","Descendant"};
	private int i;   //Nombre d'entrées
	
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

	public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) { /// initialiser les champs nécessaires pour la fenetre
		btns.add(imgNextFront);
		btns.add(imgPreviousFront);
		this.cmp = cmp;
		composant.setText(cmp.getClass().getSimpleName());
		i=((Bascule)cmp).getFront().ordinal();
		label.setText(cmp.getNom());
		front.setText(bddNbFront[i]);
		if (! cmp.isDessocier()) { /// desactiver la rotation pour ce composant
			nextFront.setDisable(true);
			previousFront.setDisable(true);
			applyOpaciteForImages(btns);
		}
	}

	@FXML
	void annuler(ActionEvent event) {
		Stage s = (Stage)annuler.getScene().getWindow(); 
		s.close();
	}

	@FXML
	void modifier(ActionEvent event) {
		//Sauvgarder les changements
		cmp.setNom(label.getText());
		if (cmp.isDessocier()) { //il faut que le composant serai dessocier 
			HomeController.sauveGarderModification();
			if(i == 0)
				((Bascule)cmp).setFront(Front.Front_Montant);
			else
				((Bascule)cmp).setFront(Front.Front_Descendant);

			Circuit.getImageFromComp(cmp).setImage(new Image(cmp.generatePath()));
		}
		Stage s = (Stage)annuler.getScene().getWindow(); 
		s.close();
	}

	@FXML
	void nextFront(ActionEvent event) {
		//changer le front
		i++;
		if(i > 1) i=0;
		front.setText(bddNbFront[i]);
	}

	@FXML
	void previousFront(ActionEvent event) {
		//changer le front
		i--;
		if(i < 0) i=1;
		front.setText(bddNbFront[i]);
	}

}
