
// used for creating a custom pane within the window

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class CustomPane extends StackPane {
   
   public CustomPane(String title) {
      
      getChildren().add(new Label(title));
      setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
   
   }
   
}
