import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Projectile extends Entity {
  protected Tracer _tracer;
  protected Player _owner;

  public Projectile(double xcor, double ycor, double radius,
                    Color color, Color outlineColor, Hex cur,
                    Tracer tracer, Player owner) {
    super(xcor, ycor, radius, color, outlineColor, cur);
    _tracer = tracer;
    _owner = owner;
  }

  public void advance() {
    super.moveTo(_tracer.advance());
  }  

  public Player getOwner() {
    return _owner;
  }
}
