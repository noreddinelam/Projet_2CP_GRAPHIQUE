
package application;

import controllers.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import noyau.Composant;


public class ClickBarDroite extends Stage{
	
    private static AnchorPane workSpace;
	

	public ClickBarDroite(int x,int y, String s, Stage st, AnchorPane w) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(s));
			Parent root = loader.load();
			System.out.println(root);
			HomeController h = loader.getController();
			h.setWorkSpace(w);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/styleFile/propriete.css").toExternalForm());
			this.setScene(scene);
			this.setX(x);
			this.initStyle(StageStyle.UNDECORATED);
			this.setY(y);
			workSpace = w;
			//
			//this.initModality(Modality.WINDOW_MODAL);
			this.initOwner(st);
			
			this.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AnchorPane getWorkStageFromRightBar() {
		return workSpace;
	}



	

}


