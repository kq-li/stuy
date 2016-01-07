import wheels.users.*;
import java.awt.event.MouseEvent;

public class ShyBlob extends Blob {
  public ShyBlob(int x, int y) {
    super(x, y);
  }

  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    move(getXLocation() + 5, getYLocation() + 5);
  }
}
