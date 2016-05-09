public class TreeNode<E> {
  private E _value;
  private TreeNode<E> _left, _right;

  public TreeNode(E value) {
    this(value, null, null);
  }

  public TreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
    _value = value;
    _left = left;
    _right = right;
  }

  public E getValue() {
    return _value;
  }

  public TreeNode<E> getLeft() {
    return _left;
  }

  public TreeNode<E> getRight() {
    return _right;
  }

  public E setValue(E value) {
    E ret = _value;
    _value = value;
    return ret;
  }

  public TreeNode<E> setLeft(TreeNode<E> left) {
    TreeNode<E> ret = _left;
    _left = left;
    return ret;
  }

  public TreeNode<E> setRight(TreeNode<E> right) {
    TreeNode<E> ret = _right;
    _right = right;
    return ret;
  }

  public String toString() {
    return getValue().toString();
  }
}
