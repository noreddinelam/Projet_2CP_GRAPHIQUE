
package application;

import controllers.Controller;
import controllers.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import noyau.Composant;


public class ClickBarDroite extends Stage{
	
    private static AnchorPane workSpace;
	

	public ClickBarDroite(int x,int y, String s, Stage st, AnchorPane w,Label label1,Label label2,ScrollPane scrollPane) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(s));
			Parent root = loader.load();
			HomeController h = loader.getController();
			h.setWorkSpace(w);
			h.setAffX(label1);
			h.setAffY(label2);
			h.setScrollPane(scrollPane);
			if(s.equals("Fichier.fxml")) {
				Controller.getRightBareButtons().add(h.getEncapsuler());
				h.getEncapsuler().setDisable(true);
				h.getEncapsuler().setOpacity(0.4);
			}else if (s.equals("Affichage.fxml")) {
				Controller.getRightBareButtons().add(h.getTableVerite());
				Controller.getRightBareButtons().add(h.getChronogramme());
			}
			//Fichier.fxmlAffichage.fxml
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


