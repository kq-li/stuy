public class DataAnalysis {
  public static boolean isMode(int[] data, int index) {
    return (index > 0 && index < data.length - 1) && (data[index] > data[index - 1]) && (data[index] > data[index + 1]);
  }

  public static int modeIndex(int[] data) {
    for (int i = 0; i < data.length; i++)
      if (isMode(data, i))
        return i;
    return -1;
  }

  public static void printHistogram(int[] data, int maxLength, String symbol) {
    int modeIndex = modeIndex(data);
    for (int i = 0; i < data.length; i++) {
      int numSymbols;
      if (i == modeIndex)
        numSymbols = maxLength;
      else
        numSymbols = data[i] * maxLength / data[modeIndex];
      for (int j = 0; j < numSymbols; j++)
        System.out.print(symbol);
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[] A = {3, 5, 9, 10, 12, 11, 9, 4};
    System.out.println(isMode(A, 4));
    System.out.println(isMode(A, 5));
    System.out.println(modeIndex(A));
    printHistogram(A, 20, "x");
  }
}
