package com.geeks.guru.ds.treeiterator;

import java.util.Stack;

public class TreeIterator implements Iterator {

	private Node curr;
	private Stack<Node> stack;

	public TreeIterator(Node root) {
		curr = root;
		stack = new Stack<>();
		while (curr != null) {
			stack.push(curr);
			curr = curr.left;
		}
	}

	@Override
	public int next() {
		Node temp;
		temp = curr = stack.pop();
		curr = curr.right;
		while (curr != null) {
			stack.push(curr);
			curr = curr.left;
		}
		return temp.data;
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

}
