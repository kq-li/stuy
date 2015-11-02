public class VerifyStdGauss {
  public static void main(String[] args) {
    double gauss;
    int i = 0, N = Integer.parseInt(args[0]), index;
    int[] distr = new int[14];
    while (i < N) {
      gauss = Math.sin(2 * Math.PI * Math.random()) * Math.sqrt((-2 * Math.log(Math.random())));
      index = Math.min(Math.max((int) Math.round(((int) (gauss * 10) / 10.0 + 3) * 2), 0), 13);
      distr[index]++;
      i++;
    }
    i = 0;
    String[] ranges = {"Under -3.0", "-3.0 to -2.5", "-2.5 to -2.0", "-2.0 to -1.5", "-1.5 to -1.0", "-1.0 to -0.5", "-0.5 to  0.0", " 0.0 to +0.5", "+0.5 to +1.0", "+1.0 to +1.5", "+1.5 to +2.0", "+2.0 to +2.5", "+2.5 to +3.0", "Over +3.0"};
    while (i < distr.length) {
      System.out.println(ranges[i] + "\t" + distr[i] + "\t" + (double) Math.round((double) distr[i] / N * 1000) / 10 + "%");
      i++;
    }
  }
}
