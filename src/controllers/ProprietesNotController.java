package controllers;


import noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProprietesNotController extends ProprietesController{

	private Direction bddDirection[] = {Direction.Nord,Direction.Est,Direction.West,Direction.Sud};
	int direct;
	
    public Composant getNot() {
		return cmp;
	}

	public void setNot(Composant cmp) {
		this.cmp = cmp;
	}
	
	public void initialiser(Composant cmp) {
		this.cmp = cmp;
		direct = 0;
		label.setText(cmp.getNom());
	}


	@FXML
    private TextField label;

    @FXML
    private Button nextDirection;

    @FXML
    private Button previousDirection;

    @FXML
    private Button mdf;

    @FXML
    private Button annuler;

    @FXML
    private Label direction;

    @FXML
    private ImageView imgNextDirection;

    @FXML
    private ImageView imgPreviousDirection;

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void nextDirection(ActionEvent event) {
    	direct ++;
    	if(direct > 3) direct=0;
    	direction.setText(bddDirection[direct].toString());
    }

    @FXML
    void previousDirection(ActionEvent event) {
    	direct--;
    	if(direct < 0) direct = 3;
    	direction.setText(bddDirection[direct].toString());
    }

}
