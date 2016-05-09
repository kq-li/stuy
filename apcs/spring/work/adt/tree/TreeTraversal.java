import java.util.ArrayList;

public class TreeTraversal {
  public static<E> void inorder(TreeNode<E> root) {
    if (root == null)
      return;

    inorder(root.getLeft());
    System.out.print(root + " ");
    inorder(root.getRight());
  }

  public static<E> void preorder(TreeNode<E> root) {
    if (root == null)
      return;

    System.out.print(root + " ");
    preorder(root.getLeft());
    preorder(root.getRight());
  }

  public static<E> void postorder(TreeNode<E> root) {
    if (root == null)
      return;

    postorder(root.getLeft());
    postorder(root.getRight());
    System.out.print(root + " ");
  }

  public static<E> int countNodes(TreeNode<E> root) {
    if (root == null)
      return 0;
    
    int left = 0;
    int right = 0;

    if (root.getLeft() != null)
      left = countNodes(root.getLeft());
    
    if (root.getRight() != null)
      right = countNodes(root.getRight());

    return left + right + 1;
  }

  public static<E> boolean isLeaf(TreeNode<E> node) {
    return (node != null) && (node.getLeft() == null) && (node.getRight() == null);
  }

  public static<E> int getHeight(TreeNode<E> root) {
    if (root == null || isLeaf(root))
      return 0;
    
    return Math.max(1 + getHeight(root.getLeft()),
                    1 + getHeight(root.getRight()));
  }

  public static<E> int countLeaves(TreeNode<E> root) {
    if (root == null)
      return 0;

    if (isLeaf(root))
      return 1;

    int leaves = 0;

    if (root.getLeft() != null)
      leaves += countLeaves(root.getLeft());

    if (root.getRight() != null)
      leaves += countLeaves(root.getRight());

    return leaves;
  }
  
  public static void main(String[] args) {
    ArrayList<TreeNode<Integer>> nodes = new ArrayList<TreeNode<Integer>>();

    for (int i = 0; i < 8; i++)
      nodes.add(new TreeNode<Integer>(i + 1));

    nodes.get(0).setLeft(nodes.get(1));
    nodes.get(0).setRight(nodes.get(2));
    nodes.get(1).setRight(nodes.get(3));
    nodes.get(2).setLeft(nodes.get(4));
    nodes.get(2).setRight(nodes.get(5));
    nodes.get(4).setLeft(nodes.get(6));
    nodes.get(4).setRight(nodes.get(7));

    TreeNode<Integer> root = nodes.get(0);
    
    inorder(root);
    System.out.println();
    preorder(root);
    System.out.println();
    postorder(root);
    System.out.println();

    System.out.println(countNodes(root));
    System.out.println(getHeight(root));
  }
}
