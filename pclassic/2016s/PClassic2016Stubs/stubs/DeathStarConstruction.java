import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
class Point {
  int[] r;
  Point(int x, int y, int z) {
    r = new int[3];
    r[0] = x;
    r[1] = y;
    r[2] = z;
  }
}
class LineSeg {
  Point a, b;
  LineSeg(Point a, Point b) {
    this.a = a;
    this.b = b;
  }
}
public class DeathStarConstruction {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("DeathStarConstructionIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int n = Integer.parseInt(data[0]);
      LineSeg[] beams = new LineSeg[n];
      Point[] pts = new Point[2];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < 2; j++) {
          int x = Integer.parseInt(data[1+3*(2*i+j)]);
          int y = Integer.parseInt(data[2+3*(2*i+j)]);
          int z = Integer.parseInt(data[3+3*(2*i+j)]);
          pts[j] = new Point(x, y, z);
        }
        beams[i] = new LineSeg(pts[0], pts[1]);
      }
      System.out.println(canRemoveBeams(beams));
    }
    br.close();
  }
  public static boolean canRemoveBeams(LineSeg[] beams) {
    //fill out the body of this method
    return false;
  }
}
