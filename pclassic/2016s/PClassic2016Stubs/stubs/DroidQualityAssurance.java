import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DroidQualityAssurance {
  //Fill out the body of this method
  public static boolean hasShortCircuit(ArrayList<LinkedList<Integer>> pins){
    return false;
  }
	
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader b = new BufferedReader(new FileReader("DroidQualityAssuranceIN.txt"));
		
    while(b.ready()){	
      ArrayList<LinkedList<Integer>> pins = new ArrayList<LinkedList<Integer>>();

      int numPins = Integer.parseInt(b.readLine());
			
      for(int i = 0; i < numPins; i++){
        LinkedList<Integer> connections = new LinkedList<Integer>();
        String[] connectionsInfo = b.readLine().split(" ");
				
        for(int j = 1; j < connectionsInfo.length; j++){
          connections.add(Integer.parseInt(connectionsInfo[j]));
        }
				
        pins.add(i, connections);	
      }
			
      b.readLine();
      b.readLine();
      b.readLine();
			
      System.out.println(hasShortCircuit(pins));
    }
		
    b.close();
  }
}
