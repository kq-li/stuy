public class NodeStack<E> implements Stack<E> {
  private int _size;
  private Node<E> _top;

  public NodeStack() {
    _size = 0;
    _top = null;
  }

  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void push(E value) {
    _top = new Node<E>(value, _top);
    _size++;
  }

  public E top() throws EmptyStackException {
    return _top.getValue();
  }
    
  public E pop() throws EmptyStackException {
    E top = _top.getValue();
    _top = _top.getNext();
    _size--;
    return top;
  }

  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;

    for (Node<E> node = _top; node != null; node = node.getNext())
      ret += node + "\n";

    return ret.substring(0, ret.length() - 1);
  }
  
  public static void main(String[] args) {
    Stack<Integer> S = new NodeStack<Integer>();

    for (int i = 0; i < 20; i++)
      S.push(i);
    
    System.out.println(S);
  }
}
