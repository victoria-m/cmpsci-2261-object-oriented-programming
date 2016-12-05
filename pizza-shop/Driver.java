
public class Driver {

   public static void main(String[] args) {
      Pizza cheese = new Cheese();

      System.out.println(cheese.getType() + " pizza costs $" + cheese.getCostPerSlice() + " per slice and has "
            + cheese.getNumSlices() + " pieces by default");
   }
}
