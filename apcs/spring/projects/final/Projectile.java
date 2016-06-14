import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Projectile extends Entity {
  protected int _moves;
  protected Tracer _tracer;
  protected Player _owner;
  
  public Projectile(double xcor, double ycor, double radius, Hex cur, Player owner) {
    super(xcor, ycor, radius, cur);
    _tracer = new Tracer(xcor, ycor, 0.5 * radius, cur);
    _owner = owner;
    _owner.setCurProjectile(this);
    _cur.addProjectile(this);
    _moves = owner.getRange();
  }

  public int getMoves() {
    return _moves;
  }
  
  public Player getOwner() {
    return _owner;
  }

  // Catch projectile up to its tracer
  public void move() {
    followTracer();
  }

  // Check for player collision
  public void update() {
    if (_cur.getPlayer() != null && _cur.getPlayer() != _owner) {
      _cur.getPlayer().setHealth(_cur.getPlayer().getHealth() - _cur.getPlayer().getDamage());
      _shouldExist = false;
    }
  }

  // "Destroy" the projectile by freeing references to it
  public void destroy() {
    _tracer.reset();
    _owner.removeProjectile(this);
    _cur.removeProjectile(this);
  }    
  
  // Overriden moveTo method
  public void moveTo(Hex hex) {
    _cur.removeProjectile(this);
    super.moveTo(hex);
    hex.addProjectile(this);
  }

  // Catch projectile up to its tracer
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

  // Reset the tracer
  public void resetTracer() {
    _tracer.reset();
  }
  
  // Move tracer to a hex
  public void moveTracer(Hex hex) {
    _moves--;
    _tracer.moveTo(hex);
  }
}
