package com.geeks.guru.ds.treeiterator;

public class TreeInorderIteratorTest {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(20);
		root.left.left = new Node(8);
		root.left.right = new Node(22);
		root.left.left.left = new Node(4);
		root.left.left.right = new Node(12);
		root.left.left.right.left = new Node(10);
		root.left.left.right.right = new Node(14);
		BinaryTree tree = new BinaryTree(root);
		Iterator iterator = tree.iterator();
		while (iterator.hasNext()) {
			System.out.print(" " + iterator.next());
		}
	}

}
