
public abstract class House implements Cloneable, Comparable<House>, Customizable {

   protected String style;
   protected double numBedrooms, numBathrooms;
   protected double totalCost, totalArea, templateRate;
   protected final static double TAX = 0.05;

   // I set the default num beds/baths for the House template as 2/2
   // since we were not given a default value, these values are used when
   // determining the number of extra bedrooms/baths in calcTotalCost() for the
   // generic House template
   private final static int DEFAULT_NUM_BEDROOMS = 2, DEFAULT_NUM_BATHROOMS = 2;

   // the customer’s specifications
   // include the house style, number of bedrooms/bathrooms, and total area.
   protected House(String style, double numBedrooms, double numBathrooms, double totalArea, double templateRate) {
      this.style = style;
      this.numBedrooms = numBedrooms;
      this.numBathrooms = numBathrooms;
      this.totalArea = totalArea;
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

   protected double getNumBedrooms() {
      return numBedrooms;
   }

   protected void setNumBedrooms(double numBedrooms) {
      this.numBedrooms = numBedrooms;
   }

   protected double getNumBathrooms() {
      return numBathrooms;
   }

   protected void setNumBathrooms(double numBathrooms) {
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
   // further implemented in subclasses depending on their default values
   protected double calcTotalCost() {
      
      double totalCost, templateBasicRate = this.getTemplateRate();
      double extraBedrooms = 0, extraBathrooms = 0;

      // if there are extra beds/baths
      if (this.getNumBedrooms() > DEFAULT_NUM_BEDROOMS)
         extraBedrooms = this.getNumBedrooms() - DEFAULT_NUM_BEDROOMS;

      if (this.getNumBathrooms() > DEFAULT_NUM_BATHROOMS)
         extraBathrooms = this.getNumBathrooms() - DEFAULT_NUM_BATHROOMS;

      // multiply basic rate by 1.5 if the total area is >= 3,000 sq ft
      if (this.getTotalArea() >= 3000)
         templateBasicRate *= 1.5;

      // calculate total cost
      totalCost = (templateBasicRate + ((800 * extraBedrooms) + (500 * extraBathrooms)));

      // add tax
      totalCost += (TAX * totalCost);

      return totalCost;
   }

   // returns a string including the description of the house design as well as
   // design costs
   @Override
   public String toString() {
      return String.format("Style: %s \nBeds: %.1f \nBaths: %.1f \nTotal area: %.2f \nTotal cost: $%.2f\n",
            this.getStyle(), this.getNumBedrooms(), this.getNumBathrooms(), this.getTotalArea(), this.getCost());
   }

   // returns negative int if current house is < other house,
   // positive int if >, and 0 if they are equal.
   // compares style, num baths, and num baths
   @Override
   public int compareTo(House otherHouse) {
      int num;

      num = this.style.compareTo(otherHouse.style);

      // return if they are not equal, otherwise continue comparisons
      if (num != 0)
         return num;

      num = Double.compare(this.numBedrooms, otherHouse.numBedrooms);

      // if not equal
      if (num != 0)
         return num;

      num = Double.compare(this.numBathrooms, otherHouse.numBathrooms);

      // I am not comparing the total area or the template cost (for the custom house)
      // because it was not included in the house templates we were given.

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
   public void customize(String style, double numBedrooms, double numBathrooms, double totalArea) {
      this.setStyle(style);
      this.setNumBedrooms(numBedrooms);
      this.setNumBathrooms(numBathrooms);
      this.setTotalArea(totalArea);
      this.setCost(this.calcTotalCost());
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
