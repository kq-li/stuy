import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public abstract class Entity {
  private double _xcor, _ycor, _radius;
  private Color _color, _outlineColor;
  private Ellipse2D.Double _shape;
  private Hex _cur;
  
  public Entity(double xcor, double ycor, double radius, 
                Color color, Color outlineColor, Hex cur) {
    _xcor = xcor;
    _ycor = ycor;
    _radius = radius;
    _color = color;
    _outlineColor = outlineColor;
    _shape = new Ellipse2D.Double(xcor - radius, ycor - radius, radius * 2, radius * 2);
    _cur = cur;
  }

  public Color getColor() {
    return _color;
  }

  public Color getOutlineColor() {
    return _outlineColor;
  }

  public Ellipse2D.Double getShape() {
    return _shape;
  }

  public Hex getCur() {
    return _cur;
  }
  
  public String toString() {
    return _cur.toString();
  }
}
