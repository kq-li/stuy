import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class EntityBuilder {
  protected double _sx, _sy, _vx, _vy, _ax, _ay, _width, _height;
  protected Color _color;

  public EntityBuilder() {
    _sx = 0;
    _sy = 0;
    _vx = 0;
    _vy = 0;
    _ax = 0;
    _ay = 0;
    _width = 0;
    _height = 0;

    _color = null;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable sx, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder sx(double sx) {
    _sx = sx;
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable sy, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder sy(double sy) {
    _sy = sy;
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable vx, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder vx(double vx) {
    _vx = vx;
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable vy, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder vy(double vy) {
    _vy = vy;
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable ax, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder ax(double ax) {
    _ax = ax;
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable ay, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder ay(double ay) {
    _ay = ay;
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable width, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder width(double width) {
    _width = Math.max(0, width);
    return this;
  }

  /**
   * precondition: none
   * postcondition: sets instance variable height, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder height(double height) {
    _height = Math.max(0, height);
    return this;
  }

  /**
   * precondition: color != null
   * postcondition: sets instance variable color, returns reference to builder
   * runtime: O(1)
   */
  public EntityBuilder color(Color color) {
    _color = color;
    return this;
  }

  /**
   * precondition: min_sx <= max_sx, min_sy <= max_sy
   * postcondition: returns Player with attributes initialized before
   * runtime: O(1)
   */
  public Player buildPlayer(double min_sx, double min_sy, double max_sx, double max_sy) {
    return new Player(_sx, _sy, min_sx, min_sy, max_sx, max_sy,
                      _vx, _vy, _ax, _ay,
                      _width, _height);
  }

  /**
   * precondition: none
   * postcondition: returns normal platforms
   * runtime: O(1)
   */  
  public Platform buildNormal() {
    return new Platform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height);
  }

  /**
   * precondition: none
   * postcondition: returns fire platforms
   * runtime: O(1)
   */
  public Platform buildFire() {
    return new FirePlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height);
  }

  /**
   * precondition: bounceStrength >= 0
   * postcondition: returns bouncy platform
   * runtime: O(1)
   */
  public Platform buildBouncy(double bounceStrength) {
    return new BouncyPlatform(_sx, _sy, _vx, _vy, _ax, _ay,  _width, _height, bounceStrength);
  }

  /**
   * precondition: delta > 0
   * postcondition: returns vertical platform
   * runtime: O(1)
   */
  public Platform buildVertical(double delta) {
    return new VerticalPlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height, delta);
  }

  /**
   * precondition: delta > 0
   * postcondition: returns horizontal platform
   * runtime: O(1)
   */
  public Platform buildHorizontal(double delta) {
    return new HorizontalPlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height, delta);
  }
  
  /**
   * precondition: none
   * postcondition: returns goal platform
   * runtime: O(1)
   */
  public Platform buildGoal() {
    return new GoalPlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height);
  }
}
