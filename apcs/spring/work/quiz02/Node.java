public class Node {
  private Entry _entry;
  private Node _prev, _next;

  public Node() {
    this(new Entry(), null, null);
  }
  
  public Node(Entry entry, Node prev, Node next) {
    _entry = entry;
    _prev = prev;
    _next = next;
  }

  public Entry getEntry() {
    return _entry;
  }

  public Node getPrev() {
    return _prev;
  }

  public Node getNext() {
    return _next;
  }

  public Entry setEntry(Entry e) {
    Entry ret = _entry;
    _entry = e;
    return ret;
  }

  public Node setPrev(Node prev) {
    Node ret = _prev;
    _prev = prev;
    return ret;
  }

  public Node setNext(Node next) {
    Node ret = _next;
    _next = next;
    return ret;
  }

  public String toString() {
    return _entry.toString();
  }

  public static void main(String[] args) {
    Node a = new Node(new Entry(), null, null);
    Node b = new Node(new Entry("AZR", 1337), null, null);
    System.out.println(a);
    System.out.println(b);
    a.setNext(b);
    b.setPrev(a);
    System.out.println(a.getNext());
    System.out.println(b.getPrev());
  }
}
