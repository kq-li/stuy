public class MyMath {
  public static int abs(int x) {
    if (x < 0)
      return -x;
    return x;
  }

  public static double abs(double x) {
    if (x < 0)
      return -x;
    return x;
  }

  public static double sqrt(double x) {
    if (x < 0)
      return Double.NaN;
    double guess = 1.0;
    while (abs(guess - x / guess) > 1e-15 * guess)
      guess = (guess + x / guess) / 2;
    return guess;
  }

  public static boolean isOdd(int n) {
    return n % 2 == 1;
  }

  public static boolean isEven(int n) {
    return !isOdd(n);
  }

  public static boolean isPrime(int n) {
    if (n == 2) return true;
    for (int i = 2; i <= n / i; i++)
      if (n % i == 0)
        return false;
    return true;
  }

  public static int log(int n) {
    int acc = 1;
    int i = 0;
    while (acc <= n) {
      acc *= 2;
      i++;
    }
    return i - 1;
  }

  public static int gcd(int a, int b) {
    if (b == 0)
      return a;
    return gcd(b, a % b);
  }

  public static void main(String[] args) {
    double[] a = new double[args.length];
    for (int i = 0; i < args.length; i++)
      a[i] = Double.parseDouble(args[i]);
    for (int i = 0; i < args.length; i++)
      System.out.println(log((int) a[i]));
  }
}
