package application;

import controllers.ProprietesNotController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesNot extends Stage{

	public ProprietesNot() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesNot.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesNotController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
