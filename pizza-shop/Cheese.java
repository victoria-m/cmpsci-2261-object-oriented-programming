package PizzaShop;

public class Cheese extends Pizza {
   final static double COSTPERSLICE = 1.50;
   final static String TYPE = "Cheese";

   protected Cheese() {
      super(COSTPERSLICE, TYPE);
   }
}
