package controllers;

import application.ClickDroitLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ClickDroitLabelController {
	  private TextArea text;
	  AnchorPane workSpace;
	  ClickDroitLabel fenetre;
	  @FXML
	    private Button supprimer;

	    @FXML
	    void suprimerLabel(MouseEvent event) {
            if (text != null) workSpace.getChildren().remove(text.getParent());
            fenetre.close();
	    }

		public TextArea getText() {
			return text;
		}

		public void setText(TextArea text) {
			this.text = text;
		}

		public AnchorPane getWorkSpace() {
			return workSpace;
		}

		public void setWorkSpace(AnchorPane workSpace) {
			this.workSpace = workSpace;
		}

		public ClickDroitLabel getFenetre() {
			return fenetre;
		}

		public void setFenetre(ClickDroitLabel fenetre) {
			this.fenetre = fenetre;
		}

		
		
		
	    

}
