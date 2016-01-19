public class Point {
  private double _x, _y;

  public Point() {
    _x = 0;
    _y = 0;
  }

  public Point(double x, double y) {
    _x = x;
    _y = y;
  }

  public Point(Point other) {
    _x = other.getX();
    _y = other.getY();
  }

  public double getX() {
    return _x;
  }

  public double getY() {
    return _y;
  }

  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }

  public void move(double x, double y) {
    _x = x;
    _y = y;
  }
  
  public Point getLocation() {
    return new Point(this);
  }

  public void translate(double dx, double dy) {
    _x += dx;
    _y += dy;
  }

  public void setLocation(Point other) {
    _x = other.getX();
    _y = other.getY();
  }

  public boolean equals(Object other) {
    return (this == other) || ((other instanceof Point) &&
                               (getX() == ((Point) other).getX()) &&
                               (getY() == ((Point) other).getY()));
  }
  
  public static void main(String[] args) {
    // insert test here
  }
}
