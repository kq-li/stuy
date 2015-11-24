public class SortTester {
  public static void main(String[] args) {
    String[] names = {"abe", "jane", "mary", "michael"};
    int[] ages = {16, 4, 12, 2};
    ArrayIO.printArray(names);
    ArrayIO.printArray(ArrayIO.asObjArray(ages));
  }
}
