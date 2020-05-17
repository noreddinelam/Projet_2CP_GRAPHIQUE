package controllers;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import noyau.Circuit;
import noyau.Horloge;
import noyau.Sauvegarde;

public class PremierePageController implements Initializable{ /// c'est le responsable de l'affichage de la 1iere page de l'application
	@FXML
	private ProgressBar crossBare; /// pour la bar de chargement

	@FXML
	private Pane pane;

	private Stage window;	
	
	private String fileToUpload;
	public void setStage(Stage window) {
		this.window = window;
	}

	class bg_thread implements Runnable /// le thread responsable pour marcher la bar de chargement
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
	public void initialize(URL url, ResourceBundle rb) { /// initialiser les attributs nï¿½cessaires pour que l'application marche
		// TODO Auto-generated method stub
		Thread th = new Thread(new bg_thread());
		th.start();
		new firstScreen().start();
	}
	class  firstScreen extends Thread{ /// le thread responsable de charger la fenetre suivante
		public void run() {
			try {
				Thread.sleep(5000);/// changer le temps  
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Parent root = null;
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Home.fxml"));
						root = (Parent)loader.load();
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						HomeController controller = (HomeController) loader.getController();
						controller.setHomeControllerStage(stage);
						controller.setHomeControllerScene(scene);
						controller.setFileToUpload(fileToUpload);
						controller.inisialiser();
						stage.getIcons().add(new Image("/homePage_icones/miniLogo.png"));
						scene.getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						stage.setScene(scene);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
								(event)-> {
									Alert alert = new Alert(AlertType.CONFIRMATION);
									alert.setContentText(Circuit.getCompUtilises().isEmpty() ? "Voulez-vous vraiment quitter ?" :"Voulez-vous sauvegarder ce circuit avant de quitter ?");
									alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
									alert.initOwner(stage);
									alert.initStyle(StageStyle.UTILITY);
									alert.getButtonTypes().clear();
									ButtonType buttonTypeNon = new ButtonType( Circuit.getCompUtilises().isEmpty() ? "Oui":"Non");
									ButtonType buttonTypeSauvgarder = null;
									ButtonType buttonTypeCancel = new ButtonType("Annuler");
									alert.getButtonTypes().setAll(buttonTypeNon, buttonTypeCancel);
									if(! Circuit.getCompUtilises().isEmpty())
									{
										buttonTypeSauvgarder = new ButtonType("Sauvegarder");
										alert.getButtonTypes().add(buttonTypeSauvgarder);
									}
									Optional<ButtonType> result = alert.showAndWait();	
									if(result.get() != buttonTypeCancel) {
										if (result.get() == buttonTypeSauvgarder){
											if (HomeController.fichierCourant == null) {
												final FileChooser fileChooser = new FileChooser();
												fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
												fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
												File f = fileChooser.showSaveDialog(stage);
												if (f != null) {
													Sauvegarde sauvegarde = new Sauvegarde();
													sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
													Alert a = new Alert(AlertType.INFORMATION);
													a.initOwner(stage);
													a.initStyle(StageStyle.UTILITY);
													a.setContentText("Le circuit est bien sauvegardé");
													a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
													a.showAndWait();
												}
											} else {
												Sauvegarde sauvegarde = new Sauvegarde();
												sauvegarde.saveCiruit(HomeController.fichierCourant.getAbsolutePath());
												Alert a = new Alert(AlertType.INFORMATION);
												a.initOwner(stage);
												a.initStyle(StageStyle.UTILITY);
												a.setContentText("Le circuit est bien sauvegardé");
												a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
												a.showAndWait();
											}
										}
										if ( HomeController.horloged && Controller.simul) {
											Horloge horloge = ((Horloge) Circuit.getCompFromImage(HomeController.horlogeDeCercuit));
											try {
												HomeController.t1.join(1);
												horloge.stop();
											
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
									else {
										event.consume();
									}
								});
						stage.show();
						pane.getScene().getWindow().hide();	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			});	
		}	
	}
	public String getFileToUpload() {
		return fileToUpload;
	}
	public void setFileToUpload(String fileToUpload) {
		this.fileToUpload = fileToUpload;
	}
	
	
}
