package controllers;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import noyau.Composant;

public abstract class ProprietesController extends Controller{
	
	protected static ArrayList<ImageView> btns = new ArrayList<ImageView>();

	public abstract void initialiser(Composant cmp);
	public void applyOpaciteForImages(ArrayList<ImageView> imageViews) {
		for (ImageView imageView : imageViews) {
			imageView.setOpacity(0.4);
		}
	}
}
