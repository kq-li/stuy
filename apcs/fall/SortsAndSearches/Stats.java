public class Stats {
  public static double mean(double[] a) {
    if (a.length == 0)
      return 0;
    double sum = 0;
    for (int i = 0; i < a.length; i++)
      sum += a[i];
    return sum / a.length;
  }

  public static double mean(int[] a) {
    if (a.length == 0)
      return 0;
    int sum = 0;
    for (int i = 0; i < a.length; i++)
      sum += a[i];
    return (double) sum / a.length;
  }

  public static double variance(double[] a) {
    double mean = mean(a);
    double[] diffs = new double[a.length];
    for (int i = 0; i < a.length; i++)
      diffs[i] = (a[i] - mean) * (a[i] - mean);
    return mean(diffs);
  }

  public static double variance(int[] a) {
    double mean = mean(a);
    double[] diffs = new double[a.length];
    for (int i = 0; i < a.length; i++)
      diffs[i] = (a[i] - mean) * (a[i] - mean);
    return mean(diffs);
  }

  public static double stdDeviation(double[] a) {
    return Math.sqrt(variance(a));
  }

  public static double stdDeviation(int[] a) {
    return Math.sqrt(variance(a));
  }
}
