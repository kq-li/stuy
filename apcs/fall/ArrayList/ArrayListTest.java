import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
  /*
  //unsafe
  public static void populate(List L) {
    L.add(5);
    L.add(10);
    L.add(3);
    L.add(2);
    L.add("abe");
  }

  //does not compile
  public static void populate(List<Comparable> L) {
    L.add(5);
    L.add(10);
    L.add(3);
    L.add(2);
    L.add("abe");
  }
  */

  public static void populate(List<Integer> L) {
    L.add(5);
    L.add(10);
    L.add(3);
    L.add(2);
    //L.add("abe");
  }

  public static Comparable max1(List L) {
    Comparable max = (Comparable) L.get(0);
    for (int i = 1; i < L.size(); i++)
      if (max.compareTo((Comparable) L.get(i)) < 0)
        max = (Comparable) L.get(i);
    return max;      
  }

  public static Integer max2(List<Integer> L) {
    Integer max = L.get(0);
    for (int i = 1; i < L.size(); i++)
      if (max.compareTo(L.get(i)) < 0)
        max = L.get(i);
    return max;
  }

  public static void reverse(List<Integer> L) {
    for (int i = 0; i < L.size() / 2; i++)
      L.set(i, L.set(L.size() - i - 1, L.get(i)));
  }

  public static ArrayList<Integer> evens(List<Integer> L) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    for (int i = 0; i < L.size(); i++)
      if (L.get(i) % 2 == 0)
        ret.add(L.get(i));
    return ret;
  }

  public static void initialize(List<Integer> L, int n) {
    for (int i = 0; i < n; i++)
      L.add(i);
  }

  public static void addDoubles(List<Integer> L) {
    for (int i = 0; i < L.size(); i += 2)
      L.add(i + 1, L.get(i) * 2);
  }

  public static void removeEvens(List<Integer> L) {
    for (int i = 0; i < L.size(); i++)
      if (L.get(i) % 2 == 0)
        L.remove(i--);
  }

  public static List<Integer> merge(List<Integer> A, List<Integer> B) {
    List<Integer> ret = new ArrayList<Integer>();
    int i = 0, j = 0;
    while (i < A.size() && j < B.size()) {
      if (A.get(i) < B.get(j)) {
        ret.add(A.get(i++));
      } else if (A.get(i) > B.get(j)) {
        ret.add(B.get(j++));
      } else {
        ret.add(A.get(i++));
        ret.add(B.get(j++));
      }
    }
    while (i < A.size())
      ret.add(A.get(i++));
    while (j < B.size())
      ret.add(B.get(j++));
    return ret;
  }        
  
  public static void main(String[] args) {
    List<Integer> A = new ArrayList<Integer>();
    List<Integer> B = new ArrayList<Integer>();
    A.add(0);
    A.add(1);
    A.add(3);
    A.add(3);
    A.add(10);
    B.add(2);
    B.add(5);
    B.add(8);
    System.out.println(A + " " + B);
    System.out.println(merge(A, B));
    
    /*
    initialize(L, 5);
    System.out.println(L);
    populate(L);
    System.out.println(L);
    System.out.println(max1(L));
    System.out.println(max2(L));
    reverse(L);
    System.out.println(L);
    System.out.println(evens(L));
    addDoubles(L);
    System.out.println(L);
    removeEvens(L);
    System.out.println(L);
    */   
  }
}
