package application;

import controllers.ProprietesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Composant;

public class Proprietes extends Stage{

	public Proprietes(String Fenete,Composant cmp,AnchorPane workSpace, Stage st) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Fenete));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesController c=loader.getController();
			c.setWorkSpace(workSpace);
			c.initialiser(cmp);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setFullScreen(false);
			this.initOwner(st);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
