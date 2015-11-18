public class Caesar {
  private static String alpha = "abcdefghijklmnopqrstuvwxyz";

  public static String enc(String plain) {
    return enc(plain, 3);
  }

  public static String enc(String plain, int offset) {
    String ret = "";
    for (int i = 0; i < plain.length(); i++) {
      int index = alpha.indexOf(plain.substring(i, i + 1)) % alpha.length();
      if (index > 0) {
        ret += alpha.substring((index + offset) % alpha.length(),
                               (index + offset) % alpha.length() + 1);
      } else {
        ret += plain.substring(i, i + 1);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    String input = args[0];
    System.out.println(enc(input));
    System.out.println(enc(enc(input), -3));
  }
}
