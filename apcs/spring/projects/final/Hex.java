import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Hex {
  protected int _x, _y, _z;
  protected double _centerX, _centerY, _radius;
  protected double[][] _vertices;
  protected Player _player;
  protected ArrayList<Projectile> _projectiles;
  protected Color _color, _defaultColor, _outlineColor, _outlineDefaultColor;
  protected Path2D.Double _shape;

  public static final Color DEFAULT = Color.BLACK;
  public static final Color HOVER = Color.WHITE;
  public static final Color OUTLINE_DEFAULT = Color.WHITE;
  public static final Color OUTLINE_HOVER = Color.GREEN;

  public Hex(int x, int y, int z, double centerX, double centerY, double radius) {
    _x = x;
    _y = y;
    _z = z;
    _centerX = centerX;
    _centerY = centerY;
    _radius = radius;
    _vertices = new double[6][2];

    for (int i = 0; i < 6; i++) {
      _vertices[i][0] = centerX + radius * Math.cos(i * Math.PI / 3);
      _vertices[i][1] = centerY + radius * Math.sin(i * Math.PI / 3);
    }                   

    _player = null;
    _projectiles = new ArrayList<Projectile>();
    _defaultColor = DEFAULT;
    _color = _defaultColor;
    _outlineDefaultColor = OUTLINE_DEFAULT;
    _outlineColor = _outlineDefaultColor;

    _shape = new Path2D.Double();
    _shape.moveTo(centerX + radius, centerY);

    for (int i = 0; i < 6; i++) 
      _shape.lineTo(_vertices[(i + 1) % 6][0], _vertices[(i + 1) % 6][1]);

    _shape.closePath();
  }

  public int getX() {
    return _x;
  }

  public int getY() {
    return _y;
  }

  public int getZ() {
    return _z;
  }

  public double getCenterX() {
    return _centerX;
  }

  public double getCenterY() {
    return _centerY;
  }

  public Player getPlayer() {
    return _player;
  }

  public void setPlayer(Player p) {
    _player = p;
  }

  public void removePlayer() {
    _player = null;
  }

  public void addProjectile(Projectile projectile) {
    _projectiles.add(projectile);
  }

  public void removeProjectile(Projectile projectile) {
    _projectiles.remove(projectile);
  }

  public ArrayList<Projectile> getProjectiles() {
    return _projectiles;
  }

  public Color getColor() {
    return _color;
  }

  public Color getDefaultColor() {
    return _defaultColor;
  }

  public Color getOutlineColor() {
    return _outlineColor;
  }

  public Color getOutlineDefaultColor() {
    return _outlineDefaultColor;
  }

  public void setColor(Color color) {
    _color = color;
  }

  public void setDefaultColor(Color color) {
    _defaultColor = color;
  }

  public void setOutlineColor(Color color) {
    _outlineColor = color;
  }

  public void setOutlineDefaultColor(Color color) {
    _outlineDefaultColor = color;
  }

  public void resetColor() {
    _color = _defaultColor;
  }

  public void resetOutlineColor() {
    _outlineColor = _outlineDefaultColor;
  }

  public void reset() {
    resetColor();
    resetOutlineColor();
  }

  public Path2D.Double getShape() {
    return _shape;
  }

  public boolean contains(double x, double y) {
    return getShape().contains(x, y);
  }

  public int distanceTo(Hex other) {
    return (Math.abs(other.getX() - getX()) +
            Math.abs(other.getY() - getY()) +
            Math.abs(other.getZ() - getZ())) / 2;
  }

  public String toString() {
    return _x + " " + _y + " " + _z;
  }
}
 
