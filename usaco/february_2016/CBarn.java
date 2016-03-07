import java.io.*;
import java.util.*;

public class CBarn {
  public static int dist(int n, int start, int end) {
    if (start > end)
      return n - start + end;
    return start - end;
  }
  
  public static int[] shift(int[] a, int n) {
    int[] b = new int[a.length];

    for (int i = 0; i < a.length; i++)
      b[(i + n) % a.length] = a[i];

    return b;
  }
  
  public static int energy(int[] cows, int n) {
    int energy = 0;

    for (int i = n - 1; i > 0; i--)
      energy += (cows[i] - (cows[0] - n + i)) * (cows[i] - (cows[0] - n + i));

    return energy;
  }

  public static void printArr(int[] a) {
    for (int i : a)
      System.out.print(i + " ");
    System.out.println();
  }

  public static boolean complete(boolean[] a) {
    for (boolean b : a)
      if (!b)
        return false;
   
    return true;
  }
  
  public static void main(String[] args) {
    BufferedReader in = null;
    BufferedWriter out = null;

    try {
      in = new BufferedReader(new FileReader("cbarn.in"));
      out = new BufferedWriter(new FileWriter("cbarn.out"));

      int n = Integer.parseInt(in.readLine());
      int[] rooms = new int[n];
      int[] cows = new int[n];
      
      for (int i = 0; i < n; i++) 
        rooms[i] = Integer.parseInt(in.readLine());

      int room = 0;
      int index = 0;

      while (index < n) {
        for (int i = 0; i < rooms[room]; i++)
          cows[index++] = room;
        
        room++;
      }

      int[] original = Arrays.copyOf(cows, n);
      boolean[] occupied = new boolean[n];
      int[] newPos = new int[n];
      int energy = 0;
      
      for (int cow = 0; cow < n; cow++) {
        if (!occupied[cows[cow]]) {
          occupied[cows[cow]] = true;
          newPos[cow] = cows[cow];
          System.out.println(cows[cow] + " is now occupied!");
          cows[cow] = -1;
        }
      }
      
      while (!complete(occupied)) {
        for (int cow = 0; cow < n; cow++) {
          if (cows[cow] != -1) {
            if (!occupied[cows[cow]]) {
              occupied[cows[cow]] = true;
              newPos[cow] = cows[cow];
              int cost = (int) Math.pow(dist(n, original[cow], newPos[cow]), 2);
              energy += cost;
              System.out.println("Cow moved from " + original[cow] + " to " + newPos[cow] + " costing " + cost + " energy");
              cows[cow] = -1;
            } else {
              cows[cow] = (cows[cow] + 1) % n;
            }
          }
        }
      }

      printArr(original);
      printArr(newPos);

      System.out.println(energy);
      
      in.close();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
