import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public abstract class Entity {
  protected boolean _shouldExist;
  protected double _xcor, _ycor, _radius;
  protected Color _color, _outlineColor;
  protected Ellipse2D.Double _shape;
  protected Hex _cur;
  
  public Entity(double xcor, double ycor, double radius, Hex cur) {
    _shouldExist = true;
    _xcor = xcor;
    _ycor = ycor;
    _radius = radius;
    _shape = new Ellipse2D.Double(xcor - radius, ycor - radius, radius * 2, radius * 2);
    _cur = cur;
  }

  public boolean shouldExist() {
    return _shouldExist;
  }

  public Ellipse2D.Double getShape() {
    return _shape;
  }

  public Hex getCur() {
    return _cur;
  }
  
  public void moveTo(Hex hex) {
    _xcor = hex.getCenterX();
    _ycor = hex.getCenterY();
    _shape = new Ellipse2D.Double(_xcor - _radius, _ycor - _radius, _radius * 2, _radius * 2);
    _cur = hex;
  }

  public String toString() {
    return _cur.toString();
  }
}
