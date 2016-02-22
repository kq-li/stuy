public class Node {
  private String _value;
  private Node _next;

  public Node(String value, Node next) {
    _value = value;
    _next = next;
  }

  public String getValue() {
    return _value;
  }

  public Node getNext() {
    return _next;
  }

  public String setValue(String value) {
    String old = getValue();
    _value = value;
    return old;
  }

  public Node setNext(Node node) {
    Node old = getNext();
    _next = node;
    return old;
  }

  public String toString() {
    return getValue();
  }

  public static void main(String[] args) {
    Node node = new Node("Sue", new Node("Mary", new Node("Bill", null)));
    System.out.println(node);
    System.out.println(node.getNext());
    System.out.println(node.getNext().getNext());
  }
}
  

  
