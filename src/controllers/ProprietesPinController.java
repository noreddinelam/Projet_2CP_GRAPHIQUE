package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import noyau.*;

public class ProprietesPinController extends ProprietesController{

	private Direction bddDirection[] = {Direction.Nord,Direction.Est,Direction.West,Direction.Sud};
	int direct;
	String bddPut[] = {"Input","Output"};
	int putInt;
	
	
    public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}
	
	public void initialiser(Composant cmp){
		this.cmp = cmp;
		label.setText(cmp.getNom());
		putInt = (((Pin)cmp).getInput() == true) ? 0 : 1 ;
		put.setText(bddPut[putInt]);
		
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
    	String path;
    	int vlr = ((Pin)cmp).getEtat().getNum();
    	if(putInt == 0){
    		((Pin)cmp).setInput(true);
    		cmp.setNombreEntree(0);
    		cmp.setNombreSortie(1);
    		cmp.getLesCoordonnees().setNbCordEntree(0);
    		cmp.getLesCoordonnees().setNbCordSorties(1);
    		path = "/pin/"+Integer.toString(vlr)+bddPut[putInt]+".png";
    	}else {
    		((Pin)cmp).setInput(false);
    		Polyline line = Circuit.getPolylineFromFil(cmp.getSorties()[0]);
    		line.getPoints().clear(); 
    		cmp.setNombreEntree(1);
    		cmp.setNombreSortie(0);
    		cmp.getLesCoordonnees().setNbCordEntree(1);
    		cmp.getLesCoordonnees().setNbCordSorties(0);
    		path = "/pin/"+Integer.toString(vlr)+bddPut[putInt]+".png";
    	}
    	cmp.setCord();
    	Circuit.getImageFromComp(cmp).setImage(new Image(path));
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void nextDirection(ActionEvent event) {
    	direct ++;
    	if(direct > 3) direct=0;
    	direction.setText(bddDirection[direct].toString());
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
    	direction.setText(bddDirection[direct].toString());
    }

    @FXML
    void previousput(ActionEvent event) {
    	putInt--;
    	if(putInt < 0) putInt = 1;
    	put.setText(bddPut[putInt]);
    }

}
