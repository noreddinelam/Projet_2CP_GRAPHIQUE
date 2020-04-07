package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableDeProblemesController {

    @FXML
    private TableView<?> tableDeProblemes;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> probleme;

    @FXML
    private TableColumn<?, ?> solution;

    @FXML
    private TableColumn<?, ?> aide;

}
