import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    static final int MAX = (int) 1e9;

    static List<Edge>[] edges;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        edges = new List[n];

        for (int u = 0; u < n; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            edges[flight[0]].add(new Edge(flight[1], flight[2]));
        }

        return dijkstra(src, dst, k);
    }

    int dijkstra(int src, int dst, int k) {
        int[][] dists = new int[edges.length][k + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        for (int[] dist : dists) {
            Arrays.fill(dist, MAX);
        }

        dists[src][0] = 0;
        pq.add(new Node(src, 0, 0));

        while (!pq.isEmpty()) {
            Node u = pq.poll();
            
            if (u.node == dst) {
                return u.cost;
            }

            for (Edge edge : edges[u.node]) {
                if (u.cost + edge.weight >= dists[edge.node][u.count]) {
                    continue;
                }

                dists[edge.node][u.count] = u.cost + edge.weight;

                if (u.count < k && edge.node != dst) {
                    pq.add(new Node(edge.node, u.count + 1, u.cost + edge.weight));
                }
            }
        }

        return Arrays.stream(dists[dst])
                .filter(dist -> dist != MAX)
                .min()
                .orElse(-1);
    }

    static class Edge {

        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

    }

    static class Node {

        int node;
        int count;
        int cost;

        public Node(int node, int count, int cost) {
            this.node = node;
            this.count = count;
            this.cost = cost;
        }

    }

}
