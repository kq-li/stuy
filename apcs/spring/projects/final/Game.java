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

  public static final int TARGET_FPS = 15;
  public static final double FRAME_LENGTH = 1.0 / TARGET_FPS;

  public static final boolean DEBUG = false;

  static class State {
    static final int PLAYER_PLACE = 0;
    static final int PLAYER_WAIT = 1;
    static final int PLAYER_MOVE = 2;
    static final int PLAYER_ATTACK = 3;
    static final int NEXT_TURN = 4;
  }

  public Game(int radius) {
    super();
    _isRunning = false;
    _state = State.PLAYER_WAIT;
    _turn = 0;
    _grid = new Grid(radius, WIDTH, HEIGHT);
    _curHex = null;
    _frame = new JFrame(Game.TITLE);

    Hex top = _grid.getHex(radius, 0, 2 * radius);
    Hex bottom = _grid.getHex(radius, 2 * radius, 0);
    
    _player = new Player(top.getCenterX(),
                         top.getCenterY(),
                         0.5 * _grid.getHexRadius(),
                         top);
    _other = new Player(bottom.getCenterX(),
                        bottom.getCenterY(),
                        0.5 * _grid.getHexRadius(),
                        bottom);
    _renderer = new Renderer();
  }

  public void start() {
    init();
    loop();
    stop();
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

  public void stop() {
    _curHex = null;
    render();
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
    case State.PLAYER_WAIT:
      for (Hex hex : _grid.getActive())
        hex.reset();

      ArrayList<Hex> hexes = new ArrayList<Hex>();

      switch (e.getButton()) {
      case MouseEvent.BUTTON1:
        if (cur == _player.getTracer().getCur()) {
          playerMove();
          _state = State.PLAYER_MOVE;
        } else {
          _grid.resetActive();
        }
         
        break;
      case MouseEvent.BUTTON3:
        if (cur == _player.getCur()) {
          _player.setCurProjectile(new Projectile(cur.getCenterX(),
                                                  cur.getCenterY(),
                                                  0.3 * _grid.getHexRadius(),
                                                  cur,
                                                  _player));
          _player.addProjectile(_player.getCurProjectile());
          playerAttack();
          _state = State.PLAYER_ATTACK;
          break;
        } else {
          _grid.resetActive();
        }
      }

      break;
    case State.PLAYER_MOVE:
      if (_grid.getActive().contains(cur)) {
        _player.moveTracer(cur);

        for (Hex hex : _grid.getActive())
          hex.reset();

        _grid.resetActive();

        if (_player.getMoves() == 0) {
          nextTurn();
          _state = State.PLAYER_WAIT;
        } else {
          playerMove();
        }
      } else {
        for (Hex hex : _grid.getActive())
          hex.reset();
        
        _grid.resetActive();
        _player.resetTracer();
        _state = State.PLAYER_WAIT;
      }
      
      break;
    case State.PLAYER_ATTACK:
      if (_grid.getActive().contains(cur)) {
        _player.getCurProjectile().moveTracer(cur);

        for (Hex hex : _grid.getActive())
          hex.reset();

        _grid.resetActive();

        if (_player.getCurProjectile().getMoves() == 0) {
          _player.resetCurProjectile();
          nextTurn();
          _state = State.PLAYER_WAIT;
        } else {
          playerAttack();
        }
      } else {
        for (Hex hex : _grid.getActive())
          hex.reset();

        _grid.resetActive();
        _player.getCurProjectile().resetTracer();
        _player.removeProjectile(_player.getCurProjectile());
        _player.resetCurProjectile();
        _state = State.PLAYER_WAIT;
      }      
      
      break;
    }
  }

  public void playerMove() {
    for (Hex hex : _grid.getActive())
      hex.reset();
    
    ArrayList<Hex> hexes = _grid.hexesInRadius(_player.getTracer().getCur(),
                                               _player.getTracer().getMoves());

    for (Hex hex : hexes) {
      hex.setColor(Color.CYAN);
      _grid.addActive(hex);
    }
  }

  public void playerAttack() {
    for (Hex hex : _grid.getActive())
      hex.reset();

    ArrayList<Hex> hexes = _grid.hexesInRadius(_player.getCurProjectile().getTracer().getCur(),
                                               _player.getCurProjectile().getTracer().getMoves());

    for (Hex hex : hexes) {
      hex.setColor(Color.RED);
      _grid.addActive(hex);
    }
  }

  public void nextTurn() {
    Player temp = _player;
    _player = _other;
    _other = temp;

    if (++_turn % 2 == 0) {
      for (Projectile p : _player.getProjectiles()) 
        p.move();
      
      for (Projectile p : _other.getProjectiles()) 
        p.move();

      _player.move();
      _other.move();
      
      ArrayList<Projectile> shouldNotExist = new ArrayList<Projectile>();

      for (Projectile p : _player.getProjectiles()) {
        p.update();

        if (!p.shouldExist())
          shouldNotExist.add(p);
      }
      
      for (Projectile p : _other.getProjectiles()) {
        p.update();

        if (!p.shouldExist())
          shouldNotExist.add(p);
      }

      _player.update();
      _other.update();

      for (Projectile p : shouldNotExist) {
        p.getOwner().removeProjectile(p);
        p.getCur().removeProjectile(p);
      }

      if (!_player.shouldExist()) {
        _player = null;
        _isRunning = false;
      }

      if (!_other.shouldExist()) {
        _other = null;
        _isRunning = false;
      }      
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

    if (DEBUG) {
      if (_other != null) {
        _renderer.renderOtherTracer(_other.getTracer());
        _renderer.renderOtherPlayer(_other);
      }

      if (_player != null) {
        _renderer.renderSelfTracer(_player.getTracer());
        _renderer.renderSelfPlayer(_player);

        for (Projectile p : _player.getProjectiles()) {
          _renderer.renderSelfTracer(p.getTracer());
          _renderer.renderSelfProjectile(p);
        }
      }

      if (_other != null) {
        for (Projectile p : _other.getProjectiles()) {
          _renderer.renderOtherTracer(p.getTracer());
          _renderer.renderOtherProjectile(p);
        }
      }
    } else {
      if (_player != null) {
        if (_state == State.PLAYER_MOVE)
          _renderer.renderSelfTracer(_player.getTracer());

        if (_curHex != null && _curHex.getPlayer() == _player)
          _renderer.renderSelfTracer(_player.getTracer());

        if (_state == State.PLAYER_ATTACK)
          _renderer.renderSelfTracer(_player.getCurProjectile().getTracer());

        if (_curHex != null) 
          for (Projectile p : _curHex.getProjectiles())
            if (p.getOwner() == _player)
              _renderer.renderSelfTracer(p.getTracer());
      }

      if (_other != null)
        _renderer.renderOtherPlayer(_other);

      if (_player != null) {
        _renderer.renderSelfPlayer(_player);
      
        for (Projectile p : _player.getProjectiles())
          _renderer.renderSelfProjectile(p);
      }

      if (_other != null)
        for (Projectile p : _other.getProjectiles())
          _renderer.renderOtherProjectile(p);
    }
  }
  
  public static void main(String[] args) {
    Game game = new Game(3);
    game.start();
  }
}
