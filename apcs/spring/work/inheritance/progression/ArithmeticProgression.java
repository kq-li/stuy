public class ArithmeticProgression extends Progression {
  protected int _diff;

  public ArithmeticProgression(int first, int diff) {
    super(first);
    _diff = diff;
  }

  public ArithmeticProgression(int diff) {
    this(1, diff);
  }

  public ArithmeticProgression() {
    this(1, 2);
  }

  public int nextValue() {
    _curr += _diff;
    return _curr;
  }

  public static void main(String[] args) {
    Progression p1 = new ArithmeticProgression();
    Progression p2 = new ArithmeticProgression(3);
    Progression p3 = new ArithmeticProgression(2, 5);
    p1.printProgression(10);
    p2.printProgression(10);
    p3.printProgression(10);
  }
}
