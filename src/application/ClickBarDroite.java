
package application;

import controllers.HomeController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import noyau.Composant;


public class ClickBarDroite extends Stage{

	public ClickBarDroite(int x,int y, String s, Stage st) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(s));
			Parent root = loader.load();
			System.out.println(root);
			HomeController h = loader.getController();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setX(x);
			this.initStyle(StageStyle.UNDECORATED);
			this.setY(y);
			//
			//this.initModality(Modality.WINDOW_MODAL);
			this.initOwner(st);
			
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}


