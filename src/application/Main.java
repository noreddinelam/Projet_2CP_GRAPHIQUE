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

public class Main extends Application {
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
