package controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;
import noyau.Direction;
import noyau.Pin;

public class ProprietesPinController extends ProprietesController{

	private String bddDirection[] = {"Est","Sud","West","Nord"}; //base de données de directions
	int direct;
	String bddPut[] = {"Entrée","Sortie"};
	int putInt;
	
	
    @Override
	public Composant getCmp() {
		return cmp;
	}

	@Override
	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}
	
	@Override
	public void initialiser(Composant cmp){
		btns.add(imgNextput);
		btns.add(imgNextput1);
		btns.add(imgPreviousput);
		btns.add(imgPreviousput1);
		this.cmp = cmp;
		direct = cmp.getDirection();
		direction.setText(bddDirection[direct]);
		label.setText(cmp.getNom());
		putInt = (((Pin)cmp).getInput() == true) ? 0 : 1 ;
		put.setText(bddPut[putInt]);
		direct = cmp.getDirection();
		direction.setText(bddDirection[direct]);
		if (! cmp.isDessocier()) {
			nextDirection.setDisable(true);
			previousDirection.setDisable(true);
			nextput.setDisable(true);
			previousput.setDisable(true);
			applyOpaciteForImages(btns);
		}
		
	}

	@FXML
    private TextField label;

    @FXML
    private Button nextput;

    @FXML
    private Button previousput;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private Label put;

    @FXML
    private ImageView imgNextput;

    @FXML
    private ImageView imgPreviousput;

    @FXML
    private Button nextDirection;

    @FXML
    private Button previousDirection;

    @FXML
    private ImageView imgPreviousput1;

    @FXML
    private ImageView imgNextput1;

    @FXML
    private Label direction;

    @FXML
    void annuler(ActionEvent event) {
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) {
    	cmp.setNom(label.getText());
    	if (cmp.isDessocier()) {
        	Pin pin =((Pin)cmp);
        	ImageView imageView = Circuit.getImageFromComp(cmp);
        	if(putInt == 0){
        		if (! pin.getInput()) {
        			pin.setInput(true);
        			Circuit.getSortiesCircuit().remove(pin);
        			Circuit.getEntreesCircuit().add(pin);
        			addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(), imageView.getLayoutY()));
            		cmp.setNombreEntree(0);
            		cmp.setNombreSortie(1);
            		cmp.getLesCoordonnees().setNbCordEntree(0);
            		cmp.getLesCoordonnees().setNbCordSorties(1);
				}
        	}else if( pin.getInput()){
        		pin.setInput(false);
    			Circuit.getEntreesCircuit().remove(pin);
    			Circuit.getSortiesCircuit().add(pin);

        		ArrayList<Polyline> line = Circuit.supprimerAllPolylinesForCompounent(pin);
        		removeAllPolylinesFromWorkSpace(line);
        		cmp.setNombreEntree(1);
        		cmp.setNombreSortie(0);
        		cmp.getLesCoordonnees().setNbCordEntree(1);
        		cmp.getLesCoordonnees().setNbCordSorties(0);
        	}
//        	cmp.setCord();
//        	Image image = new Image(cmp.generatePath());
//        	imageView.setImage(image);
//        	imageView.setFitHeight(image.getHeight());
//        	imageView.setFitWidth(image.getWidth());
        	if(cmp.getDirection() != direct) {
    			HomeController.sauveGarderRotation(cmp, imageView, cmp.getDirection());
    			cmp.setDirection(direct);
    			removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(cmp.getSorties()[0]));
    			Image image = new Image(cmp.generatePath());
    			imageView.setImage(image);
    			imageView.setFitHeight(image.getHeight());
    			imageView.setFitWidth(image.getWidth());
    			addAllPolylinesToWorkSpace(cmp.generatePolyline(imageView.getLayoutX(), imageView.getLayoutY()));
    		}
    		else {
    			cmp.setCord();
    			Image image = new Image(cmp.generatePath());
    			imageView.setImage(image);
    			imageView.setFitHeight(image.getHeight());
    			imageView.setFitWidth(image.getWidth());
			}
		} 	
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void nextDirection(ActionEvent event) {
    	direct ++;
    	if(direct > 3) direct=0;
    	direction.setText(bddDirection[direct]);
    }

    @FXML
    void nextput(ActionEvent event) {
    	putInt++;
    	if(putInt > 1) putInt = 0;
    	put.setText(bddPut[putInt]);
    }

    @FXML
    void previousDirection(ActionEvent event) {
    	direct--;
    	if(direct < 0) direct = 3;
    	direction.setText(bddDirection[direct]);
    }

    @FXML
    void previousput(ActionEvent event) {
    	putInt--;
    	if(putInt < 0) putInt = 1;
    	put.setText(bddPut[putInt]);
    }

}
