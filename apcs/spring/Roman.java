import java.lang.ArithmeticException;

public class Roman {
  private int _n;
  private String _numeral;

  private static String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC",
                                     "L", "XL", "X", "IX", "V", "IV", "I"};
  private static int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  
  public Roman(int n) {
    if (n <= 0 || n >= 4000)
      throw new ArithmeticException();
    _n = n;
    _numeral = toRoman(n);
  }

  private static int symbolIndex(String symbol) {
    for (int i = 0; i < SYMBOLS.length; i++)
      if (symbol.equals(SYMBOLS[i]))
        return i;
    return -1;
  }

  private static int valueIndex(int value) {
    for (int i = 0; i < VALUES.length; i++)
      if (value == VALUES[i])
        return i;
    return -1;
  }

  private static String toRoman(int n) {
    return toRoman(n, 0);
  }
  
  private static String toRoman(int n, int curIndex) {
    if (curIndex >= VALUES.length)
      return "";
    if (n < VALUES[curIndex])
      return toRoman(n, curIndex + 1);
    return SYMBOLS[curIndex] + toRoman(n - VALUES[curIndex], curIndex);
  }

  public String toString() {
    return _numeral;
  }

  public boolean equals(Object o) {
    return (this == other) || (o instanceof Roman &&
                               ((Roman) o)._n == _n);
  }
  
  public static void main(String[] args) {
    int[] test = {149, 59, 1005, 2, 7, 29, 3999, 4071, 5000};
    for (int t : test)
      System.out.println(t + " -> " + new Roman(t));
  }
}
    
