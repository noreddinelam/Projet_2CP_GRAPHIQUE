package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.Map.Entry;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import noyau.Affichage;
import noyau.Circuit;
import noyau.EtatLogique;
import noyau.Pin;

public class TableDeVeriteController extends Controller implements Initializable{


    @FXML
    private AnchorPane pane;
	@FXML
	private TableView<ArrayList<String>> tableDeVerite;

	public ObservableList<ArrayList<String>> list = FXCollections.observableArrayList();

	//public ArrayList<TableColumn<ArrayList<String>,String>> ListColonnes = new ArrayList<TableColumn<ArrayList<String>,String>>();
	private ArrayList<String> labels = new ArrayList<String>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		EtatLogique tableVerite[][] = Circuit.tableVerite;
		int j = 0,i = 1;
		int MapSize = ListTextPin.size();
		if(tableVerite.length != 0) { //Les labels des entrees et sorties 
			for (Pin pin : ListTextPin) {
				String entre = pin.getNom();
				if(entre.equals("Pin")) 
					entre = "E"+(MapSize-i);
				labels.add(entre);
				i++;
			}
			/*for (Entry<Pin, Text> entry : ListTextPin.entrySet()) {
				String entre = entry.getKey().getNom();
				if(entre.equals("")) 
					entre = "E"+(MapSize-i);
				labels.add(entre);
				i++;
			}*/
			i=1;
			for ( Affichage sortie : Circuit.sortiesCircuit) {
				String sort = ((Pin)sortie).getNom();
				if(sort.equals("Pin")) 
					sort = "S"+(Circuit.sortiesCircuit.size()-i);
				labels.add(sort);
				i++;
			}

			int nombreLignes = tableVerite.length;
			int nombreColonnes = tableVerite[0].length;

			if(nombreLignes <= 2) {
				tableDeVerite.setPrefHeight(78);
				pane.setPrefHeight(200);
				//tableDeVerite.setLayoutY(175);
			}else if(nombreLignes <= 4) {
				tableDeVerite.setPrefHeight(130);
				pane.setPrefHeight(250);
				//tableDeVerite.setLayoutY(140);
			}
			TableColumn<ArrayList<String>,String> c;
			for( j = 0; j < nombreColonnes; j++) {
				final int a = j;
				c = new TableColumn<ArrayList<String>, String>();
				c.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(a)));
				c.setText(labels.get(j));
				tableDeVerite.getColumns().add(c);
			}
			/*c = new TableColumn<ArrayList<String>, String>();
				c.setPrefWidth(5);
		        c.setResizable(false);
		        tableDeVerite.getColumns().add(ListTextPin.size(),c);*/

			for ( i = 0; i < nombreLignes; i++) {
				ArrayList<String> ligne = new ArrayList<String>();
				for ( j = 0; j < nombreColonnes; j++) {
					ligne.add(Integer.toString(tableVerite[i][j].getNum()));
				}
				list.add(ligne);
			}
			tableDeVerite.setItems(list);
		}
	}

}

