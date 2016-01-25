public class SkiJumperClient {
  public static void main(String[] args) {
    SkiJumper s = new SkiJumper("John", "Doe");
    s.train(50);
    s.fly();
    System.out.println(s.getHoursTrained() + " " + s.getJumps());
  }
}
