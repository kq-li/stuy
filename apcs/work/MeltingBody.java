import java.awt.Color;
import java.awt.event.MouseEvent;
import wheels.users.*;

public class MeltingBody extends Body {
  private MeltingSnowman _snowman;
  
  public MeltingBody(int x, int y, int width, int height, MeltingSnowman snowman) {
    super(x, y, width, height);
    this._snowman = snowman;
  }

  public void mousePressed(MouseEvent e) {
    this._snowman.shrink();
  }
}
