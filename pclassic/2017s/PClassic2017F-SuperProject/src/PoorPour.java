import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;



public class PoorPour {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("PoorPourIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int n = data.length;
      int[] heights = new int[n];
      for (int i = 0; i < n; i++) {
        heights[i] = Integer.parseInt(data[i]);
      }
    
      System.out.println(volume(heights));
    }
    br.close();
  }
  // Fill out the body of this method
  public static int volume(int[] heights) {
	  
	  int [] prefixMax = new int[heights.length];
	  int [] suffixMax = new int[heights.length];
	  int [] prefixMaxIndex = new int[heights.length];
	  int [] suffixMaxIndex = new int[heights.length];
	  int [] prefixSum = new int[heights.length];
	  //if there are multiple occurrences of the prefixMax, which one should we take as the index?
	  
	  
	  
	  
	  
    return 0;
  }
}
