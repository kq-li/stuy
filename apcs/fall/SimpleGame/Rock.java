import java.awt.Color;
import java.awt.event.MouseEvent;
import wheels.users.*;

public class Rock extends Rectangle {
  public Rock(int x, int y, int width, int height) {
    super(x, y);
    this.setSize(width, height);
    this.setColor(Color.GRAY);
  }
}
