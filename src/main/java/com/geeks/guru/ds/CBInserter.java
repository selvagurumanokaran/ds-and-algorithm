package com.geeks.guru.ds;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

//https://leetcode.com/problems/complete-binary-tree-inserter/
class CBTInserter {

    List<TreeNode> list;

    public CBTInserter(TreeNode root) {
        list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currTreeNode = queue.poll();
            if (currTreeNode.left != null) queue.offer(currTreeNode.left);
            if (currTreeNode.right != null) queue.offer(currTreeNode.right);
            list.add(currTreeNode);
        }
    }

    public int insert(int v) {
        TreeNode newTreeNode = new TreeNode(v);
        list.add(newTreeNode);
        int currIndex = list.size() - 1;
        int pi;
        TreeNode parentTreeNode = null;
        ;
        if (currIndex % 2 == 0) {
            pi = (currIndex - 2) / 2;
            if (pi >= 0 && pi < list.size()) {
                parentTreeNode = list.get(pi);
                parentTreeNode.right = newTreeNode;
            }
        } else {
            pi = (currIndex - 1) / 2;
            if (pi >= 0 && pi < list.size()) {
                parentTreeNode = list.get(pi);
                parentTreeNode.left = newTreeNode;
            }
        }
        int[][] array = {{1, 2}, {1, 4}};
        Arrays.stream(array).sorted(Comparator.comparingInt(e -> e[0]));
        Arrays.sort(array, Comparator.<int[]>comparingInt(e -> e[0]).thenComparing(Comparator.<int[]>comparingInt(e -> e[1]).reversed()));
        return parentTreeNode.val;
    }

    public TreeNode get_root() {
        if (list.size() > 0) return list.get(0);
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        //root.right.right = new TreeNode()
        CBTInserter obj = new CBTInserter(root);
        System.out.println(obj.insert(7));
        System.out.println(obj.insert(8));
        System.out.println(obj.get_root());
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
