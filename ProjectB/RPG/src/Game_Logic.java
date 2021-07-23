import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game_Logic {
	public Game_Logic() {
		Game_Objects.room.add(new Room(0)); //this immediately creates room 0
		List<String> roomInfo = new ArrayList<>(); //create an array list called RoomInfo
		
		try { //this allows our entire file to be encapsulated in roomInfo
			roomInfo = readLines("TextFiles/RoomDescriptions.txt"); //this will read my txt document with all the room descriptions/lines
		} catch (IOException e) { //tried to implement this from lecture. 
			e.printStackTrace();
		}
		
		for (int i = 0; i < roomInfo.size(); i++) { //loops through roomInfo.size
			String[]firstWord = roomInfo.get(i).split(" "); // it splits it by spaces. These are two variables
			String[]everythingElse = roomInfo.get(i).split(":"); // and this one by semicolons
			
			if (firstWord[0].equals("RoomName:")) {
				int currentRoomSize = Game_Objects.room.size();
				Game_Objects.room.add(new Room(currentRoomSize));
				Game_Objects.room.get(Game_Objects.room.size() - 1).name = everythingElse[1];
				Game_Objects.room.get(Game_Objects.room.size() - 1).number = (currentRoomSize);
				
				int roomcount = 0; 
				for (int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if (nextFirstWord[0].equals("RoomName:")) { //So as it reads the files, and every time it sees RoomName:, it increments room count by 1
						roomcount++; // increments room count by 1 and creates a new room with that number 
					}
					if (roomcount == currentRoomSize) {
						
						if (nextFirstWord[0].equals("Desc:")) {
							
							String[] nextEverythingElse = roomInfo.get(z).split(":");
							Game_Objects.room.get(Game_Objects.room.size() - 1).desc.add(nextEverythingElse[1]);
						}
					}
				}
				 roomcount = 0;
				for (int z = 0; z <roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if (nextFirstWord[0].equals("RoomName:")) {
						roomcount++;
					}
					if (roomcount == currentRoomSize) {
						if (nextFirstWord[0].equals("Exit:")) {	
							
							String[] nextEverythingElse = roomInfo.get(z).split(":");
							Game_Objects.room.get(Game_Objects.room.size() - 1).exits.add(nextEverythingElse[1]);
						}
					}
				}
				
			}
		}
	}
	
	public List<String> readLines(String filename) throws IOException { //this accepts a file name as a string
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) !=null) { // Basically it takes the file and reads it as an array of lines
			lines.add(line);
		}
		bufferedReader.close();
		return lines; //returns an array list of all the lines in file, where every line is an index. this code will catch that as RoomInfo
		
	}

	public void waitforCommand() { 
		if (Game_Objects.pc.inRoom == 0) { //
			createCharacter();
		}
		System.out.println("\n---------------------------------\n");
		System.out.println("What would you like to do next?");
		System.out.println("\n---------------------------------\n");
		Scanner sc = new Scanner(System.in);
		String com = sc.nextLine(); // look troll (example)
		// parse the command by spaces
		// read each word into an array valueString s = "This is a sample
		// sentence.";
		String[] words = com.split(" "); // This splits the command 
		processCommand(words);

	}

	public void processCommand(String[] x) { // I will create if statements here. making look command here
		if (x[0].equals("look")) {
			look(x);
		}
		if (x[0].equals("summon")) {
			summon(x);
		}
		if (x[0].equals("get")) {
			get(x);
		}
		if (x[0].equals("create")) {
			create_item(x);
		}
		if (x[0].equals("wear")) {
			Game_Objects.pc.wear(x);	
		}
		if (x[0].equals("eq")) {
			Game_Objects.pc.eq();
		}
		if (x[0].equals("remove")) {
			Game_Objects.pc.remove(x);
		}
		if (x[0].equals("move")) {
			move(x);
		}

	
}
	public void create_item(String[] x) {
		if (x.length ==1) {
			System.out.println("Create what exactly?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) { //loop through entire item database 
				Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
				if (localItem.id.equalsIgnoreCase(x[1])) {
					
					for (int y = 0; y < Game_Objects.room.size(); y++) {
						   if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) { //get every room to see if it matches what room the pc is in
	                        	
							   try {  // we will get the room the user is in, then add a npc to that.
	                        		Game_Objects.room.get(y).item.add((Item) Class.forName(localItem.id).newInstance()); 
	                        				// localNPC.id is a string. and the method class.forname will check a class that matches that string and create a new instance.
	                        		System.out.println("You Create a " + Game_Objects.room.get(y).item.get(Game_Objects.room.get(y).item.size() -1).name);
	                        					//"you summon a "  get the room you're in, then take a look at item list, then get the last item that was added
	                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	                        	e.printStackTrace();
	                        }
							
						}
					}
				}
			}
		}
	}
	public void move(String[] x) { // move command
		if (x.length == 1) {
			System.out.println("Move Where?");
		}
		if (x.length ==2) {
			for (int i = 0; i < Game_Objects.room.size(); i++) { // check rooms
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) { // checks to see if room number is same
					for (int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) { // loops through all the exits in room
						
						String exitRequested = Game_Objects.room.get(i).exits.get(y); // loaded into a string
						String[] exitArray = exitRequested.split(" "); // split that string
						if (x[1].equalsIgnoreCase(exitArray[1])) { // check to see if user typed the correct exit
							Game_Objects.pc.inRoom = Integer.parseInt(exitArray[2]); // parse to an integer. set pc to whatever room it is
							System.out.println("You leave " + exitArray[1]);
							String[] random = new String[1];// string array has 1 position
							random[0] = "nothing"; //set it equal to nothing
							look(random); //i put this command here so look would work and just show what room you are in 
						}
					}
				}
			}
		}
	}
	public void get(String[] x) {
		if (x.length == 1) {
			System.out.println("Get What Exactly?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) { // loop through items in the database
				for (int y = 0; y < Game_Objects.room.size(); y++) // loop through rooms 
				{
					if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
						for (int z = 0; z < Game_Objects.room.get(y).item.size(); z++) { //loop through items in that room
							if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).item.get(z).id)) { 
								Item localItem = Game_Objects.room.get(y).item.get(z);
								
								Game_Objects.pc.item.add(localItem);
								System.out.println("You pick up a "  + localItem.name);
								Game_Objects.room.get(y).item.remove(z); // remove item, placed into the pc array list
								break;
							}
						}
							
					}
				}
			}
		}
	}
	
	public void look(String[] x) // looping through array list to get what we want
	{
		if (x.length == 1) { //if user only typed 1 word. Like look, string array has a position of 0 
			for (int i = 0; i < Game_Objects.room.size(); i++) { //looping through all the rooms in the game
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					System.out.println(Game_Objects.room.get(i).name);
					for (int y = 0; y < Game_Objects.room.get(i).desc.size(); y++) { // loops through all the descriptions in the game
						System.out.println(Game_Objects.room.get(i).desc.get(y)); // prints all desc line
					}
					System.out.println("\nExits:"); // after the description this would print all the exits
					for (int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) {
						
						String exitNameFull = Game_Objects.room.get(i).exits.get(y); // size of the exits arrays in room
						String[] exitName = exitNameFull.split(" "); // split north 3 
						System.out.println(exitName[1]);
					}
					for (int y= 0; y < Game_Objects.room.get(i).npc.size(); y++) {
						
						System.out.println(Game_Objects.room.get(i).npc.get(y).desc); //any npc in the room and printing the description
					}
					for (int y= 0; y < Game_Objects.room.get(i).item.size(); y++) {
						
						System.out.println(Game_Objects.room.get(i).item.get(y).desc); // any items in the room and then printing desc
				}
			}
		}
		}
		if (x.length == 2) { //checks 2nd value of the command
			
			if (x[1].equals("self")) { //prints information about the PC
				Game_Objects.pc.look();
				System.out.println("You Are Carrying:");
				for (int i = 0; i < Game_Objects.pc.item.size(); i++) { // all items in PC item array list
					System.out.println(Game_Objects.pc.item.get(i).name); // simply prints name
				}
			}
			
			for (int y= 0; y < Game_Objects.room.size(); y++) 
			{
				if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) { //loops through the rooms to see where we are
					
					for  (int i = 0; i < Game_Objects.room.get(y).npc.size(); i++) { // loops through all the npc in that room
						
						if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).npc.get(i).id)) { //if user types the same ID it will call the look method 
							Game_Objects.room.get(y).npc.get(i).look();
						}
					}
				}
			}
		}
		}
	
	
	
	
	public void summon(String[] x) {
		if (x.length == 1) {
			System.out.println("Summon What Exactly?");
		}
	
		if (x.length == 2) {
			
			for (int i = 0; i < Game_Objects.NPCDataBase.size(); i++) { //loop through entire NPC database
				NPC localNPC = (NPC) Game_Objects.NPCDataBase.get(i); //create a local NPC object and pull from database. This is called a cast
				if (localNPC.id.equalsIgnoreCase(x[1])) { // if ID equals what person typed the id is same as class
					for (int y = 0; y < Game_Objects.room.size(); y++) { //loop through all rooms in the game
                        if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) { //get every room to see if it matches what room the pc is in
                        	try {  // we will get the room the user is in, then add a npc to that.
                        		Game_Objects.room.get(y).npc.add((NPC) Class.forName(localNPC.id).newInstance()); 
                        				// localNPC.id is a string. and the method class.forname will check a class that matches that string and create a new instance.
                        		System.out.println("You Summon a " + Game_Objects.room.get(y).npc.get(Game_Objects.room.get(y).npc.size() -1).name);
                        					//"you summon a "  get the room you're in, then take a look at npc list, then get the last npc that was added
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        	e.printStackTrace();
                        }
                        
							}
						}
					}
				}
			}
			}
		
	

	public void createCharacter() {
		System.out.println("Available Commands: look, look self, look (npc), move, get, create, summon.");
		System.out.println("");
		System.out.println("Welcome to the world of HighgardRPG.\nBefore you start your adventure, what shall we call you?");
		System.out.println("Enter your name:");
		
		Scanner sc = new Scanner(System.in);
		Game_Objects.pc.name = sc.next();
		
		System.out.println("\n-----------------------------------------------------------------------------------------------------\n");
	
		System.out.println("You wake up on the outskirts of a shattered castle. In the distance, you see an old man");
		System.out.println("with a gray hat and beard staring at you, he approaches you.");
		System.out.println("");
		System.out.println("Old Man: Hello child, my name is Randalf and I have summoned you to help me defeat the demon lord.");
		System.out.println("You are humanities last hope in defeating the demon lord. My time here in coming to an end.");
		System.out.println("I have chosen you as my successor. You will now have the power of a Summoner.");
		System.out.println("Now go my child, defeat the demon lord and bring peace to humanity. The fate of the world lies in your hands.");
		System.out.println("");
		System.out.println("New Class Obtained: Summoner");
		System.out.println("Current Stats: 100hp , 75accuracy");
		System.out.println("\n");
		Game_Objects.pc.hp = 100;
		Game_Objects.pc.accuracy = 75;
		Game_Objects.pc.inRoom = 1;
	}
}