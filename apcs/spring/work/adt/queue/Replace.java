public class Replace {
  public static<E> void replace(Queue<E> Q, E oldValue, E newValue) {
    for (int i = 0; i < Q.size(); i++) {
      E current = Q.dequeue();

      if (current.equals(oldValue))
        current = newValue;

      Q.enqueue(current);
    }
  }

  public static void main(String[] args) {
    Queue<String> Q = new NodeQueue<String>();
    Q.enqueue("a");
    Q.enqueue("b");
    Q.enqueue("c");
    System.out.println(Q);
    replace(Q, "a", "x");
    System.out.println(Q);
    replace(Q, "b", "y");
    System.out.println(Q);
  }
}
