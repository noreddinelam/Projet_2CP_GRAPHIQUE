package application;

import java.io.File;

import java.util.Optional;


import controllers.Controller;
import controllers.HomeController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import noyau.Bascule;
import noyau.Circuit;
import noyau.D;
import noyau.Front;
import noyau.Horloge;
import noyau.Not;
import noyau.Sauvegarde;

public class Main extends Application {
	private Stage pStage;
	@Override
	public void start(Stage primaryStage) {
		try {
//

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));

			Parent root = (Parent) loader.load();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());

			HomeController controller = (HomeController) loader.getController();
			controller.setHomeControllerStage(primaryStage);
			controller.setHomeControllerScene(scene);
			controller.inisialiser();

			/*
			 * FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
			 * Parent root = (Parent) fxmlLoader.load(); HomeController controller = new
			 * HomeController(primaryStage);
			 */

//					 	FadeTransition fade = new FadeTransition();
//			         	fade.setDuration(Duration.millis(1000));
//			         	fade.setDelay(Duration.millis(4000));
//				        fade.setFromValue(10);
//				        fade.setToValue(0.1);
//				        fade.setCycleCount(0);
//				        fade.setAutoReverse(true);
//				        fade.setNode(root);
//				        fade.play();
			// Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/homePage_icones/miniLogo.png"));
			primaryStage.setTitle("SimulINI");
			primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
					this::closeWindowEvent);
		    primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setResizable(false);
			pStage=primaryStage;
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeWindowEvent(WindowEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(Circuit.getCompUtilises().isEmpty() ? "Voulez vous vraiment quitter" :"Voullez vous sauvgarder ce circuit avant de quitter ?");
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
		alert.initOwner(pStage);
		alert.initStyle(StageStyle.UTILITY);
		alert.getButtonTypes().clear();
		alert.initOwner(pStage);
		alert.initStyle(StageStyle.UTILITY);
		alert.setX(pStage.getX()+500);
		alert.setY(pStage.getY()+250);
		ButtonType buttonTypeNon = new ButtonType( Circuit.getCompUtilises().isEmpty() ? "Oui":"Non");
		ButtonType buttonTypeSauvgarder = null;
		ButtonType buttonTypeCancel = new ButtonType("Annuler");
		alert.getButtonTypes().setAll(buttonTypeNon, buttonTypeCancel);
		if(! Circuit.getCompUtilises().isEmpty())
		{
			buttonTypeSauvgarder = new ButtonType("Sauvgarder");
			alert.getButtonTypes().add(buttonTypeSauvgarder);
		}
		Optional<ButtonType> result = alert.showAndWait();	
		//Optional<ButtonType> result = alert.showAndWait();
		if(result.get() != buttonTypeCancel) {
			if (result.get() == buttonTypeSauvgarder){
				if (HomeController.fichierCourant == null) {
					final FileChooser fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SIM", "*.sim"));
					File f = fileChooser.showSaveDialog(pStage);
					if (f != null) {

						Sauvegarde sauvegarde = new Sauvegarde();
						sauvegarde.saveCiruit(f.getAbsolutePath() + ".sim");
						Alert a = new Alert(AlertType.INFORMATION);
						a.initOwner(pStage);
						a.initStyle(StageStyle.UTILITY);
						a.setContentText("le circuit est bien sauvgarde");
						a.initOwner(pStage);
						a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						a.initStyle(StageStyle.UTILITY);
						a.setX(pStage.getX()+500);
						a.setY(pStage.getY()+250);
						a.showAndWait();
					}
				} else {
					Sauvegarde sauvegarde = new Sauvegarde();
					sauvegarde.saveCiruit(HomeController.fichierCourant.getAbsolutePath());
					Alert a = new Alert(AlertType.INFORMATION);
					a.initOwner(pStage);
					a.initStyle(StageStyle.UTILITY);
					a.setContentText("le circuit est bien sauvgarde");
					a.initOwner(pStage);
					a.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
					a.initStyle(StageStyle.UTILITY);
					a.setX(pStage.getX()+500);
					a.setY(pStage.getY()+250);
					a.showAndWait();
				}

			}
			
			if ( HomeController.horloged && Controller.simul) {
				Horloge horloge = ((Horloge) Circuit.getCompFromImage(HomeController.horlogeDeCercuit));

				try {
					horloge.stop();
					HomeController.t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		}
		else {
			event.consume();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

/*
 * package application; import java.util.ArrayList; import java.util.Collection;
 * import javafx.application.Application; import
 * javafx.beans.property.SimpleStringProperty; import
 * javafx.collections.FXCollections; import javafx.collections.ObservableList;
 * import javafx.scene.Scene; import javafx.scene.control.TableColumn; import
 * javafx.scene.control.TableView; import javafx.scene.layout.StackPane; import
 * javafx.stage.Stage;
 *
 *
 * public class Main extends Application{
 *
 * public static void main(String[] args){ launch (args); }
 *
 * @Override public void start(Stage primaryStage) throws Exception {
 * Collection<String[]> list = new ArrayList<>(); list.add("String1");
 * list.add("String2"); list.add("String3"); list.add("String4");
 * list.add("String5"); list.add("String6");
 *
 * ObservableList<String[]> details = FXCollections.observableArrayList(list);
 *
 * TableView<String[]> tableView = new TableView<>(); TableColumn<String[],
 * String> col1 = new TableColumn<>(); tableView.getColumns().addAll(col1);
 *
 * col1.setCellValueFactory(data -> new
 * SimpleStringProperty(data.getValue()[0])); tableView.setItems(details);
 *
 * StackPane sp = new StackPane(tableView); Scene scene = new Scene(sp);
 * primaryStage.setScene(scene); primaryStage.show(); }
 */
