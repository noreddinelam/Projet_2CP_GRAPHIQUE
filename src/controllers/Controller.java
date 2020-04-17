package controllers;

import javafx.scene.layout.AnchorPane;
import noyau.Composant;

public abstract class Controller {
	protected Composant cmp;
	protected AnchorPane workSpace;

	public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}

	public AnchorPane getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(AnchorPane workSpace) {
		this.workSpace = workSpace;
	}
	
	
}
