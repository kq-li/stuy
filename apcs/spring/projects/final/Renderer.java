import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.*;
import javax.swing.*;

public class Renderer {
  protected Graphics2D _g2;
  protected Image _check, _move, _attack;

  public static final int IMAGE_SIZE = 64;

  public Renderer() {
    _g2 = null;

    try {
      _check = ImageIO.read(new File("check.png"));
      _move = ImageIO.read(new File("move.png"));
      _attack = ImageIO.read(new File("attack.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Updates graphics object with each paint
  public void setGraphics(Graphics2D g2) {
    _g2 = g2;
    _g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }

  public Graphics2D getGraphics() {
    return _g2;
  }

  // Renders user interface - menu, text, etc
  public void renderGUI(GUI gui, Button curButton, boolean hasActed,
                        int turn, int initialTurn) {
    Graphics g = _g2;
    String[] message = gui.getMessage().split("\n");
    _g2.setColor(Color.BLACK);
    
    for (int i = 0; i < message.length; i++)
      renderString(message[i], 10, 20 + 30 * i, false);
    
    for (Button b : gui.getButtons()) {
      if (hasActed && !b.getID().equals("check"))
        _g2.setColor(Button.DISABLED);
      else if (b.equals(curButton))
        _g2.setColor(Button.HOVER);
      else
        _g2.setColor(Button.DEFAULT);        

      _g2.fill(b.getShape());
      _g2.setColor(Button.OUTLINE);
      _g2.draw(b.getShape());
      g.drawImage(b.getImage(), (int) b.getX(), (int) b.getY(), null);
    }
  }

  // Wrapper for rendering hex grid
  public void renderGrid(ArrayList<Hex> hexes, Hex cur, boolean debug) {
    for (Hex hex : hexes)
      renderRegHex(hex, debug);

    if (cur != null) 
      renderCurHex(cur, debug);
  }

  // Wrapper for rendering the current hovered hex
  public void renderCurHex(Hex hex, boolean debug) {
    renderHex(hex, Hex.HOVER, Hex.OUTLINE_HOVER, debug);
  }

  // Wrapper for rendering a normal hex
  public void renderRegHex(Hex hex, boolean debug) {
    renderHex(hex, hex.getColor(), hex.getOutlineColor(), debug);
  }

  // Renders a hex
  public void renderHex(Hex hex, Color color, Color outlineColor, boolean debug) {
    _g2.setStroke(new BasicStroke(2));
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

    if (debug)
      renderString(hex.toString(), hex.getCenterX(), hex.getCenterY(), true);
  }

  // Renders a string
  public void renderString(String text, double xcor, double ycor, boolean centered) {
    FontMetrics fm = _g2.getFontMetrics();

    if (centered) 
      _g2.drawString(text,
                     (float) (xcor - fm.stringWidth(text) / 2.0),
                     (float) (ycor - fm.getHeight() / 2.0) + fm.getAscent());
    else 
      _g2.drawString(text,
                     (float) xcor,
                     (float) (ycor - fm.getHeight() / 2.0) + fm.getAscent());                     
  }

  // Wrapper for rendering a self-owned tracer
  public void renderSelfTracer(Tracer tracer) {
    renderTracer(tracer, Tracer.PLAYER);
  }

  // Wrapper for rendering an other-owned tracer
  public void renderOtherTracer(Tracer tracer) {
    renderTracer(tracer, Tracer.OTHER);
  }
  
  // Renders a tracer
  public void renderTracer(Tracer tracer, Color color) {
    _g2.setStroke(new BasicStroke(5));
    _g2.setColor(color);
    _g2.draw(tracer.getPath());
    _g2.fill(tracer.getShape());
  }

  // Wrapper for rendering the self player
  public void renderSelfPlayer(Player player) {
    renderPlayer(player, Player.PLAYER, Player.PLAYER_OUTLINE);
  }

  // Wrapper for rendering the other player
  public void renderOtherPlayer(Player player) {
    renderPlayer(player, Player.OTHER, Player.OTHER_OUTLINE);
  }

  // Renders a player
  public void renderPlayer(Player player, Color color, Color outlineColor) {
    _g2.setStroke(new BasicStroke(2));    
    _g2.setColor(color);
    _g2.fill(player.getShape());
    _g2.setColor(outlineColor);
    _g2.draw(player.getShape());
    renderString(Integer.toString(player.getHealth()), player.getX(), player.getY(), true);
  }

  // Wrapper for rendering a self projectile
  public void renderSelfProjectile(Projectile projectile) {
    renderProjectile(projectile, Player.PLAYER, Player.PLAYER_OUTLINE);
  }

  // Wrapper for rendering an other projectile
  public void renderOtherProjectile(Projectile projectile) {
    renderProjectile(projectile, Player.OTHER, Player.OTHER_OUTLINE);
  }

  // Renders a projectile
  public void renderProjectile(Projectile projectile, Color color, Color outlineColor) {
    _g2.setStroke(new BasicStroke(2));
    _g2.setColor(color);
    _g2.fill(projectile.getShape());
    _g2.setColor(outlineColor);
    _g2.draw(projectile.getShape());
  }
}
