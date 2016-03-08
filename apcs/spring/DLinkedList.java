public class DLinkedList {
  private int _size;
  private DNode _head, _tail;

  public DLinkedList() {
    _size = 0;
    _head = new DNode(null, null, null);
    _tail = new DNode(null, null, null);
    _head.setNext(_tail);
    _tail.setPrev(_head);
  }

  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return _size == 0;
  }

  public DNode getFirst() {
    if (isEmpty())
      throw new IllegalStateException();
    
    return _head.getNext();
  }

  public DNode getLast() {
    if (isEmpty())
      throw new IllegalStateException();

    return _tail.getPrev();
  }

  public void addFirst(String value) {
    addFirst(new DNode(value, null, null));
  }

  public void addFirst(DNode dnode) {
    addAfter(dnode, _head);
  }

  public void addLast(String value) {
    addLast(new DNode(value, null, null));
  }

  public void addLast(DNode dnode) {
    addBefore(dnode, _tail);
  }

  public void addBefore(DNode dnode, DNode target) {
    if (target == _head)
      throw new IllegalStateException();

    dnode.setPrev(target.setPrev(dnode));
    dnode.setNext(target);
    _size++;
  }

  public void addAfter(DNode dnode, DNode target) {
    if (target == _tail)
      throw new IllegalStateException();

    dnode.setNext(target.setNext(dnode));
    dnode.setPrev(target);
    _size++;
  }

  public String get(int n) {
    if (n < 0 || n >= _size)
      throw new IndexOutOfBoundsException();

    DNode dn = _head.getNext();

    for (int i = 0; i < n; i++)
      dn = dn.getNext();

    return dn.getValue();
  }
  
  public String removeFirst() {
    if (_size == 0)
      throw new IllegalStateException();

    DNode first = _head.getNext();
    remove(first);
    return first.getValue();
  }

  public String removeLast() {
    if (_size == 0)
      throw new IllegalStateException();

    DNode last = _tail.getPrev();
    remove(last);
    return last.getValue();
  }

  public void remove(DNode dnode) {
    if (dnode == _head || dnode == _tail)
      throw new IllegalArgumentException();
    
    dnode.getPrev().setNext(dnode.getNext());
    dnode.getNext().setPrev(dnode.getPrev());
    _size--;
  }
  
  public DNode searchFirst(String value) {
    for (DNode dn =  _head.getNext(); dn != _tail; dn = dn.getNext())
      if (dn.getValue().equals(value))
        return dn;

    return null;
  }

  public DNode searchLast(String value) {
    for (DNode dn =  _tail.getPrev(); dn != _head; dn = dn.getPrev())
      if (dn.getValue().equals(value))
        return dn;

    return null;
  }

  public boolean contains(DNode dnode) {
    for (DNode dn =  _head.getNext(); dn != _tail; dn = dn.getNext())
      if (dn == dnode)
        return true;

    return false;
  }

  public DNode getMiddle() {
    if (_size == 0)
      throw new IllegalStateException();
    
    DNode front = _head;
    DNode back = _tail;

    while (front != back && front != back.getPrev()) {
      front = front.getNext();
      back = back.getPrev();
    }

    return back;
  }

  public void swap(DNode a, DNode b) {
    a.setPrev(b.setPrev(a.getPrev()));
    a.setNext(b.setNext(a.getNext()));
  }
  
  public String toString() {
    String ret = "";

    for (DNode dn =  _head.getNext(); dn != _tail; dn = dn.getNext())
      ret += dn + ", ";

    if (ret.length() > 0)
      ret = ret.substring(0, ret.length() - 2);
    return "[" + ret + "]";
  }

  public static void main(String[] args) {
    DLinkedList L = new DLinkedList();
    System.out.println(L);
    L.addLast("a");
    L.addLast("b");
    L.addLast("c");
    L.addLast("d");
    System.out.println(L);
    L.addFirst("z");
    L.addFirst("y");
    L.addFirst("x");
    L.addFirst("w");
    System.out.println(L);
    L.addLast("w");
    L.addLast("x");
    L.addLast("y");
    L.addLast("z");
    System.out.println(L);
    L.removeFirst();
    L.removeLast();
    System.out.println(L);
    System.out.println(L.get(3));
    System.out.println(L.getMiddle());
    L.swap(L.searchFirst("y"), L.searchLast("a"));
    System.out.println(L);
  }
}
  
