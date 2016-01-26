import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public abstract class Entity extends Tangible {
  protected double _sx, _sy, _vx, _vy, _ax, _ay, _width, _height;
  
  public Entity(double sx, double sy,
                double vx, double vy,
                double ax, double ay,
                double width, double height) {
    super(sx, sy, width, height);
    
    _sx = sx;
    _sy = sy;
    _vx = vx;
    _vy = vy;
    _ax = ax;
    _ay = ay;

    _width = width;
    _height = height;
  }
  
  protected void update(double dt) {
    setVX(_vx + _ax * dt);
    setVY(_vy + _ay * dt);
    setSX(_sx + _vx * dt);
    setSY(_sy + _vy * dt);
  }

  protected void setSX(double sx) {
    _sx = sx;
  }

  protected void setSY(double sy) {
    _sy = sy;
  }

  protected void setVX(double vx) {
    _vx = vx;
  }

  protected void setVY(double vy) {
    _vy = vy;
  }

  protected abstract void render(Graphics g);
}
