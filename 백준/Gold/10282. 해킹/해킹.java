import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static final int INF = (int) 1e9;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<int[]>[] edges;
    static int tc, n, d, c;

    public static void main(String[] args) throws IOException {
        tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edges = new List[n + 1];

            for (int u = 1; u <= n; u++) {
                edges[u] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int v = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                edges[u].add(new int[]{v, w});
            }

            travel(c);
        }

        System.out.println(sb);
    }

    private static void travel(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dists = new int[n + 1];

        Arrays.fill(dists, INF);

        pq.add(new int[]{start, 0});
        dists[start] = 0;

        while (!pq.isEmpty()) {
            int u = pq.peek()[0];
            int cost = pq.poll()[1];

            for (int[] edge : edges[u]) {
                int v = edge[0];
                int w = edge[1];

                if (dists[v] > cost + w) {
                    pq.add(new int[]{v, cost + w});
                    dists[v] = cost + w;
                }
            }
        }

        int count = 0;
        int maxDist = 0;

        for (int u = 1; u <= n; u++) {
            if (dists[u] != INF) {
                count++;
                maxDist = Math.max(maxDist, dists[u]);
            }
        }

        sb.append(count).append(' ').append(maxDist).append('\n');
    }

}
