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
    E ans = getValue();
    _value = value;
    return ans;
  }

  public TreeNode<E> setLeft(TreeNode<E> left) {
    TreeNode<E> ans = getLeft();
    _left = left;
    return ans;
  }


  public TreeNode<E> setRight(TreeNode<E> right) {
    TreeNode<E> ans = getRight();
    _right = right;
    return ans;
  }

  public String toString() {
    return getValue().toString();
  }
}
