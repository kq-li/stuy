import java.lang.Exception;

public class QuadSolver {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("Usage: java QuadSolver <a> <b> <c>");
      System.exit(1);
    } else {
      try {
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);
        double discriminant = Math.sqrt(b * b - 4 * a * c);
        double root1 = (-b + discriminant) / (2 * a);
        double root2 = (-b - discriminant) / (2 * a);
        System.out.println("Roots: ");
        System.out.println(root1);
        System.out.println(root2);
      } catch (Exception e) {
        System.err.println("Please input numbers.");
        System.exit(1);
      }
    }
  }
}

