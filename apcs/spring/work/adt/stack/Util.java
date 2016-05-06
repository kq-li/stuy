import java.util.List;
import java.util.ArrayList;

public class Util {
  public static<E> boolean contains(Stack<E> S, E stack) {
    return false;
  }
  
  // O(n) with ArrayList, O(n^2) with LinkedList
  public static<E> void reverseA(List<E> L) {
    Stack<E> S = new ArrayStack<E>(L.size());

    try {
      for (int i = 0; i < L.size(); i++)
        S.push(L.get(i)); // O(1) with ArrayList, O(n) with LinkedList

      for (int i = 0; i < L.size(); i++)
        L.set(i, S.pop()); // O(1) with ArrayList, O(n) with LinkedList
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // O(n) with ArrayList, O(n^2) with LinkedList
  public static<E> void reverseN(List<E> L) {
    Stack<E> S = new NodeStack<E>();

    try {
      for (int i = 0; i < L.size(); i++)
        S.push(L.get(i)); // O(1) with ArrayList, O(n) with LinkedList

      for (int i = 0; i < L.size(); i++)
        L.set(i, S.pop()); // O(1) with ArrayList, O(n) with LinkedList
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    List<Integer> L = new ArrayList<Integer>();

    for (int i = 0; i < 10; i++)
      L.add(i);

    System.out.println(L);
    reverseN(L);
    System.out.println(L);
  }
}
