import java.lang.ArithmeticException;

public class Rational implements Comparable {
  private int _num, _denom, _gcd;
  
  public Rational(int num, int denom) {
    if (denom == 0)
      throw new ArithmeticException();
    if (num == 0)
      _gcd = 1;
    else
      _gcd = MyMath.gcd(num, denom);
    _num = num / _gcd;
    _denom = denom / _gcd;
  }

  public Rational(Rational other) {
    this(other.getNum(), other.getDenom());
  }

  public int getNum() {
    return _num;
  }

  public int getDenom() {
    return _denom;
  }
  
  public String toString() {
    return getNum() + "/" + getDenom();
  }

  public boolean equals(Object other) {
    return (this == other) || ((other instanceof Rational) &&
                               (getNum() == ((Rational) other).getNum()) &&
                               (getDenom() == ((Rational) other).getDenom()));
  }
  
  public int compareTo(Object other) {
    return getNum() * ((Rational) other).getDenom() - ((Rational) other).getNum() * getDenom();    
  }

  public Rational negate() {
    return new Rational(-getNum(), getDenom());
  }

  public Rational add(Rational other) {
    return new Rational(getNum() * other.getDenom() + other.getNum() * getDenom(),
                        getDenom() * other.getDenom());
  }

  public Rational subtract(Rational other) {
    return add(other.negate());
  }

  public Rational multiply(Rational other) {
    return new Rational(getNum() * other.getNum(), getDenom() * other.getDenom());
  }

  public Rational invert() {
    return new Rational(getDenom(), getNum());
  }

  public Rational divide(Rational other) {
    return multiply(other.invert());
  }
  
  public static void main(String[] args) {
    Rational[] rats = new Rational[5];
    for (int i = 0; i < rats.length; i++) 
      rats[i] = new Rational((int) (Math.random() * 9 + 1), (int) (Math.random() * 9 + 1));
    for (Rational r : rats)
      System.out.println(r);
    Sorts.selectionSort(rats);
    for (Rational r : rats)
      System.out.println(r);
  }
}
