import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Player {
  private int _curHP, _maxHP, _curMoves, _maxMoves, _range;
  private double _xcor, _ycor, _radius;
  private Color _color;
  private Ellipse2D.Double _shape;
  private Hex _cur;
  
  public Player(double xcor, double ycor, double radius, Color color) {
    this(10, 10, 2, 2, 5, xcor, ycor, radius, color);
  }

  public Player(int curHP, int maxHP, int curMoves, int maxMoves, int range,
                double xcor, double ycor, double radius, Color color) {
    _curHP = curHP;
    _maxHP = maxHP;
    _curMoves = curMoves;
    _maxMoves = maxMoves;
    _range = range;
    _xcor = xcor;
    _ycor = ycor;
    _radius = radius;
    _color = color;
    _shape = new Ellipse2D.Double(xcor, ycor, radius * 2, radius * 2);
  }

  
}
