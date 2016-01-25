import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BouncyPlatform extends Platform {
  protected double _bounceStrength;

  public BouncyPlatform(double sx, double sy, double width, double height) {
    super(sx, sy, width, height, Color.PINK);
    _bounceStrength = 500;
  }
}
    
