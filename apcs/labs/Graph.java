import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
  private ArrayList<Point> _points;
  private double maxX = 10, maxY = 10;

  public Graph() {
    this._points = new ArrayList<Point>(0);
  }

  public Graph(Point[] points) {
    this._points = new ArrayList<Point>(Arrays.asList(points));
  }

  public Graph(ArrayList<Point> points) {
    this._points = new ArrayList<Point>(points);
  }

  public String toString() {
    String out = "[";
    for (int i = 0; i < this._points.size(); i++) {
      out += this._points.get(i);
      if (i < this._points.size() - 1)
        out += ", ";
    }
    out += "]";
    return out;
  }

  public void addPoint(Point point) {
    this._points.add(point);
  }

  public void draw() {
    for (Point point : this._points) {
      StdDraw.setPenRadius(0.01);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.point(point.getX() / this.maxX, point.getY() / this.maxY);
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph();
    for (int i = 0; i < 10; i++)
      graph.addPoint(new Point(i, i));
    System.out.println(graph);
    graph.draw();
  }
}
