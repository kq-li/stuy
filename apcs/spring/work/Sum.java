public class Sum {
  // Sum from 1 to n
  // Explicit: formula
  public static int sum(int n) {
    return n * (n + 1) / 2;
  }

  // Iterative: loop
  public static int sumI(int n) {
    int sum = 0;

    for (int i = 1; i <= n; i++)
      sum += i;

    return sum;
  }

  // Recursive
  public static int sumR(int n) {
    if (n == 0)
      return 0;
    return n + sumR(n - 1);
  }

  // Sum of an arithmetic progression a, a + d, ..., a + (n - 1) * d
  public static int sum(int a, int d, int n) {
    return n * (2 * a + (n - 1) * d) / 2;
  }

  public static int sumI(int a, int d, int n) {
    int sum = 0;

    for (int i = a; i <= a + (n - 1) * d; i += d)
      sum += i;

    return sum;
  }

  public static int sumR(int a, int d, int n) {
    if (n == 1)
      return a;
    return a + (n - 1) * d + sumR(a, d, n - 1);
  }
  
  public static void main(String[] args) {
    if (args.length == 1) {
      int n = Integer.parseInt(args[0]);
      System.out.println("explicit: " + sum(n));
      System.out.println("iterative: " + sumI(n));
      System.out.println("recursive: " + sumR(n));
    } else if (args.length == 3) {
      int a = Integer.parseInt(args[0]);
      int d = Integer.parseInt(args[1]);
      int n = Integer.parseInt(args[2]);
      System.out.println("explicit: " + sum(a, d, n));
      System.out.println("iterative: " + sumI(a, d, n));
      System.out.println("recursive: " + sumR(a, d, n));
    }
  }
}
  
