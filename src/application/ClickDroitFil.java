package application;

import controllers.ClickDroitFilController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClickDroitFil extends Stage{ /// pour afficher la fenetre click droit du fil
	public ClickDroitFil(Polyline line,AnchorPane workSpace,Double x,Double y, Stage s) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClickDroitFil.fxml"));
			Parent root = loader.load();
			ClickDroitFilController c=loader.getController();
			c.setLine(line);
			ClickDroitFilController.setPane(workSpace);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setX(x);
			this.initStyle(StageStyle.UNDECORATED);
			this.setY(y);
			this.setFullScreen(false);
			this.initOwner(s);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
