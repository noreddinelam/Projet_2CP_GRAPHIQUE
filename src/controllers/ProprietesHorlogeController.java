package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;
import noyau.Horloge;

public class ProprietesHorlogeController extends ProprietesController{

    @FXML
    private TextField label;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private TextField frequance;

    @FXML
    void annuler(ActionEvent event) {
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) {
    	cmp.setNom(label.getText());
    	Horloge.temps=arrondi((1/Double.parseDouble(frequance.getText()))*1000, 0);
    	Stage s = (Stage)mdf.getScene().getWindow(); 
    	s.close();
    }

	@Override
	public void initialiser(Composant cmp) {
	this.cmp=cmp;
	double tempH =(double)Horloge.temps;
	frequance.setText(String.valueOf(arrondiDouble(1/(tempH/1000),2)));
	frequance.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                frequance.setText(oldValue);
            }
        }
    });
	label.setText(cmp.getNom());
	
		
	}
	private long arrondi(double A, int B) {
		  return (long) ((double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B));
		}
	public static double arrondiDouble(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
		}

}
