package controllers;

import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.Proprietes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.CircuitIntegre;
import noyau.CircuitIntegreSequentiel;
import noyau.Composant;

public class ClickDroitController extends Controller implements Initializable{

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
		BddFenetre.put("AdditionneurN_Bites","ProprietesAdditionneur.fxml");
		BddFenetre.put("DemiAdditionneur","ProprietesAdditionneur.fxml");
		BddFenetre.put("SourceConstante","ProprietesSourceConstante.fxml");
		BddFenetre.put("AfficheurSegment","ProprietesAfficheurSegment.fxml");
	}

    @FXML
    private Pane pane;
	
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
	
	static boolean copierColler;

	@FXML
	void copier(ActionEvent event) {
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
    	HomeController.setCopierActive(true);
 	
	}

	@FXML
	void couper(ActionEvent event) {
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
    	
    	HomeController.pastButton = true;
    	
    	HomeController.cc = true;
    	
    	HomeController.setCopierActive(true);	  

    	
        
    	HomeController.copyActive = true;
        
        
        ImageView sauv = HomeController.elementSeclecionner;
        workSpace.getChildren().remove(HomeController.elementSeclecionner);
       
        Composant composantCouper = Circuit.getCompFromImage(HomeController.elementSeclecionner);
       HomeController.composantCopy = composantCouper;
        		
		ArrayList<Polyline> lineListe=Circuit.supprimerComp(composantCouper);
		 for(Polyline line : lineListe)
			 workSpace.getChildren().remove(line);

		 
		 HomeController.elementSeclecionner  = sauv ;
	
    	
	}

	@FXML
	void prop(ActionEvent event) {
		Stage s = (Stage)prop.getScene().getWindow(); 
		HomeController.sauveGarderModification();
    	s.close();
		String nom = cmp.getClass().getSimpleName(), key;
		if(bddPortes.contains(nom)) {
			key = "Portes";
		}else if(bddBascules.contains(nom)){
			key = "Bascule";
		}else {
		
			key = nom;
		}
		Proprietes f = new Proprietes(BddFenetre.get(key), cmp,workSpace, HomeController.homeWindow);
	}

	@FXML
	void rotationD(ActionEvent event) {
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
	}

	@FXML
	void rotationG(ActionEvent event) {
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
	}

	@FXML
	void supprimer(ActionEvent event) {
		
		ImageView imageDeComposant=Circuit.getImageFromComp(cmp);
		HomeController.elementAsuprimer=imageDeComposant;
		HomeController.sauveGarderSupression();		
		if(imageDeComposant.getId().equals("clock"))
		{
			HomeController.horloged =false;
			HomeController.horlogeDeCercuit =null; 
		}
		else if (imageDeComposant.getId().equals("CircuitIntegre")) {
			ArrayList<Circle> arrayList = ((CircuitIntegre)cmp).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		else if (imageDeComposant.getId().equals("CircuitIntegreSequentiel")) {
			ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)cmp).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		workSpace.getChildren().remove(imageDeComposant);
		removeAllPolylinesFromWorkSpace(Circuit.supprimerComp(cmp));		 
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		copier.setCursor(Cursor.HAND);
		couper.setCursor(Cursor.HAND);
		supprimer.setCursor(Cursor.HAND);
		rotationD.setCursor(Cursor.HAND);
		rotationG.setCursor(Cursor.HAND);
		prop.setCursor(Cursor.HAND);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}