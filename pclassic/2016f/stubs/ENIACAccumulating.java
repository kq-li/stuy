import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ENIACAccumulating {
  public static void main(String[] args) throws FileNotFoundException {
    // Before submitting, make sure the main method hasn't been changed!
    Scanner sc = new Scanner(new FileReader("ENIACAccumulatingIN.txt"));
    while(sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] elements = line.split(",");
      int[] a = new int[elements.length];
      for(int i = 0; i < elements.length; i++) {
        a[i] = Integer.parseInt(elements[i]);
      }
      int[] output = accumulate(a);
      String s = Integer.toString(output[0]);
      for(int j = 1; j < output.length; j++) { 
        s += "," + output[j];
      } 
      System.out.println(s);
    }
    sc.close();
  }

  // Fill out the body of this method
  // 'a' is an integer array of length n + 1 representing an n digit 0-padded positive integer
  // return a incremented by 1
  public static int[] accumulate(int[] a) {
    return null;
  }
}
