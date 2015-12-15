import java.lang.ArithmeticException;

public class Rational {
  private int _num, _denom;
  
  public Rational(int num, int denom) {
    if (denom == 0)
      throw new ArithmeticException();
    _num = num;
    _denom = denom;
  }

  public Rational(Rational other) {
    _num = other.getNum();
    _denom = other.getDenom();
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
  
  public Rational negate() {
    return new Rational(-getNum(), getDenom());
  }
  
  public static void main(String[] args) {
    Rational a = new Rational(4, 2);
    Rational b = new Rational(4, 2);
    Rational c = new Rational(4, 3);
    System.out.println(a.equals(a) + " " + a.equals(b) + " " + a.equals(c));
  }
}
