import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Tracer extends Entity {
  protected int _moves;
  protected Queue<Hex> _queue;
  protected Path2D.Double _path;
  
  public static final Color PLAYER_COLOR = new Color(0, 255, 0, 200);
  public static final Color OTHER_COLOR = new Color(255, 0, 0, 200);

  public Tracer(double xcor, double ycor, double radius, Hex cur) {
    super(xcor, ycor, radius, cur);
    _moves = 1;
    _queue = new LinkedList<Hex>();
    _path = new Path2D.Double();
    _path.moveTo(xcor, ycor);
  }

  public Path2D.Double getPath() {
    return _path;
  }

  public int getMoves() {
    return _moves;
  }

  public Hex advance() {
    return _queue.poll();
  }

  public Hex look() {
    return _queue.peek();
  }

  public void reset() {
    while (_queue.poll() != null);
    _path = new Path2D.Double();
    _path.moveTo(_xcor, _ycor);
  }
  
  public void moveTo(Hex hex) {
    super.moveTo(hex);
    _queue.offer(_cur);
    _path.lineTo(_cur.getCenterX(), _cur.getCenterY());
  }
}

  
