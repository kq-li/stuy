import java.util.*;
import java.io.*;

public class BPhoto {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
    PrintWriter out = new PrintWriter("bphoto.out");

    int N = Integer.parseInt(in.readLine());
    int[] heights = new int[N];

    for (int i = 0; i < N; i++) {
      heights[i] = Integer.parseInt(in.readLine());
    }

    int ans = 0;

    for (int i = 0; i < N; i++) {
      int left = 0;
      int right = 0;
      
      for (int j = 0; j < i; j++) {
        if (heights[j] > heights[i]) {
          left++;
        }
      }

      for (int j = i + 1; j < N; j++) {
        if (heights[j] > heights[i]) {
          right++;
        }
      }

      if ((double) right / left > 2 || (double) right / left < 0.5) {
        ans++;
      }
    }

    System.out.println(ans);
    out.println(ans);

    in.close();
    out.close();
  }
}
