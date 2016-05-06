public class NodeDeque<E> implements Deque<E> {
  private DNode<E> _head, _tail;
  private int _size;

  public NodeDeque() {
    _head = new DNode<E>();
    _tail = new DNode<E>();
    _head.setNext(_tail);
    _tail.setPrev(_head);
    _size = 0;
  }

  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public E getFirst() throws EmptyDequeException {
    if (isEmpty())
      throw new EmptyDequeException("Deque empty");

    return _head.getNext().getValue();
  }

  public E getLast() throws EmptyDequeException {
    if (isEmpty())
      throw new EmptyDequeException("Deque empty");

    return _tail.getPrev().getValue();
  }

  public void addFirst(E val) {
    DNode<E> d = new DNode<E>(val, _head, _head.getNext());
    _head.setNext(d).setPrev(d);
    _size++;
  }
  
  public void addLast(E val) {
    DNode<E> d = new DNode<E>(val, _tail.getPrev(), _tail);
    _tail.setPrev(d).setNext(d);
    _size++;
  }

  public E removeFirst() throws EmptyDequeException {
    E ret = getFirst();
    DNode<E> d = _head.getNext();
    d.setPrev(null).setNext(d.getNext());
    d.setNext(null).setPrev(_head);
    return ret;
  }
  
  public E removeLast() throws EmptyDequeException {
    E ret = getLast();
    DNode<E> d = _tail.getPrev();
    d.setNext(null).setPrev(d.getPrev());
    d.setPrev(null).setNext(_tail);
    return ret;
  }

  public boolean offerFirst(E val) {
    addFirst(val);
    return true;
  }
  
  public boolean offerLast(E val) {
    addLast(val);
    return true;
  }
  
  public E pollFirst() {
    if (isEmpty())
      return null;

    return removeFirst();
  }
  
  public E pollLast() {
    if (isEmpty())
      return null;

    return removeLast();
  }

  public E peekFirst() {
    if (isEmpty())
      return null;

    return getFirst();
  }
  
  public E peekLast() {
    if (isEmpty())
      return null;

    return getLast();
  }

  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;

    for (DNode<E> d = _head.getNext(); d != _tail; d = d.getNext())
      ret += d.toString() + ", ";

    return "[" + ret.substring(0, ret.length() - 2) + "]";
  }

  public static void main(String[] args) {
    Deque<Integer> d = new NodeDeque<Integer>();
    d.addFirst(5);
    System.out.println(d);
    d.addFirst(3);
    System.out.println(d);
    d.addFirst(4);
    System.out.println(d);
    d.addLast(7);
    System.out.println(d);
    d.removeFirst();
    System.out.println(d);
    d.removeLast();
    System.out.println(d);
    d.offerFirst(2);
    System.out.println(d);
    d.offerLast(8);
    System.out.println(d);
    d.pollFirst();
    System.out.println(d);
    d.pollLast();
    System.out.println(d);
    System.out.println(d.peekFirst());
    System.out.println(d.peekLast());
  }
}
