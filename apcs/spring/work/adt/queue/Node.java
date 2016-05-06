public class Node<E> {
  private E _value;
  private Node<E> _next;

  public Node(E value, Node<E> next) {
    _value = value;
    _next = next;
  }

  public E getValue() {
    return _value;
  }

  public Node<E> getNext() {
    return _next;
  }

  public E setValue(E value) {
    E ret = _value;
    _value = value;
    return ret;
  }

  public Node<E> setNext(Node<E> next) {
    Node<E> ret = _next;
    _next = next;
    return ret;
  }

  public String toString() {
    return _value.toString();
  }
}
