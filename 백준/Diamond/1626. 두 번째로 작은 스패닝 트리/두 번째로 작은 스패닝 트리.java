import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Main {
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static Edge[] edges;
    static List<Edge>[] mstEdges;
    static int[][] ancestors, maxWeights, smaxWeights;
    static int[] parents, depths;
    static int V, E, H, MST;
    static int SMST, max, smax;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        V = readInt();
        E = readInt();
        H = 1;

        while ((1 << H) < V) H++;

        edges = new Edge[E];
        mstEdges = new List[V + 1];

        for (int i = 0; i < E; i++) {
            edges[i] = new Edge(readInt(), readInt(), readInt());
        }

        for (int i = 1; i <= V; i++) {
            mstEdges[i] = new ArrayList<>();
        }

        int count = findMST();

        if (E < V - 1 || count != V - 1) {
            System.out.println(-1);
            return;
        }

        build();
        findSMST();

        if (SMST == Integer.MAX_VALUE) {
            SMST = -1;
        }

        System.out.println(SMST);
    }

    static void findSMST() {
        SMST = Integer.MAX_VALUE;

        for (Edge edge : edges) {
            if (edge.isMST) continue;

            findMaxSMax(edge.u, edge.v);

            if (max >= 0) {
                int smst = MST - max + edge.w;

                if (smst > MST) {
                    SMST = Math.min(SMST, smst);
                }
            }

            if (smax >= 0) {
                int smst = MST - smax + edge.w;

                if (smst > MST) {
                    SMST = Math.min(SMST, smst);
                }
            }
        }
    }

    static void findMaxSMax(int a, int b) {
        if (depths[a] < depths[b]) {
            int temp = a;

            a = b;
            b = temp;
        }

        max = -1;
        smax = -1;

        for (int i = H; i >= 0; i--) {
            if (depths[a] == depths[b]) break;

            int aa = ancestors[a][i];

            if (depths[aa] >= depths[b]) {
                setMaxSMax(maxWeights[a][i]);
                setMaxSMax(smaxWeights[a][i]);
                a = aa;
            }
        }

        if (a == b) return;

        for (int i = H; i >= 0; i--) {
            int aa = ancestors[a][i];
            int ab = ancestors[b][i];

            if (aa != ab) {
                setMaxSMax(maxWeights[a][i]);
                setMaxSMax(smaxWeights[a][i]);
                setMaxSMax(maxWeights[b][i]);
                setMaxSMax(smaxWeights[b][i]);

                a = aa;
                b = ab;
            }
        }

        setMaxSMax(maxWeights[a][0]);
        setMaxSMax(smaxWeights[a][0]);
        setMaxSMax(maxWeights[b][0]);
        setMaxSMax(smaxWeights[b][0]);
    }

    static void setMaxSMax(int v) {
        if (max < v) {
            smax = max;
            max = v;
        } else if (max > v) {
            smax = Math.max(smax, v);
        }
    }

    static void build() {
        depths = new int[V + 1];
        ancestors = new int[V + 1][H + 1];
        maxWeights = new int[V + 1][H + 1];
        smaxWeights = new int[V + 1][H + 1];

        for (int i = 0; i <= V; i++) {
            Arrays.fill(maxWeights[i], -1);
            Arrays.fill(smaxWeights[i], -1);
        }

        depths[0] = -1;

        travel(0, 1);

        for (int i = 1; i <= H; i++) {
            for (int u = 1; u <= V; u++) {
                if (ancestors[u][i - 1] == 0) continue;

                int au = ancestors[u][i - 1];
                int auMax = maxWeights[au][i - 1];

                ancestors[u][i] = ancestors[au][i - 1];
                maxWeights[u][i] = Math.max(maxWeights[u][i - 1], auMax);
                smaxWeights[u][i] = smaxWeights[u][i - 1];

                if (auMax != maxWeights[u][i] || smaxWeights[u][i] == maxWeights[u][i]) {
                    smaxWeights[u][i] = auMax;
                }
            }
        }
    }

    static void travel(int p, int u) {
        depths[u] = depths[p] + 1;
        ancestors[u][0] = p;

        for (Edge edge : mstEdges[u]) {
            int v = (edge.u == u) ? edge.v : edge.u;

            if (v != p) {
                travel(u, v);

                maxWeights[v][0] = Math.max(maxWeights[v][0], edge.w);
                smaxWeights[v][0] = Math.max(smaxWeights[v][0], edge.w);
            }
        }
    }

    static int findMST() {
        Arrays.sort(edges, Comparator.comparingInt(a -> a.w));

        int edgeCount = 0;

        MST = 0;
        parents = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        for (Edge edge : edges) {
            if (edgeCount == V - 1) break;

            if (find(edge.u) == find(edge.v)) continue;

            edgeCount++;
            MST += edge.w;
            edge.isMST = true;
            mstEdges[edge.u].add(edge);
            mstEdges[edge.v].add(edge);

            merge(edge.u, edge.v);
        }

        return edgeCount;
    }

    static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    static void merge(int a, int b) {
        a = find(a);
        b = find(b);

        parents[b] = a;
    }
}

class Edge {
    int u;
    int v;
    int w;
    boolean isMST = false;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}