import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Player extends Entity {
  private int _curHP, _maxHP, _curMoves, _maxMoves, _range;
  private Hex _cur;
  private Queue<Hex> _actions;
  
  public Player(double xcor, double ycor, double radius, 
                Color color, Color outlineColor, Hex cur) {
    this(10, 10, 3, 3, 5, xcor, ycor, radius, color, outlineColor, cur);
  }

  public Player(int curHP, int maxHP, int curMoves, int maxMoves, int range,
                double xcor, double ycor, double radius,
                Color color, Color outlineColor, Hex cur) {
    super(xcor, ycor, radius, color, outlineColor, cur);
    _curHP = curHP;
    _maxHP = maxHP;
    _curMoves = curMoves;
    _maxMoves = maxMoves;
    _range = range;
    _actions = new LinkedList();
  }

  public int getMoves() {
    return _curMoves;
  }

  public void reset() {
    _curMoves = _maxMoves;
  }
}
