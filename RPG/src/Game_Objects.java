import java.util.ArrayList;
import java.util.List;

// This will contain lists such as lists of items, NPCs, etc.

public class Game_Objects {
	static PC pc= new PC(); 
	static ArrayList<Room> room = new ArrayList<Room>();
	
	
	static List<Object>NPCDataBase = new ArrayList<Object>();// lists all the creatures in the game
	static List<Object>ItemDataBase = new ArrayList<Object>(); //lists all the items in the game
	
	
	
	
	
	public static void initalizeNPCArray() { // creates a method that initializes this array. this is the master list
	
		NPCDataBase.add(new NPC());
		NPCDataBase.add(new Troll());
		NPCDataBase.add(new Dragon());
		NPCDataBase.add(new DemonLord());
	}
	
	public static void initalizeItemArray() {
		ItemDataBase.add(new Item());
		ItemDataBase.add(new Flaming_Sword());
		ItemDataBase.add(new Diamond_Ring());
		ItemDataBase.add(new Fire_Staff());
	}

}
