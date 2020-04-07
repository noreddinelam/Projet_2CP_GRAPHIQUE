package application;

import controllers.ProprietesCompteurController;
import controllers.ProprietesDecodeurController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesDecodeur extends Stage{

	public ProprietesDecodeur() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesDecodeur.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesDecodeurController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
