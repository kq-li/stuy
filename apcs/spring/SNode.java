public class SNode {
  private String _value;
  private SNode _next;

  public SNode(String value, SNode next) {
    _value = value;
    _next = next;
  }

  public String getValue() {
    return _value;
  }

  public SNode getNext() {
    return _next;
  }

  public String setValue(String value) {
    String old = getValue();
    _value = value;
    return old;
  }

  public SNode setNext(SNode snode) {
    SNode old = getNext();
    _next = snode;
    return old;
  }

  public String toString() {
    return getValue();
  }

  public static void main(String[] args) {
    SNode snode = new SNode("Sue", new SNode("Mary", new SNode("Bill", null)));
    System.out.println(snode);
    System.out.println(snode.getNext());
    System.out.println(snode.getNext().getNext());
  }
}
  

  
