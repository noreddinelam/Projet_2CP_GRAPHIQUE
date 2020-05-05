package application;

import controllers.ClickDroitController;
import controllers.ClickDroitLabelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import noyau.Composant;

public class ClickDroitLabel extends Stage {
	public ClickDroitLabel(TextArea text,Double x,Double y,AnchorPane workSpace, Stage st) {
		try
		{			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClickDroitLabel.fxml"));
			Parent root = loader.load();
			ClickDroitLabelController c=loader.getController();
		    c.setText(text);
		    c.setWorkSpace(workSpace);
		    c.setFenetre(this);
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
