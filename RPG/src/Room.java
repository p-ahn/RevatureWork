import java.util.ArrayList;
import java.util.List;

public class Room {
	int number; // every room has  an integer called number
	String name; // every room will have a string called name (name the user sees)
	List<String> desc = new ArrayList<String>(); //every room will have an array list. desc = 0,1,2,3,4
	List<String> exits = new ArrayList<String>();// exits will loop through rooms (south, number)
	List<NPC> npc = new ArrayList<NPC>(); //Any npc that is in a room that has been constructed will show here. 
	ArrayList<Item> item = new ArrayList<Item>(); // array list of items 
	
	
	public Room(int x)
	{
		
		number = x;
		
	}
	

}