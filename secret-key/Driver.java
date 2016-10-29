public class Driver {
   public static void main(String[] args) {
      
      Communicator sender = new Communicator("Jill");
      Communicator receiver = new Communicator("Elliot");
      Message message = new Message();
     
      System.out.println("-------");
      System.out.println("SENDER:");
      System.out.println("-------");
      
      System.out.println("\nPlease enter a message:\n");
      message.readText();
      
      message.generateSecretKey();
      message.encryptText();
      
      sender.setText(message.getText());
      sender.setSecretKey(message.getSecretKey());
      System.out.println("\nThe encrypted message is: " + sender.getText());

      
      System.out.println("\n\n---------");
      System.out.println("RECEIVER:");
      System.out.println("---------");
      
      receiver.setText(sender.getText());
      receiver.setSecretKey(sender.getSecretKey());
      
      System.out.println("\nThe secret key is: " + receiver.getSecretKey());
      
      // check if key is valid
      
      if (message.verifySecretKey(receiver.getSecretKey()) == true) {
         System.out.println("\nValid secret key.");
      } else {
         System.err.println("\nInvalid secret key. Exiting...");
         System.exit(1);
      }
      
      // display the decrypted message
      
       message.decryptCode();
       System.out.println("\nThe decrypted message is: " + message.getText());
   }
}
