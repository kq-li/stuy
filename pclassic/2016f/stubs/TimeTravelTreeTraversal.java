import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;



public class TimeTravelTreeTraversal {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("TimeTravelTreeTraversalIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int m = Integer.parseInt(data[0]);
      int n = 2*m + 1;
      int[][] splits = new int[n][2];
      for (int i = 0; i < n; i++) {
        splits[i][0] = -1;
        splits[i][1] = -1;        
      }
      for (int i = 0; i < m; i++) {
        data = br.readLine().split(" ");
        int k = Integer.parseInt(data[0]);
        splits[k][0] = Integer.parseInt(data[1]);
        splits[k][1] = Integer.parseInt(data[2]);        
      }
      System.out.println(maxDistance(splits));
    }
    br.close();
  }
  //Fill out the body of this method
  //splits is an n by 2 array such that timeline i splits into timelines split1[i][0] and
  //splits[i][1]. If timeline i does not split then split[i][0] == split[i][1] == -1
  public static int maxDistance(int[][] splits) {
    return 0;
  }
}
