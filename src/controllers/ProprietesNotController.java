package controllers;


import noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProprietesNotController extends ProprietesController{

	private String bddDirection[] = {"Est","Sud","West","Nord"}; //base de données de directions
	int direct;
	
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
	
    public Composant getNot() {
		return cmp;
	}

	public void setNot(Composant cmp) {
		this.cmp = cmp;
	}
	
	public void initialiser(Composant cmp) { /// initialiser le nécessaire pour la fenetre
		btns.add(imgNextDirection);
		btns.add(imgPreviousDirection);
		direct = cmp.getDirection();
		direction.setText(bddDirection[direct]);
		this.cmp = cmp;
		direct = 0;
		label.setText(cmp.getNom());
		if (!cmp.isDessocier()) {
			nextDirection.setDisable(true);
			previousDirection.setDisable(true);
			imgNextDirection.setOpacity(0.4);
			imgPreviousDirection.setOpacity(0.4);
		}
	}

    @FXML
    void annuler(ActionEvent event) { /// annuler les changements faits
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void modifier(ActionEvent event) { /// appliquer les modfications faites 
    	cmp.setNom(label.getText());
    	ImageView img= Circuit.getImageFromComp(cmp);
    	if(cmp.isDessocier()) {
    		if(cmp.getDirection() != direct) {
    			HomeController.sauveGarderRotation(cmp, img, cmp.getDirection());
    			cmp.setDirection(direct);
    			removeAllPolylinesFromWorkSpace(Circuit.getListePolylineFromFil(cmp.getSorties()[0]));
    			Image image = new Image(cmp.generatePath());
    			img.setImage(image);
    			img.setFitHeight(image.getHeight());
    			img.setFitWidth(image.getWidth());
    			addAllPolylinesToWorkSpace(cmp.generatePolyline(img.getLayoutX(), img.getLayoutY()));
    		}
    		else {
    			cmp.setCord();
    			Image image = new Image(cmp.generatePath());
    			img.setImage(image);
    			img.setFitHeight(image.getHeight());
    			img.setFitWidth(image.getWidth());
    		}
    	}
    	Stage s = (Stage)annuler.getScene().getWindow(); 
    	s.close();
    }

    @FXML
    void nextDirection(ActionEvent event) { /// changer de direction
    	direct ++;
    	if(direct > 3) direct=0;
    	direction.setText(bddDirection[direct]);
    }

    @FXML 
    void previousDirection(ActionEvent event) {/// changer de direction
    	direct--;
    	if(direct < 0) direct = 3;
    	direction.setText(bddDirection[direct]);
    }

}
