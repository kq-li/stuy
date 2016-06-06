import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Hex {
  private int _x, _y, _z;
  private int[] _cube, _axial;
  private double _centerX, _centerY, _radius;
  private double[] _pixel;
  private double[][] _vertices;
  private Color _color, _defaultColor, _outlineColor, _outlineDefaultColor;
  private Path2D.Double _shape;

  public static final Color DEFAULT = Color.BLACK;
  public static final Color HOVER = Color.WHITE;
  public static final Color OUTLINE_DEFAULT = Color.WHITE;
  public static final Color OUTLINE_HOVER = Color.GREEN;

  public Hex(int x, int y, int z, double centerX, double centerY, double radius) {
    _x = x;
    _y = y;
    _z = z;
    _centerX = centerX;
    _centerY = centerY;
    _radius = radius;
    _vertices = new double[6][2];

    for (int i = 0; i < 6; i++) {
      _vertices[i][0] = centerX + radius * Math.cos(i * Math.PI / 3);
      _vertices[i][1] = centerY + radius * Math.sin(i * Math.PI / 3);
    }                   

    _defaultColor = DEFAULT;
    _color = _defaultColor;
    _outlineDefaultColor = OUTLINE_DEFAULT;
    _outlineColor = _outlineDefaultColor;

    _shape = new Path2D.Double();
    _shape.moveTo(centerX + radius, centerY);

    for (int i = 0; i < 6; i++) 
      _shape.lineTo(_vertices[(i + 1) % 6][0], _vertices[(i + 1) % 6][1]);

    _shape.closePath();
  }

  public int getX() {
    return _x;
  }

  public int getY() {
    return _y;
  }

  public int getZ() {
    return _z;
  }

  public double getCenterX() {
    return _centerX;
  }

  public double getCenterY() {
    return _centerY;
  }

  public Color getColor() {
    return _color;
  }

  public Color getDefaultColor() {
    return _defaultColor;
  }

  public Color getOutlineColor() {
    return _outlineColor;
  }

  public Color getOutlineDefaultColor() {
    return _outlineDefaultColor;
  }

  public void setColor(Color color) {
    _color = color;
  }

  public void setDefaultColor(Color color) {
    _defaultColor = color;
  }

  public void setOutlineColor(Color color) {
    _outlineColor = color;
  }

  public void setOutlineDefaultColor(Color color) {
    _outlineDefaultColor = color;
  }

  public Path2D.Double getShape() {
    return _shape;
  }

  public boolean contains(double x, double y) {
    boolean contains = true;

    for (int i = 0; i < 6; i++) {
      double x1 = _vertices[i % 6][0];
      double y1 = _vertices[i % 6][1];
      double x2 = _vertices[(i + 1) % 6][0];
      double y2 = _vertices[(i + 1) % 6][1];
      double slope = (y2 - y1) / (x2 - x1);
      double edgeY = slope * (x - x1) + y1;

      if (i < 3) 
        contains = contains && (edgeY - y > 0);
      else
        contains = contains && (edgeY - y < 0);

      if (!contains)
        return false;
    }

    return true;
  }

  public int distanceTo(Hex other) {
    return (Math.abs(other.getX() - getX()) +
            Math.abs(other.getY() - getY()) +
            Math.abs(other.getZ() - getZ())) / 2;
  }

  public String toString() {
    return _x + " " + _y + " " + _z;
  }
}
 
