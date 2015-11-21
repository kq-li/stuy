public class Caesar {
  private static String alpha = "abcdefghijklmnopqrstuvwxyz";

  public static String enc(String plain) {
    return enc(plain, 3);
  }

  public static String enc(String plain, int offset) {
    String ret = "";
    for (int i = 0; i < plain.length(); i++) {
      int index = mod(alpha.indexOf(plain.substring(i, i + 1)), alpha.length());
      if (index >= 0) {
        ret += alpha.substring(mod(index + offset, alpha.length()),
                               mod(index + offset, alpha.length()) + 1);
      } else {
        ret += plain.substring(i, i + 1);
      }
    }
    return ret;
  }

  private static int mod(int a, int b) {
    int ret = a % b;
    while (ret < 0)
      ret += b;
    return ret;
  }
  
  public static void main(String[] args) {
    String input = args[0];
    System.out.println(enc(input));
    System.out.println(enc(enc(input), -5));
  }
}
