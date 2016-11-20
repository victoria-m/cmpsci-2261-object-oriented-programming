
public class Mountain extends House {

   // default template properties
   private final static String DEFAULT_STYLE = "Mountain";
   private final static double DEFAULT_NUM_BEDROOMS = 3, DEFAULT_NUM_BATHROOMS = 2;
   private final static double TEMPLATE_BASIC_RATE = 3500, DEFAULT_TOTAL_AREA = 3200;

   // default constructor
   protected Mountain() {
      super(DEFAULT_STYLE, DEFAULT_NUM_BEDROOMS, DEFAULT_NUM_BATHROOMS, DEFAULT_TOTAL_AREA, TEMPLATE_BASIC_RATE);
   }

   // re-implement method so that it uses the default values of Mountain
   @Override
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
   
}
   
