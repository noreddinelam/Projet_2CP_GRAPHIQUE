package application;

import controllers.ProprietesDemuxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesDemux extends Stage{

	public ProprietesDemux() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesDemux.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesDemuxController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
