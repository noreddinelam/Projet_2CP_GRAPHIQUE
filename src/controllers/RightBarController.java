package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RightBarController implements Initializable {
	
	
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void enligne(String lien) {
		try {
			Desktop.getDesktop().browse(new URL(lien).toURI());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void siteWeb(ActionEvent event) {
		enligne("https://simulini.netlify.com");
	}
	
	public void aideEnLigne(ActionEvent event) {
		enligne("https://simulini.netlify.com/page-2/");
	}
	
	
	
	
	
	public void aboutSimulIni(ActionEvent event) {
	
		 try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/About.fxml"));
		        Parent root1 = fxmlLoader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("/styleFile/about.css").toExternalForm());
		        stage.setScene(scene);  
		        stage.setTitle("About SimulINI");
		        stage.setResizable(false);
		        stage.show();
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		

		
	
		
	}
	
	
	

}
