package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

public class PremierePageController implements Initializable{
	@FXML
	private ProgressBar crossBare;

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
	}

}
