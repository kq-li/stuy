import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.*;
import javax.swing.*;

public class Tracer extends Entity {
  protected int _moves;
  protected Hex _start;
  protected Queue<Hex> _queue;
  protected Path2D.Double _path;
  
  public static final Color PLAYER = new Color(0, 255, 0, 200);
  public static final Color OTHER = new Color(255, 0, 0, 200);

  public Tracer(double xcor, double ycor, double radius, Hex cur) {
    super(xcor, ycor, radius, cur);
    _moves = 1;
    _queue = new LinkedList<Hex>();
    _path = new Path2D.Double();
    _path.moveTo(xcor, ycor);
    _start = cur;
  }

  public Path2D.Double getPath() {
    return _path;
  }

  public int getMoves() {
    return _moves;
  }

  // Advances tracer along its path by polling its action queue
  public Hex advance() {
    Hex ret = _queue.poll();

    if (ret != null)
      _start = ret;
    
    return ret;
  }

  // Wrapper for peeking into the action queue
  public Hex look() {
    return _queue.peek();
  }

  // Resets the tracer's properties, used to cancel
  public void reset() {
    moveTo(_start);
    while (_queue.poll() != null);
    _path = new Path2D.Double();
    _path.moveTo(_xcor, _ycor);
  }
  
  // Overriden moveTo method
  public void moveTo(Hex hex) {
    super.moveTo(hex);
    _queue.offer(_cur);
    _path.lineTo(_cur.getCenterX(), _cur.getCenterY());
  }
}

  
