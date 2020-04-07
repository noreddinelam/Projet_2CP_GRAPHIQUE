package application;

import controllers.ProprietesHorlogeController;
import controllers.ProprietesMuxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesMux extends Stage{

	public ProprietesMux() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesMux.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesMuxController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
