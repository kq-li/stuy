import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class RocketTests {

	public static void main(String[] args) throws IOException{
		String sequence = "";
		BufferedReader br = new BufferedReader(new FileReader(
	               "RocketTestsIN.txt"));
	    while (br.ready()) {
	        sequence = br.readLine();
		    System.out.println(longestSeq(sequence));	
	    }
	}
	
	public static int longestSeq(String seq){
		int smallCount = 0;
		int bigCount = 0;
		int current = -1;
		int x = 0;
		while(x < seq.length()) {
		int next = Integer.parseInt(seq.substring(x, x+1));

		 if (current < next){
			 smallCount++;
			 if (smallCount > bigCount)
				 bigCount = smallCount;		 
		 }
		 else
			smallCount = 1;	
		 
		 current = next;
		 x++;
		}
		
		return bigCount;
	}
}
