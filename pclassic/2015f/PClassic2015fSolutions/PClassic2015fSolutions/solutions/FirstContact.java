import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FirstContact {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("FirstContactIN.txt"));

    while (br.ready()) {
      String[] input = br.readLine().split(" ");
      long numRows = Long.parseLong(input[0]);
      long divisor = Long.parseLong(input[1]);
      long modulus = Long.parseLong(input[2]);
      System.out.println(countDivisible(numRows, divisor, modulus));
    }
    br.close();
  }

  // Fill out the body of this method
  public static long countDivisible(
      long numRows, 
      long divisor,
      long modulus) {
    Long[] base = convertBase(numRows - 1, divisor);
    long multiplier = 1;
    long aftermult = 1;
    for (int i = 0; i < base.length; i++) {
      multiplier *= base[i] + 1;
    }
    long answer = multiplier;
    for (int i = 0; i < base.length; i++) {
      multiplier /= base[i] + 1;
      answer += multiplier * (base[i] + 1) * base[i] * aftermult / 2;
      answer %= modulus;
      aftermult *= divisor * (divisor + 1) / 2;
      aftermult %= modulus;
    }
    answer = (numRows * (numRows + 1) / 2) - answer;
    answer %= modulus;
    while (answer < 0) answer += modulus;
    return answer;
  }
  public static long count(long x, long d) {
    long ret = 1;
    long y = x;
    while (y != 0) {
      ret *= (y % d) + 1;
      y /= d;
    }
    return x - ret + 1;
  }
  public static Long[] convertBase(long x, long p) {
    ArrayList<Long> ret = new ArrayList<Long>();
    while (x != 0) {
      ret.add(x % p);
      x /= p;
    }
    return ret.toArray(new Long[1]);
  }
}
