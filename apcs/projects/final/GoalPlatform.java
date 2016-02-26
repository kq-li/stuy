import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GoalPlatform extends Platform {
  public GoalPlatform(double sx, double sy, double vx, double vy, double ax, double ay,
                      double width, double height) {
    super(sx, sy, vx, vy, ax, ay, width, height, Color.GREEN);
  }
}
