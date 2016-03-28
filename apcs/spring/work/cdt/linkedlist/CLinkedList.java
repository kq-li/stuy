public class CLinkedList {
  private CNode _cursor;
  private int _size;

  public CLinkedList() {
    _cursor = null;
    _size = 0;
  }

  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return _size == 0;
  }

  public CNode getCursor() {
    return _cursor;
  }

  public void add(String value) {
    add(new CNode(value, null));
  }
  
  public void add(CNode cn) {
    if (isEmpty()) {
      cn.setNext(cn);
      _cursor = cn;
    } else {
      cn.setNext(_cursor.getNext());
      _cursor.setNext(cn);
    }

    _size++;
  }

  public void advance() {
    _cursor = _cursor.getNext();
  }

  public CNode remove() {
    CNode ret = _cursor.getNext();
    _cursor.setNext(ret.setNext(null));
    _size--;
    return ret;
  }
  
  public String toString() {
    if (isEmpty())
      return "[]";
    
    String ret = _cursor + ", ";

    for (CNode cn = _cursor.getNext(); cn != _cursor; cn = cn.getNext())
      ret += cn + ", ";

    return "[" + ret.substring(0, ret.length() - 2) + "]";
  }
  
  public static void main(String[] args) {
    CLinkedList L = new CLinkedList();
    System.out.println(L);
    L.add(new CNode("A", null));
    L.add(new CNode("B", null));
    L.add(new CNode("C", null));
    System.out.println(L);
    L.advance();
    System.out.println(L);
    L.remove();
    System.out.println(L);
  }
}
