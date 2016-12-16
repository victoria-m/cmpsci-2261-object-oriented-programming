
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
      
      // these buttons are hidden by default, showed once order is placed
      Button placeOrderButton = new Button("Place order");
      Button resetButton = new Button("Click here to reset");
      
      Text choiceText = new Text("Pick your slice(s):");
      Text totalCostText = new Text("$ 0.00");
      Text thankYouText = new Text("Thank you for shopping with us! Enjoy!");
      
      // remember to format text to $0.00
      Label cheesePizzaLabel = new Label("Cheese Pizza \n" + "($" + cheese.getCostPerSlice() + "/slice)");
      Label vegetablePizzaLabel = new Label("Vegetable Pizza \n" + "($" + vegetable.getCostPerSlice() + "/slice)");
      Label pepperoniPizzaLabel = new Label("Pepperoni Pizza \n" + "($" + pepperoni.getCostPerSlice() + "/slice)");
      Label totalCostLabel = new Label("Total:");
      
      // used when setting text in GUI
      DecimalFormat decFormat = new DecimalFormat("#.00");
            
      // arcs over pizza images   
      Arc cheesePizzaSliceArc = new Arc(275, 130, 83, 83, 0, 0);
      cheesePizzaSliceArc.setFill(Color.WHITE); // Set fill color
      cheesePizzaSliceArc.setType(ArcType.ROUND); // Set arc type
      
      Arc vegetablePizzaSliceArc = new Arc(171, 280, 83, 83, 0, 0);
      vegetablePizzaSliceArc.setFill(Color.WHITE); // Set fill color
      vegetablePizzaSliceArc.setType(ArcType.ROUND); // Set arc type
      
      Arc pepperoniPizzaSliceArc = new Arc(370, 280, 83, 83, 0, 0);
      pepperoniPizzaSliceArc.setFill(Color.WHITE); // Set fill color
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
      
      // used for calculating total cost
      List<Integer> numSlicesPerPizza = new ArrayList<>();
      List<Double> costPerSlice = new ArrayList<>();
      
      cheesePizzaChoice.setOnAction(e -> {
         // update number of pizza slices
         cheese.setNumSlices(cheesePizzaChoice.getValue());
         
         // remove pizza slices depending on how many slices were chosen        
         cheesePizzaSliceArc.setLength(findArcAngle(cheese.getNumSlices()));      
         
         // add most recent values to pizza info arraylists
         numSlicesPerPizza.addAll(Arrays.asList(cheese.getNumSlices(), vegetable.getNumSlices(), pepperoni.getNumSlices()));
         costPerSlice.addAll(Arrays.asList(cheese.getCostPerSlice(), vegetable.getCostPerSlice(), pepperoni.getCostPerSlice()));  
         
         // calculate total order cost and update the total cost text
         order.setTotalCost(order.calculateCost(numSlicesPerPizza, costPerSlice));
         totalCostText.setText("$ " + decFormat.format(order.getTotalCost()));
         
         // reset lists so they can be updated when choice selection is updated
         numSlicesPerPizza.clear();
         costPerSlice.clear();
      });
      
      vegetablePizzaChoice.setOnAction(e -> {
         // update number of pizza slices
         vegetable.setNumSlices(vegetablePizzaChoice.getValue());
         
         // remove pizza slices depending on how many slices were chosen        
         vegetablePizzaSliceArc.setLength(findArcAngle(vegetable.getNumSlices()));      
  
         // add most recent values to pizza info arraylists
         numSlicesPerPizza.addAll(Arrays.asList(cheese.getNumSlices(), vegetable.getNumSlices(), pepperoni.getNumSlices()));
         costPerSlice.addAll(Arrays.asList(cheese.getCostPerSlice(), vegetable.getCostPerSlice(), pepperoni.getCostPerSlice()));  
         
         // calculate total order cost and update the total cost text
         order.setTotalCost(order.calculateCost(numSlicesPerPizza, costPerSlice));
         totalCostText.setText("$ " + decFormat.format(order.getTotalCost()));
         
         // reset lists so they can be updated when choice selection is updated
         numSlicesPerPizza.clear();
         costPerSlice.clear();
      });
      
      pepperoniPizzaChoice.setOnAction(e -> {
         // update number of pizza slices
         pepperoni.setNumSlices(pepperoniPizzaChoice.getValue());
         
         // remove pizza slices depending on how many slices were chosen        
         pepperoniPizzaSliceArc.setLength(findArcAngle(pepperoni.getNumSlices()));      
  
         // add most recent values to pizza info arraylists
         numSlicesPerPizza.addAll(Arrays.asList(cheese.getNumSlices(), vegetable.getNumSlices(), pepperoni.getNumSlices()));
         costPerSlice.addAll(Arrays.asList(cheese.getCostPerSlice(), vegetable.getCostPerSlice(), pepperoni.getCostPerSlice()));  
         
         // calculate total order cost and update the total cost text
         order.setTotalCost(order.calculateCost(numSlicesPerPizza, costPerSlice));
         totalCostText.setText("$ " + decFormat.format(order.getTotalCost()));
         
         // reset lists so they can be updated when choice selection is updated
         numSlicesPerPizza.clear();
         costPerSlice.clear();
      });
      
      
      // buttons
      
      placeOrderButton.setOnAction(e -> {
         placeOrderButton.setDisable(true);
         
         thankYouText.setVisible(true);
        
      });
      
      resetButton.setOnAction(e -> {
         placeOrderButton.setDisable(false);
         
         cheesePizzaChoice.setValue(0);
         vegetablePizzaChoice.setValue(0);
         pepperoniPizzaChoice.setValue(0);
         
         totalCostText.setText("$ 0.00");
         
         thankYouText.setVisible(false);
      });
      

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
      
      // set each child node's padding
      for (Node child : left.getChildren()) {
         GridPane.setMargin(child, new Insets(15, 15, 15, 15));
      }
      
      
      // center pane
      
      Pane center = new Pane();
      
      // cheese pizza image
      Image cheesePizzaImage = new Image("file:src/PizzaShop/img/cheese1.jpg");
      ImageView cheeseImageView = new ImageView(cheesePizzaImage);
      cheeseImageView.setFitHeight(150);
      cheeseImageView.setFitWidth(150);
      
      // vegetable pizza image
      Image vegetablePizzaImage = new Image("file:src/PizzaShop/img/vegetable1.jpg");
      ImageView vegetableImageView = new ImageView(vegetablePizzaImage);
      vegetableImageView.setFitHeight(150);
      vegetableImageView.setFitWidth(150);
      
      // pepperoni pizza image
      Image pepperoniPizzaImage = new Image("file:src/PizzaShop/img/pepperoni3.jpg");
      ImageView pepperoniImageView = new ImageView(pepperoniPizzaImage);
      pepperoniImageView.setFitHeight(150);
      pepperoniImageView.setFitWidth(150);
      
      cheeseImageView.setLayoutX(200);
      cheeseImageView.setLayoutY(50);
      
      vegetableImageView.setLayoutX(100);
      vegetableImageView.setLayoutY(200);
      
      pepperoniImageView.setLayoutX(300);
      pepperoniImageView.setLayoutY(200);
      
      // add images
      center.getChildren().addAll(cheeseImageView, vegetableImageView, pepperoniImageView);            
      
      // add arcs
      center.getChildren().addAll(cheesePizzaSliceArc, vegetablePizzaSliceArc, pepperoniPizzaSliceArc); // Add arc to pane
           
      // bottom pane
      
      HBox bottom = new HBox();
      
      // column, row order
      bottom.getChildren().addAll(thankYouText, resetButton);
      
      HBox.setMargin(bottom.getChildren().get(0), new Insets(5, 0, 5, 20));
      HBox.setMargin(bottom.getChildren().get(1), new Insets(5, 100, 5, 50));
      
      // top pane
      StackPane top = new StackPane();
      Label welcomeLabel= new Label("Welcome to the Pizza Shop");
      
      top.getChildren().add(welcomeLabel);
      

      // set layout panes
      layout.setTop(top);
      layout.setLeft(left);
      layout.setCenter(center);
      layout.setBottom(bottom);

      Scene scene = new Scene(layout, 700, 470);
//      scene.setFill(Color.web("#A5A5A5"));

      // place scene inside window and display the window
      window.setScene(scene);
      window.show();
   }
   
   public static int findArcAngle(int numSlices) {
      // change number of removed pizza slices
      
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
      
      // default return
      return 0;
   }

}
