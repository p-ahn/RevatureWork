
public class LinearSearch {

	public static void main(String[] args) {
        //create some dummy data for our method
		char letter = '0';
		char[] letters= null;
		
		
	

        //call your methods here
        LinearSearch ls = new LinearSearch();

        System.out.println(ls.findIndex(letter, letters));
        }
    

    //create your first method here
	public int findIndex(char target, char[] data) {
		   if (data == null ) return -1;
	    int result = -1;

	    //loop through the data
	    for (int i = 0; i < data.length; i++) {
	        char temp = data[i];
	        
	        //test current element against target
	        if (temp == target) {   
	            return i;
	        }
	    }

	    return result;
	}
}