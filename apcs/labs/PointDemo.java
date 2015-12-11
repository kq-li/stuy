public class PointDemo {
  public static double distance(Point x, Point y) {
    return Math.sqrt(Math.pow(x.getX() - y.getX(), 2) +
                     Math.pow(x.getY() - y.getY(), 2));
  }

  public static void main(String[] args) {
    Point a = new Point();
    Point b = new Point(-3, 4);
    System.out.println(distance(a, b));
  }
}
