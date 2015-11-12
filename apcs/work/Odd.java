public class Odd {
  public static void main(String[] args) {
    boolean a = Boolean.parseBoolean(args[0]);
    boolean b = Boolean.parseBoolean(args[1]);
    boolean c = Boolean.parseBoolean(args[2]);
    System.out.println(odd(a, b, c));
  }

  public static boolean odd(boolean a, boolean b, boolean c) {
    return (a && b) && !c || (a && c) && !b || (b && c) && !a;
  }
}
