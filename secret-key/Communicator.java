public class Communicator {
   private String name = "";
   
   public Communicator() {
      
   }
   
   public Communicator(String name) {
      this.name = name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getName() {
      return this.name;
   }
}
