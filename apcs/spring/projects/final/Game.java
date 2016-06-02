import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;

public class Game extends JPanel implements MouseListener {
  protected boolean _isRunning;
  protected int _gridRadius, _hexCount;
  protected double _hexRadius;
  protected JFrame _frame;
  protected Hex[][][] _hexGrid;
  //  protected Player _player, _other;

  protected static final int WIDTH = 800;
  protected static final int HEIGHT = 600;
  protected static final String TITLE = "Paths";
  protected static final String FONT_NAME = "Ubuntu Medium";
  protected static final int FONT_STYLE = Font.PLAIN;

  public Game(int gridRadius) {
    super();
    _gridRadius = gridRadius;
    _hexCount = numHexes(gridRadius);
    _hexRadius = (double) (Math.min(HEIGHT, WIDTH) * 0.75) / (4 * gridRadius);
    _isRunning = false;
    _hexGrid = new Hex[2 * gridRadius + 1][2 * gridRadius + 1][2 * gridRadius + 1];

    for (int x = 0; x < 2 * gridRadius + 1; x++) {
      for (int y = 0; y < 2 * gridRadius + 1; y++) { 
        for (int z = 0; z < 2 * gridRadius + 1; z++) { 
          if (isHex(x, y, z)) {
            double xcor = WIDTH / 2.0 + (x - _gridRadius) * 1.5 * _hexRadius;
            double ycor = HEIGHT / 2.0 + (y - z) * Math.sqrt(3) / 2 * _hexRadius;
            _hexGrid[x][y][z] = new Hex(x, y, z, xcor, ycor, _hexRadius);
          }
        }
      }
    }
    
    _frame = new JFrame(Game.TITLE);
  }

  private static int numHexes(int r) {
    if (r == 1)
      return 1;
        
    return 6 * r - 6 + numHexes(r - 1);
  }
  
  public void start() {
    init();
    //    loop();
  }
  
  public void init() {
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
    _frame.addMouseListener(this);
    _frame.pack();
    _frame.setVisible(true);
  }

  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }
  
  public boolean isHex(int x, int y, int z) {
    return x + y + z == _gridRadius * 3;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    for (int x = 0; x < 2 * _gridRadius + 1; x++) {
      for (int y = 0; y < 2 * _gridRadius + 1; y++) {
        for (int z = 0; z < 2 * _gridRadius + 1; z++) {
          if (isHex(x, y, z)) {
            g2.setColor(Color.BLACK);
            g2.fill(_hexGrid[x][y][z].getPath());
            g2.setColor(Color.WHITE);
            g2.draw(_hexGrid[x][y][z].getPath());
          }
        }
      }
    }
  }

  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == 1) 
      for (int x = 0; x < 2 * _gridRadius + 1; x++) 
        for (int y = 0; y < 2 * _gridRadius + 1; y++) 
          for (int z = 0; z < 2 * _gridRadius + 1; z++) 
            if (isHex(x, y, z) && _hexGrid[x][y][z].isInside(e.getX(), e.getY()))
              System.out.println((x - _gridRadius) + " " +
                                 (y - _gridRadius) + " " +
                                 (z - _gridRadius));
  }

  public void mousePressed(MouseEvent e) {

  }

  public void mouseReleased(MouseEvent e) {

  }

  public void mouseEntered(MouseEvent e) {

  }

  public void mouseExited(MouseEvent e) {

  }

  public static void main(String[] args) {
    Game game = new Game(3);
    game.start();
  }
}
