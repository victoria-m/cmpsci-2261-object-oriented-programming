import java.util.Scanner;

public class Driver {
   public static void main(String[] args) {
      
      Communicator sender = new Communicator("Jill");
      Communicator receiver = new Communicator("Elliot");
      Message message = new Message();
      Scanner scan = new Scanner(System.in);
      
      
      // sender side:
      
      System.out.println("Please enter a message: ");

      message.setText(new StringBuilder(scan.nextLine()));
      message.generateSecretKey();
      message.encryptText();   
           
      // receiver side:
      
      System.out.println("\nThe secret key is: " + message.getSecretKey());
      System.out.println("\nThe encrypted message is: " + message.getText());
      
      // display that the key is validated
      
      // display the decrypted message
   }
}
