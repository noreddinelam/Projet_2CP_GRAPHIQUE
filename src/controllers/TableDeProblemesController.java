package controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import noyau.Circuit;
import noyau.ExceptionProgramme;
import noyau.TypesExceptions;

public class TableDeProblemesController {

    @FXML
    private TableView<ExceptionProgramme> tableDeProblemes; /// tables ou s'affiche les problemes à resoudre dans le circuit

    @FXML
    private TableColumn<ExceptionProgramme,String > type; /// la colonne du type de l'erreur
    
    @FXML
    private TableColumn<ExceptionProgramme,String> probleme; /// la colonne du probleme

    @FXML
    private TableColumn<ExceptionProgramme,String> solution; /// la colonne de la solution

    private Stage stage; /// sert pour l'affichage
    
    @FXML
    private Button documentation; /// le bouton qui rammenne vers l'aide en ligne
    
    @FXML
	private Button fermer; /// pour fermer la fenetre des erreurs

	@FXML
    void onDocumente(ActionEvent event) { /// elle vous derige vers la documentation en ligne
		HomeController.enligne("https://simulini.netlify.app/page-7/");
    }

	@FXML
	void fermerTableDesErreurs(ActionEvent event) { /// fermer la fenetre des erreurs et retourner vers le mode de creation de circuit
		if (Circuit.isThereAnyError()) { /// voir s'il y'avait des problemes dans le circuit
 			Circuit.defaultColorFil(); /// affecter la valeurs par defaut au fils
		}
		stage.close();
	}
    
    public void initialiser() { /// sert pour le remplissage de la table des erreurs
    	type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeExceptions())); ///
    	probleme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProblem()));    /// remplire les colonnes
     	solution.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSolution()));   ///
    	type.setCellFactory(new Callback<TableColumn<ExceptionProgramme,String>, TableCell<ExceptionProgramme,String>>() {

			@Override
			public TableCell<ExceptionProgramme, String> call(TableColumn<ExceptionProgramme, String> arg0) {
				// TODO Auto-generated method stub
				return new TableCell<ExceptionProgramme, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                    	if (!empty) {
                    		if (item.equals(TypesExceptions.ALERTE.getTypeExString())) {
                    			setStyle("-fx-background-color: #ff9966");
                    			setText(TypesExceptions.ALERTE.getTypeExString());
                    		}
                    		else {
                    			setStyle("-fx-background-color: red");
                    			setText(TypesExceptions.ERREUR.getTypeExString());
							}
                    		setBorder(new Border(new BorderStroke(Color.WHITE ,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                        }
                    }
                };
			}
		});
    	tableDeProblemes.getItems().addAll(Circuit.getListeExceptionProgrammes()); /// ajout des exceptions détecté dans le circuit
    	Platform.setImplicitExit(false);
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    	    @Override
    	    public void handle(WindowEvent event) {
    	        event.consume();
    	    }
    	});
    	
    }
    
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
