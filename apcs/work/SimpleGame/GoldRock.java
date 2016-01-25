import java.awt.Color;
import java.awt.event.MouseEvent;
import wheels.users.*;

public class GoldRock extends Rock {
  public GoldRock(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void mousePressed(MouseEvent e) {
    this.setColor(Color.YELLOW);
  }
}
