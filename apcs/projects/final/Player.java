import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Player extends Entity {
  protected double _ix, _iy;
  
  protected int _upInputControl, _downInputControl, _leftInputControl, _rightInputControl;
  protected int _maxJumps, _currentJumps;
  protected double _jumpStrength, _horizontalSpeed;
  protected boolean _isPressingUp, _isPressingDown, _isPressingLeft, _isPressingRight, _onPlatform;
  
  protected Rectangle2D.Double _hitbox;
  protected Color _hitboxColor;
  
  protected final int MIN_INPUT_TIME;
  
  public Player(double sx, double sy,
                double min_sx, double min_sy,
                double max_sx, double max_sy,
                double vx, double vy,
                double ax, double ay,
                double width, double height,
                Color color) {
    super(sx, sy, min_sx, min_sy, max_sx, max_sy, vx, vy, ax, ay, width, height);
    
    _maxJumps = 2;
    _currentJumps = _maxJumps;

    _upInputControl = 0;
    _downInputControl = 0;
    _leftInputControl = 0;
    _rightInputControl = 0;

    _isPressingUp = false;
    _isPressingDown = false;
    _isPressingLeft = false;
    _isPressingRight = false;

    MIN_INPUT_TIME = 5;

    _jumpStrength = 150;
    _horizontalSpeed = 100;

    _hitbox = new Rectangle2D.Double(sx, sy, width, height);
    _hitboxColor = color;
  }

  protected void setIX(double ix) {
    _ix = ix;
  }

  protected void setIY(double iy) {
    _iy = iy;
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
    else if (_onPlatform)
      _sy = Math.min(sy, _sy);
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
    if (_sy >= MAX_SY || _onPlatform)
      _vy = Math.min(0, vy);
    else if (_sy <= MIN_SY)
      _vy = Math.max(0, vy);
    else 
      _vy = vy;
  }
  
  protected void setUp(boolean isPressingUp) {
    if ((!_isPressingUp && isPressingUp) && _upInputControl == 0 && _currentJumps > 0)
      startUp();
    
    _isPressingUp = isPressingUp;
  }
  
  protected void startUp() {
    _upInputControl = MIN_INPUT_TIME;
    _currentJumps--;
    if (_iy == 0 || (_sy >= MAX_SY || _onPlatform))
      setIY(_iy - _jumpStrength);
    else
      setVY(0);
  }

  protected void stopUp() {
    setIY(0);
  }
  
  protected void setDown(boolean isPressingDown) {
    if (_downInputControl == 0)
      startDown();

    _isPressingDown = isPressingDown;
  }

  protected void startDown() {
    setIY(0);
  }
    
  protected void setLeft(boolean isPressingLeft) {
    if (_leftInputControl == 0)
      startLeft();

    _isPressingLeft = isPressingLeft;
  }
  
  protected void startLeft() {
    _leftInputControl = MIN_INPUT_TIME;
    setIX(-_horizontalSpeed);
  }
    
  protected void setRight(boolean isPressingRight) {
    if (_rightInputControl == 0)
      startRight();

    _isPressingRight = isPressingRight;
  }

  protected void startRight() {
    _rightInputControl = MIN_INPUT_TIME;
    setIX(_horizontalSpeed);
  }

  protected void stopHorizontal() {
    setIX(0);
  }

  protected boolean onPlatform(Platform p) {
    return (p != null &&
            (_sx + _width >= p._sx && _sx <= p._sx + p._width &&
             p._sy - (_sy + _height) <= Game.EPSILON && p._sy - (_sy + _height) >= 0));
  }

  protected void resetJumps() {
    _currentJumps = _maxJumps;
  }

  protected void ground() {
    resetJumps();
    _isPressingUp = false;
    stopUp();
  }
  
  protected void update(double dt) {
    if (_currentJumps == _maxJumps && (_sy < MAX_SY && !_onPlatform))
      _currentJumps--;
    
    if (_upInputControl > 0)
      _upInputControl--;

    if (_upInputControl == 0 && (_sy >= MAX_SY || _onPlatform)) {
      ground();
    }

    if (_isPressingLeft == _isPressingRight)
      stopHorizontal();
    else if (_isPressingLeft)
      startLeft();
    else if (_isPressingRight)
      startRight();
      
    if (_leftInputControl > 0)
      _leftInputControl--;

    if (_rightInputControl > 0)
      _rightInputControl--;
    
    setVX(_vx + _ax * dt);
    setVY(_vy + _ay * dt);
    setSX(_sx + (_vx + _ix) * dt);
    setSY(_sy + (_vy + _iy) * dt);
    _hitbox.setRect(_sx, _sy, _width, _height);
  }

  protected void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(_hitboxColor);
    g2.fill(_hitbox);
  }
}
