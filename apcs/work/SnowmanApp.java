import java.awt.Color;
import wheels.users.*;

public class SnowmanApp extends Frame {
  public SnowmanApp() {
    super();
    this.initBackground();
    this.initSnowman();
    this.initMeltingSnowman();
  }

  public void initBackground() {
    Rectangle sky = new Rectangle(0, 0);
    sky.setSize(700, 250);
    sky.setColor(new Color(180, 200, 255));
    Rectangle ground = new Rectangle(0, 250);
    ground.setSize(700, 250);
    ground.setColor(new Color(200, 60, 0));
  }

  public void initSnowman() {
    Snowman snowman = new Snowman(100, 100);
  }

  public void initMeltingSnowman() {
    MeltingSnowman snowman = new MeltingSnowman(500, 100);
  }

  public static void main(String[] args) {
    SnowmanApp app = new SnowmanApp();
  }
}
