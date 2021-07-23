//This is my main method where loops will happen 
public class main {
	static Game_Logic gl= new Game_Logic(); // instance of the game logic names GL

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game_Objects.initalizeNPCArray(); //after game logic is created, we will populate npc array to setup our database.
		Game_Objects.initalizeItemArray();
		
		while(true)
		{
		game_loop();
		}

	}
	public static void game_loop()  
	{
		gl.waitforCommand(); // this will constantly wait for commands
	}

}

