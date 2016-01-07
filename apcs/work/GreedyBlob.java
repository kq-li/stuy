import wheels.users.*;
import java.awt.event.MouseEvent;

public class GreedyBlob extends Blob {
  public GreedyBlob(int x, int y) {
    super(x, y);
  }

  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    setSize(getWidth() + 10, getHeight() + 10);
    setLocation(getXLocation() - 5, getYLocation() - 5);
    getLeftEye().setSize(getWidth() / 10, getHeight() * 3 / 10);
    getRightEye().setSize(getWidth() / 10, getHeight() * 3 / 10);
    getLeftEye().setLocation(getXLocation() + getWidth() * 3 / 10,
                             getYLocation() + getHeight() * 2 / 10);
    getRightEye().setLocation(getXLocation() + getWidth() * 6 / 10,
                             getYLocation() + getHeight() * 2 / 10);
        
  }
}
