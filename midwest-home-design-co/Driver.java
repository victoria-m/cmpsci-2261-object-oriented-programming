
public class Driver {
   public static void main(String[] args) {
      
      // default traditional
      House defaultTrad = new Traditional();
      
      // custom traditional
      House custTrad = new Traditional();
      
      System.out.println(defaultTrad.toString());
      System.out.println(custTrad.toString());
      
      
      System.out.println("Comparisons:\n");
      System.out.println(defaultTrad.compareTo(custTrad));
      
      System.out.println("\nCloning:");
      System.out.println(custTrad.clone());
      
      // ask customer to select the design style preferences
      // and specifications
      
      // software asks customer to:
         // enter home style
         // total area range
         // num beds/baths
      // customer should enter AT LEAST home style

      // customer can customize the house design templates
      // make sure to calculate the total design costs
      // and display all that to the customer
   }
}
