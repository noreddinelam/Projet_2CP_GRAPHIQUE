package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import noyau.Circuit;
import noyau.CircuitIntegreSequentiel;
import noyau.Composant;
import noyau.ComposantDeChronogramme;
import noyau.Compteur;
import noyau.Fil;
import noyau.Horloge;
import noyau.InfoPolyline;
import noyau.Sequentiels;

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
    private Button afficher;

    @FXML
    private Button fermerBtn;
    @FXML
    private HBox header;
   
    Stage stage ;
    Stage parentStage;
    /////Variables de Drage and drop 
    private static double xOffset = 0;
    private static double yOffset = 0;
    private static double xOffsete = 0;
    private static double yOffsete = 0;
    ///////////////////////////////////
    @FXML
    void onAfficher(MouseEvent event) {///Button d'affichge d'un composant dans le circuit
       if(listeCircuit.getSelectionModel().getSelectedItem() != null ||listeChrono.getSelectionModel().getSelectedItem() != null)
       {
    	   
    	  header.getScene().getWindow().setHeight(50);
    	   if(listeCircuit.getSelectionModel().getSelectedItem() != null )
    		   showComposant((Composant) listeCircuit.getSelectionModel().getSelectedItem());
    	   else showComposant((Composant) listeChrono.getSelectionModel().getSelectedItem());
    	
    	   
       }
    }

    @FXML
    void mouseClickAjouter(MouseEvent event) {//Traintement d'ajout d'un composant au chronogramme
    	if(listeCircuit.getSelectionModel().getSelectedItem() != null && i<10) 
    	{
    		composantDeListAdroite.add(listeCircuit.getSelectionModel().getSelectedItem());
    		composantDeListAgauche.remove(listeCircuit.getSelectionModel().getSelectedItem());
    		i++;
    		listeCircuit.getSelectionModel().selectIndices(-1);
    	}
    }
    @FXML
    void afficherEnter(MouseEvent event) {
   	 afficher.setStyle("-fx-background-color:green;-fx-background-radius:15");


    }

    @FXML
    void afficherExit(MouseEvent event) {
    	 afficher.setStyle("-fx-background-color:#e0e0d1;-fx-background-radius:15");

    }

    @FXML
    void mouseClickChrono(MouseEvent event) {//Button d'affichafge de chronogramme
    	
		ChronogrammeController.setHorlogeDecHRONO((Horloge) Circuit.getCompFromImage(HomeController.horlogeDeCercuit));
		int i = 0;
		while (i < 10 && i < Circuit.getListeEtages().size()) {//Ajout des composant Sequentielle
			if (!Circuit.getListeEtages().get(i).getClass().getSimpleName().equals("Compteur")&&composantDeListAdroite.contains(Circuit.getListeEtages().get(i)))
				ChronogrammeController.composantDechrono.add(Circuit.getListeEtages().get(i));
			i++;
		}
		i=0;
		while(i<10 && i<Circuit.getSortiesCircuit().size())//Ajout des Pins de sorties
		{
			if(composantDeListAdroite.contains(Circuit.getSortiesCircuit().get(i))) ChronogrammeController.pinDeSorties.add( Circuit.getSortiesCircuit().get(i));
			i++;
		}
		try {
			Stage s= (Stage) fermerBtn.getScene().getWindow();
	        s.close();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Chronogramme.fxml"));
			Parent root = fxmlLoader.load();
		    stage= new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			scene.setFill(Color.TRANSPARENT);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setAlwaysOnTop(true);
			stage.initOwner(parentStage);
			scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
					this::closeWindowEvent);
			scene.setFill(Color.TRANSPARENT);
			annulerOpacity();
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		ChronogrammeController.lightBoxH.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset =stage.getX() - event.getScreenX();
                yOffset =stage.getY() - event.getScreenY();
            }
        });
		ChronogrammeController.lightBoxH.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });
	
    }

    @FXML
    void mouseClickFermer(MouseEvent event) {//Traitement de button fermer 
    	Stage s= (Stage) fermerBtn.getScene().getWindow();
    	annulerOpacity();
        s.close();
    }

    @FXML
    void mouseClickRetirer(MouseEvent event) {//Traitement de retirer un composant de chronogramme
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
	public void initialize(URL arg0, ResourceBundle arg1) {//Initialisation de la fentere

		composantDeListAgauche = FXCollections.observableArrayList();
		composantDeListAdroite=  FXCollections.observableArrayList();
		composantDeListAdroite.add((Horloge)Circuit.getCompFromImage(HomeController.horlogeDeCercuit));
		for (Sequentiels sequentiels : Circuit.getListeEtages()) {
			if(! sequentiels.getClass().equals(Compteur.class) && !sequentiels.getClass().equals(CircuitIntegreSequentiel.class))
				composantDeListAgauche.add(sequentiels);
		}
	if(! Circuit.getSortiesCircuit().isEmpty())     composantDeListAgauche.addAll(Circuit.getSortiesCircuit());
	if(!composantDeListAgauche.isEmpty())	    listeCircuit.setItems(composantDeListAgauche);
	if(!composantDeListAdroite.isEmpty())	    listeChrono.setItems(composantDeListAdroite);
		 listeCircuit.setCellFactory(composantDeListAgauche -> new ListCellController());
		 listeChrono.setCellFactory(composantDeListAdroite -> new ListCellController());
		 /////////Traitement de drage and Drop de la fentre/////////////////////////////////////////////////////////////////////////////////
		 header.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				header.getScene().getWindow().setHeight(600);
				
			}
			 
		 });
			header.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffsete =header.getScene().getWindow().getX() - event.getScreenX();
	                yOffsete =header.getScene().getWindow().getY() - event.getScreenY();
	            }
	        });
			header.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	header.getScene().getWindow().setX(event.getScreenX() + xOffsete);
	            	header.getScene().getWindow().setY(event.getScreenY() + yOffsete);
	            }
	        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	private void closeWindowEvent(WindowEvent event) {////Traitement de Exit
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
	
	public void showComposant(Composant composant)//role : afficher le composant dans le circuit
	{
		for (Entry<Composant, ImageView> entry : Circuit.getCompUtilises().entrySet()) {
			Composant cmp = entry.getKey();
			if (! cmp.equals(composant) ) {
				entry.getValue().setOpacity(0.1);
			}
			else entry.getValue().setOpacity(1);
		}
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : Circuit.getfilUtilises().entrySet()) {

			for (InfoPolyline info : entry.getValue()) {
				info.getLinePrincipale().setOpacity(0.1);
			}
		}
		
	}
	public void annulerOpacity() {// annuler l'affichege d'un seule  composant
		for (Entry<Composant, ImageView> entry : Circuit.getCompUtilises().entrySet()) {
		     entry.getValue().setOpacity(1);
		}
		for (Entry<Fil, ArrayList<InfoPolyline>> entry : Circuit.getfilUtilises().entrySet()) {

			for (InfoPolyline info : entry.getValue()) {
				info.getLinePrincipale().setOpacity(1);
			}
		}
		
	}

	public Stage getParentStage() {
		return parentStage;
	}

	public void setParentStage(Stage parentStage) {
		this.parentStage = parentStage;
	}
	

}
