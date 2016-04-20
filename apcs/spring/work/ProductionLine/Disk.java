public class Disk implements Comparable<Disk> {
  private int _radius;

  public Disk(int radius) {
    _radius = radius;
  }

  public int compareTo(Disk o) {
    return _radius - o._radius;
  }
}
