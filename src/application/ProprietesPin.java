package application;

import controllers.ProprietesNotController;
import controllers.ProprietesPinController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesPin extends Stage{

	public ProprietesPin() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesPin.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesPinController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
