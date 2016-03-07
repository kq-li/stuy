public class DNode {
  private String _value;
  private DNode _prev, _next;

  public DNode(String value, DNode prev, DNode next) {
    _value = value;
    _prev = prev;
    _next = next;
  }

  public String getValue() {
    return _value;
  }

  public DNode getPrev() {
    return _prev;
  }

  public DNode getNext() {
    return _next;
  }

  public String setValue(String value) {
    String ret = _value;
    _value = value;
    return ret;
  }
  
  public DNode setPrev(DNode newPrev) {
    DNode ret = _prev;
    _prev = newPrev;

    if (_prev.getNext() != this)
      _prev.setNext(this);

    return ret;
  }

  public DNode setNext(DNode newNext) {
    DNode ret = _next;
    _next = newNext;

    if (_next.getPrev() != this)
      _next.setPrev(this);

    return ret;
  }
    
  public String toString() {
    return _value;
  }

  public static void main(String[] args) {
    DNode dnode = new DNode("Amy", null, null);
    System.out.println(dnode.getPrev() + " " + dnode + " " + dnode.getNext());
    System.out.println(dnode.setPrev(new DNode("Bill", null, null)));
    System.out.println(dnode.setNext(new DNode("Gary", null, null)));
    System.out.println(dnode.getPrev() + " " + dnode + " " + dnode.getNext());
    System.out.println(dnode.setPrev(new DNode("Bob", null, null)));
    System.out.println(dnode.setNext(new DNode("Guy", null, null)));
    System.out.println(dnode.getPrev() + " " + dnode + " " + dnode.getNext());
    dnode.getNext().setValue(dnode.getPrev().setValue(dnode.getNext().getValue()));
    System.out.println(dnode.getPrev() + " " + dnode + " " + dnode.getNext());
  }
}

