import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
  protected boolean _isRunning, _hasActed;
  protected int _state, _firstTurn, _turn;
  protected Grid _grid;
  protected Hex _curHex;
  protected Button _curButton;
  protected JFrame _frame;
  protected Player[] _players;
  protected GUI _gui;
  protected Renderer _renderer;

  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

  public static final String TITLE = "Paths";

  public static final int TARGET_FPS = 15;
  public static final double FRAME_LENGTH = 1.0 / TARGET_FPS;

  public static final boolean DEBUG = false;

  // inner class containing state constants
  static class State {
    static final int PLAYER_PLACE = 0;
    static final int PLAYER_WAIT = 1;
    static final int PLAYER_MOVE = 2;
    static final int PLAYER_ATTACK = 3;
    static final int NEXT_TURN = 4;
  }

  public Game(int radius) {
    super(); 
    _state = State.PLAYER_WAIT;
    _grid = new Grid(radius, WIDTH, HEIGHT);
    _curHex = null;
    _curButton = null;
    _frame = new JFrame(TITLE);

    Hex top = _grid.getHex(radius, 0, 2 * radius);
    Hex bottom = _grid.getHex(radius, 2 * radius, 0);

    _players = new Player[2];
    _players[0] = new Player(top.getCenterX(),
                             top.getCenterY(),
                             0.5 * _grid.getHexRadius(),
                             top,
                             1);
    _players[1] = new Player(bottom.getCenterX(),
                             bottom.getCenterY(),
                             0.5 * _grid.getHexRadius(),
                             bottom,
                             2);
    _gui = new GUI(0.9 * WIDTH - Renderer.IMAGE_SIZE / 2,
                   0.5 * HEIGHT - Renderer.IMAGE_SIZE / 2,
                   Renderer.IMAGE_SIZE);
    _renderer = new Renderer();
  }

  // Overarching game function
  public void start() {
    init();
    loop();
    stop();
  }
  
  // Further initialization 
  public void init() {
    setBackground(Color.WHITE);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    _frame.pack();
    _frame.setVisible(true);
    _turn = (int) (Math.random() * 2);
    _firstTurn = _turn;
    System.out.println("Player " + (_turn + 1) + " starts!");
    _gui.setMessage("Player " + (_turn + 1) + " starts!");
    _isRunning = true;
    _hasActed = false;
  }

  // Loop that calls render() once every FRAME_LENGTH seconds
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

  // Cleanup and termination
  public void stop() {
    System.out.println("Stopping game...");
    System.out.println(_gui.getMessage());
    _curHex = null;
    _curButton = null;
    render();
  }

  // Accessors and mutators for player instance variables
  public Player getSelf() {
    return _players[_turn % 2];
  }

  public void setSelf(Player p) {
    _players[_turn % 2] = p;
  }

  public Player getOther() {
    return _players[(_turn + 1) % 2];
  }

  public void setOther(Player p) {
    _players[(_turn + 1) % 2] = p;
  }

  // Wrapper function for rendering graphics
  protected void render() {
    repaint();
  }

  // JPanel override to set window size
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  // Mouse click listener that sets state based on user input
  public void mouseClicked(MouseEvent e) {
    if (_isRunning) {
      Hex curHex = _grid.whichHex(e.getX(), e.getY());
      Button curButton = _gui.whichButton(e.getX(), e.getY());

      switch (_state) {
      case State.PLAYER_WAIT: // Player not inputting an action
        if (curButton != null && curButton.getID().equals("check")) {
          _hasActed = true;
          nextTurn();
        } else if (!_hasActed) {
          for (Hex hex : _grid.getActive())
            hex.reset();

          ArrayList<Hex> hexes = new ArrayList<Hex>();
        
          if ((curHex == getSelf().getTracer().getCur() && e.getButton() == MouseEvent.BUTTON1) ||
              (curButton != null && curButton.getID().equals("move"))) {
            playerMove();
            _state = State.PLAYER_MOVE;
          } else if ((curHex == getSelf().getCur() && e.getButton() == MouseEvent.BUTTON3) ||
                     (curButton != null && curButton.getID().equals("attack"))) {
            getSelf().setCurProjectile(new Projectile(getSelf().getCur().getCenterX(),
                                                      getSelf().getCur().getCenterY(),
                                                      0.3 * _grid.getHexRadius(),
                                                      getSelf().getCur(),
                                                      getSelf()));
            getSelf().addProjectile(getSelf().getCurProjectile());
            getSelf().getCur().addProjectile(getSelf().getCurProjectile());
            playerAttack();
            _state = State.PLAYER_ATTACK;          
          } else {
            _grid.resetActive();
          }
        } else if ((curButton != null && !curButton.getID().equals("check")) ||
                   e.getButton() == MouseEvent.BUTTON2) {
          if (getSelf().getCurProjectile() != null) {
            _gui.appendMessage("Attack canceled.");
            getSelf().resetAttack();
          } else {
            _gui.appendMessage("Move canceled.");
            getSelf().resetMove();
          }
        
          _hasActed = false;
          mouseClicked(e);
        }
      
        break;
      case State.PLAYER_MOVE: // Player inputting a move command
        if (_grid.getActive().contains(curHex) && e.getButton() != MouseEvent.BUTTON2) {
          getSelf().moveTracer(curHex);

          for (Hex hex : _grid.getActive())
            hex.reset();

          _grid.resetActive();

          if (getSelf().getMoves() == 0) {
            _gui.appendMessage("Move queued.");
            _hasActed = true;
            _state = State.PLAYER_WAIT;
          } else {
            playerMove();
          }
        } else {
          _gui.appendMessage("Move canceled.");
        
          for (Hex hex : _grid.getActive())
            hex.reset();
        
          _grid.resetActive();
          getSelf().resetMove();
          _state = State.PLAYER_WAIT;
          mouseClicked(e);
        }
      
        break;
      case State.PLAYER_ATTACK: // Player inputting an attack command
        if (_grid.getActive().contains(curHex) && e.getButton() != MouseEvent.BUTTON2) {
          getSelf().getCurProjectile().moveTracer(curHex);

          for (Hex hex : _grid.getActive())
            hex.reset();

          _grid.resetActive();

          if (getSelf().getCurProjectile().getMoves() == 0) {
            _gui.appendMessage("Attack queued.");
            _hasActed = true;
            _state = State.PLAYER_WAIT;
          } else {
            playerAttack();
          }
        } else {
          _gui.appendMessage("Attack canceled.");
        
          for (Hex hex : _grid.getActive())
            hex.reset();

          _grid.resetActive();
          getSelf().resetAttack();
          _state = State.PLAYER_WAIT;
          mouseClicked(e);
        }      
      
        break;
      }
    }
  }

  // Set up hex grid for player movement
  public void playerMove() {
    for (Hex hex : _grid.getActive())
      hex.reset();
    
    ArrayList<Hex> hexes = _grid.hexesInRadius(getSelf().getTracer().getCur(),
                                               getSelf().getTracer().getMoves());

    for (Hex hex : hexes) {
      if (hex.getPlayer() == null) {
        hex.setColor(Hex.MOVE);
        _grid.addActive(hex);
      }
    }
  }


  // Set up hex grid for player attack
  public void playerAttack() {
    for (Hex hex : _grid.getActive())
      hex.reset();

    ArrayList<Hex> hexes = _grid.hexesInRadius(getSelf().getCurProjectile().getTracer().getCur(),
                                               getSelf().getCurProjectile().getTracer().getMoves());

    for (Hex hex : hexes) {
      hex.setColor(Hex.ATTACK);
      _grid.addActive(hex);
    }
  }

  // Advance from one turn to the next, updating all positions once both players have gone
  public void nextTurn() {
    _gui.clearMessage();
    
    if (++_turn % 2 == _firstTurn) {
      int curSelfHealth = getSelf().getHealth();
      int curOtherHealth = getOther().getHealth();
      
      for (Projectile p : getSelf().getProjectiles()) 
        p.move();
      
      for (Projectile p : getOther().getProjectiles()) 
        p.move();

      getSelf().move();
      getOther().move();
      
      ArrayList<Projectile> shouldNotExist = new ArrayList<Projectile>();

      for (Projectile p : getSelf().getProjectiles()) {
        p.update();

        if (!p.shouldExist())
          shouldNotExist.add(p);
      }
      
      for (Projectile p : getOther().getProjectiles()) {
        p.update();

        if (!p.shouldExist())
          shouldNotExist.add(p);
      }
      
      getSelf().update();
      getOther().update();

      if (curSelfHealth != getSelf().getHealth())
        _gui.appendMessage("Player " + getSelf().getNumber() +
                           " took " + (curSelfHealth - getSelf().getHealth()) +
                           " damage!");

      if (curOtherHealth != getOther().getHealth())
        _gui.appendMessage("Player " + getOther().getNumber() +
                           " took " + (curOtherHealth - getOther().getHealth()) +
                           " damage!");
      
      for (Projectile p : shouldNotExist) 
        p.destroy();

      if (!getSelf().shouldExist()) 
        setSelf(null);

      if (!getOther().shouldExist()) 
        setOther(null);

      if (getSelf() == null || getOther() == null) {
        if (getSelf() == null && getOther() == null) 
          _gui.setMessage("Tie!");
        else if (getSelf() == null)
          _gui.setMessage("Player " + getOther().getNumber() + " won!");
        else if (getOther() == null)
          _gui.setMessage("Player " + getSelf().getNumber() + " won!");

        _isRunning = false;
        return;
      }
    }

    _gui.appendMessage("Player " + getSelf().getNumber() + "'s turn!");
    _hasActed = false;
  }

  // Empty implementations of functions declared in interfaces
  public void mousePressed(MouseEvent e) {

  }

  public void mouseReleased(MouseEvent e) {

  }

  public void mouseEntered(MouseEvent e) {

  }

  public void mouseExited(MouseEvent e) {

  }

  public void mouseDragged(MouseEvent e) {

  }

  // Mouse motion listener for hover effects
  public void mouseMoved(MouseEvent e) {
    Hex curHex = _grid.whichHex(e.getX(), e.getY());
    Button curButton = _gui.whichButton(e.getX(), e.getY());

    if (curHex != _curHex) 
      _curHex = curHex;

    if (curButton != _curButton)
      _curButton = curButton;
  }

  // Rendering method, called by Swing repaint() at some point
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    _renderer.setGraphics((Graphics2D) g);
    _renderer.renderGrid(_grid.gridToAL(), _curHex, DEBUG);

    if (DEBUG) {
      if (getOther() != null) {
        _renderer.renderOtherTracer(getOther().getTracer());
        _renderer.renderOtherPlayer(getOther());
      }

      if (getSelf() != null) {
        _renderer.renderSelfTracer(getSelf().getTracer());
        _renderer.renderSelfPlayer(getSelf());

        for (Projectile p : getSelf().getProjectiles()) {
          _renderer.renderSelfTracer(p.getTracer());
          _renderer.renderSelfProjectile(p);
        }
      }

      if (getOther() != null) {
        for (Projectile p : getOther().getProjectiles()) {
          _renderer.renderOtherTracer(p.getTracer());
          _renderer.renderOtherProjectile(p);
        }
      }
    } else {
      if (getSelf() != null) {
        if (_state == State.PLAYER_MOVE)
          _renderer.renderSelfTracer(getSelf().getTracer());

        if (_curHex != null && _curHex.getPlayer() == getSelf())
          _renderer.renderSelfTracer(getSelf().getTracer());

        if (_state == State.PLAYER_ATTACK)
          _renderer.renderSelfTracer(getSelf().getCurProjectile().getTracer());

        if (_curHex != null) 
          for (Projectile p : _curHex.getProjectiles())
            if (p.getOwner() == getSelf())
              _renderer.renderSelfTracer(p.getTracer());
      }

      if (getSelf() != null) {
        _renderer.renderSelfPlayer(getSelf());
      
        for (Projectile p : getSelf().getProjectiles())
          _renderer.renderSelfProjectile(p);
      }

      if (getOther() != null) {
        for (Projectile p : getOther().getProjectiles())
          if (p != getOther().getCurProjectile())
            _renderer.renderOtherProjectile(p);

        _renderer.renderOtherPlayer(getOther());
      }
    }

    _renderer.renderGUI(_gui, _curButton, _hasActed, _turn, _firstTurn);
  }
  
  public static void main(String[] args) {
    Game game = new Game(3);
    game.start();
  }
}
