import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public abstract class Entity extends Tangible {
  protected double _sx, _sy, _vx, _vy, _ax, _ay, _width, _height;
  
  protected final double MIN_SX, MIN_SY, MAX_SX, MAX_SY;

  public Entity(double sx, double sy,
                double min_sx, double min_sy,
                double max_sx, double max_sy,
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

    MIN_SX = min_sx;
    MAX_SX = max_sx;
    MIN_SY = min_sy;
    MAX_SY = max_sy;

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
    if (_sx >= MAX_SX)
      _sx = Math.min(sx, MAX_SX);
    else if (_sx <= MIN_SX)
      _sx = Math.max(sx, MIN_SX);
    else
      _sx = sx;
  }

  protected void setSY(double sy) {
    if (_sy >= MAX_SY)
      _sy = Math.min(sy, MAX_SY);
    else if (_sy <= MIN_SY)
      _sy = Math.max(sy, MIN_SY);
    else
      _sy = sy;
  }

  protected void setVX(double vx) {
    if (_sx >= MAX_SX)
      _vx = Math.min(0, vx);
    else if (_sx <= MIN_SX)
      _vx = Math.max(0, vx);
    else
      _vx = vx;
  }

  protected void setVY(double vy) {
    if (_sy >= MAX_SY)
      _vy = Math.min(0, vy);
    else if (_sy <= MIN_SY)
      _vy = Math.max(0, vy);
    else
      _vy = vy;
  }

  protected abstract void render(Graphics g);
}
