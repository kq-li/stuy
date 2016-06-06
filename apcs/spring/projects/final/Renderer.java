import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Renderer {
  private Graphics2D _g2;

  public Renderer() {
    _g2 = null;
  }

  public void setGraphics(Graphics2D g2) {
    _g2 = g2;
    _g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    _g2.setStroke(new BasicStroke(2));
  }

  public Graphics2D getGraphics() {
    return _g2;
  }

  public void renderHex(Hex hex) {
    _g2.setColor(hex.getColor());
    _g2.fill(hex.getShape());
    _g2.setColor(hex.getOutlineColor());
    _g2.draw(hex.getShape());

    if ((hex.getColor().getRed() +
         hex.getColor().getBlue() +
         hex.getColor().getGreen()) / 3 > 127)
      _g2.setColor(Color.BLACK);
    else
      _g2.setColor(Color.WHITE);
    
    if (hex.toString().equals("0 0 0"))
      renderString("x y z", hex.getCenterX(), hex.getCenterY());
    else
      renderString(hex.toString(), hex.getCenterX(), hex.getCenterY());
  }

  public void renderString(String text, double xcor, double ycor) {
    FontMetrics fm = _g2.getFontMetrics();
    _g2.drawString(text,
                   (float) (xcor - fm.stringWidth(text) / 2.0),
                   (float) (ycor - fm.getHeight() / 2.0) + fm.getAscent());
  }
}
