public class SortTester {
  public static void main(String[] args) {
    insertionTest(args);
  }

  public static void bogoTest(String[] args) {
    int size = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    int successes = 0;
    int[] shuffles = new int[trials];
    for (int i = 0; i < trials; i++) {
      Integer[] a = ArrayIO.intArray(size);
      Sorts.shuffle(a);
      shuffles[i] = Sorts.bogoSort(a);
      if (shuffles[i] == 1)
        successes++;
    }
    System.out.println("Probability of an array of size " + size + " being sorted after 1 shuffle: " + ((double) successes / trials));
    System.out.println(Stats.mean(shuffles));
    System.out.println(Stats.variance(shuffles));
    System.out.println(Stats.stdDeviation(shuffles));
  }

  public static void bubbleTest(String[] args) {
    Integer[] a = new Integer[args.length];
    for (int i = 0; i < args.length; i++)
      a[i] = Integer.parseInt(args[i]);
    int swaps = Sorts.bubbleSort(a);
    ArrayIO.printArray(a);
    System.out.println(swaps);
  }

  public static void selectionTest(String[] args) {
    Integer[] a = new Integer[args.length];
    for (int i = 0; i < args.length; i++)
      a[i] = Integer.parseInt(args[i]);
    Sorts.selectionSort(a);
    ArrayIO.printArray(a);
  }

  public static void insertionTest(String[] args) {
    Integer[] a = new Integer[args.length];
    for (int i = 0; i < args.length; i++)
      a[i] = Integer.parseInt(args[i]);
    Sorts.insertionSort(a);
    ArrayIO.printArray(a);
  }
}
