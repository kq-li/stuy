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

  /**
   * precondition: dt > 0
   * postcondition: entity updated
   * runtime: O(1)
   */
  protected void update(double dt) {
    setVX(_vx + _ax * dt);
    setVY(_vy + _ay * dt);
    setSX(_sx + _vx * dt);
    setSY(_sy + _vy * dt);
  }

  /**
   * precondition: none
   * postcondition: sx updated
   * runtime: O(1)
   */
  protected void setSX(double sx) {
    _sx = sx;
  }

  /**
   * precondition: none
   * postcondition: sy updated
   * runtime: O(1)
   */
  protected void setSY(double sy) {
    _sy = sy;
  }

  /**
   * precondition: none
   * postcondition: vx updated
   * runtime: O(1)
   */
  protected void setVX(double vx) {
    _vx = vx;
  }

  /**
   * precondition: none
   * postcondition: vy updated
   * runtime: O(1)
   */
  protected void setVY(double vy) {
    _vy = vy;
  }

  protected abstract void render(Graphics g);
}
