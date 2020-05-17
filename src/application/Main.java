package application;


import controllers.PremierePageController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application { /// charger la fenetre principale et lancer l'application
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PageEntree.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/styleFile/pageEntree.css").toExternalForm());
			PremierePageController premierePageController = (PremierePageController) loader.getController();
			premierePageController.setStage(primaryStage);
			FadeTransition fade = new FadeTransition();
			fade.setDuration(Duration.millis(1000));
			fade.setDelay(Duration.millis(4000));
			fade.setFromValue(10);
			fade.setToValue(0.1);
			fade.setCycleCount(0);
			fade.setAutoReverse(true);
			fade.setNode(root);
			fade.play();
			if (getParameters().getUnnamed().size() != 0) {
				premierePageController.setFileToUpload(getParameters().getUnnamed().get(0));
			}
			primaryStage.getIcons().add(new Image("/homePage_icones/miniLogo.png"));
			primaryStage.setScene(scene);
		    primaryStage.initStyle(StageStyle.UNDECORATED);
     		primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
