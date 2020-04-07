package controllers;

import noyau.Composant;

public abstract class Controller {
	protected Composant cmp;

	public Composant getCmp() {
		return cmp;
	}

	public void setCmp(Composant cmp) {
		this.cmp = cmp;
	}
	
	
}
