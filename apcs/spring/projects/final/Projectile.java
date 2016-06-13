import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Projectile extends Entity {
  protected int _moves;
  protected Tracer _tracer;
  protected Player _owner;
  
  public Projectile(double xcor, double ycor, double radius, Hex cur, Player owner) {
    super(xcor, ycor, radius, cur);
    _tracer = new Tracer(xcor, ycor, 0.5 * radius, cur);
    _owner = owner;
    _moves = owner.getRange();
  }

  public int getMoves() {
    return _moves;
  }
  
  public Player getOwner() {
    return _owner;
  }

  public void move() {
    followTracer();
  }

  public void update() {
    if (_cur.getPlayer() != null && _cur.getPlayer() != _owner) {
      _cur.getPlayer().setHealth(_cur.getPlayer().getHealth() - _cur.getPlayer().getDamage());
      _shouldExist = false;
    }
    
    if (_tracer.look() == null)
      _shouldExist = false;
  }

  public void moveTo(Hex hex) {
    _cur.removeProjectile(this);
    super.moveTo(hex);
    hex.addProjectile(this);
  }

  public void followTracer() {
    Hex hex = _tracer.advance();

    if (hex == null)
      _shouldExist = false;
    else
      moveTo(hex);
  }

  public Tracer getTracer() {
    return _tracer;
  }

  public void resetTracer() {
    _tracer.reset();
  }
  
  public void moveTracer(Hex hex) {
    _moves--;
    _tracer.moveTo(hex);
  }
}
