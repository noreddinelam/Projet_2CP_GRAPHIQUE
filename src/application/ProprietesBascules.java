package application;


import controllers.ProprietesBasculesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProprietesBascules extends Stage {
	
	 public ProprietesBascules() {
		super();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProprietesBascules.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ProprietesBasculesController c=loader.getController();
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

