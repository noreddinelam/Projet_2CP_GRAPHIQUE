package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import application.Proprietes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClickDroitController extends Controller{

	private ArrayList<String> bddPortes = new ArrayList<String>(Arrays.asList("And","Or","Xor","Nor","Nand"));
	private ArrayList<String> bddBascules = new ArrayList<String>(Arrays.asList("JK","D","RST","T"));
	private static HashMap<String, String> BddFenetre = new HashMap<String, String>();
	static{
		BddFenetre.put("Portes","ProprietesPortes.fxml");
		BddFenetre.put("Bascule","ProprietesBascules.fxml");
		BddFenetre.put("Multiplexeur","ProprietesMux.fxml");
		BddFenetre.put("RegistreDecalage","ProprietesRegistre.fxml");
		BddFenetre.put("Horloge","ProprietesHorloge.fxml");
		BddFenetre.put("Encodeur","ProprietesEncodeur.fxml");
		BddFenetre.put("Decodeur","ProprietesDecodeur.fxml");
		BddFenetre.put("Compteur","ProprietesCompteur.fxml");
		BddFenetre.put("Pin","ProprietesPin.fxml");
		BddFenetre.put("Not","ProprietesNot.fxml");
		BddFenetre.put("Demultiplexeur","ProprietesDemux.fxml");
	}
	@FXML
	private Button copier;

	@FXML
	private Button couper;

	@FXML
	private Button supprimer;

	@FXML
	private Button rotationD;

	@FXML
	private Button rotationG;

	@FXML
	private Button prop;

	@FXML
	void copier(ActionEvent event) {

	}

	@FXML
	void couper(ActionEvent event) {

	}

	@FXML
	void prop(ActionEvent event) {
		String nom = cmp.getClass().getSimpleName(), key;
		
		if(bddPortes.contains(nom)) {
			key = "Portes";
		}else if(bddBascules.contains(nom)){
			key = "Bascule";
		}else {
			key = nom;
			System.out.println(key);
		}
			Proprietes f = new Proprietes(BddFenetre.get(key), cmp);
	}

	@FXML
	void rotationD(ActionEvent event) {

	}

	@FXML
	void rotationG(ActionEvent event) {

	}

	@FXML
	void supprimer(ActionEvent event) {

	}
}