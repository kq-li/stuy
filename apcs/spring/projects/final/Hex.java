import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;

public class Hex {
  protected double _x, _y, _z, _xcor, _ycor, _radius;
  protected double[][] _vertices;
  protected Path2D.Double _path;

  public Hex(double x, double y, double z, double xcor, double ycor, double radius) {
    _x = x;
    _y = y;
    _z = z;
    _xcor = xcor;
    _ycor = ycor;
    _radius = radius;
    _vertices = new double[6][2];

    for (int i = 0; i < 6; i++) {
      _vertices[i][0] = xcor + radius * Math.cos(i * Math.PI / 3);
      _vertices[i][1] = ycor + radius * Math.sin(i * Math.PI / 3);
    }                   

    _path = new Path2D.Double();
    _path.moveTo(xcor + radius, ycor);

    for (int i = 0; i < 6; i++) 
      _path.lineTo(_vertices[(i + 1) % 6][0], _vertices[(i + 1) % 6][1]);

    _path.closePath();
  }

  public Path2D.Double getPath() {
    return _path;
  }

  public boolean isInside(double x, double y) {
    boolean isInside = true;

    for (int i = 0; i < 6; i++) {
      double x1 = _vertices[i % 6][0];
      double y1 = _vertices[i % 6][1];
      double x2 = _vertices[(i + 1) % 6][0];
      double y2 = _vertices[(i + 1) % 6][1];
      double slope = (y2 - y1) / (x2 - x1);
      double edgeY = slope * (x - x1) + y1;

      if (i < 3) 
        isInside = isInside && (edgeY - y > 0);
      else
        isInside = isInside && (edgeY - y < 0);

      if (!isInside)
        return false;
    }

    return true;
  }
}
 
