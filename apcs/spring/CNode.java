public class CNode {
  private String _value;
  private CNode _next;

  public CNode(String value, CNode next) {
    _value = value;
    _next = next;
  }

  public String getValue() {
    return _value;
  }

  public String setValue(String value) {
    String ret = _value;
    _value = value;
    return ret;
  }

  public CNode getNext() {
    return _next;
  }

  public CNode setNext(CNode next) {
    CNode ret = _next;
    _next = next;
    return ret;
  }

  public String toString() {
    return _value;
  }
}
