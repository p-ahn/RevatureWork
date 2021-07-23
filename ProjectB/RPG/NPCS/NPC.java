
public class NPC {
	String name;
	String id = "NPC";
	String desc;
	int hp;
	int accuracy;
	int inRoom;
	
	public void look() // every npc has a look method
	{
		System.out.println(name);
		
		System.out.println("HP:" +hp);
		System.out.println("Accuracy:" +accuracy);		
	}

}
