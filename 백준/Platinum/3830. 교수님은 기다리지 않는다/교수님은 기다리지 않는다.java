import java.io.*;
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
                sb.append(findValue(b) - findValue(a));
            } else {
                sb.append("UNKNOWN");
            }

            sb.append('\n');
        }

        main(args);
    }

    static int find(int a) {
        while (a != parents[a]) {
            a = parents[a];
        }

        return a;
    }

    static int findValue(int a) {
        int v = 0;

        while (a != parents[a]) {
            v += diffs[a];
            a = parents[a];
        }

        return v;
    }

    static void merge(int a, int b, int diff) {
        int aa = find(a);
        int ab = find(b);

        if (heights[aa] < heights[ab]) {
            int temp = a;
            int temp2 = aa;

            a = b;
            aa = ab;
            b = temp;
            ab = temp2;
            diff *= -1;
        }

        parents[ab] = aa;
        diffs[ab] = findValue(a) - findValue(b) + diff;

        if (heights[aa] == heights[ab]) {
            heights[aa]++;
        }
    }
}