import java.lang.RuntimeException;

public class BarCode implements Comparable {
  private static String[] CODES = {"||:::", ":::||", "::|:|", "::||:", ":|::|",
                                     ":|:|:", ":||::", "|:::|", "|::|:", "|:|::"};
  private String _zip;
  private int _checkDigit;

  public BarCode(String zip) {
    if (zip.length() != 5)
      throw new IllegalArgumentException("Zip must be of length 5!");
    _zip = zip;
    _checkDigit = checkSum() % 10;
  }

  public BarCode(BarCode other) {
    _zip = other._zip;
    _checkDigit = other._checkDigit;
  }

  private int checkSum() {
    int sum = 0;
    for (int i = 0; i < _zip.length(); i++)
      sum += Integer.parseInt(_zip.substring(i, i + 1));
    return sum;
  }

  public String toString() {;
    String ret = "|";
    for (int i = 0; i < _zip.length(); i++)
      ret += CODES[Integer.parseInt(_zip.substring(i, i + 1))];
    ret += CODES[Integer.parseInt("" + _checkDigit)] + "|";
    return ret;
  }

  public int compareTo(Object other) {
    if (this == other)
      return 0;
    return Integer.parseInt(_zip) - Integer.parseInt(((BarCode) other)._zip);
  }

  public void draw() {
    String code = toString();
    double x = 0.2;
    double y = 0.5;
    double halfWidth = 0.005;
    double halfHeight = 0.1;
    double spacing = halfWidth;
    for (int i = 0; i < code.length(); i++) {
      String character = code.substring(i, i + 1);
      double currentHalfWidth = halfWidth;
      if (character.equals("|"))
        currentHalfWidth *= 2;
      System.out.println(character + " " + currentHalfWidth);
      StdDraw.filledRectangle(x, y, currentHalfWidth, halfHeight);
      x += currentHalfWidth * 2 + spacing;
    }
  }

  public static void main(String[] args) {
    BarCode a = new BarCode("08451");
    a.draw();
  }
}
