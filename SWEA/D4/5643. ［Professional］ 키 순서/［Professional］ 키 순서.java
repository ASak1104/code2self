import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static List<Integer>[] natGraph, revGraph;
    private static int T, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            natGraph = new List[N];
            revGraph = new List[N];

            for (int u = 0; u < N; u++) {
                natGraph[u] = new ArrayList<>();
                revGraph[u] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                natGraph[u].add(v);
                revGraph[v].add(u);
            }

            int answer = 0;

            for (int u = 0; u < N; u++) {
                int count = -1;

                count += travel(u, natGraph, new boolean[N]);
                count += travel(u, revGraph, new boolean[N]);

                if (count == N) {
                    answer++;
                }
            }

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static int travel(int u, List<Integer>[] graph, boolean[] visit) {
        visit[u] = true;

        int count = 1;

        for (int v : graph[u]) {
            if (!visit[v]) {
                count += travel(v, graph, visit);
            }
        }

        return count;
    }

}
