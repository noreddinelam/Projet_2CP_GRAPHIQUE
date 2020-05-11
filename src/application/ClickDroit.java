package application;


import controllers.ClickDroitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import noyau.Composant;

public class ClickDroit extends Stage{ /// fenetre pour afficher le click droit sur le composant


	public ClickDroit(Composant cmp,Double x,Double y,AnchorPane workSpace, Stage st) { /// afficher
		try
		{			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClickDroit.fxml"));
			Parent root = loader.load();
			ClickDroitController c=loader.getController();
			c.setCmp(cmp);
            c.setWorkSpace(workSpace);
            c.initialiser();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setX(x + st.getX());
			this.initStyle(StageStyle.UNDECORATED);
			this.setY(y + st.getY());
			this.setFullScreen(false);
			this.toBack();
			this.initOwner(st);
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
