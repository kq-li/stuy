import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Enigma {
  public static void main(String[] args) throws IOException {
    BufferedReader b = new BufferedReader(new FileReader("EnigmaIN.txt"));
    while (b.ready()) {
      String s1 = b.readLine();
      String s2 = b.readLine();
      System.out.println(first_is_random(s1, s2));
    }
  }
  // Fill out the body of this method, you should return "nonrandom" if the first string
  // is the non random on and "random" otherwise
  public static String first_is_random(String s1, String s2) {
    return "nonrandom";
  }
}
