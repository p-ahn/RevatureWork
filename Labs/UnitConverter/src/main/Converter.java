package main;


import java.util.Scanner;

public class Converter {
	 // this allows the quantity from unit1 to be converted to unit2
	   private static double collectQuantity(String unit1, String unit2, Scanner scan) {
	      
	       double qty;
	       System.out.print("Enter the amount in "+unit1+" to convert to "+unit2+": ");
	       qty = scan.nextDouble();
	      
	       return qty;
	   }
	  
	   // cups to teaspoon - Volume Category
	   private static double convertCupsToTeaspoons(double qty) {
	      
	       return (qty*48);
	   }
	   
	   //teaspoon to tablespoon
	   private static double convertTeaspoonsToTablespoons(double qty)
	   {
		   return(qty/3);
	   }
	   
	   // teaspoon to cup - Volume Category
	   
	   private static double convertTeaspoonsToCups(double qty) 
	   {
		   return (qty*0.0208333);
	   }
	   
	   // feet to meters - Distance Category
	   private static double convertFeetToMeters(double qty) 
	   { 
		   return (qty*0.3048);
	   }
	   
	   //meters to feet - Distance Category
	   private static double convertMetersToFeet(double qty)
	   {
		   return (qty*3.2808);
	   }
	  
	   // miles to km - Distance Category
	   private static double convertMilesToKilometers(double qty)
	   {
	       return(qty*1.60934);
	   }
	   
	   //km to miles - Distance Category
	   private static double convertKilometersToMiles(double qty)
	   {
		   return(qty/1.60934);
	   }
	  
	   // Us gallons to imperial gallons - Volume Category
	   private static double convertUSGallonsToImperialGallons(double qty)
	   {
	       return(qty*0.83267384);
	   }
	   
	   // Imperial gallons to US Gallons - Volume Category
	   private static double convertImperialGallonsToUSGallons(double qty)
	   {
		   return(qty*1.20095);
	   }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		   int menuSelection = 0;
	       double qty_unit1, qty_unit2;
	       Scanner scan = new Scanner(System.in);
	       // this continues the loop until user presses 10
	       while(menuSelection != 10)
	       {
	           //display the menu
	           System.out.println("Please select an option:");
	           System.out.println();
	           System.out.println("Volume Category:");
	           System.out.println("1. Cups to Teaspoons");
	           System.out.println("2. Teaspoons to Tablespoons");
	           System.out.println("3. Teaspoons to Cups");
	           System.out.println("4. US Gallons to Imperial Gallons");
	           System.out.println("5. Imperial Gallons to US Gallons");
	           System.out.println();
	           System.out.println("Distance Category:");
	           System.out.println("6. Feet to Meters");
	           System.out.println("7. Meters to Feet");
	           System.out.println("8. Miles to Kilometers");
	           System.out.println("9. Kilometers to Miles");
	           System.out.println("10. Quit");

	           // input of menu choice
	           menuSelection = scan.nextInt();
	          
	           // based on the choice perform the action
	           switch(menuSelection)
	           {
	           case 1:
	               qty_unit1 = collectQuantity("cups","teaspoons",scan);
	               qty_unit2 = convertCupsToTeaspoons(qty_unit1);
	               System.out.println(qty_unit1+" cups = "+qty_unit2+" teaspoons.");
	               break;
	               
	           case 2:
	        	   qty_unit1 = collectQuantity("teaspoons","tablespoons",scan);
	        	   qty_unit2 = convertTeaspoonsToTablespoons(qty_unit1);
	        	   System.out.println(qty_unit1+" teaspoons = "+qty_unit2+" tablespoons.");
	        	   break;
	        	    
	           case 3:
	        	   qty_unit1 = collectQuantity("teaspoons","cups",scan);
	        	   qty_unit2 = convertTeaspoonsToCups(qty_unit1);
	        	   System.out.println(qty_unit1+" teaspoons = "+qty_unit2+" cups.");
	        	   break;
	        	   
	           case 6:
	        	   qty_unit1 = collectQuantity("feet","meters",scan);
	        	   qty_unit2 = convertFeetToMeters(qty_unit1);
	        	   System.out.println(qty_unit1+" feet = "+qty_unit2+" meters.");
	        	   break;
	        	   
	           case 7:
	        	   qty_unit1 = collectQuantity("meters","feet",scan);
	        	   qty_unit2 = convertMetersToFeet(qty_unit1);
	        	   System.out.println(qty_unit1+" meters = "+qty_unit2+" feet.");
	        	   break;
	        	   
	           case 8:
	               qty_unit1 = collectQuantity("miles","kilometers",scan);
	               qty_unit2 = convertMilesToKilometers(qty_unit1);
	               System.out.println(qty_unit1+" miles = "+qty_unit2+" kilometers.");
	               break;
	               
	           case 9:
	        	   qty_unit1 = collectQuantity("kilometers","miles",scan);
	               qty_unit2 = convertKilometersToMiles(qty_unit1);
	               System.out.println(qty_unit1+" kilometers = "+qty_unit2+" miles.");
	               break;
	               
	           case 4:
	               qty_unit1 = collectQuantity("U.S gallons","Imperial gallons",scan);
	               qty_unit2 = convertUSGallonsToImperialGallons(qty_unit1);
	               System.out.println(qty_unit1+" US gallons = "+qty_unit2+" Imperial gallons.");
	               break;
	               
	           case 5:
	        	   qty_unit1 = collectQuantity("Imperial gallons","U.S gallons",scan);
	               qty_unit2 = convertImperialGallonsToUSGallons(qty_unit1);
	               System.out.println(qty_unit1+" Imperial gallons = "+qty_unit2+" U.S gallons.");
	               break;
	               
	           case 10:
	               System.out.println("Thank you for using the application");
	               break;
	               
	           default:
	               System.out.println("Invalid choice");
	           }
	           System.out.println();
	       }
	      
	       scan.close();

	}

}
