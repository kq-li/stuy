public class ScoresArray {
  private GameEntry[] _entries;
  private int _n;
  
  public ScoresArray(int n) {
    _entries = new GameEntry[n];

    for (int i = 0; i < n; i++)
      _entries[i] = new GameEntry();
    
    _n = n;
  }

  public ScoresArray() {
    this(10);
  }

  public boolean addLS(GameEntry entry) {
    if (entry.compareTo(_entries[_n - 1]) > 0) {
      _entries[_n - 1] = entry;

      for (int i = _n - 2; i >= 0; i--) {
        if (entry.compareTo(_entries[i]) > 0) {
          _entries[i + 1] = _entries[i];
          _entries[i] = entry;
        }
      }

      return true;
    }
    
    return false;
  }

  public boolean addBS(GameEntry entry) {
    int low = _n - 1;
    int high = 0;
    int mid = (low + high) / 2;

    while (mid != low && mid != high) {
      System.out.println(low + " " + mid + " " + high);
      int val = entry.compareTo(_entries[mid]);

      if (val > 0)
        low = mid;
      else if (val < 0)
        high = mid;
      else
        break;

      mid = (low + high) / 2;
    }

    System.out.println(mid);
    return false;
/*
    for (int i = _n - 1; i > mid; i--)
      _entries[i] = _entries[i - 1];

      _entries[mid] = entry;*/
  }      

  public void initialize(int len, int max) {
    for (int i = 0; i < _n; i++) 
      addLS(GameEntry.randomEntry(len, max));
  }
  
  public String toString() {
    String ret = "";
    ret += "Rank......Initials..Score\n\n";

    for (int i = 0; i < _n; i++)
      ret += (i) + "........." + _entries[i] + "\n";

    return ret;
  }

  public static void main(String[] args) {
    ScoresArray s = new ScoresArray();
    s.initialize(3, 100);
    System.out.println(s);
    GameEntry r = GameEntry.randomEntry(3, 0);
    System.out.println(r);
    s.addBS(r);
    System.out.println(s);
  }
}
