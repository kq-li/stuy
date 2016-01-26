import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class VerticalPlatform extends Platform {
  protected int _cooldown, _currentCooldown;
  protected double _maxDistance, _currentDistance, _orig_vy;
  
  public VerticalPlatform(double sx, double sy, double vx, double vy, double ax, double ay,
                          double width, double height, double delta) {
    super(sx, sy, vx, vy, ax, ay, width, height, Color.GRAY);

    _maxDistance = delta;
    _currentDistance = 0;
    _cooldown = 120;
    _currentCooldown = 0;

    _orig_vy = vy;
  }

  protected void update(double dt) {
    if (_currentCooldown > 0) {
      _currentCooldown--;
    } else {
      if (_currentDistance >= _maxDistance) {
        _orig_vy *= -1;
        setVY(0);
        _currentDistance = 0;
        _currentCooldown = _cooldown;
      } else {
        _vy = _orig_vy;
        _currentDistance += Math.abs(_vy * dt);
      }
    }

    setSY(_sy + _vy * dt);
    _hitbox.setRect(_sx, _sy, _width, _height);
  }
}
