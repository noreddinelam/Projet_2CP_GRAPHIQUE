package application;

import controllers.ClickDroitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import noyau.Composant;

public class ClickDroit extends Stage{

	public ClickDroit(Composant cmp,Double x,Double y, Stage st) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClickDroit.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ClickDroitController c=loader.getController();
			System.out.println(cmp);
			c.setCmp(cmp);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setX(x);
			this.initStyle(StageStyle.UNDECORATED);
			this.setY(y);
			this.setFullScreen(false);
			this.toBack();
			this.initOwner(st);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
