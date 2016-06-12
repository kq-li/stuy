import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
  protected boolean _isRunning;
  protected int _state, _turn;
  protected Grid _grid;
  protected Hex _curHex;
  protected JFrame _frame;
  protected Player _player, _other;
  protected Renderer _renderer;

  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

  public static final int FONT_STYLE = Font.PLAIN;
  public static final String FONT_NAME = "Ubuntu Medium";
  public static final String TITLE = "Paths";

  public static final int TARGET_FPS = 30;
  public static final double FRAME_LENGTH = 1.0 / TARGET_FPS;

  static class State {
    static final int PLAYER_PLACE = 0;
    static final int PLAYER_WAIT = 1;
    static final int PLAYER_MOVE = 2;
    static final int PLAYER_ATTACK = 3;
  }

  public Game(int gridRadius) {
    super();
    _isRunning = false;
    _state = State.PLAYER_PLACE;
    _turn = 0;
    _grid = new Grid(gridRadius, WIDTH, HEIGHT);
    _curHex = null;
    _frame = new JFrame(Game.TITLE);
    _player = null;
    _other = null;
    _renderer = new Renderer();
  }

  public void start() {
    init();
    loop();
  }
  
  public void init() {
    setBackground(Color.WHITE);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    _frame.pack();
    _frame.setVisible(true);
    _isRunning = true;
  }

  protected void loop() {
    double next = System.nanoTime() / 1000000000.0;

    while (_isRunning) {
      double now = System.nanoTime() / 1000000000.0;

      if (now >= next) {
        next += FRAME_LENGTH;
        render();
      } else {
        int sleep = (int) (1000.0 * (next - now));

        if (sleep > 0) {
          try {
            Thread.sleep(sleep);
          } catch (Exception e) {

          }
        }
      }
    }
  }

  protected void update() {
    for (Hex hex : _grid.gridToAL()) {
      for (Entity e : hex.getEntities()) {
        e.reset();
      }
    }
  }

  protected void render() {
    repaint();
  }
  
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void mouseClicked(MouseEvent e) {
    Hex cur = _grid.whichHex(e.getX(), e.getY());

    switch (_state) {
    case State.PLAYER_PLACE:
      if (cur != null) {
        _player = new Player(cur.getCenterX(), cur.getCenterY(),
                             _grid.getHexRadius() / 2.0, cur);
        cur.addEntity(_player);
        _state = State.PLAYER_WAIT;
      }

      break;
    case State.PLAYER_WAIT:
      for (Hex hex : _grid.getActive())
        hex.reset();

      if (cur == _player.getCur()) {
        ArrayList<Hex> hexes = new ArrayList<Hex>();

        switch (e.getButton()) {
        case MouseEvent.BUTTON1:
          hexes = _grid.hexesInRadius(cur, _player.getMoves());
          
          for (Hex hex : hexes) {
            if (hex.getPlayer() == null) {
              hex.setColor(Color.CYAN);
              _grid.addActive(hex);
            }
          }

          _state = State.PLAYER_MOVE;
          break;
        case MouseEvent.BUTTON3:
          hexes = _grid.hexesInRadius(cur, _player.getRange());
          
          for (Hex hex : hexes)
            hex.setColor(Color.RED);

          _state = State.PLAYER_ATTACK;
          break;
        }
      } else {
        _grid.resetActive();
      }

      break;
    case State.PLAYER_MOVE:
      if (_grid.getActive().contains(cur))
        _player.moveTo(cur);

      for (Hex hex : _grid.getActive())
        hex.reset();

      _grid.resetActive();      
      _state = State.PLAYER_WAIT;
      break;
    case State.PLAYER_ATTACK:
      
      break;
    }
  }

  public void mousePressed(MouseEvent e) {

  }

  public void mouseReleased(MouseEvent e) {

  }

  public void mouseEntered(MouseEvent e) {

  }

  public void mouseExited(MouseEvent e) {

  }

  public void mouseMoved(MouseEvent e) {
    Hex cur = _grid.whichHex(e.getX(), e.getY());

    if (cur != null)
      _curHex = cur;
  }

  public void mouseDragged(MouseEvent e) {

  }
  
  public void renderGrid() {
    for (Hex hex : _grid.gridToAL())
      _renderer.renderRegHex(hex);

    if (_curHex != null) 
      _renderer.renderCurHex(_curHex);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    _renderer.setGraphics((Graphics2D) g);
    renderGrid();
    
    if (_player != null) 
      _renderer.renderPlayer(_player);

    if (_other != null)
      _renderer.renderOther(_player);
  }

  public void nextTurn() {
    Player temp = _player;
    _player = _other;
    _other = temp;

    if (++_turn % 2 == 0)
      update();
  }
  
  public void printArray(Object[] a) {
    for (Object o : a)
      System.out.print(o + " ");

    System.out.println();
  }

  public void printArray(double[] a) {
    for (double d : a)
      System.out.print(d + " ");

    System.out.println();
  }

  public void printArray(int[] a) {
    for (int i : a)
      System.out.print(i + " ");

    System.out.println();
  }
  
  public static void main(String[] args) {
    Game game = new Game(3);
    game.start();
  }
}
