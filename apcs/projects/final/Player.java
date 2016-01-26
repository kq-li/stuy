import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Player extends Entity {
  protected double _ix, _iy;
  
  protected int _upInputControl, _downInputControl, _leftInputControl, _rightInputControl;
  protected int _maxJumps, _currentJumps;
  protected double _jumpStrength, _horizontalSpeed;
  protected boolean _isPressingUp, _isPressingDown, _isPressingLeft, _isPressingRight;
  
  protected Rectangle2D.Double _hitbox;
  protected Color _hitboxColor;
  protected Platform _currentPlatform;
  
  protected final int MIN_INPUT_TIME;
  protected final double MIN_SX, MIN_SY, MAX_SX, MAX_SY, ORIG_AX, ORIG_AY;

  public Player(double sx, double sy,
                double min_sx, double min_sy,
                double max_sx, double max_sy,
                double vx, double vy,
                double ax, double ay,
                double width, double height) {
    super(sx, sy, vx, vy, ax, ay, width, height);
    
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

    MIN_SX = min_sx;
    MIN_SY = min_sy;
    MAX_SX = max_sx;
    MAX_SY = max_sy;
    ORIG_AX = ax;
    ORIG_AY = ay;

    _jumpStrength = 125;
    _horizontalSpeed = 100;

    _hitbox = new Rectangle2D.Double(sx, sy, width, height);
    _hitboxColor = Color.BLUE;
    _currentPlatform = null;
  }

  /**
   * precondition: none
   * postcondition: sets ix
   * runtime: O(1)
   */
  protected void setIX(double ix) {
    _ix = ix;
  }

  /**
   * precondition: none
   * postcondition: sets iy
   * runtime: O(1)
   */
  protected void setIY(double iy) {
    _iy = iy;
  }

  /**
   * precondition: none
   * postcondition: sets SX
   * runtime: O(1)
   */
  protected void setSX(double sx) {
    if (_sx >= MAX_SX)
      _sx = Math.min(sx, MAX_SX);
    else if (_sx <= MIN_SX)
      _sx = Math.max(sx, MIN_SX);
    else
      _sx = sx;
  }

  /**
   * precondition: none
   * postcondition: sets SY
   * runtime: O(1)
   */
  protected void setSY(double sy) {
    if (_sy >= MAX_SY)
      _sy = Math.min(sy, MAX_SY);
    else if (_sy <= MIN_SY)
      _sy = Math.max(sy, MIN_SY);
    else
      _sy = sy;
  }

  /**
   * precondition: none
   * postcondition: sets vx
   * runtime: O(1)
   */
  protected void setVX(double vx) {
    if (_sx >= MAX_SX)
      _vx = Math.min(0, vx);
    else if (_sx <= MIN_SX)
      _vx = Math.max(0, vx);
    else if (_currentPlatform != null)
      _vx = _currentPlatform._vx;
    else
      _vx = vx;
  }

  /**
   * precondition: none
   * postcondition: sets vy
   * runtime: O(1)
   */
  protected void setVY(double vy) {
    if (_sy >= MAX_SY)
      _vy = Math.min(0, vy);
    else if (_sy <= MIN_SY)
      _vy = Math.max(0, vy);
    else if (_currentPlatform != null && !(_currentPlatform instanceof BouncyPlatform))
      _vy = _currentPlatform._vy;
    else
      _vy = vy;
  }

  /**
   * precondition: none
   * postcondition: sets ax
   * runtime: O(1)
   */
  protected void setAX(double ax) {
    _ax = ax;
  }

  /**
   * precondition: none
   * postcondition: sets ay
   * runtime: O(1)
   */
  protected void setAY(double ay) {
    _ay = ay;
  }
  
  /**
   * precondition: none
   * postcondition: jump setup
   * runtime: O(1)
   */
  protected void setUp(boolean isPressingUp) {
    if ((!_isPressingUp && isPressingUp) && _upInputControl == 0 && _currentJumps > 0)
      startUp();
    
    _isPressingUp = isPressingUp;
  }
  
  /**
   * precondition: none
   * postcondition: jump event fired
   * runtime: O(1)
   */
  protected void startUp() {
    _upInputControl = MIN_INPUT_TIME;
    _currentJumps--;
    if (_iy == 0)
      setIY(_iy - _jumpStrength);
    setVY(0);
  }

  /**
   * precondition: none
   * postcondition: jump event stopped
   * runtime: O(1)
   */
  protected void stopUp() {
    setIY(0);
  }
  
  /**
   * precondition: none
   * postcondition: down setup
   * runtime: O(1)
   */
  protected void setDown(boolean isPressingDown) {
    if (_downInputControl == 0)
      startDown();

    _isPressingDown = isPressingDown;
  }

  /**
   * precondition: none
   * postcondition down event fired:
   * runtime: O(1)
   */
  protected void startDown() {
    setIY(0);
  }
    
  /**
   * precondition: none
   * postcondition: left setup
   * runtime: O(1)
   */
  protected void setLeft(boolean isPressingLeft) {
    if (_leftInputControl == 0)
      startLeft();

    _isPressingLeft = isPressingLeft;
  }
  
  /**
   * precondition: none
   * postcondition: left event triggered
   * runtime: O(1)
   */
  protected void startLeft() {
    _leftInputControl = MIN_INPUT_TIME;
    setIX(-_horizontalSpeed);
  }
   
  /**
   * precondition: none
   * postcondition: right setup
   * runtime: O(1)
   */ 
  protected void setRight(boolean isPressingRight) {
    if (_rightInputControl == 0)
      startRight();

    _isPressingRight = isPressingRight;
  }

  /**
   * precondition: none
   * postcondition: right start
   * runtime: O(1)
   */
  protected void startRight() {
    _rightInputControl = MIN_INPUT_TIME;
    setIX(_horizontalSpeed);
  }

  /**
   * precondition: none
   * postcondition: stop horizontal events
   * runtime: O(1)
   */
  protected void stopHorizontal() {
    setIX(0);
  }

  /**
   * precondition: t != null
   * postcondition: returns whether a rectangle overlaps with the player
   * runtime: O(1)
   */
  protected boolean touching(Tangible t) {
    return (_sx <= t._sx + t._width && _sx + _width >= t._sx &&
            _sy <= t._sy + t._height & _sy + _height >= t._sy);
  }

  /**
   * precondition: p != null
   * postcondition: returns whether the player is above a platform
   * runtime: O(1)
   */
  protected boolean overPlatform(Platform p) {
    return (p != null &&
            _sx + _width >= p._sx && _sx <= p._sx + p._width);
  }
  
  /**
   * precondition: p != null
   * postcondition: returns whether the player is on a platform
   * runtime: O(1)
   */
  protected boolean onPlatform(Platform p) {
    return (p != null && _vy + _iy - p._vy >= 0 && overPlatform(p) &&
            _sy + _height >= p._sy && _sy + _height <= p._sy + p._height);
  }

  /**
   * precondition: none
   * postcondition: resets jump count
   * runtime: O(1)
   */
  protected void resetJumps() {
    _currentJumps = _maxJumps;
  }

  /**
   * precondition: none
   * postcondition: resets vertical mvoement
   * runtime: O(1)
   */
  protected void ground() {
    resetJumps();
    _isPressingUp = false;
    stopUp();
  }

  /**
   * precondition: dt > 0
   * postcondition: updates player
   * runtime: O(1)
   */
  protected void update(double dt) {
    if (_currentJumps == _maxJumps && (_sy < MAX_SY && _currentPlatform == null))
      _currentJumps--;
    
    if (_upInputControl > 0)
      _upInputControl--;

    if (_upInputControl == 0 && (_sy >= MAX_SY || _currentPlatform != null)) {
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

    if (_currentPlatform != null) {
      setAY(0);
    } else {
      setAY(ORIG_AY);
      setVX(0);
    }
    
    setVX(_vx + _ax * dt);
    setVY(_vy + _ay * dt);  
    setSX(_sx + (_vx + _ix) * dt);
    setSY(_sy + (_vy + _iy) * dt);
    _hitbox.setRect(_sx, _sy, _width, _height);
  }

  /**
   * precondition: g is instance of Graphics2D
   * postcondition: renders images
   * runtime: O(1)
   */
  protected void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(_hitboxColor);
    g2.fill(_hitbox);
  }
}
