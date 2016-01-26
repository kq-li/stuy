import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class HorizontalPlatform extends Platform {
  protected int _cooldown, _currentCooldown;
  protected double _maxDistance, _currentDistance, _orig_vx;
  
  public HorizontalPlatform(double sx, double sy, double vx, double vy, double ax, double ay,
                          double width, double height, double delta) {
    super(sx, sy, vx, vy, ax, ay, width, height, Color.GRAY);

    _maxDistance = delta;
    _currentDistance = 0;
    _cooldown = 120;
    _currentCooldown = 0;

    _orig_vx = vx;
  }

  protected void update(double dt) {
    if (_currentCooldown > 0) {
      _currentCooldown--;
    } else {
      if (_currentDistance >= _maxDistance) {
        _orig_vx *= -1;
        setVX(0);
        _currentDistance = 0;
        _currentCooldown = _cooldown;
      } else {
        _vx = _orig_vx;
        _currentDistance += Math.abs(_vx * dt);
      }
    }

    setSX(_sx + _vx * dt);
    _hitbox.setRect(_sx, _sy, _width, _height);
  }
}
