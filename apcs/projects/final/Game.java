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

  protected final int MAX_LEVEL = 3;
  
  protected static final String TITLE = "Platformer";
  protected static final String FONT_NAME = "Ubuntu Medium";
  protected static final int FONT_STYLE = Font.PLAIN;

  public Game(int width, int height) {
    super();
    _frameWidth = width;
    _frameHeight = height;
    _currentLevel = 1;
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
    setLayout(new GridBagLayout());    
  }

  private void initKeybinds() {
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

  private void loadLevel(int level) {
    try (Scanner scanner = new Scanner(new File("level" + level + ".txt"))) {
      double[] playerData = new double[4];

      for (int i = 0; i < 4; i++) 
        playerData[i] = scanner.nextDouble();

      setPlayer(playerData);

      int numPlatforms = scanner.nextInt();
      double[][] platforms = new double[numPlatforms][5];

      for (int i = 0; i < numPlatforms; i++)
        for (int j = 0; j < 5; j++) 
          platforms[i][j] = scanner.nextDouble();

      for (double[] data : platforms) {
        switch ((int) data[4]) {
        case 0:
          addNormalPlatform(data);
          break;
        case 1:
          addFirePlatform(data);
          break;
        case 2:
          addBouncyPlatform(data);
          break;
        case 9001:
          addGoalPlatform(data);
          break;
        }
      }
    } catch (Exception e) {

    }
  }

  private void setPlayer(Player player) {
    _player = player;
  }

  private void setPlayer(double sx, double sy, double width, double height) {
    _player = new Player(sx, sy, 0, 0, _frameWidth - width, _frameHeight - height,
                              0, 0,
                              0, GRAVITY,
                              width, height,
                              Color.BLUE);
  }

  private void setPlayer(double[] data) {
    setPlayer(data[0], data[1], data[2], data[3]);
  }
  
  private Platform addPlatform(Platform platform) {
    _platforms.add(platform);
    return platform;
  }

  private Platform addNormalPlatform(double[] data) {
    return addPlatform(new Platform(data[0], data[1], data[2], data[3]));
  }

  private Platform addFirePlatform(double[] data) {
    return addPlatform(new FirePlatform(data[0], data[1], data[2], data[3]));
  }

  private Platform addBouncyPlatform(double[] data) {
    return addPlatform(new BouncyPlatform(data[0], data[1], data[2], data[3]));
  }
  
  private Platform addGoalPlatform(double[] data) {
    return addPlatform(new GoalPlatform(data[0], data[1], data[2], data[3]));
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

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

  private void update(double dt) {
    boolean won = false;
    boolean dead = false;

    if (_player._sy >= _player.MAX_SY) {
      dead = true;
    }

    if (_player._vy + _player._iy < 0) {
      _player._onPlatform = false;
    } else {
      boolean onPlatform = false;
      
      for (Platform p : _platforms) {
        if (_player.onPlatform(p))
          onPlatform = true;

        if (p instanceof GoalPlatform && onPlatform)
          won = true;

        if (p instanceof BouncyPlatform && onPlatform)
          _player.setVY(-((BouncyPlatform) p)._bounceStrength);
        
        if (p instanceof FirePlatform && _player.touching(p))
          dead = true;

        if (onPlatform || dead || won)
          break;
      }

      _player._onPlatform = onPlatform;
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

