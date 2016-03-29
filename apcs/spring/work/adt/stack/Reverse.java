import java.util.List;
import java.util.ArrayList;

public class Reverse {
  // O(n) for ArrayList, O(n^2) for LinkedList
  public static<E> void reverse(List<E> L) {
    Stack<E> S = new ArrayStack<E>(L.size());

    try {
      for (int i = 0; i < L.size(); i++)
        S.push(L.get(i)); // O(1) for ArrayList, O(n) for LinkedList

      for (int i = 0; i < L.size(); i++)
        L.set(i, S.pop()); // O(1) for ArrayList, O(n) for LinkedList
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer> L = new ArrayList<Integer>();

    for (int i = 0; i < 10; i++)
      L.add(i);

    System.out.println(L);
    reverse(L);
    System.out.println(L);
  }
}
