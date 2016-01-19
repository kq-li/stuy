public class Triangular {
  public static void main(String[] args) {
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    int c = Integer.parseInt(args[2]);
    System.out.println(isTriangular(a, b, c));
  }

  public static boolean isTriangular(double a, double b, double c) {
    return (a + b > c) && (a + c > b) && (b + c > a);
  }
}
