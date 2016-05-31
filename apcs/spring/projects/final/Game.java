import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;

public class Game extends JPanel {
  protected boolean _isRunning;
  protected int _radius, _hexCount;
  protected JFrame _frame;
  protected Hex[][][] _hexGrid;
  //  protected Player _player, _other;

  protected static final int WIDTH = 800;
  protected static final int HEIGHT = 600;
  protected static final String TITLE = "Paths";
  protected static final String FONT_NAME = "Ubuntu Medium";
  protected static final int FONT_STYLE = Font.PLAIN;

  public Game(int radius) {
    super();
    _radius = radius;
    _hexCount = numHexes(radius);
    _isRunning = false;
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
    _hexGrid = new Hex[_radius][_radius][_radius];
    
    _frame = new JFrame(Game.TITLE);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.setContentPane(this);
    _frame.pack();
    _frame.setVisible(true);
  }

  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }
  
  public boolean isHex(int x, int y, int z) {
    return x + y + z == _radius * 3;
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    for (int x = 0; x < _radius; x++) {
      for (int y = 0; y < _radius; y++) {
        for (int z = 0; z < _radius; z++) {
          if (x + y + z == 
  }

  public static void main(String[] args) {
    Game game = new Game(3);
  }
}
