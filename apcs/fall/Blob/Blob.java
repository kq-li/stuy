import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Blob extends Ellipse {
  private Ellipse _leftEye, _rightEye;
  private int _mouseX, _mouseY;
  
  public Blob(int x, int y) {
    super(x, y);
    setSize(100, 100);
    setColor(Color.BLACK);
    _leftEye = new Ellipse(x + 30, y + 20);
    _leftEye.setSize(10, 30);
    _leftEye.setColor(Color.WHITE);
    _rightEye = new Ellipse(x + 60, y + 20);
    _rightEye.setSize(10, 30);
    _rightEye.setColor(Color.WHITE);
  }

  public Ellipse getLeftEye() {
    return _leftEye;
  }

  public Ellipse getRightEye() {
    return _rightEye;
  }

  public void move(int x, int y) {
    getLeftEye().setLocation(x + getLeftEye().getXLocation() - getXLocation(),
                             y + getLeftEye().getYLocation() - getYLocation());
    getRightEye().setLocation(x + getRightEye().getXLocation() - getXLocation(),
                              y + getRightEye().getYLocation() - getYLocation());
    setLocation(x, y);
  }
  
  public void mousePressed(MouseEvent e) {
    _mouseX = e.getX() - getXLocation();
    _mouseY = e.getY() - getYLocation();
  }

  public void mouseDragged(MouseEvent e) {
    int newX = e.getX() - _mouseX;
    int newY = e.getY() - _mouseY;
    move(newX, newY);
  }

  public void mouseReleased(MouseEvent e) {
    _mouseX = 0;
    _mouseY = 0;
  }
}
