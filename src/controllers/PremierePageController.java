package controllers;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PremierePageController implements Initializable{
	@FXML
	private ProgressBar crossBare;
	
	@FXML
    private Pane pane;

	class bg_thread implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0;i < 110; i++)
			{

				try {
					crossBare.setProgress(i / 100.0);
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		Thread th = new Thread(new bg_thread());
		th.start();
		new firstScreen().start();
	}
	
	
	
	class  firstScreen extends Thread{
		public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
;
						
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						stage.setScene(scene);
						stage.show();
						pane.getScene().getWindow().hide();					
						

					}
				});
				

			
		}
		
	
		
		
		
		
	}
	

}
