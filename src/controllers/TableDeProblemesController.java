package controllers;

import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    void onDocumente(ActionEvent event) {

    }
    
    public void initialiser() {
    	type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeExceptions()));
    	probleme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProblem()));
    	solution.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSolution()));
    	tableDeProblemes.getItems().addAll(Circuit.getListeExceptionProgrammes());
    }

}
