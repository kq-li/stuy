public class Fish extends Animal {
  public Fish(Ecosystem ecosystem, int position) {
    super(ecosystem, position);
  }
  
  public Fish reproduce() {
    return _ecosystem.addFish();
  }

  public String toString() {
    return "Fish" + (_gender ? "M" : "F") + _strength;
  }
}
