
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
   public static void main(String[] args) {

      // Step 1: ask customer to enter home style, total area (in sq ft), and
      // number of beds/baths (customer should at least enter home style)

      // user input
      String input = "", userStyle = "";
      Scanner scan = new Scanner(System.in);

      // default values
      double userArea = 2000, userNumBeds = 2, userNumBaths = 2;
      boolean done = false;

      while (!done) {
         System.out.println("\nPlease select a home attribute to edit:\n");

         System.out.println("1. Style");
         System.out.println("2. Total area");
         System.out.println("3. Number of beds");
         System.out.println("4. Number of baths");
         System.out.println("5. Done editing");

         try {

            input = String.valueOf(scan.nextInt());
            scan.nextLine(); // reset input

            switch (input) {
            case "1":
               userStyle = scan.nextLine();
               break;
            case "2":
               userArea = scan.nextDouble();
               break;
            case "3":
               userNumBeds = scan.nextDouble();
               break;
            case "4":
               userNumBaths = scan.nextDouble();
               break;
            case "5":
               if (userStyle.equals(""))
                  System.err.println("Please enter a style.");
               else
                  done = true;
               break;
            default:
               System.err.println("Invalid selection.");
            }
         } catch (InputMismatchException e) {
            System.err.println("Invalid input");
            scan.nextLine(); // reset input
         }

         System.out.printf("-----------------------------------------------\n");
         System.out.printf("%-13s %-13s %-13s %-13s\n", "Style", "Area", "Beds", "Baths");
         System.out.printf("-----------------------------------------------\n");
         System.out.printf("%-13s %-13.2f %-13.1f %-13.1f\n", userStyle, userArea, userNumBeds, userNumBaths);
      }

      // create custom house
      House userHouse = new Custom(userStyle, userNumBeds, userNumBaths, userArea);

      // Step 2: compare the house style and preferences that customer entered
      // to the standard house templates available in the company.

      // array of house templates
      House[] houseTemplates = { new Traditional(), new Modern(), new European(), new Southwest(), new Mountain(),
            new Victorian(), new Country() };

      // compare house templates with the user's custom template and check if a
      // match is found
      boolean templateMatchFound = false;

      for (int i = 0; i < houseTemplates.length; ++i) {

         // if a match is found, use matching template but retain user's area
         if (houseTemplates[i].compareTo(userHouse) == 0) {

            templateMatchFound = true;
            System.out.println("\nWe found a matching template in our system: " + houseTemplates[i].getStyle() + '\n');

            userHouse = houseTemplates[i].clone();
            
            // retain user's area (make sure it is not overwritten by the standard house template)
            userHouse.setTotalArea(userArea);
            
            // set cost according to user's area
            userHouse.setCost(userHouse.calcTotalCost());

            // finally display that template and total design costs
            System.out.print(userHouse.toString());
         }

      }

      // if no template match is found, display the style the user entered and design costs.
      if (!templateMatchFound)
         System.out.printf("\nStyle: %s, Cost: $%.2f\n", userHouse.getStyle(), userHouse.getCost());

      // Step 3: offer customer the option to change his specifications until he is satisfied.

      // reset done flag
      done = false;

      while (!done) {

         try {
            System.out.println("\nPlease select a house specification to change if desired:");

            System.out.println("1. Style");
            System.out.println("2. Total area");
            System.out.println("3. Number of beds");
            System.out.println("4. Number of baths");
            System.out.println("5. Finished");

            input = String.valueOf(scan.nextInt());
            scan.nextLine(); // prepare for new input

            switch (input) {
            case "1":
               userStyle = scan.nextLine();
               break;
            case "2":
               userArea = scan.nextDouble();
               break;
            case "3":
               userNumBeds = scan.nextDouble();
               break;
            case "4":
               userNumBaths = scan.nextDouble();
               break;
            case "5":

               // if user chooses to exit, display the final design
               // specifications and total cost
               System.out.println("\nFINAL DESIGN SPECIFICATIONS AND COST:");
               System.out.println("----------------------------------------------------------------");
               System.out.printf("%-13s %-13s %-13s %-13s %-13s\n", "Style", "Area", "Beds", "Baths", "Cost");
               System.out.println("----------------------------------------------------------------");
               System.out.printf("%-13s %-13.2f %-13.1f %-13.1f $%-13.2f\n", userHouse.getStyle(),
                     userHouse.getTotalArea(), userHouse.getNumBedrooms(), userHouse.getNumBathrooms(),
                     userHouse.getCost());

               scan.close();
               System.exit(0);
               
               break;
            default:
               System.err.println("Invalid selection.");
            }
         } catch (InputMismatchException e) {
            System.err.println("Invalid input.");
            scan.nextLine(); // reset input
         }
         userHouse.customize(userStyle, userNumBeds, userNumBaths, userArea);
      }
      
   }
}
