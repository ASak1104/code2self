import java.io.*;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class Main {
    private static final int MAX_VALUE = (int) 1e9;

    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = nextInt(st);
        int m = nextInt(st);

        while (n > 0) {
            int s = nextInt(st);
            int e = nextInt(st);

            Graph graph = new Graph(n, s, e);

            for (int i = 0; i < m; i++) {
                int u = nextInt(st);
                int v = nextInt(st);
                int w = nextInt(st);

                graph.addEdge(u, v, w);
            }

            graph.removeShortestPaths();

            bw.write(graph.getDistance() + "\n");

            n = nextInt(st);
            m = nextInt(st);
        }

        bw.flush();
        bw.close();
    }

    private static int nextInt(StreamTokenizer streamTokenizer) throws IOException {
        streamTokenizer.nextToken();
        return (int) streamTokenizer.nval;
    }

    private static class Graph {
        int n;
        int s;
        int e;
        int[][] edges;
        ArrayList<Integer>[] revEdges;

        Graph(int n, int s, int e) {
            this.n = n;
            this.s = s;
            this.e = e;
            this.edges = new int[n][n];
            this.revEdges = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                revEdges[i] = new ArrayList<>();
            }
        }

        void addEdge(int u, int v, int w) {
            edges[u][v] = w;
        }

        void removeShortestPaths() {
            getDistance();

            boolean[] visited = new boolean[n];
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            visited[e] = true;
            queue.addLast(e);

            while (!queue.isEmpty()) {
                int v = queue.removeFirst();

                for (int u : revEdges[v]) {
                    edges[u][v] = 0;

                    if (!visited[u]) {
                        visited[u] = true;
                        queue.addLast(u);
                    }
                }
            }
        }

        int getDistance() {
            PriorityQueue<Node> pq = new PriorityQueue<>(comparingInt(Node::getD));
            int[] dists = new int[n];

            pq.offer(new Node(s, 0));
            Arrays.fill(dists, MAX_VALUE);
            dists[s] = 0;

            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                int u = curr.getU();
                int d = curr.getD();

                for (int v = 0; v < n; v++) {
                    if (edges[u][v] == 0) continue;

                    int w = d + edges[u][v];

                    if (dists[v] < w) continue;

                    if (dists[v] != w) {
                        dists[v] = w;
                        pq.add(new Node(v, w));
                        revEdges[v].clear();
                    }

                    revEdges[v].add(u);
                }
            }

            return dists[e] < MAX_VALUE ? dists[e] : -1;
        }

        private static class Node {
            int u;
            int d;

            Node(int u, int d) {
                this.u = u;
                this.d = d;
            }

            public int getU() {
                return u;
            }

            public int getD() {
                return d;
            }
        }
    }
}