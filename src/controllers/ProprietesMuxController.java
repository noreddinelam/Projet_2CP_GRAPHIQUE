package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;
import noyau.Demultiplexeur;
import noyau.Direction;
import noyau.Multiplexeur;

public class ProprietesMuxController extends ProprietesController {

	String bddNbEntrees[] = {"2X1","4X1","8X1","16X1"};
	int i;

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
	private ImageView imgMoinsNbEntrees;

	@FXML
	private Button mdf;

	@FXML
	private Button annuler;

	@FXML
	private Label direction;

	@FXML
	private ImageView imgPlusNbEntrees;

	@FXML
	private ImageView imgNextDirection;

	@FXML
	private ImageView imgPreviousDirection;

	@FXML
	private Label composant;

	public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public void initialiser(Composant cmp) { /// initialiser le nécessaire dans la fenetre
		btns.add(imgMoinsNbEntrees);
		btns.add(imgPlusNbEntrees);
		this.cmp = cmp;
		i=((Multiplexeur)cmp).getNbCommande();
		label.setText(cmp.getNom());
		nbEntres.setText(bddNbEntrees[i-1]);
		if(i==1) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
		}
		if(i==4){
			plusNbEntrees.setVisible(false);
			imgPlusNbEntrees.setVisible(false);
		}
		if (! cmp.isDessocier()) {
			plusNbEntrees.setDisable(true);
			moinsNbEntrees.setDisable(true);
			applyOpaciteForImages(btns);
		}
	}

	@FXML
	void annuler(ActionEvent event) { /// annuler les changements faits
		Stage s = (Stage)annuler.getScene().getWindow(); 
		s.close();
	}

	@FXML
	void modifier(ActionEvent event) { /// appliquer les modifications faites
		cmp.setNom(label.getText());
		if (cmp.isDessocier()) {
			HomeController.sauveGarderModification();
			removeAllPolylinesFromWorkSpace(Circuit.supprimerAllPolylinesForCompounent(cmp));
			((Multiplexeur)cmp).setNbCommande(i);
			int nbEntree = (int)Math.pow(2, i);
			cmp.setNombreEntree(nbEntree);
			cmp.setCord();
			cmp.getLesCoordonnees().setNbCordEntree(nbEntree);
			cmp.getLesCoordonnees().setNbCordCommandes(i);
			ImageView imageView = Circuit.getImageFromComp(cmp);
			Image image = new Image(cmp.generatePath());
			imageView.setImage(image);
			imageView.setFitHeight(image.getHeight());
			imageView.setFitWidth(image.getWidth());
			addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(),imageView.getLayoutY() ));
		}
		Stage s = (Stage)mdf.getScene().getWindow(); 
		s.close();
	}

	@FXML
	void moinsNbEntrees(ActionEvent event) { /// deminuer le nombre de bits
		i--;
		nbEntres.setText(bddNbEntrees[i-1]);
		plusNbEntrees.setVisible(true);
		imgPlusNbEntrees.setVisible(true);
		if(i==1) {
			moinsNbEntrees.setVisible(false);
			imgMoinsNbEntrees.setVisible(false);
		}
	}


	@FXML
	void plusNbEntrees(ActionEvent event) { /// augmenter le nombre de bits 
		i++;
		nbEntres.setText(bddNbEntrees[i-1]);
		moinsNbEntrees.setVisible(true);
		imgMoinsNbEntrees.setVisible(true);
		if(i==4){
			plusNbEntrees.setVisible(false);
			imgPlusNbEntrees.setVisible(false);
		}
	}


}
