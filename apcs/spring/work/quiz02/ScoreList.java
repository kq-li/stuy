public class ScoreList {
  private Node _cursor, _top;
  private int _size;

  public ScoreList() {
    this(10);
  }
  
  public ScoreList(int size) {
    _size = size;
    _cursor = new Node();
    _cursor.setNext(_cursor);
    _cursor.setPrev(_cursor);
    _top = _cursor;
    
    for (int i = 0; i < size - 1; i++) {
      Node node = new Node();
      node.setPrev(_cursor.getNext().setPrev(node));
      node.setNext(_cursor.setNext(node));
    }      
  }

  public int getSize() {
    return _size;
  }
  
  public Node getCursor() {
    return _cursor;
  }

  public Node setCursor(Node node) {
    Node cursor = _cursor;
    node.setNext(cursor.setNext(null));
    node.setPrev(cursor.setPrev(null));
    _cursor = node;
    return cursor;
  }
  
  public void forward() {
    _cursor = _cursor.getNext();
  }

  public void backward() {
    _cursor = _cursor.getPrev();
  }

  public boolean add(Entry entry) {
    _cursor = _top;

    for (int i = 0; i < _size; i++) {
      if (entry.compareTo(_cursor.getEntry()) > 0) {
        for (Node node = _top.getPrev(); node != _cursor; node = node.getPrev())
          node.setEntry(node.getPrev().getEntry());

        _cursor.setEntry(entry);
        return true;
      }
      
      forward();
    }
    
    return false;
  }
  
  public String toString() {
    String ret = "Rank......Initials..Score\n";
    Node oldCursor = _cursor;
    _cursor = _top;
    
    for (int i = 0; i < _size; i++) {
      ret += (i + 1) + Util.duplicate(".", 10 - Integer.toString(i + 1).length()) + _cursor + "\n";
      forward();
    }

    _cursor = oldCursor;
    return ret;
  }

  public static void main(String[] args) {
    ScoreList L = new ScoreList();
    System.out.println(L);
    
    for (int i = 0; i < L.getSize(); i++) {
      Entry e = Util.randomEntry(3, 10000);
      System.out.println(e + " added.");
      L.add(e);
    }
    
    System.out.println();
    System.out.println(L);

    for (int i = 0; i < L.getSize(); i++) {
      Entry e = Util.randomEntry(3, 10000);
      if (L.add(e))
        System.out.println(e + " added.");
      else
        System.out.println(e + " not added.");
    }

    System.out.println();
    System.out.println(L);
  }
}
    
