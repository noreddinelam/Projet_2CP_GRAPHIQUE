package noyau;






import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class EditableDraggableText extends StackPane {//Clasee pour traiter les fonctionalités de label
    private final double PADDING = 5;
    private EditableText text = new EditableText(PADDING, PADDING);

   EditableDraggableText(double x, double y) {//Constructeur interne

        relocate(x - PADDING, y - PADDING);
        getChildren().add(text);
        text.focusedProperty().addListener((observable, hadFocus, hasFocus) -> {//on supprime le label si il est vide
            if (!hasFocus && getParent() != null && getParent() instanceof AnchorPane &&
                (text.getText() == null || text.getText().trim().isEmpty())) {
                ((AnchorPane) getParent()).getChildren().remove(this);
            }
        });

        enableDrag(text);//Ajouter le drag and drop pour le label
    }

    public EditableDraggableText(double x, double y, String text) {//constructeur externe
      

        this(x, y);   
        this.setMaxWidth(800);
        this.text.setText(text);
       
     
    }

    private void enableDrag(EditableText t) {//Traitement de drag and drop de label
        final Delta dragDelta = new Delta();
       t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                
                        toFront();
                        mouseEvent.consume();
                    }
                
                
            }
        });
  
        t.setOnMouseExited(mouseEvent -> {
        	t.setCursor(Cursor.DEFAULT);
            mouseEvent.consume();
        });
        t.setOnMouseDragged(mouseEvent -> {
      
            t.setCursor(Cursor.HAND);
      
            double newX = getLayoutX() + mouseEvent.getX() ;
            if(newX > 0 && newX< 3340 )//verifiation des coordoonés X
                setLayoutX(newX);
            double newY = getLayoutY() + mouseEvent.getY() ;
            if(newY > 0 && newY< 2080 )// verfication des coordoonés Y
                setLayoutY(newY);
                mouseEvent.consume();
        	  
        });
      
    }

   
    private class Delta {//Classe pour traitement des coordonnés de label
        double x, y;
    }
}
class EditableText extends TextField {//Text filed de label

    private final double RIGHT_MARGIN = 5;

    EditableText(double x, double y) {//Constructeur interne
        relocate(x, y);
        this.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (getText().length() > 20) {
                    String s = getText().substring(0, 20);
                    setText(s);
                }
            }
        });
  	  this.setStyle("-fx-background-color:#5a6572;-fx-text-fill:  #e0e0d1;");
  	  this.setFont(Font.font("Calisto MT",FontWeight.BOLD,18));
      FontMetrics metrics = new FontMetrics(getFont());
        setPrefWidth(RIGHT_MARGIN);
        textProperty().addListener((observable, oldTextString, newTextString) ->
            setPrefWidth(metrics.computeStringWidth(oldTextString+"       ") + RIGHT_MARGIN)
        );

        Platform.runLater(this::requestFocus);
    }
}