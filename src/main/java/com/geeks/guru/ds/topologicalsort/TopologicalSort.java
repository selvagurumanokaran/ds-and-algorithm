package com.geeks.guru.ds.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.geeks.guru.ds.graph.Graph;
import com.geeks.guru.ds.graph.Vertex;

public class TopologicalSort {

	public void sort(Graph graph) {
		List<Vertex> visited = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>();
		for (Vertex vertex : graph.getAllVertex()) {
			if (!visited.contains(vertex)) {
				sortUtil(vertex, queue, visited);
			}
		}
		while (!queue.isEmpty()) {
			System.out.print(queue.poll().getValue() + " ");

		}
	}

	private void sortUtil(Vertex vertex, Queue<Vertex> queue, List<Vertex> visited) {
		visited.add(vertex);
		for (Vertex childVertex : vertex.getAllAdjVertex()) {
			if (!visited.contains(childVertex)) {
				sortUtil(childVertex, queue, visited);
			}
		}
		queue.offer(vertex);
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addEdge(1, 20, 0);
		graph.addEdge(20, 9, 0);
		graph.addEdge(20, 22, 0);
		graph.addEdge(9, 12, 0);
		graph.addEdge(22, 12, 0);
		graph.addEdge(9, 4, 0);
		graph.addEdge(4, 10, 0);
		graph.addEdge(9, 12, 0);
		graph.addEdge(12, 10, 0);
		graph.addEdge(12, 14, 0);

		new TopologicalSort().sort(graph);

	}
}
