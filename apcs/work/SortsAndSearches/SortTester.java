public class SortTester {
  public static void main(String[] args) {
    String[] names = {"abe", "mary", "betty", "michael"};
    Integer[] ages = {3, 19, 4, 11};
    Double[] heights = {0.2, 3.0, 1.2, 1.1};
    
    ArrayIO.printArray(names);
    ArrayIO.printArray(ages);
    ArrayIO.printArray(heights);
    System.out.println();

    Sorts.shuffle(names);
    Sorts.shuffle(ages);
    Sorts.shuffle(heights);

    ArrayIO.printArray(names);
    ArrayIO.printArray(ages);
    ArrayIO.printArray(heights);
    System.out.println();

    Integer[][] twoD = {{1, 2, 3}, {4, 5, 6}};

    ArrayIO.printArray(twoD);
  }

  public static int factorial(int n) {
    if (n < 2)
      return 1;
    return n * factorial(n - 1);
  }
}
