package controllers;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import noyau.Affichage;
import noyau.Circuit;
import noyau.Composant;
import noyau.EtatLogique;
import noyau.Pin;

public class TableDeVerite2Controller extends Controller implements Initializable{
	
	@FXML
	private ListView<HBox> Vbox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	/*@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		HBox Hbox1 = new HBox();
		Vbox.getItems().clear();
		Vbox.getItems().add(Hbox1);
		EtatLogique tableVerite[][] = Circuit.tableVerite;
		if(tableVerite.length != 0) {
			int nombreLignes = tableVerite.length;
			int nombreColonnes = tableVerite[0].length;
			
			double HboxWidth = 0;
			double HboxHeight =0;
			double textPadingLR =0;
			double textPadingTB =0;

			if(nombreLignes <= 4) {
				HboxWidth = 590;
				HboxHeight = (264/nombreLignes)-8;

				for (int i = 0; i < nombreLignes; i++) {
					HBox hb = new HBox();
					hb.setPrefHeight(HboxHeight);
					hb.setPrefWidth(HboxWidth);
					//hb.setStyle("-fx-border-color : #000000");

					textPadingLR = HboxWidth/(nombreColonnes*2);
					textPadingTB = (HboxHeight / 2) -10 ;
					for (int j = 0; j < nombreColonnes; j++) {
						Text txt = new Text();
						txt.setFont(Font.font("Calisto MT",FontWeight.BOLD,18));
						txt.setText(Integer.toString(tableVerite[i][j].getNum()));
						hb.getChildren().add(txt);
						HBox.setMargin(txt, new Insets(textPadingTB,textPadingLR,0,textPadingLR));
					}
					Vbox.getItems().add(hb);
				}
			}else {
				HboxWidth = 590;
			 	HboxHeight = 26.3;
				for (int i = 0; i < nombreLignes; i++) {
					HBox hb = new HBox();
					hb.setPrefHeight(HboxHeight);
					hb.setPrefWidth(HboxWidth);
					//hb.setStyle("-fx-border-color : #000000");

					textPadingLR = HboxWidth/(nombreColonnes*2)-4;
					textPadingTB =  5 ;
					for (int j = 0; j < nombreColonnes; j++) {
						Text txt = new Text();
						txt.setFont(Font.font("Calisto MT",FontWeight.BOLD,18));
						txt.setText(Integer.toString(tableVerite[i][j].getNum()));
						hb.getChildren().add(txt);
						HBox.setMargin(txt, new Insets(textPadingTB,textPadingLR,0,textPadingLR));
					}
					Vbox.getItems().add(hb);
				}
			}
			Hbox1.setStyle("-fx-background-color : #000000");
			Hbox1.setPrefHeight(HboxHeight);
			Hbox1.setPrefWidth(HboxWidth);
			for (Entry<Pin, Text> entry : ListTextPin.entrySet()) {
				Text txt = new Text();
				txt.setFont(Font.font("Calisto MT",20));
				txt.setStroke(Color.WHITE);
				txt.setText(entry.getKey().getNom());
				Hbox1.getChildren().add(txt);
				HBox.setMargin(txt, new Insets(textPadingTB,textPadingLR,0,textPadingLR));
			}
			for ( Affichage sortie : Circuit.sortiesCircuit) {
				Text txt = new Text();
				txt.setFont(Font.font("Calisto MT",20));
				txt.setStroke(Color.WHITE);
				txt.setText(((Pin)sortie).getNom());
				Hbox1.getChildren().add(txt);
				HBox.setMargin(txt, new Insets(textPadingTB,textPadingLR-2,0,textPadingLR-2));
			}
		}

	}*/
}


