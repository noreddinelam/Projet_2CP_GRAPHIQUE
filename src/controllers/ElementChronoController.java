package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import noyau.Circuit;
import noyau.ComposantDeChronogramme;
import noyau.Horloge;

public class ElementChronoController implements Initializable {

    @FXML
    private ListView<ComposantDeChronogramme> listeCircuit;

    @FXML
    private ListView<ComposantDeChronogramme> listeChrono;
    
    private ObservableList<ComposantDeChronogramme> composantDeListAgauche;
    private ObservableList<ComposantDeChronogramme> composantDeListAdroite;
    @FXML
    private ImageView ajouterBtn;

    @FXML
    private ImageView retirerBtn;
    int i=0;

    @FXML
    private Button chronogrameBtn;

    @FXML
    private Button fermerBtn;

    @FXML
    void mouseClickAjouter(MouseEvent event) {
    	if(listeCircuit.getSelectionModel().getSelectedItem() != null && i<10) 
    	{
    		composantDeListAdroite.add(listeCircuit.getSelectionModel().getSelectedItem());
    		composantDeListAgauche.remove(listeCircuit.getSelectionModel().getSelectedItem());
    		i++;
    		listeCircuit.getSelectionModel().selectIndices(-1);
    	}
    }

    @FXML
    void mouseClickChrono(MouseEvent event) {
    	
		ChronogrammeController.setHorlogeDecHRONO((Horloge) Circuit.getCompFromImage(HomeController.horlogeDeCercuit));
		int i = 0;
		while (i < 10 && i < Circuit.getListeEtages().size()) {
			if (!Circuit.getListeEtages().get(i).getClass().getSimpleName().equals("Compteur")&&composantDeListAdroite.contains(Circuit.getListeEtages().get(i)))
				ChronogrammeController.composantDechrono.add(Circuit.getListeEtages().get(i));
			i++;
		}
		i=0;
		while(i<10 && i<Circuit.getSortiesCircuit().size())
		{
			if(composantDeListAdroite.contains(Circuit.getSortiesCircuit().get(i))) ChronogrammeController.pinDeSorties.add( Circuit.getSortiesCircuit().get(i));
			i++;
		}
		try {
			Stage s= (Stage) fermerBtn.getScene().getWindow();
	        s.close();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Chronogramme.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			scene.setFill(Color.TRANSPARENT);
			stage.initStyle(StageStyle.TRANSPARENT);
			scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
					this::closeWindowEvent);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
    }

    @FXML
    void mouseClickFermer(MouseEvent event) {
    	Stage s= (Stage) fermerBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void mouseClickRetirer(MouseEvent event) {
      	if(listeChrono.getSelectionModel().getSelectedItem() != null &&! listeChrono.getSelectionModel().getSelectedItem().getClass().getSimpleName().equals("Horloge") ) 
    	{
    		composantDeListAgauche.add(listeChrono.getSelectionModel().getSelectedItem());
    		composantDeListAdroite.remove(listeChrono.getSelectionModel().getSelectedItem());
    		i--;
    		listeChrono.getSelectionModel().selectIndices(-1);
    	}

    }

    @FXML
    void mouseEnterAjouter(MouseEvent event) {
    	ajouterBtn.setImage(new Image("/chronoIcones/Groupe 152.png"));
    	

    }

    @FXML
    void mouseEnterChrono(MouseEvent event) {
                chronogrameBtn.setStyle("-fx-background-color:GREEN;-fx-background-radius:15");
          
    }

    @FXML
    void mouseEnterFermer(MouseEvent event) {
                   fermerBtn.setStyle("-fx-background-color:RED;-fx-background-radius:15");
          
    }

    @FXML
    void mouseEnterRetirer(MouseEvent event) {
    	retirerBtn.setImage(new Image("/chronoIcones/Groupe 153.png"));
    	

    }

    @FXML
    void mouseExitAjouter(MouseEvent event) {
    	ajouterBtn.setImage(new Image("/chronoIcones/Groupe 150.png"));

    }

    @FXML
    void mouseExitChrono(MouseEvent event) {
    	 chronogrameBtn.setStyle("-fx-background-color:#e0e0d1;-fx-background-radius:15");
        
    }

    @FXML
    void mouseExitFermer(MouseEvent event) {
        fermerBtn.setStyle("-fx-background-color:#e0e0d1;-fx-background-radius:15");
         
    }

    @FXML
    void mouseExitRetirer(MouseEvent event) {
       	retirerBtn.setImage(new Image("/chronoIcones/Groupe 151.png"));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		composantDeListAgauche = FXCollections.observableArrayList();
		composantDeListAdroite=  FXCollections.observableArrayList();
		composantDeListAdroite.add((Horloge)Circuit.getCompFromImage(HomeController.horlogeDeCercuit));
	if(! Circuit.getListeEtages().isEmpty())	composantDeListAgauche.addAll(Circuit.getListeEtages());
	if(! Circuit.getSortiesCircuit().isEmpty())     composantDeListAgauche.addAll(Circuit.getSortiesCircuit());
	if(!composantDeListAgauche.isEmpty())	    listeCircuit.setItems(composantDeListAgauche);
	if(!composantDeListAdroite.isEmpty())	    listeChrono.setItems(composantDeListAdroite);
		 listeCircuit.setCellFactory(composantDeListAgauche -> new ListCellController());
		 listeChrono.setCellFactory(composantDeListAdroite -> new ListCellController());
	}
	private void closeWindowEvent(WindowEvent event) {
		ChronogrammeController.composantDechrono.clear();
		ChronogrammeController.valeursDesuivis.clear();
		ChronogrammeController.pinDeSorties.clear();
		ChronogrammeController.extTableView.getItems().clear();    		
		ChronogrammeController.first=true;
		ChronogrammeController.i=1;
		Horloge.setStartX(1);
		Horloge.setStartY(76);
		HomeController.chrono=false;
		ChronogrammeController.scrolDeChrono.setHvalue(0);	

	}


}
