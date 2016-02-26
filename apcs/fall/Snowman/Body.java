import java.awt.Color;
import java.awt.event.MouseEvent;
import wheels.users.*;

public class Body extends Ellipse {
  public Body(int x, int y, int width, int height) {
    super(x, y);
    this.setSize(width, height);
    this.setColor(Color.WHITE);
  }
}
