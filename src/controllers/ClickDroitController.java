package controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import application.Proprietes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.CircuitIntegre;
import noyau.CircuitIntegreSequentiel;
import noyau.Composant;

public class ClickDroitController extends Controller{

	int direct ;
	private ArrayList<String> bddPortes = new ArrayList<String>(Arrays.asList("And","Or","Xor","Nor","Nand")); /// pour afficher les propriétées des portes
	private ArrayList<String> bddBascules = new ArrayList<String>(Arrays.asList("JK","D","RST","T")); /// pour faire les proprieté des bascules
	private static HashMap<String, String> BddFenetre = new HashMap<String, String>(); /// les fenetres nécessaires 
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
    private ImageView imgCopier;

    @FXML
    private ImageView imgCouper;

    @FXML
    private ImageView imgSupprimer;

    @FXML
    private ImageView imgRotationD;

    @FXML
    private ImageView imgProprietes;

    @FXML
    private ImageView imgRotationG;
    
	@FXML
	void copier(ActionEvent event) { /// sert pour copier un composant
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
    	HomeController.setCopierActive(true);
    	HomeController.copyActive = false;
	}

	@FXML
	void couper(ActionEvent event) { /// sert pour couper un composant
		workSpace.getChildren().remove(HomeController.selectionne);
		Stage s = (Stage)prop.getScene().getWindow(); 
		s.close();
		HomeController.pastButton = false;
		HomeController.cc = true;
		HomeController.setCopierActive(true);	  
		HomeController.copyActive = true;
		ImageView sauv = HomeController.elementSeclecionner;
		HomeController.elementAsuprimer = sauv;
		HomeController.sauveGarderSupression();
		HomeController.supprimerDequeFilProbleme(cmp);
		if(sauv.getId().equals("clock")) /// verifier si le composant est une horloge
		{
			HomeController.horloged =false;
			HomeController.horlogeDeCercuit =null; 
		}
		else if (sauv.getId().equals("CircuitIntegre")) { /// verifier si le composant est un circuit intégre
			ArrayList<Circle> arrayList = ((CircuitIntegre)cmp).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		else if (sauv.getId().equals("CircuitIntegreSequentiel")) {/// verifier si le composant est un circuit intégre Sequentiel
			ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)cmp).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		workSpace.getChildren().remove(HomeController.elementSeclecionner);
		Composant composantCouper = Circuit.getCompFromImage(HomeController.elementSeclecionner);
		HomeController.composantCopy = composantCouper;
		ArrayList<Polyline> lineListe=Circuit.supprimerComp(composantCouper);
		for(Polyline line : lineListe)
			workSpace.getChildren().remove(line);
		HomeController.elementSeclecionner  = sauv ;
	}

	@FXML
	void prop(ActionEvent event) { /// afficher les propriétées d'un composant
		//remouver la selection 
		workSpace.getChildren().remove(HomeController.selectionne);
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
		String nom = cmp.getClass().getSimpleName(), key;
		if(bddPortes.contains(nom)) { /// si c'est une porte
			key = "Portes";
		}else if(bddBascules.contains(nom)){ /// si c'est une bascule
			key = "Bascule";
		}else {
		
			key = nom;
		}
	    new Proprietes(BddFenetre.get(key), cmp,workSpace, HomeController.homeWindow); /// avoir une fenetre des propriétées
	}

