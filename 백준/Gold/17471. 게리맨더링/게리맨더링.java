import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = (int) 1e9;

    private static List<int[]> roads;
    private static int[] people;
    private static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        roads = new ArrayList<>(N);
        people = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int u = 0; u < N; u++) {
            people[u] = Integer.parseInt(st.nextToken());
        }

        for (int u = 0; u < N; u++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int[] edges = new int[k];

            for (int i = 0; i < k; i++) {
                edges[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            roads.add(edges);
        }

        answer = INF;

        simulate(0, new ArrayList<>(N), new ArrayList<>(N));

        System.out.println(answer != INF ? answer : -1);
        br.close();
    }

    private static void simulate(int i, List<Integer> lefts, List<Integer> rights) {
        if (i == N) {
            calculate(lefts, rights);

            return;
        }

        lefts.add(i);
        simulate(i + 1, lefts, rights);
        lefts.remove(lefts.size() - 1);

        rights.add(i);
        simulate(i + 1, lefts, rights);
        rights.remove(rights.size() - 1);
    }

    private static void calculate(List<Integer> lefts, List<Integer> rights) {
        if (lefts.isEmpty() || rights.isEmpty()) {
            return;
        }

        if (!isConnected(lefts) || !isConnected(rights)) {
            return;
        }

        int left = 0;
        int right = 0;

        for (int u : lefts) {
            left += people[u];
        }

        for (int u : rights) {
            right += people[u];
        }

        answer = Math.min(answer, Math.abs(left - right));
    }

    private static boolean isConnected(List<Integer> nodes) {
        boolean[] isTargets = new boolean[N];

        for (int node : nodes) {
            isTargets[node] = true;
        }

        travel(nodes.get(0), isTargets);

        for (int node : nodes) {
            if (isTargets[node]) {
                return false;
            }
        }

        return true;
    }

    private static void travel(int u, boolean[] isTargets) {
        isTargets[u] = false;

        for (int v : roads.get(u)) {
            if (isTargets[v]) {
                travel(v, isTargets);
            }
        }
    }

}
