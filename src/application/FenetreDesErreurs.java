package application;

import controllers.TableDeProblemesController;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FenetreDesErreurs extends Stage {
	 public FenetreDesErreurs(Stage stage){
		try
		{			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProblemeEtSolution.fxml"));
			Parent root =(Parent) loader.load();
			TableDeProblemesController c=loader.getController();
			c.setStage(this);
			c.initialiser();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.setResizable(false);
			//this.initModality(Modality.WINDOW_MODAL);
			this.initStyle(StageStyle.UNDECORATED);
			this.setX(185);
			this.setY(500);
			this.initModality(Modality.APPLICATION_MODAL);
			this.initOwner(stage);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
