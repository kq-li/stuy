import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;


public class Bugs {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("BugsIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int N = Integer.parseInt(data[0]);
      int M = Integer.parseInt(data[1]);
      int[][] A = new int[M][2];
      for (int i = 0; i < M; i++) {
        data = br.readLine().split(" ");
        A[i][0] = Integer.parseInt(data[0]);
        A[i][1] = Integer.parseInt(data[1]);
      }
      System.out.println(hasBug(N, M, A));
    }
    br.close();
  }


  // Fill out the body of this method
  public static boolean hasBug(int N, int M, int[][] A) {
    return false;
  }
}
