package application;

import controllers.ProprietesEncodeurController;
import controllers.ProprietesHorlogeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesHorloge extends Stage{

	public ProprietesHorloge() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesHorloge.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesHorlogeController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
