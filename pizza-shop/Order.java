package PizzaShop;

import java.util.List;

// contains information about the customer's order

public class Order {
   protected double totalCost;

   protected Order() {
      this.totalCost = 0;
   }

   public double getTotalCost() {
      return totalCost;
   }

   public void setTotalCost(double totalCost) {
      this.totalCost = totalCost;
   }

   protected void addToTotalCost(double amount) {
      this.totalCost += amount;
   }

   // takes into account every slice selected and its cost
   double calculateCost(List<Integer> numSlicesPerPizza, List<Double> costPerSlice) {

      double totalCost = 0;

      for (int i = 0; i < numSlicesPerPizza.size(); ++i)
         totalCost += (numSlicesPerPizza.get(i) * costPerSlice.get(i));

      return totalCost;
   }

}
