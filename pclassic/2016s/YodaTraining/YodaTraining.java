import java.io.File;
import java.util.Scanner;

public class YodaTraining {
  static class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
      this.value = value;
    }
		
    TreeNode() {
			
    }
  }

  //Fill out the body of this method
  public static Integer smallestLargerWeapon(TreeNode root, int k) {
    Integer leftAns = null;
    Integer rightAns = null;

    if (root.left != null)
      leftAns = smallestLargerWeapon(root.left, k);

    if (root.right != null)
      rightAns = smallestLargerWeapon(root.right, k);

    if (leftAns == null && rightAns == null)
      if (root.value > k)
        return root.value;
      else
        return null;
    else if (leftAns != null)
      return leftAns;
    else if (root.value > k)
      return root.value;
    else
      return rightAns;
  }

  public static void testCase(int num_nodes, int[] k_values, Scanner fileInput) {

    TreeNode[] id_to_node = new TreeNode[num_nodes + 1];
    for(int i = 0; i < num_nodes; i++) {

      String line = fileInput.nextLine();
      String[] parts = line.split("\\s+");
      int uid = Integer.parseInt(parts[0].substring(0, parts[0].length() - 1));
      int value = Integer.parseInt(parts[1]);
      int left_id = Integer.parseInt(parts[2]);
      int right_id = Integer.parseInt(parts[3]);
			
      if(id_to_node[uid] != null) {
        id_to_node[uid].value = value;
      } else {
        TreeNode node = new TreeNode(value);
        id_to_node[uid] = node;
      }

      if(left_id == -1) {
        id_to_node[uid].left = null;
      } else if(id_to_node[left_id] != null) {
        id_to_node[uid].left = id_to_node[left_id];
      } else {
        id_to_node[uid].left = new TreeNode();
        id_to_node[left_id] = id_to_node[uid].left;
      }

      if(right_id == -1) {
        id_to_node[uid].right = null;
      } else if(id_to_node[right_id] != null) {
        id_to_node[uid].right = id_to_node[right_id];
      } else {
        id_to_node[uid].right = new TreeNode();
        id_to_node[right_id] = id_to_node[uid].right;
      }


    }

    TreeNode root = id_to_node[1];

    for(int k: k_values) {
      System.out.println(smallestLargerWeapon(root, k));
    }
  }

  public static void main(String[] args) throws Exception {

    Scanner fileInput = new Scanner(new File("YodaTrainingIN.txt"));

    while(fileInput.hasNext()) {

      int num_nodes = Integer.parseInt(fileInput.nextLine());
      String[] k_values_string = fileInput.nextLine().split("\\s+");
      int[] k_values = new int[k_values_string.length];
      for(int i = 0; i < k_values_string.length; i++) {
        k_values[i] = Integer.parseInt(k_values_string[i]);
      }

      testCase(num_nodes, k_values, fileInput);
	            
      if(fileInput.hasNext()) {
        String pound = fileInput.nextLine();
        if(pound == "") {
          break;
        }
        fileInput.nextLine();
        fileInput.nextLine();
      }

    }
    fileInput.close();
  }
}
