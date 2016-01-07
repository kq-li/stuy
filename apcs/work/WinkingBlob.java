import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class WinkingBlob extends Blob {
  public WinkingBlob(int x, int y) {
    super(x, y);
  }

  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    getLeftEye().setSize(10, 10);
    getLeftEye().setLocation(getXLocation() + 30,
                             getYLocation() + 30);
  }

  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    this.getLeftEye().setSize(10, 30);
    this.getLeftEye().setLocation(getXLocation() + 30,
                                  getYLocation() + 20);
  }
}
