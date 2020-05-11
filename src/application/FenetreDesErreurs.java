package application;

import controllers.TableDeProblemesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FenetreDesErreurs extends Stage { /// pour afficher la fenetre des erreurs
	 public FenetreDesErreurs(Stage stage){
		try
		{			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProblemeEtSolution.fxml"));
			Parent root = loader.load();
			TableDeProblemesController c=loader.getController();
			c.setStage(this);
			c.initialiser();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.setResizable(false);
			this.initStyle(StageStyle.UNDECORATED);
			this.setX(186+stage.getX());
			this.setY(505+stage.getY());
			this.initModality(Modality.APPLICATION_MODAL);
			this.initOwner(stage);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
