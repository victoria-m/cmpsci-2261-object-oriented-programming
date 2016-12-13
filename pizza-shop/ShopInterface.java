
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShopInterface extends Application {

   // used for displaying information about each type of pizza and the order
   Cheese cheese = new Cheese();
   Vegetable vegetable = new Vegetable();
   Pepperoni pepperoni = new Pepperoni();
   Order order = new Order();

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {

      Stage window = primaryStage;
      window.setTitle("Pizza Shop");
      
      // layout is contained inside window
      BorderPane layout = new BorderPane();
      
      layout.setPadding(new Insets(10, 10, 10, 10));
      layout.setStyle("-fx-background-color: transparent;");      
      
      
      // LEFT PANE
      
      Button placeOrderButton = new Button("Place order");
      Text choiceText = new Text("Pick your slice(s)");
      
      // remember to format text to $0.00
      Text cheesePizzaText = new Text("Cheese Pizza \n" + "($" + cheese.getCostPerSlice() + "/slice)");
      Text vegetablePizzaText = new Text("Cheese Pizza \n" + "($" + vegetable.getCostPerSlice() + "/slice)");
      Text pepperoniPizzaText = new Text("Cheese Pizza \n" + "($" + pepperoni.getCostPerSlice() + "/slice)");
      Text totalText = new Text("Total: $" + order.getTotalCost());
      
      ChoiceBox<Integer> cheesePizzaChoice = new ChoiceBox<>();
      ChoiceBox<Integer> vegetablePizzaChoice = new ChoiceBox<>();
      ChoiceBox<Integer> pepperoniPizzaChoice = new ChoiceBox<>();

      cheesePizzaChoice.getItems().addAll(1, 2, 3, 4, 5);
      cheesePizzaChoice.setValue(1);

      vegetablePizzaChoice.getItems().addAll(1, 2, 3, 4, 5);
      vegetablePizzaChoice.setValue(1);

      pepperoniPizzaChoice.getItems().addAll(1, 2, 3, 4, 5);
      pepperoniPizzaChoice.setValue(1);

      GridPane left = new GridPane();
      
      // (column, row) order
      left.add(choiceText, 0, 0);
      left.add(cheesePizzaText, 0, 1);
      left.add(cheesePizzaChoice, 1, 1);
      left.add(vegetablePizzaText, 0, 2);
      left.add(vegetablePizzaChoice, 1, 2);
      left.add(pepperoniPizzaText, 0, 3);
      left.add(pepperoniPizzaChoice, 1, 3);     
      left.add(totalText, 0, 4);
      left.add(placeOrderButton, 0, 5);
      
      // set each child node's padding
      for (Node child : left.getChildren()) {
         GridPane.setMargin(child, new Insets(15, 0, 15, 0));
      }
      
      
      // RIGHT PANE

      // pizza locations:
      // 50, 100 + 50, 200 + 20, 300
      
     
      layout.setTop(new CustomPane("Welcome to the Pizza Shop"));
      layout.setLeft(left);

      Scene scene = new Scene(layout, 600, 400);
      scene.setFill(Color.web("#A5A5A5"));

      // place scene inside window and display the window
      window.setScene(scene);
      window.show();
   }

}
