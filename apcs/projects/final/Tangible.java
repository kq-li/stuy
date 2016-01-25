import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public abstract class Tangible {
  protected double _sx, _sy, _width, _height;
  
  public Tangible(double sx, double sy, double width, double height) {
    _sx = sx;
    _sy = sy;
    _width = width;
    _height = height;
  }

  protected abstract void render(Graphics g);
}
