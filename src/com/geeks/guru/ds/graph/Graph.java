package com.geeks.guru.ds.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private Map<Integer, Vertex> allVertex;
	private List<Edge> allEdges;

	public Graph() {
		allVertex = new HashMap<>();
		allEdges = new ArrayList<>();
	}

	public void addEdge(int value1, int value2, int weight) {
		Vertex v1 = allVertex.get(value1);
		if (v1 == null) {
			v1 = new Vertex(value1);
			allVertex.put(value1, v1);
		}

		Vertex v2 = allVertex.get(value2);
		if (v2 == null) {
			v2 = new Vertex(value2);
			allVertex.put(value2, v2);
		}

		v1.addAdjVertex(v2);
		Edge edge = new Edge(v1, v2, weight);
		if (!allEdges.contains(edge))
			allEdges.add(edge);
	}

	public Collection<Vertex> getAllVertex() {
		return allVertex.values();
	}

	public List<Edge> getAllEdges() {
		return allEdges;
	}
}
