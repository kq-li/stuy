public class DNode<E> {
  public E _value;
  public DNode<E> _prev, _next;

  public DNode(E value, DNode<E> prev, DNode<E> next) {
    _value = value;
    _prev = prev;
    _next = next;
  }

  public DNode() {
    this(null, null, null);
  }

  public E getValue() {
    return _value;
  }

  public DNode<E> getPrev() {
    return _prev;
  }
  
  public DNode<E> getNext() {
    return _next;
  }
  
  public E setValue(E value) {
    E ret = _value;
    _value = value;
    return ret;
  }

  public DNode<E> setPrev(DNode<E> prev) {
    DNode<E> ret = _prev;
    _prev = prev;
    return ret;
  }
  
  public DNode<E> setNext(DNode<E> next) {
    DNode<E> ret = _next;
    _next = next;
    return ret;
  }

  public String toString() {
    return _value.toString();
  }
}
