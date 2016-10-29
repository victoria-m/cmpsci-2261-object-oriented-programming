package SecretKey;

public class Communicator {

   private String name = "";
   private StringBuilder message = new StringBuilder("");
   private StringBuilder secretKey = new StringBuilder();

   Communicator() { }

   Communicator(String name, StringBuilder message, StringBuilder secretKey) {
      this.name = name;
      this.message = message;
      this.secretKey = secretKey;
   }

   Communicator(String name) {
      this(name, new StringBuilder(""), new StringBuilder(""));
   }

   void setName(String name) {
      this.name = name;
   }

   String getName() {
      return this.name;
   }

   void setText(StringBuilder message) {
      this.message = message;
   }

   StringBuilder getText() {
      return this.message;
   }

   void setSecretKey(StringBuilder secretKey) {
      this.secretKey = secretKey;
   }

   StringBuilder getSecretKey() {
      return this.secretKey;
   }

}
