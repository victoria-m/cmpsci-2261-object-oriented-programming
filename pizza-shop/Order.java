
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
   
   static void calculateCost() {
      
   }
   
}
