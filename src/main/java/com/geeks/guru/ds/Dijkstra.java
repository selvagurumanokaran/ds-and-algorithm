package com.geeks.guru.ds;

import java.util.*;

class DijkstraNode {
    public DijkstraNode(int cost, int val) {
        this.cost = cost;
        this.val = val;
    }

    int cost;
    int val;
}


public class Dijkstra {

    Queue<DijkstraNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
    private int[] dist;
    private Set<Integer> done;

    public void findShortestDistance(List<List<DijkstraNode>> graph, int src) {
        dist = new int[graph.size()];
        done = new HashSet<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        queue.offer(new DijkstraNode(0, src));
        while (!queue.isEmpty()) {
            DijkstraNode currNode = queue.poll();
            done.add(currNode.val);
            enqueuAdjNodes(graph, currNode);
        }
        Arrays.stream(dist).forEach(n -> System.out.println(n + " ===> " + dist[n]));
    }

    private void enqueuAdjNodes(List<List<DijkstraNode>> graph, DijkstraNode currNode) {
        List<DijkstraNode> adjNodes = graph.get(currNode.val);
        for (int i = 0; i < adjNodes.size(); i++) {
            DijkstraNode node = adjNodes.get(i);
            if (!done.contains(node.val)) {
                dist[node.val] = Math.min(dist[node.val], node.cost + dist[currNode.val]);
                queue.offer(node);
            }
        }
    }
}
