public class Bear extends Animal {
  public Bear(Ecosystem ecosystem, int position) {
    super(ecosystem, position);
  }

  public Bear reproduce() {
    return _ecosystem.addBear();
  }

  public String toString() {
    return "Bear" + (_gender ? "M" : "F") + _strength;
  }
}
