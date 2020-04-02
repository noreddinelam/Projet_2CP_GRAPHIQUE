package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {

    @FXML
    private ImageView hex;

    @FXML
    private ImageView pin;

    @FXML
    private ImageView clock;

    @FXML
    private ImageView vcc;

    @FXML
    private ImageView mass;

    @FXML
    private ImageView and;

    @FXML
    private ImageView or;

    @FXML
    private ImageView xor;

    @FXML
    private ImageView nand;

    @FXML
    private ImageView nor;

    @FXML
    private ImageView not;

    @FXML
    private ImageView jk;

    @FXML
    private ImageView d;

    @FXML
    private ImageView rs;

    @FXML
    private ImageView t;

    @FXML
    private ImageView cpt;

    @FXML
    private ImageView registreDecalge;

    @FXML
    private ImageView mux;

    @FXML
    private ImageView dmux;

    @FXML
    private ImageView dec;

    @FXML
    private ImageView addcomplet;

    @FXML
    private ImageView demiAdd;

    @FXML
    private ImageView enco;

    @FXML
    private ImageView fichier;

    @FXML
    private ImageView edition;

    @FXML
    private ImageView simulation;

    @FXML
    private ImageView affichage;

    @FXML
    private ImageView aide;

    @FXML
    private ImageView darkMode;
    
    
    
    
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
    
    	Tooltip.install(fichier, new Tooltip("fichier"));
    	Tooltip.install(edition, new Tooltip("edition"));
    	Tooltip.install(simulation, new Tooltip("simulation"));
    	Tooltip.install(affichage, new Tooltip("affichage"));
    	Tooltip.install(aide, new Tooltip("aide"));
    
	}

}

