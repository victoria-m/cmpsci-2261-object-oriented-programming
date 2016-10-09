import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StLouisCorp {
   public static void main(String[] args) throws InterruptedException {

      // the following arrays contain the employee names, base pay, and number
      // of hours worked
      String[] nameArr = new String[100];
      double[] basePayArr = new double[100];
      double[] hoursWorkedArr = new double[100];

      Scanner input = new Scanner(System.in);

      int i = 0; // represents index for each array
      int numEntries = 0; // represents number of entries for each array (each
                          // array has the same number of entries since the data
                          // is corresponding)
      boolean running = true; // used to exit while loop

      while (running) {
         
         System.out.println("\nPlease enter the employee's name:" + "\nType \"exit\" when finished.");

         nameArr[i] = input.nextLine();

         // check if user wants to exit loop, otherwise ask for employee's base
         // pay and number of hours worked
         if (nameArr[i].trim().toLowerCase().equals("exit")) running = false;
         else {
            input.useLocale(Locale.US);

            System.out.println("Please enter the employee's base pay:");
            // assign user input to base pay array element
            basePayArr[i] = validDouble(input);

            System.out.println("Please enter the employee's hours worked:");
            // assign valid user input value to hours worked array element
            hoursWorkedArr[i] = validDouble(input);

            ++numEntries;
            ++i;
            input.nextLine(); // ignore the rest of the line so that input at
                              // beginning of loop is not blocked
         }
      }
      
      input.close();
      
      // print first part of table
      System.out.printf("\n%-15s %-15s %-15s %-15s", "Employees", "Base Pay", "Hours Worked", "Total Pay");
      System.out.printf("\n---------------------------------------------------------\n");

      double minPay = 7.65;
      double maxHours = 60;

      // error flags
      boolean payTooLow = false;
      boolean notEnoughHours = false;

      // print the table's data
      for (int j = 0; j <= numEntries - 1; ++j) {
         
         double totalPay = calcTotalPay(basePayArr[j], hoursWorkedArr[j]);

         // check for errors
         payTooLow = (basePayArr[j] < minPay);
         notEnoughHours = (hoursWorkedArr[j] > maxHours);

         System.out.printf("%-15s $%-14.2f %-15.2f ", nameArr[j], basePayArr[j], hoursWorkedArr[j]);

         TimeUnit.MILLISECONDS.sleep(20); // sleep so that stderr is not printed
                                          // before remaining printf statement

         // print error message, otherwise print total pay
         if (payTooLow || notEnoughHours) {
            if (payTooLow && notEnoughHours) printError("both");
            else if (payTooLow) printError("pay");
            else if (notEnoughHours) printError("hours");
         } else System.out.printf("$%-15.2f\n", totalPay);
      }
   }

   
   // returns valid input - asks user to re-enter input until they enter a double
   
   static double validDouble(Scanner input) {
      boolean validDouble = false;
      String userInput = "";

      while (!validDouble) {
         if (input.hasNextDouble()) validDouble = true;
         else System.err.println("Invalid input: please enter the employee's base pay again.");

         userInput = input.next();
      }
      
      return Double.parseDouble(userInput);
   }
   
   
   // calculates employee's total pay
   
   static double calcTotalPay(double basePayRate, double hoursWorked) {
      double overtimePay = 0;
      double overtimeHours = 0;

      if (hoursWorked > 40) {
         overtimeHours = hoursWorked - 40;
         overtimePay = basePayRate * 1.5 * overtimeHours;
         hoursWorked = 40; // since overtime pay has already been calculated,
                           // don't include overtime hours in the next
                           // calculation
      }

      return hoursWorked * basePayRate + overtimePay;
   }
   

   // prints an error message depending on which error flag was set
   
   static void printError(String flag) {
      if (flag == "both") {
         System.err.println("The base pay is less than minimum wage and the number of hours worked exceeds 60.");
      } else if (flag == "pay") {
         System.err.println("The base pay is less than minimum wage.");
      } else if (flag == "hours") {
         System.err.println("The number of hours worked exceeds 60.");
      }
   }

}
