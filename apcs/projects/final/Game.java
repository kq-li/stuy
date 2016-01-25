import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
  protected JFrame _frame;
  protected int _frameWidth, _frameHeight;
  protected boolean _isRunning;
  protected List<Entity> _entities;
  protected List<Platform> _platforms;
  protected Player _player;

  protected final int GRAVITY = 150;

  protected static final double EPSILON = 1;
  
  protected static final String TITLE = "Platformer";
  protected static final String FONT_NAME = "Ubuntu Medium";
  protected static final int FONT_STYLE = Font.PLAIN;

  public Game(int width, int height) {
    super();
    _frameWidth = width;
    _frameHeight = height;
    _isRunning = true;

    _entities = new ArrayList<Entity>();
    _platforms = new ArrayList<Platform>();
    
    initFrame();
    initListeners();
  }

  private void initFrame() {
    _frame = new JFrame(Game.TITLE);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
    setLayout(new GridBagLayout());    
  }

  private void initListeners() {
    addMouseListener(this);
    
    Action startUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setUp(true);
        }
      };

    Action stopUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setUp(false);
        }
      };
    
    Action startDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setDown(true);
        }
      };
    
    Action startLeft = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setLeft(true);
        }
      };

    Action stopLeft = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setLeft(false);
        }
      };
        
    Action startRight = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setRight(true);
        }
      };

    Action stopRight = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          _player.setRight(false);
        }
      };
    
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"),
                                                       "upPressed");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"),
                                                       "downPressed");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed A"),
                                                       "leftPressed");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed D"),
                                                       "rightPressed");
    
    getActionMap().put("upPressed", startUp);
    getActionMap().put("downPressed", startDown);    
    getActionMap().put("leftPressed", startLeft);
    getActionMap().put("rightPressed", startRight);

    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"),
                                                       "upReleased");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"),
                                                       "downReleased");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"),
                                                       "leftReleased");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"),
                                                       "rightReleased");
    
    getActionMap().put("upReleased", stopUp);
    //    getActionMap().put("downReleased", );
    getActionMap().put("leftReleased", stopLeft);
    getActionMap().put("rightReleased", stopRight);
  }

  private void setPlayer(Player player) {
    this._player = player;
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (Entity entity : _entities)
      entity.render(g);
    

    for (Platform platform : _platforms)
      platform.render(g);
    
    _player.render(g);
  }

  public Dimension getPreferredSize() {
    return new Dimension(_frameWidth, _frameHeight);
  }
  
  public void display() {
    _frame.pack();
    _frame.setVisible(true);
  }

  private void addEntity(Entity entity) {
    _entities.add(entity);
  }

  private void addPlatform(Platform platform) {
    _platforms.add(platform);
  }

  private void update(double dt) {
    for (Entity e : _entities)
      e.update(dt);

    if (_player._vy + _player._iy < 0) {
      _player._onPlatform = false;
    } else {
      boolean onPlatform = false;
      
      for (Platform p : _platforms) {
        if (_player.onPlatform(p)) {
          onPlatform = true;
          break;
        }
      }

      _player._onPlatform = onPlatform;
    }
    
    _player.update(dt);
  }

  private void render() {
    paintImmediately(0, 0, _frameWidth, _frameHeight);
  }
  
  private void loop() {
    final int TARGET_FPS = 240;
    final double FRAME_LENGTH = 1.0 / TARGET_FPS;

    boolean isRunning = true;

    double next = System.nanoTime() / 1000000000.0;
        
    while (isRunning) {
      double now = System.nanoTime() / 1000000000.0;
      
      if (now >= next) {
        next += FRAME_LENGTH;
        update(FRAME_LENGTH);
        render();
      } else {
        int sleepTime = (int) (1000.0 * (next - now));

        if (sleepTime > 0) {
          try {
            Thread.sleep(sleepTime);
          } catch (Exception e) {

          }
        }
      }
    }
  }
  
  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}

  public void mouseEntered(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}

  public void mouseClicked(MouseEvent e) {}

  public void mouseMoved(MouseEvent e) {}

  public void mouseDragged(MouseEvent e) {}
  
  public static void main(String[] args) {
    Game game = new Game(600, 400);
    Player player = new Player(100, game._frameHeight - 50,
                               0, 0,
                               game._frameWidth - 50, game._frameHeight - 50,
                               0, 0,
                               0, game.GRAVITY,
                               20, 50, Color.BLUE);
    game.setPlayer(player);
    game.addPlatform(new Platform(250, 340, 100, 20, Color.BLACK));
    game.repaint();
    game.display();
    game.loop();
  }
}

