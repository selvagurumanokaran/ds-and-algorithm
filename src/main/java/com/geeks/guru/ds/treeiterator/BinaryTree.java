package com.geeks.guru.ds.treeiterator;

class BinaryTree {

	private Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public Iterator iterator() {
		return new TreeIterator(root);
	}

}
