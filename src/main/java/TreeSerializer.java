import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TreeSerializer {

    public String serialize(TreeNode root) {
        StringBuilder preOrderResult = preOrder(root);
        StringBuilder inOrderResult = inorder(root);
        return preOrderResult.append("#").append(inOrderResult).toString();
    }

    private StringBuilder preOrder(TreeNode root) {
        StringBuilder result = new StringBuilder();
        preOrderUtil(root, result);
        return result;
    }

    private void preOrderUtil(TreeNode root, StringBuilder result) {
        if (root == null) return;
        result.append(root.val);
        preOrderUtil(root.left, result);
        preOrderUtil(root.right, result);
    }

    private StringBuilder inorder(TreeNode root) {
        StringBuilder result = new StringBuilder();
        inorderUtil(root, result);
        return result;
    }

    private void inorderUtil(TreeNode root, StringBuilder result) {
        if (root == null) return;
        inorderUtil(root.left, result);
        result.append(root.val);
        inorderUtil(root.right, result);
    }

    public TreeNode deserialize(String data) {
        String[] traversals = data.split("#");
        return constructTree(traversals[0], traversals[1], 0, 0, traversals[1].length() - 1);
    }

    private TreeNode constructTree(String preorder, String inorder, int preOrderIndex, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) return null;
        if (inorderStart == inorderEnd) {
            return new TreeNode(inorder.charAt(inorderEnd));
        }
        char character = preorder.charAt(preOrderIndex);
        int inorderIndex = findInorderIndex(inorder, character, inorderStart, inorderEnd);
        TreeNode root = new TreeNode(character);
        root.left = constructTree(preorder, inorder, preOrderIndex + 1, inorderStart, inorderIndex - 1);
        root.right = constructTree(preorder, inorder, preOrderIndex + inorderIndex - inorderStart + 1, inorderIndex + 1, inorderEnd);
        return root;
    }

    private int findInorderIndex(String inorder, char c, int inorderStart, int inorderEnd) {
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder.charAt(i) == c) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeSerializer treeSerializer = new TreeSerializer();
        TreeNode root = treeSerializer.deserialize("12#12");
        Map<String, Integer> map = new HashMap<>();
        System.out.println(root);
    }

}
