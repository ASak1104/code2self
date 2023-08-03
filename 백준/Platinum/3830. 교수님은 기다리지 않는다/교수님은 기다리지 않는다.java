import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] parents = new int[100_001];
    static int[] heights = new int[100_001];
    static int[] diffs = new int[100_001];
    static StringTokenizer st;
    static int n, m;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 0 && m == 0) {
            System.out.println(sb);
            return;
        }

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            heights[i] = 1;
            diffs[i] = 0;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());

            char cmd = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == '!') {
                int diff = Integer.parseInt(st.nextToken());

                merge(a, b, diff);

                continue;
            }

            if (find(a) == find(b)) {
                sb.append(diffs[b] - diffs[a]);
            } else {
                sb.append("UNKNOWN");
            }

            sb.append('\n');
        }

        main(args);
    }

    static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        int aa = find(parents[a]);

        diffs[a] += diffs[parents[a]];

        return parents[a] = aa;
    }

    static void merge(int a, int b, int diff) {
        int aa = find(a);
        int ab = find(b);

        if (aa == ab) return;

        int newDiff = diffs[a] + diff;
        int oriDiff = diffs[b];

        parents[ab] = aa;
        diffs[ab] = newDiff - oriDiff;
    }
}