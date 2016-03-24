public class ScoreList {
  private Node _cursor, _top;
  private int _size;

  public ScoreList() {
    this(10);
  }
  
  public ScoreList(int size) {
    _size = size;
    _cursor = null;
    _top = null;
    
    for (int i = 0; i < size; i++)
      insert(new Node());
  }

  public Node getCursor() {
    return _cursor;
  }

  public void forward() {
    _cursor = _cursor.getNext();
  }

  public void backward() {
    _cursor = _cursor.getPrev();
  }

  public void insert(Node node) {
    if (_cursor == null) {
      node.setNext(node);
      node.setPrev(node);
      _cursor = node;
    } else {
      node.setPrev(_cursor.getNext().setPrev(node));
      node.setNext(_cursor.setNext(node));
    }
  }      
  
  public String toString() {
    String ret = "Rank......Initials..Score\n";
    
    for (int i = 0; i < _size; i++) {
      ret += (i + 1) + Util.duplicate(".", 10 - Integer.toString(i + 1).length()) + _cursor + "\n";
      forward();
    }

    return ret;
  }

  public static void main(String[] args) {
    ScoreList L = new ScoreList();
    System.out.println(L);
  }
}
    
