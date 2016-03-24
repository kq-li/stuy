public class GameEntry implements Comparable<GameEntry> {
  private String _name;
  private int _score;

  public GameEntry() {
    _name = "AAA";
    _score = 0;
  }

  public GameEntry(String name, int score) {
    _name = name;
    _score = score;
  }

  public String getName() {
    return _name;
  }
  
  public int getScore() {
    return _score;
  }

  public static String randomInitials(int len) {
    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String ret = "";

    for (int i = 0; i < len; i++)
      ret += letters[(int) (Math.random() * letters.length)];

    return ret;
  }

  public static int randomScore(int max) {
    return (int) (Math.random() * max);
  }

  public static GameEntry randomEntry(int len, int max) {
    return new GameEntry(randomInitials(len), randomScore(max));
  }
  
  public int compareTo(GameEntry o) {
    int ans = getScore() - o.getScore();

    if (ans == 0)
      ans = getName().compareTo(o.getName());

    return ans;
  }

  public String toString() {
    return _name + "......." + _score;
  }

  public static void main(String[] args) {
    GameEntry a = new GameEntry("ABE", 0);
    GameEntry b = new GameEntry();
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("a vs b: " + a.compareTo(b));
    System.out.println("random: " + randomEntry(3, 1000));
  }
}
