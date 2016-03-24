public class Entry implements Comparable<Entry>{
  private String _name;
  private int _score;

  public Entry() {
    this("AAA", 0);
  }
  
  public Entry(String name, int score) {
    _name = name;
    _score = score;
  }

  public String getName() {
    return _name;
  }

  public int getScore() {
    return _score;
  }

  public int compareTo(Entry o) {
    if (getScore() == o.getScore())
      return getName().compareTo(o.getName());
    
    return getScore() - o.getScore();
  }
  
  public String toString() {
    return _name + Util.duplicate(".", 10 - _name.length()) + _score;
  }

  public static void main(String[] args) {
    Entry a = new Entry();
    Entry b = new Entry("AZR", 1337);
    Entry c = new Entry("N00B", -9999);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }
}
