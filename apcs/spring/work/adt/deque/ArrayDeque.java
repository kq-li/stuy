public class ArrayDeque<E> implements Deque<E> {
  private int _head, _tail, _size;
  private E[] _deque;

  public static int CAPACITY = 5;
  
  public ArrayDeque(int capacity) {
    _head = 0;
    _tail = 1;
    _size = 0;
    _deque = (E[]) new Object[capacity + 1];
  }

  public ArrayDeque() {
    this(CAPACITY);
  }

  private int floorMod(int a, int b) {
    return a - (int) Math.floor((double) a / b) * b;
  }

  private int arrayIndex(int n) {
    return floorMod(n, _deque.length);
  }
  
  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean isFull() {
    return _head == _tail;
  }

  public E getFirst() throws EmptyDequeException {
    if (isEmpty())
      throw new EmptyDequeException("Deque empty");

    return _deque[arrayIndex(_head + 1)];
  }

  public E getLast() throws EmptyDequeException {
    if (isEmpty())
      throw new EmptyDequeException("Deque empty");

    return _deque[arrayIndex(_tail - 1)];
  }

  public void addFirst(E val) {
    if (isFull())
      throw new FullDequeException("Deque full");
    
    _deque[_head] = val;
    _head = arrayIndex(_head - 1);
    _size++;
  }

  public void addLast(E val) {
    if (isFull())
      throw new FullDequeException("Deque full");
    
    _deque[_tail] = val;
    _tail = arrayIndex(_tail + 1);
    _size++;
  }

  public E removeFirst() throws EmptyDequeException {
    E ret = getFirst();
    _deque[_head] = null;
    _head = arrayIndex(_head + 1);
    _size--;
    return ret;
  }

  public E removeLast() throws EmptyDequeException {
    E ret = getLast();
    _deque[_tail] = null;
    _tail = arrayIndex(_tail - 1);
    _size--;
    return ret;
  }

  public boolean offerFirst(E val) {
    if (isFull())
      return false;

    addFirst(val);
    return true;
  }

  public boolean offerLast(E val) {
    if (isFull())
      return false;

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

    for (int i = arrayIndex(_head + 1); i != _tail; i = arrayIndex(i + 1))
      ret += _deque[i] + ", ";

    return "[" + ret.substring(0, ret.length() - 2) + "]";
  }
  
  public static void main(String[] args) {
    Deque<Integer> d = new ArrayDeque<Integer>();
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
