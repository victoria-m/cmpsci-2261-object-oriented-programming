package PizzaShop;

public abstract class Pizza {
   
   protected int numSlices;
   protected double costPerSlice = 0;
   protected String type = "";
   
   protected Pizza (double costPerSlice, String type) {
      this.numSlices = 0;
      this.costPerSlice = costPerSlice;
      this.type = type;
   }
   
   protected int getNumSlices() {
      return this.numSlices;
   }
   
   protected void setNumSlices(int numSlices) {
      this.numSlices = numSlices;
   }
   
   protected String getType() {
      return this.type;
   }
   
   protected double getCostPerSlice() {
      return this.costPerSlice;
   }
   
}
