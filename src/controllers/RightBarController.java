package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RightBarController implements Initializable {
	
	
	
	@FXML
    private Button fermer;
	

    @FXML
    private Button supprimerTout;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void enligne(String lien) {//une methode utilise pour ouvrir un lien dans le navigateur par defaut
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
			    stage.initModality(Modality.APPLICATION_MODAL);
		        stage.show();
		    } catch(Exception e) {
		        e.printStackTrace();
		    }

		
	}
	
	
	public void tableDeVerite(ActionEvent event) {
		 try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/TableDeVerite.fxml"));
		        Parent root = fxmlLoader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root);
		        stage.setScene(scene);  
		        stage.setTitle("la table de verité");
		        stage.setResizable(false);
			    stage.initModality(Modality.APPLICATION_MODAL);

		        stage.show();
		    } catch(Exception e) {
		        e.printStackTrace();
		    	}
	}
	
	
	public void chronogramme(ActionEvent event) {
		 try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Chronogramme.fxml"));
		        Parent root = fxmlLoader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root);
		        stage.setScene(scene);  
		        stage.setTitle("le chronogramme");
		        stage.setResizable(false);
			    stage.initModality(Modality.APPLICATION_MODAL);

		        stage.show();
		    } catch(Exception e) {
		        e.printStackTrace();
		    	}
	}
	
	
	public void fermer(ActionEvent event) {
		Stage stage = (Stage) fermer.getScene().getWindow();
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Voullez vous vraimment quitter ! ");
		Optional<ButtonType> result = alert.showAndWait();	    		
		if(result.get() == ButtonType.OK){
		stage.close();
		}
	}
	
	public void nouveau(ActionEvent event) {
		
		 try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Home.fxml"));
		        Parent root = fxmlLoader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root);
		        stage.setScene(scene);  
		        stage.setResizable(false);

		        stage.show();
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		 
	}
	
	
	public void supprimerTout(ActionEvent event) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();
	}

	
	
	

}
