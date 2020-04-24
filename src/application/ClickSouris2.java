package application;

import controllers.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClickSouris2 extends Stage{
	public ClickSouris2(Double x,Double y,AnchorPane workSpace, Stage st) {

	try
	{			
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ClickSouris2.fxml"));
		Parent root = loader.load();
		HomeController c=loader.getController();
        c.setWorkSpace(workSpace);
		Scene scene = new Scene(root);
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
	
	public void  setx(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	

}
