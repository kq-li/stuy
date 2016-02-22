public class SLinkedList {
  private Node _head, _tail;
  private int _size;

  public SLinkedList() {
    _head = _tail = null;
    _size = 0;
  }

  public void addFirst(String value) {
    _head = new Node(value, _head);
    
    if (_size == 0)
      _tail = _head;
    
    _size++;
  }

  public void addLast(String value) {
    if (_size == 0) {
      _tail = _head = new Node(value, null);
    } else {
      _tail.setNext(new Node(value, null));
      _tail = _tail.getNext();
    }
    
    _size++;
  }

  public String toString() {
    String ret = "";
    
    for (Node node = _head; node != null; node = node.getNext())
      ret += node + " ";
    
    return ret;
  }

  public static void main(String[] args) {
    SLinkedList L = new SLinkedList();
    L.addFirst("Bill");
    System.out.println(L);
    L.addFirst("Mary");
    System.out.println(L);
    L.addLast("Patrick");
    System.out.println(L);
  }
}
  
    
