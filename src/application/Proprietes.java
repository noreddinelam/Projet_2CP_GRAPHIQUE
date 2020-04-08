package application;

import controllers.ProprietesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import noyau.Composant;

public class Proprietes extends Stage{

	public Proprietes(String Fenete,Composant cmp) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Fenete));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesController c=loader.getController();
			c.initialiser(cmp);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}