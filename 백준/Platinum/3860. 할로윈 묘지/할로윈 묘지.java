import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    private static final int INF = (int) 1e9;
    static StringTokenizer st;
    static boolean[][] cantMove;
    static boolean[][] cantArrive;
    static List<Edge> edges;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static int[] dists;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int w = nextInt();
            int h = nextInt();

            if (w == 0 && h == 0) break;

            n = w * h;
            cantMove = new boolean[h][w];
            cantArrive = new boolean[h][w];
            edges = new ArrayList<>(w * h * 4);

            int g = Integer.parseInt(br.readLine());

            while (g-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x1 = nextInt();
                int y1 = nextInt();

                cantArrive[y1][x1] = true;
            }

            int e = Integer.parseInt(br.readLine());

            while (e-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x1 = nextInt();
                int y1 = nextInt();
                int x2 = nextInt();
                int y2 = nextInt();
                int t = nextInt();

                int u = y1 * w + x1;
                int v = y2 * w + x2;

                cantMove[y1][x1] = true;
                edges.add(new Edge(u, v, t));
            }

            cantMove[h - 1][w - 1] = true;

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (cantMove[y][x]) continue;

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;

                        if (cantArrive[ny][nx]) continue;

                        int u = y * w + x;
                        int v = ny * w + nx;

                        edges.add(new Edge(u, v, 1));
                    }
                }
            }

            bellmanFord();

            if (hasNegativeCycle()) {
                sb.append("Never");
            } else if (dists[n - 1] == INF) {
                sb.append("Impossible");
            } else {
                sb.append(dists[n - 1]);
            }

            sb.append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static void bellmanFord() {
        dists = new int[n];
        Arrays.fill(dists, INF);
        dists[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge e : edges) {
                int dw = dists[e.u] + e.w;

                if (dists[e.u] != INF && dw < dists[e.v]) {
                    dists[e.v] = dw;
                }
            }
        }
    }

    static boolean hasNegativeCycle() {
        for (Edge e : edges) {
            int dw = dists[e.u] + e.w;

            if (dists[e.u] != INF && dw < dists[e.v]) {
                return true;
            }
        }

        return false;
    }
}

class Edge {
    int u;
    int v;
    int w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}