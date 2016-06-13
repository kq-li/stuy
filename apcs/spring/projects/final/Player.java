import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Player extends Entity {
  protected int _curHealth, _maxHealth, _curMoves, _maxMoves, _range, _damage;
  protected Tracer _tracer;
  protected ArrayList<Projectile> _projectiles;
  protected Projectile _projectile;

  public static final Color PLAYER_COLOR = Color.GREEN;
  public static final Color PLAYER_OUTLINE_COLOR = Color.BLACK;
  public static final Color OTHER_COLOR = Color.RED;
  public static final Color OTHER_OUTLINE_COLOR = Color.BLACK;
  
  public Player(double xcor, double ycor, double radius, Hex cur) {
    this(10, 10, 1, 1, 5, 5, xcor, ycor, radius, cur);
  }

  public Player(int curHealth, int maxHealth, int curMoves, int maxMoves, int range, int damage,
                double xcor, double ycor, double radius, Hex cur) {
    super(xcor, ycor, radius, cur);
    _curHealth = curHealth;
    _maxHealth = maxHealth;
    _curMoves = curMoves;
    _maxMoves = maxMoves;
    _range = range;
    _damage = damage;
    _tracer = new Tracer(xcor, ycor, 0.5 * radius, cur);
    _projectiles = new ArrayList<Projectile>();
    _projectile = null;
  }

  public int getHealth() {
    return _curHealth;
  }

  public void setHealth(int health) {
    _curHealth = health;
  }

  public void resetHealth() {
    _curHealth = _maxHealth;
  }

  public int getDamage() {
    return _damage;
  }    

  public int getMoves() {
    return _curMoves;
  }

  public int getRange() {
    return _range;
  }

  public void move() {
    followTracer();
    _tracer.reset();
    resetMoves();
  }

  public void update() {
    if (_curHealth <= 0)
      _shouldExist = false;
  }

  public void resetMoves() {
    _curMoves = _maxMoves;
  }

  public void moveTo(Hex hex) {
    _cur.removePlayer();
    super.moveTo(hex);
    hex.setPlayer(this);
  }

  public void followTracer() {
    Hex hex = _tracer.advance();

    while (hex != null) {
      moveTo(hex);
      hex = _tracer.advance();
    }
  }

  public Tracer getTracer() {
    return _tracer;
  }

  public void resetTracer() {
    _tracer.reset();
  }
  
  public void moveTracer(Hex hex) {
    _curMoves--;
    _tracer.moveTo(hex);
  }

  public void setCurProjectile(Projectile projectile) {
    _projectile = projectile;
  }

  public void resetCurProjectile() {
    _projectile = null;
  }

  public Projectile getCurProjectile() {
    return _projectile;
  }
  
  public ArrayList<Projectile> getProjectiles() {
    return _projectiles;
  }

  public void addProjectile(Projectile projectile) {
    _projectiles.add(projectile);
  }

  public void removeProjectile(Projectile projectile) {
    _projectiles.remove(projectile);
  }
}
