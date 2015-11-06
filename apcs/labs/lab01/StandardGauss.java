public class StandardGauss {
  public static void main(String[] args) {
    for (int i = 0; i < Integer.parseInt(args[0]); i++) {
      System.out.println(Math.sin(2 * Math.PI * Math.random()) * Math.sqrt((-2 * Math.log(Math.random()))));
    }
  }
}
