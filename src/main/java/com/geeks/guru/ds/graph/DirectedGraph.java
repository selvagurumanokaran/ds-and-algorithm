package com.geeks.guru.ds.graph;

import java.util.*;
import java.util.stream.Collectors;

class DirectedGraph {

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(4, 5);
        graph.addEdge(1, 3);
        graph.addEdge(3, 5);
        graph.addEdge(2, 4);
        graph.addEdge(1, 2);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(5, 7);
        BFS(graph);
        System.out.println();
        DFS(graph);
    }

    Set<Edge> edges;
    Map<Long, Vertex> vertices;

    DirectedGraph() {
        edges = new HashSet<>();
        vertices = new HashMap<>();
    }

    public Set<Vertex> getVertices() {
        return vertices.values().stream().collect(Collectors.toSet());
    }

    public void addEdge(long n1, long n2) {
        Vertex start = vertices.get(n1);
        if (start == null) {
            start = new Vertex(n1);
            vertices.put(n1, start);
        }
        Vertex end = vertices.get(n2);
        if (end == null) {
            end = new Vertex(n2);
            vertices.put(n2, end);
        }
        start.addAdjacentVertex(end);
        Edge edge = new Edge(start, end);
        edges.add(edge);
    }

    class Vertex {
        Long n;
        Set<Vertex> adjacentVertices;

        Vertex(long n) {
            this.n = n;
            adjacentVertices = new HashSet<>();
        }

        public void addAdjacentVertex(Vertex v1) {
            adjacentVertices.add(v1);
        }

        public Long getData() {
            return n;
        }

        public Set<Vertex> getAdjacentVertices() {
            return adjacentVertices;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            if (n != null ? !n.equals(vertex.n) : vertex.n != null) return false;
            return adjacentVertices != null ? adjacentVertices.equals(vertex.adjacentVertices) : vertex.adjacentVertices == null;
        }

        @Override
        public int hashCode() {
            int result = n != null ? n.hashCode() : 0;
            result = 31 * result + (adjacentVertices != null ? adjacentVertices.hashCode() : 0);
            return result;
        }
    }

    class Edge {
        Vertex start;
        Vertex end;

        Edge(Vertex start, Vertex end) {
            start = start;
            end = end;
        }
    }

    //  Graph BFS
    public static void BFS(DirectedGraph graph) {
        Set<Vertex> vertices = graph.getVertices();
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        for (Vertex v : vertices) {
            if (visited.contains(v)) continue;
            queue.offer(v);
            while (!queue.isEmpty()) {
                Vertex v1 = queue.poll();
                visited.add(v1);
                System.out.print(v1.getData() + " ");
                for (Vertex v2 : v1.getAdjacentVertices()) {
                    if (visited.contains(v2)) continue;
                    queue.offer(v2);
                }
            }
        }

    }

    public static void DFS(DirectedGraph graph) {
        Set<Vertex> vertices = graph.getVertices();
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        for (Vertex v : vertices) {
            if (visited.contains(v)) continue;
            ;
            stack.push(v);
            while (!stack.empty()) {
                Vertex v1 = stack.pop();
                visited.add(v1);
                System.out.print(v1.getData() + " ");
                for (Vertex v2 : v1.getAdjacentVertices()) {
                    if (visited.contains(v2)) continue;
                    stack.push(v2);
                }
            }
        }
    }

}

