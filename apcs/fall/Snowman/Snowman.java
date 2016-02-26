import java.awt.Color;
import java.awt.event.MouseEvent;
import wheels.users.*;

public class Snowman {
  private int _x, _y;
  private Body[] _body;
  private Rectangle _hatTop;
  private Rectangle _hatBrim;
  private Rectangle _hatRibbon;

  public Snowman(int x, int y) {
    this._x = x;
    this._y = y;
    this.initBody();
    this.initHat();
  }

  public void initBody() {
    this._body = new Body[3];
    for (int i = 0; i < 3; i++) {
      this._body[i] = new Body(this._x + 10 * (2 - i), this._y + (40 + 5 * i) * (i + 1), 100, 100);
      this._body[i].setSize(60 + 20 * i, 60 + 20 * i);
    }
  }

  public void initHat() {
    this._hatTop = new Rectangle(this._x + 20, this._y);
    this._hatTop.setSize(60, 60);
    this._hatTop.setColor(Color.BLACK);
    this._hatBrim = new Rectangle(this._x - 10, this._y + 50);
    this._hatBrim.setSize(120, 10);
    this._hatBrim.setColor(Color.BLACK);
    this._hatRibbon = new Rectangle(this._x + 20, this._y + 20);
    this._hatRibbon.setSize(60, 30);
    this._hatRibbon.setColor(Color.RED);
  }
}
    
