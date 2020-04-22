package application;

import controllers.TableDeProblemesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FenetreDesErreurs extends Stage {
	 public FenetreDesErreurs(Stage stage){
		try
		{			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProblemeEtSolution.fxml"));
			Parent root = loader.load();
			TableDeProblemesController c=loader.getController();
			c.initialiser();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.initOwner(stage);
			//this.initModality(Modality.WINDOW_MODAL);
			this.initModality(Modality.APPLICATION_MODAL);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
