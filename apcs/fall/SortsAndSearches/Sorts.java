public class Sorts {
  public static int bogoSort(Comparable[] a) {
    int tries = 0;
    while (!isSorted(a)) {
      tries++;
      shuffle(a);
    }
    return tries;
  }
  
  public static int bubbleSort(Comparable[] a) {
    int swaps = 0;
    for (int i = 1; i < a.length; i++) {
      int passSwaps = 0;
      for (int j = 0; j < a.length - i; j++) {
        if (a[j].compareTo(a[j + 1]) > 0) {
          passSwaps++;
          Comparable temp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = temp;
        }
      }
      swaps += passSwaps;
      if (passSwaps == 0)
        break;
    }
    return swaps;
  }

  public static void selectionSort(Comparable[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      int minPos = i;
      for (int j = i + 1; j < a.length; j++) {
        if (a[minPos].compareTo(a[j]) > 0)
          minPos = j;
      }
      Comparable temp = a[i];
      a[i] = a[minPos];
      a[minPos] = temp;
    }
  }

  public static void insertionSort(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = i; j > 0; j--) {
        if (a[j].compareTo(a[j - 1]) < 0) {
          Comparable temp = a[j];
          a[j] = a[j - 1];
          a[j - 1] = temp;
        } else {
          break;
        }
      }
    }
  }
  
  public static void shuffle(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      int index = (int) (Math.random() * (a.length - i) + i);
      Comparable temp = a[i];
      a[i] = a[index];
      a[index] = temp;
    }
  }

  public static boolean isSorted(Comparable[] a) {
    boolean sorted = true;
    for (int i = 0; i < a.length - 1; i++)
      if (a[i].compareTo(a[i + 1]) > 0) {
        sorted = false;
        break;
      }
    return sorted;
  }
}
