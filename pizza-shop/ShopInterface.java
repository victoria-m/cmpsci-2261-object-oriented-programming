package PizzaShop;

import javafx.geometry.Insets;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShopInterface extends Application {
   
   // Main function is used as a fallback to launch the
   // program in case JavaFX is not fully supported
   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {

      Stage window = primaryStage;
      window.setTitle("Pizza Shop");

      // Layout is contained inside window
      BorderPane layout = new BorderPane();
      layout.setStyle("-fx-background-color: transparent;");

      // These objects are used for displaying information about
      // the pizza and the user's order
      Cheese cheese = new Cheese();
      Vegetable vegetable = new Vegetable();
      Pepperoni pepperoni = new Pepperoni();
      Order order = new Order();

      Button placeOrderButton = new Button("Place order");
      Button resetButton = new Button("Reset");

      Text choiceText = new Text("Pick your slice(s):");
      Text totalCostText = new Text("$ 0.00");
      Text thankYouText = new Text("Thank you for shopping with us! Enjoy!");
      Text cheesePizzaText = new Text("Cheese pizza");
      Text vegetablePizzaText = new Text("Vegetable pizza");
      Text pepperoniPizzaText = new Text("Pepperoni pizza");

      // Hidden by default, visible after order is placed
      resetButton.setVisible(false);
      thankYouText.setVisible(false);

      Label cheesePizzaLabel = new Label("Cheese Pizza \n" + "($" + cheese.getCostPerSlice() + "/slice)");
      Label vegetablePizzaLabel = new Label("Vegetable Pizza \n" + "($" + vegetable.getCostPerSlice() + "/slice)");
      Label pepperoniPizzaLabel = new Label("Pepperoni Pizza \n" + "($" + pepperoni.getCostPerSlice() + "/slice)");
      Label totalCostLabel = new Label("Total:");
      Label welcomeLabel = new Label("Welcome to the Pizza Shop");

      // Used for formatting total cost text
      DecimalFormat decFormat = new DecimalFormat("#.00");

      // Arc over pizza images (represent removed pizza slices)
      Arc cheesePizzaSliceArc = new Arc(282, 133, 82, 82, 0, 0);
      cheesePizzaSliceArc.setFill(Color.web("#E3F3F1")); // Set fill color
      cheesePizzaSliceArc.setType(ArcType.ROUND); // Set arc type

      Arc vegetablePizzaSliceArc = new Arc(183, 290, 88, 88, 0, 0);
      vegetablePizzaSliceArc.setFill(Color.web("#E3F3F1")); // Set fill color
      vegetablePizzaSliceArc.setType(ArcType.ROUND); // Set arc type

      Arc pepperoniPizzaSliceArc = new Arc(375, 285, 84, 84, 0, 0);
      pepperoniPizzaSliceArc.setFill(Color.web("#E3F3F1")); // Set fill color
      pepperoniPizzaSliceArc.setType(ArcType.ROUND); // Set arc type

      ChoiceBox<Integer> cheesePizzaChoice = new ChoiceBox<>();
      ChoiceBox<Integer> vegetablePizzaChoice = new ChoiceBox<>();
      ChoiceBox<Integer> pepperoniPizzaChoice = new ChoiceBox<>();

      cheesePizzaChoice.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);
      cheesePizzaChoice.setValue(0);

      vegetablePizzaChoice.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);
      vegetablePizzaChoice.setValue(0);

      pepperoniPizzaChoice.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);
      pepperoniPizzaChoice.setValue(0);

      // Used for calculating total cost
      List<Integer> numSlicesPerPizza = new ArrayList<>();
      List<Double> costPerSlice = new ArrayList<>();
            
      cheesePizzaChoice.setOnAction(e -> {
         // Update number of pizza slices
         cheese.setNumSlices(cheesePizzaChoice.getValue());

         // Remove pizza slices depending on how many were chosen
         cheesePizzaSliceArc.setLength(findArcAngle(cheese.getNumSlices()));

         // Update arraylists with most recent values
         numSlicesPerPizza
               .addAll(Arrays.asList(cheese.getNumSlices(), vegetable.getNumSlices(), pepperoni.getNumSlices()));
         costPerSlice.addAll(
               Arrays.asList(cheese.getCostPerSlice(), vegetable.getCostPerSlice(), pepperoni.getCostPerSlice()));

         // Calculate total order cost and update the total cost text
         order.setTotalCost(order.calculateCost(numSlicesPerPizza, costPerSlice));
         totalCostText.setText("$ " + decFormat.format(order.getTotalCost()));

         // Reset lists so they can be updated when choice selection is updated
         numSlicesPerPizza.clear();
         costPerSlice.clear();
      });

      vegetablePizzaChoice.setOnAction(e -> {
         // Update number of pizza slices
         vegetable.setNumSlices(vegetablePizzaChoice.getValue());

         // Remove pizza slices depending on how many were chosen
         vegetablePizzaSliceArc.setLength(findArcAngle(vegetable.getNumSlices()));

         // Update arraylists with most recent values
         numSlicesPerPizza
               .addAll(Arrays.asList(cheese.getNumSlices(), vegetable.getNumSlices(), pepperoni.getNumSlices()));
         costPerSlice.addAll(
               Arrays.asList(cheese.getCostPerSlice(), vegetable.getCostPerSlice(), pepperoni.getCostPerSlice()));

         // Calculate total order cost and update the total cost text
         order.setTotalCost(order.calculateCost(numSlicesPerPizza, costPerSlice));
         totalCostText.setText("$ " + decFormat.format(order.getTotalCost()));

         // Reset lists so they can be updated when choice selection is updated
         numSlicesPerPizza.clear();
         costPerSlice.clear();
      });

      pepperoniPizzaChoice.setOnAction(e -> {
         // Update number of pizza slices
         pepperoni.setNumSlices(pepperoniPizzaChoice.getValue());

         // Remove pizza slices depending on how many were chosen
         pepperoniPizzaSliceArc.setLength(findArcAngle(pepperoni.getNumSlices()));

         // Update arraylists with most recent values
         numSlicesPerPizza
               .addAll(Arrays.asList(cheese.getNumSlices(), vegetable.getNumSlices(), pepperoni.getNumSlices()));
         costPerSlice.addAll(
               Arrays.asList(cheese.getCostPerSlice(), vegetable.getCostPerSlice(), pepperoni.getCostPerSlice()));

         // Calculate total order cost and update the total cost text
         order.setTotalCost(order.calculateCost(numSlicesPerPizza, costPerSlice));
         totalCostText.setText("$ " + decFormat.format(order.getTotalCost()));

         // Reset lists so they can be updated when choice selection is updated
         numSlicesPerPizza.clear();
         costPerSlice.clear();
      });

      placeOrderButton.setOnAction(e -> {
         placeOrderButton.setDisable(true);

         cheesePizzaChoice.setDisable(true);
         vegetablePizzaChoice.setDisable(true);
         pepperoniPizzaChoice.setDisable(true);
         
         thankYouText.setVisible(true);
         resetButton.setVisible(true);
      });

      resetButton.setOnAction(e -> {
         placeOrderButton.setDisable(false);

         cheesePizzaChoice.setDisable(false);
         vegetablePizzaChoice.setDisable(false);
         pepperoniPizzaChoice.setDisable(false);
         
         cheesePizzaChoice.setValue(0);
         vegetablePizzaChoice.setValue(0);
         pepperoniPizzaChoice.setValue(0);

         totalCostText.setText("$ 0.00");

         thankYouText.setVisible(false);
         resetButton.setVisible(false);
      });

      // PANES:
      StackPane top = new StackPane();
      top.getChildren().add(welcomeLabel);
      
      HBox bottom = new HBox();
      bottom.getChildren().addAll(thankYouText, resetButton);

      HBox.setMargin(bottom.getChildren().get(0), new Insets(5, 0, 5, 20));
      HBox.setMargin(bottom.getChildren().get(1), new Insets(5, 100, 5, 50));

      GridPane left = new GridPane();

      // (column, row) order
      left.add(choiceText, 0, 0);
      left.add(cheesePizzaLabel, 0, 1);
      left.add(cheesePizzaChoice, 1, 1);
      left.add(vegetablePizzaLabel, 0, 2);
      left.add(vegetablePizzaChoice, 1, 2);
      left.add(pepperoniPizzaLabel, 0, 3);
      left.add(pepperoniPizzaChoice, 1, 3);
      left.add(totalCostLabel, 0, 4);
      left.add(totalCostText, 1, 4);
      left.add(placeOrderButton, 0, 5);

      // Set each child node's padding
      for (Node child : left.getChildren()) {
         GridPane.setMargin(child, new Insets(15, 15, 15, 15));
      }

      Pane center = new Pane();

      // Cheese pizza image
      Image cheesePizzaImage = new Image("file:src/PizzaShop/img/cheese.png");
      ImageView cheeseImageView = new ImageView(cheesePizzaImage);
      cheeseImageView.setFitHeight(160);
      cheeseImageView.setFitWidth(160);

      // Vegetable pizza image
      Image vegetablePizzaImage = new Image("file:src/PizzaShop/img/vegetable.png");
      ImageView vegetableImageView = new ImageView(vegetablePizzaImage);
      vegetableImageView.setFitHeight(170);
      vegetableImageView.setFitWidth(170);

      // Pepperoni pizza image
      Image pepperoniPizzaImage = new Image("file:src/PizzaShop/img/pepperoni.png");
      ImageView pepperoniImageView = new ImageView(pepperoniPizzaImage);
      pepperoniImageView.setFitHeight(160);
      pepperoniImageView.setFitWidth(160);

      cheeseImageView.setLayoutX(200);
      cheeseImageView.setLayoutY(50);

      cheesePizzaText.setLayoutX(232);
      cheesePizzaText.setLayoutY(38);

      vegetableImageView.setLayoutX(100);
      vegetableImageView.setLayoutY(200);

      vegetablePizzaText.setLayoutX(132);
      vegetablePizzaText.setLayoutY(380);

      pepperoniImageView.setLayoutX(300);
      pepperoniImageView.setLayoutY(200);

      pepperoniPizzaText.setLayoutX(330);
      pepperoniPizzaText.setLayoutY(380);

      // Add images
      center.getChildren().addAll(cheeseImageView, vegetableImageView, pepperoniImageView);

      // Add arcs (removed pizza slices)
      center.getChildren().addAll(cheesePizzaSliceArc, vegetablePizzaSliceArc, pepperoniPizzaSliceArc); 

      // Add pizza text
      center.getChildren().addAll(cheesePizzaText, vegetablePizzaText, pepperoniPizzaText);

      
      // Style
      placeOrderButton.getStyleClass().add("place-order-button");
      resetButton.getStyleClass().add("reset-button");
      thankYouText.getStyleClass().add("thank-you-text");
      totalCostLabel.getStyleClass().add("total-cost");
      totalCostText.getStyleClass().add("total-cost");
      cheesePizzaChoice.getStyleClass().add("choicebox");
      vegetablePizzaChoice.getStyleClass().add("choicebox");
      pepperoniPizzaChoice.getStyleClass().add("choicebox");
      cheesePizzaText.getStyleClass().add("pizza-text");
      vegetablePizzaText.getStyleClass().add("pizza-text");
      pepperoniPizzaText.getStyleClass().add("pizza-text");

      top.getStyleClass().add("top");
      left.getStyleClass().add("left");
      center.getStyleClass().add("center");
      bottom.getStyleClass().add("bottom");

      // Set layout panes
      layout.setTop(top);
      layout.setLeft(left);
      layout.setCenter(center);
      layout.setBottom(bottom);

      // Scene

      Scene scene = new Scene(layout, 790, 560);
      scene.setFill(Color.web("#E3F3F1"));

      // Add style sheet
      scene.getStylesheets().add("file:src/PizzaShop/stylesheet/style.css");

      window.getIcons().add(new Image("file:src/PizzaShop/img/pizza_slice.png"));

      // Place scene inside window and display the window
      window.setScene(scene);
      window.show();
   }

   // find the arc angle depending on the number of pizza slices selected
   public static int findArcAngle(int numSlices) {
      switch (numSlices) {
      case 0:
         return 0;
      case 1:
         return 45;
      case 2:
         return 90;
      case 3:
         return 135;
      case 4:
         return 180;
      case 5:
         return 225;
      case 6:
         return 270;
      case 7:
         return 315;
      case 8:
         return 360;
      default:
         break;
      }

      return 0;
   }

}
