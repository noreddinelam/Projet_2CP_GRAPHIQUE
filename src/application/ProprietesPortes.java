package application;

import controllers.ProprietesPinController;
import controllers.ProprietesPortesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesPortes extends Stage{

	public ProprietesPortes() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesPortes.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesPortesController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
