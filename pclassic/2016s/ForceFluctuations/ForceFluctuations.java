import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ForceFluctuations {
  static class Point {
    long value;
    ArrayList<Integer> smaller;

    public Point(long value) {
      this.value = value;
      this.smaller = new ArrayList<Integer>();
    }
  }
  
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("ForceFluctuationsIN.txt"));

    while (br.ready()) {
			int len = Integer.parseInt(br.readLine());
      String[] data = br.readLine().split(" ");
			long[] measurements = new long[data.length];
			for (int i = 0; i < data.length; i++) {
				measurements[i] = Long.parseLong(data[i]);
			}
      System.out.println(countTriples(measurements));
    }
    br.close();
  }

	// Fill out the body of this method
  public static long countTriples(long[] measurements) {
    Point[] points = new Point[measurements.length];
    
    for (int i = 0; i < measurements.length; i++) {
      Point p = new Point(measurements[i]);
      
      for (int j = i + 1; j < measurements.length; j++) 
        if (measurements[j] < measurements[i])
          p.smaller.add(j);

      points[i] = p;
    }

    long ret = 0L;
    
    for (int i = 0; i < points.length; i++) {
      Point p = points[i];
      
      for (int j : p.smaller)
        ret += points[j].smaller.size();
    }
    
    return ret;
  }
}
