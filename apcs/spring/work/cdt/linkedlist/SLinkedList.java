public class SLinkedList {
  private SNode _head, _tail;
  private int _size;

  public SLinkedList() {
    _head = _tail = null;
    _size = 0;
  }

  // O(1)
  public void addFirst(String value) {
    _head = new SNode(value, _head);
    
    if (_size == 0)
      _tail = _head;
    
    _size++;
  }

  // O(1)
  public void addLast(String value) {
    if (_size == 0) {
      _tail = _head = new SNode(value, null);
    } else {
      _tail.setNext(new SNode(value, null));
      _tail = _tail.getNext();
    }
    
    _size++;
  }
  
  // O(1)
  public int size() {
    return _size;
  }

  // O(1)
  public String removeFirst() {
    if (_size == 0)
      throw new IllegalStateException();

    String ret = _head.getValue();
    
    if (_size == 1) {
      _head = _tail = null;
    } else {
      _head = _head.getNext();
    }

    _size--;
    return ret;
  }

  // O(1)
  public String removeLast() {
    if (_size == 0)
      throw new IllegalStateException();

    String ret = _tail.getValue();

    if (_size == 1) {
      _head = _tail = null;
    } else {
      SNode snode = _head.getNext();

      while (snode.getNext() != _tail)
        snode = snode.getNext();

      _tail = snode;
      _tail.setNext(null);
    }

    _size--;
    return ret;
  }

  // O(1)
  public String getFirst() {
    if (_size == 0)
      throw new IllegalStateException();
    return _head.getValue();
  }

  // O(1)
  public String getLast() {
    if (_size == 0)
      throw new IllegalStateException();
    return _tail.getValue();
  }

  // O(n)
  public String get(int i) {
    if (i < 0 || i >= _size)
      throw new IndexOutOfBoundsException();
    SNode snode = _head;

    for (int j = 0; j < i; j++)
      snode = snode.getNext();

    return snode.getValue();
  }

  // O(n)
  public SLinkedList append(SLinkedList L) {
    SLinkedList ret = new SLinkedList();
    for (SNode snode = _head; snode != null; snode = snode.getNext())
      ret.addLast(snode.getValue());
    
    for (SNode snode = L._head; snode != null; snode = snode.getNext())
      ret.addLast(snode.getValue());

    return ret;
  }

  // O(n)
  public void reverse() {
    if (_size > 1) {
      SNode snode = _head;
      _head = _head.getNext();
      _size--;
      reverse();
      _tail.setNext(snode);
      _tail = snode;
      _tail.setNext(null);
      _size++;
    }
  }

  // O(n)
  public SNode search(String key) {
    SNode snode = _head;
    
    while (snode != null) {
      if (snode.getValue().equals(key))
        return snode;

      snode = snode.getNext();
    }

    return null;
  }    
  
  public void swap(SNode x, SNode y) {
    if (x == y)
      return;
        
    SNode xPrev = new SNode(null, _head);
    SNode yPrev = new SNode(null, _head);
    SNode xNext = x.getNext();
    SNode yNext = y.getNext();

    while (xPrev.getNext() != x)
      xPrev = xPrev.getNext();
    
    while (yPrev.getNext() != y)
      yPrev = yPrev.getNext();

    System.out.println(xPrev + " " + x + " " + xNext + "  " + yPrev + " " + y + " " + yNext);

    if (_head == x)
      _head = y;

    if (_head == y)
      _head = x;

    if (_tail == x)
      _tail = y;

    if (_tail == y)
      _tail = x;
    
    x.setNext(yNext);
    y.setNext(xNext);
    xPrev.setNext(y);
    yPrev.setNext(x);
  }
  
  public String toString() {
    String ret = "";
    
    for (SNode snode = _head; snode != null; snode = snode.getNext())
      ret += snode + " ";
    
    return ret;
  }

  public static void main(String[] args) {
    SLinkedList L = new SLinkedList();
    SLinkedList M = new SLinkedList();

    System.out.println(L);
    L.reverse();
    System.out.println(L);
    L.addLast("a");
    System.out.println(L);
    L.reverse();
    System.out.println(L);
    L.addLast("b");
    System.out.println(L);
    L.reverse();
    System.out.println(L);
    
    M.addLast("a");
    M.addLast("b");
    M.addLast("c");
    M.addLast("d");
    M.addLast("e");
    System.out.println(M);
    M.reverse();
    System.out.println(M);

    System.out.println(M.search("a"));

    System.out.println(M);
    M.swap(M.search("b"), M.search("e"));
    System.out.println(M);
  }
}
  
    
