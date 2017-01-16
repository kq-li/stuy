import java.util.*;
import java.io.*;

public class CowCode {
  public static int max(int... args) {
    int max = 0;
    
    for (int i : args) {
      if (i > max) {
        max = i;
      }
    }

    return max;
  }     
  
  public static void printArray(int[] arr) {
    String ret = "[";
    
    for (int i : arr) {
      ret += i + " ";
    }

    ret = ret.substring(0, ret.length() - 1) + "]";
    System.out.println(ret);
  }

  public static void printArray(String[] arr) {
    String ret = "[";
    
    for (String i : arr) {
      ret += i + " ";
    }

    ret = ret.substring(0, ret.length() - 1) + "]";
    System.out.println(ret);
  }

  public static String transform(String s) {
    return s + s.substring(s.length() - 1) + s.substring(0, s.length() - 1);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("cowcode.in"));
    PrintWriter out = new PrintWriter("cowcode.out");

    String line[] = in.readLine().split(" ");
    String s = line[0];
    long n = Long.parseLong(line[1]) - 1;

    int baseLength = s.length();
    int power = (int) Math.ceil(Math.log((double) n / baseLength) / Math.log(2));
    long length = (long) (baseLength * Math.pow(2, power));

    System.out.println(length);

    while (n > baseLength) {
      // System.out.println(n);

      if (n >= length / 2) {
        n = n - (length / 2 + 1);

        if (n < 0) {
          n += length / 2;
        }
      }

      length /= 2;
    }

    String ans = s.substring((int) n, (int) n + 1);
    System.out.println(ans);
    out.println(ans);

    // int baseLength = s.length();
    // int length = s.length();
    // int pos[] = new int[length];
    // String ans = null;

    // for (int i = 0; i < length; i++) {
      // pos[i] = i;
    // }

    // printArray(pos);

    // while (n > length) {
      // for (int i = 0; i < baseLength; i++) {
        // int curPos = pos[i] + length + 1;

        // if (curPos >= 2 * length) {
          // curPos -= length;
        // }

        // pos[i] = curPos;

        // if (curPos == n) {
          // ans = s.substring(i, i + 1);
          // break;
        // }
      // }

      // if (ans != null) {
        // break;
      // }

      // printArray(pos);
      
      // length *= 2;
    // }

    // System.out.println(ans);

    // out.println(ans);

    in.close();
    out.close();
  }
}

/*

  COW

  Cs: 0
  Os: 1
  Ws: 2
  
  COWWCO

  Cs: 0, 4
  Os: 1, 5
  Ws: 2, 3
  
  COWWCOOCOWWC

  Cs: 0, 4, 7, 11
  Os: 1, 5, 6, 8
  Ws: 2, 3, 9, 10
  
  COWWCOOCOWWCCCOWWCOOCOWW

  Cs: 0, 4, 7, 11, 12, 13, 17, 20
  Os: 1, 5, 6, 8, 14, 18, 19, 21
  Ws: 2, 3, 9, 10, 15, 16, 22, 23
 */
