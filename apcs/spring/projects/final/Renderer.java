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

  public void renderCurHex(Hex hex) {
    renderHex(hex, Hex.HOVER, Hex.OUTLINE_HOVER);
  }

  public void renderRegHex(Hex hex) {
    renderHex(hex, hex.getColor(), hex.getOutlineColor());
  }

  public void renderHex(Hex hex, Color color, Color outlineColor) {
    _g2.setColor(color);
    _g2.fill(hex.getShape());
    _g2.setColor(outlineColor);
    _g2.draw(hex.getShape());

    if ((color.getRed() +
         color.getBlue() +
         color.getGreen()) / 3 > 127)
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

  public void renderPlayer(Player player) {
    _g2.setColor(player.getColor());
    _g2.fill(player.getShape());
    _g2.setColor(player.getOutlineColor());
    _g2.draw(player.getShape());
  }
}
