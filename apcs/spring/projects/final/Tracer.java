import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Tracer extends Entity {
  protected int _curMoves, _maxMoves;
  protected Queue<Hex> _path;
  protected Path2D.Double _shape;
  
  public Tracer(double xcor, double ycor, double radius, 
                Color color, Color outlineColor, Hex cur) {
    super(xcor, ycor, radius, color, outlineColor, cur);
    _maxMoves = 1;
    _curMoves = _maxMoves;
    _path = new LinkedList<Hex>();
    _shape = new Path2D.Double();
    _shape.moveTo(xcor, ycor);
  }

  public int getMoves() {
    return _curMoves;
  }

  public void reset() {
    _curMoves = _maxMoves;
  }

  public Hex advance() {
    return _path.poll();
  }

  public void moveTo(Hex hex) {
    _curMoves -= hex.distanceTo(_cur);
    super.moveTo(hex);
    _path.offer(_cur);
    _shape.lineTo(_cur.getCenterX(), _cur.getCenterY());
  }
}

  
