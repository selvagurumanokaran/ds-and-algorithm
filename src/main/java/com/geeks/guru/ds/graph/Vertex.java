package com.geeks.guru.ds.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private int value;
	private List<Vertex> allAdjVertex;

	public Vertex(int value) {
		this.value = value;
		this.allAdjVertex = new ArrayList<>();
	}

	public void addAdjVertex(Vertex v) {
		if (!this.allAdjVertex.contains(v))
			this.allAdjVertex.add(v);
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public List<Vertex> getAllAdjVertex() {
		return allAdjVertex;
	}

}
