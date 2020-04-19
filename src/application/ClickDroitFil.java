package application;

import controllers.ClickDroitFilController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import noyau.Composant;

public class ClickDroitFil extends Stage{
	public ClickDroitFil(Polyline line,AnchorPane workSpace,Double x,Double y) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClickDroitFil.fxml"));
			Parent root = loader.load();
			System.out.println(root);
			ClickDroitFilController c=loader.getController();
			c.setLine(line);
			c.setPane(workSpace);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setX(x);
			this.initStyle(StageStyle.UNDECORATED);
			this.setY(y);
			this.setFullScreen(false);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
