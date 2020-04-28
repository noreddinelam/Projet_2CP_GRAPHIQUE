package controllers;

import java.util.Observable;



import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import noyau.Circuit;
import noyau.ExceptionProgramme;
import noyau.TypesExceptions;

public class TableDeProblemesController {

    @FXML
    private TableView<ExceptionProgramme> tableDeProblemes;

    @FXML
    private TableColumn<ExceptionProgramme,String > type;
    
    @FXML
    private TableColumn<ExceptionProgramme,String> probleme;

    @FXML
    private TableColumn<ExceptionProgramme,String> solution;

    @FXML
    private Button documentation;
    
    private Stage stage;

    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
    void onDocumente(ActionEvent event) {

    }
	
	@FXML
	private Button fermer;

	@FXML
	void fermerTableDesErreurs(ActionEvent event) {
		if (Circuit.isThereAnyError()) {
			Circuit.defaultColorFil();
		}
		stage.close();
	}
    
    public void initialiser() {
    	type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeExceptions()));
    	probleme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProblem()));
    	solution.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSolution()));
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
    	tableDeProblemes.getItems().addAll(Circuit.getListeExceptionProgrammes());
    	Platform.setImplicitExit(false);
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    	    @Override
    	    public void handle(WindowEvent event) {
    	        event.consume();
    	    }
    	});
    	
    }

}
