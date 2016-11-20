// traditional house template

public class Traditional extends House {

   // default template properties
   private final static String DEFAULT_STYLE = "Traditional";
   private final static int DEFAULT_NUM_BEDROOMS = 3, DEFAULT_NUM_BATHROOMS = 2;
   private final static double TEMPLATE_BASIC_RATE = 3300, DEFAULT_TOTAL_AREA = 1800;

   // default constructor
   protected Traditional() {
      super(DEFAULT_STYLE, DEFAULT_NUM_BEDROOMS, DEFAULT_NUM_BATHROOMS, DEFAULT_TOTAL_AREA, TEMPLATE_BASIC_RATE);
   }

   // based on house style, total area, number of beds/baths
   // this is calculated within the custom house class since we cannot access
   // the static variables in the House class
   protected double calcTotalCost() {
      double totalCost, templateBasicRate = this.getTemplateRate();
      int extraBedrooms = 0;
      int extraBathrooms = 0;
      
      
      // if there are extra beds/baths
      if (this.getNumBathrooms() > DEFAULT_NUM_BEDROOMS)
         extraBedrooms = this.getNumBedrooms() - DEFAULT_NUM_BEDROOMS;
      
      if (this.getNumBathrooms() > DEFAULT_NUM_BATHROOMS)
         extraBedrooms = this.getNumBathrooms() - DEFAULT_NUM_BATHROOMS;
      
      // multiply basic rate by 1.5 if the total area is >= 3,000 sq ft
      if (this.getTotalArea() >= 3000)
         templateBasicRate *= 1.5;
      
      
      // calculate total cost
      totalCost = (templateBasicRate + ((800 * extraBedrooms) + (500 * extraBathrooms))) + TAX;
      
      this.setCost(totalCost); 
      
      return totalCost;
   }

}
