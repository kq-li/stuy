import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ForceFluctuations {
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
		return 0L;
  }
}
