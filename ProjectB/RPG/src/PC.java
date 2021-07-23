import java.util.ArrayList;

// Simple code that will show Player status like hp, accuracy, etc

public class PC {
	
	String name;
	int hp;
	int accuracy;
	int inRoom = 0;
	ArrayList<Item> item = new ArrayList<Item>();
	ArrayList<Item> wornItems = new ArrayList<Item>();
	
	public void look() {
		System.out.println("hp:" + hp);
		System.out.println("accuracy:" + accuracy);

	}
	
	public void remove(String[] x) {
		for (int i = 0; i < wornItems.size(); i++) { //loop through worn items
			
			if (wornItems.get(i).id.equals(x[1])) { // check to see if the Id equals what they typed in
				
				System.out.println("You Remove a " + wornItems.get(i).id);
				item.add(wornItems.get(i)); //adds it back to items/user inventory
				wornItems.remove(i); //removes from worn items
			}
		}
	}


	public void eq() {
		for (int i = 0; i < wornItems.size(); i++) { //loops through worn items
			System.out.println(wornItems.get(i).name + ":" + wornItems.get(i).wearloc);
		}
	}
	public void wear(String[] x) { // wear is going to accept a string
		if (wornItems.size() == 0) {
			
			for (int i = 0; i < item.size(); i++) { // loops through all items in inventory
				if (x[1].equalsIgnoreCase(item.get(i).id) && item.get(i).isWearable) {

					wornItems.add(item.get(i)); // adds item to worn items
					System.out.println("You wear a " + item.get(i).name);
					item.remove(i);
					break;
				}
			}
		}

		else
			// this is a code for if there is an already in the inventory
		{
			boolean isWearing = false;
			for (int i=0; i < wornItems.size(); i++) { // loops through all worn items
				for (int z =0; z < item.size(); z++) // loops through all items in inventory
				{
					if (x[1].equalsIgnoreCase(item.get(z).id) && item.get(z).isWearable) // matches id and to see if its wearable
					{
						if (item.get(z).wearloc.equals(wornItems.get(i).wearloc)) //checking each worn item to see if they have the same worn location
						{
							System.out.println("You already have something worn in that location.");
							isWearing = true;
						}
					}
				}
				if (!isWearing) // this checks if its false
				{
					wornItems.add(item.get(i)); // add item to worn item          
					System.out.println("You wear a " + item.get(i).name);
					item.remove(i); // remove it from inventory
					
					break;
				}
			}
		}
	}

}
