import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
  protected boolean _isRunning;
  protected int _state, _initialTurn, _turn;
  protected Grid _grid;
  protected Hex _curHex;
  protected JFrame _frame;
  protected Player[] _players;
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
    _initialTurn = 0;
    _grid = new Grid(radius, WIDTH, HEIGHT);
    _curHex = null;
    _frame = new JFrame(Game.TITLE);

    Hex top = _grid.getHex(radius, 0, 2 * radius);
    Hex bottom = _grid.getHex(radius, 2 * radius, 0);

    _players = new Player[2];
    _players[0] = new Player(top.getCenterX(),
                             top.getCenterY(),
                             0.5 * _grid.getHexRadius(),
                             top);
    _players[1] = new Player(bottom.getCenterX(),
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
    _turn = (int) (Math.random() * 2);
    _initialTurn = _turn;
    System.out.println("Player " + (_turn + 1) + " starts!");
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

  public Player self() {
    return _players[_turn % 2];
  }

  public void self(Player p) {
    _players[_turn % 2] = p;
  }

  public Player other() {
    return _players[(_turn + 1) % 2];
  }

  public void other(Player p) {
    _players[(_turn + 1) % 2] = p;
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
        if (cur == self().getTracer().getCur()) {
          playerMove();
          _state = State.PLAYER_MOVE;
        } else {
          _grid.resetActive();
        }
         
        break;
      case MouseEvent.BUTTON3:
        if (cur == self().getCur()) {
          self().setCurProjectile(new Projectile(cur.getCenterX(),
                                                  cur.getCenterY(),
                                                  0.3 * _grid.getHexRadius(),
                                                  cur,
                                                  self()));
          self().addProjectile(self().getCurProjectile());
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
        self().moveTracer(cur);

        for (Hex hex : _grid.getActive())
          hex.reset();

        _grid.resetActive();

        if (self().getMoves() == 0) {
          nextTurn();
          _state = State.PLAYER_WAIT;
        } else {
          playerMove();
        }
      } else {
        for (Hex hex : _grid.getActive())
          hex.reset();
        
        _grid.resetActive();
        self().resetTracer();
        _state = State.PLAYER_WAIT;
      }
      
      break;
    case State.PLAYER_ATTACK:
      if (_grid.getActive().contains(cur)) {
        self().getCurProjectile().moveTracer(cur);

        for (Hex hex : _grid.getActive())
          hex.reset();

        _grid.resetActive();

        if (self().getCurProjectile().getMoves() == 0) {
          self().resetCurProjectile();
          nextTurn();
          _state = State.PLAYER_WAIT;
        } else {
          playerAttack();
        }
      } else {
        for (Hex hex : _grid.getActive())
          hex.reset();

        _grid.resetActive();
        self().getCurProjectile().resetTracer();
        self().removeProjectile(self().getCurProjectile());
        self().resetCurProjectile();
        _state = State.PLAYER_WAIT;
      }      
      
      break;
    }
  }

  public void playerMove() {
    for (Hex hex : _grid.getActive())
      hex.reset();
    
    ArrayList<Hex> hexes = _grid.hexesInRadius(self().getTracer().getCur(),
                                               self().getTracer().getMoves());

    for (Hex hex : hexes) {
      hex.setColor(Color.CYAN);
      _grid.addActive(hex);
    }
  }

  public void playerAttack() {
    for (Hex hex : _grid.getActive())
      hex.reset();

    ArrayList<Hex> hexes = _grid.hexesInRadius(self().getCurProjectile().getTracer().getCur(),
                                               self().getCurProjectile().getTracer().getMoves());

    for (Hex hex : hexes) {
      hex.setColor(Color.RED);
      _grid.addActive(hex);
    }
  }

  public void nextTurn() {
    if (++_turn % 2 == _initialTurn) {
      for (Projectile p : self().getProjectiles()) 
        p.move();
      
      for (Projectile p : other().getProjectiles()) 
        p.move();

      self().move();
      other().move();
      
      ArrayList<Projectile> shouldNotExist = new ArrayList<Projectile>();

      for (Projectile p : self().getProjectiles()) {
        p.update();

        if (!p.shouldExist())
          shouldNotExist.add(p);
      }
      
      for (Projectile p : other().getProjectiles()) {
        p.update();

        if (!p.shouldExist())
          shouldNotExist.add(p);
      }

      self().update();
      other().update();

      for (Projectile p : shouldNotExist) {
        p.getOwner().removeProjectile(p);
        p.getCur().removeProjectile(p);
      }

      if (!self().shouldExist()) {
        self(null);
        _isRunning = false;
      }

      if (!other().shouldExist()) {
        other(null);
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
      if (other() != null) {
        _renderer.renderOtherTracer(other().getTracer());
        _renderer.renderOtherPlayer(other());
      }

      if (self() != null) {
        _renderer.renderSelfTracer(self().getTracer());
        _renderer.renderSelfPlayer(self());

        for (Projectile p : self().getProjectiles()) {
          _renderer.renderSelfTracer(p.getTracer());
          _renderer.renderSelfProjectile(p);
        }
      }

      if (other() != null) {
        for (Projectile p : other().getProjectiles()) {
          _renderer.renderOtherTracer(p.getTracer());
          _renderer.renderOtherProjectile(p);
        }
      }
    } else {
      if (self() != null) {
        if (_state == State.PLAYER_MOVE)
          _renderer.renderSelfTracer(self().getTracer());

        if (_curHex != null && _curHex.getPlayer() == self())
          _renderer.renderSelfTracer(self().getTracer());

        if (_state == State.PLAYER_ATTACK)
          _renderer.renderSelfTracer(self().getCurProjectile().getTracer());

        if (_curHex != null) 
          for (Projectile p : _curHex.getProjectiles())
            if (p.getOwner() == self())
              _renderer.renderSelfTracer(p.getTracer());
      }

      if (other() != null)
        _renderer.renderOtherPlayer(other());

      if (self() != null) {
        _renderer.renderSelfPlayer(self());
      
        for (Projectile p : self().getProjectiles())
          _renderer.renderSelfProjectile(p);
      }

      if (other() != null)
        for (Projectile p : other().getProjectiles())
          _renderer.renderOtherProjectile(p);
    }
  }
  
  public static void main(String[] args) {
    Game game = new Game(3);
    game.start();
  }
}
