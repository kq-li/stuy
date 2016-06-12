import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Player extends Entity {
  protected int _curHP, _maxHP, _curMoves, _maxMoves, _range;

  public static final Color PLAYER_COLOR = Color.GREEN;
  public static final Color PLAYER_OUTLINE_COLOR = Color.BLACK;
  public static final Color OTHER_COLOR = Color.RED;
  public static final Color OTHER_OUTLINE_COLOR = Color.BLACK;
  
  public Player(double xcor, double ycor, double radius, Hex cur) {
    this(10, 10, 3, 3, 2, xcor, ycor, radius, cur);
  }

  public Player(int curHP, int maxHP, int curMoves, int maxMoves, int range,
                double xcor, double ycor, double radius, Hex cur) {
    super(xcor, ycor, radius, cur);
    _curHP = curHP;
    _maxHP = maxHP;
    _curMoves = curMoves;
    _maxMoves = maxMoves;
    _range = range;
  }

  public int getMoves() {
    return _curMoves;
  }

  public int getRange() {
    return _range;
  }

  public void reset() {
    _curMoves = _maxMoves;
  }

  public void moveTo(Hex hex) {
    _curMoves -= hex.distanceTo(_cur);
    _cur.removePlayer();
    super.moveTo(hex);
    hex.setPlayer(this);
  }
}
