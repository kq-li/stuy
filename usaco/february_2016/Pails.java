import java.io.*;
import java.util.*;

public class Pails {
  int pail1, pail2;
  public static void 
  
  public static void main(String[] args) {
    BufferedReader in;
    BufferedWriter out;

    try {
      in = new BufferedReader(new FileReader("pails.in"));
      out = new BufferedWriter(new FileWriter("pails.out"));

      String[] line = in.readLine().split(" ");

      int X = Integer.parseInt(line[0]);
      int Y = Integer.parseInt(line[1]);
      int K = Integer.parseInt(line[2]);
      int M = Integer.parseInt(line[3]);

      
      
      in.close();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