	@FXML
	void rotationD(ActionEvent event) { /// faire une rotation droite
		workSpace.getChildren().remove(HomeController.selectionne);
		direct ++;
		if(direct > 3) direct=0;
		ImageView img= Circuit.getImageFromComp(cmp);
		HomeController.sauveGarderRotation(cmp, img, cmp.getDirection());
		cmp.setDirection(direct);
		removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(cmp.getSorties()[0]));
		Image image = new Image(cmp.generatePath());
		img.setImage(image);
		img.setFitHeight(image.getHeight());
		img.setFitWidth(image.getWidth());
		addAllPolylinesToWorkSpace(cmp.generatePolyline(img.getLayoutX(), img.getLayoutY()));
	}

	@FXML
	void rotationG(ActionEvent event) { /// faire une rotation gauche
		workSpace.getChildren().remove(HomeController.selectionne);
		direct--;
		if(direct < 0) direct = 3;
		ImageView img= Circuit.getImageFromComp(cmp);
		HomeController.sauveGarderRotation(cmp, img, cmp.getDirection());
		cmp.setDirection(direct);
		removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(cmp.getSorties()[0]));
		Image image = new Image(cmp.generatePath());
		img.setImage(image);
		img.setFitHeight(image.getHeight());
		img.setFitWidth(image.getWidth());
		addAllPolylinesToWorkSpace(cmp.generatePolyline(img.getLayoutX(), img.getLayoutY()));

	}

	@FXML
	void supprimer(ActionEvent event) { /// pour supprimer un composant
		workSpace.getChildren().remove(HomeController.selectionne);
		ImageView imageDeComposant=Circuit.getImageFromComp(cmp);
		HomeController.elementAsuprimer=imageDeComposant;
		HomeController.sauveGarderSupression();
		HomeController.supprimerDequeFilProbleme(cmp);
		if(imageDeComposant.getId().equals("clock")) /// verifier si le composant est une horloge
		{
			HomeController.horloged =false;
			HomeController.horlogeDeCercuit =null; 
		}
		else if (imageDeComposant.getId().equals("CircuitIntegre")) {/// verifier si le composant est un circuit intégre
			ArrayList<Circle> arrayList = ((CircuitIntegre)cmp).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		else if (imageDeComposant.getId().equals("CircuitIntegreSequentiel")) {/// verifier si le composant est un circuit intégre Sequentiel
			ArrayList<Circle> arrayList = ((CircuitIntegreSequentiel)cmp).getListeCercles();
			for (Circle circle : arrayList) {
				workSpace.getChildren().remove(circle);
			}
		} 
		workSpace.getChildren().remove(imageDeComposant);
		removeAllPolylinesFromWorkSpace(Circuit.supprimerComp(cmp));
		HomeController.elementAsuprimer = null;
		HomeController.elementSeclecionner = null;
		Stage s = (Stage)prop.getScene().getWindow(); 
    	s.close();
	}
	public void initialiser() { /// sert pour initialiser les effets nécessaires de la fenetre click droit
		supprimer.setCursor(Cursor.HAND);
		direct = cmp.getDirection();
		if (cmp.getClass().equals(CircuitIntegre.class) || cmp.getClass().equals(CircuitIntegreSequentiel.class)) { /// verifier s'il s'agit d'un circuit intégré
			prop.setDisable(true);
			imgProprietes.setOpacity(0.4);
		}
		else {
			prop.setCursor(Cursor.HAND);
		}
		if ( (!bddPortes.contains(cmp.getClass().getSimpleName()) && !cmp.getClass().getSimpleName().equals("Pin") && !cmp.getClass().getSimpleName().equals("Not"))  || ! cmp.isDessocier()) { 
			rotationD.setDisable(true); /// desactiver la rotation pour les composants a part les portes/pin
			rotationG.setDisable(true);
			imgRotationD.setOpacity(0.4);
			imgRotationG.setOpacity(0.4);
		}
		else {
			rotationD.setCursor(Cursor.HAND);
			rotationG.setCursor(Cursor.HAND);
		}
		if (cmp.getClass().equals(CircuitIntegreSequentiel.class)) {
			copier.setDisable(true);
			imgCopier.setOpacity(0.4);
			couper.setDisable(true);
			imgCouper.setOpacity(0.4);
		}
		else {
			copier.setCursor(Cursor.HAND);
			couper.setCursor(Cursor.HAND);
		}
	}	
	
}