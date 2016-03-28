public class WithCommas {
  public static String withCommas(int n) {
    String last3 = n + "";
    if (n < 1000)
      return last3;
    last3 = last3.substring(last3.length() - 3);
    return withCommas(n / 1000) + "," + last3;
  }

  public static void main(String[] args) {
    int[] test = {100, 1000, 1925000};
    for (int i : test)
      System.out.println(i + " -> " + withCommas(i));
  }
}
