package com.geeks.guru.ds.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        //BinaryTree.inorderIterative(root);
        printZigZag(root);
    }

    public static void inorderIterative(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            Node curr = root;
            while (!stack.empty() || curr != null) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                System.out.print(curr.data + " ");
                curr = curr.right;
            }
        }
    }

    public static void printZigZag(Node root) {
        if (root != null) {
            boolean ltr = true;
            Deque<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size != 0) {
                    if (ltr) {
                        Node curr = queue.pollFirst();
                        System.out.print(curr.data + " ");
                        if (curr.left != null) queue.offerLast(curr.left);
                        if (curr.right != null) queue.offerLast(curr.right);
                    } else {
                        Node curr = queue.pollLast();
                        System.out.print(curr.data + " ");
                        if (curr.right != null) queue.offerFirst(curr.right);
                        if (curr.left != null) queue.offerFirst(curr.left);
                    }
                    --size;
                }
                ltr = !ltr;
            }
        }
    }


}
