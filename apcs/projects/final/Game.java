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
  protected static final String TITLE = "Platformer";
  protected static final String FONT_NAME = "Ubuntu Medium";
  protected static final int FONT_STYLE = Font.PLAIN;

  public Game() {
    super();
    this._frameWidth = 600;
    this._frameHeight = 400;
    this.initFrame();
  }

  private void initFrame() {
    this._frame = new JFrame(Game.TITLE);
    this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this._frame.setContentPane(this);
    this.setLayout(new GridBagLayout());    
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
  }

  public Dimension getPreferredSize() {
    return new Dimension(this._frameWidth, this._frameHeight);
  }
  
  public void display() {
    this._frame.pack();
    this._frame.setVisible(true);
  }
 
  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}

  public void mouseEntered(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}

  public void mouseClicked(MouseEvent e) {}

  public void mouseMoved(MouseEvent e) {}

  public void mouseDragged(MouseEvent e) {}

  public static void main(String[] args) {
    Game game = new Game();
    game.display();
  }
}

