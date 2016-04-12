public class StackQueue<E> implements Stack<E> {
  Queue<E> _q1, _q2;

  public StackQueue() {
    _q1 = new NodeQueue<E>();
    _q2 = new NodeQueue<E>();
  }

  public void push(E element) {
    while (!_q1.isEmpty())
      _q2.enqueue(_q1.dequeue());

    _q1.enqueue(element);

    while (!_q2.isEmpty())
      _q1.enqueue(_q2.dequeue());
  }

  public E pop() throws EmptyStackException {
    if (isEmpty())
      throw new EmptyStackException("Stack empty");

    return _q1.dequeue();
  }

  public E top() throws EmptyStackException {
    if (isEmpty())
      throw new EmptyStackException("Stack empty");

    return _q1.front();
  }

  public int size() {
    return _q1.size();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public String toString() {
    return _q1.toString();
  }

  public static void main(String[] args) {
    Stack<String> S = new StackQueue<String>();
    S.push("Abe");
    S.push("Ben");
    S.push("Carl");
    System.out.println(S);
    System.out.println(S.pop());
    System.out.println(S.top());
    System.out.println(S);
  }
}
