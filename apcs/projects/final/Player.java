import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Player extends Entity {
  protected int _maxJumpTime, _currentJumpTime, _maxJumps, _currentJumps;
  protected double _jumpStrength;
  protected boolean _isJumping;
  
  public Player(double sx, double sy, double min_sx, double min_sy, double max_sx, double max_sy,
                double width, double height, Color color) {
    super(sx, sy, min_sx, min_sy, max_sx, max_sy,
          0, 0, 0, 100,
          width, height, color);
    _maxJumpTime = 5;
    _currentJumpTime = 0;
    _maxJumps = 1;
    _currentJumps = _maxJumps;

    _jumpStrength = 100;

    _isJumping = false;
  }

  protected void startJump() {
    _currentJumpTime = _maxJumpTime;
    _currentJumps--;
    setIY(_iy - _jumpStrength);
  }

  protected void stopJump() {
    _currentJumpTime = 0;
    setIY(_iy + _jumpStrength);
  }

  protected void update(double dt) {
    if (_isJumping) {
      System.out.println("jumping");
      if (_currentJumps > 0) {
        startJump();
      } else {
        if (_currentJumpTime == 0) {
          stopJump();
        } else {
          _currentJumpTime--;
        }
      }
      if (_sy >= MAX_SY && _currentJumps == 0) {
        _isJumping = false;
        _currentJumps = _maxJumps;
      }
    } else {
      if (_sy >= MAX_SY && _currentJumpTime == 0) {
        stopJump();
      }
    }
    
    super.update(dt);
  }
}
