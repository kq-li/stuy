import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;

public class Game extends JPanel {
  protected JFrame _frame;
  protected int _frameWidth, _frameHeight, _currentLevel, _deaths;
  protected boolean _isRunning;
  protected List<Platform> _platforms;
  protected Player _player;

  protected final int GRAVITY = 325;

  protected final int MAX_LEVEL = 10;
  
  protected static final String TITLE = "Platformer";
  protected static final String FONT_NAME = "Ubuntu Medium";
  protected static final int FONT_STYLE = Font.PLAIN;

  public Game(int width, int height) {
    super();
    _frameWidth = width;
    _frameHeight = height;
    _currentLevel = 4;
    _deaths = 0;
    
    _isRunning = true;

    clearLevel();
    initFrame();
    initKeybinds();
  }

  private void clearLevel() {
    _platforms = new ArrayList<Platform>();
    _player = null;
  }
  
  private void initFrame() {
    _frame = new JFrame(Game.TITLE);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
  }

  private void initKeybinds() {
    Action startUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
            _player.setUp(true);
        }
      };

    Action stopUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
            _player.setUp(false);
        }
      };
    
    Action startDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
            _player.setDown(true);
        }
      };
    
    Action startLeft = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
            _player.setLeft(true);
        }
      };

    Action stopLeft = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
            _player.setLeft(false);
        }
      };
        
    Action startRight = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
            _player.setRight(true);
        }
      };

    Action stopRight = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          if (_player != null)
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

  private void loadLevel(int level) {
    try (Scanner scanner = new Scanner(new File("level" + level + ".txt"))) {
      String[] line = scanner.nextLine().split(" ");
      double sx = Double.parseDouble(line[0]);
      double sy = Double.parseDouble(line[1]);
      double width = Double.parseDouble(line[2]);
      double height = Double.parseDouble(line[3]);

      setPlayer(new EntityBuilder()
                .sx(sx)
                .sy(sy)
                .width(width)
                .height(height)
                .ay(GRAVITY)
                .buildPlayer(0, 0, _frameWidth, _frameHeight));
     
      while ((line = scanner.nextLine().split(" ")).length > 0) {
        sx = Double.parseDouble(line[0]);
        sy = Double.parseDouble(line[1]);
        width = Double.parseDouble(line[2]);
        height = Double.parseDouble(line[3]);
        int type = Integer.parseInt(line[4]);

        switch (type) {
        case 0:
          addPlatform(new EntityBuilder()
                      .sx(sx)
                      .sy(sy)
                      .width(width)
                      .height(height)
                      .buildNormal());
          break;
        case 1:
          addPlatform(new EntityBuilder()
                      .sx(sx)
                      .sy(sy)
                      .width(width)
                      .height(height)
                      .buildFire());
          break;
        case 2:
          double bounceStrength = Double.parseDouble(line[5]);
          addPlatform(new EntityBuilder()
                      .sx(sx)
                      .sy(sy)
                      .width(width)
                      .height(height)
                      .buildBouncy(bounceStrength));
          break;
        case 3:
          double vy = Double.parseDouble(line[5]);
          double deltay = Double.parseDouble(line[6]);
          addPlatform(new EntityBuilder()
                      .sx(sx)
                      .sy(sy)
                      .width(width)
                      .height(height)
                      .vy(vy)
                      .buildVertical(deltay));
          break;
        case 4:
          double vx = Double.parseDouble(line[5]);
          double deltax = Double.parseDouble(line[6]);
          addPlatform(new EntityBuilder()
                      .sx(sx)
                      .sy(sy)
                      .width(width)
                      .height(height)
                      .vx(vx)
                      .buildHorizontal(deltax));
          break;
        case 9001:
          addPlatform(new EntityBuilder()
                      .sx(sx)
                      .sy(sy)
                      .width(width)
                      .height(height)
                      .buildGoal());
          break;
        }
      }
    } catch (Exception e) {

    }
  }

  private void setPlayer(Player player) {
    _player = player;
  }

  private Platform addPlatform(Platform platform) {
    _platforms.add(platform);
    return platform;
  }
 
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (Platform p : _platforms)
      p.render(g);

    if (_player != null)
      _player.render(g);
  }

  public Dimension getPreferredSize() {
    return new Dimension(_frameWidth, _frameHeight);
  }
  
  public void display() {
    _frame.pack();
    _frame.setVisible(true);
  }

  private void update(double dt) {
    for (Platform p : _platforms)
      p.update(dt);
    
    boolean won = false;
    boolean dead = false;

    if (_player._sy >= _player.MAX_SY)
      dead = true;

    Platform lastPlatform = _player._currentPlatform;
    _player._currentPlatform = null;
   
    for (Platform p : _platforms) {
      if (_player.onPlatform(p))
        _player._currentPlatform = p;
      else if (lastPlatform == p && _player._iy >= 0 && _player.overPlatform(p))
        _player._currentPlatform = p;

      if (p instanceof FirePlatform && _player.touching(p))
        dead = true;

      if (p instanceof BouncyPlatform && _player._currentPlatform == p)
        _player.setVY(-((BouncyPlatform) p)._bounceStrength);

      if (p instanceof GoalPlatform && _player._currentPlatform == p)
        won = true;
      
      if (dead || won)
        break;
    }
    
    _player.update(dt);
    
    if (won) {
      System.out.println("Victory!");
      _currentLevel++;
      
      if (_currentLevel > MAX_LEVEL) {
        System.out.print("Congratulations! You have completed the game with " + _deaths);
        
        if (_deaths == 1)
          System.out.println(" death.");
        else
          System.out.println(" deaths.");
        
        _isRunning = false;
      } else {
        System.out.println("Promoted to level " + _currentLevel);
        clearLevel();
        loadLevel(_currentLevel);
      }
    }
    
    if (dead) {
      _deaths++;
      System.out.println("Deaths: " + _deaths);
      clearLevel();
      loadLevel(_currentLevel);
    }
  }

  private void render() {
    paintImmediately(0, 0, _frameWidth, _frameHeight);
  }
  
  private void loop() {
    final int TARGET_FPS = 240;
    final double FRAME_LENGTH = 1.0 / TARGET_FPS;

    double next = System.nanoTime() / 1000000000.0;
        
    while (_isRunning) {
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
  
  public static void main(String[] args) {
    Game game = new Game(600, 400);
    game.loadLevel(game._currentLevel);
    game.repaint();
    game.display();
    game.loop();
  }
}

