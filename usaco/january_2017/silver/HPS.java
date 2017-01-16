import java.util.*;
import java.io.*;

public class HPS {
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
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("hps.in"));
    PrintWriter out = new PrintWriter("hps.out");

    int N = Integer.parseInt(in.readLine());
    String moves[] = new String[N];
    int numH[] = new int[N];
    int numP[] = new int[N];
    int numS[] = new int[N];
    int H = 0;
    int P = 0;
    int S = 0;

    for (int i = 0; i < N; i++) {
      String line = in.readLine();

      switch (line) {
      case "H":
        H++;
        break;
      case "P":
        P++;
        break;
      case "S":
        S++;
        break;
      }

      numH[i] = H;
      numP[i] = P;
      numS[i] = S;
      moves[i] = line;
    }

    // printArray(moves);
    // printArray(numH);
    // printArray(numP);
    // printArray(numS);

    int max = 0;
    
    for (int i = 0; i < N; i++) {
      int H1 = numH[i];
      int P1 = numP[i];
      int S1 = numS[i];
      int H2 = numH[N - 1] - numH[i];
      int P2 = numP[N - 1] - numP[i];
      int S2 = numS[N - 1] - numS[i];
      max = max(max,
                H1 + P2, H1 + S2,
                P1 + H2, P1 + S2,
                S1 + H2, S1 + P2);
    }

    System.out.println(max);
    out.println(max);

    in.close();
    out.close();
  }
}
