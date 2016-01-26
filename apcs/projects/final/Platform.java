import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Platform extends Entity {
  protected Rectangle2D.Double _hitbox;
  protected Color _hitboxColor;

  public Platform(double sx, double sy, double vx, double vy, double ax, double ay,
                  double width, double height) {
    this(sx, sy, vx, vy, ax, ay, width, height, Color.BLACK);
  }
  
  public Platform(double sx, double sy, double vx, double vy, double ax, double ay,
                  double width, double height, Color color) {
    super(sx, sy, vx, vy, ax, ay, width, height);

    _hitbox = new Rectangle2D.Double(sx, sy, width, height);
    _hitboxColor = color;
  }

  protected void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(_hitboxColor);
    g2.fill(_hitbox);
  }
}
  
