import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static StringTokenizer st;
    static int[] seq;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = nextInt();
        m = nextInt();

        st = new StringTokenizer(br.readLine());
        seq = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            seq[i] = seq[i - 1] + nextInt();
        }

        StringBuilder sb = new StringBuilder();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = nextInt();
            int e = nextInt();

            sb.append(seq[e] - seq[s - 1]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}