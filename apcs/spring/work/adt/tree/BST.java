import java.util.ArrayList;

public class BST<E extends Comparable> {
  private TreeNode<E> _root;
  private int _size;

  public BST() {
    _root = null;
    _size = 0;
  }

  private boolean isLeaf(TreeNode<E> node) {
    return (node != null) && (node.getLeft() == null) && (node.getRight() == null);
  }

  public int height() {
    return height(_root);
  }
  
  private int height(TreeNode<E> root) {
    if (root == null || isLeaf(root))
      return 0;
    
    return Math.max(1 + height(root.getLeft()),
                    1 + height(root.getRight()));
  }

  public void insert(E e) {
    insertR(e);
  }
  
  private void insertI(E e) {
    TreeNode<E> node = new TreeNode<E>(e);

    if (_root == null) {
      _root = node;
    } else {
      TreeNode<E> prev = _root;
      TreeNode<E> cur = _root;

      while (cur != null) {
        prev = cur;

        if (e.compareTo(cur.getValue()) < 0)
          cur = cur.getLeft();
        else
          cur = cur.getRight();
      }

      if (e.compareTo(prev.getValue()) < 0)
        prev.setLeft(node);
      else
        prev.setRight(node);
    }

    _size++;    
  }

  private void insertR(E e) {
    _root = insertR(_root, new TreeNode<E>(e));
    _size++;
  }
  
  private TreeNode<E> insertR(TreeNode<E> root, TreeNode<E> node) {
    if (root == null)
      return node;

    if (node.getValue().compareTo(root.getValue()) < 0)
      root.setLeft(insertR(root.getLeft(), node));
    else
      root.setRight(insertR(root.getRight(), node));

    return root;
  }

  public TreeNode<E> find(E e) {
    return findI(e);
  }

  private TreeNode<E> findI(E e) {
    TreeNode<E> cur = _root;

    while (cur != null && !e.equals(cur.getValue())) {
      if (e.compareTo(cur.getValue()) < 0)
        cur = cur.getLeft();
      else
        cur = cur.getRight();
    }

    return cur;
  }

  private TreeNode<E> findR(TreeNode<E> root, E e) {
    if (root == null || e.equals(root.getValue()))
      return root;
    
    if (e.compareTo(root.getValue()) < 0)
      return findR(root.getLeft(), e);
    else
      return findR(root.getRight(), e);
  }

  public boolean isFound(E e) {
    return find(e) != null;
  }

  public TreeNode<E> maxNode() {
    return maxNode(_root);
  }

  private TreeNode<E> maxNode(TreeNode<E> root) {
    if (root == null || root.getRight() == null)
      return root;

    return maxNode(root.getRight());
  }
  
  public E maxValue() throws IllegalStateException {
    if (maxNode() == null)
      throw new IllegalStateException();

    return maxNode().getValue();
  }

  public TreeNode<E> minNode() {
    return minNode(_root);
  }

  private TreeNode<E> minNode(TreeNode<E> root) {
    if (root == null || root.getLeft() == null)
      return root;

    return minNode(root.getLeft());
  }

  public E minValue() throws IllegalStateException {
    if (minNode() == null)
      throw new IllegalStateException();

    return minNode().getValue();
  }
  
  public boolean isBalanced() {
    return isBalanced(_root);
  }

  private boolean isBalanced(TreeNode<E> root) {
    if (height(root) < 2)
      return true;

    if (height(root) == 2)
      return root.getLeft() != null && root.getRight() != null;

    return isBalanced(root.getLeft()) && isBalanced(root.getRight());
  }

  public ArrayList<TreeNode<E>> nodesAtHeight(int height) {
    return nodesAtHeight(_root, height);
  }
  
  private ArrayList<TreeNode<E>> nodesAtHeight(TreeNode<E> root, int height) {
    ArrayList<TreeNode<E>> ret = new ArrayList<TreeNode<E>>();

    if (root == null)
      return ret;

    if (height == 0) {
      ret.add(root);
    } else {
      for (TreeNode<E> node : nodesAtHeight(root.getLeft(), height - 1))
        ret.add(node);
      
      for (TreeNode<E> node : nodesAtHeight(root.getRight(), height - 1))
        ret.add(node);
    }

    return ret;
  }
  
  private String multiply(String s, int n) {
    String ret = "";

    for (int i = 0; i < n; i++)
      ret += s;

    return ret;
  }
  
  public String toString() {
    String ret = "";

    for (int i = 0; i <= height(); i++)
      ret += nodesAtHeight(i) + "\n";

    return ret;
  }
  
  public static void main(String[] args) {
    BST<Integer> bst = new BST<Integer>();
    int[] nodes = {12, 10, 8, 11, 25, 17, 5, 25};

    for (int i : nodes)
      bst.insert(i);

    System.out.println(bst);
    System.out.println(bst.isBalanced());
    System.out.println(bst.minValue());
    System.out.println(bst.maxValue());
    System.out.println(bst.find(12));
  }
}
