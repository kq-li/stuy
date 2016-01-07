import java.awt.Color;
import java.awt.event.MouseEvent;
import wheels.users.*;

public class MeltingSnowman {
  private int _x, _y;
  private MeltingBody[] _body;
  private Rectangle _hatTop;
  private Rectangle _hatBrim;
  private Rectangle _hatRibbon;
  
  public MeltingSnowman(int x, int y) {
    this._x = x;
    this._y = y;
    this.initBody();
    this.initHat();
  }

  public void initBody() {
    this._body = new MeltingBody[3];
    for (int i = 0; i < 3; i++) {
      this._body[i] = new MeltingBody(this._x + 10 * (2 - i), this._y + (40 + 5 * i) * (i + 1), 100, 100, this);
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
    this._hatRibbon.setColor(Color.BLUE);
  }

  public void shrink() {
    this._hatTop.setLocation(this._hatTop.getXLocation(), this._hatTop.getYLocation() + 105);
    this._hatBrim.setLocation(this._hatBrim.getXLocation(), this._hatBrim.getYLocation() + 105);
    this._hatRibbon.setLocation(this._hatRibbon.getXLocation(), this._hatRibbon.getYLocation() + 90);
    this._body[0].setLocation(this._body[0].getXLocation() + 15, this._body[0].getYLocation() + 120);
    this._body[0].setSize(this._body[0].getWidth() - 30, this._body[0].getHeight() - 30);
    this._body[1].setLocation(this._body[1].getXLocation() + 20, this._body[1].getYLocation() + 90);
    this._body[1].setSize(this._body[1].getWidth() - 40, this._body[1].getHeight() - 40);
    this._body[2].setLocation(this._body[2].getXLocation() + 25, this._body[2].getYLocation() + 50);
    this._body[2].setSize(this._body[2].getWidth() - 50, this._body[2].getHeight() - 50);
  }
}
