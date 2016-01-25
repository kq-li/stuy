import java.awt.Color;
import wheels.users.*;

public class SimpleGame extends Frame {
  private Rock[] _rocks;
  private int _gold;

  public SimpleGame() {
    super();
    this.initRocks();
  }

  private void initRocks() {
    this._rocks = new Rock[3];
    this._gold = (int) (Math.random() * 3);
    for (int i = 0; i < 3; i++)
      if (i == this._gold)
        this._rocks[i] = new GoldRock(50 + 250 * i, 100, 100, 100);
      else
        this._rocks[i] = new Rock(50 + 250 * i, 100, 100, 100);
  }

  public static void main(String[] args) {
    SimpleGame game = new SimpleGame();
  }
}
