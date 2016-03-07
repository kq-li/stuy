import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BouncyPlatform extends Platform {
  protected double _bounceStrength;

  public BouncyPlatform(double sx, double sy, double vx, double vy, double ax, double ay,
                        double width, double height, double bounceStrength) {
    super(sx, sy, vx, vy, ax, ay, width, height, Color.PINK);
    _bounceStrength = bounceStrength;
  }
}
    
