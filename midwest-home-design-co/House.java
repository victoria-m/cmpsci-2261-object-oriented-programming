
public abstract class House implements Cloneable, Comparable<House>, Customizable {

   protected String style;
   protected int numBedrooms, numBathrooms;
   protected double totalCost, totalArea, templateRate;
   protected final static double TAX = 0.05;

   // the customer’s specifications
   // include the house style, number of bedrooms/bathrooms, and total area.
   protected House(String style, int numBedrooms, int numBathrooms, double totalArea, double templateRate) {
      this.style = style;
      this.numBedrooms = numBedrooms;
      this.numBathrooms = numBathrooms;
      this.totalArea = totalArea;
      this.totalCost = 0;
      this.templateRate = templateRate;
      this.totalCost = this.calcTotalCost();
   }

   protected double calcTEMPLATE_BASIC_RATE() {
      return 1;
   }

   protected void setStyle(String style) {
      this.style = style;
   }

   protected String getStyle() {
      return this.style;
   }

   protected int getNumBedrooms() {
      return numBedrooms;
   }

   protected void setNumBedrooms(int numBedrooms) {
      this.numBedrooms = numBedrooms;
   }

   protected int getNumBathrooms() {
      return numBathrooms;
   }

   protected void setNumBathrooms(int numBathrooms) {
      this.numBathrooms = numBathrooms;
   }

   protected double getTotalArea() {
      return totalArea;
   }

   protected void setTotalArea(double totalArea) {
      this.totalArea = totalArea;
   }

   protected void setCost(double totalCost) {
      this.totalCost = totalCost;
   }

   protected double getCost() {
      return this.totalCost;
   }

   protected double getTemplateRate() {
      return this.templateRate;
   }

   // based on house style, total area, number of beds/baths
   // further implemented in sub-classes, by default returns 0;
   protected double calcTotalCost() {
      return 0;
   }

   // returns a string including the description of the house design as well as
   // design costs
   @Override
   public String toString() {
      return "Style: " + this.getStyle() + "\nBeds: " + this.getNumBedrooms() + "\nBath: " + this.getNumBathrooms()
            + "\nTotal area: " + this.getTotalArea() + "\nTotal cost: $" + this.getCost() + "\n";
   }

   // returns negative int if current house is < other house,
   // positive int if >, and 0 if they are equal.
   @Override
   public int compareTo(House otherHouse) {
      int num;

      num = this.style.compareTo(otherHouse.style);

      // return if they are not equal, otherwise continue comparisons
      if (num != 0)
         return num;

      num = Integer.compare(this.numBedrooms, otherHouse.numBedrooms);

      // if not equal
      if (num != 0)
         return num;

      num = Integer.compare(this.numBathrooms, otherHouse.numBathrooms);

      // if not equal
      if (num != 0)
         return num;

      // I am not comparing the total area because it was not included in
      // the house templates we were given.

      num = Double.compare(this.totalCost, otherHouse.totalCost);
      return num;
   }

   // remember to cast to houes after cloning
   @Override
   protected House clone() {
      try {
         return (House) super.clone();
      } catch (CloneNotSupportedException ex) {
         return null;
      }
   }

   @Override
   public void customize(String style, int numBedrooms, int numBathrooms, double totalArea) {
      this.setStyle(style);
      this.setNumBedrooms(numBedrooms);
      this.setNumBathrooms(numBathrooms);
      this.setTotalArea(totalArea);
   }

   // compares house style and preferences
   // to default standard templates
   // if any templates match the customer's search, then
   // display the template + total design costs
   // if not, display the user's style + total design costs

}

// use generic programming

// sub-classes extend House

// House x = new Country(); if user chooses Country style

// ask user to at least choose a style

// obj attributes: beds, baths, template area

// compareTo() compares to template

// need to override toString() method and return description string
