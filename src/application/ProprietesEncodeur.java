package application;

import controllers.ProprietesDemuxController;
import controllers.ProprietesEncodeurController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesEncodeur extends Stage{

	public ProprietesEncodeur() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesEncodeur.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesEncodeurController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
