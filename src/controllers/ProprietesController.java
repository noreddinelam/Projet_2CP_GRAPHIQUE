package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import noyau.Composant;

public abstract class ProprietesController extends Controller{
		
	public abstract void initialiser(Composant cmp);
    protected void alert() {
	Alert alert=new Alert(AlertType.WARNING);
	alert.setTitle("Erreur de Modification");
	alert.setContentText("Le composant est relier les modifications ne seront pas appliqués "
			+ "veuillez d'abord dérelier le composant");
	alert.getDialogPane().getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
	alert.showAndWait();
}
}
