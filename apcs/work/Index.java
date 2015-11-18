public class Index {
  public static int index1(String a, String b) {
    return a.indexOf(b);
  }

  public static int index2(String a, String b) {
    for (int i = 0; i < a.length() - b.length(); i++) {
      String sub = a.substring(i, i + b.length());
      if (sub.equals(b))
        return i;
    }
    return -1;
  }

  public static int index3(String a, String b) {
    System.out.println(a + " " + b);
    if (a.substring(0, b.length()).equals(b))
      return 0;
    if (a.length() == b.length())
      return -1;
    int index = index3(a.substring(1), b);
    System.out.println(index);
    if (index < 0)
      return 0;
    else
      return 1 + index;
  }

  public static void main(String[] args) {
    String a = args[0];
    String b = args[1];
    System.out.println(index1(a, b) + " " + index2(a, b) + " " + index3(a, b));
  }
}
