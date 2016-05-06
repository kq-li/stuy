public class BST<E extends Comparable> {
  private TreeNode<E> _root;
  private int _size;

  public BST() {
    _root = null;
    _size = 0;
  }

  public void insert(E x) {
    insertNode(new TreeNode<E>(x));
  }

  private void insertNode(TreeNode<E> x) {
    if (_root == null) {
      _root = x;
    } else if (x.compareTo(_root) < 0) {
      TreeNode<E> root = _root;
      _root = _root.getLeft();
      insertNode(x);
      _root = root;
    } else {
      TreeNode<E> root = _root;
      _root = _root.getRight();
      insertNode(x);
      _root = root;
    }      
  }
}
