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

  public EntityBuilder sx(double sx) {
    _sx = sx;
    return this;
  }

  public EntityBuilder sy(double sy) {
    _sy = sy;
    return this;
  }

  public EntityBuilder vx(double vx) {
    _vx = vx;
    return this;
  }

  public EntityBuilder vy(double vy) {
    _vy = vy;
    return this;
  }

  public EntityBuilder ax(double ax) {
    _ax = ax;
    return this;
  }

  public EntityBuilder ay(double ay) {
    _ay = ay;
    return this;
  }

  public EntityBuilder width(double width) {
    _width = Math.max(0, width);
    return this;
  }

  public EntityBuilder height(double height) {
    _height = Math.max(0, height);
    return this;
  }

  public EntityBuilder color(Color color) {
    _color = color;
    return this;
  }

  public Player buildPlayer(double min_sx, double min_sy, double max_sx, double max_sy) {
    return new Player(_sx, _sy, min_sx, min_sy, max_sx, max_sy,
                      _vx, _vy, _ax, _ay,
                      _width, _height);
  }
  
  public Platform buildNormal() {
    return new Platform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height);
  }

  public Platform buildFire() {
    return new FirePlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height);
  }

  public Platform buildBouncy(double bounceStrength) {
    return new BouncyPlatform(_sx, _sy, _vx, _vy, _ax, _ay,  _width, _height, bounceStrength);
  }

  public Platform buildVertical(double delta) {
    return new VerticalPlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height, delta);
  }

  public Platform buildHorizontal(double delta) {
    return new HorizontalPlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height, delta);
  }
  
  public Platform buildGoal() {
    return new GoalPlatform(_sx, _sy, _vx, _vy, _ax, _ay, _width, _height);
  }
}
