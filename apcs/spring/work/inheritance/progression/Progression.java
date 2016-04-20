public class Progression {
  protected int _first, _curr;

  public Progression(int first) {
    _first = first;
  }

  public Progression() {
    this(1);
  }

  public int firstValue() {
    _curr = _first;
    return _curr;
  }

  public int nextValue() {
    return ++_curr;
  }

  public void printProgression(int n) {
    String ans = firstValue() + " ";

    for (int i = 1; i < n; i++)
      ans += nextValue() + " ";

    System.out.println(ans);
  }

  public static void main(String[] args) {
    Progression p = new Progression(6);
    p.printProgression(20);
  }
}
      
