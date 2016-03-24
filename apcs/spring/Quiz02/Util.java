public class Util {
  public static String duplicate(String s, int n) {
    String ret = "";

    for (int i = 0; i < n; i++)
      ret += s;

    return ret;
  }

  public static String randomString(int len) {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String ret = "";

    for (int i = 0; i < len; i++) {
      int r = (int) (Math.random() * alphabet.length());
      ret += alphabet.substring(r, r + 1);
    }

    return ret;
  }

  public static int randomScore(int max) {
    return (int) (Math.random() * max);
  }
}
    
