import java.io.*;
import java.util.*;

class Main {
    static List<Node>[] tree;
    static boolean[] vis;
    static int maxWeight = 0;
    static int x = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());

        tree = new List[v + 1];
        vis = new boolean[v + 1];

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            tree[s] = new ArrayList<>();

            int node = Integer.parseInt(st.nextToken());

            while (node > -1) {
                int weight = Integer.parseInt(st.nextToken());

                tree[s].add(new Node(node, weight));

                node = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        dfs(1, 0);

        Arrays.fill(vis, false);

        dfs(x, 0);

        System.out.println(maxWeight);
    }

    static void dfs(int s, int weight) {
        vis[s] = true;

        if (weight > maxWeight) {
            maxWeight = weight;
            x = s;
        }

        for (Node node : tree[s]) {
            if (!vis[node.node]) {
                dfs(node.node, weight + node.weight);
            }
        }
    }

    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}