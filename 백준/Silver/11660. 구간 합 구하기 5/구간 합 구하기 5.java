import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static StringTokenizer st;
    static int[][] seq;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = nextInt();
        m = nextInt();

        seq = new int[n + 1][n + 1];

        for (int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 1; c <= n; c++) {
                seq[r][c] = nextInt() + seq[r - 1][c] + seq[r][c - 1] - seq[r - 1][c - 1];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int rs = nextInt();
            int cs = nextInt();
            int re = nextInt();
            int ce = nextInt();

            int res = seq[re][ce] - seq[re][cs - 1] - seq[rs - 1][ce] + seq[rs - 1][cs - 1];

            sb.append(res).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}