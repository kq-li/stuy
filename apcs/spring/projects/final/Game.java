import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
  private boolean _isRunning;
  private JFrame _frame;
  private Hex _curHex;
  private Grid _grid;
  private Player _player, _other;
  private Renderer _renderer;

  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  public static final String TITLE = "Paths";
  public static final String FONT_NAME = "Ubuntu Medium";
  public static final int FONT_STYLE = Font.PLAIN;

  public static final int TARGET_FPS = 30;
  public static final double FRAME_LENGTH = 1.0 / TARGET_FPS;


  public Game(int gridRadius) {
    super();
    _grid = new Grid(gridRadius, WIDTH, HEIGHT);
    _isRunning = false;
    _curHex = null;
    _frame = new JFrame(Game.TITLE);
    _renderer = new Renderer();
  }

  public void start() {
    init();
    loop();
  }
  
  public void init() {
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    _frame.pack();
    _frame.setVisible(true);
    _isRunning = true;
    setBackground(Color.WHITE);
  }

  private void loop() {
    double next = System.nanoTime() / 1000000000.0;

    while (_isRunning) {
      double now = System.nanoTime() / 1000000000.0;

      if (now >= next) {
        next += FRAME_LENGTH;
        update(FRAME_LENGTH);
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

  private void update(double dt) {

  }

  private void render() {
    repaint();
  }
  
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == 1) {
      Hex cur = _grid.whichHex(e.getX(), e.getY());

      if (cur != null) {
        for (Hex hex : _grid.gridToPQ(cur)) {
          int colorVal = cur.distanceTo(hex) * 255 / (2 * _grid.getRadius() + 1);
          Color color = new Color(colorVal, colorVal, colorVal);
          hex.setDefaultColor(color);

          if (hex != _curHex)
            hex.setColor(color);
        }
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

    if (_curHex != cur) {
      if (_curHex != null) {
        _curHex.setColor(_curHex.getDefaultColor());
        _curHex.setOutlineColor(_curHex.getOutlineDefaultColor());
      }
        
      _curHex = cur;

      if (_curHex != null) {
        _curHex.setColor(Hex.HOVER);
        _curHex.setOutlineColor(Hex.OUTLINE_HOVER);
      }
    }
  }

  public void mouseDragged(MouseEvent e) {

  }
  
  public void renderGrid() {
    for (Hex hex : _grid.gridToAL())
      _renderer.renderHex(hex);

    if (_curHex != null)
      _renderer.renderHex(_curHex);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    _renderer.setGraphics((Graphics2D) g);
    renderGrid();
  }
  
  public static void main(String[] args) {
    Game game = new Game(3);
    System.out.println(Integer.toHexString((new Color(0, 0, 0)).getRGB()));
    game.start();
  }
}
