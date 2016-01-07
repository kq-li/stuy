import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Tile extends Rectangle {
  private int _x, _y, _state; // _state is 0 for unrevealed, 1 for marked, 2 for revealed
  private boolean _isBomb;

  public Tile(int x, int y, boolean isBomb) {
    super(x, y);
    _x = x;
    _y = y;
    _state = 0;
    _isBomb = false;
  }
  
  public void mouseReleased(MouseEvent e) {
    int button = e.getButton();
    switch (_state) {
    case 0:
      if (button == MouseEvent.BUTTON1)
        _state = 2;
      else if (button == MouseEvent.BUTTON2)
        _state = 1;
      break;
    case 1:
      if (button == MouseEvent.BUTTON2)
        _state = 0;
      break;
    }
  }
}
