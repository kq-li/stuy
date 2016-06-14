import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Button {
  protected double _xcor, _ycor, _size, _radius;
  protected String _id;
  protected Image _image;
  protected RoundRectangle2D.Double _shape;

  public static final Color DEFAULT = new Color(240, 240, 240);
  public static final Color HOVER = new Color(192, 192, 192);
  public static final Color DISABLED = new Color(128, 128, 128);
  public static final Color OUTLINE = Color.BLACK;

  public Button(double xcor, double ycor, double size, double radius, String id) {
    _xcor = xcor;
    _ycor = ycor;
    
    _size = size;
    _radius = radius;
    _shape = new RoundRectangle2D.Double(xcor, ycor, size, size, radius, radius);
    _id = id;

    try {
      _image = ImageIO.read(new File(id + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public double getX() {
    return _xcor;
  }

  public double getY() {
    return _ycor;
  }

  public double getSize() {
    return _size;
  }

  public double getRadius() {
    return _radius;
  }

  public RoundRectangle2D.Double getShape() {
    return _shape;
  }

  public Image getImage() {
    return _image;
  }

  public String getID() {
    return _id;
  }

  public boolean contains(double xcor, double ycor) {
    return _shape.contains(xcor, ycor);
  }

  public String toString() {
    return _id;
  }
}
  
