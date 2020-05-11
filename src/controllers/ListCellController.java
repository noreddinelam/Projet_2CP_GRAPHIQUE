package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import noyau.ComposantDeChronogramme;

public class ListCellController extends ListCell<ComposantDeChronogramme>{ /// utiliser dans la construction des cellules de la table de composant qui s'affiche avant le chronogramme
	
	private FXMLLoader mLLoader;
	
    @FXML
    private ImageView imageDeComposant;

    @FXML
    private Label nomDeComposant;

    @FXML
    private HBox hbox;
	
    public ListCellController() {
		super();
		this.setStyle("-fx-background-radius:32 32 32 32;-fx-border-radius:32 32 32 32;-fx-background-color:#e0e0d1");
	
		// TODO Auto-generated constructor stub
	}
    
    @Override
    protected void updateItem(ComposantDeChronogramme composant, boolean empty) {
    	// TODO Auto-generated method stub
    	super.updateItem(composant, empty);
    	if(empty || composant == null) {

    		setText(null);
    		setGraphic(null);
    		this.setStyle("-fx-background-radius:32 32 32 32;-fx-border-radius:32 32 32 32;-fx-background-color:#e0e0d1");

    	} else {
    		if (mLLoader == null) {
    			mLLoader = new FXMLLoader(getClass().getResource("/application/listeCell.fxml"));
    			mLLoader.setController(this);

    			try {
    				mLLoader.load();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}

    		}
    		this.setStyle("-fx-background-radius:32 32 32 32;-fx-border-radius:32 32 32 32");
    		imageDeComposant.setImage(new Image(composant.getIcon()));
    		nomDeComposant.setText(composant.getNom());
    		setText(null);
    		setGraphic(hbox);
    	}
    	
    }

}
