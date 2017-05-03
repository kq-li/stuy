import java.util.*;
import java.io.*;
public class ImageLock {
    public static void main(String[] args) throws FileNotFoundException {
        // Before submitting, make sure the main method hasn't been changed!
        Scanner sc = new Scanner(new FileReader("ImageLockIN.txt"));
        while(sc.hasNext()) {
            int width = sc.nextInt();
            int height = sc.nextInt();
            int[][] image = new int[width][height];
            for (int i = 0; i < height; i++) {
              for (int j = 0; j < width; j++) {
                image[j][i] = sc.nextInt();
              }
            }
            if (isCat(image)) {
              System.out.println("cat");
            } else {
              System.out.println("random");
            }
        }
        sc.close();
    }

  //Fill out the body of this method
  public static boolean isCat(int[][] image) {
    boolean ans = true;
    return ans;
  }
}
