import java.util.*;
import java.io.*;

public class CowDance {
  public static int minPos(ArrayList<Integer> list) {
    return minPos(list, 0, list.size());
  }
  
  public static int minPos(ArrayList<Integer> list, int start, int end) {
    int min = list.get(start);
    int minPos = start;
    
    for (int i = start + 1; i < end; i++) {
      if (list.get(i) < min) {
        min = list.get(i);
        minPos = i;
      }
    }

    return minPos;
  }

  public static int maxPos(ArrayList<Integer> list) {
    return maxPos(list, 0, list.size());
  }

  public static int maxPos(ArrayList<Integer> list, int start, int end) {
    int max = list.get(start);
    int maxPos = start;

    for (int i = start + 1; i < end; i++) {
      if (list.get(i) > max) {
        max = list.get(i);
        maxPos = i;
      }
    }

    return maxPos;
  }
  
  public static void printList(ArrayList<Integer> list) {
    printList(list, 0, list.size());
  }
  
  public static void printList(ArrayList<Integer> list, int start, int end) {
    if (end - start < 1) {
      System.out.println("[]");
    } else {
      System.out.print("[" + list.get(start));

      for (int i = start + 1; i < end; i++) {
        System.out.print(" " + list.get(i));
      }

      System.out.println("]");
    }
  }

  public static ArrayList<Integer> subList(ArrayList<Integer> list, int start, int end) {
    ArrayList<Integer> ret = new ArrayList<Integer>();

    for (int i = start; i < end; i++) {
      ret.add(list.get(i));
    }

    return ret;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
    PrintWriter out = new PrintWriter("cowdance.out");

    String[] line = in.readLine().split(" ");
    int N = Integer.parseInt(line[0]);
    int T = Integer.parseInt(line[1]);
    ArrayList<Integer> cows = new ArrayList<Integer>();

    for (int i = 0; i < N; i++) {
      cows.add(Integer.parseInt(in.readLine()));
    }
    
    int ans = 0;

    for (int K = 1; K < N; K++) {
      ArrayList<Integer> copy = new ArrayList<Integer>(cows);
      // printList(copy, 0, K);
      // printList(copy, K, N);

      boolean fine = true;
      
      for (int i = K; i < N; i++) {
        int minPos = minPos(copy, 0, K);
        copy.set(minPos, copy.get(minPos) + copy.get(i));

        if (copy.get(minPos) > T) {
          fine = false;
          break;
        }
      }

      if (!fine) {
        continue;
      }

      // printList(copy);

      if (copy.get(maxPos(copy)) <= T) {
        ans = K;
        break;
      }
    }
    
    System.out.println(ans);
    out.println(ans);

    in.close();
    out.close();
  }
}
