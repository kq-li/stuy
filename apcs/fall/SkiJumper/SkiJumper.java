public class SkiJumper implements Athlete, Comparable, Flier {
  private String _firstName, _lastName;
  private double _hoursTraining;
  private int _numberOfJumps;

  public SkiJumper(String first, String last) {
    _firstName = first;
    _lastName = last;
    _hoursTraining = 0;
    _numberOfJumps = 0;
  }

  public void fly() {
    _numberOfJumps++;
  }

  public void train(double hours) {
    _hoursTraining += hours;
  }

  public double getHoursTrained() {
    return _hoursTraining;
  }

  public int getJumps() {
    return _numberOfJumps;
  }

  public int compareTo(Object other) {
    return getJumps() - ((SkiJumper) other).getJumps();
  }
}
