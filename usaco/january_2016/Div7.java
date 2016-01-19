import java.io.*;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Div7 {
  private static long modSum(long[] a, int start, int end, int base) {
    long ret = 0;
    for (int i = start; i < end; i++)
      ret += a[i] % base;
    return ret % base;
  }
      
  public static void main(String[] args) throws IOException {
    BufferedReader in = null;
    BufferedWriter out = null;

    try {
      in = new BufferedReader(new FileReader("div7.in"));
      out = new BufferedWriter(new FileWriter("div7.out"));

      int N = Integer.parseInt(in.readLine());
      long[] cows = new long[N];

      for (int i = 0; i < N; i++)
        cows[i] = Integer.parseInt(in.readLine());

      int ans = 0;
      int i = 0, j = N;
      
      while (j > i) {
        if (modSum(cows, i, j, 7) == 0) {
          ans = j - i;
          break;
        }
        j--;
        if (modSum(cows, i, j, 7) == 0) {
          ans = j - i;
          break;
        }
        i++;
      }
      
      for (i = 0; i < N; i++) {
        for (j = N; j > i; j--) {
          if (j - i > ans) {
            if (modSum(cows, i, j, 7) == 0) {
              ans = j - i;
            }
          }
        }
      }
      
      System.out.println(ans);
      out.write(ans + "\n");
      out.flush();
    } finally {
      if (in != null)
        in.close();
      if (out != null)
        out.close();
    }
  }
}
