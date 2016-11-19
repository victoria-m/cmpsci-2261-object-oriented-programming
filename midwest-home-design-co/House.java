
public abstract class House {
   
   private String houseStyle;
   private int numBedrooms;
   private int numBathrooms;
   private int totalArea;
   
   // the customer’s specifications
   // include the house style, number of bedrooms/ bathrooms, and total area.

   
   protected House(String houseStyle, int numBedrooms, int numBathrooms, int totalArea) {
      this.houseStyle = houseStyle;
      this.numBedrooms = numBedrooms;
      this.numBathrooms = numBathrooms;
      this.totalArea = totalArea;
   }
}

// use generic programming

// sub-classes extend House

// House x = new Country();   if user chooses Country style

// ask user to at least choose a style

// obj attributes: beds, baths, template area

// compareTo() compares to template

// need to override toString() method and return description string
