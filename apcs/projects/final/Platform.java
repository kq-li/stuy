import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Platform extends Tangible {
  protected Rectangle2D.Double _hitbox;
  protected Color _hitboxColor;

  public Platform(double sx, double sy, double width, double height) {
    this(sx, sy, width, height, Color.BLACK);
  }

  public Platform(double sx, double sy, double width, double height, Color color) {
    super(sx, sy, width, height);

    _hitbox = new Rectangle2D.Double(sx, sy, width, height);
    _hitboxColor = color;
  }

  protected void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(_hitboxColor);
    g2.fill(_hitbox);
  }
}
  
